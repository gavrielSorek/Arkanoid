//ID 318525185
package gamefeatures;
import animations.Animation;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Block;
import geometry.Collidable;
import geometry.Paddle;
import geometry.Sprite;
import geometry.SpriteCollection;
import geometry.Ball;
import geometry.Velocity;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import java.awt.Color;
import java.util.List;
/**
 * This class run game accordingly to gaven level.
 */
public class GameLevel implements Animation {
    private static final int DISTANCE_OF_BALLS_FROM_PADDLE = 200;
    private LevelInformation levelInformation;
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter amountOfRemainBlocks = new Counter();
    private Counter amountOfRemainBalls = new Counter();
    private Counter score = new Counter();
    private GUI gui; //the screen
    public static final int BORDER_WIDTH = 25;
    public static final java.awt.Color BORDER_COLOR = Color.gray;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 10;
    /**.
     *  accept level info, keyboard sensor, animationRunner, score, gui.
     *  create new GameLevel object that represent level in the game.
     * @param levelInfo contain information on the level.(amount of blocks, balls...)
     * @param keyboardSensor the keyboard sensor.
     * @param animationRunner the animation runner.
     * @param score the score of the player.
     * @param gui the gui of the game.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score, GUI gui) {
        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.score = score;
        this.gui = gui;
    }
    /**.
     * The method add collidable object to the environment of the game.
     * @param c is the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**.
     * The method add sprite object to the environment of the game.
     * @param s is the sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    /**.
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     */
    public void initialize() {
        addSprite(this.levelInformation.getBackground());
        HitListener lis = new BlockRemover(this, this.amountOfRemainBlocks);
        this.runner = new AnimationRunner(this.gui, 60);
        Block deathRegion = Block.deathRegionCreator(BOARD_HEIGHT, BOARD_WIDTH); //death region block
        //add ball remover listener
        deathRegion.addHitListener(new BallRemover(this, this.amountOfRemainBalls));
        Paddle paddle = createPaddle(); //create paddle
        paddle.addToGame(this); //add the paddle to game
        Block[] borders = Block.createBordersFromBlocks(GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        deathRegion.addToGame(this);
        for (Block b:borders) {
            b.addToGame(this);
        }
        //add the blocks
        for (Block b:this.levelInformation.blocks()) { //for every block in the list
            b.addToGame(this);
            b.addHitListener(lis);
        }
        this.amountOfRemainBlocks.increase(this.levelInformation.numberOfBlocksToRemove());
        this.amountOfRemainBalls.increase(this.levelInformation.numberOfBalls());
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, BOARD_WIDTH, BOARD_HEIGHT);
        scoreIndicator.addToGame(this);
        addSprite(new LevelName(this.levelInformation.levelName()));
    }
    /**.
     * create paddle for the game.
     * @return the paddle.
     */
    private Paddle createPaddle() {
        Point upperLeft = new Point((BOARD_WIDTH / 2) - (this.levelInformation.paddleWidth()) / 2,
                BOARD_HEIGHT - BORDER_WIDTH - PADDLE_HEIGHT); //the upper left paddle point
        Rectangle paddleRec = new Rectangle(upperLeft, this.levelInformation.paddleWidth(),
               PADDLE_HEIGHT);
        Paddle paddle = new Paddle(paddleRec, Color.yellow, this.keyboard, this.levelInformation.paddleSpeed());
        return paddle;
    }
    /**the method remove collidable object.
     * @param c the gaven collidable object to remove
     * */
    public void removeCollidable(Collidable c) {
        GameEnvironment gameEnvironment = getGameEnvironment();
        gameEnvironment.removeCollidable(c);
    }
    /**remove sprite object.
     * @param s the sprite object to remove.
     * */
    public void removeSprite(Sprite s) {
        SpriteCollection spriteCollection = getSpriteCollection();
        spriteCollection.removeSprite(s);
    }
    /** return the sprite sprite collection.
     * @return the sprite sprite collection.
     * */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }
    /** return the game environment.
     * @return the game environment.
     * */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }
    /**return the score counter.
     * @return the score counter.
     * */
    public Counter getScore() {
        return this.score;
    }
    /** return true if the animation should stop.
     * @return  true if the animation should stop, false otherwise.
     * */
    public boolean shouldStop() { return !this.running; }
    /**
     * do one frame of the game board, and draw it on gaven surface.
     * @param d gaven surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //if there is no more blocks/balls
        if ((this.amountOfRemainBlocks.getValue() == 0) || (this.amountOfRemainBalls.getValue() == 0)) {
            if (this.amountOfRemainBlocks.getValue() == 0) {
                this.score.increase(100);
            }
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            KeyPressStoppableAnimation stopAble = new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(stopAble);
        }
    }
    /** run this level.
     * */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.getSpriteCollection()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    /**
     * this method create environment of balls, and puts it on given environment.
     * this method locate balls on the board, and determine their velocities and sizes.
     * @return array of balls that located in different location on the screen.
     */
    public Ball[] createBallsOnTopOfPaddle() {
        int numberOfBalls = this.levelInformation.numberOfBalls();
        int numberOfVelocities = this.levelInformation.initialBallVelocities().size();
        List<Velocity> velocityList = this.levelInformation.initialBallVelocities();
        //create the balls
        Ball[] balls = new Ball[numberOfBalls]; //create balls
        Velocity[] velocities = new Velocity[numberOfVelocities];
        int i = 0;
        for (Velocity v: velocityList) { //add the velocity to array
            velocities[i] = v;
            i++;
        }
        if (numberOfBalls == 0) {
            throw new RuntimeException("no balls");
        }
        int degreeBetweenBalls = 140 / numberOfBalls; //range od degree
        //create balls and add the velocities to the balls
        for (i = 0; i < numberOfBalls && i < numberOfVelocities; i++) {
            double x = calculateXOfBallsAbovePaddle(i, degreeBetweenBalls);
            double y = calculateYOfBallsAbovePaddle(i, degreeBetweenBalls);
            balls[i] = new Ball(x, y, 7, Color.WHITE);
            balls[i].setVelocity(velocities[i]);
        }
        for (Ball b:balls) {
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
        return balls;
    }
    /** this method accept the number of ball and range of degrees between to balls.
     * this method  return the x coordinate in order to put the ball symmetrical above the paddle.
     * @param numberOfTheBall the number of ball.
     * @param degreeBetweenBalls the decree of 2 balls accordingly to the paddle.
     * @return the x coordinate to put the ball.
     * */
    private double calculateXOfBallsAbovePaddle(int numberOfTheBall, int degreeBetweenBalls) {
        int degree;
        if (numberOfTheBall % 2 == 0) {
              degree = 90 + ((numberOfTheBall + 1) / 2) * degreeBetweenBalls;
        } else  {
              degree = 90 - ((numberOfTheBall + 1) / 2) * degreeBetweenBalls;
        }
        //calculate the x coordinate of the ball
        double x = DISTANCE_OF_BALLS_FROM_PADDLE *  Math.cos(Math.toRadians(degree));
        return (x + BOARD_WIDTH / 2);
    }
    /** this method accept the number of ball and range of degrees between to balls.
     * this method  return the y coordinate in order to put the ball symmetrical above the paddle.
     * @param numberOfTheBall the number of ball.
     * @param degreeBetweenBalls the decree of 2 balls accordingly to the paddle.
     * @return the y coordinate to put the ball.
     * */
    private double calculateYOfBallsAbovePaddle(int numberOfTheBall, int degreeBetweenBalls) {
        int degree;
        if (numberOfTheBall % 2 == 0) {
            degree = 90 + ((numberOfTheBall + 1) / 2) * degreeBetweenBalls;
        } else  {
            degree = 90 - ((numberOfTheBall + 1) / 2) * degreeBetweenBalls;
        }
        //calculate the x coordinate of the ball
        double y = DISTANCE_OF_BALLS_FROM_PADDLE  *  Math.sin(Math.toRadians(degree));
        return  (BOARD_HEIGHT - y);
    }
    /**.
     * return the amount of remain balls in this level.
     * @return the remain balls.
     * */
    public Counter getAmountOfRemainBalls() {
        return amountOfRemainBalls;
    }
    /**.
     * return the amount of remain blocks in this level.
     * @return the amount of blocks.
     * */
    public Counter getAmountOfRemainBlocks() {
        return amountOfRemainBlocks;
    }
}