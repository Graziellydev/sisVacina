package app.ui;

import Modelo.Campanha;
import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.components.RoundedButton;
import app.ui.components.TitleLabel;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaAplicarDose {

    private JFrame frame;

    public TelaAplicarDose(AgendamentoService agendamentoService, CampanhaService campanhaService) {
        frame = new JFrame("Registro de Vacinação");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(new TitleLabel("Aplicar Dose"), BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridLayout(6, 1, 10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // CRIAÇÃO DA MÁSCARA DE CPF
        JFormattedTextField txtCpf = new JFormattedTextField();
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            mascaraCpf.install(txtCpf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JComboBox<String> cbCampanhas = new JComboBox<>();
        campanhaService.listarTodas().forEach(c -> cbCampanhas.addItem(c.getNome()));

        RoundedButton btnConfirmar = new RoundedButton("Confirmar Aplicação");

        painelCentral.add(new JLabel("CPF do Cidadão:"));
        painelCentral.add(txtCpf);
        painelCentral.add(new JLabel("Campanha:"));
        painelCentral.add(cbCampanhas);
        painelCentral.add(new JLabel()); // Espaçador
        painelCentral.add(btnConfirmar);

        frame.add(painelCentral, BorderLayout.CENTER);

        btnConfirmar.addActionListener(e -> {
            // O CPF vem com pontos e traços, o trim remove espaços
            String cpf = txtCpf.getText().trim();
            String nomeCampanha = (String) cbCampanhas.getSelectedItem();

            // Validação básica se o campo foi preenchido além da máscara
            if (cpf.contains("_") || nomeCampanha == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha o CPF completo!");
                return;
            }

            boolean sucesso = agendamentoService.aplicarDose(cpf, nomeCampanha, campanhaService);

            if (sucesso) {
                JOptionPane.showMessageDialog(frame, "Sucesso: Registro atualizado para " + nomeCampanha);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Erro: Nenhum agendamento pendente para este CPF nesta campanha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}