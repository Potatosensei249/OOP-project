package images;

import images.pixels.Pixels;

public interface Image {
    void load(String path);
    void save(String path);

    String getType();
    String getFileName();
    void setFileName(String fileName);

    int getWidth();
    int getHeight();
    Pixels getPixels();
    void setPixels(Pixels pixels);

    void grayscale();
    void monochrome();
    void negative();
    void rotateLeft();
    void rotateRight();
}
