package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaLogin {

    private JFrame frame;

    public TelaLogin(String tipoUsuario, CampanhaService campanhaService, AgendamentoService agendamentoService) {

        frame = new JFrame("Login");
        frame.setSize(500, 380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Login - " + tipoUsuario), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(4, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(30, 100, 20, 100));

        JTextField txtNome = new JTextField();
        JFormattedTextField txtCpf = criarCampoCPF();

        centro.add(new JLabel("Nome:"));
        centro.add(txtNome);
        centro.add(new JLabel("CPF:"));
        centro.add(txtCpf);

        frame.add(centro, BorderLayout.CENTER);

        JPanel rodape = new JPanel(new FlowLayout());
        RoundedButton btnEntrar = new RoundedButton("Entrar");
        RoundedButton btnVoltar = new RoundedButton("Voltar");

        rodape.add(btnEntrar);
        rodape.add(btnVoltar);

        frame.add(rodape, BorderLayout.SOUTH);

        btnEntrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();

            if (nome.isEmpty() || cpf.contains("_")) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
                return;
            }

            frame.dispose();

            if (tipoUsuario.equals("FUNCIONARIO")) {
                new TelaFuncionario(campanhaService, agendamentoService);
            } else {
                new TelaCidadao(cpf, nome, campanhaService, agendamentoService);
            }
        });

        btnVoltar.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService);
        });

        frame.setVisible(true);
    }

    private JFormattedTextField criarCampoCPF() {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            return new JFormattedTextField(mask);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}