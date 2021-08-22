//ID 318525185.
package geometry;
/**
 * This class represent a velocity of a ball in the space.
 */
public class Velocity {
    private static final int CYCLE = 360;
    private static final int DEFAULT_VALUE = 0;
    private double dx;
    private double dy;
    /**
     * The method is a constructor , the method accept 2 double and create a velocity (the change in y,x axes).
     *
     * @param dx represent the change in x axe .
     * @param dy represent the change in y axe .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * The method take a point with position (x,y) and return a new point ,with position (x+dx, y+dy).
     * The method make the change in y,x axes accordingly to the velocity.
     * @param p represent the point in y,x axes that the method change .
     * @return  new point ,with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }
    /**
     * The method accept angle and speed and create a velocity appear accordingly the angle and speed.
     * @param angle represent the angle relatively y axe (angle 0 it is up) .
     * @param speed represent the speed of the ball .
     * @return  velocity accordingly to the data.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //create new velocity
        Velocity v = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
        angle = angle % CYCLE;
        calculateAngleQuadrant(angle, speed, v);
        return v;
    }
    /**
     * The method return the change in x axe.
     *
     *@return the dx of velocity.
     */
    public double getDx() {
        return this.dx; }
    /**
     * The method return the change in y axe.
     *
     *@return the dy of velocity.
     */
    public double getDy() {
        return this.dy; }
    /**.
     * The method sets the dx in velocity
     * @param x is the parameter to set in dx
     */
    public void setDx(double x) {
       this.dx = x; }
    /**.
     * The method sets the dy in velocity
     * @param y is the parameter to set in dy
     */
    public void setDy(double y) {
        this.dy = y;
    }
    /**.
     * The method return the speed of the ball
     * @return the speed of the ball.
     */
    public double getSpeed() {
        Point start = new Point(0, 0);
        Point end = new Point(this.dx, this.dy); //the position after one move
        return start.distance(end);
    }
    /**
     * The method accept angle , speed and velocity , and update the velocity accordingly to the data.
     * @param angle is the angle that the method  need to calculate the velocity accordingly.
     * @param speed the speed of the ball that the method update.
     * @param velocity the velocity that the method update.
     */
    private static void calculateAngleQuadrant(double angle, double speed, Velocity velocity) {
        //calculate the change on x,y axes
            double tempX = Math.sin(Math.PI * 2 * (angle / 360));
            double tempY = Math.cos(Math.PI * 2 * (angle / 360));
            velocity.setDx(speed * tempX);
            velocity.setDy(-speed * tempY);
    }
}