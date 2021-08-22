//ID 318525185
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
/** this class represent a cloud object, and can draw the cloud.
 * */
public class Cloud implements Sprite {
    private static final int NUMBER_OF_LINES = 10;
    private static final int LENGTH_OF_LINES = 200;
    private static final int GAP_BETWEEN_LINES = 10;
    private static final int GAP_BETWEEN_CLOUDS = 20;
    private static final int RADIUS = 30;
    private Point center;
    /** create cloud object.
     * the center point of the cloud will be the given center point.
     * @param center the center point of the cloud.
     * */
    public Cloud(Point center) {
        this.center = center;
    }
    /**
     * draw the cloud on gaven draw surface.
     * @param d the gaven draw surface.
     * */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the clouds
        d.setColor(Color.WHITE.darker());
        d.fillCircle((int) this.center.getX() +  GAP_BETWEEN_CLOUDS,
                (int) this.center.getY() -  GAP_BETWEEN_CLOUDS, RADIUS);
        d.setColor(Color.GRAY);
        d.fillCircle((int) this.center.getX() + 2 * GAP_BETWEEN_CLOUDS,
                (int) this.center.getY(), RADIUS);
        d.fillCircle((int) this.center.getX() + 2 * GAP_BETWEEN_CLOUDS + GAP_BETWEEN_CLOUDS,
                (int) this.center.getY() + GAP_BETWEEN_CLOUDS, RADIUS / 2);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), RADIUS);
        d.fillCircle((int) this.center.getX() -  GAP_BETWEEN_CLOUDS,
                (int) this.center.getY() + GAP_BETWEEN_CLOUDS, RADIUS / 2);
        Point leftPointOfLine = new Point(this.center.getX() - GAP_BETWEEN_CLOUDS, this.center.getY());
        Point endLeftPointOfLine = new Point(leftPointOfLine.getX() - 4 * GAP_BETWEEN_LINES,
                this.center.getY() + LENGTH_OF_LINES);
        //create the rain lines
        for (int i = 0; i < NUMBER_OF_LINES; i++) {
            d.drawLine((int) leftPointOfLine.getX(), (int) leftPointOfLine.getY(),
                    (int) endLeftPointOfLine.getX(), (int) endLeftPointOfLine.getY());
            leftPointOfLine = new Point(leftPointOfLine.getX() + GAP_BETWEEN_LINES, leftPointOfLine.getY());
            endLeftPointOfLine = new Point(endLeftPointOfLine.getX() + GAP_BETWEEN_LINES, endLeftPointOfLine.getY());
        }
    }
    /** doesn't used.
     * */
    @Override
    public void timePassed() {
    }
}
