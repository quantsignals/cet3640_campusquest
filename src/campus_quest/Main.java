package campus_quest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Main {
	

	public static void main(String[] args) {
		 
		        CampusQuest game = new CampusQuest();

              // Single console player start
		        	Player player = new ConsolePlayer(new java.util.Scanner(System.in));
		        	game.resetState();
		        	player.play(game);  
             // Single console player end
/* 		    
              // For using RandomPlayer remove the single player code
		        	// start a loop
		        	Player player = new RandomPlayer();
		        	game.resetState();
		        	player.play(game);  
		        	// check game outcome
		        	System.out.println(game.getOutcome());
		        // end a loop 
		        // report number of winning and loosing games	
*/
	}
}
