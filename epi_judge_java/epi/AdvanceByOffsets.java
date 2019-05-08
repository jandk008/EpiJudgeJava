package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // TODO - you fill in here.
    int maxRange = maxAdvanceSteps.get(0);
    for(int i = 0 ; i<= Math.min(maxAdvanceSteps.size() - 1,maxRange); i++){
      maxRange = Math.max(i + maxAdvanceSteps.get(i), maxRange);
    }
    return maxRange >= maxAdvanceSteps.size() - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
