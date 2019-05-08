package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null || L.next == null || L.next.next == null) return L;
    ListNode<Integer> evenHead = L;
    ListNode<Integer> oddHead = L.next;
    boolean isEven = true;
    ListNode<Integer> current = L.next.next;
    ListNode<Integer> evenTail = evenHead;
    ListNode<Integer> oddTail = oddHead;
    while (current != null) {
      if (isEven) {
        evenTail.next = current;
        evenTail = evenTail.next;
      } else {
        oddTail.next = current;
        oddTail = oddTail.next;
      }
      current = current.next;
      isEven ^= true;
    }
    evenTail.next = oddHead;
    oddTail.next = null;
    return evenHead;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
