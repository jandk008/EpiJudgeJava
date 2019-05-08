package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.
    return findLca(tree, node0, node1).ancestor;
  }

  private static Status findLca(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
    if (root == null) return new Status(0, null);
    Status leftStatus = findLca(root.left, p, q);
    if (leftStatus.numberNodesFound == 2) return leftStatus;
    Status rightStatus = findLca(root.right, p, q);
    if (rightStatus.numberNodesFound == 2) return rightStatus;

    final int numberNodesFound = leftStatus.numberNodesFound + rightStatus.numberNodesFound + (root == p ? 1 :0) + (root == q ? 1 : 0);
    return new Status(numberNodesFound, numberNodesFound == 2 ? root : null);
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static class Status {
    int numberNodesFound;
    BinaryTreeNode<Integer> ancestor;
    Status(int numberNodesFound, BinaryTreeNode<Integer> ancestor) {
      this.numberNodesFound = numberNodesFound;
      this.ancestor = ancestor;
    }
  }
}
