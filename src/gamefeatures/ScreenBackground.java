//ID 318525185
package gamefeatures;
import biuoop.DrawSurface;
import geometry.Rectangle;
import geometry.Sprite;

import java.util.ArrayList;
import java.util.List;
/** this class represent background of level in the game.
 * */
public class ScreenBackground implements Sprite {
    private Rectangle rectangle;
    private List<Sprite> sprites = new ArrayList<>();
    /** accept rectangle and create ScreenBackground object accordingly.
     * @param rectangle the rectangle that represent the screen.
     * */
    public ScreenBackground(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    /** draw this background on gaven surface.
     * @param d gaven surface.
     * */
    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
        for (Sprite s: this.sprites) {
            s.drawOn(d);
        }
    }
    @Override
    /** does'nt used.
     * */
    public void timePassed() {
    }
    /** add this background to gaven game.
     * @param gameLevel the game to add this background.
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /** add sprite object to this background.
     * @param s the sprite object.
     * */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
}
