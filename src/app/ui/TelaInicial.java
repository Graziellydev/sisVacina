package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;

import javax.swing.*;
import javax.swing.SwingConstants;

public class TelaInicial {

    public TelaInicial(CampanhaService campanhaService,
                       AgendamentoService agendamentoService,
                       ParenteService parenteService) {

        JFrame frame = new JFrame("Posto de Saúde");
        frame.setSize(420, 260);
        frame.setLayout(null);

        JLabel titulo = new JLabel("Bem-vindo ao Sistema do Posto de Saúde", SwingConstants.CENTER);
        titulo.setBounds(20, 20, 380, 30);

        JLabel pergunta = new JLabel("Você é Funcionário ou Cidadão?", SwingConstants.CENTER);
        pergunta.setBounds(20, 60, 380, 25);

        JButton btnFuncionario = new JButton("Funcionário");
        btnFuncionario.setBounds(130, 110, 160, 30);

        JButton btnCidadao = new JButton("Cidadão");
        btnCidadao.setBounds(130, 150, 160, 30);

        btnFuncionario.addActionListener(e -> {
            frame.dispose();
            new TelaLogin(
                    "FUNCIONARIO",
                    campanhaService,
                    agendamentoService,
                    parenteService
            );
        });

        btnCidadao.addActionListener(e -> {
            frame.dispose();
            new TelaLogin(
                    "CIDADAO",
                    campanhaService,
                    agendamentoService,
                    parenteService
            );
        });

        frame.add(titulo);
        frame.add(pergunta);
        frame.add(btnFuncionario);
        frame.add(btnCidadao);

        frame.setVisible(true);
    }
}
