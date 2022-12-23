package Blackjack;

import java.util.*;

public class Deck
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    // Instance variables
    
    // Number of decks (to be added in 1.1)
    int numOfDecks = 6; 
    
    // This stores the deck which is a list of the Card objects.
    private ArrayList<Card> deck;
    
    /**
     * This creates a Deck. A Deck starts as a list of 52 cards.
     * We loop through each suit and rank and construct a card
     * and add it to the deck.
     */
    public Deck()
    {
        deck = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
        	for(int rank = 2; rank <= ACE; rank++)
            {
                for(int suit = HEARTS; suit <= CLUBS; suit++)
                {
                    Card card = new Card(rank, suit);
                    deck.add(card);
                }
            }
        }
    }
    
    /*
     * Copy constructor- used for rearranging deck.
     * */
    public Deck(Deck otherDeck) {
    	deck = otherDeck.deck; 
    }
    
    // Getter method
    
    /**
     * This getter method returns the ArrayList of cards.
     * @return ArrayList<Card> of the Cards.
     */
    public ArrayList<Card> getCards()
    {
        return deck;
    }
    
    /**
     * This adds a card to the deck. Used in debugger and rearranging the cards.
     * 
     * @param c The card to add to the deck.
     */
    public void addCard(Card c)
    {
        deck.add(c);
    }
    
    // two helper methods that help with undoing.
    public Card getLastCard() {
    	return deck.get(deck.size() - 1);
    }
    
    public void removeLastCard() {
    	deck.remove(deck.size() - 1);
    }
    /*
     * Method that can be used in conjunction with the constructor to generate a blank deck, especially during the debugger.
     * */
    public void clearDeck() {
    	deck = new ArrayList<Card>();
    }
    
    /**
     * This deals the first Card from the deck by removing it.
     * @return The first Card in the deck.
     */
    /**
     * This shuffles the deck by making 52 swaps of
     * card positions.
     */
    public void shuffle()
    {
        for(int i = 0; i < deck.size(); i++)
        {
            int randomIndex = Randomizer.nextInt(52*numOfDecks);
            Card x = deck.get(i);
            Card y = deck.get(randomIndex);
            
            deck.set(i, y);
            deck.set(randomIndex, x);
        }
    } 
    
    public Card deal()
    {
        if (deck.size() == 0) {
            System.out.println("Auxiliary deck cards added."); // Method to be used after preset cards are done.
            System.out.println("You manipulated the deck and the dealer was none the wiser.");
            for (int i = 0; i < 6; i++) {
            	for(int rank = 2; rank <= ACE; rank++)
                {
                    for(int suit = HEARTS; suit <= CLUBS; suit++)
                    {
                        Card card = new Card(rank, suit);
                        deck.add(card);
                    }
                }
            }
            // deck.shuffle();- why can't I run this?
            for(int i = 0; i < deck.size(); i++)
            {
                int randomIndex = Randomizer.nextInt(52*numOfDecks);
                Card x = deck.get(i);
                Card y = deck.get(randomIndex);
                
                deck.set(i, y);
                deck.set(randomIndex, x);
            }
            return deck.remove(0); 
        } else {
            return deck.remove(0);
        }
        
    }
    
    /* returns size of array */
    public int deckSize() {
        return deck.size(); 
    }
    
    /**
     * This prints out the current state of the deck.
     */
    public void print()
    {
        for(Card card: deck)
        {
            System.out.println(card);
        }
    }
    
    

}

