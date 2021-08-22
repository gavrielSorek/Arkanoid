import java.awt.*;
import java.util.*;
import java.util.Random;
public class CollideTest {

    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("test", 600, 600);
        Random random = new Random();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        biuoop.KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        int x = random.nextInt(200) + 120;
        int y = random.nextInt(200) + 120;
        Ball ball = new Ball(x, y, 7, java.awt.Color.BLUE);
        double speed = random.nextInt(50);
        double angle = random.nextInt(360);
        Velocity v = Velocity.fromAngleAndSpeed(20, speed);
        ball.setVelocity(v);
        GameEnvironment gameEnvironment = new GameEnvironment();
        ball.setGameEnvironment(gameEnvironment);
        Rectangle rectangle = new Rectangle(new Point(0, 0), 600, 10);
        Block block1 = new Block(rectangle, java.awt.Color.GREEN);
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 10, 600);
        Block block2 = new Block(rectangle2, java.awt.Color.GREEN);
        Rectangle rectangle3 = new Rectangle(new Point(500, 0), 10, 600);
        Block block3 = new Block(rectangle3, java.awt.Color.GREEN);
        Rectangle rectangle4 = new Rectangle(new Point(0, 500), 600, 10);
        Rectangle rectangle5 = new Rectangle(new Point(200, 300), 100, 20);
        Block block5 = new Block(rectangle5, Color.BLUE);
        Block block4 = new Block(rectangle4, java.awt.Color.GREEN);
        Block[] blocks = new Block[] { block1, block2, block3, block4, block5 };
        for (Block block : blocks) {
            ball.getGameEnvironment().addCollidable(block);
        }
        while (true) {
            if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                gui.close();
            }
            biuoop.DrawSurface drawSurface = gui.getDrawSurface();
            for (Block block : blocks) {
                block.drawOn(drawSurface);
            }
            ball.moveOneStep();
            ball.drawOn(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(50);
        }

    }
}
