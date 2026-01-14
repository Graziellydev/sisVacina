package Servico;

import Modelo.Campanha;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampanhaService {
    private final List<Campanha> campanhas = new ArrayList<>();
    private final String ARQUIVO_DB = "banco_campanhas.txt";

    public CampanhaService() {
        carregarDados();
        if (campanhas.isEmpty()) {
            criarCampanhasBase();
        }
    }

    private void criarCampanhasBase() {
        adicionarCampanha("Gripe H1N1", "Influenza", "Geral", LocalDate.now().plusMonths(6), 1);
        adicionarCampanha("Covid-19", "Pfizer", "Geral", LocalDate.now().plusMonths(4), 2);
    }

    public void adicionarCampanha(String nome, String vacina, String publico, LocalDate dataFim, int doses) {
        Campanha nova = new Campanha(nome, vacina, publico, dataFim, doses);
        campanhas.add(nova);
        salvarDados();
    }

    public void atualizarCampanha(Campanha original, String novoNome, String novaVacina, LocalDate novaData, int novasDoses) {
        original.setNome(novoNome);
        original.setVacina(novaVacina);
        original.setDataFim(novaData);
        original.setQuantidadeDoses(novasDoses);
        salvarDados();
    }

    public void excluirCampanha(Campanha c) {
        campanhas.remove(c);
        salvarDados();
    }

    public List<Campanha> listarTodas() {
        return new ArrayList<>(campanhas);
    }

    public List<Campanha> listarCampanhasAtivas() {
        return campanhas.stream()
                .filter(c -> c.getDataFim().isAfter(LocalDate.now()) || c.getDataFim().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public Campanha buscarPorNome(String nome) {
        return campanhas.stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DB, false))) {
            for (Campanha c : campanhas) {
                writer.write(c.paraTexto());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO_DB);
        if (!arquivo.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DB))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    campanhas.add(new Campanha(dados[0], dados[1], dados[2], LocalDate.parse(dados[3]), Integer.parseInt(dados[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}