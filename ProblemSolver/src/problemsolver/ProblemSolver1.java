package problemsolver;

import myapi.math.ImageProcessing1;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver1 {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageProcessing1.loadImage("input.jpg");
            BufferedImage blue = ImageProcessing1.recolorToBlue(image);
            ImageProcessing1.saveImage(blue, "output_blue.jpg", "jpg");

            System.out.println("Done! Check output_blue.jpg");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}