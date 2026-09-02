package problemsolver;

import java.io.IOException;
import myapi.math.CSBOYLOVECSGIRL;

public class LoveLetterMain {

    public static void main(String[] args) {

        String inputPath = "input.jpg";
        String outputPath = "output_love_letter.png";
        String message = "I yearn for you, bbg";

        try {
            CSBOYLOVECSGIRL.hideMessage(inputPath, outputPath, message);
            System.out.println("Saved: " + outputPath);

            String decoded = CSBOYLOVECSGIRL.revealMessage(outputPath);
            System.out.println("Decoded: " + decoded);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}