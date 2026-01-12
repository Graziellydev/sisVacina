package Main;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import Servico.ParenteService;
import app.ui.TelaInicial;

public class Main {

    public static void main(String[] args) {

        // Services únicos (compartilhados por todo o sistema)
        CampanhaService campanhaService = new CampanhaService();
        AgendamentoService agendamentoService = new AgendamentoService();
        ParenteService parenteService = new ParenteService();

        // Tela inicial do sistema
        new TelaInicial(
                campanhaService,
                agendamentoService,
                parenteService
        );
    }
}
