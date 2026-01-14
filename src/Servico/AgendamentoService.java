package Servico;

import Modelo.Agendamento;
import Modelo.Campanha;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoService {
    private final List<Agendamento> agendamentos = new ArrayList<>();
    private final String NOME_ARQUIVO = "banco_agendamentos.txt";

    public AgendamentoService() {
        carregarDados();
    }

    public String agendar(String cpf, String nome, String nomeCampanha, LocalDate data) {
        boolean jaAtivo = agendamentos.stream()
                .anyMatch(a -> a.getCpf().equals(cpf) && 
                               a.getCampanha().equals(nomeCampanha) && 
                               !a.getStatus().equals("Concluído"));
        
        if (jaAtivo) return "Agendamento já existe para esta vacina.";

        Agendamento novo = new Agendamento(cpf, nome, nomeCampanha, data);
        agendamentos.add(novo);
        salvarDados();
        return "SUCESSO";
    }

    public boolean aplicarDose(String cpf, String nomeCampanha, CampanhaService campanhaService) {
        Agendamento agendamento = agendamentos.stream()
                .filter(a -> a.getCpf().equals(cpf) && 
                             a.getCampanha().equalsIgnoreCase(nomeCampanha) && 
                             !a.getStatus().equalsIgnoreCase("Concluído"))
                .findFirst().orElse(null);

        if (agendamento == null) return false;

        Campanha campanhaObj = campanhaService.buscarPorNome(nomeCampanha);
        int totalDoses = (campanhaObj != null) ? campanhaObj.getQuantidadeDoses() : 1;

        if (totalDoses == 1) {
            agendamento.setStatus("Concluído");
        } else {
            if (agendamento.getStatus().equalsIgnoreCase("Agendado")) {
                agendamento.setStatus("1ª Dose Aplicada");
            } else {
                agendamento.setStatus("Concluído");
            }
        }
        salvarDados();
        return true;
    }

    public List<Agendamento> listarTodosDoCidadao(String cpf) {
        return agendamentos.stream()
                .filter(a -> a.getCpf().equals(cpf))
                .collect(Collectors.toList());
    }

    private void salvarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(arquivo, false), StandardCharsets.UTF_8))) {
            
            for (Agendamento a : agendamentos) {
                writer.println(a.getCpf() + ";" + 
                               a.getNome() + ";" + 
                               a.getCampanha() + ";" + 
                               a.getData() + ";" + 
                               a.getStatus());
            }
            
            writer.flush(); 
            System.out.println("=== GRAVAÇÃO REALIZADA ===");
            System.out.println("Local: " + arquivo.getAbsolutePath());
            System.out.println("Registros salvos: " + agendamentos.size());
            
        } catch (IOException e) {
            System.err.println("ERRO CRÍTICO AO SALVAR: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) return;

        agendamentos.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] d = linha.split(";");
                if (d.length >= 5) {
                    Agendamento a = new Agendamento(d[0], d[1], d[2], LocalDate.parse(d[3]));
                    a.setStatus(d[4]);
                    agendamentos.add(a);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
    }
}