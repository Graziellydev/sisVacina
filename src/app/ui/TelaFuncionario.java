package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaFuncionario {

    private JFrame frame;

    public TelaFuncionario(CampanhaService campanhaService,
                           AgendamentoService agendamentoService,
                           ParenteService parenteService) {

        frame = new JFrame("Área do Funcionário");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Área do Funcionário"), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(4, 1, 15, 15));
        centro.setBorder(BorderFactory.createEmptyBorder(30, 120, 30, 120));

        RoundedButton btnAdicionar = new RoundedButton("Adicionar Campanha");
        RoundedButton btnEditar = new RoundedButton("Editar Campanha");
        RoundedButton btnExcluir = new RoundedButton("Excluir Campanha");
        RoundedButton btnSair = new RoundedButton("Sair");

        centro.add(btnAdicionar);
        centro.add(btnEditar);
        centro.add(btnExcluir);
        centro.add(btnSair);

        frame.add(centro, BorderLayout.CENTER);

        btnAdicionar.addActionListener(e ->
                new TelaCadastroCampanha(campanhaService)
        );

        btnEditar.addActionListener(e ->
                new TelaEditarCampanha(campanhaService)
        );

        btnExcluir.addActionListener(e ->
                new TelaExcluirCampanha(campanhaService)
        );

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService, parenteService);
        });

        frame.setVisible(true);
    }
}
