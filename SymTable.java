/**
 * Author:		Matt Stout
 * Email:		mcstout@wisc.edu
 * Class:		CS 536
 * Assignment:	P1 Part 1
 */

import java.util.*;
public class SymTable {

	public LinkedList<HashMap<String, Sym>> list;
	
	/**
	 * SymTable constructor method
	 */
	public SymTable() {
		this.list = new LinkedList<HashMap<String, Sym>>();
		this.list.add(new HashMap<String, Sym>());
	}
	
	public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException, WrongArgumentException {
		// Check that the sym table is not empty
		if (list.size() == 0) throw new EmptySymTableException();
		
		// Check that neither argument is null
		if (name == null){
			if (sym == (null) || sym.type == null)	throw new WrongArgumentException("Arguments name and sym are null.");
			else									throw new WrongArgumentException("Argument name is null.");
		}
		else if (sym == null || sym.type == null)	throw new WrongArgumentException("Argument sym is null.");
		
		// Check if the name is a duplicate to the existing key
		if (list.getFirst().containsKey(name)) throw new DuplicateSymException();
		else list.getFirst().put(name, sym);
	}
	
	public void addScope() {
		HashMap<String, Sym> scope = new HashMap<String, Sym>();
		this.list.addFirst(scope);
	}
	
	public Sym lookupLocal (String name) throws EmptySymTableException {
		if (list.size() == 0) throw new EmptySymTableException();
		
		if (list.getFirst().containsKey(name))
			return list.getFirst().get(name);
		else
			return null;
	}
	
	public Sym lookupGlobal (String name) throws EmptySymTableException {
		if (list.size() == 0) throw new EmptySymTableException();
		
		Iterator<HashMap<String,Sym>> itr = list.iterator();
		while (itr.hasNext()) {
			HashMap<String,Sym> map = itr.next();
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}
		return null;
	}
	
	public void removeScope() throws EmptySymTableException {
		if (list.size() == 0) throw new EmptySymTableException();
		
		list.remove(list.getFirst());
	}
	
	public void print() {
		System.out.println("\n=== Sym Table ===");
		
		Iterator<HashMap<String,Sym>> itr = list.iterator();
		while (itr.hasNext())
			System.out.println(itr.next().toString());
		
		System.out.println();
	}
}