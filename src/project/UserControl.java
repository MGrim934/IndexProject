package project;

import java.util.Scanner;

public class UserControl {
	//this is a class that simply displays user options
	//returns integers or Strings based on the questions asked
	
	int choice;
	String input;
	Scanner console = new Scanner(System.in);

	public UserControl() {
		// TODO Auto-generated constructor stub
	}
	
	//display user options
	int displayMainMenu(){
		do{
		System.out.println("Index Parser 3000!");
		System.out.println("==================");
		System.out.println("1) Parse something");
		System.out.println("0) Exit");
		choice=console.nextInt();
		}while(choice!=1&&choice!=0);
		return choice;
	}//displayMainMenu
	
	int chooseParse(){
		do{
		System.out.println("What do you wish to Parse?");
		System.out.println("==================");
		System.out.println("1) DeBelloGallico.txt");
		System.out.println("2) PoblachtNaHEireann.txt");
		System.out.println("3) WarAndPeace-LeoTolstoy.txt");
		System.out.println("4) Enter FileName!");
		System.out.println("0) Back");
		choice=console.nextInt();
		}while(choice>4&&choice<0);
		
		return choice;
		
	}
	
	String whatFile(){
		System.out.println("==================");
		System.out.println("Enter the Name of the File you wish to parse: ");
		input=console.next().trim();
		return input;
		
	}//whatFile
	

	String searchThis(){
		System.out.println("==================");
		System.out.println("Enter the Name of the Word you wish to find in the index: ");
		System.out.println("Enter \"-999\" to Stop");
		input=console.next().trim().toLowerCase();
		//changes input to lower case so map searching is easier
		return input;
		
	}
	
	int searchOrClear(){
		do{
		System.out.println("==================");
		System.out.println("1) Search Index"); 
		System.out.println("2) Clear Index");
		System.out.println("3) Show Size of map and dictionary");
		System.out.println("4) Show All Entries in index map.");
		System.out.println("5) Show All Entries in dictionary.");
		System.out.println("6) Show All Entries in Ignore List.");
		System.out.println("0) Quit");
		choice=console.nextInt();
		}while(choice>6||choice<0);
		return choice;
		
	}
}
