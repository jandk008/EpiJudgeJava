package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;

public class NumberOfTraversalsMatrix {

  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
  public static int numberOfWays(int n, int m) {
    // TODO - you fill in here.
    int size = Math.min(n, m);
    int[] cache = new int[size];
    Arrays.fill(cache, 1);
    for (int i = 1; i < Math.max(n, m); i++) {
      for (int j = 0; j < size; j++) {
        if (j > 0) cache[j] += cache[j - 1];
      }
    }
    return cache[size - 1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
