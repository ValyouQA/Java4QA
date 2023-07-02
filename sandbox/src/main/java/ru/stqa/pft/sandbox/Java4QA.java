package ru.stqa.pft.sandbox;

public class Java4QA {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Alexey");

    Square s = new Square(5);
    System.out.println("Area of a square with a side " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Area of a rectangle with sides " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello (String somebody) {
    System.out.println ("Hello, " + somebody + "!");
  }



}