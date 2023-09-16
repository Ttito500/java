import java.util.Scanner;

public class Main {
    int bateria;
    int bateriamax;
    float display;

    public static void main(String[] args) {
        Main calc = new Main();
        calc.menu();
    }

    void menu() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] parts = input.split(" ");

        if (parts[0].equals("init")) {
            init(Integer.parseInt(parts[1]));
        } else if (parts[0].equals("show")) {
            show();
        } else if (parts[0].equals("charge")) {
            charge(Integer.parseInt(parts[1]));
        } else if (parts[0].equals("sum")) {
            sum(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        } else if (parts[0].equals("div")) {
            div(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        } else if (parts[0].equals("end")){
            return;
        }
    }

    void init(int max){
        bateriamax = max;
        menu();
    }

    void show(){
        System.out.printf("display = %.2f, battery = %d\n", display, bateria);
        menu();
    }

    void charge(int valor){
        bateria += valor;
        if (bateria > bateriamax){
            bateria = bateriamax;
        }
        menu();
    }

    void usebateria(){
        bateria--;
    }

    void sum (float a, float b){
        if(bateria == 0){
            System.out.print("fail: bateria insuficiente");
            menu();
        }
        usebateria();
        display = a + b;
        menu();
    }

    void div(float a, float b){
        if (b == 0){
            System.out.println("fail: divisao por zero");
            menu();
        }
        if(bateria == 0){
            System.out.print("fail: bateria insuficiente");
            menu();
        }
        usebateria();
        display =  a / b;
        menu();
    }
}
