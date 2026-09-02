import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import javax.imageio.ImageIO;

/**
 * Standalone decoder. Reads a PNG created by CSBOYLOVECSGIRL.hideMessage,
 * reveals the hidden message from its block pattern, then replaces that
 * same strip with the message drawn as actual readable text and saves the
 * result as a new image.
 *
 * How to run (no NetBeans project needed):
 *   1. Save this file as ImageDecoder.java
 *   2. Put output_love_letter.png in the same folder
 *   3. Open Command Prompt / Terminal in that folder
 *   4. javac ImageDecoder.java
 *   5. java ImageDecoder
 *   6. Open revealed_letter.png to see the actual letter
 */
public class ImageDecoder {

    private static final int BORDER_WIDTH = 150; // must match CSBOYLOVECSGIRL's BORDER_WIDTH
    private static final int BLOCK_SIZE = 15;    // must match CSBOYLOVECSGIRL's BLOCK_SIZE
    private static final int MARKER = 99;

    public static void main(String[] args) throws IOException {
        String inputPath = "output_love_letter.png";
        String outputPath = "revealed_letter.png";

        String message = revealMessage(inputPath);
        System.out.println("Hidden message: " + message);

        renderMessageAsText(inputPath, outputPath, message);
        System.out.println("Saved readable letter to: " + outputPath);
    }

    /** Reads the hidden message out of the block pattern on the left edge. */
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

    /** Replaces the block pattern on the left edge with the message drawn as real text. */
    public static void renderMessageAsText(String inputPath, String outputPath, String message) throws IOException {
        BufferedImage original = ImageIO.read(new File(inputPath));

        Graphics2D g2d = original.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // paint over the block strip with a plain letter-paper background
        g2d.setColor(new Color(255, 240, 245));
        g2d.fillRect(0, 0, BORDER_WIDTH, original.getHeight());

        // write the actual message on top of it
        g2d.setColor(new Color(139, 0, 0));
        g2d.setFont(new Font("Serif", Font.ITALIC, 18));
        int padding = 20;
        drawWrappedText(g2d, message, padding, padding, BORDER_WIDTH - (padding * 2), original.getHeight() - (padding * 2));

        g2d.dispose();
        ImageIO.write(original, "png", new File(outputPath));
    }

    private static void drawWrappedText(Graphics2D g2d, String text, int x, int y, int width, int height) {
        FontRenderContext frc = g2d.getFontRenderContext();
        FontMetrics metrics = g2d.getFontMetrics();
        float drawY = y;

        String[] paragraphs = text.split("\n");
        for (String paragraph : paragraphs) {
            if (paragraph.isEmpty()) {
                drawY += metrics.getHeight();
                continue;
            }

            AttributedString attributedString = new AttributedString(paragraph);
            attributedString.addAttribute(TextAttribute.FONT, g2d.getFont());
            LineBreakMeasurer measurer = new LineBreakMeasurer(attributedString.getIterator(), frc);

            while (measurer.getPosition() < paragraph.length()) {
                if (drawY > y + height) {
                    return;
                }
                TextLayout layout = measurer.nextLayout(width);
                drawY += layout.getAscent();
                layout.draw(g2d, x, drawY);
                drawY += layout.getDescent() + layout.getLeading();
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