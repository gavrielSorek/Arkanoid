//ID 318525185.
package geometry;
import biuoop.DrawSurface;
import gamefeatures.GameLevel;
import listeners.HitListener;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * This class represent block and can draw blocks .
 */
public class Block implements Collidable, Sprite, HitNotifier {
    public static final int DEFAULT_BLOCK_WIDTH = 50;
    public static final int DEFAULT_BLOCK_HEIGHT = 20;
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * The method is a constructor , the method accept rectangle and color.
     * The method create a block.
     *
     * @param rectangle is the shape that represent the block .
     * @param color  is the color of the block.
     */
   public Block(Rectangle rectangle, java.awt.Color color) {
       this.rectangle = rectangle;
       this.color = color;
   }
    /**
     * The method is a constructor , the method accept rectangle.
     * The method create a block.
     *
     * @param rectangle is the shape that represent the block .
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.BLACK; //default color
    }
    /**
     * The method return the rectangle of this block.
     *
     * @return the rectangle of this block.
     */
   public Rectangle getRectangle() {
       return this.rectangle;
   }
    /**
     * The method return the color of this block.
     *
     * @return the color of this block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * The method accept color and sets it in this block.
     *
     * @param  colorOfBlock the color to sets of this block.
     */
    public void setColor(java.awt.Color colorOfBlock) {
        this.color = colorOfBlock;
    }
    /**
     * The method accept a draw surface
     * the method draw the block.
     *
     * @param  surface the surface to draw this block.
     */
    @Override
    public void drawOn(DrawSurface surface) {
       this.rectangle.drawOn(surface, color);
       //draw the frame of the block
        surface.setColor(Color.black);
       surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
               (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }
    /**.
     * The method return the rectangle that belong to this block
     *
     * @return this block rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**.
     * The method accept collision point with this block and velocity.
     * the method calculate new velocity expected after the hit.
     * @param hitter the ball that collide with this block.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity of the object that collide the block
     * @return new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
       Velocity newVelocity = this.rectangle.hitOnRectangularShape(collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        return newVelocity;
    }
    /**
     * the method implement Colidable interface
     * the method doesn't do anything (for now).
     */
    @Override
    public void timePassed() {
    }
    /**
     * the method add the block to given game.
     * the method add the block to sprites list and to Collidables list.
     * @param gameLevel given game to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * this method create borders to board that made of blocks, accordingly to the size of the board.
     * @param width the width of the board.
     * @param height the height of the board.
     * @return array of blocks that locate on the borders of the screen.
     */
    public static Block[] createBordersFromBlocks(int width, int height) {
        //create borders starting point
        //start from BORDER_WIDTH because of the Score text
        Point upBlockStart = new Point(0, GameLevel.BORDER_WIDTH);
        Point leftBlockStart = new Point(0, GameLevel.BORDER_WIDTH * 2);
        Point rightBlockStart = new Point(width - GameLevel.BORDER_WIDTH, GameLevel.BORDER_WIDTH * 2);
        //create boarders
        Block[] blockArray = new Block[3];
        blockArray[0] = new Block(new Rectangle(upBlockStart, width, GameLevel.BORDER_WIDTH), GameLevel.BORDER_COLOR);
        blockArray[1] = new Block(new Rectangle(leftBlockStart, GameLevel.BORDER_WIDTH,
                height - GameLevel.BORDER_WIDTH),
                GameLevel.BORDER_COLOR);
        blockArray[2] = new Block(new Rectangle(rightBlockStart, GameLevel.BORDER_WIDTH,
                height - GameLevel.BORDER_WIDTH),
                GameLevel.BORDER_COLOR);
        return blockArray;
    }
    /**
     * this method create row of blocks.
     * @param startingPoint the upper left point of the row of blocks.
     * @param numOfBlocks the number of blocks in the row.
     * @param colorOfBlocks the color of the row.
     * @return array that contain blocks.(the blocks located in a row according to the input)
     */
    public static  Block[] createRowOfBlocks(Point startingPoint, int numOfBlocks, java.awt.Color colorOfBlocks) {
        Block[] blockArray = new Block[numOfBlocks];
        //create Row Of Blocks
        for (int i = 0; i < blockArray.length; i++) {
            Rectangle rec = new Rectangle(startingPoint, Block.DEFAULT_BLOCK_WIDTH, Block.DEFAULT_BLOCK_HEIGHT);
            blockArray[i] = new Block(rec, colorOfBlocks);
            //move the starting point
            startingPoint = new Point(startingPoint.getX() + Block.DEFAULT_BLOCK_WIDTH, startingPoint.getY());
        }
        return blockArray;
    }
    /**create the death region block.
     * @param height the game board height.
     * @param width the game board width.
     * @return the death region block.
     * */
    public static Block deathRegionCreator(int height, int width) {
        Point downBlockStart = new Point(GameLevel.BORDER_WIDTH, height);
        return new Block(new Rectangle(downBlockStart, width - 2 * GameLevel.BORDER_WIDTH, GameLevel.BORDER_WIDTH),
                GameLevel.BORDER_COLOR);
    }
    /**remove block from gaven game.
     * @param gameLevel the gaven game.
     * */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    /**notify all the listeners that hit occur.
     * @param hitter the ball that hit the block.
     * */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**Add hl as a listener to hit events.
     * @param hl the listener to add.
     * */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**Remove hl from the list of listeners to hit events.
     * @param hl the listener yo remove.
     * */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * create a new block accordingly to the starting point and to the gaven color.
     * @param startingPoint is the upper left point of the block.
     * @param colorOfBlocks is the color of the block.
     * @return a  new block accordingly to the data.
     * */
    public static  Block createSingleBlock(Point startingPoint, java.awt.Color colorOfBlocks) {
        Rectangle rec = new Rectangle(startingPoint, Block.DEFAULT_BLOCK_WIDTH, Block.DEFAULT_BLOCK_HEIGHT);
        Block block = new Block(rec, colorOfBlocks);
        return block;
    }
}


