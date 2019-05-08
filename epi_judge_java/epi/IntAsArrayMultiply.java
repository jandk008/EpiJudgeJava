package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.
    int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1: 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> list = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    for(int i = num1.size() - 1; i>=0; i--) {
      for(int j = num2.size() - 1; j >= 0; j--) {
        list.set(i + j + 1, list.get(i + j + 1) + num1.get(i) * num2.get(j));
        list.set(i + j, list.get(i + j) + list.get(i + j + 1) / 10);
        list.set(i + j + 1, list.get(i + j + 1) % 10);
      }
    }

    while (list.size() > 1 && list.get(0) == 0) {
      list.remove(0);
    }
    list.set(0, list.get(0) * sign);
    return list;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
