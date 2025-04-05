package campus_quest;
import java.util.*;

public class CampusQuest {
    private static final Set<String> REQUIRED_ITEMS = Set.of("notebook", "laptop", "coffee");
    private static final int STEP_LIMIT = 10;

    public final Campus campus;
    private GameState state;
    private boolean gameOver = false;

    public GameState getState() {
        return state;
    }

    public String getOutcome() {
        return state.getOutcome();
    }

    // initialize start of a new game
    public CampusQuest() {
        this.campus = new Campus();
        this.state = new GameState("Dorm Room");
    }

    public void resetState() {
        this.state = new GameState("Dorm Room");
        this.gameOver = false;
        this.state.setOutcome("FAIL");
    }

    public String getCurrentRoom() {
        return state.getCurrentRoom();
    }

    // returns a list of all possible directions that can be taken from the current room
    public List<String> getDirections() {
        Map<String, String> directionsToRoomsMap = campus.getDirectionsToRoomsMap(getCurrentRoom());
        List<String> directions = new ArrayList<>();
        for (String key : directionsToRoomsMap.keySet()) {
        	// the map also hold the item if one is in the room, don't add it to directions
            if (!key.equals("item")) directions.add(key);
        }
        return directions.reversed();
    }

    // move to the next room
    public void move(String direction) {
        if (gameOver) return;

        String current_room = getCurrentRoom();
        Map<String, String> directionsToRoomsMap = campus.getDirectionsToRoomsMap(getCurrentRoom());
        
        // check if it is a valid move
        if (!directionsToRoomsMap.containsKey(direction)) {
            System.out.println("Invalid direction.");
            return;
        }
        
        // move and update the current room
        current_room = directionsToRoomsMap.get(direction);
        state.setCurrentRoom(current_room);
        state.incrementSteps();

        // update the map to one that corresponds to the new location
        directionsToRoomsMap = campus.getDirectionsToRoomsMap(current_room);
        
        // check if the new location has an item and collect it 
        if (directionsToRoomsMap.containsKey("item")) {
            String item = directionsToRoomsMap.get("item");
            if (!state.getItems().contains(item)) {
                state.addItem(item);
                System.out.println("You found a " + item + "!");
            }
        }

        // apply different game play scenarios according to the new location
        if (current_room.equals("Admin Office")) {
            System.out.println("Oh no! You got stuck in the Admin Office. Game over.");
            gameOver = true;
            this.state.setOutcome("FAIL");
            
        } else if (current_room.equals("Student Center")) {
            System.out.println("You spent too long chatting. You lost 1 step.");
            state.incrementSteps();

        } else if (current_room.equals("Professor's Office")) {
            System.out.println("You attended office hours. You gain 1 step.");
            state.decrementSteps();            
            
        } else if (current_room.equals("Classroom") && state.getItems().containsAll(REQUIRED_ITEMS)) {
        	System.out.println("You win!");
        	System.out.println("You gave your presentation! You passed!");
            System.out.println("Items: " + state.getItems());
            System.out.println("Steps: " + state.getSteps());
            gameOver = true;
            this.state.setOutcome("PASS");
            
        } else if (state.getSteps() >= STEP_LIMIT) {
            System.out.println("You ran out of time and missed the presentation.");
            gameOver = true;
            this.state.setOutcome("FAIL");
        }

        return;
    }

    public boolean isOver() {
        return gameOver;
    }

    public void hint() {
        System.out.println("Hint:");
        String loc = state.getCurrentRoom();
        System.out.println("Your are at: "+loc);
        // ADD CODE HERE TO PRINT OUT ThE ADJECENT DIRECTIONS AND ROOM NAMES
    }
}
