import java.util.Scanner;

public class Main {
    public static void main(String[] a) {
        Time time = new Time(0, 0, 0);

        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");


            if (args[0].equals("show"))  {
                write("" + time);
            }
            else if (args[0].equals("init")) {
                boolean valid = true;
                if (Integer.parseInt(args[1]) > 23) {
                    System.out.println("fail: hora invalida");
                    valid = false;
                } if (Integer.parseInt(args[2]) > 59) {
                    System.out.println("fail: minuto invalido");
                    valid = false;
                } if (Integer.parseInt(args[3]) > 59) {
                    System.out.println("fail: segundo invalido");
                    valid = false;
                } if (valid){
                    time = new Time((int) number(args[1]), (int) number(args[2]), (int) number(args[3]));
                }
            }

            else if (args[0].equals("set")) {
                time.setHour((int)number(args[1]));
                time.setMinute((int)number(args[2]));
                time.setSecond((int)number(args[3]));
            }
            else if (args[0].equals("end"))   {
                break;
            }
            else if (args[0].equals("next")) {
                time.next();
            }
            else {
                write("fail: comando invalido");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }

}

class Time{
    private int hour, minute, second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour) {
        if (hour > 24 || hour < 0){
            System.out.println("fail: hora invalida");
        }else {
            this.hour = hour;
        }
    }

    public void setMinute(int minute) {
        if (minute > 60 || minute < 0){
            System.out.println("fail: minuto invalido");
        }else {
            this.minute = minute;
        }
    }

    public void setSecond(int second) {
        if (second > 60 || second < 0) {
            System.out.println("fail: segundo invalido");
        } else {
            this.second = second;
        }
    }

    void next(){
        setSecond(getSecond() + 1);
        if (getSecond() == 60){
            setSecond(0);
            setMinute(getMinute() + 1);
            if (getMinute() == 60){
                setMinute(0);
                setHour(getHour() + 1);
                if (getHour() == 24){
                    setHour(0);
                }
            }
        }
    }


}