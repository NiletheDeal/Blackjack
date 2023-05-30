package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Deck{
	
	Boolean playing = true;
	int cardsPlayed;
	ArrayList<String> playerHand = new ArrayList<String>();
	ArrayList<String> dealerHand = new ArrayList<String>();
	int playerValue = 0;
	int dealerValue = 0;
	Deck theDeck = new Deck();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void Start() {
		System.out.println("BLACKJACK RULES:");
		System.out.println("-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
		System.out.println("-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11");
		System.out.println("-The players' cards are added up for their total.");
		System.out.println("-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.");
		System.out.println("-Dealer “Hits” until they equal or exceed 17.");
		System.out.println("-The goal is to have a higher card total than the dealer without going over 21.");
		System.out.println("-If the player total equals the dealer total, it is a “Push” and the hand ends.");
		playerHand.add(theDeck.getCard());
		dealerHand.add(theDeck.getCard());
		playerHand.add(theDeck.getCard());
		dealerHand.add(theDeck.getCard());
	}
	public Main() {
		Start();
		Scanner sc = new Scanner(System.in);
		while (playing == true) {
			System.out.println("Dealers card: " + dealerHand.get(0));
			System.out.println("Your hand: " + playerHand);
			System.out.println("What do you want to do? (hit, stand, quit)");
			String input = sc.nextLine();
			if (input.equals("hit")) {
				playerHand.add(theDeck.getCard());
				getTotal(playerHand);
				//printHand();
				//checkWin();
			}
			else if(input.equals("stand")) {
				dealerPlay();
			}
			else if (input.equals("quit")) {
				System.exit(0);
			}else{
				System.out.println("Please enter a valid command of hit, stand, or quit");
			}
		}
	}
	//Check Win
	//Check Tie

	private void dealerPlay() {
		Boolean dealerTurn = true;
		while (dealerTurn == true) {
			dealerHand.add(theDeck.getCard());
			getTotal(dealerHand);
			if(dealerValue > 16) {
				//checkWin();
				dealerTurn = false;
			}
		}
	}
	private void getTotal(ArrayList<String> array) {//10, k, q J, A
		dealerValue = 0;
		playerValue = 0;
		for (int a = 0; a < array.size(); a++) {
			if(Character.isDigit(array.get(a).charAt(0)) && array.get(a).charAt(0) != '1') {
				if(array == playerHand) {
					playerValue += (int)(array.get(a).charAt(0));
				}
				if(array == dealerHand) {
					dealerValue += (int)(array.get(a).charAt(0));
				}
			}
			if (array.get(a).charAt(0) == 'k' || array.get(a).charAt(0) == 'q' || array.get(a).charAt(0) == 'j'|| array.get(a).charAt(0) == '1') {
				if (array == playerHand) {
					playerValue += 10;
				}
				if (array == dealerHand) {
					dealerValue += 10;
				}
			}
			if (array.get(a).charAt(0) == a) {//If you have ace checks whether it is worth 11 or 1 depending on busting on 11.
				if (array == dealerHand) {
					if ((dealerValue + 11) > 21) {
						dealerValue += 1;
					}
					else {
						dealerValue += 11;
					}
				}
				if (array == playerHand) {
					if ((playerValue + 11) > 21) {
						playerValue += 1;
					}
					else {
						playerValue += 11;
					}
				}
			}
		}
		
	}

}
