package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;

public class TelaEditarCampanha {
    public TelaEditarCampanha(CampanhaService campanhaService) {
        JFrame frame = new JFrame("Editar Campanha");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Editar Campanhas"), BorderLayout.NORTH);

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        campanhaService.listarTodas().forEach(model::addElement);
        JList<Campanha> lista = new JList<>(model);

        RoundedButton btnEditar = new RoundedButton("Alterar Nome");
        btnEditar.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                String novoNome = JOptionPane.showInputDialog(frame, "Novo nome para: " + c.getNome());
                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    c.setNome(novoNome);
                    lista.repaint();
                    JOptionPane.showMessageDialog(frame, "Nome alterado localmente. (Recomendado: implementar atualizar no Service)");
                }
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);
        JPanel rodape = new JPanel();
        rodape.add(btnEditar);
        frame.add(rodape, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}