package Modelo;

import java.time.LocalDate;

public class Agendamento {

    private String cpf;
    private String nomePessoa;
    private String campanha;
    private LocalDate data;
    private String cpfResponsavel;

    public Agendamento(String cpf, String nomePessoa, String campanha, LocalDate data) {
        this.cpf = cpf;
        this.nomePessoa = nomePessoa;
        this.campanha = campanha;
        this.data = data;
        this.cpfResponsavel = cpf;
    }

    public String getCpf() { return cpf; }
    public String getNomePessoa() { return nomePessoa; }
    public String getCampanha() { return campanha; }
    public LocalDate getData() { return data; }
    public String getCpfResponsavel() { return cpfResponsavel; }
}
