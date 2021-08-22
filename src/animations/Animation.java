//ID 318525185
package animations;
import biuoop.DrawSurface;
/**this interface in charge of game animation.*/
public interface Animation {
    /**the method do one frame on gaven draw surface.
     * @param d the draw surface.
     * */
    void doOneFrame(DrawSurface d);
    /**
     * the method notify when the animation should stop.
     * @return true if the animation should stop, false otherwise.
     **/
    boolean shouldStop();
}