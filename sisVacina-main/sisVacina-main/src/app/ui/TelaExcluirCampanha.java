package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;

public class TelaExcluirCampanha {
    public TelaExcluirCampanha(CampanhaService campanhaService) {
        JFrame frame = new JFrame("Excluir Campanha");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Excluir Campanha"), BorderLayout.NORTH);

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        campanhaService.listarTodas().forEach(model::addElement);
        JList<Campanha> lista = new JList<>(model);

        RoundedButton btnExcluir = new RoundedButton("Remover Definitivamente");
        btnExcluir.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja excluir: " + c.getNome() + "?");
                if (confirm == JOptionPane.YES_OPTION) {
                    campanhaService.listarTodas().remove(c); 
                    model.removeElement(c);
                }
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);
        JPanel rodape = new JPanel();
        rodape.add(btnExcluir);
        frame.add(rodape, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}