package images;

import images.pixels.Pixel;
import images.pixels.Pixels;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PGM extends AbstractImage {
    public PGM(String fileName) { super(fileName); }

    @Override
    public void load(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            String type = scanner.next();
            if (!type.equals("P2")) throw new IllegalArgumentException("Invalid PGM format.");

            width = scanner.nextInt();
            height = scanner.nextInt();
            int maxValue = scanner.nextInt();
            pixels = new Pixels(width, height);

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int gray = scanner.nextInt();
                    // Мащабиране към 255, ако оригиналният макс е различен
                    if (maxValue != 255) {
                        gray = (gray * 255) / maxValue;
                    }
                    pixels.setPixel(col, row, new Pixel(gray, gray, gray));
                }
            }
            System.out.println("Successfully loaded " + path);
        } catch (Exception e) {
            System.out.println("Error while opening " + path);
        }
    }

    @Override
    public void save(String path) {
        try (PrintWriter writer = new PrintWriter(new File(path))) {
            writer.println("P2");
            writer.println(width + " " + height);
            writer.println("255");
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    writer.print(pixels.getPixel(col, row).getRed() + " ");
                }
                writer.println();
            }
            System.out.println("Successfully saved " + path);
        } catch (Exception e) {
            System.out.println("Error while saving " + path);
        }
    }

    @Override public String getType() { return "pgm"; }
}
