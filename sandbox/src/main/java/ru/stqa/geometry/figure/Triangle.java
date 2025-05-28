package ru.stqa.geometry.figure;

public class Triangle {
  private double sideOne;
  private double sideTwo;
  private double sideThree;

  public Triangle(double sideOne, double sideTwo, double sideThree) {
    this.sideOne = sideOne;
    this.sideTwo = sideTwo;
    this.sideThree = sideThree;
  }

  public double getSideOne() {
    return sideOne;
  }

  public double getSideTwo() {
    return sideTwo;
  }

  public double getSideThree() {
    return sideThree;
  }

  public double calculatePerimeter() {
    return sideOne + sideTwo + sideThree;
  }

  public double calculateSquare() {
    double p = calculatePerimeter() / 2;
    return Math.sqrt(p * ((p - sideOne) * (p - sideTwo) * (p - sideThree)));
  }
}
