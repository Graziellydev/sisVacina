package app.ui;

import Servico.AgendamentoService;

import javax.swing.*;

public class TelaMinhasVacinas {

    public TelaMinhasVacinas(String cpf, AgendamentoService service) {

        JFrame frame = new JFrame("Vacinas Agendadas");
        frame.setSize(400, 300);

        DefaultListModel<String> model = new DefaultListModel<>();
        service.listarTodosDoCidadao(cpf).forEach(a ->
                model.addElement(a.toString())
        );

        JList<String> lista = new JList<>(model);
        frame.add(new JScrollPane(lista));

        frame.setVisible(true);
    }
}
