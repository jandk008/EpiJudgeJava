package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    // TODO - you fill in here.
    if (L == null || k == 0) return L;
    ListNode<Integer> end = L;
    int length = 1;
    while (end.next != null) {
      length++;
      end = end.next;
    }
    if (length == 1 || k % length == 0) return L;
    k = length - k % length;
    ListNode<Integer> previous = L;
    while(k > 1) {
      previous = previous.next;
      k--;
    }
    ListNode<Integer> temp = L;
    L = previous.next;
    previous.next = end.next;
    end.next = temp;
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
