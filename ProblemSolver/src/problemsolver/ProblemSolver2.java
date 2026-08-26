package problemsolver;

import myapi.math.ImageProcessing2;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver2 {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageProcessing2.loadImage("flower.jpg");

            // Change to false if you want pure white background instead
            boolean makeBackgroundBlack = true;

            BufferedImage result = ImageProcessing2.removeBackground(image, makeBackgroundBlack);
            ImageProcessing2.saveImage(result, "output_bg_removed.jpg", "jpg");

            System.out.println("Done! Check output_bg_removed.jpg");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
