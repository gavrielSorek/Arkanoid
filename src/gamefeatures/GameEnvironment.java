//ID 318525185.
package gamefeatures;
import geometry.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represent environment of game and contain list of collidable objects that found on game board .
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();
    /**
     * The method add the given collidable to the environment.
     *
     * @param c is a collidable object that the method add to game environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**.
     * The method check if trajectory collide with any of the object in the collection.
     * if collision occur , the method return information on the closest collision (CollisionInfo)
     * If the object will not collide with any of the collidables in this collection, return null.
     * @param trajectory is line that represent a trajectory of object.
     * @return object that his type is CollisionInfo .
     * that object contain information on the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollisionPoint = null; // the point of closest collision that is going to occur.
        Collidable collisionClosestObject = null; // the object of closest collision that is going to occur.
        List<Collidable> collidablesList = new ArrayList<>(this.collidables);
        for (Collidable c : collidablesList) {
            Rectangle rec = c.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(rec) != null) {
                if (closestCollisionPoint == null) { //if still there isn't collision point
                    closestCollisionPoint = trajectory.closestIntersectionToStartOfLine(rec);
                    collisionClosestObject = c;
                } else { //if there is already collision point
                    Point newCollisionPoint = trajectory.closestIntersectionToStartOfLine(rec);
                    if (trajectory.start().distance(newCollisionPoint)
                            < trajectory.start().distance(closestCollisionPoint)) { //if the new collision point closer
                        closestCollisionPoint = newCollisionPoint;
                        collisionClosestObject = c;
                    }
                }
            }
        }
        if (closestCollisionPoint != null) { //if collision occur
            return new CollisionInfo(closestCollisionPoint, collisionClosestObject);
        }
        return null; //if collision didnt occur
    }
    /**remove collidable object from the game.
     *@param c the collidable object to remove.
     **/
    public void removeCollidable(Collidable c) {
        List<Collidable> collidablesList = getCollidables();
        collidablesList.remove(c);
    }
    /**return list of collidables object that participant in the game.
     *@return  the list.
     **/
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}