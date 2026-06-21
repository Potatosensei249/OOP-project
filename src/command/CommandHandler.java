package command;

import images.Image;
import images.PBM;
import images.PGM;
import images.PPM;
import sessions.Session;
import sessions.SessionManager;
import transformations.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private final SessionManager manager;
    private final Map<String, Runnable> basicCommands;

    public CommandHandler() {
        this.manager = new SessionManager();
        this.basicCommands = new HashMap<>();
        initBasicCommands();
    }

    private void initBasicCommands() {
        basicCommands.put("grayscale", () -> {
            if (!hasSession()) return;
            manager.getCurrentSession().addTransformation(new Grayscale());
            System.out.println("Grayscale transformation added.");
        });

        basicCommands.put("monochrome", () -> {
            if (!hasSession()) return;
            manager.getCurrentSession().addTransformation(new Monochrome());
            System.out.println("Monochrome transformation added.");
        });

        basicCommands.put("negative", () -> {
            if (!hasSession()) return;
            manager.getCurrentSession().addTransformation(new Negative());
            System.out.println("Negative transformation added.");
        });

        basicCommands.put("undo", () -> {
            if (!hasSession()) return;
            manager.getCurrentSession().undo();
        });

        basicCommands.put("session-info", () -> {
            if (!hasSession()) return;
            manager.getCurrentSession().sessionInfo();
        });

        basicCommands.put("help", this::printHelp);
    }

    /**
     * Обработва масив от аргументи, подадени директно през CLI
     */
    public void executeCliArguments(String[] args) {
        int i = 0;
        while (i < args.length) {
            String command = args[i];

            if (command.equals("load") || command.equals("add")) {
                int start = i + 1;
                while (i + 1 < args.length && !basicCommands.containsKey(args[i + 1])
                        && !args[i + 1].equals("load") && !args[i + 1].equals("add")
                        && !args[i + 1].equals("rotate") && !args[i + 1].equals("save")
                        && !args[i + 1].equals("switch") && !args[i + 1].equals("exit")) {
                    i++;
                }
                int end = i + 1;

                String[] tokens = new String[end - start + 1];
                tokens[0] = command;
                System.arraycopy(args, start, tokens, 1, end - start);

                processCommand(tokens);
            }
            else if (command.equals("rotate") || command.equals("switch")) {
                if (i + 1 < args.length) {
                    processCommand(new String[]{command, args[i + 1]});
                    i++;
                } else {
                    System.out.println("Missing argument for command: " + command);
                }
            }
            else {
                processCommand(new String[]{command});
            }
            i++;
        }
    }

    /**
     * Централно място за обработка на всяка команда
     * @return false ако командата е 'exit', иначе true
     */
    public boolean processCommand(String[] tokens) {
        String command = tokens[0];

        if (command.equals("exit")) {
            System.out.println("Exiting program...");
            return false;
        }

        switch (command) {
            case "load":
                executeLoad(tokens);
                break;
            case "add":
                executeAdd(tokens);
                break;
            case "rotate":
                executeRotate(tokens);
                break;
            case "save":
                executeSave();
                break;
            case "switch":
                executeSwitch(tokens);
                break;
            default:
                Runnable action = basicCommands.get(command);
                if (action != null) {
                    action.run();
                } else {
                    System.out.println("Unknown command: " + command);
                }
                break;
        }
        return true;
    }

    private void executeLoad(String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String fileName = tokens[i];
            Image image = createImage(fileName);
            if (image == null) {
                System.out.println("Unsupported file type: " + fileName);
                continue;
            }
            image.load(fileName);
            manager.createSession(image);
        }
    }

    private void executeAdd(String[] tokens) {
        if (!hasSession()) return;
        for (int i = 1; i < tokens.length; i++) {
            String fileName = tokens[i];
            Image image = createImage(fileName);
            if (image == null) {
                System.out.println("Unsupported file type: " + fileName);
                continue;
            }
            image.load(fileName);
            manager.getCurrentSession().addImage(image);
        }
    }

    private void executeRotate(String[] tokens) {
        if (!hasSession()) return;
        if (tokens.length < 2) {
            System.out.println("Missing direction.");
            return;
        }
        String direction = tokens[1];
        if (direction.equals("left")) {
            manager.getCurrentSession().addTransformation(new RotateLeft());
            System.out.println("Rotate left added.");
        } else if (direction.equals("right")) {
            manager.getCurrentSession().addTransformation(new RotateRight());
            System.out.println("Rotate right added.");
        } else {
            System.out.println("Invalid direction.");
        }
    }

    private void executeSave() {
        if (!hasSession()) return;
        Session current = manager.getCurrentSession();
        current.applyTransformations();
        for (Image image : current.getImages()) {
            image.save(image.getFileName());
        }
        System.out.println("Session saved.");
    }

    private void executeSwitch(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Missing session ID.");
            return;
        }
        try {
            int id = Integer.parseInt(tokens[1]);
            manager.switchSession(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid session ID format.");
        }
    }

    private Image createImage(String fileName) {
        if (fileName.endsWith(".ppm")) return new PPM(fileName);
        if (fileName.endsWith(".pgm")) return new PGM(fileName);
        if (fileName.endsWith(".pbm")) return new PBM(fileName);
        return null;
    }

    private boolean hasSession() {
        if (manager.getCurrentSession() == null) {
            System.out.println("No active session.");
            return false;
        }
        return true;
    }

    public void printHelp() {
        System.out.println("Supported commands:");
        System.out.println("  load <files>       - Load initial images and create session");
        System.out.println("  add <files>        - Add images to current session");
        System.out.println("  grayscale          - Add grayscale transformation");
        System.out.println("  monochrome         - Add monochrome transformation");
        System.out.println("  negative           - Add negative transformation");
        System.out.println("  rotate left/right  - Add rotation");
        System.out.println("  undo               - Undo last transformation");
        System.out.println("  save               - Apply transformations and save files");
        System.out.println("  session-info       - Show current session info");
        System.out.println("  switch <id>        - Switch to session ID");
        System.out.println("  help / exit");
    }
}