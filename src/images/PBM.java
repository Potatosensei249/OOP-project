package images;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PBM extends AbstractImage{
    public PBM(String fileName) {
        super(fileName);
    }

    /**
     * Зарежда изображение
     * @param path
     */
    @Override
    public void load(String path) {

        try {

            Scanner scanner =
                    new Scanner(
                            new File(path)
                    );

            String type = scanner.next();

            if (!type.equals("P1")) {

                throw new IllegalArgumentException(
                        "Invalid PBM format."
                );
            }

            width = scanner.nextInt();
            height = scanner.nextInt();

            pixels =
                    new int[height][width][3];

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    int value =
                            scanner.nextInt();

                    int color =
                            value == 1 ? 255 : 0;

                    pixels[row][col][0] = color;
                    pixels[row][col][1] = color;
                    pixels[row][col][2] = color;
                }
            }

            scanner.close();

            System.out.println(
                    "Successfully loaded "
                            + path
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while opening "
                            + path
            );

            e.printStackTrace();
        }
    }

    /**
     * Запазва изображение
     * @param path
     */
    @Override
    public void save(String path) {

        try (
                PrintWriter writer =
                        new PrintWriter(
                                new File(path)
                        )
        ) {

            writer.println("P1");

            writer.println(
                    width + " " + height
            );

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    int value =
                            pixels[row][col][0] > 127
                                    ? 1
                                    : 0;

                    writer.print(value + " ");
                }

                writer.println();
            }

            System.out.println(
                    "Successfully saved "
                            + path
            );

            System.out.println(
                    new File(path)
                            .getAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while saving "
                            + path
            );

            e.printStackTrace();
        }
    }

    @Override
    public String getType() {
        return "pbm";
    }

}
