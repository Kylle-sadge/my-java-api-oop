package myapi.math;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing1 {

    public static BufferedImage loadImage(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String filePath, String format) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, format, outputFile);
    }

    // Recolors the flower's petals and stem to blue, leaving the white background alone
    public static BufferedImage recolorToBlue(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Color c = new Color(original.getRGB(j, i));

                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                // Skip near-white/near-black pixels (background and outlines) so they stay unchanged
                boolean isNearWhite = (red > 200 && green > 200 && blue > 200);
                boolean isNearBlack = (red < 60 && green < 60 && blue < 60);

                if (!isNearWhite && !isNearBlack) {
                    // Use the pixel's brightness to create a blue shade,
                    // so shading/highlights from the original image are kept
                    int brightness = (red + green + blue) / 3;

                    int newRed = brightness / 4;
                    int newGreen = brightness / 2;
                    int newBlue = brightness;

                    Color newColor = new Color(newRed, newGreen, newBlue);
                    original.setRGB(j, i, newColor.getRGB());
                }
            }
        }

        return original;
    }
}