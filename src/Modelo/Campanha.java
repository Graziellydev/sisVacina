package Modelo;

import java.time.LocalDate;

public class Campanha {
    private String nome;
    private String vacina;
    private String publicoAlvo;
    private LocalDate dataFim;
    private int quantidadeDoses;

    public Campanha(String nome, String vacina, String publicoAlvo, LocalDate dataFim, int quantidadeDoses) {
        this.nome = nome;
        this.vacina = vacina;
        this.publicoAlvo = publicoAlvo;
        this.dataFim = dataFim;
        this.quantidadeDoses = quantidadeDoses;
    }

    public String getNome() { return nome; }
    public String getVacina() { return vacina; }
    public int getQuantidadeDoses() { return quantidadeDoses; }
    public LocalDate getDataFim() { return dataFim; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setVacina(String vacina) { this.vacina = vacina; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public void setQuantidadeDoses(int doses) { this.quantidadeDoses = doses; }

    @Override
    public String toString() {
        return nome + " (" + quantidadeDoses + " doses)";
    }

    public String paraTexto() {
        return nome + ";" + vacina + ";" + publicoAlvo + ";" + dataFim + ";" + quantidadeDoses;
    }
}