//ID 318525185.
package geometry;
import biuoop.DrawSurface;
/**
 * This interface  represent drawnable objects .
 */
public interface Sprite {
    /**.
     * the method draw the sprite to the screen.
     * @param d is the draw surface.
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    /**.
     * notify the sprite that time has passed
     */
    void timePassed();
}