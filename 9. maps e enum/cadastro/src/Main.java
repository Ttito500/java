import java.util.*;
import java.text.DecimalFormat;

class Client {
    private String clientId;
    private ArrayList<Account> accounts;

    public Client( String clientId ) {
        this.clientId = clientId;
        accounts = new ArrayList<>();
    }

    public void addAccount( Account acc ) {
        this.accounts.add(acc);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public String getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}

abstract class Account {
    protected double balance;
    private static int nextAccountId = 0;
    protected int accId;
    protected String clientId;
    protected String typeId;

    public Account(String clientId, String typeId) {
        this.clientId = clientId;
        this.typeId = typeId;
        this.balance = 0;
        this.accId = nextAccountId;
    }

    public void deposit(double value ) {
        this.balance += value;
    }
    public void withdraw( double value ) {
        this.balance -= value;
    }
    public void transfer( Account other, double value ) {
        if (value <= balance){
            other.deposit(value);
            balance -= value;
        }
    }

    @Override
    public String toString() {
        //        DecimalFormat d = new DecimalFormat("0.00"); //double x = 4.3; System.out.println( d.format(x) ); //4.30
        return "Account{" +
                "balance=" + balance +
                ", accId=" + accId +
                ", clientId='" + clientId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return accId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTypeId() {
        return typeId;
    }

    public abstract void updateMonthly();
}

class Agency {
    private Map<Integer, Account> accounts;
    private Map<String, Client> clients;

    private Account getAccount(int accountId) throws Exception {
        if (accounts.containsKey(accountId)){
            return accounts.get(accountId);
        } else {
            throw new Exception();
        }
    }

    public Agency() {
        this.accounts = new HashMap<Integer,Account>();
        // this.clients = new HashMap<String,Client>();
        this.clients = new LinkedHashMap<String,Client>();
    }

    // cria uma conta para o cliente
    // cria um objeto cliente e insere no mapa de clientes
    // cria uma conta corrente e uma conta poupança e insere no mapa de contas
    // faz o vínculo cruzado colocando as contas dentro do objeto do cliente
    public void addClient(String clientId) {
    }

    // procura pela conta usando o getAccount e realiza a operação de depósito
    // utiliza o método deposit da classe Account
    public void deposit(int accId, double value) {
    }

    // procura pela conta e realiza a operação de saque
    // utiliza o método withdraw da classe Account
    public void withdraw(int accId, double value) {
    }

    // procura pela conta e realiza a operação de transferência
    // utiliza o método transfer da classe Account
    public void transfer(int fromAccId, int toAccId, double value) {
    }

    // realiza a operação de atualização mensal em todas as contas
    public void updateMonthly() {
    }

    @Override
    public String toString() {
        String s = "- Clients\n";
        for ( Client client : this.clients.values() ) {
            s += client;
        }
        s += "- Accounts\n";
        for ( Account acc : this.accounts.values() ) {
            s += acc;
        }
        return s;
    }
}

public class Solver {
    public static void main(String[] arg) {
        Agency agency = new Agency();

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            try {
                if      (args[0].equals("end"))       { break; }
                else if (args[0].equals("show"))      { print(agency); }
                else if (args[0].equals("addCli"))    { agency.addClient( args[1] ); }
                else if (args[0].equals("deposito"))  { agency.deposit( (int) number(args[1]), number(args[2]) ); }
                else if (args[0].equals("saque"))     { agency.withdraw( (int) number(args[1]), number(args[2]) ); }
                else if (args[0].equals("transf"))    { agency.transfer( (int) number(args[1]), (int) number(args[2]), number(args[3]) ); }
                else if (args[0].equals("update"))    { agency.updateMonthly(); }
                else                                { println("fail: comando invalido"); }
            } catch (Exception e) {
                println( e.getMessage() );
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()                { return scanner.nextLine();        }
    private static double  number(String value)   { return Double.parseDouble(value); }
    public  static void    println(Object value)  { System.out.println(value);        }
    public  static void    print(Object value)    { System.out.print(value);          }
}