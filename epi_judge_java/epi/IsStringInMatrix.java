package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class IsStringInMatrix {

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        // TODO - you fill in here.
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (dfs(grid, i, j, 0, pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(List<List<Integer>> grid, int i, int j, int index, List<Integer> pattern) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(0).size() || !grid.get(i).get(j).equals(pattern.get(index))) {
            return false;
        }
        if (index == pattern.size() - 1 && grid.get(i).get(j).equals(pattern.get(index))) {
            return true;
        }
        return dfs(grid, i + 1, j, index + 1, pattern)
                || dfs(grid, i - 1, j, index + 1, pattern)
                || dfs(grid, i, j + 1, index + 1, pattern)
                || dfs(grid, i, j - 1, index + 1, pattern);
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
