import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int qtdponto = Integer.parseInt(in.nextLine());
        ponto[] coord = new ponto[qtdponto];

        for (int i = 0; i < qtdponto; i++) {
            coord[i] = new ponto();
            coord[i].input(in);
        }
        //System.out.printf("%f", coord[1].x);

        circulo circulo = new circulo();
        circulo.centro = new ponto();
        circulo.input(in);

        int count = 0;
        for (int i = 0; i < qtdponto; i++){
            if(circulo.dentro(coord[i])){
                count++;
            }
        }
        System.out.println(count);
    }

    static class ponto {
        float x;
        float y;

        void input(Scanner in) {
            var line = in.nextLine();
            var split = line.split(" ");
            this.x = Float.parseFloat(split[0]);
            this.y = Float.parseFloat(split[1]);
        }

    }

    static class circulo {
        ponto centro;
        float raio;

        void input(Scanner in) {
            var line = in.nextLine();
            var split = line.split(" ");
            this.centro.x = Float.parseFloat(split[0]);
            this.centro.y = Float.parseFloat(split[1]);
            this.raio = Float.parseFloat(split[2]);
        }

        boolean dentro(ponto a){
            double dis = pow(this.centro.x - a.x, 2) + pow(this.centro.y - a.y, 2);
            if (dis > this.raio){
                return false;
            } else {
                return true;
            }
        }
    }
}
