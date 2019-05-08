package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    return String.valueOf(x);
  }
  public static int stringToInt(String s) {
    // TODO - you fill in here.
    boolean isNegative = s.charAt(0) == '-';
    s = isNegative ? s.substring(1) : s;
    long res = 0;
    for(int i = 0; i < s.length(); i++) {
      res += s.charAt(i) - '0';
      res *= 10;
    }
    res /= 10;
    return isNegative ? (int) -res: (int) res;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    StringIntegerInterconversion.stringToInt("940623023");
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
