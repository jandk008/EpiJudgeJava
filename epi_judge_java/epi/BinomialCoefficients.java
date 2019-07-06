package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class BinomialCoefficients {
  @EpiTest(testDataFile = "binomial_coefficients.tsv")
  public static int computeBinomialCoefficient(int n, int k) {
    // TODO - you fill in here.
    return helper(new int[n + 1][k + 1], n, k);
  }

  private static int helper(int[][] cache, int n, int k) {
    if (k == 0 || n == k) return 1;

    if (cache[n][k] == 0) {
      cache[n][k] = helper(cache, n - 1, k) + helper(cache, n - 1, k - 1);
    }
    return cache[n][k];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
