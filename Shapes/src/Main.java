import java.util.*;
import java.text.DecimalFormat;

class Point2D {
    public double x;
    public double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point2D p) {
        double cH = this.x - p.x;
        double cV = this.y - p.y;
        return Math.sqrt(cH * cH + cV * cV);
    }

    @Override
    public String toString() {
        return "";
    }
}

abstract class Shape {
    public String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean inside(Point2D p);

    public abstract double getArea();

    public abstract double getPerimeter();

    public String getInfo() {
        DecimalFormat d = new DecimalFormat("0.00");
        if (name.equals("circle")) {
            return "Circ: A=" + d.format(getArea()) + " P=" + d.format(getPerimeter());
        } else {
            return "Rect: A=" + d.format(getArea()) + " P=" + d.format(getPerimeter());
        }
    }

    @Override
    public String toString() {
        return "";
    }
}

class Circle extends Shape {
    public Point2D center;
    public double radius;

    public Circle(Point2D center, double radius) {
        super("circle");
        this.center = center;
        this.radius = radius;
    }

    public boolean inside(Point2D p) {
        return p.distance(center) <= radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return "Circ" + ": " + "C=(" + d.format(this.center.x) + ", " + d.format(this.center.y) + "), R=" + d.format(this.radius);
    }
}

class Rectangle extends Shape {
    public Point2D p1;
    public Point2D p2;

    public Rectangle(Point2D p1, Point2D p2) {
        super("rectangle");
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean inside(Point2D p) {
        return p.x >= Math.min(p1.x, p2.x) && p.x <= Math.max(p1.x, p2.x) && p.y >= Math.min(p1.y, p2.y) && p.y <= Math.max(p1.y, p2.y);
    }

    public double getArea() {
        Point2D ponto3 = new Point2D(p1.x, p2.y);
        return p1.distance(ponto3) * p2.distance(ponto3);
    }

    public double getPerimeter() {
        Point2D ponto3 = new Point2D(p1.x, p2.y);
        return p1.distance(ponto3) * 2 + p2.distance(ponto3) * 2;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00"); //double x = 4.3; System.out.println( d.format(x) ); //4.30
        return "Rect" + ": " + "P1=(" + d.format(this.p1.x) + ", " + d.format(this.p1.y) + ") P2=(" + d.format(this.p2.x) + ", " + d.format(this.p2.y) + ")";
    }
}

public class Solver {
    public static void main(String[] arg) {
        Circle shape0 = new Circle(new Point2D(0, 0), 0);
        Rectangle shape1 = new Rectangle(new Point2D(0, 0), new Point2D(0, 0));
        ArrayList<Shape> shapes = new ArrayList<>();

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("circle")) {
                shapes.add(new Circle(new Point2D(Double.parseDouble(args[1]), Double.parseDouble(args[2])), Double.parseDouble(args[3])));
            } else if (args[0].equals("rect")) {
                shapes.add(new Rectangle(new Point2D(Double.parseDouble(args[1]), Double.parseDouble(args[2])), new Point2D(Double.parseDouble(args[3]), Double.parseDouble(args[4]))));
            } else if (args[0].equals("show")) {
                showAll(shapes);
            } else if (args[0].equals("info")) {
                infoAll(shapes);
            } else {
                println("fail: comando invalido");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static double number(String value) {
        return Double.parseDouble(value);
    }

    public static void println(Object value) {
        System.out.println(value);
    }

    public static void print(Object value) {
        System.out.print(value);
    }

    public static void showAll(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            println(s);
        }
    }

    public static void infoAll(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            println(s.getInfo());
        }
    }
}
