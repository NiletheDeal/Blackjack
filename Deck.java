//This Java file finds the deck text file, shuffles the deck, and deals a card if asked
//Neel Madala, Lukas Martinek
//6/7/2023
package BlackJack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {
	
	public ArrayList<String> deck = new ArrayList<String>();
	int cardsPlayed = 0;
	public Deck() {//Get deck and shuffle deck
		getFile();
		shuffleDeck();
	}
	
	public void getFile() {//Finds the deck file
		try {
			Scanner fileScanner = new Scanner (new File("deck.txt"));
			while (fileScanner.hasNextLine() == true) {
				deck.add(fileScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void shuffleDeck() {//shuffles the deck file
		Collections.shuffle(deck);
	}
	public String getCard() {//gets a card
		if (cardsPlayed >= 51) {//if you play through entire deck then shuffle and go again
			shuffleDeck();
			cardsPlayed = 0;
		}
		//Deals card and adds 1 to cardsPlayed
		String card = deck.get(cardsPlayed);
		cardsPlayed++;
		return card;
	}
}

