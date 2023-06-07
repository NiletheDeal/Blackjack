//This Program runs a simple console version of blackjack that allows the player to hit and stand against the dealer. 
//The deck is shuffled and is random each time you play, but runs through the entire deck before shuffling again
//It checks for BlackJacks and wins while keeping track of the number of wins of both the computer and player
//Neel Madala, Lukas Martinek
//6/7/2023
package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Deck{
	
	Boolean stillPlaying = true;
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
	Deck theDeck = new Deck();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void Start() {//Prints out rules
		System.out.println("BLACKJACK RULES:");
		System.out.println("-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
		System.out.println("-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11");
		System.out.println("-The players' cards are added up for their total.");
		System.out.println("-Players “hit” to gain another card from the deck. Players “stand” to keep their current card total.");
		System.out.println("-Dealer “hit” until they equal or exceed 17.");
		System.out.println("-The goal is to have a higher card total than the dealer without going over 21.");
		System.out.println("-If the player total equals the dealer total, it is a “Push” and the hand ends.");
	}
	public Main() {//Plays the game and asks for commands
		Start();
		Scanner sc = new Scanner(System.in);
		while (stillPlaying == true) {//Goes back and plays a new game
			playing = true;
			playerHand.add(theDeck.getCard());
			dealerHand.add(theDeck.getCard());
			playerHand.add(theDeck.getCard());
			dealerHand.add(theDeck.getCard());
				System.out.println("Dealers card: " + toWords(dealerHand));
				dealerCards.clear();
				wordCards.clear();
				getTotal(dealerHand);
				System.out.println("Your hand: " + toWords(playerHand));
				wordCards.clear();
				getTotal(playerHand);
				if (checkBlackjack() == true) {//If the player or dealer gets blackjack the round is over and asks if they want to play again
					playing = false;
				}
			while (playing == true) {//Keeps on asking for commands until there is a winner and ends the game
				System.out.println("What do you want to do? (hit, stand, quit)");
				String input = sc.nextLine();
				if (input.equals("hit")) {//gives player a card
					playerHand.add(theDeck.getCard());
					System.out.println("Dealers card: " + toWords(dealerHand));
					dealerCards.clear();
					wordCards.clear();
					System.out.println("Your hand: " + toWords(playerHand));
					wordCards.clear();
					getTotal(playerHand);
					playerTurn = true;
					checkWin();
				}
				else if(input.equals("stand")) {//Shifts turn from player to Dealer
					getTotal(dealerHand);
					playerTurn = false;
					dealerPlay();
					checkWin();
				}
				else if (input.equals("quit")) {//quits game
					System.exit(0);
				}else{
					System.out.println("Pleasse enter a valid command of hit, stand, or quit");
				}
			}
			printWins();
			System.out.println("Do you want to play again? Type yes or y to continue playing, anything else to stop");
			String yesno = sc.nextLine();
				if (yesno.equals("yes") || yesno.equals("y")) {//checks if they want to play again and also resets hands
					stillPlaying = true;
					playerValue = 0;
					dealerValue = 0;
					dealerHand.clear();
					playerHand.clear();
					dealerCards.clear();
					wordCards.clear();
					playerTurn = true;
					
					
				}
				else {//closes the game
					stillPlaying = false;
					System.out.println("Thank you for Playing!!");
					System.exit(0);
				}
			}
	}
	

	private void dealerPlay() {//After player turn, dealer draws until its hand goes over 16
		Boolean dealerTurn = true;
		System.out.println("Dealers card: " + toWords(dealerHand));
		wordCards.clear();
		while (dealerTurn == true) {
			if (dealerValue <= 16) {
			dealerHand.add(theDeck.getCard());
			System.out.println("Dealers card: " + toWords(dealerHand));
			wordCards.clear();
			System.out.println("Your hand: " + toWords(playerHand));
			wordCards.clear();
			getTotal(dealerHand);
			}
			if(dealerValue > 16) {
				dealerTurn = false;
			}
		}
	}
	private void getTotal(ArrayList<String> array) {//Adds the singular card values to get the total value of the hand. This takes in either player hand or dealer hand and changes Value of that hand
		int aceNumber = 0;
		if(array == playerHand) {
			playerValue = 0;
		}
		if(array == dealerHand) {
			dealerValue = 0;
		}
		for (int a = 0; a < array.size(); a++) {
			if(Character.isDigit(array.get(a).charAt(0)) && array.get(a).charAt(0) != '1') {
				if(array == playerHand) {
					playerValue += (int)((array.get(a).charAt(0)) - '0');
				}
				if(array == dealerHand) {
					dealerValue += (int)((array.get(a).charAt(0)) - '0');
				}
			}
			if (array.get(a).charAt(0) == 'k' || array.get(a).charAt(0) == 'q' || array.get(a).charAt(0) == 'j'|| array.get(a).charAt(0) == '1') {//checks if card is King, Jack, Queen or ten, and adds value
				//the first character of a 10 card is 1 since an ace would start with a
				if (array == playerHand) {
					playerValue += 10;
				}
				if (array == dealerHand) {
					dealerValue += 10;
				}
			}
			if (array.get(a).charAt(0) == 'a') {//If you have ace checks whether it is worth 11 or 1 depending on busting on 11.
				aceNumber++;
			}
		}
		for (int i = 0; i < aceNumber; i++) {
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
	private boolean checkBlackjack() {//checks if starting hands are blackjacks (worth 21)
		if (playerValue == 21 && dealerValue < playerValue) {
			playerWins++;
			System.out.println("The Player got BlackJack");
			return true;
			//reset
		}
		else if (dealerValue == 21 && dealerValue > playerValue) {
			dealerWins++;
			System.out.println("The Dealer got BlackJack");
			return true;
		}
		else if (dealerValue == 21 && playerValue == 21) {
			System.out.println("Both the Dealer and Player got Blackjack and its a Tie!");
			return true;
		}
		return false;
	}
	private void checkWin() {
		if (playerValue > 21) {//if player busts
			dealerWins++;
			System.out.println("The Dealer Won!");
			playing = false;
		}
		else if (dealerValue > 21) {//if dealer busts and player hasn't
			playerWins++;
			System.out.println("The Player Won!");
			playing = false;
		}
		else if (playerValue > dealerValue && playerTurn == false) {//if player is above dealer and neither has busted 
			playerWins++;
			System.out.println("The Player Won!!");
			playing = false;
		}
		else if (playerValue < dealerValue && playerTurn == false) {//if dealer is above player and neither has busted
			dealerWins++;
			System.out.println("The Dealer Won!!");
			playing = false;
		}
		else if (dealerValue == playerValue && playerTurn == false) {//if the dealer and player end with same value
			System.out.println("It was a tie!");
			playing = false;
		}
	}
	private void printWins() {//Prints the wins of the dealer and Player
		System.out.println("Dealer Wins: " + dealerWins);
		System.out.println("Player Wins: " + playerWins);
	}
	
	public ArrayList<String> toWords(ArrayList<String> Cards) {//transforms the cards from short from to the Long form of (2S -> 2 of Spades)
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
		if (Cards == dealerHand && playerTurn ==true) {//This allows for the dealer hand to only reveal 1 card to the player
			dealerCards.add(wordCards.get(0));
			return dealerCards;
		} else {
			return wordCards;
		}
	}

}
