    import java.util.*;
    class MsgException extends RuntimeException {
        public MsgException(String message){
    
        };
    }
    abstract class Funcionario {
        protected String nome;
        protected int bonus;
        protected int diarias;
        protected int maxDiarias;
        public Funcionario(String nome){
            this.nome = nome;
        }
        public String getNome(){
            return this.nome;
        }
        public void setBonus(int bonus) {
            this.bonus = bonus;
        }
        public void addDiaria(){
            if (maxDiarias < diarias) {
                diarias++;
                //se não atingiu o máximo, adicione mais uma diária
                //se atingiu o máximo, lance uma MsgException
            } else {
                System.out.println("msgexpsetbonus");//placeholder
            }
        }
        //retorna bonus + diarias * 100
        public int getSalario(){
            return bonus + diarias * 100;
        };
    }
    class Professor extends Funcionario {
        protected String classe;
        //inicializa classe e muda maxDiarias para 2
        public Professor(String nome, String classe){
            super(nome);
            this.classe = classe;
            super.maxDiarias = 2;
        }
        public String getClasse(){
            return this.classe;
        }
        //lógica do salário do professor
        //usa o super.getSalario para pegar bonus e diarias
        public int getSalario(){
            if (classe.equals("A")){
                return 3000 + super.getSalario();
            } else if (classe.equals("B")) {
                return 5000 + super.getSalario();
            } else if (classe.equals("C")) {
                return 7000 + super.getSalario();
            } else if (classe.equals("D")) {
                return 9000 + super.getSalario();
            } else if (classe.equals("E")) {
                return 11000 + super.getSalario();
            } else {
                return -1; //placeholder
            }
        }
    
        @Override
        public String toString() {
            return "Professor{" +
                    "classe='" + classe + '\'' +
                    ", nome='" + nome + '\'' +
                    ", bonus=" + bonus +
                    ", diarias=" + diarias +
                    ", maxDiarias=" + maxDiarias +
                    '}';
        }
    }
    class STA extends Funcionario {
        protected int nivel;
        public STA(String nome, int nivel) {
            super(nome);
            this.nivel = nivel;
            super.maxDiarias = 1;
        }
        public int getNivel(){
            return this.nivel;
        }
        public int getSalario(){
            return 3000 + 300 * nivel;
        }
    
        @Override
        public String toString() { //placeholder
            return "STA{" +
                    "nivel=" + nivel +
                    ", nome='" + nome + '\'' +
                    ", bonus=" + bonus +
                    ", diarias=" + diarias +
                    ", maxDiarias=" + maxDiarias +
                    '}';
        }
    }
    class Tercerizado extends Funcionario {
        protected int horas;
        protected boolean isSalubre = false;
        public Tercerizado(String nome, int horas, String isSalubre){
            super(nome);
            this.horas = horas;
            if (isSalubre.equals("sim")){
                this.isSalubre = true;
            }
        }
        public int getHoras(){
            return horas;
        }
        public String getIsSalubre(){ // s / n
            if (isSalubre){
                return "sim";
            } else {
                return "não";
            }
        }
        public int getSalario(){
            int sal = 0;
            if (!isSalubre){
                sal += 500;
            }
            return sal + 4 * horas;
        }
        public void addDiaria(){
            System.out.println("npode"); //placeholder
        }
    
        @Override
        public String toString() {
            return "Tercerizado{" +
                    "horas=" + horas +
                    ", isSalubre=" + isSalubre +
                    ", nome='" + nome + '\'' +
                    ", bonus=" + bonus +
                    ", diarias=" + diarias +
                    ", maxDiarias=" + maxDiarias +
                    '}';
        }
    }
    class UFC {
        private Map<String, Funcionario> funcionarios = new TreeMap<>();
    
        @Override
        public String toString() {
            return "UFC{" +
                    "funcionarios=" + funcionarios +
                    '}';
        }
    
        public Funcionario getFuncionario(String nome){
            return funcionarios.get(nome);
        }
        public void addFuncionario(Funcionario funcionario){
            funcionarios.put(funcionario.nome, funcionario);
        }
        public void rmFuncionario(String nome){
            funcionarios.remove(nome);
        }
        //reparte o bonus para todos os funcionarios
        public void setBonus(int bonus){
            for (Map.Entry<String, Funcionario> entry : funcionarios.entrySet()){
                getFuncionario(entry.getKey()).setBonus(bonus);
            }
        };
    }
    class Solver {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            UFC ufc = new UFC();
            while (true) {
                try {
                    String line = scanner.nextLine();
                    System.out.println("$" + line);
                    String ui[] = line.split(" ");
                    if (ui[0].equals("end")) {
                        break;
                    } else if (ui[0].equals("addProf")) {
                        ufc.addFuncionario(new Professor(ui[1], ui[2]));
                    } else if (ui[0].equals("addSta")) {
                        ufc.addFuncionario(new STA(ui[1], Integer.parseInt(ui[2])));
                    } else if (ui[0].equals("addTer")) {
                        ufc.addFuncionario(new Tercerizado(ui[1], Integer.parseInt(ui[2]), ui[3]));
                    } else if (ui[0].equals("rm")) {
                        ufc.rmFuncionario(ui[1]);
                    } else if (ui[0].equals("showAll")) {
                        System.out.println(ufc);
                    } else if (ui[0].equals("show")) {
                        System.out.println(ufc.getFuncionario(ui[1]));
                    } else if (ui[0].equals("addDiaria")) {
                        ufc.getFuncionario(ui[1]).addDiaria();
                    } else if (ui[0].equals("setBonus")) {
                        ufc.setBonus(Integer.parseInt(ui[1]));
                    } else {
                        System.out.print("fail: comando invalido");
                    }
                } catch (MsgException me) {
                    System.out.println(me.getMessage());
                }
            }
        }
    }