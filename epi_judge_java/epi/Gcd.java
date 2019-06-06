package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Gcd {

  @EpiTest(testDataFile = "gcd.tsv")
  public static long GCD(long x, long y) {
    // TODO - you fill in here.
    if (x == 0 || y == 0) return x == 0 ? y : x;
    if (x == y) return x;
    if ((x & 1) == 0 && (y & 1) == 0) {
      return GCD(x >> 1, y >> 1) << 1;
    } else if ((x & 1) == 1 && (y & 1) == 1) {
      return x > y ? GCD( x - y, y) : GCD(x, y - x);
    } else if ((x & 1) == 0){
      return GCD(x >> 1, y);
    } else {
      return GCD(x, y >> 1);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Gcd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
