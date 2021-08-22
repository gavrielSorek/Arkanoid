//ID 318525185.
package geometry;
import biuoop.DrawSurface;
import gamefeatures.CollisionInfo;
import gamefeatures.GameLevel;
import gamefeatures.GameEnvironment;
import java.awt.Color;
import java.util.Random;
/**
 * This class represent a ball and can draw ball.
 */
public class Ball implements Sprite {
    private static final double CLOSE_RANGE = 0; //small range that count ass impact
    private static final double DISTANCE_FROM_COLLISION_POINT = 0.1;
    private int size;
    private Point point;
    private java.awt.Color color;
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;

    /**
     * The method is a constructor , the method accept point and radius and color.
     * The method create a ball.
     *
     * @param center is the center point of the ball .
     * @param r      is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.size = r;
        this.point = center;
        this.color = color;
    }

    /**
     * The method is a constructor , the method accept x ,y and radius and color.
     * The method create a ball.
     *
     * @param x     is the location on x line .
     * @param y     is the location on x line .
     * @param r     is the radius of the ball.
     * @param color is the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
    }

    /**
     * The method return the location on x line of this ball.
     *
     * @return the location on x line.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * The method return the location on y line of this ball.
     *
     * @return the location on y line.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * The method return the radius of this ball.
     *
     * @return the the radius of this ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * The method return the color of this ball.
     *
     * @return the the color of this ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The method draw this ball on given DrawSurface.
     *
     * @param surface is the color of the ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), this.size);
        surface.setColor(Color.BLACK); //draw the frame of the ball
        surface.drawCircle((int) this.point.getX(), (int) this.point.getY(), this.size);
        surface.setColor(Color.RED);
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), 2); //red point on the ball
    }

    /**
     * the method accept 2 numbers that represent the velocity.
     * the method sets the velocity.
     *
     * @param dx represent the change in x axe.
     * @param dy represent the change in y axe.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * the method accept velocity and sets the velocity of the ball accordingly.
     *
     * @param velocity is the velocity that the method sets in the ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * the method return the velocity of the ball.
     *
     * @return the velocity of this ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * the method return the center point of the ball.
     *
     * @return center point of the ball.
     */
    public Point getCenterPoint() {
        return this.point;
    }

    /**
     * The method move the ball accordingly the velocity.
     * the method check if the ball collision and change the velocity accordingly
     */
    public void moveOneStep() {
        Velocity oldVelocity = new Velocity(this.v.getDx(), this.v.getDy()); //copy of current velocity
        boolean occurImpactWithSides = false;
        boolean occurImpactWithUpDownBorder = false;
        Point prvPoint = new Point(this.point.getX(), this.point.getY()); //previous ball location
        Line directionLine = directionLine();
        //update the new point accordingly to the velocity.
        this.point = this.getVelocity().applyToPoint(this.point);
        Point currentPoint = new Point(this.point.getX(), this.point.getY()); //it's a copy of the original point
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(directionLine);
        if (this.gameEnvironment.getClosestCollision(directionLine) == null) { //there isn't collision point
            return;
        }
        //update velocity
        this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
        if (oldVelocity.getDy() != this.v.getDy()) { //if there was a change in dy velocity collision with upper/lower
            occurImpactWithUpDownBorder = true;
        }
        if (oldVelocity.getDx() != this.v.getDx()) { //if there was a change in dy velocity collision with left/right
            occurImpactWithSides = true;
        }
        changeBallPositionAccordinglyToCollisions(occurImpactWithSides, occurImpactWithUpDownBorder, directionLine,
                collisionInfo.collisionPoint());
    }
    /**
     * .
     * The method change the ball position when the ball collide.
     * the method change the ball position to point near the collision point.
     *
     * @param occurImpactWithSides        is boolean variable that indicate if the ball collide in vertical line.
     * @param occurImpactWithUpDownBorder is boolean variable that indicate if the ball collide in horizontal line.
     * @param directionLine               is a line that indicate on the direction of the ball.
     * @param collisionPoint              is the point where a collision happened.
     */
    private void changeBallPositionAccordinglyToCollisions(boolean occurImpactWithSides,
                                                           boolean occurImpactWithUpDownBorder, Line directionLine,
                                                           Point collisionPoint) {
        //if vertical line
        if (directionLine.start().getX() - directionLine.end().getX() == Line.INCLINE_OF_VERTICAL_LINE) {
            nearCollisionCalculateForVerticalCollision(collisionPoint, directionLine);
            return;
        }
        //if collision occur with the sides border
        if (occurImpactWithSides && !occurImpactWithUpDownBorder) {
            nearCollisionCalculateSidesCollision(collisionPoint, directionLine);
        } else if (!occurImpactWithSides && occurImpactWithUpDownBorder) {
            nearCollisionCalculateUpDownCollision(collisionPoint, directionLine);
        } else { //occurImpactWithSides and occurImpactWithUpDownBorder
            Ball ballFromXCollision = this.copyThisBall();
            Ball ballFromYCollision = this.copyThisBall();
            ballFromXCollision.nearCollisionCalculateSidesCollision(collisionPoint, directionLine);
            ballFromYCollision.nearCollisionCalculateUpDownCollision(collisionPoint, directionLine);
            //if ballFromXCollision more far away from the collision point then ballFromYCollision
            if (ballFromXCollision.getCenterPoint().distance(collisionPoint)
                    > ballFromYCollision.getCenterPoint().distance(collisionPoint)) {
                this.point = ballFromXCollision.getCenterPoint();
            } else {
                this.point = ballFromYCollision.getCenterPoint();
            }
        }
    }

    /**
     * .
     * The method change the ball position when the ball collide with vertical line.
     * the method change the ball position to point near the collision point.
     * this method doesn't support vertical line direction
     *
     * @param collisionPoint is the point where a collision happened.
     * @param directionLine  is a line that indicate on the direction of the ball.
     */
    private void nearCollisionCalculateSidesCollision(Point collisionPoint, Line directionLine) {
        //if the direction line is vertical
        if (directionLine.start().getX() - directionLine.end().getX() == Line.INCLINE_OF_VERTICAL_LINE) {
            throw new RuntimeException("nearCollisionCalculateSidesCollision doesn't support vertical direction line");
        }
        if (directionLine.start().getX() < collisionPoint.getX()) { // the ball came from left to collision point
            // y on ball direction near collision point
            double y = directionLine.getYFromLineByX(collisionPoint.getX() - DISTANCE_FROM_COLLISION_POINT);
            this.point = new Point(collisionPoint.getX() - DISTANCE_FROM_COLLISION_POINT, y);
        } else { //if prvPoint.getX() > collisionPoint.getX()
            double y = directionLine.getYFromLineByX(collisionPoint.getX() + DISTANCE_FROM_COLLISION_POINT);
            this.point = new Point(collisionPoint.getX() + DISTANCE_FROM_COLLISION_POINT, y);
        }
    }

    /**
     * .
     * The method change the ball position when the ball collide with horizontal line.
     * the method change the ball position to point near the collision point.
     * this method doesn't support vertical line direction
     *
     * @param collisionPoint is the point where a collision happened.
     * @param directionLine  is a line that indicate on the direction of the ball.
     */
    private void nearCollisionCalculateUpDownCollision(Point collisionPoint, Line directionLine) {
        //if the direction line is vertical
        if (directionLine.start().getX() - directionLine.end().getX() == Line.INCLINE_OF_VERTICAL_LINE) {
            throw new RuntimeException("nearCollisionCalculateSidesCollision doesn't support vertical direction line");
        }
        if (directionLine.start().getY() < collisionPoint.getY()) { // the ball came from UP to collision point
            // x on ball direction near collision point
            double x = directionLine.getXFromLineByY(collisionPoint.getY() - DISTANCE_FROM_COLLISION_POINT);
            this.point = new Point(x, collisionPoint.getY() - DISTANCE_FROM_COLLISION_POINT);
        } else { //if prvPoint.getY() > collisionPoint.getY() - the ball found lower then the collision point
            double x = directionLine.getXFromLineByY(collisionPoint.getY() + DISTANCE_FROM_COLLISION_POINT);
            this.point = new Point(x, collisionPoint.getY() + DISTANCE_FROM_COLLISION_POINT);
        }
    }

    /**
     * .
     * The method change the ball position when the ball collide with horizontal line.
     * the method change the ball position to point near the collision point.
     * this method support only vertical line direction
     *
     * @param collisionPoint is the point where a collision happened.
     * @param directionLine  is a line that indicate on the direction of the ball.
     */
    private void nearCollisionCalculateForVerticalCollision(Point collisionPoint, Line directionLine) {
        //if the direction line is not vertical
        if (directionLine.start().getX() - directionLine.end().getX() != Line.INCLINE_OF_VERTICAL_LINE) {
            throw new RuntimeException("nearCollisionCalculateForVerticalCollision"
                    + " support only in vertical direction lines");
        }
        if (collisionPoint.getY() < directionLine.start().getY()) { //if collision point is upper
            this.point = new Point(collisionPoint.getX(), collisionPoint.getY() + DISTANCE_FROM_COLLISION_POINT);
        } else { //if collision point is lower than the ball
            this.point = new Point(collisionPoint.getX(), collisionPoint.getY() - DISTANCE_FROM_COLLISION_POINT);
        }
    }

    /**
     * .
     * The method calculate the direction of "this" ball and enter it to line.
     * the start point of the line is the current location of the ball.
     * the end point of the line is the location of the ball after one move.
     *
     * @return line that represent the direction of the ball .
     */
    private Line directionLine() {
        Line directionL = new Line(this.point, new Point(point.getX() + v.getDx(),
                point.getY() + v.getDy()));
        return directionL;
    }

    /**
     * the method accept a array with sizes.
     * this method create a array with balls (the balls sizes takes from the array).
     * the balls have a random speed and random starting point.
     *
     * @param sizesArray is array with sizes of balls.
     * @param boardHigh  is the height of the board that the balls need to fit.
     * @param boardWidth is the width of the board that the balls need to fit.
     * @return array with the random balls.
     */
    public static Ball[] createRandomBallsBySizes(int[] sizesArray, int boardWidth, int boardHigh) {
        Ball[] ballsArray = new Ball[sizesArray.length];
        //crate array of colors
        java.awt.Color[] colors = new java.awt.Color[5];
        colors[0] = Color.black;
        colors[1] = Color.RED;
        colors[2] = Color.GREEN;
        colors[3] = Color.blue;
        colors[4] = Color.CYAN;
        Random rand = new Random();
        //generate all the balls
        for (int i = 0; i < sizesArray.length; i++) {
            java.awt.Color color = colors[rand.nextInt(5)]; //random color from the array
            int x = rand.nextInt(boardWidth - 2 * sizesArray[i]) + (sizesArray[i] + 1);
            int y = rand.nextInt(boardHigh - 2 * sizesArray[i]) + (sizesArray[i] + 1);
            ballsArray[i] = new Ball((double) x, (double) y, sizesArray[i], color);
        }
        return ballsArray;
    }

    /**
     * .
     * the method generate a random starting point of ball accordingly the borders.
     *
     * @param upLeftCornerX is the X coordinate of the upper-left corner of the boarder.
     * @param upLeftCornerY is the Y coordinate of the upper-left corner of the boarder.
     * @param boardWidth    is the width of the board that the balls need to fit.
     * @param boardHeight   is the height of the board that the balls need to fit.
     */
    public void randomStartingPointByBorders(int upLeftCornerX, int upLeftCornerY,
                                             int boardWidth, int boardHeight) {

        Random rand = new Random();
        int x = rand.nextInt(boardWidth - 2 * this.size) + upLeftCornerX + (this.size + 1);
        int y = rand.nextInt(boardHeight - 2 * this.size) + upLeftCornerY + (this.size + 1);
        this.point = new Point(x, y); //create the new point
    }

    /**
     * .
     * the method sets the game environment of this ball.
     *
     * @param gameEnv is the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnvironment = gameEnv;
    }

    /**
     * .
     * the method return the game environment.
     *
     * @return the game environment of this ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * the method copy "this" ball.
     *
     * @return replication of "this" ball.
     */
    public Ball copyThisBall() {
        Ball newBall = new Ball(this.point.getX(), this.point.getY(), this.size, this.color);
        newBall.setVelocity(this.getVelocity());
        if (this.gameEnvironment != null) {
            newBall.setGameEnvironment(this.gameEnvironment);
        }
        return newBall;
    }
    /**
     * the method move the ball by using moveOneStepFunction.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * this method add the ball to game .
     * the method add the ball to list that represent drawnable objects
     * in order to draw the ball while the game is playing.
     * @param gameLevel the game that the method add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /** remove the ball from the gaven game.
     * @param g the gaven game.
     * */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}




