package app.ui;

import Modelo.Campanha;
import Servico.CampanhaService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class TelaCadastroCampanha {

    public TelaCadastroCampanha(CampanhaService service, Runnable callback) {
        this(service, null, callback);
    }

    public TelaCadastroCampanha(CampanhaService service,
                                Campanha campanhaEditar,
                                Runnable callback) {

        JFrame frame = new JFrame("Cadastro de Campanha");
        frame.setSize(420, 300);
        frame.setLayout(null);

        JLabel lblVacina = new JLabel("Nome da Vacina:");
        lblVacina.setBounds(30, 30, 150, 25);

        JTextField txtVacina = new JTextField();
        txtVacina.setBounds(180, 30, 180, 25);

        JLabel lblPublico = new JLabel("Público-alvo:");
        lblPublico.setBounds(30, 70, 150, 25);

        JTextField txtPublico = new JTextField();
        txtPublico.setBounds(180, 70, 180, 25);

        JLabel lblData = new JLabel("Data fim:");
        lblData.setBounds(30, 110, 150, 25);

        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
        spinner.setBounds(180, 110, 180, 25);

        if (campanhaEditar != null) {
            txtVacina.setText(campanhaEditar.getNomeVacina());
            txtPublico.setText(campanhaEditar.getPublico());
        }

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 180, 160, 30);

        btnSalvar.addActionListener(e -> {
            try {
                Date d = (Date) spinner.getValue();
                LocalDate dataFim = new java.sql.Date(d.getTime()).toLocalDate();

                if (campanhaEditar == null) {
                    service.cadastrarCampanha(
                            txtVacina.getText(),
                            txtPublico.getText(),
                            dataFim
                    );
                } else {
                    campanhaEditar.setNomeVacina(txtVacina.getText());
                    campanhaEditar.setPublico(txtPublico.getText());
                    campanhaEditar.setDataFim(dataFim);
                }

                callback.run();
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar campanha.");
            }
        });

        frame.add(lblVacina);
        frame.add(txtVacina);
        frame.add(lblPublico);
        frame.add(txtPublico);
        frame.add(lblData);
        frame.add(spinner);
        frame.add(btnSalvar);

        frame.setVisible(true);
    }
}
