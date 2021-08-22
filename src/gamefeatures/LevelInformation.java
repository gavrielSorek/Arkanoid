//ID 318525185
package gamefeatures;
import geometry.Block;
import geometry.Sprite;
import geometry.Velocity;
import java.util.List;
/** this interface represent information of level.
 * */
public interface LevelInformation {
    /** return the number of balls in this level.
     * @return the number of balls in this level.
     * */
    int numberOfBalls();
    /** return list of velocities that fit to the balls number.
     * @return list of velocities.
     * */
    List<Velocity> initialBallVelocities();
    /** return the paddle speed.
     * @return paddle speed.
     * */
    int paddleSpeed();
    /** return the paddle width.
     * @return paddle width.
     * */
    int paddleWidth();
    /** return the level name.
     * the level name will be displayed at the top of the screen.
     * @return the level name.
     * */
    String levelName();
    /** Returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     * */
    Sprite getBackground();
    /** return The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     * */
    List<Block> blocks();
    /** return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return return Number of blocks that should be removed.
     * */
    int numberOfBlocksToRemove();
}