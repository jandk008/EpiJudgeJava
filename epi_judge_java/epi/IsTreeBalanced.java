package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    IsBalanced isBalanced = isBalancedForLevel(tree);
    return isBalanced.isBalance;
  }

  private static IsBalanced isBalancedForLevel(BinaryTreeNode<Integer> root) {
    if (root == null) return new IsBalanced(true, -1);
    IsBalanced leftIsBalanced = isBalancedForLevel(root.left);
    IsBalanced rightIsBalanced = isBalancedForLevel(root.right);
    return new IsBalanced(leftIsBalanced.isBalance && rightIsBalanced.isBalance && Math.abs(leftIsBalanced.height - rightIsBalanced.height) <= 1, Math.max(leftIsBalanced.height, rightIsBalanced.height) + 1);
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static class IsBalanced {
    boolean isBalance;
    int height;
    IsBalanced(boolean isBalanced, int height) {
      this.isBalance = isBalanced;
      this.height = height;
    }
  }
}
