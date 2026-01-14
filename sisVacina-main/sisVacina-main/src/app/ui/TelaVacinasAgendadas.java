package app.ui;

import Modelo.Agendamento;
import Servico.AgendamentoService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaVacinasAgendadas {
    public TelaVacinasAgendadas(String cpfCidadao, AgendamentoService agendamentoService) {
        JFrame frame = new JFrame("Meu Histórico");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Minhas Vacinas"), BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        List<Agendamento> agendamentos = agendamentoService.listarTodosDoCidadao(cpfCidadao);
        for (Agendamento a : agendamentos) {
            String prefixo = a.getStatus().equalsIgnoreCase("Aplicado") ? "Aplicada em: " : "Agendada para: ";
            model.addElement(a.getCampanha() + " | " + prefixo + a.getData() + " [" + a.getStatus().toUpperCase() + "]");
        }

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);
        
        RoundedButton btnFechar = new RoundedButton("Fechar");
        btnFechar.addActionListener(e -> frame.dispose());
        JPanel rodape = new JPanel();
        rodape.add(btnFechar);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}