package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return tree == null || isSymmetricForEachChild(tree.left, tree.right);
  }

  private static boolean isSymmetricForEachChild(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if (left == null || right == null) return left == right;
    return left.data.equals(right.data) && isSymmetricForEachChild(left.left, right.right) && isSymmetricForEachChild(left.right, right.left);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
