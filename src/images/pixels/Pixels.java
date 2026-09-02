package images.pixels;

/**
 * Съхранява и управлява колекция от пиксели, принадлежащи към изображение.
 *
 * @author Иво Маринов
 */

public class Pixels {
    private final int width;
    private final int height;
    private final Pixel[] matrix;

    public Pixels(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Pixel[width * height];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Pixel getPixel(int x, int y) {
        validateCoordinates(x, y);
        return matrix[y * width + x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        validateCoordinates(x, y);
        matrix[y * width + x] = pixel;
    }

    private void validateCoordinates(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates (" + x + ", " + y + ") are out of bounds.");
        }
    }
}
