//ID 318525185
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamefeatures.LevelOne;
import gamefeatures.LevelTwo;
import gamefeatures.LevelThree;
import gamefeatures.LevelFour;
import gamefeatures.GameFlow;
import gamefeatures.AnimationRunner;
import gamefeatures.GameLevel;
import gamefeatures.LevelInformation;
import java.util.ArrayList;
import java.util.List;
/** this class Initializing game, and run it.
 * */
public class Ass6Game {
    private static final int NUM_OF_LEVELS = 4;
    /** create a game with levels accordingly to the args.
     * if there is no args, the levels of the game will be 1 ,2 ,3 ,4
     * @param args contain strings that represent levels (this levels will appear in this game).
     * */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, 60);
        List<LevelInformation> listOfLevels = new ArrayList<>();
        for (String s : args) { //if we accept args
            //if its valid level
            if (isNumber(s) && (Integer.parseInt(s) >= 1) && (Integer.parseInt(s) <= NUM_OF_LEVELS)) {
                addLevel(Integer.parseInt(s), listOfLevels);
            }
        }
        if (listOfLevels.isEmpty()) { //if there is no levels in the list
            addLevel(1, listOfLevels);
            addLevel(2, listOfLevels);
            addLevel(3, listOfLevels);
            addLevel(4, listOfLevels);
        }
        GameFlow g = new GameFlow(runner, keyboardSensor, gui);
        GameMenu menu = new GameMenu(runner, keyboardSensor, gui, g, listOfLevels);
        menu.run();
    }
    /** the method accept number of level and add this level to acceptance list.
     * @param numberOfLevel the number of level to add to the list.
     * @param levelsList the list to add to the level.
     * */
    private static void addLevel(int numberOfLevel, List<LevelInformation> levelsList) {
        if (numberOfLevel == 1) {
            levelsList.add(new LevelOne());
        } else if (numberOfLevel == 2) {
            levelsList.add(new LevelTwo());
        } else if (numberOfLevel == 3) {
            levelsList.add(new LevelThree());
        } else if (numberOfLevel == 4) {
            levelsList.add(new LevelFour());
        } else {
            throw new RuntimeException("we created only 4 levels");
        }
    }
    /**
     * check if string is a number.
     *
     * @param s the string to check.
     * @return true if s is  a number, false otherwise.
     */
    private static boolean isNumber(String s) {
        return s.matches("[0-9]+");
    }
}
