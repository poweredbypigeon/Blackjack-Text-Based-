package Blackjack;

public class Gadget {

	int id;
	int level = 1; 
	final String[] names = {"Debugger"}; // basically with id x, the name is the x'th in the list (Debugger is id 0)
	final String[] descriptions = {"A piece of ancient technology. With this you can change your money, rearrange the deck and reveal valuable information about the game."};
	String name;
	String description; 
	boolean status = true; // 0/false - off, 1/true - on
	
	public Gadget(int otherID) {
		id = otherID;
		name = names[id];
		description = descriptions[id];
	}
	
	/*
	 * Prints a short description of a gadget. Takes no parameters and uses instance variables only.
	 * */
	
	public void printIntroduction() {
		// a field that shows up once revealed.
		System.out.println(name);
		if (status) {
			System.out.println("STATUS: ON");
		} else {
			System.out.println("STATUS: OFF");
		}
		System.out.println();
		System.out.println(description);
		System.out.println();
	}
	

}
