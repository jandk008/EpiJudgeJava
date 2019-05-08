package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    double res = 1;
    if (y < 0) {
      y = -y;
      x = 1.0 / x;
    }
    while (y != 0) {
      if ((y & 1L) != 0) {
        res *= x;
      }
      x *= x;
      y >>>=1;
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
