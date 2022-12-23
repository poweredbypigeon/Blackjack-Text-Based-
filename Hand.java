package Blackjack;

import java.util.*;

public class Hand 
{

    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    private int insuranceBet = 0;
    
    /*The money received from insurance, only used with dealer hands. If it is more than 0 the player's insurance bid was successful. If it wasn't the player failed. */
    
    private int bet = 0; // Bets may be modified by doubling down. 
    
    private String outcome = ""; // The outcome of the hand (player). Plays a role in deciding the dealer's actions. 
    /* Outcomes: busted, stood, surrendered, charles*/
    
    static int[] movesPlayed = {0, 0, 0, 0, 0, 0}; // in the order: hit, stand, surrender, insurance, split, double down. Tracks how many of each people have done each round.
    // hoping to reset movesPlayed later and have it be the same across ALL hands.
    
    // should add a bet on this hand.

    // This represents the list of cards in the hand.
    private ArrayList<Card> cards;
    
    /**
     * This constructor sets up our hand by initializing our
     * ArrayList.
     */
    public Hand()
    {
        cards = new ArrayList<Card>();
        outcome = ""; 
    }
    
    /**
     * This adds a card to our hand.
     * 
     * @param c The card to add to the hand.
     */
    public void addCard(Card c)
    {
        cards.add(c);
    }
    
    /*
     * A method used during splitting where the first card is removed and sent to another hand. 
     * 
     **/
    public void splitRem() {
    	cards.remove(0);
    }
    
    /**
     * This returns the value of the hand as an integer.
     * 
     * The value of the hand is the sum of the values
     * of the individual cards. There is also an adjustment
     * made for the value of an ace, which can be 11 or 1
     * depending on the situation.
     * 
     * @return The value of the hand.
     */
    public int getValue()
    {
        int sum = 0;
        int aceCount = 0;
        
        for(Card c: cards)
        {
            sum += c.getValue();
            
            if(c.getRank() == ACE)
            {
                aceCount++;
            }
        }
        
        while(sum > 21 && aceCount > 0)
        {
            sum -= 10;
            aceCount--;
        }
        
        return sum;
    }
    
    /*Getter & setter methods for 3 different instance variables. */
    
    public void setBet(int nb) {
        bet = nb; 
    }
    
    public int getBet() {
        return bet;
    }
    
    public void setOutcome(String o) {
    	outcome = o;
    }
    
    public String getOutcome () {
    	return outcome; 
    }
    
    public void setInsurance(int i) {
    	insuranceBet = i;
    }
    
    public int getInsurance() {
    	return insuranceBet; // if it's 0, don't announce it. If it is, announce it. 
    }
    
    
    /**
     * Return if this hand has a blackjack.
     * Add that this is not true if you have split. 
     * 
     * @return If the hand is a blackjack.
     */
    public boolean hasBlackjack()
    {
        return (getValue() == 21 && cards.size() == 2) && (Hand.movesPlayed[4] == 0);
    }
    
    /**
     * Return if the hand busted, which means has a value
     * greater than 21.
     * 
     * @return If the hand busted.
     */
    public boolean busted()
    {
        return getValue() > 21;
    }
    
    /**
     * Return if the hand is a five-card-charlie, which means
     * contained five cards.
     * 
     * @return If the hand was a five-card charlie.
     */
    public boolean fiveCardCharlie()
    {
        return cards.size() == 5;
    }
    
    // returns if the hand can be split.
    /*
     * For a hand to split, the card sizes must be 2 and their value must be the same.
     * 
     * */
    public boolean splittable() {
    	return (cards.size() == 2 && (cards.get(0).getValue() == cards.get(1).getValue()));
    }
    
    /**
     * Print out a dealer hand, and show an X instead of
     * the first card.
     */
    public void printDealerHand()
    {
        for(int i = 0; i < cards.size(); i++)
        {
            Card c = cards.get(i);
            
            if(i == 0)
            {
                System.out.print("X ");
            }
            else
            {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }
    
    /* Getter utility method to print the ith card.*/
    
    public Card getCard(int index) {
        return cards.get(index);
    }
    
    /**
     * Return a String representation of the hand, including showing
     * the value.
     */
    public String toString()
    {
        String result = "";
        
        for(Card c: cards)
        {
            result += c + " ";
        }
        
        result += "(" + getValue() + ")";
        
        return result;
    }

}

