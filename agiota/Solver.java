import java.awt.*;
import java.util.*;

class Operation {
    private static int nextOpId = 0;
    private int id;
    private String name;
    private Label label;
    private int value;

    public Operation( String name, Label label, int value ) {
        this.name = name;
        this.label = label;
        this.value = value;
        this.id = Operation.nextOpId++;
    }
    @Override
    public String toString() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }
}

enum label {
    GIVE,
    TAKE,
    PLUS;
}

class Client {
    private String name;
    private int limite;
    ArrayList<Operation> operations;

    public Client(String name, int limite) {
        this.name = name;
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", limite=" + limite +
                ", operations=" + operations +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getLimite() {
        return limite;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }
    //quanto esta devendo
    public int getBalance() {
    }
}


class Agiota {
    private ArrayList<Client> aliveList;
    private ArrayList<Client> deathList;
    private ArrayList<Operation> aliveOper;
    private ArrayList<Operation> deathOper;

    private int searchClient(String name) {
    }
    private void pushOperation(Client client, String name, Label label, int value) {
    }

    private void sortAliveList() {
        this.aliveList.sort( new Comparator<Client>() {
            public int compare(Client c1, Client c2) {

            }
        });
    }

    public Agiota(){

    }

    public Client getClient(String name) {
    }

    public void addClient(String name, int limite) {

        this.sortAliveList();
    }

    public void give(String name, int value) {
    }

    public void take(String name, int value) {
    }

    public void kill(String name) {
    }

    public void plus() {
    }

    @Override
    public String toString() {
    }

}

public class Solver {
    public static void main(String[] arg) {
        Agiota agiota = new Agiota();

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            if      (args[0].equals("end"))     { break; }
            else if (args[0].equals("init"))    { agiota = new Agiota(); }
            else if (args[0].equals("show"))    { print(agiota); }
            else if (args[0].equals("showCli")) { print( agiota.getClient( args[1] ) ); }
            else if (args[0].equals("addCli"))  { agiota.addClient( args[1], (int) number(args[2]) ); }
            else if (args[0].equals("give"))    { agiota.give( args[1], (int) number(args[2]) ); }
            else if (args[0].equals("take"))    { agiota.take( args[1], (int) number(args[2]) ); }
            else if (args[0].equals("kill"))    { agiota.kill( args[1] ); }
            else if (args[0].equals("plus"))    { agiota.plus(); }
            else                                { println("fail: comando invalido"); }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()                { return scanner.nextLine();        }
    private static double  number(String value)   { return Double.parseDouble(value); }
    public  static void    println(Object value)  { System.out.println(value);        }
    public  static void    print(Object value)    { System.out.print(value);          }
}
