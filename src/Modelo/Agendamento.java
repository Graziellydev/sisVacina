package Modelo;

import java.time.LocalDate;

public class Agendamento {

    private String cpfPessoa;
    private String nomePessoa;
    private String vacina;
    private LocalDate data;

    public Agendamento(String cpfPessoa, String nomePessoa, String vacina, LocalDate data) {
        this.cpfPessoa = cpfPessoa;
        this.nomePessoa = nomePessoa;
        this.vacina = vacina;
        this.data = data;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    @Override
    public String toString() {
        return nomePessoa + " - " + vacina + " - " + data;
    }
}
