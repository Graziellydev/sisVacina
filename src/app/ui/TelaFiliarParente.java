package app.ui;

import Modelo.Parente;
import Servico.ParenteService;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TelaFiliarParente {

    public TelaFiliarParente(String cpfResponsavel, ParenteService service) {

        JFrame frame = new JFrame("Filiar Parente");
        frame.setSize(420, 260);
        frame.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 40, 100, 25);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(150, 40, 220, 25);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(30, 80, 100, 25);

        JFormattedTextField txtCpf = new JFormattedTextField();
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            txtCpf.setFormatterFactory(new DefaultFormatterFactory(mask));
        } catch (ParseException e) {
        }
        txtCpf.setBounds(150, 80, 220, 25);

        JButton btnSalvar = new JButton("Filiar");
        btnSalvar.setBounds(130, 150, 150, 30);

        btnSalvar.addActionListener(e -> {
            service.filiar(new Parente(
                    cpfResponsavel,
                    txtCpf.getText(),
                    txtNome.getText()
            ));
            JOptionPane.showMessageDialog(frame, "Parente filiado!");
            frame.dispose();
        });

        frame.add(lblNome);
        frame.add(txtNome);
        frame.add(lblCpf);
        frame.add(txtCpf);
        frame.add(btnSalvar);

        frame.setVisible(true);
    }
}
