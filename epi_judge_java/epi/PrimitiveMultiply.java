package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // TODO - you fill in here.
    long res = 0;
    while (y != 0) {
      if ((y & 1L) == 1) {
        res = add(res, x);
      }
      x <<= 1;
      y >>>= 1;
    }
    return res;
  }

  private static long add(long a, long b) {
    long sum = 0, carry = 0;
    long cb = 0;
    while (a != 0 || b != 0) {
      long ac = a & 1L;
      long bc = b & 1L;
      long sc = ac ^ bc ^ carry;
      carry = (ac & bc) | (ac & carry) | (bc & carry);
      sum |= (sc << cb);
      cb++;
      a >>= 1;
      b >>= 1;
    }
    return sum | (carry << cb);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
