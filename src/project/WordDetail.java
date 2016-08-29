package project;


import java.util.HashSet;

import java.util.Set;
import java.util.TreeSet;

public class WordDetail {
	
	private String definition;
	private Set <Integer> pages = new HashSet<Integer>();
	//all indexes added should be unique
	//Must be unique in a set
	//HashSet faster than Treeset

	//must use wrapper class of "Integer" as list deals with objects

	public WordDetail() {
		//empty default constructor
	}
	
	public WordDetail(String definition){
		this.setDefinition(definition);
		//sets the definition
		
		
	}
	//if its just a page
	public WordDetail(int page){
		addIndex(page);
		//calls addIndex Method
	}
	//Getters and Setters
	//================================

	public String getDefinition() {
		return definition;
		//constant time operation
	
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	//addIndex
	public void addIndex(int page){
		//adds a page number to the pages!
		//through the magic of autoboxing - int will convert to Integer
		//adding a page should be constant time - O(1)(Hashset)
		this.pages.add(page);
		
	}//addIndex
	
	public Set<Integer> getIndices(){
		//create a TreeSet
		Set<Integer> pageList = new TreeSet<Integer>(this.pages);
		//TreeSet should sort the list of Integers correctly!
		//adding to a TreeSet is O(log (n))
		//Nature of trees means they need to be sorted
		//just uses the default comparator for integers
				
		return pageList;
		//returns a newly created object
		//So they have a copy of the pages, but cannot change the actual TreeSet
	
			
	}//getIndices


}
