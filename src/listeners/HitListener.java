//ID 318525185.
package listeners;
import geometry.Ball;
import geometry.Block;
/** .
 * this interface represent object that listen to collides in the game.
 * */
public interface HitListener {
    /** This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that the ball collide with.
     * @param hitter the ball that hit the beingHit block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}