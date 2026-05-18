package transformations;

import images.Image;

public class Grayscale implements Transformation {

    /**
     * Прилага трансформация
     * @param image
     */
    @Override
    public void apply(Image image) {

        image.grayscale();
    }

    @Override
    public String getName() {

        return "grayscale";
    }
}
