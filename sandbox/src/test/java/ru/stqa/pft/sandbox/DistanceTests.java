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
  public void positiveResponse() {
    Point p1 = new Point(-5, -4);
    Point p2 = new Point(-5, 6);
    Assert.assertEquals ( p1.distance(p2), 10);
  }
  @Test
  public void changePoints() {
    Point p1 = new Point(7, 8);
    Point p2 = new Point(9, 10);
    Assert.assertEquals ( p1.distance(p2), p2.distance(p1));
  }
}
