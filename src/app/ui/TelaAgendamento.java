package app.ui;

import Modelo.Parente;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class TelaAgendamento {

    public TelaAgendamento(
            String cpfCidadao,
            String nomeCidadao,
            CampanhaService campanhaService,
            AgendamentoService agendamentoService,
            ParenteService parenteService
    ) {

        JFrame frame = new JFrame("Agendar Vacina");
        frame.setSize(460, 360);
        frame.setLayout(null);

        JLabel lblPessoa = new JLabel("Agendar para:");
        lblPessoa.setBounds(30, 30, 120, 25);

        JComboBox<Object> comboPessoa = new JComboBox<>();
        comboPessoa.setBounds(150, 30, 250, 25);

        // O próprio cidadão
        comboPessoa.addItem(nomeCidadao + " (Você)");

        // Parentes filiados
        for (Parente p : parenteService.listarPorResponsavel(cpfCidadao)) {
            comboPessoa.addItem(p);
        }

        JLabel lblVacina = new JLabel("Vacina:");
        lblVacina.setBounds(30, 80, 120, 25);

        JComboBox<String> comboVacina = new JComboBox<>();
        campanhaService.listarCampanhasAtivas()
                .forEach(c -> comboVacina.addItem(c.getNomeVacina()));
        comboVacina.setBounds(150, 80, 250, 25);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(30, 130, 120, 25);

        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinnerData = new JSpinner(model);
        spinnerData.setEditor(
                new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy")
        );
        spinnerData.setBounds(150, 130, 250, 25);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(150, 200, 160, 30);

        btnAgendar.addActionListener(e -> {
            try {
                Object selecionado = comboPessoa.getSelectedItem();

                String cpf;
                String nome;

                if (selecionado instanceof Parente) {
                    Parente p = (Parente) selecionado;
                    cpf = p.getCpfParente();
                    nome = p.getNome() + " (Parente)";
                } else {
                    cpf = cpfCidadao;
                    nome = nomeCidadao;
                }

                Date d = (Date) spinnerData.getValue();
                LocalDate data = new java.sql.Date(d.getTime()).toLocalDate();

                agendamentoService.agendar(
                        cpf,
                        nome,
                        comboVacina.getSelectedItem().toString(),
                        data
                );

                JOptionPane.showMessageDialog(frame,
                        "Vacina agendada com sucesso!");

                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Erro ao agendar vacina.");
            }
        });

        frame.add(lblPessoa);
        frame.add(comboPessoa);
        frame.add(lblVacina);
        frame.add(comboVacina);
        frame.add(lblData);
        frame.add(spinnerData);
        frame.add(btnAgendar);

        frame.setVisible(true);
    }
}
