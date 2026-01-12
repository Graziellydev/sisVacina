package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;

import javax.swing.*;

public class TelaCidadao {

    private String cpfCidadao;
    private String nomeCidadao;

    private CampanhaService campanhaService;
    private AgendamentoService agendamentoService;
    private ParenteService parenteService;

    public TelaCidadao(String cpfCidadao,
                       String nomeCidadao,
                       CampanhaService campanhaService,
                       AgendamentoService agendamentoService,
                       ParenteService parenteService) {

        this.cpfCidadao = cpfCidadao;
        this.nomeCidadao = nomeCidadao;
        this.campanhaService = campanhaService;
        this.agendamentoService = agendamentoService;
        this.parenteService = parenteService;

        JFrame frame = new JFrame("Área do Cidadão");
        frame.setSize(460, 380);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + nomeCidadao);
        lblBemVindo.setBounds(20, 20, 300, 25);

        JButton btnCampanhas = new JButton("Ver Campanhas");
        btnCampanhas.setBounds(130, 60, 200, 30);

        JButton btnAgendar = new JButton("Agendar Vacina");
        btnAgendar.setBounds(130, 100, 200, 30);

        JButton btnMinhasVacinas = new JButton("Minhas Vacinas");
        btnMinhasVacinas.setBounds(130, 140, 200, 30);

        JButton btnFiliarParente = new JButton("Filiar Parente");
        btnFiliarParente.setBounds(130, 180, 200, 30);

        JButton btnParentes = new JButton("Parentes Filiados");
        btnParentes.setBounds(130, 220, 200, 30);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(130, 270, 200, 30);

        // Ações dos botões

        btnCampanhas.addActionListener(e ->
                new TelaCampanha(campanhaService)
        );

        btnAgendar.addActionListener(e ->
                new TelaAgendamento(
                        cpfCidadao,
                        nomeCidadao,
                        campanhaService,
                        agendamentoService,
                        parenteService
                )
        );

        btnMinhasVacinas.addActionListener(e ->
                new TelaVacinasAgendadas(
                        cpfCidadao,
                        agendamentoService,
                        parenteService
                )
        );

        btnFiliarParente.addActionListener(e ->
                new TelaFiliarParente(
                        cpfCidadao,
                        parenteService
                )
        );

        btnParentes.addActionListener(e ->
                new TelaParente(
                        cpfCidadao,
                        parenteService
                )
        );

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(
                    campanhaService,
                    agendamentoService,
                    parenteService
            );
        });

        frame.add(lblBemVindo);
        frame.add(btnCampanhas);
        frame.add(btnAgendar);
        frame.add(btnMinhasVacinas);
        frame.add(btnFiliarParente);
        frame.add(btnParentes);
        frame.add(btnSair);

        frame.setVisible(true);
    }
}
