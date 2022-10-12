// Christopher Cao (including source code by Sean Szumlanski)
// ch282858
// COP3503, Fall 2021

// pre-processor directives
import java.io.*;
import java.util.*;

public class TopoPaths
{
	// Method to count the number of topological sorts in a given graph.
	public static int countTopoPaths(String filename) throws IOException 
	{
		Scanner sc = new Scanner(new File(filename));
		int numNodes = sc.nextInt();
		int i, j, temp1 = 0, temp2 = 0, topoPaths = 0;
		boolean [][] matrix = new boolean[numNodes][numNodes];
		
		// Loops to initialize the matrix.
		for (i = 0; i < numNodes; i++)
		{
			temp1 = sc.nextInt();
			
			for (j = 0; j < temp1; j++)
			{
				temp2 = sc.nextInt();
				matrix[i][temp2 - 1] = true;
			}
		}
		
		// Following code is edited from toposort.java by Sean Szumlanski.
		int [] incoming = new int[matrix.length];
		int cnt = 0;
		
		// Count the number of incoming edges incident to each vertex. For sparse
		// graphs, this could be made more efficient by using an adjacency list.
		for (i = 0; i < matrix.length; i++)
		{
			for (j = 0; j < matrix.length; j++)
			{
				incoming[j] += (matrix[i][j] ? 1 : 0);
			}
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		// Any vertex with zero incoming edges is ready to be visited, so add it to the queue.
		for (i = 0; i < matrix.length; i++)
		{
			if (incoming[i] == 0)
			{
				q.add(i);
			}
		}
		
		while (!q.isEmpty())
		{
			// Pull a vertex out of the queue and add it to the topological sort.
			int node = q.remove();
			
			// Count the number of unique vertices we see.
			++cnt;
			
			if ((q.peek() != null) && (matrix[node][q.peek()] == false))
			{
				// If we pass out of the loop without including each vertex in our
				// topological sort, we must have a cycle in the graph.
				if (cnt != matrix.length)
				{
					return 0;
				}
				
				// Otherwise, there is at least one valid topological sort.
				topoPaths = topoPaths + 1;
			}
			
			// All vertices we can reach via an edge from the current vertex should
			// have their incoming edge counts decremented. If one of these hits
			// zero, add it to the queue, as it's ready to be included in our
			// topological sort.
			for (i = 0; i < matrix.length; i++)
			{
				if (matrix[node][i] && --incoming[i] == 0)
				{
					q.add(i);
				}
			}
		}
		
		// If we pass out of the loop without including each vertex in our
		// topological sort, we must have a cycle in the graph.
		if (cnt != matrix.length)
		{
			return 0;
		}
		
		// Otherwise, there is at least one valid topological sort.
		topoPaths = topoPaths + 1;
		
		return topoPaths;
	}
	
	public static double difficultyRating()
	{
		return 3.5;
	}
	
	public static double hoursSpent()
	{
		return 5.0;
	}
}
