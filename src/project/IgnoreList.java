package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class IgnoreList implements Index {
	private Set <String> ignore = new HashSet<String>();
	//contains the ignore words

	public IgnoreList() {
	}
	
	public Set<String> getIgnoreList(){
		return ignore;
		
	}

	@Override
	public void parse() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("stopwords.txt")));
		String line = null;

		while ((line = br.readLine()) != null) {
			// while there is another line
			// dont need to split this string, since each word of ignore file is
			// one word
			// fill each string with the word
			// should read in words one by one due to the nature of the ignore
			// file
			this.ignore.add(line);
			// adding to a hashSet is constant time
			// O(1)
			// no need to check if its already there. If it is there, the set
			// just returns false
			// nothing is changed

		}

		br.close();

	}

	@Override
	public void clearIndex() {
		ignore.clear();

	}

	@Override
	public void displayAll() {
		for(String s: ignore){
			System.out.println(s);
		}
		
	

	}

}
