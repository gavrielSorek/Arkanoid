//ID 318525185.
package gamefeatures;
import biuoop.DrawSurface;
import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Sprite;
import java.awt.Color;
/**This class represent the score of the user in the game, and printing the score to the screen.
 * */
public class ScoreIndicator implements Sprite {
    private static final int FONT_SIZE = 15;
    private static final int DISTANCE_FROM_THE_CENTER = 15;
    private static final int HEIGHT = 20;
    private Counter score = null;
    private Block representBlock = null;
/** the constructor accept counter and create ScoreIndicator.
 * @param score the gaven counter.
 **/
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /** the constructor accept counter, board width/height and create ScoreIndicator.
     * this constructor create rectangle make it easy to write the score.
     * @param score the gaven counter.
     * @param boardHeight the height of the game board.
     * @param boardWidth the width of the game board.
     **/
    public ScoreIndicator(Counter score, int boardWidth, int boardHeight) {
        this.score = score;
        Point startPoint = new Point(0, 0);
        Rectangle rec = new Rectangle(startPoint, boardWidth, HEIGHT);
        representBlock = new Block(rec, Color.WHITE);
    }

    /**
     * .
     * the method draw the score to the screen.
     *
     * @param d is the draw surface.
     */
    public void drawOn(DrawSurface d) {
        Point middle = this.representBlock.getRectangle().getUpperLine().middle();
        Point middleLeft = new Point(middle.getX() - 10, middle.getY()); //middle point of represent block
        String scoreString = "Score: " + this.score.getValue();
        d.setColor(Color.BLACK);
        d.drawText((int) middleLeft.getX() - DISTANCE_FROM_THE_CENTER,
                (int) middleLeft.getY() + DISTANCE_FROM_THE_CENTER, scoreString, FONT_SIZE);

    }
    /**the method add the score to gaven game.
     * @param gameLevel the gaven game.
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * .
     * notify the sprite that time has passed
     */
    public void timePassed() {
    }
}
