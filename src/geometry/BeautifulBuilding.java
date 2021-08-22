//ID 318525185
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/** this class represent a building and can draw it to gaven surface.
 * */
public class BeautifulBuilding implements Sprite {
    private static final int TORCH_RADIUS = 15;
    private static final int HEIGHT_TOP_OF_ANTENNA = 150;
    private static final int WIDTH_TOP_OF_ANTENNA = 15;
    private static final int WIDTH_BASE_OF_ANTENNA = 40;
    private static final int FLOORS = 5;
    private static final int WINDOWS_IN_ROW = 5;
    private static final int GAP_BETWEEN_WINDOWS = 10;
    private int height;
    private int width;
    private Point cornerTopLeft;

    /**
     * create a BeautifulBuilding accordingly to gaven corner top left point, height, width.
     *
     * @param cornerTopLeft the top left point of this building.
     * @param height        the height of the building.
     * @param width         the width of the building.
     */
    public BeautifulBuilding(Point cornerTopLeft, int height, int width) {
        this.cornerTopLeft = cornerTopLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * draw this building to gaven draw surface.
     *
     * @param d the gaven draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //fill the building
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.cornerTopLeft.getX(), (int) this.cornerTopLeft.getY(), this.width, this.height);
        List<Rectangle> windows = createAllTheWindows(this.cornerTopLeft);
        for (Rectangle r : windows) {
            r.drawOn(d);
        }
        List<Sprite> antenna = createAntenna();
        //draw the antenna
        for (Sprite s : antenna) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
    }

    /**
     * this method create the windows of the building.
     * return list of rectangles that represent the windows.
     *
     * @param startingPoint is the left upper point where the windows should start.
     * @return list of rectangles (windows).
     */
    private List<Rectangle> createAllTheWindows(Point startingPoint) {
        int windowsHeight = (this.height - (GAP_BETWEEN_WINDOWS * WINDOWS_IN_ROW + 1)) / WINDOWS_IN_ROW;
        Rectangle[][] allWindows = new Rectangle[FLOORS][WINDOWS_IN_ROW];
        //do the first gap
        startingPoint = new Point(startingPoint.getX() + GAP_BETWEEN_WINDOWS,
                startingPoint.getY() + GAP_BETWEEN_WINDOWS);
        //create all the rows of windows;
        for (int i = 0; i < FLOORS; i++) {
            allWindows[i] = createRowOfWindows(startingPoint);
            startingPoint = new Point(startingPoint.getX(),
                    startingPoint.getY() + windowsHeight + GAP_BETWEEN_WINDOWS);
        }
        List<Rectangle> recList = new ArrayList<>();
        //add all the windows to the list
        for (Rectangle[] recRow : allWindows) {
            for (Rectangle r : recRow) {
                recList.add(r);
            }
        }
        return recList;
    }

    /**
     * this method create row of windows of the building.
     * return list of rectangles that represent the windows.
     *
     * @param startingPoint is the left upper point where the row of windows should start.
     * @return list of rectangles (windows).
     */
    private Rectangle[] createRowOfWindows(Point startingPoint) {
        //calculate windows height and width
        int windowWidth = (this.width - (GAP_BETWEEN_WINDOWS * WINDOWS_IN_ROW + 1)) / WINDOWS_IN_ROW;
        int windowsHeight = (this.height - (GAP_BETWEEN_WINDOWS * WINDOWS_IN_ROW + 1)) / WINDOWS_IN_ROW;
        Rectangle[] rectangles = new Rectangle[WINDOWS_IN_ROW];
        for (int i = 0; i < rectangles.length; i++) { //create all the windows
            Point upperLeftWindowPoint = new Point(startingPoint.getX() + i * (windowWidth + GAP_BETWEEN_WINDOWS),
                    startingPoint.getY());
            rectangles[i] = new Rectangle(upperLeftWindowPoint, windowWidth, windowsHeight, Color.WHITE);
        }
        return rectangles;
    }
    /**
     * create the antenna of the building.
     *
     * @return sprite object that contain the antenna.
     */
    private List<Sprite> createAntenna() {
        List<Sprite> antenna = new ArrayList<>();
        double middleX = this.cornerTopLeft.getX() + (this.width / 2);
        //create the base of the antenna
        Point upperLeftBaseOfAntenna = new Point(middleX - (WIDTH_BASE_OF_ANTENNA) / 2,
                this.cornerTopLeft.getY() - WIDTH_BASE_OF_ANTENNA * 2);
        Rectangle rectangle = new Rectangle(upperLeftBaseOfAntenna, WIDTH_BASE_OF_ANTENNA,
                WIDTH_BASE_OF_ANTENNA * 2);
        Block antennaBase = new Block(rectangle, Color.DARK_GRAY);
        double bodyOfAntennaX = upperLeftBaseOfAntenna.getX() + WIDTH_BASE_OF_ANTENNA / 2 - WIDTH_TOP_OF_ANTENNA / 2;
        Point upperLeftAntennaBody = new Point(bodyOfAntennaX,
                upperLeftBaseOfAntenna.getY() - HEIGHT_TOP_OF_ANTENNA);
        Rectangle antennaBodyRec = new Rectangle(upperLeftAntennaBody, WIDTH_TOP_OF_ANTENNA, HEIGHT_TOP_OF_ANTENNA);
        Block antennaBody = new Block(antennaBodyRec, Color.GRAY); //the antenna body block
        Point centerOfTorch = new Point(upperLeftAntennaBody.getX() + (WIDTH_TOP_OF_ANTENNA / 2),
                upperLeftAntennaBody.getY() - TORCH_RADIUS);
        Ball torch = new Ball(centerOfTorch, TORCH_RADIUS, Color.YELLOW);
        Ball internalTorch = new Ball(centerOfTorch, TORCH_RADIUS / 3, Color.ORANGE);
        //add all the antenna components
        antenna.add(antennaBase);
        antenna.add(antennaBody);
        antenna.add(torch);
        antenna.add(internalTorch);
        return antenna;
    }
}








