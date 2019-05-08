package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.
    final ArrayList<Integer> primes = new ArrayList<>();
    boolean[] isPrimes = new boolean[n + 1];
    Arrays.fill(isPrimes, true);
    for (int i = 2; i <= n; i++) {
      if (isPrimes[i]) {
        primes.add(i);
        for (int j = i + i; j <= n; j +=i) {
          isPrimes[j] = false;
        }
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
