package images;

import images.pixels.Pixel;
import images.pixels.Pixels;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Представя изображение във формат PPM и предоставя функционалност
 * за зареждане и записване на изображения в този формат.
 *
 * @author Иво Маринов
 */

public class PPM extends AbstractImage {
    public PPM(String fileName) { super(fileName); }

    @Override
    public void load(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            String type = scanner.next();
            if (!type.equals("P3")) throw new IllegalArgumentException("Invalid PPM format.");

            width = scanner.nextInt();
            height = scanner.nextInt();
            int maxValue = scanner.nextInt();
            pixels = new Pixels(width, height);

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int r = scanner.nextInt();
                    int g = scanner.nextInt();
                    int b = scanner.nextInt();
                    if (maxValue != 255) {
                        r = (r * 255) / maxValue;
                        g = (g * 255) / maxValue;
                        b = (b * 255) / maxValue;
                    }
                    pixels.setPixel(col, row, new Pixel(r, g, b));
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
            writer.println("P3");
            writer.println(width + " " + height);
            writer.println("255");
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    Pixel p = pixels.getPixel(col, row);
                    writer.print(p.getRed() + " " + p.getGreen() + " " + p.getBlue() + "  ");
                }
                writer.println();
            }
            System.out.println("Successfully saved " + path);
        } catch (Exception e) {
            System.out.println("Error while saving " + path);
        }
    }

    @Override public String getType() { return "ppm"; }
}
