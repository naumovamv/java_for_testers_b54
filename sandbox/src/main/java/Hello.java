import ru.stqa.geometry.figure.Triangle;

public class Hello {
  public static void main(String[] args) {
    System.out.println("Hello, world!");

    Triangle t= new Triangle(3.0, 4.0, 5.0);
    System.out.println(String.format("Периметр треугольника со сторонами %f, %f и %f равен %f, а площадь данного треугольника равна %f",
            t.getSideOne(), t.getSideTwo(), t.getSideThree(),t.calculatePerimeter(),t.calculateSquare()));
  }
}
