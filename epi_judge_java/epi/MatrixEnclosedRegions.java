package epi;
import epi.SearchMaze.Coordinate;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrixEnclosedRegions {

  public static void fillSurroundedRegions(List<List<Character>> board) {
    // TODO - you fill in here.
    int length = board.get(0).size();
    List<List<Boolean>> visited = new ArrayList<>(board.size());
    for (int i = 0; i < board.size(); i++) {
      visited.add(new ArrayList<>(Collections.nCopies(board.get(i).size(), false)));
    }

    for (int i = 1; i < board.size() - 1; i++) {
      if (board.get(i).get(0) == 'W' && !visited.get(i).get(0)) {
        paintBlack(board, new Coordinate(i, 0), visited);
      }

      if (board.get(i).get(length -  1) == 'W' && !visited.get(i).get(length - 1)) {
        paintBlack(board, new Coordinate(i, length - 1), visited);
      }
    }

    for (int i = 0; i < length; i++) {
      if (board.get(0).get(i) == 'W' && !visited.get(0).get(i)) {
        paintBlack(board, new Coordinate(0, i), visited);
      }

      if (board.get(board.size() - 1).get(i) == 'W' && !visited.get(board.size() - 1).get(i)) {
        paintBlack(board, new Coordinate(board.size() - 1, i), visited);
      }
    }

    for (int i = 1; i < board.size() - 1; i++) {
      for (int j = 1; j < length - 1; j++) {
        if (!visited.get(i).get(j)) {
          board.get(i).set(j, 'B');
        }
      }
    }
  }

  private static void paintBlack(final List<List<Character>> board, final Coordinate c, List<List<Boolean>> visited) {
    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(c);
    int[][] nextSteps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    while (!queue.isEmpty()) {
      Coordinate curr = queue.poll();
      visited.get(curr.x).set(curr.y, true);
      for (int[] step : nextSteps) {
        Coordinate next = new Coordinate(curr.x + step[0], curr.y + step[1]);
        if (next.x >= 0 && next.x < board.size() && next.y >= 0 && next.y < board.get(0).size() && !visited.get(next.x).get(next.y)
                && board.get(next.x).get(next.y) == 'W') {
          queue.add(next);
        }
      }
    }
  }

  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>> fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
