//ID 318525185
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
/** this class represent sun, and can draw the sun ob gave surface.
 * */
public class Sun implements Sprite {
    private static final int CASING = 20;
    private static final int NUMBER_OF_SUNBEAM = 160;
    private static final int DISTANCE_BETWEEN_SUNBEAM = 5;
    private int radius;
    private Color color;
    private Point center;
    private Point mostLeftSunbeamPoint;
    /** create a sun accordingly to the gaven center point, and radius.
     * @param radius the radius of the sun.
     * @param center the center point of the sun.
     * @param mostLeftSunbeamPoint the most left sunbeam point of the sun.
     * */
    public Sun(int radius, Point center, Point mostLeftSunbeamPoint) {
        this.radius = radius;
        this.center = center;
        this.color = Color.YELLOW;
        this.mostLeftSunbeamPoint = mostLeftSunbeamPoint;
    }
    /** draw the sun on gaven draw surface.
     * @param d gaven draw surface.
     * */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the sunbeam
        d.setColor(Color.YELLOW);
        for (int i = 0; i < NUMBER_OF_SUNBEAM; i++) {
            double mostLeftSunbeamPointX = this.mostLeftSunbeamPoint.getX();
            double mostLeftSunbeamPointY = this.mostLeftSunbeamPoint.getY();
            d.drawLine((int) this.center.getX(), (int) this.center.getY(),
                    (int) mostLeftSunbeamPointX + i * DISTANCE_BETWEEN_SUNBEAM, (int) mostLeftSunbeamPointY);
        }
        d.setColor(this.color);
        d.fillCircle((int) center.getX(), (int) center.getY(), radius + CASING);
        //draw the internal sun
        d.setColor(Color.ORANGE);
        d.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }
    /** doesn't do anything.
     * */
    @Override
    public void timePassed() {
    }
}
