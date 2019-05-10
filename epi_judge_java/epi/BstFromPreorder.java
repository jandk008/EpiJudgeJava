package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {

  private static int index;
  @EpiTest(testDataFile = "bst_from_preorder.tsv")
  public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    // TODO - you fill in here.
    index = 0;
    return constructBstFromPreorderOnValueRange(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> constructBstFromPreorderOnValueRange(List<Integer> preorder, int low, int high) {
    if (index >= preorder.size()) return null;
    int root = preorder.get(index);
    if (root > high || root < low) return null;
    index++;
    BstNode<Integer> left = constructBstFromPreorderOnValueRange(preorder, low, root);
    BstNode<Integer> right = constructBstFromPreorderOnValueRange(preorder, root, high);
    return new BstNode<>(root, left, right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
