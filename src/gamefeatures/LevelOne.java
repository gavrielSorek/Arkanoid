package gamefeatures;
import geometry.Block;
import geometry.Sprite;
import geometry.Velocity;
import geometry.Point;
import geometry.Rectangle;
import geometry.Target;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**.
 * this class represent level in the game, and contain information on the level.
 * */
public class LevelOne implements LevelInformation {
    /**return the amount of ball in this level.
     * @return the amount of ball in this level.
     * */
    @Override
    public int numberOfBalls() {
        return 1;
    }
    /** create list of velocity , the velocities belong to the balls in this level.
     * @return list of velocities.
     * */
    @Override
    public List<Velocity> initialBallVelocities() {
        //list of Velocities
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(40, 6));
        return velocities;
    }
    /** .
     * return this level paddle speed.
     * @return this level paddle speed.
     * */
    @Override
    public int paddleSpeed() {
        return 7;
    }
    /**.
     * return paddle width.
     * @return paddle width
     * */
    @Override
    public int paddleWidth() {
        return 100;
    }
    /**return level name.
     * @return level name.
     * */
    @Override
    public String levelName() {
        return "Direct Hit";
    }
    /** create a beautiful background and return it .
     * @return background of this level.
     * */
    @Override
    public Sprite getBackground() {
        //rec represent the Screen Background
        Rectangle rec = new Rectangle(new Point(GameLevel.BORDER_WIDTH, GameLevel.BORDER_WIDTH),
                GameLevel.BOARD_WIDTH - GameLevel.BORDER_WIDTH * 2,
                GameLevel.BOARD_HEIGHT -  GameLevel.BORDER_WIDTH);
        rec.setColor(Color.BLACK);
        ScreenBackground screenBackground = new ScreenBackground(rec);
        screenBackground.addSprite(new Target(160, Color.BLUE, new Point(400, 225)));
        return screenBackground;
    }
    /** create this level blocks and returns it.
     * @return list of blocks.
     * */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Rectangle rec = new Rectangle(new Point(375, 200), 50, 50); //main block
        blockList.add(new Block(rec, Color.RED)); // add the block
        return blockList;
    }
    /** return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return return Number of blocks that should be removed.
     * */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
