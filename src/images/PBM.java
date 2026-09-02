package images;

import images.pixels.Pixel;
import images.pixels.Pixels;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Представя изображение във формат PBM и предоставя функционалност
 * за зареждане и записване на изображения в този формат.
 *
 * @author Иво Маринов
 */

public class PBM extends AbstractImage {
    public PBM(String fileName) { super(fileName); }

    @Override
    public void load(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            String type = scanner.next();
            if (!type.equals("P1")) throw new IllegalArgumentException("Invalid PBM format.");

            width = scanner.nextInt();
            height = scanner.nextInt();
            pixels = new Pixels(width, height);

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int value = scanner.nextInt();
                    int color = (value == 1) ? 0 : 255; // P1: 1 e черно, 0 е бяло
                    pixels.setPixel(col, row, new Pixel(color, color, color));
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
            writer.println("P1");
            writer.println(width + " " + height);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int val = (pixels.getPixel(col, row).getRed() < 128) ? 1 : 0;
                    writer.print(val + " ");
                }
                writer.println();
            }
            System.out.println("Successfully saved " + path);
        } catch (Exception e) {
            System.out.println("Error while saving " + path);
        }
    }

    @Override public String getType() { return "pbm"; }
}
