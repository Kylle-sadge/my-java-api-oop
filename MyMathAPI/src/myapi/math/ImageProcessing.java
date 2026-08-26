package myapi.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing {

    // Load an image file into a BufferedImage
    public static BufferedImage loadImage(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("Unsupported or unreadable image format: " + filePath);
        }
        return image;
    }

    // Save a BufferedImage to disk (format inferred from file extension, e.g. "png", "jpg")
    public static void saveImage(BufferedImage image, String filePath, String format) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, format, outputFile);
    }

    // Convert an image to grayscale using the standard luminance formula
    public static BufferedImage toGrayscale(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = original.getRGB(x, y);

                int alpha = (argb >> 24) & 0xFF;
                int red   = (argb >> 16) & 0xFF;
                int green = (argb >> 8) & 0xFF;
                int blue  = argb & 0xFF;

                // Luminance-weighted grayscale (matches human perception of brightness)
                int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                int grayArgb = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
                grayImage.setRGB(x, y, grayArgb);
            }
        }

        return grayImage;
    }

    // Invert the colors of an image (negative effect)
    public static BufferedImage invertColors(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage inverted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = original.getRGB(x, y);

                int alpha = (argb >> 24) & 0xFF;
                int red   = 255 - ((argb >> 16) & 0xFF);
                int green = 255 - ((argb >> 8) & 0xFF);
                int blue  = 255 - (argb & 0xFF);

                int invertedArgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                inverted.setRGB(x, y, invertedArgb);
            }
        }

        return inverted;
    }

    // Adjust brightness by adding an offset to each RGB channel (clamped 0-255)
    public static BufferedImage adjustBrightness(BufferedImage original, int offset) {
        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage adjusted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = original.getRGB(x, y);

                int alpha = (argb >> 24) & 0xFF;
                int red   = clamp(((argb >> 16) & 0xFF) + offset);
                int green = clamp(((argb >> 8) & 0xFF) + offset);
                int blue  = clamp((argb & 0xFF) + offset);

                int newArgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                adjusted.setRGB(x, y, newArgb);
            }
        }

        return adjusted;
    }

    // Get the RGBA components of a single pixel as an int array [R, G, B, A]
    public static int[] getPixelRGBA(BufferedImage image, int x, int y) {
        int argb = image.getRGB(x, y);
        int alpha = (argb >> 24) & 0xFF;
        int red   = (argb >> 16) & 0xFF;
        int green = (argb >> 8) & 0xFF;
        int blue  = argb & 0xFF;
        return new int[] { red, green, blue, alpha };
    }

    // Helper: keep a value within 0-255
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}