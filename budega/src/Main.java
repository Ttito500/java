import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Pessoa {
    private String name;

    public Pessoa(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

class Mercantil {
    private List<Pessoa> caixas;
    private List<Pessoa> esperando;

    public Mercantil() {
        caixas = new ArrayList<>();
        esperando = new ArrayList<>();
    }

    public Mercantil(int qtdCaixas) {
        caixas = new ArrayList<>(qtdCaixas);
        esperando = new ArrayList<>();

        for (int i = 0; i < qtdCaixas; i++) {
            caixas.add(null);
        }
    }

    private boolean validarIndice(int indice) {
        if (indice >= 0 && indice < caixas.size()) {
            return true;
        }
        System.out.println("fail: caixa inexistente");
        return false;
    }

    public String toString() {
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i) == null) {
                caixas.set(i, new Pessoa("-----"));
            }
        }

        return "Caixas: " + caixas.toString() + "\nEspera: " + esperando.toString();
    }

    public void chegar(Pessoa pessoa) {
        esperando.add(pessoa);
    }

    public boolean chamar(int indice) {
        if (validarIndice(indice) && !esperando.isEmpty()) {
            if (caixas.get(indice).getName().equals("-----")) {
                Pessoa cliente = esperando.remove(0);
                caixas.set(indice, cliente);
                return true;
            } else {
                System.out.println("fail: caixa ocupado");
            }
        } else {
            System.out.println("fail: sem clientes");
        }
        return false;
    }

    public Pessoa finalizar(int indice) {
        if (validarIndice(indice)) {
            Pessoa cliente = caixas.get(indice);
            if (!cliente.getName().equals("-----")) {
                caixas.set(indice, null);
                return cliente;
            } else {
                System.out.println("fail: caixa vazio");
            }
        }
        return null;
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Mercantil mercantil = new Mercantil();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            var arguments = line.split(" ");
            System.out.println('$' + line);

            if ("end".equals(arguments[0])) {
                break;
            } else if ("init".equals(arguments[0])) {
                int numCaixas = Integer.parseInt(arguments[1]);
                mercantil = new Mercantil(numCaixas);
            } else if ("show".equals(arguments[0])) {
                System.out.println(mercantil);
            } else if ("call".equals(arguments[0])) {
                int indice = Integer.parseInt(arguments[1]);
                mercantil.chamar(indice);
            } else if ("finish".equals(arguments[0])) {
                int indice = Integer.parseInt(arguments[1]); // Convert the second argument to an integer
                Pessoa cliente = mercantil.finalizar(indice);
                if (cliente != null) {
                    System.out.println("Cliente finalizado: " + cliente.toString()); // Print the message
                }
            } else if ("arrive".equals(arguments[0])) {
                mercantil.chegar(new Pessoa(arguments[1]));
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
