import animations.KeyPressStoppableAnimation;
import animations.Menu;
import animations.ShowHighScore;
import animations.MenuAnimation;
import animations.Animation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamefeatures.LevelInformation;
import gamefeatures.GameFlow;
import gamefeatures.Task;
import gamefeatures.AnimationRunner;
import gamefeatures.UpdateHighScores;
import gamefeatures.GameLevel;
import gamefeatures.ScreenBackground;
import geometry.Sprite;
import geometry.BeautifulBuilding;
import geometry.Target;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.List;
/** this class represent the game menu and operate the game.
 * */
public class GameMenu {
    private List<LevelInformation> levels;
    private GameFlow gameFlow;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    /** accept animation runner and keyboard sensor and gui.
     * create GameMenu object.
     * @param ar the animation runner.
     * @param ks the keyboard sensor.
     * @param gui the gui of the game.
     * @param gameFlow run the levels of the game.
     * @param levels the list of levels to run.
     * */
    public GameMenu(AnimationRunner ar, KeyboardSensor ks, GUI gui, GameFlow gameFlow, List<LevelInformation> levels) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.gameFlow = gameFlow;
        this.levels = levels;
    }
    /** the method run the menu of the game.
     * */
    public void run() {
        MenuAnimation<Task<Void>> menuA = new MenuAnimation<Task<Void>>(this.keyboardSensor, "GAME MENU");
        menuA.addSprite(menuBackground());
        Menu<Task<Void>> menu = menuA;
            Task<Void> scoreTask = showHighScoreTask();
            Task<Void> gameTask = new Task<Void>() { //game animation
                @Override
                public Void run() {
                    gameFlow.runLevels(levels);
                    return null;
                }
            };
            Task<Void> quit = new Task<Void>() { //quit task
                @Override
                public Void run() {
                    System.exit(0);
                    return null;
                }
            };
            //add the tasks
            menu.addSelection("h", "high score", scoreTask);
            menu.addSelection("s", "start game", gameTask);
            menu.addSelection("q", "quit", quit);
            while (true) {
                this.animationRunner.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
                task.run();
            }
    }
    /** create a show high score task.
     * @return the show high score task.
     * */
    private Task<Void> showHighScoreTask() {
        UpdateHighScores highScore = new UpdateHighScores();
        int hScore = highScore.getCurrentHighScore();
        Task<Void> showHighScoresTask = new Task<Void>() { //create show highScore Task
            @Override
            public Void run() {
                Animation scoreAnimation = new ShowHighScore();
                KeyPressStoppableAnimation stoppableAnimation = new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, scoreAnimation);
                animationRunner.run(stoppableAnimation); //run score animation
                return null;
            }
        };
        return showHighScoresTask;
    }
    /** create a background for the menu.
     * @return the background.
     * */
    public Sprite menuBackground() {
        //rec represent the Screen Background
        Rectangle rec = new Rectangle(new Point(0, 0), GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        rec.setColor(Color.GREEN);
        ScreenBackground screenBackground = new ScreenBackground(rec);
        //add pictures to the background.
        screenBackground.addSprite(new Target(100, Color.BLUE, new Point(600, 150)));
        Point topLeftBuildingPoint = new Point(550, 400);
        screenBackground.addSprite(new BeautifulBuilding(topLeftBuildingPoint, 200, 100));
        return screenBackground;
    }
}
