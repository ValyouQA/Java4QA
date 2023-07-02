package ru.stqa.pft.sandbox;

public class Distance {
  public static void main (String[] args) {
    Point p1 = new Point(4, 5);
    Point p2 = new Point(4, 7);
    System.out.println("Distance between points = " + p1.distance(p2));
  }

}
