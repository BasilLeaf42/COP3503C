// Christopher Cao
// ch282858
// COP3503, Fall 2021

// pre-processor directives
import java.io.*;
import java.util.*;
import java.awt.Point;

public class SneakyKnights
{
	// mandatory function
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// variable declaration
		int i, j, k, temp = 0;
		int numKnights = coordinateStrings.size();
		int stringLength = 0, rowLength = 0;
		int currentRow = 0, currentColumn = 0;
		char storedCharacter = 'X';
		String rowString, columnString;
		HashSet<Point> knightCoordinates = new HashSet<>();
		
		// return true if there is only one knight (thus it is guaranteed to be safe)
		if (numKnights == 1)
		{
			return true;
		}
		
		// loop to sort through each knight
		for (i = 0; i < numKnights; i++)
		{
			stringLength = coordinateStrings.get(i).length();
			
			// loop to find when the string switches from letters to numbers
			for (j = 0; j < stringLength; j++)
			{
				storedCharacter = coordinateStrings.get(i).charAt(j);
				
				// statement to break from loop once a digit is found
				if (Character.isDigit(storedCharacter))
				{
					break;
				}
			}
			
			// update variables
			rowString = coordinateStrings.get(i).substring(0, j);
			columnString = coordinateStrings.get(i).substring(j);
			rowLength = rowString.length();
			
			// loop to parse rowString
			for (k = 0; k < rowLength; k++)
			{
				temp = power(rowLength - (k + 1));
				currentRow = currentRow + (temp * (rowString.charAt(k) - 96));
			}
			
			// parse columnString
			currentColumn = Integer.parseInt(columnString);
			
			// add the knight to the board
			knightCoordinates.add(new Point(currentColumn, currentRow));
			
			// statements to find if the current knight is unsafe and return false if it is
			if (knightCoordinates.contains(new Point(currentColumn - 2, currentRow - 1)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn - 2, currentRow + 1)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn - 1, currentRow - 2)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn - 1, currentRow + 2)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn + 1, currentRow - 2)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn + 1, currentRow + 2)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn + 2, currentRow - 1)))
			{
				return false;
			}
			
			if (knightCoordinates.contains(new Point(currentColumn + 2, currentRow + 1)))
			{
				return false;
			}
			
			// update variables
			currentColumn = 0;
			currentRow = 0;
		}
		
		// return true if all the knights are safe
		return true;
	}
	
	// function to calculate powers
	public static int power(int exponent)
	{
		// variable declaration
		int i;
		int product = 1;
		
		// loop to calculate power
		for (i = 0; i < exponent; i++)
		{
			product = product * 26;
		}
		
		return product;
	}
	
	public static double difficultyRating()
	{
		return 3.0;
	}
	
	// it didn't take me too long since I was able to reuse a good chunk of my SneakyQueens code
	public static double hoursSpent()
	{
		return 5.0;
	}
}
