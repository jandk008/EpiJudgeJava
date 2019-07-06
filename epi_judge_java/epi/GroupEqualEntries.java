package epi;
import epi.IntervalAdd.Interval;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupEqualEntries {

  private static List<Integer> indices;
  private static Integer swapAge;

  @EpiUserType(ctorParams = {Integer.class, String.class})

  public static class Person {
    public Integer age;
    public String name;

    public Person(Integer k, String n) {
      age = k;
      name = n;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      Person person = (Person)o;

      if (!age.equals(person.age))
        return false;
      return name.equals(person.name);
    }

    @Override
    public int hashCode() {
      int result = age.hashCode();
      result = 31 * result + name.hashCode();
      return result;
    }
  }
  private static void groupByAge(List<Person> people) {
    Map<Integer, Integer> ageCount = new HashMap<>();
    for (Person person : people) {
      if (ageCount.containsKey(person.age)) {
        ageCount.put(person.age, ageCount.get(person.age) + 1);
      } else {
        ageCount.put(person.age, 1);
      }
    }

    // offset starting position for each age to swap with
    Map<Integer, Integer> ageOffset = new HashMap<>();
    int offset = 0;
    for (Entry<Integer, Integer> entry : ageCount.entrySet()) {
      ageOffset.put(entry.getKey(), offset);
      offset += entry.getValue();
    }

    while(!ageOffset.isEmpty()) {
      // each time trying to deal with the first entry which indicates the first position not swapped
      Entry<Integer, Integer> swapEntry = ageOffset.entrySet().iterator().next();
      int swapIndex = swapEntry.getValue();

      // check the age at the position of the original list and get the position we should swap with
      swapAge = people.get(swapIndex).age;
      int swapWithIndex = ageOffset.get(swapAge);

      Collections.swap(people, swapIndex, swapWithIndex);

      // update the count for the age swapped with
      int swapWithCount = ageCount.get(swapAge);
      swapWithCount--;
      ageCount.put(swapAge, swapWithCount);

      // remove the age from the remaining if the count reduced to 0
      if (swapWithCount > 0) {
        ageOffset.put(swapAge, swapWithIndex + 1);
      } else {
        ageOffset.remove(swapAge);
      }
    }
  }
  private static Map<Person, Integer> buildMultiset(List<Person> people) {
    Map<Person, Integer> m = new HashMap<>();
    for (Person p : people) {
      m.put(p, m.getOrDefault(p, 0) + 1);
    }
    return m;
  }

  @EpiTest(testDataFile = "group_equal_entries.tsv")
  public static void groupByAgeWrapper(TimedExecutor executor,
                                       List<Person> people) throws Exception {
    if (people.isEmpty()) {
      return;
    }
    Map<Person, Integer> values = buildMultiset(people);

    executor.run(() -> groupByAge(people));

    Map<Person, Integer> newValues = buildMultiset(people);
    if (!values.equals(newValues)) {
      throw new TestFailure("Entry set changed");
    }
    int lastAge = people.get(0).age;
    Set<Integer> ages = new HashSet<>();

    for (Person p : people) {
      if (ages.contains(p.age)) {
        throw new TestFailure("Entries are not grouped by age");
      }
      if (p.age != lastAge) {
        ages.add(lastAge);
        lastAge = p.age;
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GroupEqualEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
