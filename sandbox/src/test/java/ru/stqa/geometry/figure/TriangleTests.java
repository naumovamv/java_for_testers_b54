package ru.stqa.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

  @Test
  void canCalculatePerimeter() {
    Assertions.assertEquals(12.0, (new Triangle(3.0, 4.0, 5.0)).calculatePerimeter());
  }

  @Test
  void canCalculateSquare() {
    Assertions.assertEquals(6.0, (new Triangle(3.0, 4.0, 5.0)).calculateSquare());
  }

  @Test
  void cannotCreateTriangleWithNegativeSide() {
    try {
         new Triangle(-5.0, 6.0, 7.0);
      Assertions.fail();
    } catch (IllegalArgumentException exception) {
      // OK
    }
  }
  
  @Test
  void cannotCreateTriangleWhenTriangleRuleIsViolated() {
    try {
      new Triangle(10.0, 3.0, 3.0);
      Assertions.fail();
    } catch (IllegalArgumentException exception) {
      // OK
    }
  }
}
