package images;

import images.pixels.Pixel;
import images.pixels.Pixels;

/**
 * Предоставя общо състояние и функционалност за реализациите
 * на изображения.
 *
 * @author Иво Маринов
 */

public abstract class AbstractImage implements Image {
    protected int width;
    protected int height;
    protected Pixels pixels;
    protected String fileName;

    public AbstractImage(String fileName) {
        this.fileName = fileName;
    }

    @Override public String getFileName() { return fileName; }
    @Override public void setFileName(String fileName) { this.fileName = fileName; }
    @Override public int getWidth() { return width; }
    @Override public int getHeight() { return height; }
    @Override public Pixels getPixels() { return pixels; }
    @Override public void setPixels(Pixels pixels) {
        this.pixels = pixels;
        this.width = pixels.getWidth();
        this.height = pixels.getHeight();
    }

    @Override
    public void negative() {
        if (pixels == null) return;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels.getPixel(x, y).invert();
            }
        }
    }

    @Override
    public void grayscale() {
        if (pixels == null) return;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel p = pixels.getPixel(x, y);
                int gray = p.grayscale();
                p.setRed(gray); p.setGreen(gray); p.setBlue(gray);
            }
        }
    }

    @Override
    public void monochrome() {
        if (pixels == null) return;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels.getPixel(x, y).monochrome();
            }
        }
    }

    @Override
    public void rotateLeft() {
        if (pixels == null) return;
        Pixels rotated = new Pixels(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotated.setPixel(y, width - 1 - x, pixels.getPixel(x, y));
            }
        }
        updateDimensions(rotated);
    }

    @Override
    public void rotateRight() {
        if (pixels == null) return;
        Pixels rotated = new Pixels(height, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotated.setPixel(height - 1 - y, x, pixels.getPixel(x, y));
            }
        }
        updateDimensions(rotated);
    }

    private void updateDimensions(Pixels newPixels) {
        this.pixels = newPixels;
        int temp = width;
        width = height;
        height = temp;
    }
}