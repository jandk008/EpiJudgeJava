package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
public class KLargestInHeap {
  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    // TODO - you fill in here.
    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>(k, (e1, e2) -> Integer.compare(e2.value, e1.value));
    maxHeap.add(new HeapEntry(0, A.get(0)));
    final ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      HeapEntry entry = maxHeap.poll();
      list.add(entry.value);
      final int leftChildIndex = 2 * entry.index + 1;
      if (leftChildIndex < A.size()) {
        maxHeap.add(new HeapEntry(leftChildIndex, A.get(leftChildIndex)));
      }
      final int rightChildIndex = (entry.index + 1) * 2;
      if (rightChildIndex < A.size()) {
        maxHeap.add(new HeapEntry(rightChildIndex, A.get(rightChildIndex)));
      }
    }
    return list;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static class HeapEntry {

    private int index;
    private Integer value;

    public HeapEntry(final int i, final Integer integer) {
      index = i;
      value = integer;
    }
  }
}
