package ru.stqa.geometry.figure;

import java.util.Objects;

public record Triangle(double sideOne, double sideTwo, double sideThree) {
  public Triangle {
    if ((sideOne <= 0) | (sideTwo <= 0) | (sideThree <= 0)) {
      throw new IllegalArgumentException("The length of the triangle side should be positive.");
    } else if ((sideOne >= sideTwo + sideThree) | (sideTwo >= sideOne + sideThree) | (sideThree >= sideOne + sideTwo)) {
      throw new IllegalArgumentException("The length of one side of the triangle should be less than the sum of the other two sides.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Triangle triangle = (Triangle) o;
    return (Double.compare(sideOne, triangle.sideOne) == 0 && Double.compare(sideTwo, triangle.sideTwo) == 0 && Double.compare(sideThree, triangle.sideThree) == 0)
            || (Double.compare(sideOne, triangle.sideTwo) == 0 && Double.compare(sideTwo, triangle.sideThree) == 0 && Double.compare(sideThree, triangle.sideOne) == 0)
            || (Double.compare(sideOne, triangle.sideThree) == 0 && Double.compare(sideTwo, triangle.sideOne) == 0 && Double.compare(sideThree, triangle.sideTwo) == 0);
  }

  @Override
  public int hashCode() {
    return 1;
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
