package images;

public interface Image {

    void load(String path);

    void save(String path);

    void setFileName(String fileName);

    String getType();

    String getFileName();

    int getWidth();

    int getHeight();

    int[][][] getPixels();

    void setPixels(int[][][] pixels);

    void grayscale();

    void monochrome();

    void negative();

    void rotateLeft();

    void rotateRight();
}
