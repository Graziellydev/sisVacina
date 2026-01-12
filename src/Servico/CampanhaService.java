package Servico;

import Modelo.Campanha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampanhaService {

    private final List<Campanha> campanhas = new ArrayList<>();

    public CampanhaService() {
        // campanhas de exemplo
        campanhas.add(new Campanha("Gripe 2026", "Influenza", "Idosos", LocalDate.now().plusMonths(2)));
        campanhas.add(new Campanha("Covid-19", "Pfizer", "Adultos", LocalDate.now().plusMonths(1)));
    }

    public void adicionarCampanha(String nome, String vacina, String publico, LocalDate fim) {
        campanhas.add(new Campanha(nome, vacina, publico, fim));
    }

    public List<Campanha> listarCampanhas() {
        return campanhas;
    }

    public List<Campanha> listarCampanhasAtivas() {
        return campanhas.stream()
                .filter(c -> c.getDataFim().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public void atualizarNome(Campanha campanha, String novoNome) {
        campanha.setNome(novoNome);
    }

    public void removerCampanha(Campanha campanha) {
        campanhas.remove(campanha);
    }
}
