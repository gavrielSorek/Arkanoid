//ID 318525185
package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/** .
 * this class decorate animation and capable to stop the animation.
 * */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private Animation animation;
    private KeyboardSensor sensor;
    private String key;
    private boolean stop = false;
    /**create KeyPressStoppableAnimation with gaven key and keyboard sensor and animation.
     * when the "key" pressed the animation stop.
     * @param sensor keyboard sensor.
     * @param key the key to stop the animation.
     * @param animation gaven animation.
     * */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }
    /** this function draw the animation on gaven draw surface.
     *  if the key pressed stop become true and the animation stops.
     * @param d the gaven draw surface.
     * */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!this.sensor.isPressed(key)) { //if the key doesnt pressed
            this.isAlreadyPressed = false;
        }
        if (this.sensor.isPressed(key) && !isAlreadyPressed) { //if the key is pressed and not was pressed before
            this.stop = true;
        }
    }
    /** if the animation should stop return true.
     * @return true if the animation should stop, false otherwise.
     * */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}