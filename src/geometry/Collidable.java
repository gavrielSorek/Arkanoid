//ID 318525185.
package geometry;
/**
 * This interface represent collidable objects .
 */
public interface Collidable {
    /**.
     * the method return the "collision shape" of the object that occur with it collision.
     * @return  the "collision shape" of the object that occur with it collision .
     */
    Rectangle getCollisionRectangle();
    /**.
     * The method accept collision point with the collidable object and velocity.
     * the method calculate new velocity expected after the hit.
     * @param hitter the ball the collide with the collidable object.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity of the object that,
     * collide with the the collidable object.
     * @return new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
