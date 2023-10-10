import java.util.*;

enum Label{
    GIVE,
    TAKE,
    PLUS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
class Operation {
    private static int nextOpId = 0;
    private int id;
    private String name;
    private Label label;
    private int value;

    public Operation( String name, Label label, int value ) {
        this.id = Operation.nextOpId++;
        this.name = name;
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "id:" + this.id + " " + this.label + ":" + this.name + " " + this.value;
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

    public int getValue() {
        return value;
    }
}

class Client {
    private String name;
    private int limite;
    ArrayList<Operation> operations;

    public Client(String name, int limite) {
        this.name = name;
        this.limite = limite;
        this.operations = new ArrayList<Operation>();
    }

    @Override
    public String toString() {
        String out = this.name + " " + this.getBalance() + "/" + this.limite + "\n";
        for ( Operation oper : this.operations ) {
            out += oper + "\n";
        }
        return out;
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
        operations.add(operation);
    }
    //quanto esta devendo
    public int getBalance() {
        int balance = 0;
        for (int i = 0; i < operations.size(); i++){
            if (operations.get(i).getLabel().equals("take") || operations.get(i).getLabel().equals("plus")){
                balance -= operations.get(i).getValue();
            } else {
                balance += operations.get(i).getValue();
            }
        }
        return balance;
    }
}


class Agiota {
    private ArrayList<Client> aliveList;
    private ArrayList<Client> deathList;
    private ArrayList<Operation> aliveOper;
    private ArrayList<Operation> deathOper;

    private int searchClient(String name) {
        for (int i = 0; i < this.aliveList.size(); i++){
            if(this.aliveList.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    private void pushOperation(Client client, String name, Label label, int value) {
        Operation op = new Operation(name, label, value);
        this.aliveOper.add(op);
        client.addOperation(op);
    }

    private void sortAliveList() {
        this.aliveList.sort( new Comparator<Client>() {
            public int compare(Client c1, Client c2) {
                return c1.getName().compareTo( c2.getName() );
            }
        });
    }

    public Agiota() {
        this.aliveList = new ArrayList<Client>();
        this.deathList = new ArrayList<Client>();
        this.deathOper = new ArrayList<Operation>();
        this.aliveOper = new ArrayList<Operation>();
    }

    public Client getClient(String name) {
        int ind = this.searchClient(name);

        if (ind == -1) {
            return null;
        }

        return this.aliveList.get(ind);
    }

    public void addClient(String name, int limite) throws Exception {
        if (searchClient(name) == -1) { // Check if the client does not exist
            Client novo = new Client(name, limite);
            aliveList.add(novo);
            this.sortAliveList();
        } else {
            throw new Exception("fail: cliente ja existe");
        }
    }

    public void give(String name, int value) throws Exception {
        if (searchClient(name) != -1) {
            Operation give = new Operation(name, Label.GIVE, value);
            getClient(name).addOperation(give);
        } else {
            throw new Exception("fail: cliente nao existe");
        }
    }

    public void take(String name, int value) {
        Operation give = new Operation(name, Label.GIVE, value);
        getClient(name).addOperation(give);
    }

    public void kill(String name) {
        deathList.add(getClient(name));
        aliveList.remove(getClient(name));
    }

    public void plus() {
        for(Client clientes : aliveList){
            int value = clientes.getBalance() + clientes.getBalance() /10;
            Operation juro = new Operation(clientes.getName(), Label.PLUS, value);
            clientes.addOperation(juro);
        }


    }

    @Override
    public String toString() {
        String ss = "";
        for ( Client client : this.aliveList ) {
            ss += ":) " + client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n";
        }
        for ( Operation oper : this.aliveOper ) {
            ss += "+ " + oper + "\n";
        }
        for ( Client client : this.deathList ) {
            ss += ":( " + client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n";
        }
        for ( Operation oper : this.deathOper ) {
            ss += "- " + oper + "\n";
        }
        return ss;
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
            else if (args[0].equals("addCli"))  {
                try {
                    agiota.addClient( args[1], (int) number(args[2]) );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (args[0].equals("give"))    {
                try {
                    agiota.give( args[1], (int) number(args[2]) );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
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
