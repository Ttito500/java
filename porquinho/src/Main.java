import java.util.*;
import java.util.stream.Collectors;

enum Coin {
    C10(0.1, 1, "C10"),
    C25(0.25, 2, "C25"),
    C50(0.5, 3, "C50"),
    C100(1, 4, "C100");

    double value;
    int volume;
    String label;

    Coin(double value, int volume, String label) {
        this.value = value;
        this.volume = volume;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                ", volume=" + volume +
                ", label='" + label + '\'' +
                '}';
    }
}

class Item {

    private String label;
    private int volume;

    public Item(String label, int volume) {
        this.label = label;
        this.volume = volume;
    }

    public String getLabel() {
        return label;
    }

    public int getVolume() {
        return volume;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Item{" +
                "label='" + label + '\'' +
                ", volume=" + volume +
                '}';
    }
}


class Pig {

    private boolean broken;
    private List<Coin> coins;
    private List<Item> items;
    private int volumeMax;

    public Pig(int volumeMax) {
        this.volumeMax = volumeMax;
        broken = false;
        coins = new ArrayList<>();
        items = new ArrayList<>();

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

    public boolean addCoin(Coin coin) throws Exception {
        if (!broken){
            if (getVolume() + coin.volume <= volumeMax){
                coins.add(coin);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addItem(Item item) throws Exception {

    }

    public boolean breakPig() {
        if (broken){
            return false;
        }else {
            this.broken = true;
            return true;
        }
    }

    public ArrayList<String> extractCoins() throws Exception {
    }

    public ArrayList<String> extractItems() throws Exception {
    }

    @Override
    public String toString() {
    }

    public int getVolume() {
        if (coins != null){
            int volume = 0;
            for (Coin coin : coins) {
                volume += coin.volume;
            }
            for (Item item : items) {
                volume += item.getVolume();
            }
            return volume;
        } else {
            return 0;
        }
    }

    public double getValue() {
        if (coins != null){
            double value = 0;
            for (Coin coin : coins) {
                value += coin.value;
            }
            return value;
        } else {
            return 0;
        }
    }

    public int getVolumeMax() {
        return volumeMax;
    }

    public int getVolumeRestante() {
        return volumeMax - getVolume();
    }

    public boolean isBroken() {
        return broken;
    }
}

public class Solver {
    public static void main(String[] arg) {
        Pig pig = new Pig(5);

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            if      (args[0].equals("end"))          { break; }
            else if (args[0].equals("init"))         { pig = new Pig( (int) number(args[1]) ); }
            else if (args[0].equals("show"))         { println(pig); }
            else if (args[0].equals("addCoin"))      { pig.addCoin( pig.createCoin( args[1] ) ); }
            else if (args[0].equals("addItem"))      { pig.addItem( new Item( args[1], (int) number(args[2]) ) ); }
            else if (args[0].equals("break"))        { pig.breakPig(); }
            else if (args[0].equals("extractCoins")) { println("[" + String.join(", ", pig.extractCoins()) + "]"); }
            else if (args[0].equals("extractItems")) { println("[" + String.join(", ", pig.extractItems()) + "]"); }
            else                                     { println("fail: comando invalido"); }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()                { return scanner.nextLine();        }
    private static double  number(String value)   { return Double.parseDouble(value); }
    public  static void    println(Object value)  { System.out.println(value);        }
    public  static void    print(Object value)    { System.out.print(value);          }
}
