package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.Date;

public class TelaGerenciarCampanhas {
    public TelaGerenciarCampanhas(CampanhaService service, TelaFuncionario telaPrincipal) {
        JFrame frame = new JFrame("Gerenciar Campanhas");
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        DefaultListModel<Campanha> model = new DefaultListModel<>();
        service.listarTodas().forEach(model::addElement);
        JList<Campanha> lista = new JList<>(model);
        frame.add(new JScrollPane(lista), BorderLayout.NORTH);

        JPanel painelEdicao = new JPanel(new GridLayout(5, 2, 10, 10));
        painelEdicao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField txtNome = new JTextField();
        JTextField txtVacina = new JTextField();
        JComboBox<Integer> cbDoses = new JComboBox<>(new Integer[]{1, 2});
        JSpinner spinnerData = new JSpinner(new SpinnerDateModel());
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));

        painelEdicao.add(new JLabel("Nome:")); painelEdicao.add(txtNome);
        painelEdicao.add(new JLabel("Vacina:")); painelEdicao.add(txtVacina);
        painelEdicao.add(new JLabel("Doses Necessárias:")); painelEdicao.add(cbDoses);
        painelEdicao.add(new JLabel("Data Fim:")); painelEdicao.add(spinnerData);

        lista.addListSelectionListener(e -> {
            Campanha sel = lista.getSelectedValue();
            if (sel != null) {
                txtNome.setText(sel.getNome());
                txtVacina.setText(sel.getVacina());
                cbDoses.setSelectedItem(sel.getQuantidadeDoses());
                Date data = Date.from(sel.getDataFim().atStartOfDay(ZoneId.systemDefault()).toInstant());
                spinnerData.setValue(data);
            }
        });

        frame.add(painelEdicao, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        RoundedButton btnSalvar = new RoundedButton("Salvar Alterações");
        RoundedButton btnExcluir = new RoundedButton("Excluir Campanha");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        frame.add(painelBotoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> {
            Campanha sel = lista.getSelectedValue();
            if (sel != null) {
                Date d = (Date) spinnerData.getValue();
                service.atualizarCampanha(sel, txtNome.getText(), txtVacina.getText(), d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (int)cbDoses.getSelectedItem());
                telaPrincipal.atualizarCampanhas();
                frame.dispose();
            }
        });

        btnExcluir.addActionListener(e -> {
            Campanha sel = lista.getSelectedValue();
            if (sel != null) {
                service.excluirCampanha(sel);
                telaPrincipal.atualizarCampanhas();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}