package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaExcluirCampanha {

    public TelaExcluirCampanha(CampanhaService campanhaService) {

        JFrame frame = new JFrame("Excluir Campanha");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Excluir Campanha"), BorderLayout.NORTH);

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        campanhaService.listarCampanhas().forEach(model::addElement);

        JList<Campanha> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        RoundedButton btnExcluir = new RoundedButton("Excluir");

        btnExcluir.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                campanhaService.removerCampanha(c);
                model.removeElement(c);
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnExcluir);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
