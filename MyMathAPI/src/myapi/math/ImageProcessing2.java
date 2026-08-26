package myapi.math;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing2 {

    public static BufferedImage loadImage(String filePath) throws IOException {
        File file = new File(filePath);
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String filePath, String format) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, format, outputFile);
    }

    // Removes a near-white/light background, replacing it with pure white or pure black.
    // toBlack = true -> background becomes pure black. toBlack = false -> pure white.
    public static BufferedImage removeBackground(BufferedImage original, boolean toBlack) {
        int width = original.getWidth();
        int height = original.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Color c = new Color(original.getRGB(j, i));

                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                // Treat light, low-contrast pixels (the textured white background) as background
                boolean isBackground = (red > 190 && green > 190 && blue > 190);

                if (isBackground) {
                    Color replacement = toBlack ? Color.BLACK : Color.WHITE;
                    original.setRGB(j, i, replacement.getRGB());
                }
            }
        }

        return original;
    }
}