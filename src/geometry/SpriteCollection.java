//ID 318525185.
/**
 * This class represent sprite object collection.
 */
package geometry;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * This class represent sprites object, this class use list to save the sprites objects.
 */
public class SpriteCollection {
    private List<Sprite> spriteAbles = new ArrayList<Sprite>();
    /**.
     * The method add sprite to the list
     *
     * @param s is a sprite object that the method add to list.
     */
    public void addSprite(Sprite s) {
        this.spriteAbles.add(s);
    }

    /**.
     * the method call method timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteAbles);
        for (Sprite s: sprites) {
            s.timePassed();
        }
    }
    /**.
     * the method call drawOn(d) on all sprites.
     * the method notify all the sprites to draw their objects.
     * @param d is the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: spriteAbles) {
            s.drawOn(d);
        }
    }
    /** remove sprite from the sprites list.
     * @param s the sprite to remove.
     * */
    public void removeSprite(Sprite s) {
        List<Sprite> sprites = getSprites();
        sprites.remove(s);
    }
    /** return the sprites list.
     * @return return the sprites list.
     * */
    public List<Sprite> getSprites() {
        return this.spriteAbles;
    }
}