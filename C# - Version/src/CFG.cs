using System;

public class GFG
{

	// Funktion zum finden des Knoten mit der geringsten Distanz
	// von den Knoten die noch nicht besucht worden sind.
	public static int GetMinDistance(int[] dist, bool[] sptSet)
	{
		int min = int.MaxValue;
		int min_index = -1;

		for (int v = 0; v < dist.Length; v++)
		{
			if (sptSet[v] == false && dist[v] <= min)
			{
				min = dist[v];
				min_index = v;
			}
		}

		return min_index;
	}

	public static void PrintSolution(int[] dist, int n)
	{
		Console.Write("Vertex     Distance from Source\n");
		for (int i = 0; i < n; i++)
		{
			Console.Write(i + " \t\t " + dist[i] + "\n");
		}
	}

	public static void Dijkstra(int[,] graph)
	{
		int length = graph.GetLength(0);

		int[] dist = new int[length];

		bool[] sptSet = new bool[length];

		for (int i = 1; i < length; i++)
		{
			dist[i] = int.MaxValue;
		}

		for (int count = 0; count < length - 1; count++)
		{
			// Funktion zum finden des Knoten mit der geringsten Distanz
			// von den Knoten die noch nicht besucht worden sind.
			int u = GetMinDistance(dist, sptSet);

			// Markiert den Knoten als besucht
			sptSet[u] = true;

			// Aktualisiert die Distanz aller Kanten eines Knotens
			for (int v = 0; v < length; v++)
			{
				if (sptSet[v]) // Knoten die bereits besucht worden sind, sollen übersprungen werden 
				{
					continue;
				}

				if (graph[u, v] != 0 && //Schaut ob eine Kante vorhanden ist
					dist[u] != int.MaxValue && // Schaut ob die Distant schon berechnet worden ist
					dist[u] + graph[u, v] < dist[v]) // Schaut ob die Distanz zu dem Knoten kürzer als die bereits berechnet worden ist
				{
					dist[v] = dist[u] + graph[u, v]; 
				}
			}
		}

		// Ausgeben der berechneten Distanzen
		PrintSolution(dist, length);
	}


	public static void Main()
	{
		// Aufgabe: Erstelle einen Graphen der für die Methode Dijkstra geeignet ist.

		Dijkstra(null /* Null muss hierbei ersetzt werden mit dem Graphen */);
	}
}