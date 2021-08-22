//ID 318525185
package geometry;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamefeatures.GameLevel;

import java.awt.Color;
/**.
 * the class Paddle represent a paddle, and operate puddle
 */
public class Paddle implements Sprite, Collidable {
    private static final int NUM_OF_PADDLE_AREAS = 5;
    private static final int DEFAULT_SPEED = 5;
    public static final int MOVE_SIZE = 7;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int speedOfPaddle;
    /**
     * The method is a constructor , the method accept rectangle ,color and keyboard.
     * the method create paddle that complex from rectangle and color and keyboard sensor.
     * @param rectangle is the shape and the dimensions of the paddle .
     * @param color is the color of the paddle.
     * @param keyboard is the keyboard sensor
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speedOfPaddle = DEFAULT_SPEED;
    }
    /**
     * The method is a constructor , the method accept rectangle ,color ,speed and keyboard.
     * the method create paddle that complex from rectangle and color and keyboard sensor.
     * @param rectangle is the shape and the dimensions of the paddle .
     * @param color is the color of the paddle.
     * @param keyboard is the keyboard sensor
     * @param speed is the speed of the paddle.
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, biuoop.KeyboardSensor keyboard, int speed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speedOfPaddle = speed;
    }
    /**
     * The method move the paddle to the left MOVE_SIZE (5) pixels
     * if the paddle "touch" in the left border of the screen the method does'nt do anything.
     * if the paddle almost "touch" the left border of the screen , the method move the paddle to the left border.
     */
    public void moveLeft() {
        // if the paddle exceed the limits in the next step
        if (this.rectangle.getUpperLeft().getX() - this.speedOfPaddle < GameLevel.BORDER_WIDTH) {
            //if still there is space
            if (this.rectangle.getUpperLeft().getX() > GameLevel.BORDER_WIDTH) {
                //put the paddle on the border
                this.rectangle.setStartingPoint(new Point(GameLevel.BORDER_WIDTH,
                        this.rectangle.getUpperLeft().getY()));
            }
            return;
        } else { //move the paddle
            this.rectangle.setStartingPoint(new Point(this.rectangle.getUpperLeft().getX() - this.speedOfPaddle,
                    this.rectangle.getUpperLeft().getY()));
        }
    }
    /**
     * The method move the paddle to the right MOVE_SIZE (5) pixels
     * if the paddle "touch" in the right border of the screen the method does'nt do anything.
     * if the paddle almost "touch" the right border of the screen , the method move the paddle to the right border.
     */
    public void moveRight() {
        // if the paddle exceed the limits in the next step
        if (this.rectangle.getDownRight().getX()
                > GameLevel.BOARD_WIDTH - this.speedOfPaddle - GameLevel.BORDER_WIDTH) {
            //if still there is space
            if (this.rectangle.getDownRight().getX() < GameLevel.BOARD_WIDTH - GameLevel.BORDER_WIDTH) {
                //put the paddle on the border of the board
                this.rectangle.setStartingPoint(new Point(GameLevel.BOARD_WIDTH - GameLevel.BORDER_WIDTH
                        - this.rectangle.getWidth(), this.rectangle.getUpperLeft().getY()));
            }
            return;
        } else {
            //move the paddle
            this.rectangle.setStartingPoint(new Point(this.rectangle.getUpperLeft().getX() + this.speedOfPaddle,
                    this.rectangle.getUpperLeft().getY()));
        }
    }
    /**
     * The method checks what key pressed in the keyboard.
     * if the left key pressed , the method move the paddle to the left.
     * if the right key pressed , the method move the paddle to the right.
     * if the paddle collide with the left/right border the method doesn't do anything
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * The method draw the paddle to given surface.
     * @param d the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
        //draw the frame of the block
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**
     * The method return the shape of the paddle.
     * @return the shape object of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**.
     * The method accept collision point with this paddle and velocity.
     * the method calculate new velocity expected after the hit.
     * the velocity change in every part of the paddle (the paddle divided to 5 parts)
     * @param hitter the ball that collide with the paddle.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity of the object that collide the block
     * @return new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int area =  checkAreaOfCollision(collisionPoint);
        if (area == 1) {
            return collisionArea1(currentVelocity);
        } else if (area == 2) {
            return collisionArea2(currentVelocity);
        } else if (area == 3) {
            return collisionArea3(collisionPoint, currentVelocity);
        }  else if (area == 4) {
            return collisionArea4(currentVelocity);
        } else if (area == 5) {
            return collisionArea5(currentVelocity);
        }
        //if the collision point not on the surface of the paddle
        return this.rectangle.hitOnRectangularShape(collisionPoint, currentVelocity);
    }
    /**
     * this method add the paddle to the game .
     * the method add the paddle to list that represent drawnable objects.
     * the method add the paddle to list that represent collidable objects.
     * @param g the game that the method add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * this method return the velocity of the ball after collision in area 1 of the paddle.
     * @param currentVelocity the velocity before the collision.
     * @return the new velocity after collision on area 1.
     */
    private Velocity collisionArea1(Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        return Velocity.fromAngleAndSpeed(300, speed);
    }
    /**
     * this method return the velocity of the ball after collision in area 2 of the paddle.
     * @param currentVelocity the velocity before the collision.
     * @return the new velocity after collision on area 2.
     */
    private Velocity collisionArea2(Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        return Velocity.fromAngleAndSpeed(330, speed);
    }
    /**
     * this method return the velocity of the ball after collision in area 3 of the paddle.
     * @param currentVelocity the velocity before the collision.
     * @param collisionPoint the collision point.
     * @return the new velocity after collision on area 3.
     */
    private Velocity collisionArea3(Point collisionPoint, Velocity currentVelocity) {
        return this.rectangle.hitOnRectangularShape(collisionPoint, currentVelocity);
    }
    /**
     * this method return the velocity of the ball after collision in area 4 of the paddle.
     * @param currentVelocity the velocity before the collision.
     * @return the new velocity after collision on area 4.
     */
    private Velocity collisionArea4(Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        return Velocity.fromAngleAndSpeed(30, speed);
    }
    /**
     * this method return the velocity of the ball after collision in area 5 of the paddle.
     * @param currentVelocity the velocity before the collision.
     * @return the new velocity after collision on area 5.
     */
    private Velocity collisionArea5(Velocity currentVelocity) {
        double speed = currentVelocity.getSpeed();
        return Velocity.fromAngleAndSpeed(60, speed);
    }
    /**
     * this method accept the collision point and return what area the collision occur.
     * @param collisionPoint is the collision point in the paddle.
     * @return the area of the collision.
     */
    private int checkAreaOfCollision(Point collisionPoint) {
        //the size of every part
        double sizeOfPart = this.rectangle.getWidth() / NUM_OF_PADDLE_AREAS;
        Line surfaceLine = this.rectangle.getUpperLine();
        //the ending/starting point of area number one
        Point areaStartingPoint = new Point(surfaceLine.start().getX(), surfaceLine.start().getY());
        Point areaEndPoint = new Point(surfaceLine.start().getX() + sizeOfPart, surfaceLine.start().getY());
        //check what area occur the collision
        for (int i = 0, area = 1; i < NUM_OF_PADDLE_AREAS; i++, area++) {
            Point newStartPart = new Point(areaStartingPoint.getX() + sizeOfPart * i, areaStartingPoint.getY());
            Point newEndPart = new Point(areaEndPoint.getX() + sizeOfPart * i, areaEndPoint.getY());
            //the line that represent the current part
            Line line = new Line(newStartPart, newEndPart);
            //if the collision point found on this part
            if (Line.isPointOnLine(collisionPoint, line)) {
                return area;
            }
        }
        //if there isn't intersection point on the surface of the paddle
        return 0;
    }
}