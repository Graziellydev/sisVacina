package app.ui;

import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaFiliarParente {

    public TelaFiliarParente(String cpfResponsavel, ParenteService parenteService) {

        JFrame frame = new JFrame("Filiar Parente");
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Filiar Parente"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(4, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JTextField txtNome = new JTextField();
        JFormattedTextField txtCpf = criarCPF();

        RoundedButton btnSalvar = new RoundedButton("Salvar");

        centro.add(new JLabel("Nome do Parente:"));
        centro.add(txtNome);
        centro.add(new JLabel("CPF do Parente:"));
        centro.add(txtCpf);

        frame.add(centro, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> {
            parenteService.filiarParente(
                    cpfResponsavel,
                    txtCpf.getText(),
                    txtNome.getText()
            );
            JOptionPane.showMessageDialog(frame, "Parente filiado!");
            frame.dispose();
        });

        JPanel rodape = new JPanel();
        rodape.add(btnSalvar);
        frame.add(rodape, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JFormattedTextField criarCPF() {
        try {
            return new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}
