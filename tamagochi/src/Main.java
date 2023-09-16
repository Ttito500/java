import java.util.Scanner;

public class Main {
    public static void main(String[] a) {
        Pet pet = new Pet(0, 0, 0);

        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if      (args[0].equals("end"))   { break;                                                                           }
            else if (args[0].equals("show"))  { pet.show();                                                           }
            else if (args[0].equals("init"))  { pet = new Pet((int)number(args[3]), (int)number(args[1]), (int)number(args[2])); }
            else if (args[0].equals("play"))  { pet.play();                                                                      }
            else if (args[0].equals("eat"))   { pet.eat();                                                                       }
            else if (args[0].equals("sleep")) { pet.sleep();                                                                     }
            else if (args[0].equals("shower")){ pet.shower();                                                                    }
            else                              { write("fail: comando invalido");                                                 }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}

class Pet {
    private boolean alive;
    private int clean;
    private int cleanMax;
    private int energy;
    private int energyMax;
    private int hungry;
    private int hungryMax;
    private int age;
    private int diamonds;

    private boolean testAlive(){
        if (clean == 0){
            alive = false;
            return false;
        } else if (energy == 0) {
            alive = false;
            return false;
        } else if (hungry == 0) {
            alive = false;
            return false;
        } else {
            return true;
        }
    }

    public Pet(int cleanMax, int energyMax, int hungryMax) {
        this.cleanMax = cleanMax;
        this.energyMax = energyMax;
        this.hungryMax = hungryMax;
        this.energy = energyMax;
        this.hungry = hungryMax;
        this.clean = cleanMax;
        this.diamonds = 0;
        this.age = 0;
        this.alive = true;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "alive=" + alive +
                ", clean=" + clean +
                ", cleanMax=" + cleanMax +
                ", energy=" + energy +
                ", energyMax=" + energyMax +
                ", hungry=" + hungry +
                ", hungryMax=" + hungryMax +
                ", age=" + age +
                ", diamonds=" + diamonds +
                '}';
    }

    public int getClean() {
        return clean;
    }

    public int getCleanMax() {
        return cleanMax;
    }

    public int getEnergy() {
        return energy;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public int getHungry() {
        return hungry;
    }

    public int getHungryMax() {
        return hungryMax;
    }

    public void setClean(int clean) {
        this.clean += clean;
    }

    public void setEnergy(int energy) {
        this.energy += energy;
    }

    public void setHungry(int hungry) {
        this.hungry += hungry;
    }

    public void setAge(int age) {
        this.age += age;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds += diamonds;
    }

    void show(){
        System.out.printf("E:%d/%d, S:%d/%d, L:%d/%d, D:%d, I:%d\n", energy, energyMax, hungry, hungryMax, clean, cleanMax,diamonds, age);
    }

    void eat(){
        if (testAlive()){
            setAge(1);
            setEnergy(-1);
            setHungry(4);
            setClean(-2);
            if (hungry > hungryMax){
                hungry = hungryMax;
            }
        } else {
            System.out.println("bixo morto");
        }
    }

    void play(){
        if(testAlive()){
            setEnergy(-2);
            setHungry(-1);
            setClean(-3);
            setAge(1);
            setDiamonds(1);
        } else {
            System.out.println("bixo morto");
        }

    }

    void shower(){
        if (testAlive()){
            setEnergy(-3);
            setHungry(-1);
            setClean(cleanMax);
            setAge(2);
            if (clean > cleanMax){
                clean = cleanMax;
            }
        }
    }

    void sleep(){
        if (testAlive()) {
            if (energy > 16) {
                System.out.println("fail: nao esta com sono");
            } else {
                energy = energyMax;
                setHungry(-1);
                setAge(5);
            }
        } else {
            System.out.println("bixo morto");
        }
    }
}