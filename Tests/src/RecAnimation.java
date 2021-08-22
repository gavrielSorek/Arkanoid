import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class RecAnimation {
    private static final int SCREEN_SIZE = 500;

    public static void main(String[] args) {

        Rectangle[] rArray = new Rectangle[7];
        rArray[0] = new Rectangle(new Point(100, 90), 10, 10);
        rArray[1] = new Rectangle(new Point(140, 90), 10, 10);
        rArray[6] = new Rectangle(new Point(100, 300), 100, 10);
        rArray[2] = new Rectangle(new Point(0, 0), 500, 10);
        rArray[3] = new Rectangle(new Point(0, 10), 10, 490);
        rArray[4] = new Rectangle(new Point(10, 490), 480, 10);
        rArray[5] = new Rectangle(new Point(490, 10), 10, 490);
        Ball ball = new Ball(60 ,100 ,  7, Color.BLACK);
        Block[]  b= new Block[7];
        GameEnvironment game = new GameEnvironment();
        for (int i=0; i<b.length;i++)
        {
            b[i] = new Block(rArray[i], Color.red);
            game.addCollidable(b[i]);
        }

        ball.setGameEnvironment(game);
        //ball.setVelocity(new Velocity(1,0));
        ball.setVelocity(Velocity.fromAngleAndSpeed(90,1));

        GUI gui1 = new GUI("BallsAnimationMultiFrame", SCREEN_SIZE, SCREEN_SIZE);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        DrawSurface d1;
        //create the animation
        while (true) {
            d1 = gui1.getDrawSurface(); //get draw surface
            for (int i = 0; i < b.length; i++) {
               b[i].drawOn(d1);
            }
            ball.drawOn(d1);
           ball.moveOneStep();
           System.out.println("("+ball.getCenterPoint().getX()+","+ball.getCenterPoint().getY()+")");
            //show the board
            gui1.show(d1);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.

        }

    }

}

