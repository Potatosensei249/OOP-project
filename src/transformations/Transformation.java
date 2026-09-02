package transformations;

import images.Image;

/**
 * Определя общия интерфейс за трансформации на изображения.
 *
 * @author Иво Маринов
 */

public interface Transformation {

    void apply(Image image);

    String getName();
}
