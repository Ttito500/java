import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) {
        //Animal bixo = new Animal(); //Coisa a ser manipulada******
        Animal bixo = null;
        while (true) {
            var line = input();         //lê a linha
            var args = line.split(" "); // quebra em array de palavras
            write("$" + line);          //mostra '$' na frente

            if (Objects.equals(args[0], "init")) {
                bixo = new Animal(args[1], args[2]);
            } else if (Objects.equals(args[0], "show")) {
                if (bixo == null){
                    System.out.println("defina bixo");
                }else bixo.show();
            } else if (Objects.equals(args[0], "grow")) {
                bixo.envelhecer(Integer.parseInt(args[1]));
            } else if (Objects.equals(args[0], "noise")){
                bixo.fazerBarulho();
            }

            else if (Objects.equals(args[0], "end")) {
                break;
            } else {
                write("fail: comando invalido");
            }
        }
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}

class Animal{
    String especie;
    String barulho;
    int idade;

    public Animal(String especie, String barulho) {
        this.especie = especie;
        this.barulho = barulho;
    }

    void show(){
        System.out.printf("%s:%d:%s\n", especie, idade, barulho);
    }

    void fazerBarulho(){
        if (idade > 3){
            System.out.println("RIP");
        }else if (idade > 0) {
            System.out.println(barulho);
        } else {
            System.out.println("---");
        }
    }

    void envelhecer(int value){
        idade += value;
        if (idade > 3){
            System.out.printf("warning: %s morreu\n", especie);
        }
        if (idade > 4){
            idade = 4;
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "especie='" + especie + '\'' +
                ", barulho='" + barulho + '\'' +
                ", idade=" + idade +
                '}';
    }
}