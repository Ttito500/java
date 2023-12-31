import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Scanner;
interface Valuable {
    public String getLabel();
    public double getValue();
    public int getVolume();
}
enum Coin implements Valuable {
    C10( 0.10, 1, "M10"  ),
    C25( 0.25, 2, "M25"  ),
    C50( 0.50, 3, "M50"  ),
    C100( 1.00, 4, "M100" );

    private double value;
    private int volume;
    private String label;
    private Coin( double value, int volume, String label ) {
        this.value = value;
        this.volume = volume;
        this.label = label;
    }
    public double getValue() {
        return this.value;
    }
    public int getVolume() {
        return this.volume;
    }
    public String getLabel() {
        return this.label;
    }
    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return this.label + ":" + d.format(this.value) + ":" + this.volume;
    }
}
class Item implements Valuable {
    private String label;
    private double value;
    private int volume;
    public Item( String label, double value, int volume ) {
        this.label = label;
        this.value = value;
        this.volume = volume;
    }
    public String getLabel() {
        return this.label;
    }
    public double getValue() {
        return this.value;
    }
    public int getVolume() {
        return this.volume;
    }
    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return this.label + ":" + d.format(this.value) + ":" + this.volume;
    }
}
class Pig {
    private boolean broken;
    private List<Valuable> valuables;
    private int volumeMax;
    public Pig(int volumeMax) {
        this.broken = false;
        this.valuables = new ArrayList<Valuable>();
        this.volumeMax = volumeMax;
    }
    public Coin createCoin(String valor) {
        switch (valor) {
            case "10":
                return Coin.C10;
            case "25":
                return Coin.C25;
            case "50":
                return Coin.C50;
            case "100":
                return Coin.C100;
            default:
                return null;
        }
    }
    public boolean addValuable(Valuable valuable) throws Exception {
        if (this.broken) {
            throw new Exception("fail: the pig is broken");
        }
        if (valuable.getVolume() > this.getVolumeRestante()) {
            throw new Exception("fail: the pig is full");
        }
        this.valuables.add(valuable);
        return true;
    }
    public boolean breakPig() throws Exception {
        if (this.broken) {
            throw new Exception("fail: the pig is broken");
        }
        this.broken = true;
        return true;
    }
    public ArrayList<String> extractCoins() throws Exception {
        if (!this.broken) {
            throw new Exception("fail: you must break the pig first");
        }
        ArrayList<String> labels = new ArrayList<String>();
        for (Valuable v : this.valuables) {
            if ( v instanceof Coin ) {
                labels.add( v.toString() );
            }
        }
        for (int i=0; i<this.valuables.size(); i++) {
            Valuable v = this.valuables.get(i);
            if ( v instanceof Coin ) {
                this.valuables.remove( i );
                i--;
            }
        }
        return labels;
    }
    public ArrayList<String> extractItems() throws Exception {
        if (!this.broken) {
            throw new Exception("fail: you must break the pig first");
        }
        ArrayList<String> labels = new ArrayList<String>();
        for (Valuable v : this.valuables) {
            if ( v instanceof Item ) {
                labels.add( v.toString() );
            }
        }
        for (int i=0; i<this.valuables.size(); i++) {
            Valuable v = this.valuables.get(i);
            if ( v instanceof Item ) {
                this.valuables.remove( i );
                i--;
            }
        }
        return labels;
    }
    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        String s = "";
        s += this.valuables + " : ";
        s += d.format(this.getValue()) + "$ : ";
        s += this.getVolume() + "/" + this.getVolumeMax() + " : ";
        s += ((this.broken) ? "broken" : "intact");
        return s;
    }
    public int getVolume() {
        if (this.isBroken()) {
            return 0;
        }
        int volume = 0;
        for (Valuable v : this.valuables) {
            volume += v.getVolume();
        }
        return volume;
    }
    public double getValue() {
        double value = 0;
        for (Valuable v : this.valuables) {
            value += v.getValue();
        }
        return value;
    }
    public int getVolumeMax() {
        return this.volumeMax;
    }
    public int getVolumeRestante() {
        return this.getVolumeMax() - this.getVolume();
    }
    public boolean isBroken() {
        return this.broken;
    }
}
public class Solver {
    public static void main(String[] arg) {
        Pig pig = new Pig(5);
        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");
            try {
                if      (args[0].equals("end"))          { break; }
                else if (args[0].equals("init"))         { pig = new Pig( (int) number(args[1]) ); }
                else if (args[0].equals("show"))         { println(pig); }
                else if (args[0].equals("addCoin"))      { pig.addValuable( pig.createCoin( args[1] ) ); }
                else if (args[0].equals("addItem"))      { pig.addValuable( new Item( args[1], number(args[2]), (int) number(args[3]) ) ); }
                else if (args[0].equals("break"))        { pig.breakPig(); }
                else if (args[0].equals("extractCoins")) { println("[" + String.join(", ", pig.extractCoins()) + "]"); }
                else if (args[0].equals("extractItems")) { println("[" + String.join(", ", pig.extractItems()) + "]"); }
                else                                     { println("fail: comando invalido"); }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
    }
    private static Scanner scanner = new Scanner(System.in);
    private static String  input()                { return scanner.nextLine();        }
    private static double  number(String value)   { return Double.parseDouble(value); }
    public  static void    println(Object value)  { System.out.println(value);        }
    public  static void    print(Object value)    { System.out.print(value);          }
}
