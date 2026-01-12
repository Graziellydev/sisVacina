package app.ui;

import Modelo.Parente;
import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaParente {

    public TelaParente(String cpf, ParenteService parenteService) {

        JFrame frame = new JFrame("Parentes Filiados");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Parentes Filiados"), BorderLayout.NORTH);

        DefaultListModel<Parente> model = new DefaultListModel<>();
        parenteService.listarParentes(cpf).forEach(model::addElement);

        JList<Parente> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        RoundedButton btnFechar = new RoundedButton("Fechar");
        btnFechar.addActionListener(e -> frame.dispose());
        rodape.add(btnFechar);

        frame.add(rodape, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
