//ID 318525185.
package geometry;
/**
 * This class represent a line and can compare lines.
 */
public class Line {
    public static final int INCLINE_OF_VERTICAL_LINE = 0;
    public static final double EPSILON = 0.000000001;
    private Point start;
    private Point end;

    /**
     * The method is a constructor , the method accept 2 points and create a line.
     *
     * @param start the start of the line.
     * @param end   the end of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The method is a constructors, the method accept 2 x and 2 y and create a line .
     *
     * @param x1 the x of the point that represent the start of the line.
     * @param y1 the y of the point that represent the start of the line.
     * @param x2 the x of the point that represent the end of the line.
     * @param y2 the y of the point that represent the end of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The method is return the length of the line.
     *
     * @return return the length of the line.
     */
    public double length() {
        return (this.start.distance(end));
    }

    /**
     * The method is check the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        Point newPoint = new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
        return (newPoint);
    }

    /**
     * The method is check the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The method is check the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }
    // Returns true if the lines intersect, false otherwise

    /**
     * The method accept line and check if this line and the other line are Intersecting .
     *
     * @param other the other line , we need to check if our lines (this and other) Intersecting .
     * @return true if the lines are intersecting and false if they dont.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * The method accept line and check what is the intersection point if the lines intersect .
     *
     * @param other the other line , we need to check what the  intersection point (of this and other)  .
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //if one or more of the lines is point
        if (other.start().equals(other.end()) || this.start().equals(this.end())) {
            return intersectionWithPoint(other);
        }
        //if it is vertical line
        if ((this.start.getX() - this.end.getX() == 0) && (other.start.getX() - other.end.getX() == 0)) {
            //if the lines are touching
            return this.isTouching(other);
            //if this line is vertical , and the other line isn't
        } else if ((this.start.getX() - this.end.getX() == 0) && !(other.start.getX() - other.end.getX() == 0)) {
            //Incline of other = m2
            double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            double x = this.start.getX();
            double y = m2 * (x - other.start.getX()) + other.start.getY();
            Point intersectionPoint = new Point(x, y);
            //if there is intersection point
            if (isIntersectionPointInRange(intersectionPoint, other, this)) {
                return intersectionPoint;
            }
            return null;
            //if the other line is vertical
        } else if (!(this.start.getX() - this.end.getX() == 0) && (other.start.getX() - other.end.getX() == 0)) {
            //Incline of other = m2
            double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            double x = other.start.getX();
            double y = m1 * (x - this.start.getX()) + this.start.getY();
            Point intersectionPoint = new Point(x, y);
            //if there is intersection point
            if (isIntersectionPointInRange(intersectionPoint, other, this)) {
                return intersectionPoint;
            }
            return null;
            //if the lines are not vertical
        } else {
            double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            //if the inclines are equal
            if (m1 == m2) {
                //if it is the same line equation
                if (m1 * (-this.start.getX()) + this.start.getY() == m2 * (-other.start.getX()) + other.start.getY()) {
                    return isTouching(other);
                }
                //if the lines parallel
                return null;
                //if the inclines are not equal
            } else {
                double x = ((m1 * (-this.start.getX()) + this.start.getY())
                        - (m2 * (-other.start.getX()) + other.start.getY())) / (m2 - m1);
                double y = m1 * (x - this.start.getX()) + this.start.getY();
                Point intersectionPoint = new Point(x, y);
                //if there is intersection point
                if (isIntersectionPointInRange(intersectionPoint, other, this)) {
                    return intersectionPoint;
                }
                return null;
            }
        }
    }

    /**
     * The method accept line and check if the line equal to this object line .
     *
     * @param other the other line , we need to check if other line equal to this object  .
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * The method accept line and check if the line "touch" in only one point the object line .
     * this method helps isTouching method usually for vertical/merge lines.
     *
     * @param other the other line , we need to check if touch this object line  .
     * @return true is the lines are touching, false otherwise.
     */
    private boolean isTouchingHelper(Line other) {
        //if the line meeting in only one point
        if ((checkIfUpperOnYLine(this, other) || checkIfUpperOnYLine(other, this))
                && (checkIfRightOnXLine(this, other) || checkIfRightOnXLine(other, this))) {
            return true;
        }
        return false;
    }

    /**
     * The method accept line and check if the line "touch" in only one point the object line .
     * using for for vertical/merge lines
     *
     * @param other the other line , we need to check if touch this object line  .
     * @return the meeting point of the lines, or null if the lines dont meeting.
     */
    private Point isTouching(Line other) {
        //if our object starting point equal to start/end point of other line
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            if (this.isTouchingHelper(other)) {
                return this.start;
            }
            return null;
        }
        //if our object end point equal to start/end point of other line
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            if (this.isTouchingHelper(other)) {
                return this.end;
            }
            return null;
        }
        return null;
    }

    /**
     * The method accept 2 lines ant point ant check if this point in range of the 2 lines .
     *
     * @param point the point that we need to check if it in the range the of 2 lines .
     * @param line1 the line that we need to check if in range of the point.
     * @param line2 the other line , we need to check if in range of the point.
     * @return true if the point in range of the 2 lines.
     */
    private static boolean isIntersectionPointInRange(Point point, Line line1, Line line2) {
        return (checkIfPointInLineRange(point, line1) && checkIfPointInLineRange(point, line2));
    }

    /**
     * The method accept point and line.
     * the method check if the point in range of the line
     *
     * @param point the point that we need to check if it in the range the of the lines .
     * @param line  the line that we need to check if in range of the point.
     * @return true if the point in range of the line.
     */
    public static boolean checkIfPointInLineRange(Point point, Line line) {
        return (checkIfXCoordinateIsInRange(point, line) && checkIfYCoordinateIsInRange(point, line));
    }

    /**
     * The method accept point and line.
     * the method check if the Y coordinate of the point in range of the line
     *
     * @param point the point that we need to check if it in the range the of the lines .
     * @param line  the line that we need to check if in range of the point.
     * @return true if the y coordinate of the point in range of the line , false otherwise.
     */
    public static boolean checkIfYCoordinateIsInRange(Point point, Line line) {
        if ((point.getY() >= line.start.getY() && point.getY() <= line.end.getY())
                || (point.getY() <= line.start.getY() && point.getY() >= line.end.getY())) {
            return true;
        } else if (checkEqualByEpsilon(point.getY(), line.start.getY())
                || checkEqualByEpsilon(point.getY(), line.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * The method accept point and line.
     * the method check if the X coordinate of the point in range of the line
     *
     * @param point the point that we need to check if it in the range the of the lines .
     * @param line  the line that we need to check if in range of the point.
     * @return true if the x coordinate of the point in range of the line , false otherwise.
     */
    public static boolean checkIfXCoordinateIsInRange(Point point, Line line) {
        if ((point.getX() >= line.start.getX() && point.getX() <= line.end.getX())
                || (point.getX() <= line.start.getX() && point.getX() >= line.end.getX())) {
            return true;
        } else if (checkEqualByEpsilon(point.getX(), line.start.getX())
                || checkEqualByEpsilon(point.getX(), line.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * The method accept check if 2 values are equal.
     * the method use epsilon.
     *
     * @param num1 the number to check if equal to num2
     * @param num2 the number to check if equal to num1.
     * @return true if the numbers are equal ,false otherwise.
     */
    public static boolean checkEqualByEpsilon(double num1, double num2) {
        return (Math.abs(num1 - num2) < EPSILON);
    }

    /**
     * The method accept 2 lines and check if line1 located higher then line 2 on y line .
     *
     * @param line1 the line that we need to compare to line 2.
     * @param line2 the line that need to be located lower on y line.
     * @return true if line1 located higher then line 2 on y line , else return false.
     */
    private static boolean checkIfUpperOnYLine(Line line1, Line line2) {
        //if line 1 is Located higher then line 2
        if ((line1.start.getY() >= line2.start.getY()) && (line1.end.getY() >= line2.start.getY())
                && (line1.start.getY() >= line2.end.getY()) && (line1.end.getY() >= line2.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * The method accept 2 lines and check if line1 locate more right then line 2 on x line .
     *
     * @param line1 the line that we need to compare to line 2.
     * @param line2 the line that need to be located more left on x line.
     * @return true if line1 located more right then line 2 on y line , else return false.
     */
    private static boolean checkIfRightOnXLine(Line line1, Line line2) {
        //if the line 1 is Located to the right of the other line
        if ((line1.start.getX() >= line2.start.getX()) && (line1.end.getX() >= line2.start.getX())
                && (line1.start.getX() >= line2.end.getX()) && (line1.end.getX() >= line2.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * The method accept line other (one/both of the lines "other or this" is a point )
     * the method checks if the lines have intersection point while one or 2 of the lines are points.
     *
     * @param other the line that the method check if it has intersection point with "this" line.
     * @return true if the lines have intersection point , else return false.
     */
    private Point intersectionWithPoint(Line other) {
        //if the 2 lines are point
        if (other.start().equals(other.end()) && this.start().equals(this.end())) {
            if (this.start().equals(other.start())) {
                return this.start();
            }
            return null; //if it's not the same point
            //if this line is point and the other line isn't point
        } else if (!other.start().equals(other.end()) && this.start().equals(this.end())) {
            if (isPointOnLine(this.start(), other)) {
                return this.start(); //return the intersection point
            }
            return null; //if the point of this isn't on the line other
        } else { //if the other is point , and "this" is a line
            if (isPointOnLine(other.start(), this)) {
                return other.start(); //return the intersection point
            }
            return null; //if the point of this isn't on the line other
        }
    }

    /**
     * The method accept line and point
     * the method checks if the point is on the line.
     *
     * @param point the point that the method check if it's on the line.
     * @param line  the line that the method check if the point is on this line.
     * @return true if the point is on the line , else return false.
     */
    public static boolean isPointOnLine(Point point, Line line) {
        //if the line is vertical
        if (Line.checkEqualByEpsilon(line.start.getX(), line.end.getX())) {
            //if the point found in the same X coordinate
            if (Line.checkEqualByEpsilon(line.start.getX(), point.getX())) {
                return checkIfPointInLineRange(point, line);
            }
            return false; //if the point dos'nt have the same x coordinate as the line
        }
        //if the line is not vertical
        //m1 is the incline of line
        double m1 = (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
        double x = point.getX();
        double y = m1 * (x - line.start.getX()) + line.start.getY();
        if (point.getY() == y) { //if the point is on the line check if point is in range
            return checkIfPointInLineRange(point, line);
        }
        return false; //if the point doesnt found on the line
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * The method accept rectangle
     * the method checks what the closest intersection point with the rectangle to the start of the line.
     * @param rect the rectangle that the method check intersection with "this" line.
     * @return the closest intersection point to the start of the line , if there isn't intersection point return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //array that contain all the intersection point with "this" line and the rectangle edges
        Point[] intersectionPointArray = new Point[Rectangle.NUM_OF_EDGES];
        intersectionPointArray[0] = rect.getUpperLine().intersectionWith(this); //up Line Intersection
        intersectionPointArray[1] = rect.getDownLine().intersectionWith(this); //down Line Intersection
        intersectionPointArray[2] = rect.getRightLine().intersectionWith(this); //right Line Intersection
        intersectionPointArray[3] = rect.getLeftLine().intersectionWith(this); //leftLineIntersection
        return closestIntersectionPointOfRec(intersectionPointArray);
    }
    /**
     * The method accept array with lines of rectangle
     * the method checks what the closest intersection point with the lines (the lines from lines array).
     * @param intersectionPointArray the array that contain the lines of rectangle.
     * @return the closest intersection point with the lines to "this" line
     *  if there isn't intersection point return null.
     */
    private Point closestIntersectionPointOfRec(Point[] intersectionPointArray) {
        Point closestIntersectionPoint = null; //initialize the closest intersection point to null
        for (int i = 0; i < intersectionPointArray.length; i++) {
            if (intersectionPointArray[i] != null) {
                if (closestIntersectionPoint == null) { //if there isn't intersection point yet
                    closestIntersectionPoint = intersectionPointArray[i];
                } else { //if there is intersection point
                    if (intersectionPointArray[i].distance(this.start())
                            < closestIntersectionPoint.distance(this.start())) {
                        closestIntersectionPoint = intersectionPointArray[i];
                    }
                }
            }
        }
        return closestIntersectionPoint;
    }
    /**.
     * The method accept x coordinate and check what is the value of y coordinate of this line in location x
     * @param x the coordinate x.
     * @return y coordinate accordingly the x coordinate
     */
    public  double getYFromLineByX(double x) {
        if (this.end().getX() - this.start().getX() == INCLINE_OF_VERTICAL_LINE) {
            throw new RuntimeException("getYFromLineByX method does'nt support vertical line");
        }
        double m = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX()); //the incline
        double y = m * (x - this.start().getX()) + this.start().getY();
        return y;
    }
    /**.
     * The method accept y coordinate and check what is the value of x coordinate of this line in location y.
     * @param y the coordinate y.
     * @return x coordinate accordingly the y coordinate input.
     */
    public  double getXFromLineByY(double y) {
        if (this.end().getX() - this.start().getX() == INCLINE_OF_VERTICAL_LINE) {
            throw new RuntimeException("getXFromLineByY method does'nt support vertical line");
        }
        double m = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX()); //the incline
        if (m == 0) {
            throw new  RuntimeException("getXFromLineByY m = 0 problem");
        }
        double x = (y - this.start().getY() + m * this.start().getX()) / m;
        return x;
    }
}
