package app.ui;

import Modelo.Campanha;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;

public class TelaFuncionario {

    private JFrame frame;
    private DefaultListModel<Campanha> modelCampanhas;
    private CampanhaService campanhaService;
    private AgendamentoService agendamentoService;

    public TelaFuncionario(CampanhaService campanhaService, AgendamentoService agendamentoService) {
        this.campanhaService = campanhaService;
        this.agendamentoService = agendamentoService;

        frame = new JFrame("Painel Administrativo - Funcionário");
        frame.setSize(850, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Gerenciamento de Campanhas e Doses"), BorderLayout.NORTH);

        JPanel painelEsquerdo = new JPanel(new GridLayout(6, 1, 15, 15));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        painelEsquerdo.setBackground(new Color(240, 240, 240));

        RoundedButton btnNovaCampanha = new RoundedButton("Nova Campanha");
        RoundedButton btnGerenciar = new RoundedButton("Editar/Excluir");
        RoundedButton btnAplicarDose = new RoundedButton("Aplicar Dose");
        RoundedButton btnSair = new RoundedButton("Sair do Sistema");

        painelEsquerdo.add(btnNovaCampanha);
        painelEsquerdo.add(btnGerenciar);
        painelEsquerdo.add(new JLabel("--- Registro ---", SwingConstants.CENTER));
        painelEsquerdo.add(btnAplicarDose);
        painelEsquerdo.add(new JLabel());
        painelEsquerdo.add(btnSair);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createTitledBorder("Visualização de Campanhas Ativas"));
        
        modelCampanhas = new DefaultListModel<>();
        JList<Campanha> listaCampanhas = new JList<>(modelCampanhas);
        listaHistoricoVisual(listaCampanhas);
        
        painelCentral.add(new JScrollPane(listaCampanhas), BorderLayout.CENTER);

        frame.add(painelEsquerdo, BorderLayout.WEST);
        frame.add(painelCentral, BorderLayout.CENTER);

        atualizarCampanhas();

        btnNovaCampanha.addActionListener(e -> new TelaCadastroCampanha(campanhaService, this));

     
        btnGerenciar.addActionListener(e -> new TelaGerenciarCampanhas(campanhaService, this));

        
        btnAplicarDose.addActionListener(e -> new TelaAplicarDose(agendamentoService, campanhaService));

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService);
        });

        frame.setVisible(true);
    }

    public void atualizarCampanhas() {
        modelCampanhas.clear();
        campanhaService.listarTodas().forEach(modelCampanhas::addElement);
    }

    private void listaHistoricoVisual(JList<Campanha> lista) {
        lista.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}