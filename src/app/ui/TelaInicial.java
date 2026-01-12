package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;
import app.ui.components.*;
import app.ui.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class TelaInicial {

    public TelaInicial(CampanhaService campanhaService,
                       AgendamentoService agendamentoService,
                       ParenteService parenteService) {

        JFrame frame = new JFrame("Posto de Saúde");
        frame.setSize(520, 380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Bem-vindo ao Sistema do Posto de Saúde"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(2, 1, 20, 20));
        centro.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));

        RoundedButton btnFuncionario = new RoundedButton("Sou Funcionário");
        RoundedButton btnCidadao = new RoundedButton("Sou Cidadão");

        centro.add(btnFuncionario);
        centro.add(btnCidadao);

        frame.add(centro, BorderLayout.CENTER);

        btnFuncionario.addActionListener(e -> {
            frame.dispose();
            new TelaLogin("FUNCIONARIO", campanhaService, agendamentoService, parenteService);
        });

        btnCidadao.addActionListener(e -> {
            frame.dispose();
            new TelaLogin("CIDADAO", campanhaService, agendamentoService, parenteService);
        });

        frame.setVisible(true);
    }
}
