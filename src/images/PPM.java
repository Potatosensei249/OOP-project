package images;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PPM extends AbstractImage {

    private int maxValue;

    public PPM(String fileName) {
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
                    new Scanner(new File(path));

            String type = scanner.next();

            if (!type.equals("P3")) {

                throw new IllegalArgumentException(
                        "Invalid PPM format."
                );
            }

            width = scanner.nextInt();
            height = scanner.nextInt();

            int maxValue = scanner.nextInt();

            pixels = new int[height][width][3];

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    pixels[row][col][0] =
                            scanner.nextInt();

                    pixels[row][col][1] =
                            scanner.nextInt();

                    pixels[row][col][2] =
                            scanner.nextInt();
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

            writer.println("P3");

            writer.println(
                    width + " " + height
            );

            writer.println("255");

            for (int row = 0; row < height; row++) {

                for (int col = 0; col < width; col++) {

                    writer.print(
                            pixels[row][col][0] + " "
                    );

                    writer.print(
                            pixels[row][col][1] + " "
                    );

                    writer.print(
                            pixels[row][col][2] + " "
                    );
                }

                writer.println();
            }

            System.out.println(
                    "Successfully saved "
                            + path
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while saving "
                            + path
            );

            e.printStackTrace();
        }
        System.out.println(
                new File(path).getAbsolutePath()
        );
    }

    @Override
    public String getType() {
        return "ppm";
    }

}
