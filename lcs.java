// Solution to the Longest Common Subsequence problem.
// Given two strings, it finds the length of the longest sequence of characters that occurs in both strings, but it does not have to be contiguous.
// Example: LCS("agtgc", "ggttac") = 3 ("gtc")
import java.util.*;
public class lcs
{
    static String s, t;
    static int n, m;
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        // Input is two strings s and t.
        s = input.next();
        t = input.next();
        // The runtime of the algorithm is O(n*m), where n and m are the lengths of s and t, respectively.
        n = s.length();
        m = t.length();
        
        // Now, build the dp table.
        // dp[i][j] is the longest common subsequence of the first i characters of s and the first j characters of t.
        // For example, if s is "abca" and t is "aa", dp[2][1] is 1 because the LCS of "ab" and "a" is 1.
        // After, the table is filled, the answer will be dp[n][m] since it is the LCS of s and t.
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i<=n; i++)
            for(int j = 1; j<=m; j++)
            {
                // Case 1: If the current characters match (suppose they're both "x"), then we can take the longest subsequence
                // not counting the current characters and add an "x" to the end of it.
                // For example, if the strings at this point are "babc" and "dbbc", we will take the LCS of "bab" and "dbb",
                // which is "bb", and add a "c" to the end to get a longest common subsequence of "bbc"
                int match = 0;
                if(s.charAt(i-1) == t.charAt(j-1))
                {
                    match = 1;
                }
                dp[i][j] = 0;
                dp[i][j] = Math.max(dp[i][j], match + dp[i-1][j-1]);
                // Case 2: If the current character of t was already matched with something earlier in s, we cannot extend our LCS further.
                // For example, if the strings at this point are "cbebp" and "abb", the last "b" in "abb" was already matched with something before the "p",
                // so the answer is the same as the LCS for "cbeb" and "abb", which is "bb"
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                // Case 3: If the current character of s was already matched with something earlier in t, we cannot extend our LCS further.
                // This is similar to case 2, but with the two strings switched.
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
            }
        System.out.println(dp[n][m]);
        
        // Now call the recursive version.
        System.out.println(lcs());
    }
    // The recursive version has very similar logic.
    static int lcs()
    {
        // First, initialize a memo array to -1's.
        // If we ever try to calculate an answer that has been calculated already, we can just look up the answer in memo.
        memo = new int[n][m];
        for(int[] A : memo) Arrays.fill(A, -1);
        return dp(0, 0);
    }
    static int[][] memo;
    // Here, the pair (i, j) represents a state.
    // The state is i = the number of characters in s we have used already, and j = the number of characters in t we have used already.
    // This method calculates the LCS out of the remaining characters.
    // This is backwards from what the iterative code above is doing.  Here, dp(i, j) calculates the LCS of s with the first i characters missing and 
    // t with the first j characters missing, while dp[i][j] above was the LCS of the first i characters in s and the first j characters in t.
    static int dp(int i, int j)
    {
        // Base case: If we have used up either of the strings entirely, the remaining answer is 0.
        if(i == s.length() || j == t.length()) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        int res = 0;
        // Here, we have the same three cases as above.
        res = Math.max(res, dp(i+1, j));
        res = Math.max(res, dp(i, j+1));
        int match = 0;
        if(s.charAt(i) == t.charAt(j)) match = 1;
        res = Math.max(res, match + dp(i+1, j+1));
        // Store the answer in memo before returning!
        memo[i][j] = res;
        return res;
    }
}
