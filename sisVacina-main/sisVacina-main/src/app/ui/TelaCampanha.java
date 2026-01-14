package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaCampanha {

    public TelaCampanha(CampanhaService campanhaService) {

        JFrame frame = new JFrame("Campanhas");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Campanhas de Vacinação"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        DefaultListModel<Campanha> model = new DefaultListModel<>();
        JList<Campanha> lista = new JList<>(model);
        lista.setCellRenderer(new ListRenderer());

        campanhaService.listarTodas().forEach(model::addElement);

        centro.setLayout(new BorderLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
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
