package gamefeatures; /** this class update the high score if needed.
 * */
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.OutputStreamWriter;
/** this class update the high score if needed.
 * */
public class UpdateHighScores {
    public static final int NO_SCORE = -1;
    /** the method accept currently score and return true if it's the high score, false otherwise.
     * @param score current score and possibly the high score.
     * @return true if the gaven number is the high score, false otherwise.
     * */
    private boolean isHighScore(int score) {
        int currentHighScore = getCurrentHighScore();
        if (score > currentHighScore) { //if the new score is bigger
            return true;
        }
        return false;
    }
    /** this method accept string that contain the high score, and return the high score.
     * @param scoreString the string that contain the high score.
     * @return the high score
     * */
    public int extractScoreFromString(String scoreString) {
        String patternString = "[0-9]+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(scoreString);
        if (matcher.find()) { //if found the score
            return Integer.parseInt(scoreString.substring(matcher.start(), matcher.end())); //return the score
        } else {
            throw new RuntimeException("there is no score in score file");
        }

    }
    /** update the high score if score is bigger then the high score.
     * @param score the score to check if bigger than the high score.
     * */
    public void updateHighScore(int score) {
        if (isHighScore(score)) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("highscores.txt")));
                writer.printf("The highest score so far is: %d", score);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        } else {
            return;
        }
    }
    public int getCurrentHighScore() {
        File scoreFile = new File("highscores.txt");
        if (!scoreFile.exists()) { //if the file not exist
            return NO_SCORE; //no score yet
        }
        String scoreString = ""; //initialize
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("highscores.txt")));
            String line;
            while ((line = reader.readLine()) != null) { //read all the file
                scoreString = scoreString + "" + line;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally { //close the file
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("fail to close the file");
                }
            }
        }
        return extractScoreFromString(scoreString);
    }
}
