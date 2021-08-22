//ID 318525185.
package listeners;
import geometry.Block;
import gamefeatures.Counter;
import gamefeatures.GameLevel;
import geometry.Ball;
/** a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private static final int SCORE_OF_BLOCK_COLLISION = 5;
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**the constructor accept game and counter that represent the count of the remaining blocks.
     * the constructor create BlockRemover object.
     * @param gameLevel the game that BlockRemover remove block from.
     * @param removedBlocks the counter that contain the numbers of the remain blocks.
     * */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /** .
     * when hit event occur this method remove the block from the game.
     * @param beingHit the block that the ball collide with, the method remove this block from the game.
     * @param hitter the ball that hit with the block. (does'nt useful in this method).
     * */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        getRemainingBlocks().decrease(1);
        this.gameLevel.getScore().increase(SCORE_OF_BLOCK_COLLISION);
    }
    /**
     * Set the number of blocks that remain in the game.
     * @param number the amount of the remaining blocks.
     * */
    public void setRemainingBlocks(int number) {
        this.remainingBlocks.setValue(number);
    }
    /**
     * Return the counter of the remaining blocks.
     * @return the counter of the remaining blocks.
     * */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

}