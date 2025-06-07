package ru.stqa.geometry.figure;

public record Triangle(double sideOne,double sideTwo, double sideThree) {
  public Triangle {
    if ((sideOne <= 0) | (sideTwo <= 0) | (sideThree <= 0)) {
      throw new IllegalArgumentException("The length of the triangle side should be positive.");
    } else if ((sideOne >= sideTwo + sideThree) | (sideTwo >= sideOne + sideThree) | (sideThree >= sideOne + sideTwo)) {
      throw new IllegalArgumentException("The length of one side of the triangle should be less than the sum of the other two sides.");
    }
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
