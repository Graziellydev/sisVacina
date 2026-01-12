package app.ui;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;
import app.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class TelaCidadao {

    public TelaCidadao(String cpf,
                       String nome,
                       CampanhaService campanhaService,
                       AgendamentoService agendamentoService,
                       ParenteService parenteService) {

        JFrame frame = new JFrame("Área do Cidadão");
        frame.setSize(600, 460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Bem-vindo, " + nome), BorderLayout.NORTH);

        TexturedPanel centro = new TexturedPanel();
        centro.setLayout(new GridLayout(5, 1, 15, 15));
        centro.setBorder(BorderFactory.createEmptyBorder(30, 120, 30, 120));

        RoundedButton btnCampanhas = new RoundedButton("Ver Campanhas");
        RoundedButton btnAgendar = new RoundedButton("Agendar Vacina");
        RoundedButton btnMinhasVacinas = new RoundedButton("Minhas Vacinas");
        RoundedButton btnParente = new RoundedButton("Filiar Parente");
        RoundedButton btnSair = new RoundedButton("Sair");

        centro.add(btnCampanhas);
        centro.add(btnAgendar);
        centro.add(btnMinhasVacinas);
        centro.add(btnParente);
        centro.add(btnSair);

        frame.add(centro, BorderLayout.CENTER);

        btnCampanhas.addActionListener(e ->
                new TelaCampanha(campanhaService)
        );

        btnAgendar.addActionListener(e ->
                new TelaAgendamento(cpf, nome, campanhaService, agendamentoService, parenteService)
        );

        btnMinhasVacinas.addActionListener(e ->
                new TelaVacinasAgendadas(cpf, agendamentoService, parenteService)
        );

        btnParente.addActionListener(e ->
                new TelaFiliarParente(cpf, parenteService)
        );

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService, parenteService);
        });

        frame.setVisible(true);
    }
}
