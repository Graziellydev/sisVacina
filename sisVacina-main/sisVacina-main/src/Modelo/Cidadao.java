package Modelo;

public class Cidadao {
    private String cpf;
    private String nome;

    public Cidadao(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
}
