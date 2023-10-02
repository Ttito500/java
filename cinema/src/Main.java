import java.util.*;

class Client {
    private String id;
    private String fone;
    public Client(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

    @Override
    public String toString() {
        return id + ":" + fone;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {

    }
}


class Sala{
    private List<Client> cadeiras;


    public Sala(int capacidade) {
        cadeiras = new ArrayList<>(); // Initialize the cadeiras list
        for (int i = 0; i < capacidade; i++) {
            cadeiras.add(null);
        }
    }


    public List<Client> getCadeiras() {
        return null;
    }

    Boolean search(Client esse){
        for (int i = 0; i < cadeiras.size(); i++){
            if (cadeiras.get(i).getId().equals(esse.getId())){
                return true;
            }
        }
        return false;
    }

    public boolean reservar(String id, String fone, int ind) {
        if (cadeiras.get(ind) == null){
            Client esse = new Client(id, fone);
            if (search(esse)){
                System.out.println("fail: cliente ja esta no cinema");
                return false;
            } else {
                Client set = new Client(id, fone);
                cadeiras.set(ind, set);
                return true;
            }
        }else {
            System.out.println("fail: cadeira ja esta ocupada");
            return false;
        }
    }

    public void cancelar(String id) {
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder("[");
        for (int i = 0; i < cadeiras.size(); i++) {
            if (i > 0) {
                saida.append(" ");
            }
            if (cadeiras.get(i) == null) {
                saida.append("-");
            } else {
                saida.append(cadeiras.get(i));
            }
        }
        saida.append("]");
        return saida.toString();
    }

}

class Main {
    static Shell sh = new Shell();
    static Sala sala = new Sala(0);

    public static void main(String args[]) {
        sh.chain.put("init",     () -> { sala = new Sala(getInt(1));});
        sh.chain.put("show",     () -> { System.out.println(sala);});
        sh.chain.put("reservar", () -> { sala.reservar(getStr(1), getStr(2), getInt(3));});
        sh.chain.put("cancelar", () -> { sala.cancelar(getStr(1));});

        sh.execute();
    }

    static int getInt(int pos) {
        return Integer.parseInt(sh.param.get(pos));
    }
    static String getStr(int pos) {
        return sh.param.get(pos);
    }
}

class Shell {
    public Scanner scanner = new Scanner(System.in);
    public HashMap<String, Runnable> chain = new HashMap<>();
    public ArrayList<String> param = new ArrayList<>();
    public Shell() {
        Locale.setDefault(new Locale("en", "US"));
    }
    public void execute() {
        while(true) {
            param.clear();
            String line = scanner.nextLine();
            Collections.addAll(param, line.split(" "));
            System.out.println("$" + line);
            if(param.get(0).equals("end")) {
                break;
            } else if (chain.containsKey(param.get(0))) {
                chain.get(param.get(0)).run();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
    }
}