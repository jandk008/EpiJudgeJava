package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    // TODO - you fill in here.
    long shift = 32;
    long res = 0;
    long yp = y << shift;
    while ( x >= y) {
      while (yp > x) {
        yp >>>= 1;
        shift--;
      }
      res  += 1L << shift;
      x -= yp;
    }
    return (int) res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
