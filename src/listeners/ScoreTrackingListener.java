//ID 318525185.
package listeners;
import gamefeatures.Counter;
import geometry.Ball;
import geometry.Block;
/**.
 * listener that tracking the score in the game.
 * */
public class ScoreTrackingListener implements HitListener {
    public static final int SCORE_OF_IMPACT_BLOCK = 5;
    private Counter currentScore;
    /**
     * the constructor accept counter and create ScoreTrackingListener object.
     * @param scoreCounter the counter that represent the score in the game.
     * */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /** when ball collide with block this method increase the score in 5 points.
     * @param beingHit the block that the ball collide with.
     * @param hitter the ball that collide with the block.
     * */
    public void hitEvent(Block beingHit, Ball hitter) {
      this.currentScore.increase(SCORE_OF_IMPACT_BLOCK);
    }
}

