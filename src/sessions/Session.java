package sessions;

import images.Image;
import transformations.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Представя сесия за обработка и нейните текущо заредени изображения.
 *
 * @author Иво Маринов
 */

public class Session {

    private int id;

    private List<Image> images;

    private List<Transformation> transformations;

    public Session(int id) {

        this.id = id;

        images = new ArrayList<>();

        transformations = new ArrayList<>();
    }

    public List<Image> getImages() {
        return images;
    }

    /**
     * Добавя изображение
     * @param image
     */
    public void addImage(Image image) {

        images.add(image);

        System.out.println(
                "Image \"" +
                        image.getFileName() +
                        "\" added"
        );
    }

    /**
     * Добавя трансформация, зададена от потребителя
     * @param transformation
     */
    public void addTransformation(
            Transformation transformation) {

        transformations.add(transformation);
    }

    /**
     * Премахва трансформация
     */
    public void undo() {

        if (transformations.isEmpty()) {

            System.out.println("Nothing to undo.");
            return;
        }

        Transformation removed =
                transformations.remove(
                        transformations.size() - 1
                );

        System.out.println(
                removed.getName() + " undone."
        );
    }

    /**
     * Окончателно трансформира изображението
     */
    public void applyTransformations() {

        for (Transformation transformation
                : transformations) {

            for (Image image : images) {

                transformation.apply(image);
            }
        }

        transformations.clear();
    }

    /**
     * Връща информация за текущата сесия
     */
    public void sessionInfo() {

        System.out.println(
                "sessions.Session ID: " + id
        );

        System.out.println("Images:");

        for (Image image : images) {

            System.out.println(
                    image.getFileName()
            );
        }

        System.out.println(
                "Pending transformations:"
        );

        for (Transformation t
                : transformations) {

            System.out.println(
                    t.getName()
            );
        }
    }

}