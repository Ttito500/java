import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;


enum Direcao {
    IN("in"),
    OUT("out");

    private String label;

    Direcao(String label) {
        this.label = label;
    }

    public String toString() {
        return switch (this.name()) {
            case "IN" -> "in";
            case "OUT" -> "out";
            default -> "fail";
        };
    }

    public String getLabel() {
        return this.label;
    }
}

class Movimento{
    private Passageiro idPass;
    private Direcao direcao;

    Movimento(Passageiro idPass, Direcao dir){
        this.idPass = idPass;
        this.direcao = dir;
    }

    public String toString(){
        return this.idPass + " " + this.direcao;
    }

    public String getNome(){
        return this.idPass.getId();
    }

}

class Registro{

    private static ArrayList<Passageiro> cadastro;
    private static ArrayList<Movimento> movimentacao;

    Registro(){

        cadastro = new ArrayList<Passageiro>();
        movimentacao = new ArrayList<Movimento>();

    }

    public String outMov(){////////////////////////////////////////
        StringBuilder string = new StringBuilder();

        for(Movimento mov : movimentacao){
            string.append(mov).append("\n");
        }

        return string.toString();
    }

    public String outCad(){///////////////////////////////////////////////////
        Collections.sort(cadastro, Comparator.comparing(Passageiro::getId));

        StringBuilder string = new StringBuilder();

        for(Passageiro pass : cadastro){
            string = string.append(pass).append("\n");
        }

        return string.toString();
    }

    public void cadastrar(Passageiro pass){
        for(Passageiro p : cadastro){
            if(p.getId().equals(pass.getId())){ return; }
        }

        cadastro.add(pass);
    }

    public void addMov(Movimento mov){
        movimentacao.add(mov);
    }
}

class Passageiro{
    private String id;

    public Passageiro(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return this.id;
    }
}


class Solver {

    public static void main(String[] arg) {

        Trem trem = new Trem(1);

        Registro registro = new Registro();

        while (true) {

            var line = input();
            output("$" + line);
            var args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("init")) {
                trem = new Trem((int) number(args[1]));
            } else if (args[0].equals("entrar")) {
                trem.embarcar(new Passageiro(args[1]));
            } else if (args[0].equals("sair")) {
                trem.desembarcar(args[1]);
            } else if (args[0].equals("nwvag")) {
                trem.addVagao(new Vagao((int) number(args[1])));
            } else if (args[0].equals("show")) {
                output(trem);
            } else if (args[0].equals("la")) {
                output(trem);
            } else if (args[0].equals("movimentacao")) {
                print(registro.outMov());
            } else if (args[0].equals("cadastro")) {
                print(registro.outCad());
            }

        }

    }

    public static Scanner in = new Scanner(System.in);

    public static String input() {
        return in.nextLine();
    }

    public static void print(Object value) {
        System.out.print(value);
    }

    public static void output(Object value) {
        System.out.println(value);
    }

    public static float number(String value) {
        return Float.parseFloat(value);
    }

}
