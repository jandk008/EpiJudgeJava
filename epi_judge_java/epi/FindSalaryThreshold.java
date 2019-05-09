package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class FindSalaryThreshold {

  @EpiTest(testDataFile = "find_salary_threshold.tsv")
  public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
    // TODO - you fill in here.
    currentSalaries.sort(Integer::compare);

    double threshold = targetPayroll / (1.0 * currentSalaries.size());
    int sum = 0;
    for(int i = 0; i < currentSalaries.size(); i++) {
      int integer = currentSalaries.get(i);
      if (threshold - integer < 0) {
        threshold = (targetPayroll - sum) / (1.0 * (currentSalaries.size() - i));
        if (threshold < integer) return threshold;
      }
      sum += integer;
    }
    return targetPayroll > sum ? -1 : threshold;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
