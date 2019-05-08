package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return sumEachNode(tree, 0);
  }

  private static int sumEachNode(BinaryTreeNode<Integer> root,  int sum) {
    if (root == null) return 0;
    if (root.left == null && root.right == null) {
      return sum * 2 + root.data;
    } else {
      return sumEachNode(root.left, sum * 2 + root.data) + sumEachNode(root.right, sum * 2 + root.data);
    }
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
