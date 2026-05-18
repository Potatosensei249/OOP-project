package images;

public abstract class AbstractImage
        implements Image {

    protected String fileName;

    protected int width;
    protected int height;

    protected int[][][] pixels;

    public AbstractImage(String fileName) {

        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int[][][] getPixels() {
        return pixels;
    }

    @Override
    public void setPixels(int[][][] pixels) {
        this.pixels = pixels;
    }

    @Override
    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    /**
     * Прави изображението сиво
     */
    @Override
    public void grayscale() {

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                int r = pixels[row][col][0];
                int g = pixels[row][col][1];
                int b = pixels[row][col][2];

                int gray = (r + g + b) / 3;

                pixels[row][col][0] = gray;
                pixels[row][col][1] = gray;
                pixels[row][col][2] = gray;
            }
        }
    }

    /**
     * Прави изображението черно-бяло
     */
    @Override
    public void monochrome() {

        grayscale();

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                int value = pixels[row][col][0];

                int mono = value > 127 ? 255 : 0;

                pixels[row][col][0] = mono;
                pixels[row][col][1] = mono;
                pixels[row][col][2] = mono;
            }
        }
    }

    /**
     * Сменя цветовете на изображението
     */
    @Override
    public void negative() {

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                pixels[row][col][0] =
                        255 - pixels[row][col][0];

                pixels[row][col][1] =
                        255 - pixels[row][col][1];

                pixels[row][col][2] =
                        255 - pixels[row][col][2];
            }
        }
    }

    /**
     * Завърта изображението наляво
     */
    @Override
    public void rotateLeft() {

        int[][][] rotated =
                new int[width][height][3];

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                rotated[width - col - 1][row]
                        = pixels[row][col];
            }
        }

        pixels = rotated;

        int temp = width;
        width = height;
        height = temp;
    }

    /**
     * Завърта изображението надясно
     */
    @Override
    public void rotateRight() {

        int[][][] rotated =
                new int[width][height][3];

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                rotated[col][height - row - 1]
                        = pixels[row][col];
            }
        }

        pixels = rotated;

        int temp = width;
        width = height;
        height = temp;
    }
}