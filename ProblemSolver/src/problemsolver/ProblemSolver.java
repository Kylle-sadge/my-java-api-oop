package problemsolver;

import myapi.math.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageProcessing.loadImage("jb.png");
            BufferedImage gray = ImageProcessing.toGrayscale(image);
            ImageProcessing.saveImage(gray, "grayscale.png", "png");

            System.out.println("Done! Check grayscale.png");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}