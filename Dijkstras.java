/*
 * Dijkstra's algorithm for the shortest path in a directed graph
 */
import java.util.*;
public class Dijkstras {
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	// n is number of nodes, m is number of edges
	int n = input.nextInt(), m = input.nextInt();
	ArrayList<Edge>[] g = new ArrayList[n]; // store the directed graph
	for(int i = 0; i<n; i++) g[i] = new ArrayList<Edge>();
	for(int i = 0; i<m; i++)
	{
		// Assumes the input is for each edge: <from> <to> <weight>
		// If the vertex numbers in the input are 1-indexed, be sure to subtract 1 to make them 0-indexed
		int a = input.nextInt()-1, b = input.nextInt()-1;
		int cost = input.nextInt();
		g[a].add(new Edge(b, cost));
	}
	dijkstras(0, g);
}
// Dijkstra's shortest path algorithm - O((V + E) * log(E))
// Does not work with negative edge weights
static long[] ds; // Will contain distance from start to all vertices
static int[] prev; // For each vertex, stores the vertex coming before it on its shortest path

static void dijkstras(int start, ArrayList<Edge>[] g)
{
	int n = g.length;
	prev = new int[n];
	Arrays.fill(prev, -1);
	ds = new long[n];
	Arrays.fill(ds, (long)1e18);
	ds[start] = 0;
	PriorityQueue<State> pq = new PriorityQueue<State>();
	pq.add(new State(start, 0));
	while(!pq.isEmpty())
	{
		State at = pq.poll();
		if(ds[at.at] < at.d) continue;
		for(Edge e: g[at.at])
		{
			int to = e.to;
			long nd = at.d + e.d;
			if(nd >= ds[to]) continue;
			ds[to] = nd;
			prev[to] = at.at;
			pq.add(new State(to, nd));
		}
	}
}
static class State implements Comparable<State>
{
	int at; // Which vertex we are at
	long d; // The distance it took to get there
	public State(int aa, long dd)
	{
		at = aa; d = dd;
	}
	public int compareTo(State o) {
		return (int)Long.signum(d - o.d);
	}
}
static class Edge
{
	int to, d;
	public Edge(int tt, int dd)
	{
		to = tt; d = dd;
	}
}
}
