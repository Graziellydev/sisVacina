package Servico;

import Modelo.Agendamento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {

    private List<Agendamento> agendamentos = new ArrayList<>();

    public void agendar(String cpfPessoa,
                        String nomePessoa,
                        String vacina,
                        LocalDate data) {

        agendamentos.add(
                new Agendamento(cpfPessoa, nomePessoa, vacina, data)
        );
    }

    public List<Agendamento> listarPorCpf(String cpf) {
        List<Agendamento> lista = new ArrayList<>();

        for (Agendamento a : agendamentos) {
            if (a.getCpfPessoa().equals(cpf)) {
                lista.add(a);
            }
        }
        return lista;
    }
}
