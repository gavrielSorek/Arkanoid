//ID 318525185.
/**
 * This class represent rectangle and can draw rectangle.
 */
package geometry;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
/**
 * this class represent rectangle , and can do operation on rectangle.
 */
public class Rectangle  {
public static final int NUM_OF_EDGES = 4;
private Point upperLeft;
private double width;
private double height;
private java.awt.Color color = Color.BLACK; //default color
    /**
     * The method is a constructor , the method accept upper left point, width and height.
     * the method create rectangle.
     * @param upperLeft is the upper left point of the rectangle .
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * The method is a constructor , the method accept upper left point, width and height and color.
     * the method create rectangle with the given color.
     * @param upperLeft is the upper left point of the rectangle .
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     * @param color is the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * The method accept line and check what is the intersection point with this rectangle .
     * @param line is the line that the method check the intersections point with the rectangle .
     * @return  a (possibly empty) List of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointList = new ArrayList<Point>();
        //enter all the edges lines to an array
        Line[] edgesArray = new Line[NUM_OF_EDGES];
        edgesArray[0] = this.getUpperLine();
        edgesArray[1] = this.getDownLine();
        edgesArray[2] = this.getRightLine();
        edgesArray[3] = this.getLeftLine();
        for (Line l:edgesArray) {
            if (l.isIntersecting(line)) { //if there is intersection point (l and line).
                intersectionPointList.add(l.intersectionWith(line));
            }
        }
        return intersectionPointList;
    }
    /**
     * The method return the width of the rectangle.
     * @return  the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * The method return the Height of the rectangle.
     * @return  the Height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * The method sets the upper left point of the rectangle.
     * @param newStartingPoint is the new upper left point of the rectangle
     */
    public void setStartingPoint(Point newStartingPoint) {
        this.upperLeft = newStartingPoint;
    }
    /**
     * The method return the upper left point of the rectangle.
     * @return  the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }
    /**
     * The method return the upper right point of the rectangle.
     * @return  the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }
    /**
     * The method return the down left point of the rectangle.
     * @return  the down left point of the rectangle.
     */
    public Point getDownLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }
    /**
     * The method return the down right point of the rectangle.
     * @return  the down right point of the rectangle.
     */
    public Point getDownRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }
    /**
     * The method return the upper line of the rectangle.
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return new Line(getUpperLeft(), getUpperRight());
    }
    /**
     * The method return the lower base of the rectangle.
     * @return the lower base of the rectangle.
     */
    public Line getDownLine() {
        return new Line(getDownLeft(), getDownRight());
    }
    /**
     * The method return the right edge of the rectangle.
     * @return the right edge of the rectangle.
     */
    public Line getRightLine() {
        return new Line(getUpperRight(), getDownRight());
    }
    /**
     * The method return the left edge of the rectangle.
     * @return the left edge of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(getUpperLeft(), getDownLeft());
    }
    /**
     * The method return the velocity of object that collide on rectangular shape.
     * @param collisionPoint is the collision point of the object in the rectangle.
     * @param currentVelocity is the velocity of the object that collide
     * in the rectangle before change.
     * @return the new velocity of the object that collide the rectangle.
     */
    public Velocity hitOnRectangularShape(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (isCollisionWithSides(collisionPoint, currentVelocity)) {
            newVelocity.setDx(currentVelocity.getDx() * (-1)); //change the x axe velocity
        }
        if (isCollisionWithUpperLowerBases(collisionPoint, currentVelocity)) {
            newVelocity.setDy(currentVelocity.getDy() * (-1)); //change the y axe velocity
        }
        return newVelocity;
    }
    /**
     * The method check if the collision point on the rectangle is on left/right edge.
     * @param collisionPoint is the collision point of the object in the rectangle.
     * @param v is the velocity of the object that collide
     * with the rectangle before change.
     * @return true if the collision point on the rectangle is on left/right edge, false otherwise.
     */
    public boolean isCollisionWithSides(Point collisionPoint, Velocity v) {
        if (Line.checkEqualByEpsilon(v.getDx(), 0)) { //if dx = 0 cant collide with right / left edges.
            return false;
        }
        Line rightSide = this.getRightLine();
        Line leftSide = this.getLeftLine();
        //if the collision point on one of the side edges
        if (Line.isPointOnLine(collisionPoint, rightSide)) {
            if (v.getDx() < 0) { //if it is negative dx velocity (the only way to collide with right left edge)
                return true;
            }
        }
        if (Line.isPointOnLine(collisionPoint, leftSide)) {
            if (v.getDx() > 0) { //if it is positive dx velocity (the only way to collide with the left edge)
                return true;
            }
        }
        return false;
    }
    /**
     * The method check if the collision point on the rectangle is on upper/lower base.
     * @param collisionPoint is the collision point of the object in the rectangle.
     * @param v is the velocity of the object that collide
     * with the rectangle before change.
     * @return true if the collision point on the rectangle is on upper/lower base, false otherwise.
     */
    public boolean isCollisionWithUpperLowerBases(Point collisionPoint, Velocity v) {
        if (Line.checkEqualByEpsilon(v.getDy(), 0)) { //if dy = 0 cant collide with upper / lower bases.
            return false;
        }
        Line upperLine = this.getUpperLine();
        Line lowerLine = this.getDownLine();
        //if the collision point on one of the side edges
        if (Line.isPointOnLine(collisionPoint, upperLine)) {
            if (v.getDy() > 0) { //if it is positive dy velocity (the only way to collide with the upper edge)
                return true;
            }
        }
        if (Line.isPointOnLine(collisionPoint, lowerLine)) {
            if (v.getDy() < 0) { //if it is positive dy velocity (the only way to collide with the lower edge)
                return true;
            }
        }
        return false;
    }
    /**
     * the method draw the rectangle on given surface.
     * @param surface is the surface to draw the rectangle on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
    /**
     * the method draw the rectangle on given surface, with given color.
     * @param surface is the surface to draw the rectangle on.
     * @param colorOfRec is the color of the rectangle that the method  draw.
     */
    public void drawOn(DrawSurface surface, java.awt.Color colorOfRec) {
        surface.setColor(colorOfRec);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
    /** set the color of the rectangle.
     * @param colorOfRec the color to set.
     * */
    public void setColor(Color colorOfRec) {
        this.color = colorOfRec;
    }
}
