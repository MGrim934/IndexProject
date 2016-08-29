package project;


import java.io.*;

public class Main {

	// Mark Grimes g00289916
	// Java Indexing and Dictionary API
	// Main class
	// this allows the user to interact with the index class
	// which stores the dictionary, text file and ignore list

	public static void main(String[] args) {
		int choice;
		boolean quit = false;
		//long startTime;

		UserControl menu = new UserControl();
		IndexCreator index = new IndexCreator();

		boolean parsed = false;

		// menu for the users
		do {

			do {
				choice = menu.chooseParse();
				System.out.println(choice);
				String fileChoice;
				switch (choice) {
				case 1:
					fileChoice = "DeBelloGallico.txt";
					index.parseFile(fileChoice);
					parsed = true;
		
					break;
				case 2:
					fileChoice = "PoblachtNaHeireann.txt";
					index.parseFile(fileChoice);
					parsed = true;
		
					break;
				case 3:
					fileChoice = "WarAndPeace-LeoTolstoy.txt";
					index.parseFile(fileChoice);
					parsed = true;
					break;
				case 4:
					fileChoice = menu.whatFile();

					File fileTest = new File(fileChoice);
					boolean existsYN = fileTest.isFile();
					// this tests that the file exists
					// it does this before passing it to a method
					// prevents errors
					if (existsYN) {
						index.parseFile(fileChoice);
						parsed = true;
					} else {
						System.out.println(fileChoice + " File Does Not Exist");
					}
					break;
				case 0:
					System.out.println("Cancelling");

					quit = true;
					parsed = false;

					break;

				default:
					System.out.println("Error");
					break;
				}// switch
			} while (parsed == false && quit == false);
			// keep this loop going until they've parsed something
			// search the map
			String searchThis;
			if (parsed == true) {
				do {
					choice = menu.searchOrClear();
					switch (choice) {
					case 1:

						do {
							searchThis = menu.searchThis();
							if (searchThis.equals("-999")) {
								// do nothing
								System.out.println("Exiting");
							} else {
								index.lookUpIndex(searchThis);
							}
						} while (searchThis.equals("-999") == false);
						// search while they've not entered -999
						// end of search

						break;
					case 2:
						// clear the map
						index.clearIndex();
						parsed = false;
						break;
					case 3:
						index.displayDetails();
						// shows details of the maps
						break;
					case 4:
						//index.displayAll();
						index.displayMap();
						break;
					case 5:
						index.displayDictionary();
						break;
					case 6:
						index.displayIgnoreList();
					}
				} while (choice != 0 && choice != 2);
			}
			// give searchclear option until 0

		} while (choice != 0 && parsed == false);
		// if the choice was 2, we clear the index, set parsed to false and run
		// the loop from choose
		System.out.println("Shutting Down");

	}// main

	
	public static long getStartTime() {
		long startTime = System.currentTimeMillis();
		return startTime;
	}

	public static void printEndTime(long startTime) {
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Run Time: " + totalTime + " ms");
	}

}
