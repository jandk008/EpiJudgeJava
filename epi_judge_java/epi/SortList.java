package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortList {

  @EpiTest(testDataFile = "sort_list.tsv")
  public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null || L.next == null) return L;

    ListNode<Integer> slow = L;
    ListNode<Integer> fast = L;
    ListNode<Integer> preSlow = slow;
    while (fast != null && fast.next != null) {
      preSlow = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    preSlow.next = null;

    return SortedListsMerge.mergeTwoSortedLists(stableSortList(L), stableSortList(slow));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
