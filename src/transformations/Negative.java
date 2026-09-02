package transformations;

import images.Image;

/**
 * Преобразува изображение чрез обръщане на стойностите на неговите пиксели.
 *
 * @author Иво Маринов
 */

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
