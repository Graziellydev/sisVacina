package Modelo;

import java.time.LocalDate;

public class Campanha {

    private String nomeVacina;
    private String publico;
    private LocalDate dataFim;

    public Campanha(String nomeVacina, String publico, LocalDate dataFim) {
        this.nomeVacina = nomeVacina;
        this.publico = publico;
        this.dataFim = dataFim;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getPublico() {
        return publico;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    
    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }


    @Override
    public String toString() {
        return nomeVacina + " | Público: " + publico + " | Até: " + dataFim;
    }
}
