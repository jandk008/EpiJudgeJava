package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
import java.util.List;
public class LongestNondecreasingSubsequence {

  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
  public static int longestNondecreasingSubsequenceLength(List<Integer> A) {
    // TODO - you fill in here.
    int[] cache = new int[A.size()];
    Arrays.fill(cache, 1);
    int max = 1;
    for (int i = 1; i < A.size(); i++) {
      for (int j = 0; j < i; j++){
        if (A.get(j) <= A.get(i)) {
          cache[i] = Math.max(cache[i], cache[j] + 1);
          max = Math.max(max, cache[i]);
        }
      }
    }

    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
