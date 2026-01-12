package app.ui;

import Servico.CampanhaService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaCadastroCampanha {

    public TelaCadastroCampanha(CampanhaService campanhaService) {

        JFrame frame = new JFrame("Nova Campanha");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Cadastrar Campanha"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(6, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JTextField txtNome = new JTextField();
        JTextField txtPublico = new JTextField();
        JTextField txtVacina = new JTextField();

        RoundedButton btnSalvar = new RoundedButton("Salvar");

        centro.add(new JLabel("Nome da Campanha:"));
        centro.add(txtNome);
        centro.add(new JLabel("Vacina:"));
        centro.add(txtVacina);
        centro.add(new JLabel("Público-alvo:"));
        centro.add(txtPublico);
        centro.add(btnSalvar);

        frame.add(centro, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            campanhaService.adicionarCampanha(
                    txtNome.getText(),
                    txtVacina.getText(),
                    txtPublico.getText(),
                    LocalDate.now().plusMonths(1)
            );
            JOptionPane.showMessageDialog(frame, "Campanha cadastrada!");
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
