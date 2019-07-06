package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
public class StringTransformability {

  @EpiTest(testDataFile = "string_transformability.tsv")
  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    // TODO - you fill in here.
    int res = 0;
    Queue<String> queue = new LinkedList<>();
    queue.add(s);
    while (!queue.isEmpty()) {
      int sizeLevel = queue.size();
      res++;
      while (sizeLevel > 0) {
        String visiting = queue.poll();
        D.remove(visiting);
        char[] chars = visiting.toCharArray();
        for (int i = 0; i < chars.length; i++) {
          for (char j = 'a'; j <= 'z'; j++) {
            String nextString = String.copyValueOf(chars, 0, i) + j + String.copyValueOf(chars, i + 1, chars.length - i - 1);
            if (D.contains(nextString)) {
              if (nextString.equals(t)) return res;
              queue.add(nextString);
              D.remove(nextString);
            }
          }
        }
        sizeLevel--;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
