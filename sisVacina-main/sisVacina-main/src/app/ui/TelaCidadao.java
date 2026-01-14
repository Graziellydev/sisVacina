package app.ui;

import Modelo.Agendamento;
import Modelo.Campanha;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.components.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaCidadao {

    private JFrame frame;
    private DefaultListModel<String> modelHistorico;
    private String cpf;
    private String nome;
    private AgendamentoService agendamentoService;
    private CampanhaService campanhaService;

    public TelaCidadao(String cpf, String nome, CampanhaService campanhaService, AgendamentoService agendamentoService) {
        this.cpf = cpf;
        this.nome = nome;
        this.campanhaService = campanhaService;
        this.agendamentoService = agendamentoService;

        frame = new JFrame("Minha Área - " + nome);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Painel do Cidadão"), BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel colCampanhas = new JPanel(new BorderLayout());
        colCampanhas.setBorder(BorderFactory.createTitledBorder("Campanhas Ativas"));
        
        DefaultListModel<Campanha> modelCamp = new DefaultListModel<>();
        campanhaService.listarCampanhasAtivas().forEach(modelCamp::addElement);
        JList<Campanha> listaCampanhas = new JList<>(modelCamp);
        colCampanhas.add(new JScrollPane(listaCampanhas), BorderLayout.CENTER);

        RoundedButton btnAgendar = new RoundedButton("Agendar Selecionada");
        colCampanhas.add(btnAgendar, BorderLayout.SOUTH);

        JPanel colHistorico = new JPanel(new BorderLayout());
        colHistorico.setBorder(BorderFactory.createTitledBorder("Meu Histórico"));
        
        modelHistorico = new DefaultListModel<>();
        JList<String> listaHistorico = new JList<>(modelHistorico);
        listaHistorico.setFont(new Font("Monospaced", Font.PLAIN, 12));
        colHistorico.add(new JScrollPane(listaHistorico), BorderLayout.CENTER);

        painelCentral.add(colCampanhas);
        painelCentral.add(colHistorico);
        frame.add(painelCentral, BorderLayout.CENTER);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        RoundedButton btnSair = new RoundedButton("Sair");
        rodape.add(btnSair);
        frame.add(rodape, BorderLayout.SOUTH);

        atualizarHistorico();

        btnAgendar.addActionListener(e -> {
            Campanha sel = listaCampanhas.getSelectedValue();
            if (sel != null) {
                new TelaAgendamento(cpf, nome, campanhaService, agendamentoService, this);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma campanha na lista.");
            }
        });

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService);
        });

        frame.setVisible(true);
    }

    public void atualizarHistorico() {
        modelHistorico.clear();
        List<Agendamento> agendamentos = agendamentoService.listarTodosDoCidadao(cpf);
        LocalDate hoje = LocalDate.now();

        modelHistorico.addElement("=== VACINAS APLICADAS ===");
        for (Agendamento a : agendamentos) {
            if (a.getStatus().equalsIgnoreCase("Concluído") || a.getStatus().equalsIgnoreCase("1ª Dose Aplicada")) {
                modelHistorico.addElement(" [OK] " + a.getCampanha() + " - " + a.getStatus() + " - " + a.getData());
            }
        }

        modelHistorico.addElement("");
        modelHistorico.addElement("=== PRÓXIMOS AGENDAMENTOS ===");
        for (Agendamento a : agendamentos) {
            if (a.getStatus().equalsIgnoreCase("Agendado") && (a.getData().isAfter(hoje) || a.getData().isEqual(hoje))) {
                modelHistorico.addElement(" [⏳] " + a.getCampanha() + " - " + a.getStatus() + " - " + a.getData());
            }
        }

        modelHistorico.addElement("");
        modelHistorico.addElement("=== VACINAS PERDIDAS / EXPIRADAS ===");
        for (Agendamento a : agendamentos) {
            if (a.getStatus().equalsIgnoreCase("Agendado") && a.getData().isBefore(hoje)) {
                modelHistorico.addElement(" [❌] " + a.getCampanha() + " - Não Compareceu - " + a.getData());
            }
        }
        
        if (agendamentos.isEmpty()) {
            modelHistorico.addElement("(Nenhum registro encontrado)");
        }
    }
}