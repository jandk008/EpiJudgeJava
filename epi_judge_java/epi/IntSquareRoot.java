package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.sql.SQLInvalidAuthorizationSpecException;

public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    // TODO - you fill in here.
    if (k == 0 || k == 1) return k;
    long left = 0;
    long right = k;
    while (left <= right) {
      long mid = left + (right - left) / 2;
      long power = mid * mid;
      if (power > k)
        right = mid - 1;
      else left = mid + 1;
    }
    return ((int) left) - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
