package problemsolver;

import myapi.math.ImageProcessing3;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver3 {

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageProcessing3.loadImage("jb.png");
            BufferedImage enlarged = ImageProcessing3.enlarge4x(image);
            ImageProcessing3.saveImage(enlarged, "output_enlarged.png", "png");

            System.out.println("Original size: " + image.getWidth() + "x" + image.getHeight());
            System.out.println("Enlarged size: " + enlarged.getWidth() + "x" + enlarged.getHeight());
            System.out.println("Done! Check output_enlarged.png"
                    + "");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}