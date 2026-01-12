package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;

import javax.swing.*;

public class TelaCampanha {

    public TelaCampanha(CampanhaService service) {

        JFrame frame = new JFrame("Campanhas Ativas");
        frame.setSize(420, 300);

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        service.listarCampanhasAtivas()
                .forEach(model::addElement);

        JList<Campanha> lista = new JList<>(model);
        frame.add(new JScrollPane(lista));

        frame.setVisible(true);
    }
}
