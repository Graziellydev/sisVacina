package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TelaLogin {

    private String tipoUsuario;

    private CampanhaService campanhaService;
    private AgendamentoService agendamentoService;
    private ParenteService parenteService;

    public TelaLogin(String tipoUsuario,
                     CampanhaService campanhaService,
                     AgendamentoService agendamentoService,
                     ParenteService parenteService) {

        this.tipoUsuario = tipoUsuario;
        this.campanhaService = campanhaService;
        this.agendamentoService = agendamentoService;
        this.parenteService = parenteService;

        JFrame frame = new JFrame("Login");
        frame.setSize(420, 280);
        frame.setLayout(null);

        JLabel lblTitulo = new JLabel("Login - " + tipoUsuario);
        lblTitulo.setBounds(140, 20, 200, 25);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(40, 70, 100, 25);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(140, 70, 200, 25);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(40, 110, 100, 25);

        JFormattedTextField txtCpf = criarCampoCpf();
        txtCpf.setBounds(140, 110, 200, 25);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(140, 160, 90, 30);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(250, 160, 90, 30);

        btnEntrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText();

            if (nome.isEmpty() || cpf.contains("_")) {
                JOptionPane.showMessageDialog(frame,
                        "Preencha nome e CPF corretamente.");
                return;
            }

            frame.dispose();

            if (tipoUsuario.equals("FUNCIONARIO")) {
                new TelaFuncionario(
                        campanhaService,
                        agendamentoService,
                        parenteService
                );
            } else {
                new TelaCidadao(
                        cpf,
                        nome,
                        campanhaService,
                        agendamentoService,
                        parenteService
                );
            }
        });

        btnVoltar.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(
                    campanhaService,
                    agendamentoService,
                    parenteService
            );
        });

        frame.add(lblTitulo);
        frame.add(lblNome);
        frame.add(txtNome);
        frame.add(lblCpf);
        frame.add(txtCpf);
        frame.add(btnEntrar);
        frame.add(btnVoltar);

        frame.setVisible(true);
    }

    private JFormattedTextField criarCampoCpf() {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            return new JFormattedTextField(mask);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}
