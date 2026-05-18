import images.PBM;
import images.PGM;
import images.PPM;
import sessions.SessionManager;
import transformations.Grayscale;
import transformations.Monochrome;
import transformations.Negative;
import images.Image;
import sessions.Session;
import transformations.RotateLeft;
import transformations.RotateRight;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SessionManager manager = new SessionManager();

        Map<String, Runnable> commands = new HashMap<>();

        commands.put("grayscale", () -> {

            if (!hasSession(manager)) {
                return;
            }

            manager.getCurrentSession().addTransformation(new Grayscale()
                    );

            System.out.println("Grayscale transformation added.");
        });

        commands.put("monochrome", () -> {

            if (!hasSession(manager)) {
                return;
            }

            manager.getCurrentSession().addTransformation(new Monochrome());

            System.out.println("Monochrome transformation added.");
        });

        commands.put("negative", () -> {

            if (!hasSession(manager)) {
                return;
            }

            manager.getCurrentSession().addTransformation(new Negative());

            System.out.println("Negative transformation added.");
        });

        commands.put("undo", () -> {

            if (!hasSession(manager)) {
                return;
            }

            manager.getCurrentSession().undo();
        });

        commands.put("session-info", () -> {

            if (!hasSession(manager)) {
                return;
            }

            manager.getCurrentSession().sessionInfo();
        });


        commands.put("help", Main::printHelp);

        while (true) {

            System.out.print("> ");

            String input = scanner.nextLine();

            String[] tokens = input.split(" ");

            String command = tokens[0];

            if (command.equals("exit")) {

                System.out.println("Exiting program...");

                break;
            }

            else if (command.equals("load")) {

                for (int i = 1; i < tokens.length; i++) {

                    String fileName = tokens[i];

                    Image image = createImage(fileName);

                    if (image == null) {

                        System.out.println("Unsupported file type.");

                        continue;
                    }

                    image.load(fileName);

                    manager.createSession(image);
                }
            }

            else if (command.equals("add")) {

                if (!hasSession(manager)) {
                    continue;
                }

                for (int i = 1; i < tokens.length; i++) {

                    String fileName = tokens[i];

                    Image image = createImage(fileName);

                    if (image == null) {

                        System.out.println("Unsupported file type.");

                        continue;
                    }

                    image.load(fileName);

                    manager.getCurrentSession().addImage(image);
                }
            }

            else if (command.equals("rotate")) {

                if (!hasSession(manager)) {
                    continue;
                }

                if (tokens.length < 2) {

                    System.out.println("Missing direction.");

                    continue;
                }

                String direction = tokens[1];

                if (direction.equals("left")) {

                    manager.getCurrentSession().addTransformation(new RotateLeft());

                    System.out.println("Rotate left added.");
                }

                else if (direction.equals("right")) {

                    manager.getCurrentSession().addTransformation(new RotateRight());

                    System.out.println("Rotate right added.");
                }

                else {
                    System.out.println("Invalid direction.");
                }
            }

            else if (command.equals("save")) {

                if (!hasSession(manager)) {
                    continue;
                }

                Session current = manager.getCurrentSession();

                current.applyTransformations();

                for (Image image : current.getImages()) {

                    image.save(image.getFileName());
                }

                System.out.println("Session saved.");
            }

            else if (command.equals("switch")) {

                if (tokens.length < 2) {

                    System.out.println("Missing session ID.");

                    continue;
                }

                int id =
                        Integer.parseInt(tokens[1]);

                manager.switchSession(id);
            }

            else {

                Runnable action = commands.get(command);

                if (action != null) {

                    action.run();
                }

                else {

                    System.out.println("Unknown command.");
                }
            }
        }

        scanner.close();
    }

    /**
     * Създава изображение според зададения тип от потребителя
     * @param fileName
     * @return
     */
    private static Image createImage(
            String fileName) {

        if (fileName.endsWith(".ppm")) {

            return new PPM(fileName);
        }

        else if (
                fileName.endsWith(".pgm")) {

            return new PGM(fileName);
        }

        else if (fileName.endsWith(".pbm")) {

            return new PBM(fileName);
        }

        return null;
    }

    /**
     * Проверява дали има сесия
     * @param manager
     * @return
     */
    private static boolean hasSession(SessionManager manager) {

        if (manager.getCurrentSession() == null) {

            System.out.println("No active session.");

            return false;
        }

        return true;
    }

    /**
     * Извежда командите
     */
    private static void printHelp() {

        System.out.println("Supported commands:");

        System.out.println("load <files>");

        System.out.println("add <files>");

        System.out.println("grayscale");

        System.out.println("monochrome");

        System.out.println("negative");

        System.out.println("rotate left/right");

        System.out.println("undo");

        System.out.println("save");

        System.out.println("session-info");

        System.out.println("switch <id>");

        System.out.println("help");

        System.out.println("exit");
    }
}
