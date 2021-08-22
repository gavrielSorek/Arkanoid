//ID 318525185
package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/** .
 * this class represent animation of wining screen, this class print the score in the end of the game.
 * */
public class YowWinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * constructor of YowWinScreen.
     *
     * @param k     is the keyboard sensor.
     * @param score is the score of the player at the end of the game.
     */
    public YowWinScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    /**
     * do one frame print massage and score on given draw surface.
     *
     * @param d the draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
    }

    /**
     * return true if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
