//ID 318525185.
package gamefeatures;
import geometry.Collidable;
import geometry.Point;
/**
 * This class represent information of collision point .
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * The method is a constructor , the method accept collision point and collision object.
     * The method create a collision info object that contain information of collision.
     *
     * @param collisionPoint is the collision point .
     * @param collisionObject  is the object that occur collision with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * The method return the collision point.
     *
     * @return  the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * The method return collidable object that involved in the collision.
     *
     * @return collidable object that involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}