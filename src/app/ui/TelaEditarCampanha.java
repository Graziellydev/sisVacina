package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaEditarCampanha {

    public TelaEditarCampanha(CampanhaService campanhaService) {

        JFrame frame = new JFrame("Editar Campanha");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Editar Campanha"), BorderLayout.NORTH);

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        campanhaService.listarCampanhas().forEach(model::addElement);

        JList<Campanha> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        RoundedButton btnEditar = new RoundedButton("Editar Nome");

        btnEditar.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                String novoNome = JOptionPane.showInputDialog("Novo nome:");
                campanhaService.atualizarNome(c, novoNome);
                lista.repaint();
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
