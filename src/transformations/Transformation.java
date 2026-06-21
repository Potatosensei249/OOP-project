package transformations;

import images.Image;

public interface Transformation {

    void apply(Image image);

    String getName();
}
