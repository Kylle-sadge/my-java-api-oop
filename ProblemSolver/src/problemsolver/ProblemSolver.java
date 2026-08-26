package problemsolver;

import myapi.image.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProblemSolver {

    public static void main(String[] args) {
        try {
            // Change this to a real image path on your machine
            String inputPath = "input.png";

            BufferedImage original = ImageProcessing.loadImage(inputPath);

            // Convert to grayscale
            BufferedImage gray = ImageProcessing.toGrayscale(original);
            ImageProcessing.saveImage(gray, "output_gray.png", "png");

            // Invert colors
            BufferedImage inverted = ImageProcessing.invertColors(original);
            ImageProcessing.saveImage(inverted, "output_inverted.png", "png");

            // Increase brightness
            BufferedImage brighter = ImageProcessing.adjustBrightness(original, 40);
            ImageProcessing.saveImage(brighter, "output_bright.png", "png");

            // Read RGBA of the top-left pixel
            int[] rgba = ImageProcessing.getPixelRGBA(original, 0, 0);
            System.out.println("Pixel (0,0) RGBA: R=" + rgba[0] + " G=" + rgba[1] +
                                " B=" + rgba[2] + " A=" + rgba[3]);

            System.out.println("Processing complete. Check output_gray.png, output_inverted.png, output_bright.png");

        } catch (IOException e) {
            System.out.println("Error processing image: " + e.getMessage());
        }
    }
}