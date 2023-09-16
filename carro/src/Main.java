import java.util.*;

    public class Main {
        public static void main(String[] a) {
            Carro car = new Carro();

            while (true) {
                var line = input();
                write("$" + line);
                var args = line.split(" ");

                if      (args[0].equals("end"))   { break;                                }
                else if (args[0].equals("show"))  { car.show();                             }
                else if (args[0].equals("enter")) { car.enter();                          }
                else if (args[0].equals("leave")) { car.leave();                          }
                else if (args[0].equals("drive")) { car.drive((int) number(args[1]));     }
                else if (args[0].equals("fuel"))  { car.fuel((int) number(args[1]));      }
                else                              { write("fail: comando invalido");}
            }
        }

        private static Scanner scanner = new Scanner(System.in);
        private static String  input()              { return scanner.nextLine(); }
        private static double  number(String value) { return Double.parseDouble(value); }
        private static void    write(String value)  { System.out.println(value); }
    }

    class Carro{
        int pass;
        int passMax;
        int gas; // 1km/L
        int gasMax;
        int km;

        public Carro() {
            this.pass = 0;
            this.passMax = 2;
            this.gas = 0;
            this.gasMax = 100;
            this.km = 0;
        }

        public String toString() {
            return "carro{" +
                    "pass=" + pass +
                    ", passMax=" + passMax +
                    ", gas=" + gas +
                    ", gasMax=" + gasMax +
                    ", km=" + km +
                    '}';
        }

        void show(){
            System.out.printf("pass: %d, gas: %d, km: %d", pass, gas, km);
        }

        void enter(){
            if(pass < 2){
                this.pass++;
            }
            else {
                System.out.println("fail: limite de pessoas atingido");
            }
        }

        void leave(){
            if (pass != 0) {
                this.pass--;
            }
            else {
                System.out.println("fail: nao ha ninguem no carro");
            }
        }

        void drive(int value){
            if (pass == 0){
                System.out.println("fail: nao ha ninguem no carro");
            } else if (gas == 0){
                System.out.println("fail: tanque vazio");
            } else if (gas < value){
                System.out.printf("fail: tanque vazio apos andar %d km\n", gas);
                gas = 0;
            } else if (gas >= value) {
                gas -= value;
            }
        }

        void fuel(int value){
            this.gas += value;
            if(gas > gasMax){
                gas = gasMax;
            }
        }
    }