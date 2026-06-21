package transformations;

import images.Image;

public class Negative implements Transformation {

    /**
     * Прилага трансформация
     * @param image
     */
    @Override
    public void apply(Image image) {

        image.negative();
    }

    @Override
    public String getName() {

        return "negative";
    }
}
