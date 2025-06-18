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
  @Test
  void testEquality() {
    Triangle t1 = new Triangle (3.0, 4.0, 5.0);
    Triangle t2 = new Triangle (3.0, 4.0, 5.0);
    Assertions.assertEquals(t1, t2);
  }

  @Test
  void testEquality2() {
    Triangle t1 = new Triangle (3.0, 4.0, 5.0);
    Triangle t2 = new Triangle (4.0, 5.0, 3.0);
    Assertions.assertEquals(t1, t2);
  }

  @Test
  void testNotEquality() {
    Triangle t1 = new Triangle (3.0, 4.0, 5.0);
    Triangle t2 = new Triangle (7.0, 8.0, 9.0);
    Assertions.assertNotEquals(t1, t2);
  }

  @Test
  void testEquality22() {
    var t1 = new Triangle(5, 6, 7);
    var t2 = new Triangle(5, 5, 5);
    Assertions.assertNotEquals(t1, t2);
  }

  @Test
  void testEquality23() {
    var t1 = new Triangle(5, 5, 5);
    var t2 = new Triangle(5, 6, 7);
    Assertions.assertNotEquals(t1, t2);
  }

  @Test
  void testEquality24() {

    var t1 = new Triangle(5, 6, 7);
    var t2 = new Triangle(5, 5, 5);
    Assertions.assertNotEquals(t1, t2);
  }
}
