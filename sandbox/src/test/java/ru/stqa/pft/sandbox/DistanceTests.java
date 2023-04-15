package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void isEqual() {
    Point p1 = new Point(4, 5);
    Point p2 = new Point(4, 7);
    Assert.assertEquals ( p1.distance(p2), 2);
  }
  @Test
  public void notNull() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(5, 6);
    Assert.assertNotNull (p1.distance(p2));
  }
  @Test
  public void isNull() {
    Point p1 = new Point(7, 8);
    Point p2 = new Point(9, 10);
    Assert.assertNull (p1.distance(p2));
  }
}
