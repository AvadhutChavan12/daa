package daa;
//Java program for Floyd Warshall All Pairs Shortest
//Path algorithm.
import java.io.*;
import java.lang.*;
import java.util.*;
class Assignment3 
{

	void floydWarshall(int dist[][],int vertex)
	{
		int i, j, k;

		for (k = 0; k < vertex; k++)
		{
			for (i = 0; i < vertex; i++)
			{
				for (j = 0; j < vertex; j++)
				{
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
 // Print the shortest distance matrix

		printSolution(dist,vertex);
	}

	void printSolution(int dist[][],int V)
	{
		System.out.println("The following matrix shows the shortest "
 + "distances between every pair of vertices");
		for (int i = 0; i < V; ++i) 
		{
			for (int j = 0; j < V; ++j) 
			{
				if (dist[i][j] == 9999)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
	}
// Driver's code

	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of vertex");
		int v=sc.nextInt();
		int a[][]=new int[v][v];
		for(int i=0;i<v;i++)
		{
			for(int j=0;j<v;j++)
			{
				if (i == j) 
				{
					a[i][j] = 0;
				}
				else
				{
					System.out.println("Enter value for vetex for infinity 9999 "+i+" "+j);
					int b=sc.nextInt();
					a[i][j]=b;
				}

			}
		}
 
		Assignment3 z = new Assignment3();
		z.floydWarshall(a,v);
}
}