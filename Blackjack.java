


package Blackjack;

import java.util.*; 
/*"VERSION HISTORY":

v1.0-

I added some additional features on Blackjack:

Flavor text- Is somewhat goofy, but the comments will explain what their purpose is. 

Integer overflow prevention- If you have more than 1 billion dollars (theoretically you could reach a 2B max) you will get banned from the casino.

You can increase your cash by up to 100% each game, so even if you enter with $999,999,999 you won't generate enough money to exceed the integer's max value.

Basic Debugger- Allows you to bet $0 and find out what actions you have done already.

A "lounge" or "outside the game environment" where you can take a break in between rounds.

A rules page, accessible via the lounge. 

Added splitting, doubling down, surrender and insurance.

Already included:

- Naturals 
- Five-card charlies

v1.1-

Changed deck size to 52*6.

Enhanced debugger to re-order the deck, and allowed you to toggle it on or off. 

Added try/catch statements to stop program from crashing when user entered wrong input.

 FUTURE UPDATES:
 
v1.2-
 
 

v2.0-

Achievements, gadgets, story mode added

*/ 
 
/* 

Interest loan: Once you dip below zero, you get the option to loan money from the bank as long as (current money before loan - debt after loan is less than 100)

Every match, debt increases by 2% (rounded to 2 dp) and 50% of all wins will be spent on the debt. You can also pay debts after a match voluntarily.

constants: DEBT_PAY_RATE = 50

BANK_DEBT_THRESHOLD = 100 (stops loaning you after this much in debt, money - debt > 100

---

Achievements (you could access them after a game)

---

There will be a special comment of having debt once you leave the casino. Once you leave, all debts will be paid with your money.

EDIT: Idea seems bad and stupid 



Upgrades: A 'cheat engine' that costs X^2 where X is the level and increases your 'win rate' by 0.1. 

(basically you would get it from another player)

A 'DeqTraq' (cheap) that tells you how many cards are left in the deck. 

A 'Strategy sheet' for $100 that you can access at any time by typing "strat". 

A 'CardCounter 3000' that identifies the cards left in the deck and how many cards left in the deck, but not the order. 

A 'probability engine' which counts cards and does math. 

A book titled "Covert Operations Vol. 1: On the Double" which allows you to double down wheneverf

'TimeWarp', control whether you can bet after you see the dealer's hand. 

'FutureSightTM', play hand after dealer finishes theirs so you can make decisions.

'TableX', you can split 5 times instead of 3. Fairly useless:

	TableX- the new generation of tables. 66% longer than your average table, 1000% more durable and 50% lighter!
	Also includes centimetre long graduations on the sides of the table and a bunch of things you don't need.

(A multi-level 'RealityEngine' that allows you to have some chance of breaking rules (e.g. 9-10-11 double down rule) and (splitting unsplittable cards) and transmute cards, or "Covert Operations")

'Debugger'- unlocked at start, can change order of deck and manipulate money and see dealer's hand.

Some are more monetary:

- 'Player Edge'- A sword. 

- '' multiplies losses by 0.99*level

- '' multiplies wins by 1.01*level

- 'Ashmore Cash Converters'

- 'Bank Membership'- gives you interest on all the money you DON'T spend (availableCash) but doesn't let you go into debt.

(interest can be upgraded)

- 'Magical Wallet' (different tiers) gives you $20 when you fail instead of $5 on story mode ($5 -> $20 -> $50 -> $100 -> $250) (end costs lots of money)

e.g. Level 0 (default)- "Leather Wallet"- A boring wallet.

Level 1 (default in story mode)- "Enchanted Wallet"- This wallet was fabricated from plot armor itself and always keeps you 5 dollars away from bankruptcy.

Level 2- "Golden Wallet"- Now you get $20 every time you 

Level 3- 

Level 4-

Level 5- "Wallet of the Gods"- The failsafe to end ALL failsafes. (Gives you $250 if you would go bankrupt)

All gadgets can be toggled on or off. They have text when acquired and text in the description. 

 Add storyline about how the casino is panicking over the player.

---

DEBUG MODE (for test cases):

- Type in a number from 0 to 1 billion 

- manipulate the order of the deck (include a fresh deck of 52 cards, and just type what you want.

You could even type in duplicates, and type in 'XX' to close everything off. However, it will check if the card is valid.)



---



House cash: House could have lots of money and if you bankrupt it you win (only with cheat engine though). 

House could buy its own cheat engines once your cheat engine level is above 0.55 or something and it would decrease it by 0.01. 

Multiple decks: very practical 

A Blackjack Tournament between a few people. (figure out # of options)

An option where decks DON'T reset until they go down to a certain threshold (e.g. 10 cards)

63, a Blackjack variation. Dealer hits on a 56. (stupid). 13-card charlies exist. 

---

*/

/* The debugger is a small device that's supposed to provide you with more information about the game, similar to how
 * I sometimes debug by printing something out to see if the code made it to a certain point. 
 * 
 * The debugger also lets you rearrange the deck in advance so you can test different scenarios out and change your cash.
 * 
 * In story mode, I am planning to disable the debugger. 
 * */

/*
 * Add some gadgets in the store, story mode, comment and package stuff up
 * And split help
 * And come up with a list of test cases to showcase features of the program.
 * */

// TODO Could split help into multiple parts (Part I: The Objective of the game, Part II: Decisions, Part III: Additional Tips, EXTRA: Gadgets)
// TODO Add test cases to demonstrate all features. 
// TODO Add ideas for other gadgets in my own copy.
 
public class Blackjack
{

	// constants
	
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    private static final int MAX_SPLITS = 3; 
    private static final int DECK_SIZE = 52;
    
    // The starting bankroll for the player.
    static double cash = 100.0;
    
    // The amount of available cash.
    static double availableCash = 100.0; 
    /*Money is deducted whenever you make a normal bet, insurance and double down and is basically there to stop you from spending too much:
     * 
     * e.g. you have $100 and bet $51. If you decide to split, that means you will be betting another $51. If you lose on both hands, you go $2 into debt.
     * 
     * The game is designed not to let you have negative money, but you still lose if you go down to $0. 
     * 
     * Each round, availableCash should be reset to your current amount of cash.*/
    
    // a different mode
    static boolean storyMode = false;
    
    // an easter egg variable.
    static boolean played = false;
    
    // An easter egg variable.
    static boolean discoveredStore = false;
    
    // an easter egg variable.
    /*When in menu mode, after reading Part III the book will be unlocked and say you find the book and save it for later.*/
    static boolean foundBook = false;
    
    /* Controls gadgets. Gadgets were a feature that I didn't have time to fully implement. */
    static ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
    
    // remaining hands left.
    static ArrayList<Hand> remainingHands = new ArrayList<Hand>(); 
    
    // contains all the hands once they're done to compare them to the dealer's hand. 
    static ArrayList<Hand> playerHands = new ArrayList<Hand>(); 
    
    // A utility method for printing out spaces.
    public static void margin(int spaceNum) {
    	for (int i = 0; i < spaceNum; i++) {
    		System.out.println();
    	}
    }
    
    // A really basic method that just prints out some opening remarks. 
    public static void introMessage (Scanner v) {
    	System.out.println("Welcome to CodeHS Casinos!");
    	System.out.println("CodeHS Casinos offers the latest in entertainment with its flagship blackjack game!");
    	System.out.println("Just make sure to have fun, don't break the rules and don't hold back when it comes to bets! We want YOUR money-");
    	System.out.println("Oops... wasn't supposed to say that.");
    	System.out.println("You can play blackjack or rest in the lounge area.");
    	System.out.println("(Press enter to go to lounge)");
    	String buffer = v.nextLine();
    	System.out.println("A small computer chip in your palm whirs and whizzes, illuminating blue flashes of light.");
    	System.out.println("NEW GADGET UNLOCKED: Debugger");
    	Debugger d = new Debugger();
    	gadgets.add(d);

    	margin(2);
    	 lounge();
    }
    
    /*
     * This function builds the main menu and allows the player to do many things like play or check the rulebook or quit.
     * */
    public static void lounge () {
    	Scanner i = new Scanner(System.in);
    	if (cash >= 1000000000) {
    		System.out.println("You have become Wealth, the destroyer of casinos.");
    		System.out.println("Oh... and $" + cash);
    	} else if (cash >= 1000000) {
    		System.out.println("With $" + cash + ", you are living the dream!!");
    	} else if (cash > 0) {
    		System.out.println("You currently have: $" + cash + " on hand.");
    	} else {
    		System.out.println("You are bankrupt...");
    	}
    	// could include storylines about CodeHS Casinos panicking over what to do with the player. 
    	boolean notSelected = true;
    	
    	while (notSelected) {
    		try {
    			System.out.println("Choose from: ");
    	    	System.out.println("1. Play");
    	    	System.out.println("2. Rules");
    	    	System.out.println("3. Gadgets");
    	    	if (discoveredStore) {
    	    		System.out.println("4. Store");
    	    	} else {
    	    		System.out.println("4. Explore Area");
    	    	}
    	    	System.out.println("5. Achievements");
    	    	System.out.println("6. Leave");
    	    	System.out.println();
    			int choice = i.nextInt();
        		if (choice == 1) {
        			if (cash <= 0) {
        				System.out.println("You are bankrupt and you cannot play.");
        			} else if (cash >= 1000000000) {
        				System.out.println("The security guard denies you access to the gambling hall. It seems as if you have been banned.");
        			} else {
        				notSelected = false;
        				played = true; 
            			playRound();
        			}
        		} else if (choice == 2) {
        			help();
        		} else if (choice == 3) {
        			gadget();
        		} else if (choice == 4) {
        			if (discoveredStore) {
        				System.out.println("You go back to the die, but it still says COMING SOON.");
        				margin(1);
        			} else {
        				System.out.println("You exit the casino and walk around a couple blocks...");
            			System.out.println("You find nothing interesting, so you walk back to the casino.");
            			System.out.println("This casino is pretty much your second home now, or first, considering you only have " + cash + " dollars in your pocket.");
            			System.out.println("Suddenly you see a giant die made out of metal. It looks like some sort of sculpture...");
            			System.out.println("You spy a button on it and press it...");
            			margin(1);
            			System.out.println("COMING SOON... Oh.");
            			discoveredStore = true; 
        			}
        			margin(1);
        		} else if (choice == 5) {
        			System.out.println("Achievements coming soon.");
        		} else if (choice == 6) {
        			exitMessage();
        			return; 
        		} else {
        			System.out.println("Invalid command. Try again.");
        		}
    		} catch (InputMismatchException ime) {
    			System.out.println("It appears as if you didn't enter an integer.");
    			System.out.println("Invalid command. Try again.");
    			i.next();
    		}
    	}
    }
    
    /* 
     * Gives player access to list of gadgets.
     * 
     * What I was trying to do with this feature is have a 'story' where the player is trying to bankrupt the casino by getting a billion dollars.
     * However, this is very hard to do in normal Blackjack so I was thinking of making gadgets that could help you. 
     * The very first gadget, the Debugger, gives you more information.
     * 
     * */
    public static void gadget() {
    	while (true) {
    		System.out.println("<PLAYER>'s INVENTORY: ");
        	margin(1);
        	for (int i = 0; i < gadgets.size(); i++) {
        		Gadget g = gadgets.get(i);
        		System.out.println(g.name + " ID: " + g.id + " LVL: " + g.level);
        	}
        	margin(1);
    		int selected = getGadget();
        	if (selected == -1) {
        		System.out.println("Exiting inventory...");
        		margin(1);
        		break;
        	} else {
        		Gadget theGadget = gadgets.get(selected);
        		theGadget.printIntroduction();
        		if (selected == 0) {
        			Debugger d = (Debugger) theGadget; 
        			Scanner sc = new Scanner(System.in);
        			while (true) {
        				d.printInstructions();
        				String a = sc.nextLine();
        				if (a.equals("RE")) {
        					d.deckMgmt();
        				} else if (a.equals("$")) {
        					int changes = forceInputBetweenXAndY(1, 1000000000, "Enter the number of cash you want from 1-1000000000. Press 0 to cancel.");
        					cash = changes;
        				} else if (a.equals("ON")) {
        					if (!d.status) {
        						d.status = true;
        					}
        				} else if (a.equals("OFF")) {
        					if (d.status) {
        						d.status = false; 
        					}
        				} else if (a.equals("X")) {
        					break;
        				} else {
        					System.out.println("Invalid command.");
        				}
        			}
        		}
        	}
    	}
    	 lounge();
    }
    
    /* 
     * Keeps asking user for a gadget unless they cancel 
     * */
    public static int getGadget() {
    	Scanner gkey = new Scanner(System.in);
    	while (true) {
    		System.out.println("Type the ID of the gadget you want to use, or type -1 to exit and go back to the lounge.");
    		try {
    			int input = gkey.nextInt();
    			for (int i = 0; i < gadgets.size(); i++) {
    	    		Gadget g = gadgets.get(i);
    	    		if (g.id == input) {
    	    			return input;
    	    		}
    	    	}
    			if (input == -1) {
    				return -1;
    			}
    			System.out.println("You do not have that gadget with you.");
    		} catch (InputMismatchException ime) {
    			System.out.println("It appears as if you didn't enter an integer.");
    			gkey.next();
    		}
    	}
    }
    
    // basically displays the FAQ and redirects you back to the lounge. 
    public static void help() {
    	Scanner enterKey = new Scanner(System.in);
		System.out.println("CODEHS CASINOS OFFICIAL RULEBOOK");
		margin(1);
		System.out.println("Blackjack is played using a 52 card deck of playing cards.");
		System.out.println("Cards with numbers on them are worth points equal to their number (e.g. 7 of hearts = 7 pts) and jack, queen and king are all worth 10 pts.");
		System.out.println("You are playing against the dealer. The objective for both players is to draw cards and go as close to 21 as possible but not over 21. If you go over 21, you have �busted� and you automatically lose.");
		System.out.println("An ace is worth 11 points, but if you would receive enough points to go over 21, it is worth 1 point.");
		System.out.println("Jokers are illegal cards here.");
		margin(1);
		System.out.println("At CodeHS Casinos, it is MANDATORY to bet money.");
		System.out.println("If you win, you gain money equal to your bet and if you lose, you lose money equal to your bet.");
		System.out.println("However, you go first, and one of the dealer�s cards is face down whereas both of yours are face up.");
		System.out.println("You will play your hand and then the dealer will play dealer's hand.");
		margin(1);
		System.out.println("(Press enter to continue)");
		String buffer = enterKey.nextLine();
		System.out.println("Here are all the decisions you can make:");
		System.out.println("HIT: draw another card.");
		margin(1);
		System.out.println("STAND: stop drawing cards and let the dealer play.");
		System.out.println("The dealer will hit until they reach 17 or more, and at that point the dealer stands.");
		margin(1);
		System.out.println("SURRENDER: You can only do this on the first round. You automatically forfeit and lose half your bet.");
		margin(1);
		System.out.println("INSURANCE: You can only do this once. If the dealer�s face up card is an ace, you may make a side bet of up to half your main bet that the dealer�s face down card is worth 10. If it is, you get paid twice your side bet (if it isn�t, you still lose one times your side bet).");
		margin(1);
		System.out.println("SPLIT: If you have 2 cards that are worth the same (they don�t have to be of the same rank), you can split them into two hands, each with the same bet (so you could double gains and double losses) and then draw a card for both. Play the hand with the 2nd card first.");
		System.out.println("Each hand will be compared against the dealer�s hand. The dealer will not play two hands.");
		System.out.println("You cannot go into debt with a split. For example, if you have $100 and bet $60, you cannot split as if you lost on both hands you would be $20 in debt.");
		System.out.println("You can split up to 3 times per round.");
		System.out.println("If you have two aces and split, you cannot draw any more cards after the 2nd.");
		System.out.println("When playing a new hand, you cannot perform any �first turn� actions (surrendering).");
		margin(1);
		System.out.println("DOUBLE DOWN: You may increase your bet up to twice your old bet, and then you must take a card and then stand.");
		System.out.println("You may only double down if your hand�s value is 9, 10 or 11.");
		System.out.println("If you split and double down on one hand, the bet on the other hand will not increase.");
		margin(1);
		System.out.println("(Press enter to continue)");
		buffer = enterKey.nextLine();
		System.out.println("Some other points worth mentioning:");
		System.out.println("The deck gets reset and reshuffled after each round");
		System.out.println("If you and the dealer have the same score, you 'push' and no one gains anything.");
		System.out.println("If you bust and the dealer busts, you still lose. ");
		System.out.println("The dealer will stand even if they have an ace that is currently giving them 11 points. (this is called a 'soft 17').");
		System.out.println("If you have an Ace and a card worth 10, you have a 'blackjack' and you will receive 1.5x your normal bet (if you win).");
		System.out.println("If you have a blackjack but the dealer has a non-blackjack 21, you still win. If both of you have blackjacks, you push.");
		System.out.println("If you have a non-blackjack 21 and the dealer has a blackjack, you lose.");
		System.out.println("If you have two aces, split and then draw a card worth 10, that is not counted as a blackjack.");
		System.out.println("If your hand has 5 cards and you haven�t busted yet, you automatically win (this is called a 5-card charlie).");
		System.out.println("");
		System.out.println("You have reached the end of the book. Call 1-999-200-0000 or press enter to return to the lounge.");
		buffer = enterKey.nextLine();
		// story mode content
		System.out.println("Just as you're about to head back, a second book falls into your pocket.");
		System.out.println("Gadgets & How to Use Them");
		margin(1);
		System.out.println("If you are reading this book right now, you probably have a debugger in your inventory.");
		System.out.println("The debugger is a GADGET, a tool that could potentially help you win the game.");
		System.out.println("To access it, go to the gadgets panel in the lounge.");
		System.out.println("You can select it from a menu and turn it on or off.");
		System.out.println("Some gadgets have passive effects and some have active effects that you can activate by going to the gadgets panel and selecting it.");
		System.out.println("Oh, and consider going outside by typing 4 in the lounge. You never know what you might find...");
		margin(1);
		// end of story mode content
		System.out.println("(Press enter to continue)");
		buffer = enterKey.nextLine();
		 lounge();
		
    }
    
    // utility method for insurance & double down, keeps on asking user for input until they get something between x and y. The method will be called in such a way that x >= y.
    public static int forceInputBetweenXAndY (int x, int y, String prompt) {
    	Scanner xy = new Scanner(System.in);
    	int a = 0;
    	while (true) {
    		 System.out.println(prompt);
    		 try { // this is to protect against people who type in the wrong thing (e.g. being asked to enter an integer and entering 'dog')
    			 a = xy.nextInt();
        		 if (a >= x && a <= y) {
        			 break;
        		 } else if (a == 0) { // 0 will cancel the action so the user doesn't get stuck in an infinite loop if they want to change their mind.
        			 break;
        		 } else {
        			 System.out.println("Input not within allotted range.");
        		 }
    		 } catch (InputMismatchException ime) {
     			System.out.println("It appears as if you didn't enter an integer.");
     			xy.next();
     		 }
    	}
    	return a;
    }
    
    /*
     * Prints a customized farewell message based on how much money you have.
     * */
    public static void exitMessage() {
    	if (!played) {
    		System.out.println("Leaving so soon?");
    	} else if (cash >= 1000000000) {
            System.out.println("Thank you for playing...");
        } else if (cash < 100) {
            System.out.println("Thanks for playing (and your money)!");
        } else {
            System.out.println("Thanks for playing!");   
        }
    	System.out.println("...");
    	if (cash >= 1000000000) {
    		System.out.println("You did it. You really did it.");
    		System.out.println("You have become an overnight billionaire and you have beaten CodeHS Casinos out of its own game, a game that was meant for them to win.");
    		System.out.println("You stare up at the sky, elated.");
        } else if (cash >= 10000) {
        	System.out.println("You walk out having gained " + (cash - 100) + " dollars. You made a killing!");
        } else if (cash >= 110) {
        	System.out.println("You feel happy that you managed to secure " + (cash - 100) + " dollars.");
        } else if (cash > 100 && cash < 110) {
            System.out.println("You are pleasantly surprised by the fact that you managed to gain money at all. ");
        } else if (cash == 100) {
            System.out.println("You didn't take or lose anything. Go figure.");
        } else if (cash > 0) {
            System.out.println("You are now " + (100 - cash) + " dollars less richer than you came into it. Luck was not on your side.");
        } else {
        	System.out.println("Alas... it looks like CodeHS Casinos and their ultra-charismatic dealers have used probability to their advantage, claiming the wallet of yet another gullible victim (you).");
            System.out.println("You walk out of the casino destitute and grieve at the 100 dollars you lost chasing vain illusions of wealth.");
            System.out.println("You vow never to gamble again and rethink your approach to money.");
            
        }
    	// change later
    	System.exit(0); // for some reason lounge() is being called twice if you play, so this is a blanket solution.
    }
    
    /*
     * Takes a card in string form and converts into an actual Card.
     * 
     * @param compressedCard: the string (e.g. "8H")
     * */
    public static Card stringToCard(String compressedCard) {
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
    
    /*
     * A method that was supposed to generate a player's hand and accommodated split hands where one card from a leftover hand would be added.
     * 
     * e.g. 9D & 9C -> 9D would be added to a split hand, so I would store it for later.
     * 
     * However, I changed the program structure to generate a split hand immediately after splitting, so this program functionally only gives the player 2 cards.
     * 
     * */
    public static Hand generatePlayerHand(Deck deck, String splitCard) {
    	// if splitCard is 2R, generate normal hand. 
    	Hand h = new Hand();
    	if (splitCard == "2R") {
    		h.addCard(deck.deal());
    	} else {
    		h.addCard(stringToCard(splitCard));
    	}
    	h.addCard(deck.deal());
    	// add to remainingHands the result of this.
    	return h; 
    }
    
    /*
     * Gives the dealer 2 cards.
     * */
    public static Hand generateDealerHand(Deck deck) {
    	Hand h = new Hand();
    	h.addCard(deck.deal());
    	h.addCard(deck.deal());
    	return h;
    }
    /*
     * Plays 1 hand and returns it so it can be compared to the dealer's hand.
     * 
     * */
    public static Hand playerHand (Deck deck, Hand player, Hand dealer, int bet) {
   
    	// considering adding a bet variable to each hand, as well as the outcome of that hand. 
    	while (true) {
    		if (player.fiveCardCharlie()) {
    			System.out.println("Is your name Charles? Because you just won the game!");
    			player.setOutcome("charles");
    			margin(1);
    			break; 
    		}
    		String move = getPlayerMove(deck, player, dealer);
    		if (move.equals("hit") || move.equals("h")) {
    			Card c = deck.deal();
                System.out.println("Your card was: " + c);
                player.addCard(c);
                System.out.println("Dealer's hand: ");
                dealer.printDealerHand();
                System.out.println("Your new hand is: ");
                System.out.println(player);
                if (player.busted()) {
                	player.setOutcome("busted");
                	System.out.println("Your hand overextended itself by " + (player.getValue() - 21) + " and you have busted.");
                	margin(1);
                	break;
                }
    		} else if (move.equals("stand") || move.equals("s")) {
    			player.setOutcome("stood");
    			 System.out.println("Dealer's hand: ");
                 dealer.printDealerHand();
                 System.out.println("Your hand is: ");
                 System.out.println(player);
    			break;
    		} else if (move.equals("surrender") || move.equals("u")) {
    			player.setOutcome("surrendered");
    			System.out.println("Dealer's hand: ");
                dealer.printDealerHand();
                System.out.println("Your hand is: ");
                System.out.println(player);
    			break;
    		} else if (move.equals("split") || move.equals("p")) { // no text for insurance.
    			// It's a good things you can't bust on 2 cards. If not, how would I store a busted hand? 
    			Hand hand2 = new Hand();
    			hand2.setBet(bet);
    			hand2.addCard(player.getCard(0));
    			player.splitRem();
    			Card c = deck.deal();
    			Card c2 = deck.deal();
    			player.addCard(c);
    			hand2.addCard(c2);
    			margin(1);
    			System.out.println("Drew " + c + " and added it to current hand.");
    			System.out.println("Drew " + c2 + " and added it to the next hand.");
    			margin(1);
    			System.out.println("Dealer's hand: ");
                dealer.printDealerHand();
    			System.out.println("Your new hand is: ");
                System.out.println(player);
                margin(1);
    			if (player.getCard(0).getValue() == 11) { // If you split aces, you can only draw one more card. 
    				player.setOutcome("stood");
    				hand2.setOutcome("stood");
    				playerHands.add(hand2); // the program will automatically add the player's hand to playerHands once this is done.
    				break;
    			}
    		
    			remainingHands.add(hand2);
    		} else if (move.equals("double down") || move.equals("d")) {
    			// basically a hit, but if player doesn't bust, stand. 
    			Card c = deck.deal();
                System.out.println("Your card was: " + c);
                player.addCard(c);
                System.out.println("Dealer's hand: ");
                dealer.printDealerHand();
                System.out.println("Your new hand is: ");
                System.out.println(player);
                if (player.busted()) {
                	player.setOutcome("busted");
                } else {
                	player.setOutcome("stood");
                }
                break;
    		} else if (move.equals("insurance") || move.equals("i")) {
    			System.out.println("Dealer's hand: ");
                dealer.printDealerHand();
                System.out.println("Your hand is: ");
                System.out.println(player);
    		}
    	}
    	return player; 
    }
    
    
    /*
     * Checks the list of actions the player has done this round to see if this is the player's first turn or not. 
     * 
     * */
    public static boolean firstTurn (int[] mp) { // utility method that looks at moves played to see if player did anything 
    	// should include whether the player split before though. 
    	boolean ft = true;
    	for (int e : mp) {
    		if (e != 0) {
    			ft = false;
    		}
    	}
    	return ft; 
    }
    
    /*
     * 
     * Gets the player move, and loops until the move is valid. 	
     * 
     * */
    
    public static String getPlayerMove (Deck deck, Hand player, Hand dealer) {
    	Scanner sc = new Scanner(System.in);
    	int modBet = player.getBet(); // bet might not be the same as the original bet player entered, name changed to avoid confusion
    	while(true)
        {
    		System.out.println("Possible moves (case insensitive): hit (H), stand (S), surrender (U), insurance (I), split (P), double down (D)"); // capital letters are shortcuts
    		// debug lines
    		Debugger d = (Debugger) gadgets.get(0);
    		if (d.status) {
    			System.out.println("[DEBUG MODE ON] The debugger WHIRRS and prints out a strange list: ");
        		for (int i = 0; i < 6; i++) {
        			System.out.print(Hand.movesPlayed[i] + " "); // how many actions played. 
        		}
        		System.out.println();
    		}
    		
    		// end of debug lines
    		System.out.println("Enter a move: ");
    		String move = sc.nextLine();
            move = move.toLowerCase();
            if (move.equals("hit") || move.equals("h")) {
            	System.out.println("You knifehand the table."); // a parody of hand signals used in blackjack that are supposed to make it easier for cameras.
            	if (player.getValue() == 21) {
            		System.out.println("Why... just why... would you do that?"); // The player would have won or at least pushed. A move like this will 100% bust.  
            	}
            	Hand.movesPlayed[0]++;
            	return move;
            }
            if (move.equals("stand") || move.equals("s")) {
            	System.out.println("You stand up. No more cards for me!");
            	Hand.movesPlayed[1]++;
            	return move;
            }
            if (move.equals("surrender") || move.equals("u")) { // you shouldn't be able to surrender after splitting.
            	if (firstTurn(Hand.movesPlayed)) {
            		System.out.println("You back against the wall behind you and raise your hands up and nervously look around.");
            		Hand.movesPlayed[2]++;
                	return move; 
            	} else {
                	System.out.println("You cannot surrender. You already committed, you coward.");
                }
            } 
            if (move.equals("insurance") || move.equals("i")) {
            	if (Hand.movesPlayed[3] == 0) {
            		
            		System.out.println("You pull out a form titled AC-3: Ace Evasion. Courtesy of CodeHS Insurance Ltd.");
            		if (dealer.getCard(1).getRank() == ACE) {
            			System.out.println("You work through the form.");
            			int maxBet = Math.min((int) Math.floor(availableCash), modBet/2); // I think modBet / 2 should round down because they're both integers.
            			if (maxBet == 0) {
            				System.out.println("You stop at a section called 'Initial Commitment'. You have to spend money for insurance? Doesn't make sense. What is this business model?");
            				System.out.println("Also, you don't have any money, but you quickly remind yourself that that's besides the point.");
            			} else {
            				// get side bet for insurance.
            				System.out.println("OK. Now it's time to choose a side bet for the insurance. Weird but OK.");
            				String pr = "Enter a number between 1 and " + maxBet + ". Type 0 to cancel.";
            				int sb = forceInputBetweenXAndY(1, maxBet, pr);
            				if (sb == 0) {
            					System.out.println("After all that work, you ultimately decide to throw the form away. What a waste of time.");
            				} else {
            					System.out.println("Took you long enough, but you got it done.");
            					availableCash -= sb; // the side bet cannot be spent until the round is over. 
            					calculateInsurance(dealer, sb); // store result of the insurance bet but don't announce it until the dealer turns over dealer's face down card. 
                				Hand.movesPlayed[3]++;
                            	return move;
            				}
            			}
            		} else {
            			System.out.println("AC-3 does not protect against hands with no visible aces in them as they are deemed of low importance, you read.");
            			System.out.println("You throw the form in a nearby trash bin behind you... and you miss.");
            		}
            		
            		// add input here.
            		
            	} else {
                	System.out.println("You have already created a portfolio for Hand #100 under CodeHS Insurance Ltd."); // change 100 to something random. 
                } 
            } 
            if (move.equals("split") || move.equals("p")) { 
                int timesSplit = Hand.movesPlayed[4]; 
                if (player.splittable()) {
                	if (availableCash >= modBet) {
                		if ((timesSplit <= 3)) {
                			System.out.println("I would like to split, you say.");
                			System.out.println("To emphasize you point at one card with one hand and the other card with your other hand and then swing your fingers upward like a conductor."); // reference to hand signals in blackjack. 
                			availableCash -= modBet; // cash for the newly created hand cannot be spent. 
                			Hand.movesPlayed[4]++;
                			return move;
                		} else {
                			System.out.println("I would like to split, you say.");
                			System.out.println("By this point, your virtual table is FLOODED with cards, and you look around to find that there is no room.");
                			System.out.println("Maybe they should have bought a bigger table, because you can't split right here. Not right now.");
                		}
                	} else {
                		System.out.println("I would like to split, you say.");
                		System.out.println("Great. Now put " + modBet + " dollars in that stash next to you.");
                		System.out.println("You count your chips...");
                		System.out.println("...");
                		System.out.println("...");
                		System.out.println("Whoops. There isn't enough. If only you had 20 emergency dollars in your shoes.");
                	}
                } else {
                	System.out.println("I would like to split, you say.");
                	System.out.println("Unfortunately for you, the dealer is too observant and points out that you cannot split those cards. Better luck next time...");	
                }
            	
            }
            if (move.equals("double down") || move.equals("d")) {
            	if (player.getValue() == 9 || player.getValue() == 10 || player.getValue() == 11) {
            		System.out.println("You bend over and intently stare at the cards like your life depends on it.");
            		int maxBet = Math.min((int) Math.floor(availableCash), modBet);
            		if (maxBet == 0) {
            			System.out.println("The nagging feeling of debt works your way into your mind, and you get up, stunned.");
            		} else {
            			// get input for the bet 
            			System.out.println("OK. So you're concentrating REALLY HEAVILY on the cards, but anyone knows that you haven't truly committed until you've laid some money down.");
            			System.out.println("FLAVOR TEXT OFF: You are selecting the amount of money to raise your bet by.");
        				String pr = "Enter a number between 1 and " + maxBet + ". Type 0 to cancel.";
        				int sb = forceInputBetweenXAndY(1, maxBet, pr);
        				if (sb == 0) {
        					System.out.println("You break your concentration and for the next little while, you wonder, 'What was I thinking when I decided to initiate an anti-climactic standoff?'");
        				} else {
        					System.out.println("You double down and toss some more chips on the table.");
        					availableCash -= sb; // the side bet cannot be spent until the round is over.
        					player.setBet(modBet + sb);
            				Hand.movesPlayed[5]++;
                        	return move;
        				}
            		}
            		// get bet here
            		 
            	} else {
            		System.out.println("The dealer points out that your hand's value is not 9, 10 or 11. Guess you're going to have to triple down now.");
            	}
            	
            }
            margin(1);
            System.out.println("Invalid command."); 
            margin(1);
        }
    }
    
    /*
     * 
     * Calculates whether the player wins the insurance or not. The insurance is stored in the dealer's hand.
     * 
     * @param dealer: Looks at the dealer's hand to see if the face down card is worth 10.
     * 
     * @param sideBet: The side bet of the player. 
     * 
     * 
     * */
    
    public static void calculateInsurance(Hand dealer, int sideBet) { // stores the result of the insurnace in the dealer. 
    	// even if the side bet is successful, don't give them the money until it's over.
    	if (dealer.getCard(0).getValue() == 10) { 
    		dealer.setInsurance(2*sideBet);
    	} else {
    		dealer.setInsurance(-1*sideBet); // deduct from cash, not from availableCash. 
    	}
    }
    
    /*
     * Plays the dealer's hand and returns the dealer's new hand.
     * 
     * @param Deck: deck is used to get cards from.
     * 
     * @param dealer: modifies the dealer's hand. However, you can't modify a hand in a function unless you return it. 
     * */
    
    public static Hand dealerHand(Hand dealer, Deck deck) {
        while(true)
        {
        	Scanner enterKey = new Scanner(System.in);
            System.out.println("Dealer's hand");
            System.out.println(dealer);
            margin(1);
            if (dealer.getInsurance() > 0) {
            	System.out.println("New call from: CodeHS Insurance Ltd.");
            	System.out.println("Heard you were in trouble at the might of the dealer's hand! No worries, take some reimbursement money.");
            	// as player has nothing left to spend it on, don't need to add 3*sidebet next to availableCash. 
            	margin(1);
            	System.out.println("You were compensated: $" + (2*dealer.getInsurance()));
            	cash += dealer.getInsurance();
                System.out.println("New balance: $" + cash);
            } else if (dealer.getInsurance() < 0) {
            	System.out.println("New call from: CodeHS Insurance Ltd.");
            	System.out.println("We received a form about a blackjack dealer's hand.");
            	System.out.println("We tried our best, but we couldn't find anything related to it.");
            	System.out.println("Oh, and our company is being liquidated, and your bet has been lost to the sands of time.");
            	System.out.println("I guess you're in hot water now! Heh-heh-heh...");
            	margin(1);
            	System.out.println("You lost: $" + (-1*dealer.getInsurance()));
            	cash += dealer.getInsurance();
                System.out.println("New balance: $" + cash);
            }
            
            
            int value = dealer.getValue();
            System.out.println("Dealer's hand has value " + value);
            
            System.out.println("Enter to continue...");
            String buffer = enterKey.nextLine();
            
            
            if(value < 17)
            {
                System.out.println("Dealer hits");
                Card c = deck.deal();
                dealer.addCard(c);
                
                System.out.println("Dealer card was " + c);
                
                if(dealer.busted())
                {
                    System.out.println("Dealer busted! What a fail...");
                    margin(1);
                    break;
                }
            }
            else
            {
                System.out.println("Dealer stands.");
                margin(1);
                break;
            }
        }
        return dealer;
    }
    // figures out how much the player wins/loses against the dealer.
    /*
     * @param: player's hand and dealer's hand, to be compared against each other. 
     * */
    public static double findWinner(Hand player, Hand dealer) { 
    	int pBet = player.getBet();
    	if (player.getOutcome().equals("busted")) {
    		System.out.println("Your hand busted and lost.");
    		return -1*pBet;
    	} else if (player.getOutcome().equals("surrendered")) {
    		System.out.println("Your hand lost via surrender.");
    		return -0.5*pBet; 
    	} else if (player.getOutcome().equals("charles")) {
    		System.out.println("Your hand won via The Charles Act.");
    		return pBet; 
    	} else if (dealer.busted()){ // not sure why there are two busted indicators here.
    		return pBet;
    	} else if (player.hasBlackjack()) {
    		if (dealer.hasBlackjack()) {
    			System.out.println("It's a STANDOFF!");
    			System.out.println("You push...");
    			return 0;
    		} else {
    			System.out.println("Your hand won. 1.5X BLACKJACK BONUS! ");
    			return Math.min((1000000000 - cash), 1.5*pBet); // blackjacks give 150%. Theoretically, if you walked in with $999,999,999 this could cause integer overflow, but I fixed it here.
    		}
    	} else if (player.getValue() == 21) {
    		if (dealer.hasBlackjack()) {
    			System.out.println("Your hand lost.");
    			return -1*pBet;
    		} else if (dealer.getValue() == 21) {
    			System.out.println("It's a STANDOFF!");
    			System.out.println("You push...");
    			return 0;
    		} else {
    			return pBet;
    		}
    	} else { // etc hand 
    		if (player.getValue() == dealer.getValue()) {
    			System.out.println("It's a STANDOFF!");
    			System.out.println("You push...");
    			return 0;
    		} else if (player.getValue() > dealer.getValue()) {
    			System.out.println("Your hand won.");
    			return pBet;
    		} else {
    			System.out.println("Your hand lost.");
    			return -1*pBet;
    		}
    	}
    	/* 
    	 * Etc is a stood hand that is less than 21.  
    	 * 
    	 * Player statuses: Blackjack, 21, 5-Card Charlie, Etc, Surrendered Loss, Busted Loss (P1, P2, P3...)
    	 * Dealer statuses: Blackjack, 21, Etc, Busted Loss (D1, D2, D3...)
    	 * 
    	 * P1 pushes on D1, beats D2, beats D3, beats D4, 1.5x the reward
    	 * P2 loses to D1, pushes on D2, beats D3, beats D4
    	 * P3 wins against everything 
    	 * P4 loses to D1, loses to D2, depends on D3 and beats D4
    	 * P5 loses to everything but only half the penalty
    	 * P6 loses to everything 
    	 * */
    	
    }
    
    /*
     * Plays a full complete round. Doesn't take variables because it generates most of them inside playRound().
     * 
     * 
     * */
    public static void playRound() {
    	
    	// reset variables
    	availableCash = cash; 
    	remainingHands = new ArrayList<Hand>(); 
    	playerHands = new ArrayList<Hand>(); 
    	for (int i = 0; i < 6; i++) {
    		Hand.movesPlayed[i] = 0; // Java syntax insisted I didn't use {0, 0, 0, 0, 0, 0}. 
    	}
    	
    	
    	Scanner betReader = new Scanner(System.in);
    	int bet = -1; 
        while (true) {
        	System.out.println("What is your bet? (Positive integers only)");
        	try {
        		bet = betReader.nextInt();
                if (bet <= -1) {
                    System.out.println("You cannot bet a negative amount.");
                    // I left 0 values open to debug. 
                } else if (bet > cash) {
                    System.out.println("You're spending too much!"); 
                } else if (bet == 0) {
                	Debugger d = (Debugger) gadgets.get(0);
                	if (d.status) {
                		System.out.println("[DEBUG MODE ON] You plug in the Debugger, and your zero dollar spending somehow does not cause any commotion.");
                		break;
                	} else {
                		// basically with the debugger turned off you can't bet $0. I made a story here just to make it fun.
                		System.out.println("You take a look at your wallet, and then throw a heaping of air onto your pile.");
                		System.out.println("The dealer pulls out the rulebook and says,");
                		System.out.println("At CodeHS Casinos, it is MANDATORY to bet money.");
                		System.out.println("What are you talking about? I put down money right here!");
                		System.out.println("You think you can fool me with that?");
                		System.out.println("You take out your debugger and investigate, but problem is, it's off. And it takes 30 minutes to recharge, so there's only so much you can do here, but...");
                		System.out.println("Look, I was just kidding! I do have some money here.");
                		System.out.println("OK. Quit horsing around and tell us...");
                	}
                } else {
                    break; 
                }
        	} catch (InputMismatchException ime) {
        		System.out.println("The input does not appear to be a positive integer.");
        		betReader.next();
        	}
        }
        int baseBet = bet; // used for all split hands, might want to add to Hand class.
        availableCash = cash - bet; // the cash you have remaining.
        
        Deck deck;
        Debugger d = (Debugger) gadgets.get(0);
        if (d.status && d.d1.deckSize() != 0) {
        	System.out.println("The dealer takes out a fresh BICYCLE and adds it to the shoe.");
        	System.out.println("We are not sp-");
        	System.out.println("Your debugger flashes a bright baby blue. Instinctively, you point it at the deck.");
        	System.out.println("A blue bolt of lightning hits the deck and the cards change. Man, that looks cool.");
        	System.out.println("How the dealer didn't see that, I don't know.");
        	deck = new Deck(d.d1); // not working, creates a copy. 
        	d.d1.clearDeck();
        } else {
        	System.out.println("The dealer takes out a fresh BICYCLE and adds it to the shoe.");
        	System.out.println("We are not sponsored, though.");
        	deck = new Deck();
            deck.shuffle();
        }
        margin(1);
        
        remainingHands.add(generatePlayerHand(deck, "2R")); 
        Hand dealer = generateDealerHand(deck);
               
        System.out.println("Dealer's hand: ");
        dealer.printDealerHand(); 
        // System.out.println(dealer)
        
        int i = 0;
        while (true) { // remainingHands's size might change, so I don't want to risk it with a traditional for loop
        	if (i == (remainingHands.size())) {
        		break;
        	}
        	Hand subj = remainingHands.get(i);
        	subj.setBet(bet);
        	
        	// print out player's hand
        	System.out.println("Your hand: ");
        	System.out.println(subj);
        	
        	 // put outcome inside the hand, return the hand
        	playerHands.add(playerHand(deck, subj, dealer, bet));
        	i++;
        }
        // figure out whether the dealer needs to play. 
        boolean checkNecessity = false; 
        double totalChange = 0.0;
        for (int j = 0; j < playerHands.size(); j++) {
        	if (playerHands.get(j).getOutcome().equals("stood")) {
        		checkNecessity = true; 
        	}
        }
        if (Hand.movesPlayed[3] != 0) {
        	checkNecessity = true; 
        }
        if (checkNecessity) {
        	// even if all hands are Charles or bust or surrender still need to factor it in the winning algorithm in case the player played insurance.
        	dealer = dealerHand(dealer, deck);
            for (int j = 0; j < playerHands.size(); j++) {
            	Hand h = playerHands.get(j);
            	System.out.println("Your hand: " + h);
            	double change = findWinner(h, dealer);
            	totalChange += change;
            	if (change > 0) {
            		System.out.println("You won: " + change);
            	} else if (change == 0) {
            		System.out.println("Your cash stayed the same.");
            	} else {
            		System.out.println("You lost: " + (-1*change));
            	}
            }
        } else {
        	System.out.println("Dealer does not play as all of your hands have automatically won/lost.");
        	for (int j = 0; j < playerHands.size(); j++) {
        		
        		Hand h = playerHands.get(j);
        		System.out.println("Your hand: " + h);
            	if (h.getOutcome().equals("surrendered")) {
            		totalChange -= (0.5*h.getBet());
            		System.out.println("Your hand lost via surrender.");
            		System.out.println("You lost " + 0.5*h.getBet());
            	} else if (h.getOutcome().equals("charles")) {
            		totalChange += (h.getBet());
            		System.out.println("Your hand won via The Charles Act.");
            		System.out.println("You gained " + h.getBet());
            	} else {
            		totalChange -= (h.getBet());
            		System.out.println("Your hand busted and lost.");
            		System.out.println("You lost " + h.getBet());
            	}
            }
        }
        margin(1);
        cash += totalChange;
        System.out.println("New balance: $" + cash);
        margin(1);
        // ask player if they want to play again, and checks the legality of doing so.
        System.out.println("Would you like to play again or go back to the lounge? Type N to go to the lounge and anything else to play again");
        Scanner sc = new Scanner(System.in);
        String playAgain = sc.nextLine();
        if (playAgain.equalsIgnoreCase("N") || playAgain.equalsIgnoreCase("No")) {
        	 lounge();
        } else {
        	if (cash == 0) {
        		margin(1);
        		System.out.println("You play it again, and open your wallet for more chips. Unfortunately, nothing comes up after fishing for 30 seconds. You turn your wallet upside down, and...");
        		System.out.println("AN EMERGENCY 5 DOLLARS!"); // gadget idea: extra money upon death in story mode 
        		System.out.println("Just kidding... sand pours out, as well as your hopes and dreams.");
        		System.out.println("You slink away from the gambling hall in despair.");
        		margin(1);
        		 lounge();
        	} else if (cash >= 1000000000) {
        		System.out.println("You search for the nearest gambling table.");
        		System.out.println("Suddenly, alarm bells start ringing off.");
        		System.out.println("A bunch of people in suits walk around frantically, taking random things from the gambling hall while talking about bankruptcy.");
        		System.out.println("We lost almost a billion dollars!, one of them cried. 'Who did it?'");
        		System.out.println("You decide it's best to leave.");
        		margin(1);
        		lounge();
        	} else {
        		playRound();
        	}
        }
        margin(2);
    }
    
    
    public static void main (String[] args) {
    	Scanner enterKey = new Scanner(System.in);
    	System.out.println("CodeHS Casinos' Blackjack");
    	System.out.println("BY: AVERY CHAN");
    	System.out.println();
    	introMessage(enterKey);
    }
    
	
}

/*
 * Test cases to try out:
 * 1. The split
 * 2. Insurance + bust
 * 3. 
 * Non blackjack 21 after split (turn on debugger and rearrange the deck in order: JC, JD, 9H, 7H, AS, AD, 5H)
 * 4. Blackjack vs 21
 * 
 * 
 * */
