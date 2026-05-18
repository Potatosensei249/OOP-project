package transformations;

import images.Image;

public class Monochrome implements Transformation {

    /**
     * Прилага трансформация
     * @param image
     */
    @Override
    public void apply(Image image) {

        image.monochrome();
    }

    @Override
    public String getName() {

        return "monochrome";
    }
}
