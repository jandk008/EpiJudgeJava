package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TreeFromPreorderWithNull {
  private static int index;
  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    // TODO - you fill in here.
    index = 0;
    return reconstructSubtree(preorder);
  }

  private static BinaryTreeNode<Integer> reconstructSubtree(List<Integer> preorder) {
    final Integer integer = preorder.get(index++);
    final PriorityQueue<Integer> integers = new PriorityQueue<>();
    if (integer == null) return null;
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(integer);
    root.left = reconstructSubtree(preorder);
    root.right = reconstructSubtree(preorder);
    return root;
  }
  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
