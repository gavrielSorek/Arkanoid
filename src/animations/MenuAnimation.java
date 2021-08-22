package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Sprite;
import geometry.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/** this class represent a menu animation.
 * @param <T> is the return value of the menu.
 * */
public class MenuAnimation<T> implements Menu<T> {
    private static final int GAP_BETWEEN_MASSAGES = 50;
    private SpriteCollection spriteCollection = new SpriteCollection();
    private List<Selection> selections = new ArrayList<>();
    private boolean isAlreadyPressed;
    private T status;
    private boolean shouldStop;
    private KeyboardSensor sensor;
    private String name;
    /** the method accept keyboard sensor and name of the menu, and create menu.
     * @param keyboard the keyboard sensor.
     * @param name the menu name.
     * */
    public MenuAnimation(KeyboardSensor keyboard, String name) {
        this.name = name;
        this.sensor = keyboard;
        this.shouldStop = false;
    }
    /** add sprites to the menu.
     * @param s the sprite to add.
     * */
    public void addSprite(Sprite s) {
        this.spriteCollection.addSprite(s);
    }
    /** add selection to the menu.
     * @param key the key to press to choose the selection
     * @param message the name of the selection.
     * @param returnVal the return of the selection that chosen.
     * */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        selections.add(new Selection<T>(key, message, returnVal));
    }
    /** return the status of the menu (the selection that chosen);
     * @return the status of the menu.
     * */
    @Override
    public T getStatus() {
        return this.status;
    }
    /** do one frame, draw the menu and check id selection chosen.
     * @param d the draw surface.
     * */
    @Override
    public void doOneFrame(DrawSurface d) {
        spriteCollection.drawAllOn(d);
        d.setColor(Color.blue);
        d.drawText(10, d.getHeight() / 10, this.name, 50);
        d.setColor(Color.black);
        int gapToWriteTheMessage = 0;
        for (Selection s: selections) {
            d.drawText(10, (d.getHeight() / 4) + gapToWriteTheMessage,
                    "press " + s.getKey() + " to " + s.getMessage(), 32);
            if (this.sensor.isPressed(s.getKey())) { //if the key pressed
                this.status = (T) s.getReturnVal();
                this.shouldStop = true;
            }
            gapToWriteTheMessage = gapToWriteTheMessage + GAP_BETWEEN_MASSAGES; //increase the gap between 2 massages
        }
    }
    /** return true if the animation should stop.
     * @return true if the animation should stop, false otherwise.
     * */
    @Override
    public boolean shouldStop() {
        if (this.shouldStop) {
           shouldStop = false;
           return true;
        } else { //if not should stop
            return false;
        }
    }
}
