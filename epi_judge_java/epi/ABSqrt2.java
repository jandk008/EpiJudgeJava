package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;

public class ABSqrt2 {

  @EpiTest(testDataFile = "a_b_sqrt2.tsv")
  public static List<Double> generateFirstKABSqrt2(int k) {
    // TODO - you fill in here.
    List<SqrtEntity> list = new ArrayList<>();
    int i = 0;
    int j = 0;
    list.add(new SqrtEntity(0, 0));
    for (int n = 1; n < k; n++) {
      SqrtEntity aNext = new SqrtEntity(list.get(i).a + 1, list.get(i).b);
      SqrtEntity bNext = new SqrtEntity(list.get(j).a, list.get(j).b + 1);
      SqrtEntity min = aNext.val < bNext.val ? aNext : bNext;
      list.add(min);
      if (Double.compare(min.val, aNext.val) == 0) i++;
      if (Double.compare(min.val, bNext.val) == 0) j++;
    }
    List<Double> res = new ArrayList<>();
    for(SqrtEntity entity : list) {
      res.add(entity.val);
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  private static class SqrtEntity {
    int a;
    int b;
    double val;

    private SqrtEntity(final int a, final int b) {
      this.a = a;
      this.b = b;
      this.val = a + b * Math.sqrt(2.0);
    }
  }
}
