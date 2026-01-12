package app.ui;

import Modelo.Parente;
import Servico.ParenteService;

import javax.swing.*;

public class TelaParente {

    public TelaParente(String cpfResponsavel, ParenteService service) {

        JFrame frame = new JFrame("Parentes Filiados");
        frame.setSize(420, 300);

        DefaultListModel<Parente> model = new DefaultListModel<>();

        service.listarPorResponsavel(cpfResponsavel)
                .forEach(model::addElement);

        JList<Parente> lista = new JList<>(model);

        frame.add(new JScrollPane(lista));
        frame.setVisible(true);
    }
}
