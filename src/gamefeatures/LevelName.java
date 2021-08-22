//ID 318525185
package gamefeatures;
import biuoop.DrawSurface;
import geometry.Sprite;
import java.awt.Color;
/** this class represent level name, and can draw the name to gaven surface.
 * */
public class LevelName implements Sprite {
    private static final int DISTANCE_FROM_THE_CENTER_X = 100;
    private static final int FONT_SIZE = 15;
    private static final int HEIGHT_OF_TEXT = 15;
    private String levelName;

    /**
     * create level name object accordingly to the gaven name.
     *
     * @param levelName the name of the level.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    /** draw the name of the level in the upper part of gaven draw surface.
     * @param d gaven surface.
     * */
    @Override
    public void drawOn(DrawSurface d) {
        String scoreString = "Level Name: " + this.levelName;
        d.setColor(Color.BLACK);
        d.drawText((GameLevel.BOARD_WIDTH / 2) + DISTANCE_FROM_THE_CENTER_X,
                HEIGHT_OF_TEXT, scoreString, FONT_SIZE);
    }

    /**
     * does'nt used.
     */
    @Override
    public void timePassed() {
    }
}
