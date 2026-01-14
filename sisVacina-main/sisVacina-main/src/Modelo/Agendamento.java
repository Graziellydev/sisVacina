package Modelo;

import java.time.LocalDate;

public class Agendamento {
    private String cpf;
    private String nome;
    private String campanha;
    private LocalDate data;
    private String status;

    public Agendamento(String cpf, String nome, String campanha, LocalDate data) {
        this.cpf = cpf;
        this.nome = nome;
        this.campanha = campanha;
        this.data = data;
        this.status = "Agendado";
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getCampanha() { return campanha; }
    public LocalDate getData() { return data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}