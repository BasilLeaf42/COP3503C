// Christopher Cao
// ch282858
// Fall 2021, COP3503

// pre-processor directives
import java.io.*;
import java.util.*;

public class RunLikeHell
{
	public static int maxGain(int [] blocks)
	{
		// variable declaration
		int i;
		int totalBlocks = blocks.length;
		int [] maxKnowledge = new int[totalBlocks];
		
		// statement to return 0 if there are no blocks
		if (totalBlocks == 0 || blocks == null)
		{
			return 0;
		}
		
		// statement to return the value of the first block if there is only one block
		else if (totalBlocks == 1)
		{
			return blocks[0];
		}
		
		// statement to return the larger value if there are two blocks
		else if (totalBlocks == 2)
		{
			if (blocks[1] < blocks[0])
			{
				return blocks[0];
			}
			
			else if (blocks[0] < blocks[1])
			{
				return blocks[1];
			}
			
			else if (blocks[0] == blocks[1])
			{
				return blocks[0];
			}
		}
		
		// statements to initialize the first three elements of maxKnowledge array
		maxKnowledge[0] = blocks[0];
		maxKnowledge[1] = blocks[1];
		maxKnowledge[2] = blocks[2] + blocks[0];
		
		// loop to hit blocks and initialize the rest of the maxKnowledge array
		for (i = 3; i < totalBlocks; i++)
		{
			// statements to add the larger index value between the two
			if (maxKnowledge[i - 3] < maxKnowledge[i - 2])
			{
				maxKnowledge[i] = maxKnowledge[i] + maxKnowledge[i - 2] + blocks[i];
			}
			
			else if (maxKnowledge[i - 2] < maxKnowledge[i - 3])
			{
				maxKnowledge[i] = maxKnowledge[i] + maxKnowledge[i - 3] + blocks[i];
			}
			
			else if (maxKnowledge[i - 2] == maxKnowledge[i - 3])
			{
				maxKnowledge[i] = maxKnowledge[i] + maxKnowledge[i - 3] + blocks[i];
			}
		}
		
		// statements to return the largest maxKnowledge value
		if (maxKnowledge[totalBlocks - 2] < maxKnowledge[totalBlocks - 1])
		{
			return maxKnowledge[totalBlocks - 1];
		}
		
		else if (maxKnowledge[totalBlocks - 1] < maxKnowledge[totalBlocks - 2])
		{
			return maxKnowledge[totalBlocks - 2];
		}
		
		else if (maxKnowledge[totalBlocks - 1] == maxKnowledge[totalBlocks - 2])
		{
			return maxKnowledge[totalBlocks - 2];
		}
		
		// unrealistic default return value; should only be returned if something goes horribly wrong
		return -1;
	}
	
	public static double difficultyRating()
	{
		return 2.5;
	}
	
	public static double hoursSpent()
	{
		return 3.0;
	}
}
