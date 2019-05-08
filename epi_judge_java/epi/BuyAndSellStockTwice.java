package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.
    final List<Double> maxProfits = new ArrayList<>();
    double maxSofar = Double.MIN_VALUE;
    double minPrice = Double.MAX_VALUE;

    for (Double d : prices) {
      minPrice = Math.min(minPrice, d);
      maxSofar = Math.max(d - minPrice, maxSofar);
      maxProfits.add(maxSofar);
    }
    double maxPrice = Double.MIN_VALUE;
    for (int i = prices.size() - 1; i > 0; i--) {
      maxPrice = Math.max(maxPrice, prices.get(i));
      maxSofar = Math.max(maxPrice - prices.get(i) + maxProfits.get(i - 1),maxSofar);
    }
    return maxSofar;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
