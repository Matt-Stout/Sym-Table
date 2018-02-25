/**
 * Author:		Matt Stout
 * Email:		mcstout@wisc.edu
 * Class:		CS 536
 * Assignment:	P1 Part 1
 */

public class P1 {

	public static void main(String[] args) {
		// Test Sym.java class
		testSym();
				
		// Test SymTable.java class
		testSymTable();
	}
	
	/**
	 * Test the functionality of the SymTable class, which includes the following operations:
	 * 		no-arg constructor					--	create an empty SymTable
	 * 		void addDecl(String name, Sym sym)	--	if the SymTable list is not empty,
	 * 												neither 'name' nor 'sym' are null,
	 * 												and the first HashMap does not already
	 * 												contain the given name as a key, then
	 * 												this adds the given 'name' and 'sym'
	 * 												to the first HashMap in the list
	 * 		void addScope()						--	adds a new, empty HashMap to the
	 * 												front of the list
	 * 		Sym lookupLocal(String name)		--	if the SymTable list is not empty,
	 * 												looks if the first HashMap in the list
	 * 												contains 'name' as a key and returns
	 * 												the associated Sym, otherwise it
	 * 												returns null
	 * 		Sym lookupGlobal(String name)		--	if the SymTable list is not empty,
	 * 												this searches the list for any HashMap
	 * 												that contains 'name' as the key and
	 * 												returns the first associated Sym,
	 * 												or else returns null
	 * 		void removeScope()					--	if the SymTable list is not empty,
	 * 												this removes the HashMap value from
	 * 												the front of the list
	 * 		void print()						--	prints the values held with the
	 * 												current SymTable list
	 * 
	 * This method tests all of the above operations, testing both correct and incorrect
	 * implementations of the operations. It will display nothing if all tests pass.
	 */
	private static void testSymTable() {
		SymTable table = new SymTable();
		
		testEmptyTable(table);
		
		createTestTable(table);
		
		testDupEntryandWrongArgument(table);
		
		testLookupMethods(table);
		
		testPrintMethod(table);
		
		// TODO: add something to test the WrongArgumentException for addDecl() method
		//		 and finish the testPrintMethod test method
	}
	
	private static void testPrintMethod(SymTable table) {
		
	}
	
	private static void testLookupMethods(SymTable table) {
		Sym sym;
		// test the lookupLocal method
		try {
			// test on first item in list
			sym = table.lookupLocal("name1");
			if (sym != null)
				if (!sym.getType().equals("boolean"))
					System.out.println("lookupLocal test failed: did not return expected value. Returned " + sym.getType().toString() );
			
			// test further down the list
			sym = table.lookupLocal("name5");
			if (sym != null)
				if (!sym.getType().equals("boolean"))
					System.out.println("lookupLocal test failed: did not return expected value. Returned " + sym.getType().toString() );
			
			
			// test on an empty scope
			table.addScope();
			sym = table.lookupLocal("name5");
			if (sym != null)
				if (!sym.getType().equals("boolean"))
					System.out.println("lookupLocal test failed: did not return expected value. Returned " + sym.getType().toString() );
			table.removeScope(); //return table to previous state
		}
		catch (EmptySymTableException ex) {
			System.out.println("lookupLocal test failed: threw an EmptySymTableException");
		}
		
		// test the lookupGlobal method
		try {
			sym = table.lookupGlobal("name1");
			if (sym != null) {
				if (!sym.getType().equals("boolean"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			
			sym = table.lookupGlobal("name2");
			if (sym != null) {
				if (!sym.getType().equals("string"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			
			sym = table.lookupGlobal("name3");
			if (sym != null) {
				if (!sym.getType().equals("integer"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			
			sym = table.lookupGlobal("name4");
			if (sym != null) {
				if (!sym.getType().equals("double"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			
			sym = table.lookupGlobal("name5");
			if (sym != null) {
				if (!sym.getType().equals("long"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			
			// test on an empty scope
			table.addScope();
			sym = table.lookupGlobal("name5");
			if (sym != null) {
				if (!sym.getType().equals("long"))
					System.out.println("lookupGlobal test failed: did not return expected value. Returned " + sym.getType() );
			}
			table.removeScope(); //return table to previous state
		}
		catch (EmptySymTableException ex) {
			System.out.println("lookupGlobal test failed: threw an EmptySymTableException");
		}
		
	}
	
	private static void testDupEntryandWrongArgument(SymTable table) {
		// Test duplicate entry
		try {
			table.addDecl("name5", new Sym("long"));
			System.out.println("addDecl with a duplicate entry test failed: did not throw a DuplicateSymException");
		}
		catch (DuplicateSymException ex) {}
		catch (EmptySymTableException ex) {
			System.out.println("addDecl with a duplicate entry test failed: threw an EmptySymTableException");
		}
		catch (WrongArgumentException ex) {
			System.out.println("addDecl with a duplicate entry test failed: threw an WrongArgumentException");
		}
		
		// Test Wrong Argument with null name argument
		try {
			table.addDecl(null, new Sym("long"));
			System.out.println("addDecl with a wrong argument test failed: did not throw a WrongArgumentException");
		}
		catch (DuplicateSymException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an DuplicateSymException");
		}
		catch (EmptySymTableException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an EmptySymTableException");
		}
		catch (WrongArgumentException ex) {} //test passed
		
		// Test Wrong Argument with null sym argument
		try {
			table.addDecl("name5", null);
			System.out.println("addDecl with a wrong argument test failed: did not throw a WrongArgumentException");
		}
		catch (DuplicateSymException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an DuplicateSymException");
		}
		catch (EmptySymTableException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an EmptySymTableException");
		}
		catch (WrongArgumentException ex) {} //test passed
		
		// Test Wrong Argument with null name and sym arguments
		try {
			table.addDecl(null, null);
			System.out.println("addDecl with a wrong argument test failed: did not throw a WrongArgumentException");
		}
		catch (DuplicateSymException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an DuplicateSymException");
		}
		catch (EmptySymTableException ex) {
			System.out.println("addDecl with a wrong argument test failed: threw an EmptySymTableException");
		}
		catch (WrongArgumentException ex) {} //test passed
	}
	
	private static SymTable createTestTable(SymTable table) {
		try {
			table.addScope();
			table.addDecl("name1", new Sym("boolean"));
			table.addScope();
			table.addDecl("name2", new Sym("string"));
			table.addScope();
			table.addDecl("name3", new Sym("integer"));
			table.addScope();
			table.addDecl("name4", new Sym("double"));
			table.addScope();
			table.addDecl("name5", new Sym("long"));
		}
		catch (DuplicateSymException ex) {
			System.out.println("addDecl test failed: threw an unexpected DuplicateSymException");
		}
		catch (EmptySymTableException ex) {
			System.out.println("addDecl test failed: threw an unexpected EmptySymTableException");
		}
		catch (WrongArgumentException ex) {
			System.out.println("addDecl with a duplicate entry test failed: threw an WrongArgumentException");
		}
		
		return table;
	}
	
	private static void testEmptyTable(SymTable table) {
		// Test the removeScope method on an empty table
		try {
			table.removeScope();
			System.out.println("removeScope on empty table test failed");
		} catch (EmptySymTableException ex) {}
		
		// Test the addDecl method on an empty table
		try {
			table.addDecl("Name", new Sym("type"));
			System.out.println("addDecl on empty table test failed");
		} catch (EmptySymTableException ex) {} 
		catch (DuplicateSymException ex) {
			System.out.println("addDecl with a duplicate entry test failed: threw an DuplicateSymException");
		}
		catch (WrongArgumentException ex) {
			System.out.println("addDecl with a duplicate entry test failed: threw an WrongArgumentException");
		}
		
		// Test the lookupLocal method on an empty table
		try {
			table.lookupLocal("Name");
			System.out.println("lookupLocal on empty table test failed");
		} catch (EmptySymTableException ex) {}
		
		// Test the lookupGlobal method on an empty table
		try {
			table.lookupGlobal("Name");
			System.out.println("lookupGlobal on empty table test failed");
		} catch (EmptySymTableException ex) {}
		
		// Test the addScope() method on an empty table
		table.addScope();
		if (table.list.size() != 1)
			System.out.println("addScope test failed");
		
		// Test removeScope() method on a table with an empty scope
		try {
			table.removeScope();
			if (table.list.size() != 0)
				System.out.println("removeScope test failed");
		}
		catch (EmptySymTableException ex) {
			System.out.println("removeScope with an empty scope test failed: threw an EmptySymTableException");
		}
	}
	
	/**
	 * Test the functionality of the Sym class, which includes the following operations:
	 * 		String arg constructor		--	create a Sym object assigning the String
	 * 										argument to be that Sym's "type"
	 * 		getType()					--	returns the String value of this Sym's "type"
	 * 		toString()					--	returns the String value of this Sym's "type"
	 * 
	 * This method tests all of the above operations, testing both correct and incorrect
	 * implementations of the operations. It will display nothing if all tests pass.
	 */
	private static void testSym() {
		// Create Sym objects to test
		String integer = "int";
		Sym sym1 = new Sym(integer);
		String bool = "boolean";
		Sym sym2 = new Sym(bool);
		String number = "double";
		Sym sym3 = new Sym(number);
		String word = "String";
		Sym sym4 = new Sym(word);
		String letter = "char";
		Sym sym5 = new Sym(letter);
		
		getTypeTest(sym1, sym2, sym3, sym4, sym5);
		
		toStringTest(sym1, sym2, sym3, sym4, sym5);
	}
	
	private static void getTypeTest(Sym sym1, Sym sym2, Sym sym3, Sym sym4, Sym sym5) {
		if (!sym1.getType().equals("int")) 
			System.out.println("Sym1 failed");
		if (!sym2.getType().equals("boolean")) 
			System.out.println("Sym2 failed");
		if (!sym3.getType().equals("double")) 
			System.out.println("Sym3 failed");
		if (!sym4.getType().equals("String")) 
			System.out.println("Sym4 failed");
		if (!sym5.getType().equals("char")) 
			System.out.println("Sym5 failed");
	}
	
	private static void toStringTest(Sym sym1, Sym sym2, Sym sym3, Sym sym4, Sym sym5) {
		if (!sym1.getType().equals("int")) 
			System.out.println("Sym1 failed");
		if (!sym2.getType().equals("boolean")) 
			System.out.println("Sym2 failed");
		if (!sym3.getType().equals("double")) 
			System.out.println("Sym3 failed");
		if (!sym4.getType().equals("String")) 
			System.out.println("Sym4 failed");
		if (!sym5.getType().equals("char")) 
			System.out.println("Sym5 failed");
	}
}
