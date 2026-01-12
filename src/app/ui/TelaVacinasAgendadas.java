package app.ui;

import Modelo.Agendamento;
import Servico.AgendamentoService;
import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaVacinasAgendadas {

    public TelaVacinasAgendadas(String cpfCidadao,
                                AgendamentoService agendamentoService,
                                ParenteService parenteService) {

        JFrame frame = new JFrame("Minhas Vacinas");
        frame.setSize(650, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Vacinas Agendadas"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new BorderLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        List<Agendamento> agendamentos =
                agendamentoService.listarTodosDoCidadao(cpfCidadao);

        for (Agendamento a : agendamentos) {
            String texto = a.getNomePessoa()
                    + " → " + a.getCampanha()
                    + " | " + a.getData();
            model.addElement(texto);
        }

        centro.add(new JScrollPane(lista), BorderLayout.CENTER);
        frame.add(centro, BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        RoundedButton btnFechar = new RoundedButton("Fechar");
        btnFechar.addActionListener(e -> frame.dispose());
        rodape.add(btnFechar);

        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
