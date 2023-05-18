package BlackJack;

import java.util.Scanner;

public class Main {
	
	Boolean playing = true;
	int cardsPlayed;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Play
	public void Game() {
		while (playing == true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if (input == "hit") {
				
			}
			if (input == "stand") {
				
			}
			else {
				System.out.println("Please enter a valid command of hit, stand, or quit");
			}
		}
	}
	//Hit
	//Stand
	//Check Win
	//Check Tie

}
