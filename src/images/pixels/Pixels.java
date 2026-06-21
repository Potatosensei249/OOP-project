package images.pixels;

public class Pixels {
    private final int width;
    private final int height;
    private final Pixel[][] matrix;

    public Pixels(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Pixel[height][width];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Pixel getPixel(int x, int y) {
        return matrix[y][x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        matrix[y][x] = pixel;
    }
}
