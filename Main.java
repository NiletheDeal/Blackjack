package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Deck{
	
	Boolean playing = true;
	Boolean playerTurn = true;
	int cardsPlayed;
	ArrayList<String> playerHand = new ArrayList<String>();
	ArrayList<String> dealerHand = new ArrayList<String>();
	ArrayList<String> wordCards = new ArrayList<String>();
	ArrayList<String> dealerCards = new ArrayList<String>();
	int playerValue = 0;
	int dealerValue = 0;
	int playerWins = 0;
	int dealerWins = 0;
	boolean blackjack = false;
	Deck theDeck = new Deck();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Main();
	}
	
	private void Start() {
		System.out.println("Welcome to Blackjack!");
		System.out.println("");
		System.out.println("  BLACKJACK RULES: ");
		System.out.println("	-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
		System.out.println("	-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
		System.out.println("	-The players cards are added up for their total.");
		System.out.println("	-Players “Hit” to gain another card from the deck. Players “Stand” to keep their current card total.");
		System.out.println("	-Dealer “Hits” until they equal or exceed 17.");
		System.out.println("	-The goal is to have a higher card total than the dealer without going over 21.");
		System.out.println("	-If the player total equals the dealer total, it is a “Push” and the hand ends."); 
		System.out.println("");
		System.out.println("");
	}
	
	private void Deal() {
		playerHand.add(theDeck.getCard());
		dealerHand.add(theDeck.getCard());
		playerHand.add(theDeck.getCard());
		dealerHand.add(theDeck.getCard());
	}
	
	public Main() {
		Start();
		Deal();
		while (playing == true) {
			System.out.println("Dealers card: " + toWords(dealerHand));
			dealerCards.clear();
			wordCards.clear();
			getTotal(dealerHand);
			System.out.println(dealerValue);
			System.out.println("Your hand: " + toWords(playerHand));
			wordCards.clear();
			getTotal(playerHand);
			System.out.println(playerValue);
			checkBlackjack();
			if (blackjack == true) {
				playAgain();
			}
			System.out.println("What do you want to do? (hit, stand, quit)");
			String input = sc.nextLine();
			if (input.equals("hit")) {
				playerHand.add(theDeck.getCard());
				getTotal(playerHand);
				if (playerValue > 21) {
					playerTurn = false;
					System.out.println("Dealers card: " + toWords(dealerHand));
					System.out.println(dealerValue);
					dealerCards.clear();
					wordCards.clear();
					System.out.println("Your hand: " + toWords(playerHand));
					getTotal(playerHand);
					System.out.println(playerValue);
					checkWin();
					playAgain();
				}
			}
			else if(input.equals("stand")) {
				playerTurn = false;
				dealerPlay();
				System.out.println("Dealers card: " + toWords(dealerHand));
				System.out.println(dealerValue);
				dealerCards.clear();
				wordCards.clear();
				System.out.println("Your hand: " + toWords(playerHand));
				getTotal(playerHand);
				System.out.println(playerValue);
				checkWin();
				playAgain();
			}
			else if (input.equals("quit")) {
				System.exit(0);
			} else {
				System.out.println("Please enter a valid command of hit, stand, or quit");
			}
		}
	}

	private void dealerPlay() {
		Boolean dealerTurn = true;
		getTotal(dealerHand);
		while (dealerTurn == true) {
			if (dealerValue < 17) {
			dealerHand.add(theDeck.getCard());
			getTotal(dealerHand);
			} else {
				dealerTurn = false;
			}
		}
	}
	
	private void getTotal(ArrayList<String> array) {
		dealerValue = 0;
		playerValue = 0;
		for (int a = 0; a < array.size(); a++) {
			if(Character.isDigit(array.get(a).charAt(0)) && array.get(a).charAt(0) != '1') {
				if(array == playerHand) {
					playerValue += (int)((array.get(a).charAt(0)) - '0');
				}
				if(array == dealerHand) {
					dealerValue += (int)((array.get(a).charAt(0)) - '0');
				}
			}
			else if (array.get(a).charAt(0) == 'k' || array.get(a).charAt(0) == 'q' || array.get(a).charAt(0) == 'j'|| array.get(a).charAt(0) == '1') {
				if (array == playerHand) {
					playerValue += 10;
				}
				if (array == dealerHand) {
					dealerValue += 10;
				}
			}
			else if (array.get(a).charAt(0) == 'a') {//If you have ace checks whether it is worth 11 or 1 depending on busting on 11.
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
	
	private void checkBlackjack() {
		getTotal(playerHand);
		getTotal(dealerHand);
		if (playerValue == 21 && dealerValue < playerValue) {
			playerWins++;
			blackjack = true;
			System.out.println("The Player got BlackJack");

		}
		else if (dealerValue == 21 && dealerValue > playerValue) {
			dealerWins++;
			blackjack = true;
			System.out.println("The Dealer got BlackJack");

		}
		else if (dealerValue == 21 && playerValue == 21) {
			blackjack = true;
			System.out.println("Both the Dealer and Player got Blackjack and its a Tie!");
		}
	}
	
	private void checkWin() {
		getTotal(playerHand);
		getTotal(dealerHand);
		if (playerValue > 21) {//if player busts
			dealerWins++;
			System.out.println("The Dealer Won!");
		}
		else if (playerValue == dealerValue) {//if dealer and player are equal
			System.out.println("It is a Push!");
		}
		else if (playerValue < dealerValue && dealerValue <= 21) {//if player is below dealer and neither has busted 
			dealerWins++;
			System.out.println("The Dealer Won!");
		}
		else if (playerValue == 21) {//if player got blackjack
			playerWins++;
			System.out.println("The Player Won With Blackjack!");
		}
		else {//if nothing above happened then player won
			playerWins++;
			System.out.println("The Player Won!");
		}
	}
	
	private void printWins() {
		System.out.println("Dealer Wins: " + dealerWins);
		System.out.println("Player Wins: " + playerWins);
	}
	
	private void reset() {
		playerHand.clear();
		dealerHand.clear();
		playerValue = 0;
		dealerValue = 0;
		dealerCards.clear();
		wordCards.clear();
		Deal();
	}
	
	private void playAgain() {
		//printWins();
		System.out.println("Do you want to play again? (y or n)");
		String again = sc.nextLine();
			if (again.equals("n")) {
				playing = false;
			}
		reset();
	}
	
	public ArrayList toWords(ArrayList<String> Cards) {
		for (int a = 0; a < Cards.size(); a++) {
			String card = (String) Cards.get(a);
			//suite is char at 2, either S, C, D, H
			String Suite = String.valueOf(card.charAt(1));
			if (Suite.equals("0")) {
				Suite =  String.valueOf(card.charAt(2));
			}
			if (Suite.equals("S")) {
				Suite = "Spades";
			}
			else if (Suite.equals("C")) {
				Suite = "Clubs";
			}
			else if (Suite.equals("D")) {
				Suite = "Diamonds";
			}
			else if (Suite.equals("H")) {
				Suite = "Hearts";
			}
			//value is int at 1, from 2 - 10
			String Value = String.valueOf(card.charAt(0));
			if (Value.equals("1")) {
				Value = "10";
			} 
			else if (Value.equals("a")) {
				Value = "Ace";
			}
			else if (Value.equals("j")) {
				Value = "Jack";
			}
			else if (Value.equals("k")) {
				Value = "King";
			}
			else if (Value.equals("q")) {
				Value = "Queen";
			}
			card = Value + " of " + Suite;
			wordCards.add(card);
		}
		//if (Cards == dealerHand && playerTurn ==true) {
			//dealerCards.add(wordCards.get(0));
			//return dealerCards;
		//} else {
			return wordCards;
		//}
	}

}
