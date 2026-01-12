package Modelo;

public class Parente {

    private String cpfResponsavel;
    private String cpfParente;
    private String nome;

    public Parente(String cpfResponsavel, String cpfParente, String nome) {
        this.cpfResponsavel = cpfResponsavel;
        this.cpfParente = cpfParente;
        this.nome = nome;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public String getCpfParente() {
        return cpfParente;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - " + cpfParente;
    }
}
