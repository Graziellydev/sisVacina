package Modelo;

public class Parente {

    private String cpfResponsavel;
    private String cpf;
    private String nome;

    public Parente(String cpfResponsavel, String cpf, String nome) {
        this.cpfResponsavel = cpfResponsavel;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpfResponsavel() { return cpfResponsavel; }
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
