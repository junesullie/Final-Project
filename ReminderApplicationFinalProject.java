import java.io.*;									// Needed for file IO class
import java.time.LocalDate;							// Needed for LocalDate class
import java.time.format.DateTimeFormatter;			// Needed for DateTimeFormatter class
import java.time.format.DateTimeParseException;		// Needed for DateTimeParseException class
import java.util.ArrayList;							// Needed for ArrayList class
import java.util.Scanner;							// Needed for scanner class

/**
Author:   Sydney Sullivan
File(s):  ReminderApplicationFinalProject.java  
Course:   CPS 176 - Spring 2018
Due Date: 
Purpose:  
*/

public class ReminderApplicationFinalProject 
{

	public static void main(String[] args) throws IOException 
	{
		int choice;								// user's menu choice
		char letter = 'N';						// user's Y/N decision
		String assignmentName;					// Name of assignment entered
		LocalDate dueDate = null;				// Formated due date
		LocalDate today = LocalDate.now();		// Today's date
		String answer;
		
		//Create Array List to read Assignment objects into main method
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		
		// Create scanner object to read from keyboard
		Scanner keyboard = new Scanner(System.in);
		
		
		//read data from file in an array list if a file exists containing data
		assignmentList = read();
		
		// The do loop allows the menu to be displayed first
		 do
	      {
	         // Call the printMenu method
	    	  	printMenu();
	         choice = keyboard.nextInt();
	         keyboard.nextLine(); // Consume the new line

	         switch(choice)
	         {
	            case 1:
	            	// Print exit statement
	            	System.out.println("If at any time you would like to return to the main"
	            						+ " menu, please enter 'EXIT'");
	            	
	            	// Get new assignment name
	            	System.out.println("Please enter the name of the assigment that is due. ");
	            	assignmentName = keyboard.nextLine();
	            	
	            	if (assignmentName.equalsIgnoreCase("exit"))
	            	{
	            		System.out.println();
	            		break;
	            	}
	            	
	            	// Get new assignment due date
	            	dueDate = convertLoop(assignmentName);
	            	
	            	if(dueDate == null)
	            	{
	            		System.out.println();
	            		break;
	            	}
	            	
	            	//Create new Assignment object using name and date provided by user
	            	Assignment asmtName1 = new Assignment(assignmentName, dueDate);
	            	
	            	// Add assignment object to Array List
	            	assignmentList.add(asmtName1);
	            	
	            	break;
			case 2:
	            	// If there are items in the Array List, then
					// use enhanced for loop to read from array and compare date.
					// If date of an array is equal to today then display.
					// Else display message saying no assignments on file.
	            	if(!(assignmentList.size() == 0))
	            	{
	            		System.out.println("Assignments due today: ");
	            		
	            		int counter = 0;
	            		for (Assignment asmtName2 : assignmentList)
	            		{
	            			dueDate = asmtName2.getDate();
	            			if(dueDate.equals(today))
	            			{
	            				System.out.println(asmtName2.getName() + " is due today");
	            				counter++;
	            			}
	            		}
	            		
	            		if (counter == 0)
            			{
            				System.out.println("There are no assignments due today.");
            			}
	            		
	            		System.out.println();
	            	}
	            	
	            	else
	            	{
	            		System.out.println("There are no assignments on file. Please enter"
	            							+ " new assignments first.");
	            		System.out.println();
	            	}
	            	
	            	break;
	            case 3:
	            	//Write items in Array List to file
	            	if(!(assignmentList.size() == 0));
	            	{
	            		
		            	for (Assignment asmtName3 : assignmentList)
		            	{
		            		asmtName3.save();
		            	}
	            	}
	            	
	            	System.out.println("Do you want to exit " +
                            "the program (Y/N)?: ");
	            	
	            	answer = keyboard.nextLine();
	   	         letter = answer.charAt(0); 
	   	         
	            	break;
	            default:
	               System.out.println("You did not enter " +
	                                  "a valid choice.");
	         }

	      } 
		 while(letter != 'Y' && letter != 'y');
		 
		 // Close the program
		 System.exit(0);
	}


/** 
The printMenu method displays the menu of options for user.
*/   
	public static void printMenu()
	{
		//Display menu options 
		System.out.println("This is an assignment reminder application" 
			 			+ "\nChoose what you would like to do" 
			 			+ "\n1. Enter in a new assignment"
			 			+ "\n2. Display assignments due today"
			 			+ "\n3. Save and exit"
			 			+ "\nEnter the number of your choice: ");
		
		//add choice 3 to exit while loop and then after (in case 3) can write to file if new data exists.
	  
	}	
	
	/**
	 * The convert method converts a date stored in a String object into
	 * a LocalDate object 
	 * @param date
	 * @return
	 */
	public static LocalDate convert(String date)
	{
		LocalDate dueDate = null;
		DateTimeFormatter formatter = null;
		
		try
		{
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dueDate = LocalDate.parse(date, formatter);
		}
		catch (DateTimeParseException ex)
		{
			System.out.printf("%s is not parsable, please edit text file ReminderList.txt"
							+ " to fix date error. Should be formatted yyyy-MM-dd%n", date);
			
		}
	return dueDate;
	}
	
	/**
	 * The convertLoop method 
	 * @param name The name of the assignment
	 * @return 
	 */
	public static LocalDate convertLoop(String name)
	{
		LocalDate dueDate = null;
		DateTimeFormatter formatter = null;
		String date;
		boolean valid = false;
		
		Scanner keyboard = new Scanner(System.in);
		
		while (valid == false)
    	{
    		System.out.println("Please enter the date " + name + " is due. Please enter in MM-DD-YYYY format. ");
			date = keyboard.nextLine();
			
			if (date.equalsIgnoreCase("exit"))
			{
				dueDate = null;
				valid = true;
			}
			
			else
			{
	        	//Convert the date from a string to a LocalDate
	        	try
	    		{
	    			formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
	    			dueDate = LocalDate.parse(date, formatter);
	    			System.out.println();
	    			valid = true;
	    		}
	    		catch (DateTimeParseException ex)
	    		{
	    			System.out.printf("%s is not parsable, please re-enter date in MM-DD-YYYY format.%n", date);
	    			
	    		
	    		}
			}
    	}
		return dueDate;
	}
	
	/**
	 * The read method reads data from file in an array list if a 
	 * file exists containing data.
	 * @return 
	 * @throws IOException
	 */
	public static ArrayList<Assignment> read() throws IOException
	{
		String name;
		String date;
		LocalDate dueDate;
		
		//Create Array List to hold assignment objects
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		
		//Make sure the file exists
		File file = new File("ReminderList.txt");
		if(file.exists())
		{
			// Open file for reading
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext())
			{
				//Read data from file into Array list
				name = inputFile.nextLine();
				date = inputFile.nextLine();
				dueDate = convert(date);
				assignmentList.add(new Assignment(name,dueDate));
			}
			
			// Close the file.
			inputFile.close();
		}
		// Return the assignment Array List
		return assignmentList;
	}

}
