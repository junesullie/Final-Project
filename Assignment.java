import java.time.LocalDate;		// Needed for LocalDate class
import java.io.*;				// Needed for file IO class

/**
Author:   Sydney Sullivan
File(s):  Assingment.java  
Course:   CPS 176 - Spring 2018
Due Date: 
Purpose:  The Assignment class is used to create assignment objects.
*/

public class Assignment 
{
	private String assignmentName;		// Assignment's name	
	private LocalDate dueDate;			// Assighment's due date
	
	/**
	 * The constructor sets the assignment name and due date
	 * to the values passed as arguments.
	 * @param name The assignment name
	 * @param date The assignment due date
	 */
	public Assignment(String name, LocalDate date)
	{
		assignmentName = name;		
		dueDate = date;
	}
	
	/**
	 * The save method appends the name and date of an 
	 * assignment object to the ReminderList.txt file.
	 * @throws IOException
	 */
	public void save() throws IOException
	{
		// Open file
		FileWriter fwriter = new FileWriter("ReminderList.txt", true);
		PrintWriter outputFile = new PrintWriter(fwriter);
		
		// Write name and due date to file
		outputFile.println(assignmentName);
		outputFile.println(dueDate);
		
		// Close file
		outputFile.close();
	}
	
	/**
	 * The getDate method returns the assignment due date.
	 * @return the assignment due date.
	 */
	public LocalDate getDate()
	{
		return dueDate;
	}
	
	/**
	 * The getName method returns the assignment name.
	 * @return the assignment name.
	 */
	public String getName()
	{
		return assignmentName;
	}
}
