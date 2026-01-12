package Servico;

import Modelo.Campanha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampanhaService {

    private List<Campanha> campanhas = new ArrayList<>();

    public CampanhaService() {
        // Campanhas de exemplo
        campanhas.add(new Campanha(
                "COVID-19",
                "Adultos",
                LocalDate.now().plusDays(30)
        ));
        campanhas.add(new Campanha(
                "Influenza",
                "Idosos",
                LocalDate.now().plusDays(15)
        ));
    }

    public void cadastrarCampanha(String nomeVacina, String publico, LocalDate dataFim) throws Exception {
        if (nomeVacina.isEmpty() || publico.isEmpty() || dataFim == null) {
            throw new Exception("Todos os campos devem ser preenchidos.");
        }

        campanhas.add(new Campanha(nomeVacina, publico, dataFim));
    }

    public List<Campanha> listarCampanhasAtivas() {
        LocalDate hoje = LocalDate.now();
        List<Campanha> ativas = new ArrayList<>();

        for (Campanha c : campanhas) {
            if (!c.getDataFim().isBefore(hoje)) {
                ativas.add(c);
            }
        }
        return ativas;
    }

    public void removerCampanha(Campanha campanha) {
        campanhas.remove(campanha);
    }
}
