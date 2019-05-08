package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    final ArrayList<Integer> integers = new ArrayList<>();
    integers.add(3);
    integers.add(8);
    integers.add(1);
    integers.add(4);
    Collections.rotate(integers.subList(1,4), -1);
    System.out.println(integers.subList(1,4));
    return null;
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
