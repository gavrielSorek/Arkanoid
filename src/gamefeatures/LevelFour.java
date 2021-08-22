//ID 318525185
package gamefeatures;
import java.awt.Color;
import java.util.ArrayList;
import geometry.Block;
import geometry.Sprite;
import geometry.Velocity;
import geometry.Point;
import geometry.Rectangle;
import geometry.Cloud;
import java.util.List;
/**.
 * this class represent level in the game, and contain information on the level.
 * */
public class LevelFour implements LevelInformation {
    /**return the amount of ball in this level.
     * @return the amount of ball in this level.
     * */
    @Override
    public int numberOfBalls() {
        return 3;
    }
    /** create list of velocity , the velocities belong to the balls in this level.
     * @return list of velocities.
     * */
    @Override
    public List<Velocity> initialBallVelocities() {
        //list of Velocities
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(160, 6));
        velocities.add(Velocity.fromAngleAndSpeed(20, 6));
        velocities.add(Velocity.fromAngleAndSpeed(20, 5));
        return velocities;
    }
    /** return this level paddle speed.
     * @return this level paddle speed.
     * */
    @Override
    public int paddleSpeed() {
        return 6;
    }
    /**return paddle width.
     * @return paddle width
     * */
    @Override
    public int paddleWidth() {
        return 150;
    }
    /**return level name.
     * @return level name.
     * */
    @Override
    public String levelName() {
        return "Final Four";
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
        rec.setColor(Color.CYAN);
        //create screen Background
        ScreenBackground screenBackground = new ScreenBackground(rec);
        Point centerOfCloud1 = new Point(100, 400);
        Point centerOfCloud2 = new Point(600, 500);
        screenBackground.addSprite(new Cloud(centerOfCloud1));
        screenBackground.addSprite(new Cloud(centerOfCloud2));
        return screenBackground;
    }
    /** create this level blocks and returns it.
     * @return list of blocks.
     * */
    @Override
    public List<Block> blocks() {
        int blocksInRow = 15;
        int rowsOfBlock = 7;
        Point startRow = new Point(GameLevel.BORDER_WIDTH, GameLevel.BOARD_HEIGHT / 5); //start of row block
        List<Block> blockList = new ArrayList<>();
        Block[][] blocksRows = new Block[rowsOfBlock][blocksInRow]; //all the rows of the blocks
        //create all the blocks
        for (int i = 0; i < blocksRows.length; i++) {
            Color color = changeColor(i);
            blocksRows[i] = Block.createRowOfBlocks(startRow, blocksInRow, color);
            startRow = new Point(startRow.getX(), startRow.getY() + Block.DEFAULT_BLOCK_HEIGHT);
        }
        //add all the blocks to list
        for (Block[] blockRow: blocksRows) {
            for (Block b: blockRow) {
                blockList.add(b);
            }
        }
        return blockList;
    }
    /** return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return return Number of blocks that should be removed.
     * */
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
    /** return color according to gaven number.
     * @param num the gaven number.
     * @return color accordingly to the number num.
     * */
    private Color changeColor(int num) {
        if (num == 0) {
            return Color.GRAY;
        }
        if (num == 1) {
                return Color.RED;
        }
        if (num == 2) {
            return Color.YELLOW;
        }
        if (num == 3) {
            return Color.GREEN;
        }
        if (num == 4) {
            return Color.WHITE;
        }
        if (num == 5) {
            return Color.PINK;
        }
        if (num == 6) {
            return Color.MAGENTA;
        }
        return Color.CYAN;
    }
}
