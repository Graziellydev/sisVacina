package app.ui;

import Modelo.Parente;
import Servico.AgendamentoService;
import Servico.ParenteService;

import javax.swing.*;

public class TelaVacinasAgendadas {

    public TelaVacinasAgendadas(String cpfCidadao,
                                AgendamentoService agendamentoService,
                                ParenteService parenteService) {

        JFrame frame = new JFrame("Minhas Vacinas");
        frame.setSize(460, 340);

        DefaultListModel<String> model = new DefaultListModel<>();

        // Vacinas do próprio cidadão
        agendamentoService.listarPorCpf(cpfCidadao)
                .forEach(a -> model.addElement(a.toString()));

        // Vacinas dos parentes filiados
        for (Parente p : parenteService.listarPorResponsavel(cpfCidadao)) {
            agendamentoService.listarPorCpf(p.getCpfParente())
                    .forEach(a -> model.addElement(a.toString()));
        }

        if (model.isEmpty()) {
            model.addElement("Nenhuma vacina agendada.");
        }

        JList<String> lista = new JList<>(model);
        frame.add(new JScrollPane(lista));

        frame.setVisible(true);
    }
}
