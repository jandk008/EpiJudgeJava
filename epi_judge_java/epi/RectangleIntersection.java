package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rectangle rectangle = (Rectangle)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {
    // TODO - you fill in here.
    int r1xs = R1.x;
    int r1xe = r1xs + R1.width;
    int r2xs = R2.x;
    int r2xe = r2xs + R2.width;
    int maxXs = Math.max(r1xs, r2xs);
    int minXe = Math.min(r1xe, r2xe);

    int r1ys = R1.y;
    int r1ye = r1ys + R1.height;
    int r2ys = R2.y;
    int r2ye = r2ys + R2.height;
    int maxYs = Math.max(r1ys, r2ys);
    int minYe = Math.min(r1ye, r2ye);

    if (maxXs <= minXe && maxYs <= minYe) {
      return new Rectangle(maxXs, maxYs, minXe - maxXs, minYe - maxYs);
    } else {
      return new Rectangle(0,0, -1, -1);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
