package Blackjack;

import java.util.Scanner;

public class Debugger extends Gadget {

	Deck d1; 
	
	public Debugger() {
		super(0);
		d1 = new Deck();
		d1.clearDeck();
		
	}
	
	/*
	 * Prints the instructions on how to use the debugger. Each gadget has different commands, so it works differently. 
	 * 
	 * */
	
	public void printInstructions() {
		System.out.println("- INSTRUCTIONS -");
		System.out.println();
		System.out.println("Type RE to rearrange the deck in your next game.");
		System.out.println("Type $ to change your money anywhere from 1 to 1,000,000,000.");
		if (this.status) {
			System.out.println("Type OFF to turn the Debugger off.");
		} else {
			System.out.println("Type ON to turn the Debugger on.");
		}
		System.out.println("Type X to leave.");
	}
	
	/*
	 * Rearranges the deck.
	 * The way this works is that you create a new deck with only the cards you put in and then after those cards are over the dealer puts in a brand new 52 card deck.
	 * You could add duplicates. 
	 * */
	public void deckMgmt() {
		d1.clearDeck();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Add a card (type XX to quit and UNDO to remove the last card added to the deck):");
			String carte = sc.nextLine();
			if (isValidCard(carte)) {
				if (carte.equals("XX")) {
					return;
				} else if (carte.equals("UNDO")) {
					System.out.println(d1.getLastCard().toString() + " has been removed from the deck.");
					d1.removeLastCard();
				}
				Card c = stringToCard(carte);
				d1.addCard(c);
			} else {
				System.out.println("That card is invalid. Try again.");
				System.out.println();
			}
		}
		
	}
	
	/*
	 * Checks if a string is a valid representation of a card so to avoid errors.
	 * 
	 * @param potent: the string
	 * */
	
	public boolean isValidCard (String potent) { // Debugger only method that checks if a card in string form is valid or not.
		if (potent.equals("XX") || potent.equals("UNDO")) {
        	return true;
        } 
		String[] ranks = {"X", "X", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"H", "D", "S", "C"};
        if (potent.length() == 1) {
        	return false; // substring would cause errors here.
        }
        String ranque = potent.substring(0, 1);
        String sute = potent.substring(1, 2);
		if (potent.length() == 2) {
			int i = 0; // just a filler method here.
		} else if (potent.length() == 3) {
			ranque = potent.substring(0, 2);
			sute = potent.substring(2, 3);
		} else {
			return false;
		}
        boolean validRank = false;
        boolean validSuit = false; 
        for (int i = 2; i < ranks.length; i++) {
        	if (ranks[i].equals(ranque)) {
        		validRank = true;
        	}
        }
    	for (int i = 0; i < suits.length; i++) {
        	if (suits[i].equals(sute)) {
        		validSuit = true;
        	}
    	}
    	return validRank && validSuit; 
	}
	
    /*
     * Takes a card in string form and converts into an actual Card.
     * 
     * @param compressedCard: the string (e.g. "8H")
     * */
	public Card stringToCard(String compressedCard) {
    	String[] ranks = {"X", "X", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"H", "D", "S", "C"};
        int rank = -1;
        int suit = -1;
        
        String strRank = compressedCard.substring(0, 1);
        String strSuit = compressedCard.substring(1, 2);
        if (compressedCard.length() == 3) {
        	strRank = compressedCard.substring(0, 2);
        	strSuit = compressedCard.substring(2, 3);
        }
        for (int i = 0; i < ranks.length; i++) {
        	if (ranks[i].equals(strRank)) {
        		rank = i;
        	}
        }
        for (int i = 0; i < suits.length; i++) {
        	if (suits[i].equals(strSuit)) {
        		suit = i;
        	}
        }
        if (rank == -1 || suit == -1) {
        	System.out.println("There was an error with the data being fed into stringToCard()"); // for debug purposes
        }
        Card c = new Card(rank, suit);
        return c; 
    }
    
}
