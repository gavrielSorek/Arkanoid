//ID 318525185.
package geometry;
/**
 * This class represent a point and can Perform mathematical operations on points.
 */
public class Point {
    public static final double EPSILON = 0.000000001;
    private double x;
    private double y;
    /**
     * The method is a constructor , the method accept 2 double and create a point.
     *
     * @param x is the x coordinate .
     * @param y is the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * The method check distance between 2 points.
     *
     * @param other is the other point.
     *@return the distance between this object and other point.         .
     */
    public double distance(Point other) {
        return (Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2)));
    }
    /**
     * The method check if the 2 points are equal.
     *
     * @param other is the other point that we need to compare.
     *@return true if the points are equal ,false otherwise.         .
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return  true;
        }
        return false;
    }
    /**
     * The method return the x value of this point.
     *
     *@return the x value of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * The method return the y value of this point.
     *
     *@return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
    /**.
     * The method set x
     *
     *@param  xPoint the x value to set at the point.
     */
    public void setX(double xPoint) {
        this.x = xPoint;
    }
    /**.
     * The method set x
     *
     *@param  yPoint the y value to set at the point.
     */
    public void setY(double yPoint) {
        this.y = yPoint;
    }
    /**.
     * The method accept board width and board height.
     * the method checks if "this" point on the board range.
     * @param boardHeight is the board height.
     * @param boardWidth is the board width.
     *@return true if the point on the board range, false otherwise.
     */
    public boolean isPointInBoardRange(int boardHeight, int boardWidth) {
        //if the point in range of the board (the board start in (0,0))
        if ((checkEqualByEpsilon(this.getX(), 0) || this.getX() >= 0)
                && (checkEqualByEpsilon(this.getX(), boardWidth) || this.getX() <= boardWidth)
                && (checkEqualByEpsilon(this.getY(), 0) || this.getY() >= 0)
                && (checkEqualByEpsilon(this.getY(), boardHeight) || this.getY() <= boardHeight)) {
            return true;
        }
        return false;
    }
    /**.
     * The method accept 2 numbers.
     * the method checks if 2 numbers equal or not.
     * the method using epsilon method to check if the numbers equal.
     * @param num1 is number to check if equal to num2.
     * @param num2 is number to check if equal to num1.
     *@return true if the point on the board range, false otherwise.
     */
    public static boolean checkEqualByEpsilon(double num1, double num2) {
        return (Math.abs(num1 - num2) < EPSILON);
    }

}