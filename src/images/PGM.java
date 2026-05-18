package images;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PGM extends AbstractImage{
    private int maxValue;

    public PGM(String fileName) {
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

            if (!type.equals("P2")) {

                throw new IllegalArgumentException(
                        "Invalid PGM format."
                );
            }

            width = scanner.nextInt();
            height = scanner.nextInt();

            int maxValue =
                    scanner.nextInt();

            pixels =
                    new int[height][width][3];

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    int gray =
                            scanner.nextInt();

                    pixels[row][col][0] = gray;
                    pixels[row][col][1] = gray;
                    pixels[row][col][2] = gray;
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

            writer.println("P2");

            writer.println(
                    width + " " + height
            );

            writer.println("255");

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    writer.print(
                            pixels[row][col][0] + " "
                    );
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
        return "pgm";
    }

}
