//ID 318525185
package animations;
import biuoop.DrawSurface;
import gamefeatures.AnimationRunner;
import geometry.SpriteCollection;
import java.awt.Color;
/**
  The CountdownAnimation will display the given gameScreen,
  for numOfSeconds seconds, and on top of them it will show
  a countdown from countFrom back to 1, where each number will
  appear on the screen for (numOfSeconds / countFrom) seconds, before
  it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final int COUNT_UNTIL = 0;
    private  biuoop.Sleeper sleeper = new biuoop.Sleeper();;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private AnimationRunner runner;
    private final double timeToWait; // waiting time
    private boolean firstLoop;
    private int currentCount;
    private boolean lastLoop;
    private long beginningStartTime;
    /** constructor create a new countdown animation, the animation will appear for numOfSecond seconds.
     * the counting will begin from count from.
     *the count will appear on the game screen.
     * @param numOfSeconds the number of second that the count down will appear.
     * @param countFrom the number to count from to 0.
     * @param gameScreen is the sprites that need to appear in the game board.
     * */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.timeToWait =  (numOfSeconds / countFrom) * 1000;
        this.firstLoop = true;
        this.currentCount = countFrom;
        this.lastLoop = false;
        this.beginningStartTime = System.currentTimeMillis();
    }
    /** do one game frame.
     * @param d the draw surface.
     * */
    public void doOneFrame(DrawSurface d) {
        if (this.currentCount >= COUNT_UNTIL) { //if the count doesnt over
            if (this.firstLoop) { //if it is the first iteration.
                this.gameScreen.drawAllOn(d);
                d.drawText(80, d.getHeight() / 7, "The game starts in:" + getCurrentCount(), 32);
                this.firstLoop = false;
                this.beginningStartTime = System.currentTimeMillis();
                this.currentCount = this.countFrom - 1;
                return;
            }
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.RED);
            d.drawText(80, d.getHeight() / 7, "The game starts in:" + getCurrentCount(), 32);
            setCurrentCount(getCurrentCount() - 1); //set the current count
            long sleepFor = (long) this.timeToWait - (System.currentTimeMillis() - this.beginningStartTime);
            if (sleepFor <= 0) {
                sleepFor = 0;
            }
            sleeper.sleepFor(sleepFor);
            this.beginningStartTime = System.currentTimeMillis();
        } else {
            this.gameScreen.drawAllOn(d);
            d.drawText(80, d.getHeight() / 7, "The game starts in:" + COUNT_UNTIL, 32);
            if (lastLoop) { //in order to do one more loop
                long sleepFor = (long) this.timeToWait - (System.currentTimeMillis() - this.beginningStartTime);
                if (sleepFor <= 0) {
                    sleepFor = 0;
                }
                sleeper.sleepFor(sleepFor);
                this.stop = true;
            }
            lastLoop = true;
        }
    }

    /**return true if the animation should stop.
     * @return true true if the animation should stop, false otherwise.
     * */
    public boolean shouldStop() {
        return this.stop;
    }
     /** .
     * return the current count.
     * @return the current count.
     * */
    public int getCurrentCount() {
        return currentCount;
    }
    /** .
     * set current count.
     * @param currentC is the new current count.
     * */
    public void setCurrentCount(int currentC) {
        this.currentCount = currentC;
    }
}
