package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.
    if (n == 1) return "1";
    return lookAndSayEach("1", n - 1);
  }

  private static String lookAndSayEach(String previous, int n) {
    if (n == 0) return previous;
    StringBuilder stringbuilder = new StringBuilder();
    char currentDigits = previous.charAt(0);
    int occurrence = 0;
    for (int i = 0; i < previous.length(); i++) {
      if (previous.charAt(i) != currentDigits) {
        stringbuilder.append(occurrence).append(currentDigits);
        occurrence = 1;
        currentDigits = previous.charAt(i);
      } else {
        occurrence ++;
      }
    }
    stringbuilder.append(occurrence).append(currentDigits);
    return lookAndSayEach(stringbuilder.toString(), n - 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
