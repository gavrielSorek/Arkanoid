//ID 318525185
package gamefeatures;
import geometry.Velocity;
import geometry.Sprite;
import geometry.Block;
import geometry.Point;
import geometry.Sun;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**.
 * this class represent level in the game, and contain information on the level.
 * */
public class LevelTwo implements LevelInformation {
    /**return the amount of ball in this level.
     * @return the amount of ball in this level.
     * */
    @Override
    public int numberOfBalls() {
        return 10;
    }
    /** create list of velocities , the velocities belong to the balls in this level.
     * @return list of velocities.
     * */
    @Override
    public List<Velocity> initialBallVelocities() {
        //list of Velocities
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        velocities.add(Velocity.fromAngleAndSpeed(20, 6));
        velocities.add(Velocity.fromAngleAndSpeed(160, 6));
        velocities.add(Velocity.fromAngleAndSpeed(25, 6));
        velocities.add(Velocity.fromAngleAndSpeed(155, 6));
        velocities.add(Velocity.fromAngleAndSpeed(30, 6));
        velocities.add(Velocity.fromAngleAndSpeed(150, 6));
        velocities.add(Velocity.fromAngleAndSpeed(35, 6));
        velocities.add(Velocity.fromAngleAndSpeed(145, 6));
        velocities.add(Velocity.fromAngleAndSpeed(160, 6));
        return velocities;
    }
    /** .
     * return this level paddle speed.
     * @return this level paddle speed.
     * */
    @Override
    public int paddleSpeed() {
        return 8;
    }
    /**.
     * return paddle width.
     * @return paddle width
     * */
    @Override
    public int paddleWidth() {
        return 400;
    }
    /**return level name.
     * @return level name.
     * */
    @Override
    public String levelName() {
        return "Wide Easy";
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
        rec.setColor(Color.WHITE);
        //create screen Background
        ScreenBackground screenBackground = new ScreenBackground(rec);
        Point centerOfSun = new Point(100, 100);
        //the edge of sunbeam
        Point mostLeftSunBeam = new Point(GameLevel.BORDER_WIDTH, GameLevel.BOARD_HEIGHT / 3);
        screenBackground.addSprite(new Sun(50, centerOfSun, mostLeftSunBeam));
        return screenBackground;
    }
    /** create this level blocks and returns it.
     * @return list of blocks.
     * */
    @Override
    public List<Block> blocks() {
        Point startRow = new Point(GameLevel.BORDER_WIDTH,
                GameLevel.BOARD_HEIGHT / 3); //start of row block
        List<Block> blockList = new ArrayList<>();
        Block[] blocks = new Block[15];
        //create the blocks
        for (int i = 0; i < blocks.length; i++) {
            Point startOfBlock = new Point(startRow.getX() + Block.DEFAULT_BLOCK_WIDTH  * i, startRow.getY());
            Color color = changeColor(i);
            blocks[i] = Block.createSingleBlock(startOfBlock, color);
        }
        for (Block b: blocks) {
            blockList.add(b);
        }
        return blockList;
    }
    /** return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return return Number of blocks that should be removed.
     * */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
    /** return color according to gaven number.
     * @param num the gaven number.
     * @return color accordingly to the number num.
     * */
    private Color changeColor(int num) {
        if (num <= 1) {
            return Color.RED;
        }
        if (num <= 3) {
            return Color.ORANGE;
        }
        if (num <= 5) {
            return Color.YELLOW;
        }
        if (num <= 8) {
            return Color.GREEN;
        }
        if (num <= 10) {
            return Color.BLUE;
        }
        if (num <= 12) {
            return Color.PINK;
        }
        if (num <= 14) {
            return Color.MAGENTA;
        }
        return Color.CYAN;
    }
}

