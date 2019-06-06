package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ZipList {
  @EpiTest(testDataFile = "zip_list.tsv")

  public static ListNode<Integer> zippingLinkedList(ListNode<Integer> L) {
    // TODO - you fill in here.
    if (L == null || L.size() < 3) return L;

    ListNode<Integer> slow = L;
    ListNode<Integer> fast = L;
    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode<Integer> secondHead = reverse(slow.next);
    slow.next = null;

    ListNode<Integer> iterator1 = L;
    ListNode<Integer> iterator2 = secondHead;

    while(iterator2 != null) {
        ListNode<Integer> t = iterator2.next;
        iterator2.next = iterator1.next;
        iterator1.next = iterator2;
        iterator1 = iterator1.next.next;
        iterator2 = t;
    }

    return L;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> head) {
    if (head  == null || head.next == null) return head;
    ListNode<Integer> dummy = new ListNode<>(0, head);
    ListNode<Integer> current = head.next;
    head.next = null;
    while (current != null) {
      ListNode<Integer> t = current.next;
      current.next = dummy.next;
      dummy.next = current;
      current = t;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ZipList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
