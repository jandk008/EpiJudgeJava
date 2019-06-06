package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LongestSubstringWithMatchingParentheses {
  @EpiTest(testDataFile = "longest_substring_with_matching_parentheses.tsv")

  public static int longestMatchingParentheses(String s) {
    // TODO - you fill in here.
    if (s == null || s.length() < 2) return 0;
    int max = 0;
    char[] cs = s.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if (cs[i] == ')') continue;
      int numOpen = 1;
      for (int j = i + 1; j < cs.length; j++) {
        if (cs[j] == ')') {
            if (numOpen == 1) {
              max = Math.max(max, j - i + 1);
            } else if (numOpen < 1){
              break;
            }
          numOpen --;
        } else {
          numOpen ++;
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "LongestSubstringWithMatchingParentheses.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
