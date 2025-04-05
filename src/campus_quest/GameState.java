package campus_quest;
import java.util.*;

public class GameState {
    private String currentRoom;
    private final List<String> items;
    private int steps;
    private String outcome;

    public GameState(String startRoom) {
        this.currentRoom = startRoom;
        this.items = new ArrayList<>();
        this.steps = 0;
        this.outcome = "FAIL";
    }

    public String getOutcome() {
    	return outcome; // PASS or FAIL
    }

    public void setOutcome(String outcome) {
    	this.outcome = outcome; // PASS or FAIL
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String room) {
        this.currentRoom = room;
    }

    public List<String> getItems() {
        return items;
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public int getSteps() {
        return steps;
    }

    public void incrementSteps() {
        this.steps++;
    }

    public void decrementSteps() {
        this.steps = Math.max(0, this.steps-1);
    }
}
