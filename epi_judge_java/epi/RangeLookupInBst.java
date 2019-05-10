package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RangeLookupInBst {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  @EpiTest(testDataFile = "range_lookup_in_bst.tsv")
  public static List<Integer> rangeLookupInBst(BstNode<Integer> tree, Interval interval) {
    // TODO - you fill in here.
    List<Integer> list = new ArrayList<>();
    lookUpOnRange(tree, interval, list);
    return list;
  }

  private static void lookUpOnRange(BstNode<Integer> root, Interval interval, List<Integer> list) {
    if (root == null) return;
    if (interval.left<= root.data && root.data <= interval.right) {
      lookUpOnRange(root.left, new Interval(interval.left, root.data), list);
      list.add(root.data);
      lookUpOnRange(root.right, new Interval(root.data, interval.right), list);
    } else if (root.data < interval.left) lookUpOnRange(root.right, interval, list);
    else lookUpOnRange(root.left, interval, list);
  }
  public static void rangeLookupInBstHelper(BstNode<Integer> tree,
                                            Interval interval,
                                            List<Integer> result) {
    if (tree == null) {
      return;
    }
    if (interval.left <= tree.data && tree.data <= interval.right) {
      // tree.data lies in the interval.
      rangeLookupInBstHelper(tree.left, interval, result);
      result.add(tree.data);
      rangeLookupInBstHelper(tree.right, interval, result);
    } else if (interval.left > tree.data) {
      rangeLookupInBstHelper(tree.right, interval, result);
    } else { // interval.right >= tree.data
      rangeLookupInBstHelper(tree.left, interval, result);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RangeLookupInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
