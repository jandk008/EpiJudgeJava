package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {

  @EpiTest(testDataFile = "levenshtein_distance.tsv")
  public static int levenshteinDistance(String A, String B) {
    // TODO - you fill in here.
    int[][] cache = new int[A.length() + 1][B.length() + 1];
    cache[0][0] = 0;
    for (int i = 1; i <= B.length(); i++) {
      cache[0][i] = cache[0][i - 1] + 1;
    }
    for (int i = 1; i <= A.length(); i++) {
      cache[i][0] = cache[i - 1][0] + 1;
    }

    for (int i = 1; i <= A.length(); i++) {
      for (int j = 1; j <= B.length(); j++) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          cache[i][j] = cache[i - 1][j - 1];
        } else {
          cache[i][j] = Math.min(Math.min(cache[i - 1][j], cache[i][j - 1]), cache[i - 1][j - 1]) + 1;
        }
      }
    }
    return cache[A.length()][B.length()];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
