package problem_1;

public class Circle {

  private double x;
  private double y;
  private double radius;
  private final double MAX_RADIUS = 100.0; // Maximum radius, change as needed

  // Default constructor
  public Circle() {
    this.x = 0.0;
    this.y = 0.0;
    this.radius = 1.0; // Default radius
    setRadius(this.radius); // Set radius with check
  }

  // Constructor with parameters
  public Circle(double x, double y, double radius) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    setRadius(this.radius); // Set radius with check
  }

  // Method to calculate circumference
  public double circumference() {
    return 2 * Math.PI * radius;
  }

  // Method to calculate area
  public double area() {
    return Math.PI * radius * radius;
  }

  // Method to set radius with a maximum check
  public void setRadius(double r) {
    this.radius = (r > MAX_RADIUS) ? MAX_RADIUS : r;
  }

  // Method to print attributes
  public void printAttributes() {
    System.out.println("Coordinates: (" + x + ", " + y + ")");
    System.out.println("Radius: " + radius);
    System.out.println("Circumference: " + circumference());
    System.out.println("Area: " + area());
  }

  // Method to check if a point is inside the circle
  public boolean isInside(double xPoint, double yPoint) {
    double distance = Math.sqrt(
      Math.pow((xPoint - x), 2) + Math.pow((yPoint - y), 2)
    );
    return distance < radius;
  }

  // Method to move the origin
  public void move(int xMove, int yMove) {
    this.x += xMove;
    this.y += yMove;
  }

  // Test the Circle class
  public static void main(String[] args) {
    Circle circle1 = new Circle();
    Circle circle2 = new Circle(3.0, 4.0, 5.0);

    circle1.printAttributes();
    System.out.println(
      "Is point (1,1) inside circle1: " + circle1.isInside(1, 1)
    );
    circle1.move(2, 3);
    circle1.printAttributes();

    circle2.printAttributes();
    System.out.println(
      "Is point (6,6) inside circle2: " + circle2.isInside(6, 6)
    );
    circle2.move(-1, -1);
    circle2.printAttributes();
  }
}
