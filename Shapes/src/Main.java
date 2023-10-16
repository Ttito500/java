import java.util.*;
import java.text.DecimalFormat;

class Point2D {
    public double x;
    public double y;

    public Point2D( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    public double distance( Point2D p ) {
        double cH = this.x - p.x;
        double cV = this.y - p.y;
        return Math.sqrt( cH*cH + cV*cV );
    }

    @Override
    public String toString() {
        return "";
    }
}

 abstract class Shape {
     public String name;

     public Shape( String name ) {
         this.name = name;
     }

     public String getName() {
         return this.name;
     }

     public abstract boolean inside( Point2D p );   
     public abstract double getArea();
     public abstract double getPerimeter();

     public String getInfo() { //retorna area e o perimetro
         DecimalFormat d = new DecimalFormat("0.00"); //double x = 4.3; System.out.println( d.format(x) ); //4.30
         return "";
     }
     @Override
     public String toString() {
         return "";
     }
 }

class Circle extends Shape {
    public Point2D center;
    public double radius;

    public Circle( Point2D center, double radius ) {
        super("circle");
        this.center = center;
        this.radius = radius;
    }

    public boolean inside( Point2D p ) {
        if (p.distance(center) <= radius){
            return true;
        } else {
            return false;
        }
    }
    public double getArea() {
        return 3.14f * radius * radius;
    }
    public double getPerimeter() {
        return 2 * 3.14f * radius;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00"); //double x = 4.3; System.out.println( d.format(x) ); //4.30
        return "Circ" + ": " + "C=(" + d.format(this.center.x) + ", " + d.format(this.center.y) + "), R=" + d.format(this.radius);
    }
}

 class Rectangle extends Shape {
     public Point2D p1;
     public Point2D p2;

     public Rectangle( Point2D p1, Point2D p2 ) {
         super("rectangle");
         this.p1 = p1;
         this.p2 = p2;
     }

     public boolean inside( Point2D p ) {
         return false;
     }
     public double getArea() {

         return ;
     }
     public double getPerimeter() {
         return 0.0;
     }

     @Override
     public String toString() {
         DecimalFormat d = new DecimalFormat("0.00"); //double x = 4.3; System.out.println( d.format(x) ); //4.30
         return "";
     }
 }

public class Solver {
    public static void main(String[] arg) {
        Circle shape0 = new Circle( new Point2D(0,0), 0 );
        Rectangle shape1 = new Rectangle( new Point2D(0,0), new Point2D(0,0) );
        ArrayList<Shape> shapes = null;

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            if      (args[0].equals("end"))       { break; }
            else if (args[0].equals("circle"))    { shape0 = new Circle( new Point2D( number(args[1]), number(args[2]) ), number(args[3]) ); }
            else if (args[0].equals("rect"))      { shape1 = new Rectangle( new Point2D( number(args[1]), number(args[2]) ), new Point2D( number(args[3]), number(args[4]) ) ); }
            else if (args[0].equals("show"))      { println(shape0); }
            else if (args[0].equals("circle"))    { shapes.add( new Circle( new Point2D( number(args[1]), number(args[2]) ), number(args[3]) ) ); }
            else if (args[0].equals("rect"))      { shapes.add( new Rectangle( new Point2D( number(args[1]), number(args[2]) ), new Point2D( number(args[3]), number(args[4]) ) ) ); }
            else if (args[0].equals("show"))      { showAll( shapes ); }
            else if (args[0].equals("info"))      { infoAll( shapes ); }
            else                                { println("fail: comando invalido"); }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()                { return scanner.nextLine();        }
    private static double  number(String value)   { return Double.parseDouble(value); }
    public  static void    println(Object value)  { System.out.println(value);        }
    public  static void    print(Object value)    { System.out.print(value);          }
    public  static void showAll( ArrayList<Shape> shapes ) { for ( Shape s : shapes ) println( s ); }
    public  static void infoAll( ArrayList<Shape> shapes ) { for ( Shape s : shapes ) println( s.getInfo() ); }
}
