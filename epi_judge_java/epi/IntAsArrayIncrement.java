package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int currentIndex = A.size() - 1;
    int carry = 1;
    do {
      int k = A.get(currentIndex) + carry;
      carry = k / 10;
      A.set(currentIndex, k % 10);
      currentIndex --;
    } while (carry != 0 && currentIndex >= 0);
    if (carry != 0) {
      A.add(0, carry);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
