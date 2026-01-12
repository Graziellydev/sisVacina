package app.ui;

import Modelo.Campanha;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;

import javax.swing.*;

public class TelaFuncionario {

    private DefaultListModel<Campanha> model;
    private CampanhaService campanhaService;

    public TelaFuncionario(CampanhaService campanhaService,
                           AgendamentoService agendamentoService,
                           ParenteService parenteService) {

        this.campanhaService = campanhaService;

        JFrame frame = new JFrame("Área do Funcionário");
        frame.setSize(540, 360);

        model = new DefaultListModel<>();
        JList<Campanha> lista = new JList<>(model);

        atualizarLista();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnSair = new JButton("Sair");

        btnAdicionar.addActionListener(e -> {
            new TelaCadastroCampanha(campanhaService, this::atualizarLista);
        });

        btnEditar.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                new TelaCadastroCampanha(campanhaService, c, this::atualizarLista);
            }
        });

        btnExcluir.addActionListener(e -> {
            Campanha c = lista.getSelectedValue();
            if (c != null) {
                campanhaService.removerCampanha(c);
                atualizarLista();
            }
        });

        btnSair.addActionListener(e -> {
            frame.dispose();
            new TelaInicial(campanhaService, agendamentoService, parenteService);
        });

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);
        botoes.add(btnSair);

        frame.add(new JScrollPane(lista), "Center");
        frame.add(botoes, "South");
        frame.setVisible(true);
    }

    private void atualizarLista() {
        model.clear();
        campanhaService.listarCampanhasAtivas()
                .forEach(model::addElement);
    }
}
