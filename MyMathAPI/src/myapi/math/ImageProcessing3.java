package myapi.math;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing3 {

    public static BufferedImage loadImage(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String filePath, String format) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, format, outputFile);
    }

    // Enlarges the image 4x by copying each pixel into a 4x4 block
    public static BufferedImage enlarge4x(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        int newWidth = width * 2;
        int newHeight = height * 2;

        BufferedImage enlarged = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Color c = new Color(original.getRGB(j, i));

                // Each original pixel (j, i) maps to a 4x4 block
                // starting at (j*2, i*2) in the enlarged image
                int newX = j * 2;
                int newY = i * 2;

                enlarged.setRGB(newX, newY, c.getRGB());       // top-left
                enlarged.setRGB(newX + 1, newY, c.getRGB());   // top-right
                enlarged.setRGB(newX, newY + 1, c.getRGB());   // bottom-left
                enlarged.setRGB(newX + 1, newY + 1, c.getRGB()); // bottom-right
            }
        }

        return enlarged;
    }
}