package Modelo;

import java.time.LocalDate;

public class Campanha {

    private String nome;
    private String vacina;
    private String publico;
    private LocalDate dataFim;

    public Campanha(String nome, String vacina, String publico, LocalDate dataFim) {
        this.nome = nome;
        this.vacina = vacina;
        this.publico = publico;
        this.dataFim = dataFim;
    }

    public String getNome() { return nome; }
    public String getVacina() { return vacina; }
    public String getPublico() { return publico; }
    public LocalDate getDataFim() { return dataFim; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return nome + " - " + vacina + " (" + publico + ")";
    }
}
