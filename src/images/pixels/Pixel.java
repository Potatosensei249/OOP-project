package images.pixels;

public class Pixel {
    private int r, g, b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getRed() { return r; }
    public void setRed(int r) { this.r = r; }

    public int getGreen() { return g; }
    public void setGreen(int g) { this.g = g; }

    public int getBlue() { return b; }
    public void setBlue(int b) { this.b = b; }

    public void invert() {
        this.r = 255 - r;
        this.g = 255 - g;
        this.b = 255 - b;
    }

    public int grayscale() {
        return (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
    }

    public void monochrome() {
        int gray = grayscale();
        int val = (gray > 127) ? 255 : 0;
        this.r = val;
        this.g = val;
        this.b = val;
    }
}
