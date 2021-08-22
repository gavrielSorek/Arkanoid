//ID 318525185
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
/** this class represent a target and can draw the target to gaven surface.
 * */
public class Target implements Sprite {
    private static final int DISTANCE_FROM_CENTER = 40;
    private int radius;
    private Color color;
    private Point center;
    /** create a target object with the gaven color, and gaven radius, center point.
     * @param radius the radius of the target.
     * @param color the color of the target.
     * @param center the center point of the target.
     *
     * */
    public Target(int radius, Color color, Point center) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }
    /** draw the target to gaven surface.
     * @param d the gaven surface.
     * */
    public void drawOn(DrawSurface d) {
        int distanceBetweenCircles = this.radius / 4;
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), (int) (this.radius - distanceBetweenCircles));
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                (int) this.radius - 2 * distanceBetweenCircles);
        d.drawLine((int) center.getX() + DISTANCE_FROM_CENTER, (int) center.getY(),
                (int) center.getX() + DISTANCE_FROM_CENTER + this.radius, (int) center.getY());
        d.drawLine((int) center.getX() - DISTANCE_FROM_CENTER, (int) center.getY(),
                (int) center.getX() - DISTANCE_FROM_CENTER - this.radius, (int) center.getY());
        d.drawLine((int) center.getX(), (int) center.getY() + DISTANCE_FROM_CENTER,
                (int) center.getX(), (int) center.getY() + radius + DISTANCE_FROM_CENTER);
        d.drawLine((int) center.getX(), (int) center.getY() - DISTANCE_FROM_CENTER,
                (int) center.getX(), (int) center.getY() - radius - DISTANCE_FROM_CENTER);

    }
    /** doesn't used.
     * */
    @Override
    public void timePassed() {
    }
}
