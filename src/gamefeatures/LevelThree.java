//ID 318525185
package gamefeatures;
import geometry.Velocity;
import geometry.Point;
import geometry.Block;
import geometry.Sprite;
import geometry.BeautifulBuilding;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**.
 * this class represent level in the game, and contain information on the level.
 * */
public class LevelThree implements LevelInformation {
    /**return the amount of ball in this level.
     * @return the amount of ball in this level.
     * */
    @Override
    public int numberOfBalls() {
        return 2;
    }
    /** create list of velocities , the velocities belong to the balls in this level.
     * @return list of velocities.
     * */
    @Override
    public List<Velocity> initialBallVelocities() {
        //list of Velocities
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(160, 6));
        velocities.add(Velocity.fromAngleAndSpeed(20, 6));
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
        return 200;
    }
    /**return level name.
     * @return level name.
     * */
    @Override
    public String levelName() {
        return "green 3";
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
        rec.setColor(Color.GREEN);
        //create screen Background
        ScreenBackground screenBackground = new ScreenBackground(rec);
        Point topLeftBuildingPoint = new Point(100, 400);
        screenBackground.addSprite(new BeautifulBuilding(topLeftBuildingPoint, 200, 100));
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
        Block[] blocksRow1;
        //create the blocks
        Point startOfBlocksRow = new Point(275, GameLevel.BOARD_HEIGHT / 4);
        blocksRow1 = Block.createRowOfBlocks(startOfBlocksRow, 10, Color.GRAY);
        startOfBlocksRow = new Point(startOfBlocksRow.getX() + Block.DEFAULT_BLOCK_WIDTH,
                startOfBlocksRow.getY() + Block.DEFAULT_BLOCK_HEIGHT);
        Block[] blocksRow2;
        blocksRow2 = Block.createRowOfBlocks(startOfBlocksRow, 9, Color.RED);
        startOfBlocksRow = new Point(startOfBlocksRow.getX() + Block.DEFAULT_BLOCK_WIDTH,
                startOfBlocksRow.getY() + Block.DEFAULT_BLOCK_HEIGHT);
        Block[] blocksRow3;
        blocksRow3 = Block.createRowOfBlocks(startOfBlocksRow, 8, Color.YELLOW);
        startOfBlocksRow = new Point(startOfBlocksRow.getX() + Block.DEFAULT_BLOCK_WIDTH,
                startOfBlocksRow.getY() + Block.DEFAULT_BLOCK_HEIGHT);
        Block[] blocksRow4;
        blocksRow4 = Block.createRowOfBlocks(startOfBlocksRow, 7, Color.BLUE);
        startOfBlocksRow = new Point(startOfBlocksRow.getX() + Block.DEFAULT_BLOCK_WIDTH,
                startOfBlocksRow.getY() + Block.DEFAULT_BLOCK_HEIGHT);
        Block[] blocksRow5;
        blocksRow5 = Block.createRowOfBlocks(startOfBlocksRow, 6, Color.WHITE);
        //add the blocks to the list
        for (Block b: blocksRow1) {
            blockList.add(b);
        }
        for (Block b: blocksRow2) {
            blockList.add(b);
        }
        for (Block b: blocksRow3) {
            blockList.add(b);
        }
        for (Block b: blocksRow4) {
            blockList.add(b);
        }
        for (Block b: blocksRow5) {
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
        return 40;
    }
}
