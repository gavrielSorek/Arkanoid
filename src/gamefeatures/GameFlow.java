//ID 318525185
package gamefeatures;
import animations.*;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
/** .
 * this class represent course of game and charge to run the levels.
 * */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score = new Counter();
    private GUI gui;
    /** accept animation runner and keyboard sensor and gui.
     * create GameFlow object.
     * @param ar the animation runner.
     * @param ks the keyboard sensor.
     * @param gui the gui of the game.
     * */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }
    /** run all the levels that appear in the list.
     * @param levels the list of levels that the method run.
     * */
    public void runLevels(List<LevelInformation> levels) {
        Animation highScore = new ShowHighScore(); //high score animation
        KeyPressStoppableAnimation stopHighScore = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, highScore);
        UpdateHighScores updateScore = new UpdateHighScores();
        int numberOfLevel = 1;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.gui);
            level.initialize();
            while (level.getAmountOfRemainBlocks().getValue() > 0
                    && level.getAmountOfRemainBalls().getValue() > 0) { //level has more blocks and balls
                level.run();
            }
            if (numberOfLevel == levels.size() && level.getAmountOfRemainBlocks().getValue() == 0) { //if winning
                updateScore.updateHighScore(this.score.getValue());
                YowWinScreen winScreen = new YowWinScreen(this.keyboardSensor, this.score.getValue());
                KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, winScreen);
                this.animationRunner.run(k);
                this.animationRunner.run(stopHighScore);
                break;
            }
            if (level.getAmountOfRemainBalls().getValue() == 0) { //no more balls
                updateScore.updateHighScore(this.score.getValue());
                GameOverScreen gameOverScreen = new GameOverScreen(this.keyboardSensor, this.score.getValue());
                KeyPressStoppableAnimation g = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, gameOverScreen);
                this.animationRunner.run(g); //run game over animation
                this.animationRunner.run(stopHighScore);
                break;
            }
            numberOfLevel++; // increase to the next level
        }
    }
}