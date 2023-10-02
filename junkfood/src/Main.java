import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

class Slot {
    private String name;
    private float price;
    private int quantity;

    public Slot(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return String.format("%8s", this.name) + " : " +
                this.quantity + " U : " +
                Solver.decForm.format(this.price) + " RS";
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class VendingMachine {
    private ArrayList<Slot> slots;
    private float profit;
    private float cash;
    private int capacity;

    public VendingMachine(int capacity) {
        this.profit = 0;
        this.cash = 0;
        this.capacity = capacity;
        slots = new ArrayList<Slot>();
        for (int i = 0; i < capacity; i++) {
            this.slots.add(new Slot("empty", 0, 0));
        }
    }

    private boolean indiceInvalido(int ind) {
        return ind < 0 || ind >= this.slots.size();
    }

    public Slot getSlot(int ind) {
        if (this.indiceInvalido(ind)) {
            return null;
        }

        return this.slots.get(ind);
    }

    public void setSlot(int ind, Slot slot) {
        if (this.indiceInvalido(ind)) {
            System.out.println("fail: indice nao existe");
            return;
        }

        this.slots.set(ind, slot);
    }

    public void clearSlot(int ind) {
        this.setSlot(ind, new Slot("empty", 0, 0));
    }

    public void insertCash(float cash) {
        this.cash += cash;
    }

    public float getCash() {
        return cash;
    }

    public float withdrawCash() {
        float qtd = cash;
        Solver.println("voce recebeu " + Solver.decForm.format(this.cash) + " RS");
        cash = 0;
        return qtd;
    }

    public float getProfit() {
        return profit;
    }

    public void buyItem(int ind) {
        if (!indiceInvalido(ind)) {
            Slot selectedSlot = slots.get(ind);
            if (selectedSlot.getPrice() <= cash && selectedSlot.getQuantity() > 0) {
                selectedSlot.setQuantity(selectedSlot.getQuantity() - 1);
                cash -= selectedSlot.getPrice();
                profit += selectedSlot.getPrice();
                System.out.printf("voce comprou um %s\n", selectedSlot.getName());
            } else if (selectedSlot.getQuantity() == 0) {
                System.out.println("fail: espiral sem produtos");
            } else {
                System.out.println("fail: saldo insuficiente");
            }
        } else {
            System.out.println("fail: indice nao existe");
        }
    }

    public String toString() {
        String s = "saldo: " + Solver.decForm.format(this.cash) + "\n";
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = this.getSlot(i);
            s += i + " [" + slot + "]\n";
        }
        return s;
    }
}

class Solver {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(0);

        while (true) {
            String linha = input();
            String[] palavras = linha.split(" ");
            println("$" + linha);

            if (palavras[0].equals("end")) {
                break;
            } else if (palavras[0].equals("init")) {
                machine = new VendingMachine(Integer.parseInt(palavras[1]));
            } else if (palavras[0].equals("show")) {
                print(machine);
            } else if (palavras[0].equals("set")) {
                if (palavras.length == 5) {
                    machine.setSlot(Integer.parseInt(palavras[1]), new Slot(palavras[2], Float.parseFloat(palavras[4]), Integer.parseInt(palavras[3])));
                } else {
                    System.out.println("fail: comando invalido");
                }
            } else if (palavras[0].equals("limpar")) {
                if (palavras.length == 2) {
                    machine.clearSlot(Integer.parseInt(palavras[1]));
                } else {
                    System.out.println("fail: comando invalido");
                }
            } else if (palavras[0].equals("apurado")) {
                println("apurado total: " + decForm.format(machine.getProfit()));
            } else if (palavras[0].equals("dinheiro")) {
                if (palavras.length == 2) {
                    machine.insertCash(Float.parseFloat(palavras[1]));
                } else {
                    System.out.println("fail: comando invalido");
                }
            } else if (palavras[0].equals("troco")) {
                machine.withdrawCash();
            } else if (palavras[0].equals("comprar")) {
                if (palavras.length == 2) {
                    machine.buyItem(Integer.parseInt(palavras[1]));
                } else {
                    System.out.println("fail: comando invalido");
                }
            } else {
                println("comando inválido!");
            }
        }
    }

    public static Scanner scan = new Scanner(System.in);

    public static String input() {
        return scan.nextLine();
    }

    public static void println(Object str) {
        System.out.println(str);
    }

    public static void print(Object str) {
        System.out.print(str);
    }

    public static DecimalFormat decForm = new DecimalFormat("0.00");
}
