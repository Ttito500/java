import java.util.*;

public class Main {
    public static void main(String[] args) {
        Motorcycle motoca = new Motorcycle(1);
        while(true) {
            String line = input();
            args = line.split(" ");
            write('$' + line);

            if      (args[0].equals("show"))     { System.out.println(motoca.toString());                         }
            else if (args[0].equals("init"))     { motoca = new Motorcycle(number(args[1]));           }
            else if (args[0].equals("enter"))    { motoca.enter(new Person(args[1], number(args[2]))); }
            else if (args[0].equals("end"))      { break;                                              }
            else if (args[0].equals("leave"))    {motoca.leave();                                      }
            else {
                System.out.println("fail: comando invalido");
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);
    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static int number(String str)   { return Integer.parseInt(str); }
}


class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
//Falta no rascunho buzinhar, comprarTempo e dirigir

class Motorcycle {
    private Person person = null; //agregacao
    private int power;
    private int time;

    //Inicia o atributo power, time com zero e person com null


    public Motorcycle(int power) {
        this.power = power;
    }

    public Person getPerson() {
        return person;
    }

    public int getPower() {
        return power;
    }

    public int getTime() {
        return time;
    }

    //Se estiver vazio, coloca a pessoa na moto e retorna true
    public void enter(Person entry) {
        if (person == null) {
            person = entry;
            System.out.println(entry.getName());
        } else {
            System.out.println("fail: busy motorcycle");
        }
        System.out.println(person.getName());
    }
    public void leave() {
        if (person == null){
            System.out.println("fail: empty motorcycle");
        } else {
            person = null;
        }
    }
    public String toString() {
        if (this.person == null){
            return "power:" + power + ", time:" + time + ", person:(empty)";
        } else {
            return "power:" + power + ", time:" + time + ", person:(" + this.person.getName() + ":" + this.person.getAge() + ")";
        }
    }
}