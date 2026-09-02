package transformations;

import images.Image;

/**
 * Преобразува изображение в монохромно изображение.
 *
 * @author Иво Маринов
 */

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
