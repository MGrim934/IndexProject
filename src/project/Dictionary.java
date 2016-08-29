package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;



public class Dictionary implements Index {
	private Map<String, String> dictionary = new HashMap<String, String>();

	public Dictionary() {
	}
	
	public Map<String, String> getDictionary(){
		return dictionary;
		
	}

	@Override
	public void parse() throws IOException {

		//int count = 0;
		// test code to ensure it is grabbing as many words as possible

		// dictionary file is a csv
		// one line contains 3 elements
		// Word, wordtype and definition
		// some words have multiple definitions

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("dictionary.csv")));
		// more efficient than reading from a Scanner
		String line = null;

		StringBuilder text = new StringBuilder();
		// for the text that will be pushed to the dictionary
		StringBuilder update = new StringBuilder();
		// for when the dictionary wants to add another definition to a word
		// that is already
		// in the dictionary
		// get the current string
		// append
		// push the update as a string and replace

		// using a StringBuilder
		// not synchronized but faster than StringBuffer solution
		// allows one to append strings
		// better than just "changing" a string and creating a new object

		// need to get rid of the first line since it just describes the
		// structure
		br.readLine();

		// now the ACTUAL first line, append on to the empty string builder
		// handle potential error
		line = br.readLine();
		text.append(line);

		while ((line = br.readLine()) != null) {
			// System.out.println(line.length());
			if (line.substring((line.length() - 2)).equals(".\"")) {

				// this means we are going to push something so
				text.append(line);
				int indexOfComma = text.indexOf(",\"");
				// get the index of the end of the first word

				// get the word from the line
				// change it to lower case so when a user searches it will match
				// no matter what
				// decided to make all keys lower case
				// change the first letter to uppercase when printing out with
				// sysout

				String word = text.substring(1, indexOfComma - 1).toLowerCase();
				// test
				//	count++;

				// make both the key lower case
				// when searching against a text file, make the text file lower
				// case too!
				// same with user search
				// otherwise
				// alternatively
				// create a wrapper for the string and override the hashmap
				// equals() and hashCode()
				// or use a TreeMap with a case insensitive comparator?

				// we need to check multiple things
				// is the word in the map
				if (this.dictionary.containsKey(word)) {
					// containsKey is a constant time operation
					// O(1)

					// the word is there
					// we need to get the value
					// put in the additional definition
					// and update the map

					update.setLength(0);
					update.trimToSize();
					// clear the string builder if there is something in it
					update.append(this.dictionary.get(word));
					// This returns the object value through the key
					// constant time operation
					// O(1)
					// now add a new line character
					String newline = System.lineSeparator();
					// os specific line separator
					update.append(newline);
					// add the seperator

					update.append(text);
					// add the new definition
					this.dictionary.put(word, update.toString());
					// replaces the old value
					// key must be unique after all
					// putting a value in the map is constant time
					// O(1)

				} else {

					this.dictionary.put(word, text.toString());
					// put a new key/value into the map!
					// Constant time
					// O(1)

				} // else

				// System.out.println("Word: "+word+ " Definition: ");
				// System.out.println(m.get(word));
				// test to make sure that the words were being taken in
				// correctly

				// either pushed a new key and value
				// or updated an existing one
				// so the stringBuilder can be cleared for new input

				text.setLength(0);
				text.trimToSize();
				// clear the stringBuilders buffer with trim after setting
				// length to 0

			} else {
				text.append(line);
				// this runs if we want to append to the a string
				// if the string doesn't end in ."
				// so we just want to append
				// not clear
				// and keep going to the next iteration
				// because it is the end of the line in the file
				// but not the "true" end of the line

			}

		} // while
			// fix thing

		br.close();
		// close the stream
		//System.out.println("Dictionary Words: " + count);
		// testing the word count
		// final map size will vary from actual word count
		// due to it concatonating definitions of duplicate entries with
		// different definitions

	}

	@Override
	public void clearIndex() {
		dictionary.clear();

	}

	@Override
	public void displayAll() {
		
		for(Map.Entry<String,String> entry: dictionary.entrySet()){
			System.out.println("Word: "+entry.getKey());
			System.out.println("Definition");
			System.out.println(entry.getValue());
			System.out.println("===============================");
			
			
		}//for

	}

}
