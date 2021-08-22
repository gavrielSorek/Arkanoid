//ID 318525185.
package listeners;
import gamefeatures.Counter;
import gamefeatures.GameLevel;
import geometry.Ball;
import geometry.Block;
/**BallRemover is in charge of removing balls from the game, as well as keeping count
   of the number of blocks that remain.
 * */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**the constructor accept game and counter that represent the count of the remaining balls.
     * the constructor create BallRemover object.
     * @param gameLevel the game that BallRemover remove balls from.
     * @param removedBalls the counter that contain the numbers of the remain balls.
     * */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    /** .
     * when hit event occur this method remove the ball from the game.
     * @param beingHit the block that the ball collide with. (does'nt useful in this method).
     * @param hitter the ball that hit with the block, the method remove this ball from the game.
     * */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        getRemainingBalls().decrease(1);
    }
/**
 * Set the number of balls that remain in the game.
 * @param number the amount of the remaining balls.
 * */
    public void setRemainingBalls(int number) {
        this.remainingBalls.setValue(number);
    }
/**
 * Return the counter of the remaining balls.
 * @return the counter of the remaining balls.
 * */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

}

