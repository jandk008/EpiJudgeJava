package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class EnumerateTrees {

  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
    // TODO - you fill in here.
    List<BinaryTreeNode<Integer>> list = new ArrayList<>();
    if (numNodes == 0) {
      list.add(null);
    }
    for (int leftNodesNum = 0;  leftNodesNum < numNodes; leftNodesNum++) {
      List<BinaryTreeNode<Integer>> leftTree = generateAllBinaryTrees(leftNodesNum);
      List<BinaryTreeNode<Integer>> rightTree = generateAllBinaryTrees(numNodes - 1 - leftNodesNum);
      for (BinaryTreeNode<Integer> left : leftTree) {
        for (BinaryTreeNode<Integer> right : rightTree) {
          list.add(new BinaryTreeNode<>(0, left, right));
        }
      }
    }
    return list;
  }

  private static BinaryTreeNode<Integer> generate(int left, int leftCount, int right, int rightCount) {
    if (leftCount < left) {
      return new BinaryTreeNode<>(0, generate(left, leftCount + 1, right, rightCount), generate(left, leftCount + 1, right, rightCount));
    }
    if (rightCount < right) {
      return new BinaryTreeNode<>(0, generate(left, leftCount, right, rightCount + 1), generate(left, leftCount, right, rightCount + 1));
    }
    return null;
  }

  public static List<Integer> serializeStructure(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> p = stack.pop();
      result.add(p == null ? 0 : 1);
      if (p != null) {
        stack.push(p.left);
        stack.push(p.right);
      }
    }
    return result;
  }

  @EpiTest(testDataFile = "enumerate_trees.tsv")
  public static List<List<Integer>>
  generateAllBinaryTreesWrapper(TimedExecutor executor, int numNodes)
      throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> generateAllBinaryTrees(numNodes));

    List<List<Integer>> serialized = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      serialized.add(serializeStructure(x));
    }
    serialized.sort(new LexicographicalListComparator<>());
    return serialized;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateTrees.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
