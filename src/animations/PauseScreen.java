//ID 318525185
package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/** .
 * this class is animation of pause screen.
 * */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /** create PauseScreen.
     * @param k is a keyboard sensor.
     * */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /** do one frame of pause screen on gaven draw surface.
     * @param d the gaven draw surface.
     * */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    /** return true if the animation should stop.
     * @return true if the animation should stop, false otherwise.
     * */
    public boolean shouldStop() {
        return this.stop; }
}