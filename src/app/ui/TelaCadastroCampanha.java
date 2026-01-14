package app.ui;

import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TelaCadastroCampanha {

    public TelaCadastroCampanha(CampanhaService campanhaService, TelaFuncionario telaPrincipal) {
        JFrame frame = new JFrame("Nova Campanha");
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Configurar Campanha"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(8, 1, 5, 5));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JTextField txtNome = new JTextField();
        JTextField txtVacina = new JTextField();
        
        JSpinner spinnerDataFim = new JSpinner(new SpinnerDateModel());
        spinnerDataFim.setEditor(new JSpinner.DateEditor(spinnerDataFim, "dd/MM/yyyy"));

        RoundedButton btnSalvar = new RoundedButton("Ativar Campanha");

        centro.add(new JLabel("Nome da Campanha:"));
        centro.add(txtNome);
        centro.add(new JLabel("Tipo da Vacina:"));
        centro.add(txtVacina);
        centro.add(new JLabel("Data de Término:"));
        centro.add(spinnerDataFim);
        centro.add(new JLabel()); 
        centro.add(btnSalvar);

        frame.add(centro, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            if(txtNome.getText().isEmpty()) return;

            Date dataD = (Date) spinnerDataFim.getValue();
            LocalDate dataFim = dataD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            campanhaService.adicionarCampanha(
                txtNome.getText(),
                txtVacina.getText(),
                "Geral", 
                dataFim, 0
            );

            telaPrincipal.atualizarCampanhas();
            JOptionPane.showMessageDialog(frame, "Campanha registrada!");
            frame.dispose();
        });

        frame.setVisible(true);
    }
}