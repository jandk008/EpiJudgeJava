package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.Comparator;
import java.util.List;
public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
  public static Integer findMinimumVisits(List<Interval> intervals) {
    // TODO - you fill in here.
    if (intervals.size() == 0) return 0;
    intervals.sort(Comparator.comparingInt(i -> i.right));
    int count = 1;
    int visitTime = intervals.get(0).right;
    for (int i = 1; i < intervals.size(); i++){
      if (intervals.get(i).left > visitTime) {
        count++;
        visitTime = intervals.get(i).right;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
