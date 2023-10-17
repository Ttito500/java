import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.input();
        System.out.printf("%.1f", aluno.media());
    }
}


class Aluno{
    String nome;
    float[] notas;
    Scanner in = new Scanner(System.in);
    void input(){
        notas = new float[3];
        for (int i = 0; i < 3; i++){
            notas[i] = Float.parseFloat(in.nextLine());
        }
    }

    float media(){
        float soma = this.notas[0] + this.notas[1] + this.notas[2];
        return soma / 3;
    }
}