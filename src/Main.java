import command.CommandHandler;
import java.util.Scanner;

/**
 * Съдържа началната точка на приложението.
 *
 * @author Иво Маринов
 */

public class Main {

    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();

        if (args.length > 0) {
            handler.executeCliArguments(args);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Interactive session started. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");

            if (!handler.processCommand(tokens)) {
                break;
            }
        }

        scanner.close();
    }
}
