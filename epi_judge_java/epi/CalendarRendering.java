package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sun.rmi.server.LoaderHandler;

public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint implements Comparable<Endpoint>{
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public int compareTo(final Endpoint o) {
      if (time != o.time) {
        return Integer.compare(time, o.time);
      }
      return isStart && !o.isStart ? -1 : !isStart && o.isStart ? 1 : 0;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> endpoints = new ArrayList<>();
    for(Event event : A) {
      endpoints.add(new Endpoint(event.start, true));
      endpoints.add(new Endpoint(event.finish, false));
    }
    Collections.sort(endpoints);

    int max = 0;
    int local = 0;
    for(Endpoint endpoint : endpoints) {
      if (endpoint.isStart) {
        local++;
        max = Math.max(max, local);
      } else {
        local--;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
