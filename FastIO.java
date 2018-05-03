/*
 * FastIO - reads in a space-separated list of numbers and outputs it
 * Illustrates the use of BufferedReader and PrintWriter for fast I/O
 * Makes a significant difference when there are >10,000 values to input/output 
 */
import java.util.*;
import java.io.*;
public class FastIO {
public static void main(String[] args) throws IOException
{
	FastScanner input = new FastScanner(System.in);
	PrintWriter out = new PrintWriter(System.out); // Buffers the output to speed it up
	int n = input.nextInt();
	int[] a = new int[n];
	for(int i = 0; i<n; i++) a[i] = input.nextInt();
	for(int i = 0; i<n; i++) out.print(a[i] + (i == n-1 ? "\n" : " "));
	out.close(); // If you forget this, anything still in the buffer will not be output
}
/*
 * Combines nice methods of the Scanner class with speed of BufferedReader
 */
static class FastScanner
{
	BufferedReader br;
	StringTokenizer st;
	FastScanner(InputStream in)
	{
		br = new BufferedReader(new InputStreamReader(in));
	}
	String next() throws IOException
	{
		while(st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}
	int nextInt() throws IOException
	{
		return Integer.parseInt(next());
	}
	double nextDouble() throws IOException
	{
		return Double.parseDouble(next());
	}
	long nextLong() throws IOException
	{
		return Long.parseLong(next());
	}
}
}

