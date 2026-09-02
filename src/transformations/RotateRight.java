package transformations;

import images.Image;

/**
 * Завърта изображението на 90 градуса наляво.
 *
 * @author Иво Маринов
 */

public class RotateRight implements Transformation {

    /**
     * Прилага трансформация
     * @param image
     */
    @Override
    public void apply(Image image) {

        image.rotateRight();
    }

    @Override
    public String getName() {

        return "rotate right";
    }
}
