//ID 318525185
package gamefeatures;
import animations.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/** this class in charge of running animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;
    /** .
     * accept gui and how many frame per second , and create AnimationRunner.
     * @param gui the gui of the game.
     * @param framesPerSecond this is a many frame per second this animation runner will do.
     * */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new biuoop.Sleeper();
    }
    /** run gaven animation.
     * @param animation the animation to run.
     * */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}