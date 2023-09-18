class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }
}

class Moto {
    private Pessoa pessoa; // Representa a pessoa na moto
    private int potencia;
    private int minutos;

    public Moto() {
        this.potencia = 1;
        this.minutos = 0;
        this.pessoa = null; // Inicia sem ninguém na moto
    }

    public int getPotencia() {
        return potencia;
    }

    public int getMinutos() {
        return minutos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    // Método para subir na moto
    public boolean subir(Pessoa pessoa) {
        if (this.pessoa == null) {
            this.pessoa = pessoa;
            return true; // Pessoa subiu com sucesso
        } else {
            return false; // Já há alguém na moto
        }
    }

    // Método para descer da moto
    public boolean descer() {
        if (this.pessoa != null) {
            this.pessoa = null; // Pessoa desceu
            return true;
        } else {
            return false; // Não há ninguém na moto para descer
        }
    }

    @Override
    public String toString() {
        String status = "Potência da Moto: " + potencia + ", Minutos na Moto: " + minutos;
        if (pessoa != null) {
            status += "\nPessoa na Moto: " + pessoa.toString();
        }
        return status;
    }
}

public class teste {
    public static void main(String[] args) {
        Moto moto = new Moto();

        // Testando a funcionalidade da moto
        System.out.println(moto);

        Pessoa pessoa1 = new Pessoa("João", 25);
        Pessoa pessoa2 = new Pessoa("Maria", 30);

        boolean subiuJoao = moto.subir(pessoa1);
        System.out.println("João subiu na moto: " + subiuJoao);

        boolean subiuMaria = moto.subir(pessoa2);
        System.out.println("Maria subiu na moto: " + subiuMaria);

        System.out.println(moto);

        boolean desceuAlguem = moto.descer();
        System.out.println("Alguém desceu da moto: " + desceuAlguem);

        boolean subiuNovamente = moto.subir(pessoa2);
        System.out.println("Maria subiu novamente na moto: " + subiuNovamente);

        System.out.println(moto);
    }
}
