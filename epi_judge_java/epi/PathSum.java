package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    // TODO - you fill in here.
    return hasPathSumHelper(tree, remainingWeight);
  }

  private static boolean hasPathSumHelper(BinaryTreeNode<Integer> root, int remainingSum) {
    if (root == null) return false;
    if (root.left == null && root.right == null && root.data == remainingSum) return true;
    return hasPathSumHelper(root.left, remainingSum - root.data) || hasPathSumHelper(root.right, remainingSum - root.data);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
