package problemsolver;

import myapi.math.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageProcessing.loadImage("jb.png");
            BufferedImage gray = ImageProcessing.toGrayscale(image);
            ImageProcessing.saveImage(gray, "grayscale.jpg", "jpg");

            System.out.println("Done! Check grayscale.jpg");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}