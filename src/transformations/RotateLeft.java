package transformations;

import images.Image;

public class RotateLeft implements Transformation {

    /**
     * Прилага трансформация
     * @param image
     */
    @Override
    public void apply(Image image) {

        image.rotateLeft();
    }

    @Override
    public String getName() {

        return "rotate left";
    }
}
