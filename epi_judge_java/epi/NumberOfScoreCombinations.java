package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class NumberOfScoreCombinations {
  @EpiTest(testDataFile = "number_of_score_combinations.tsv")

  public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
    // TODO - you fill in here.
    int[] cache = new int[finalScore + 1];
    cache[0] = 1;

    for (int i = 0; i < individualPlayScores.size(); i++) {
      for (int j = 1; j <= finalScore ; j++) {
        int score = individualPlayScores.get(i);
        if (i == 0 && j >= score) {
          cache[j] =  cache[j - score];
        } else if (j >= score) {
          cache[j] = cache[j - score] + cache[j];
        }
      }
    }
    return cache[finalScore];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
