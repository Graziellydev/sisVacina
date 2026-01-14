package app.ui;

import Modelo.Campanha;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TelaAgendamento {

    private JFrame frame;

    public TelaAgendamento(String cpfCidadao, 
                           String nomeCidadao, 
                           CampanhaService campanhaService, 
                           AgendamentoService agendamentoService,
                           TelaCidadao telaPrincipal) {

        frame = new JFrame("Agendar Vacinação");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Novo Agendamento"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(6, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JComboBox<Campanha> cbCampanhas = new JComboBox<>();
        campanhaService.listarCampanhasAtivas().forEach(cbCampanhas::addItem);

        JSpinner calendario = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(calendario, "dd/MM/yyyy");
        calendario.setEditor(editor);

        RoundedButton btnAgendar = new RoundedButton("Confirmar Agendamento");

        centro.add(new JLabel("Selecione a Campanha:"));
        centro.add(cbCampanhas);
        centro.add(new JLabel("Escolha a Data:"));
        centro.add(calendario);
        centro.add(new JLabel());
        centro.add(btnAgendar);

        frame.add(centro, BorderLayout.CENTER);

        btnAgendar.addActionListener(e -> {
            Campanha campanha = (Campanha) cbCampanhas.getSelectedItem();
            if (campanha == null) return;

            Date dataD = (Date) calendario.getValue();
            LocalDate dataLocalDate = dataD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String resultado = agendamentoService.agendar(cpfCidadao, nomeCidadao, campanha.getNome(), dataLocalDate);

            if (resultado.equals("SUCESSO")) {
                telaPrincipal.atualizarHistorico();
                JOptionPane.showMessageDialog(frame, "Vacina agendada com sucesso!");
                frame.dispose();
            } else {
             
                JOptionPane.showMessageDialog(frame, resultado, "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}