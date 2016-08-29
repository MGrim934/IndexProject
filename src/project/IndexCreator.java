package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.WordDetail;

public class IndexCreator implements Index {
	private Dictionary dictionary = new Dictionary();
	private IgnoreList ignore = new IgnoreList();
	private Map<String, WordDetail> map = new HashMap<String, WordDetail>();
	String fileName;

	public IndexCreator() {
		fillDictionaryAndIgnoreList();
	}

	public IndexCreator(String fileName) {
		setFileName(fileName);
		fillDictionaryAndIgnoreList();

	}

	public void fillDictionaryAndIgnoreList() {
		try {
			dictionary.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ignore.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void parse() throws IOException {

		// this method parses the text file

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		// more efficient than a scanner
		String line = null;

		int page = 1;
		// first page of the text file!
		int lineCounter = 0;

		while ((line = br.readLine()) != null) {
			lineCounter++;
			// each time there is a line!
			if (lineCounter % 40 == 0) {
				// if there are 40 lines in the page
				// new page
				page++;
				// adds to the pages every 40 lines
			}
			String[] words = line.split(" ");
			// splits the line into an array of strings based on the " " space
			for (int i = 0; i < words.length; i++) {
				// for every word in the line
				words[i] = words[i].toLowerCase();
				// change the word to lower case
				// this allows easier searching against the other map
				if (words[i].length() > 1) {
					// need to put this in to stop errors
					// cant take away from a one char word
					if (words[i].charAt((words[i].length() - 1)) == '.'
							|| words[i].charAt((words[i].length() - 1)) == ','
							|| words[i].charAt((words[i].length() - 1)) == '?'
							|| words[i].charAt((words[i].length() - 1)) == '\"'
							|| words[i].charAt((words[i].length() - 1)) == ';'
							|| words[i].charAt((words[i].length() - 1)) == ':'|| 
							words[i].charAt((words[i].length() - 1)) == '!') {
						

						words[i] = words[i].substring(0, words[i].length() - 1);
						// if there is a full stop or comma, question mark etc. at
						// the end of a word. We must remove it!
						// otherwise the indices won't be updated properly

					}
				}

				if (ignore.getIgnoreList().contains(words[i])) {
					// hashset contains should be O(1)
					// if this word is an ignore case
					continue;
					// move onto the next iteration of the loop
				} // if

				if (this.map.containsKey(words[i])) {
					// hashmap containsKey is constant time
					// O(1)
					WordDetail wd = this.map.get(words[i]);
					// get is constant time
					// O(1)

					// doesn't create a new space in memory
					// references the map's value
					wd.addIndex(page);
					// adding to hashset is constant time
					// O(1)
					// actually adding to the maps value
				} else {
					WordDetail wd = new WordDetail(page);
					// creates a new WordDetail and adds the current page to the
					// indices
					// has no definition yet!
					this.map.put(words[i], wd);
					// constant time operation
					// O(1)
					// puts it in the map!
					String definition = dictionary.getDictionary().get(words[i]);
					// get is constant time O(1)

					// should get the definition value from the dictionary map
					if (definition != null) {
						// if the definition isn't null
						// set the definition as the definition that was pulled
						// from the dictionary map

						wd.setDefinition(definition);

					}
				} // else
			} // for
		} // while

		br.close();
		// close the stream

	}// parse

	@Override
	public void clearIndex() {
		map.clear();

	}

	@Override
	public void displayAll() {

		for (Map.Entry<String, WordDetail> entry : map.entrySet()) {

			String formatWord;
			if (entry.getKey().length() > 1) {
				formatWord = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
				System.out.println("Word: " + formatWord);
			} else {
				System.out.println("Word: " + entry.getKey());
			}

			System.out.println("===============================");
			System.out.println("Details: ");
			System.out.println("Definition: ");
			if (entry.getValue().getDefinition() != null) {
				System.out.println(entry.getValue().getDefinition());
			} else {
				System.out.println("No Definition");
			}
			System.out.println("Pages: ");
			System.out.println(entry.getValue().getIndices());
			System.out.println("===============================");

		} // for

	}// displayAll

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void parseFile(String fileName) {
		setFileName(fileName);
		try {
			parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lookUpIndex(String word) {
		// looks up whether a word appears in the index

		if (this.map.containsKey(word)) {
			// containsKey is constant time operation
			// O(1)

			// print as specified
			String formatWord = word.substring(0, 1).toUpperCase() + word.substring(1);
			// decided to only format words when displaying
			// the parsing method doesn't have to worry about this

			System.out.println("Word: " + formatWord);
			System.out.println("===============================");
			System.out.println("Details: ");
			System.out.println("Definition: ");
			System.out.println(this.map.get(word).getDefinition());
			System.out.println("Pages: ");
			System.out.println(this.map.get(word).getIndices());

		} else {
			// sorry the word aint there
			System.out.println("Word: " + word + " is not in the index");
		}

	}// lookUpIndex

	public void displayDetails() {

		System.out.println("Map contains: " + this.map.size() + " entries");
		// shows the number of words in the map
		System.out.println("Dictionary contains: " + this.dictionary.getDictionary().size() + " entries.");
		System.out.println("Ignore List contains: " + this.ignore.getIgnoreList().size());
		// shows the number of words in the dictionary. Slightly altered because
		// it does not count ignore words
		// instead of a new entry for multiple instances of a word
		// concats the subsequent definitions to the one word

	}// displayDetails

	void displayMap() {
		List<String> sortedIndex = new ArrayList<String>(map.keySet());
		Collections.sort(sortedIndex);

		for (String s : sortedIndex) {
			String formatWord;
			if (s.length() > 1) {
				formatWord = s.substring(0, 1).toUpperCase() + s.substring(1);
				System.out.println("Word: " + formatWord);
			} else {
				System.out.println("Word: " + s);
			}

			System.out.println("===============================");
			System.out.println("Details: ");
			System.out.println("Definition: ");
			if (map.get(s).getDefinition() != null) {
				System.out.println(map.get(s).getDefinition());
			} else {
				System.out.println("No Definition");
			}
			System.out.println("Pages: ");
			System.out.println(map.get(s).getIndices());
			System.out.println("===============================");

		} // for

	}
	
	void displayDictionary(){
		dictionary.displayAll();
	}
	void displayIgnoreList(){
		ignore.displayAll();
	}

}
