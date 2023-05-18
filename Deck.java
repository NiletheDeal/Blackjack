package BlackJack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {
	
	ArrayList<String> deck = new ArrayList<String>();
	public Deck() {
		getFile();
		shuffleDeck();
		System.out.println(deck);
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
	public static void main(String[]args) {
		new Deck();
	}
}

