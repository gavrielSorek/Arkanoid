package animations;

import biuoop.DrawSurface;
import gamefeatures.UpdateHighScores;
/** this class is high score animation.
 * */
public class ShowHighScore implements Animation {
    /**the method do one frame on gaven draw surface, the method draw the high score.
     * @param d the draw surface.
     * */
    @Override
    public void doOneFrame(DrawSurface d) {
        int highScore = new UpdateHighScores().getCurrentHighScore();
        if (highScore == UpdateHighScores.NO_SCORE) { //if there is no score
            d.drawText(20, d.getHeight() / 2, "NO SCORE YET ", 32);
        } else {
            d.drawText(20, d.getHeight() / 2, "THE HIGH SCORE IS: " + highScore, 32);
        }
    }
    /**
     * the method notify when the animation should stop.
     * @return true if the animation should stop, false otherwise.
     **/
    @Override
    public boolean shouldStop() {
        return false;
    }
}
