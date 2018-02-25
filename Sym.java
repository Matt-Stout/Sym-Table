/**
 * Author:		Matt Stout
 * Email:		mcstout@wisc.edu
 * Class:		CS 536
 * Assignment:	P1 Part 1
 */

public class Sym {

	public String type;
	
	/**
	 * Sym constructor method
	 * 
	 * @param type
	 */
	public Sym(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
	public String toString() {
		return this.type;
	}
}