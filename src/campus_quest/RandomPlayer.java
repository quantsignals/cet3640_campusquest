package campus_quest;
import java.util.*;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public void play(CampusQuest game) {
        while (!game.isOver()) {
            List<String> directions = game.getDirections();
            if (directions.isEmpty()) break;
            String move = directions.get(random.nextInt(directions.size()));
            System.out.println("Bot moves: " + move);
            game.move(move);
        }
    }
}
