package campus_quest;
import java.util.*;

public class ConsolePlayer implements Player {
    private final Scanner scanner;

    public ConsolePlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void play(CampusQuest game) {
        while (!game.isOver()) {
            System.out.println("You are in " + game.getLocation());
            System.out.println("Available directions: " + game.getDirections());
            System.out.print("> ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("QUIT")) {
                System.out.println("You quit the game.");
                break;
            } else if (input.equals("HINT")) {
                game.hint();
            } else {
                game.move(input);
            }
        }
    }
}
