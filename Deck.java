package BlackJack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {
	
	public ArrayList<String> deck = new ArrayList<String>();
	int cardsPlayed = 0;
	public Deck() {
		getFile();
		shuffleDeck();
	}
	
	public void getFile() {
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
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	public String getCard() {
		if (cardsPlayed >= 51) {
			shuffleDeck();
			cardsPlayed = 0;
		}
		String card = deck.get(cardsPlayed);
		cardsPlayed++;
		return card;
	}
}

