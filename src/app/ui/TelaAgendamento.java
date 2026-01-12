package app.ui;

import Modelo.Campanha;
import Modelo.Parente;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaAgendamento {

    private JFrame frame;

    public TelaAgendamento(String cpfCidadao,
                           String nomeCidadao,
                           CampanhaService campanhaService,
                           AgendamentoService agendamentoService,
                           ParenteService parenteService) {

        frame = new JFrame("Agendar Vacinação");
        frame.setSize(650, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Agendamento de Vacinação"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(8, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JComboBox<Campanha> cbCampanhas = new JComboBox<>();
        campanhaService.listarCampanhasAtivas()
                .forEach(cbCampanhas::addItem);

        JComboBox<String> cbPessoa = new JComboBox<>();
        cbPessoa.addItem(nomeCidadao + " (Você)");

        List<Parente> parentes = parenteService.listarParentes(cpfCidadao);
        for (Parente p : parentes) {
            cbPessoa.addItem(p.getNome() + " (Parente)");
        }

        JSpinner calendario = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(calendario, "dd/MM/yyyy");
        calendario.setEditor(editor);

        RoundedButton btnAgendar = new RoundedButton("Confirmar Agendamento");

        centro.add(new JLabel("Campanha:"));
        centro.add(cbCampanhas);
        centro.add(new JLabel("Quem irá se vacinar:"));
        centro.add(cbPessoa);
        centro.add(new JLabel("Data da vacinação:"));
        centro.add(calendario);
        centro.add(new JLabel());
        centro.add(btnAgendar);

        frame.add(centro, BorderLayout.CENTER);

        btnAgendar.addActionListener(e -> {
            Campanha campanha = (Campanha) cbCampanhas.getSelectedItem();
            String pessoaSelecionada = (String) cbPessoa.getSelectedItem();

            if (campanha == null) {
                JOptionPane.showMessageDialog(frame, "Selecione uma campanha.");
                return;
            }

            LocalDate data = LocalDate.now();

            String cpfDestino = cpfCidadao;
            String nomeDestino = nomeCidadao;

            if (pessoaSelecionada.contains("(Parente)")) {
                String nome = pessoaSelecionada.replace(" (Parente)", "");
                Parente p = parenteService.buscarPorNome(cpfCidadao, nome);
                cpfDestino = p.getCpf();
                nomeDestino = p.getNome();
            }

            agendamentoService.agendar(
                    cpfDestino,
                    nomeDestino,
                    campanha.getNome(),
                    data
            );

            JOptionPane.showMessageDialog(frame,
                    "Vacina agendada para " + nomeDestino);

            frame.dispose();
        });

        frame.setVisible(true);
    }
}
