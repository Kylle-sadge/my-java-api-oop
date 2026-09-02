package myapi.math;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Hides a text message inside a photo by overwriting a strip of its left
 * edge with colored blocks. Each block's R/G/B values are the raw ASCII
 * codes of up to 3 characters, so the strip looks like a colorful pattern
 * but isn't readable as text without calling revealMessage.
 *
 * Output must be saved as PNG — JPEG compression will corrupt the hidden
 * values.
 *
 * @author CS Boy
 */
public class CSBOYLOVECSGIRL {

    private static final int BORDER_WIDTH = 150; // how wide the strip is, in pixels
    private static final int BLOCK_SIZE = 15;    // size of each colored square, in pixels
    private static final int MARKER = 99;        // confirms a message is really there

    private CSBOYLOVECSGIRL() {
    }

    /** Hides message in the photo's left edge and saves the result as PNG. */
    public static void hideMessage(String inputPath, String outputPath, String message) throws IOException {
        BufferedImage img = ImageIO.read(new File(inputPath));
        if (img == null) {
            throw new IOException("Could not read image file: " + inputPath);
        }

        int blocksPerColumn = img.getHeight() / BLOCK_SIZE;
        int blocksPerRow = BORDER_WIDTH / BLOCK_SIZE;
        int totalBlocks = blocksPerRow * blocksPerColumn;
        int neededBlocks = 1 + (int) Math.ceil(message.length() / 3.0);

        if (neededBlocks > totalBlocks) {
            throw new IllegalArgumentException("Message too long for this border size. Needs "
                    + neededBlocks + " blocks, only " + totalBlocks + " available.");
        }

        int length = message.length();
        int blockIndex = 0;

        // header block: message length + marker
        setBlock(img, blockIndex, (length >> 8) & 0xFF, length & 0xFF, MARKER);
        blockIndex++;

        // 3 characters per block, one per R/G/B
        for (int i = 0; i < length; i += 3) {
            int r = message.charAt(i);
            int g = (i + 1 < length) ? message.charAt(i + 1) : 0;
            int b = (i + 2 < length) ? message.charAt(i + 2) : 0;
            setBlock(img, blockIndex, r, g, b);
            blockIndex++;
        }

        ImageIO.write(img, "png", new File(outputPath));
    }

    /** Reads the hidden message back out of a PNG created by hideMessage. */
    public static String revealMessage(String imagePath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imagePath));
        if (img == null) {
            throw new IOException("Could not read image file: " + imagePath);
        }

        Color header = getBlock(img, 0);
        if (header.getBlue() != MARKER) {
            throw new IllegalStateException("No hidden message found (was this file re-saved as JPEG?)");
        }
        int length = (header.getRed() << 8) | header.getGreen();

        StringBuilder result = new StringBuilder();
        int blockIndex = 1;
        while (result.length() < length) {
            Color c = getBlock(img, blockIndex);
            int[] values = { c.getRed(), c.getGreen(), c.getBlue() };
            for (int v : values) {
                if (result.length() >= length) {
                    break;
                }
                result.append((char) v);
            }
            blockIndex++;
        }

        return result.toString();
    }

    private static void setBlock(BufferedImage img, int blockIndex, int r, int g, int b) {
        int blocksPerColumn = img.getHeight() / BLOCK_SIZE;
        int col = blockIndex / blocksPerColumn;
        int row = blockIndex % blocksPerColumn;
        int startX = col * BLOCK_SIZE;
        int startY = row * BLOCK_SIZE;
        int rgb = new Color(r, g, b).getRGB();

        for (int y = startY; y < startY + BLOCK_SIZE && y < img.getHeight(); y++) {
            for (int x = startX; x < startX + BLOCK_SIZE && x < img.getWidth(); x++) {
                img.setRGB(x, y, rgb);
            }
        }
    }

    private static Color getBlock(BufferedImage img, int blockIndex) {
        int blocksPerColumn = img.getHeight() / BLOCK_SIZE;
        int col = blockIndex / blocksPerColumn;
        int row = blockIndex % blocksPerColumn;
        return new Color(img.getRGB(col * BLOCK_SIZE, row * BLOCK_SIZE));
    }
}