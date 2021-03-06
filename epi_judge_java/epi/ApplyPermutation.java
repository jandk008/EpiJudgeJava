package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  private static void applyPermutation(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); i++) {
      int index = i;
      while (perm.get(index) >= 0) {
        Collections.swap(A, index, i);
        int nextIndex = perm.get(index);
        perm.set(index, perm.get(index) - perm.size());
        index = nextIndex;
      }
    }
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
