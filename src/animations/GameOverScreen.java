//ID 318525185
package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/** .
 * this class represent animation of game over, this class print the score in the end of the game.
 * */
public class GameOverScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    /** constructor of gameOverScreen.
     * @param k is the keyboard sensor.
     * @param score is the score of the player at the end of the game.
     * */
    public GameOverScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    /** do one frame print massage and score on given draw surface.
     * @param d the draw surface.
     * */
    public void doOneFrame(DrawSurface d) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is  " + this.score, 32);
    }
    /**return true if the animation should stop.
     * @return  true if the animation should stop, false otherwise.
     * */
    public boolean shouldStop() { return this.stop; }
}
