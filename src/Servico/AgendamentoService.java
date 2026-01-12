package Servico;

import Modelo.Agendamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoService {

    private final List<Agendamento> agendamentos = new ArrayList<>();

    public void agendar(String cpf, String nome, String campanha, LocalDate data) {
        agendamentos.add(new Agendamento(cpf, nome, campanha, data));
    }

    public List<Agendamento> listarTodosDoCidadao(String cpfResponsavel) {
        return agendamentos.stream()
                .filter(a -> a.getCpf().equals(cpfResponsavel)
                        || a.getCpfResponsavel().equals(cpfResponsavel))
                .collect(Collectors.toList());
    }
    
    public List<Agendamento> listarPorCpf(String cpf) {
        List<Agendamento> resultado = new ArrayList<>();

        for (Agendamento a : agendamentos) {
            if (a.getCpf().equals(cpf) || a.getCpfResponsavel().equals(cpf)) {
                resultado.add(a);
            }
        }

        return resultado;
    }

}
