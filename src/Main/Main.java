package Main;

import Servico.AgendamentoService;
import Servico.CampanhaService;
import app.ui.TelaInicial;

public class Main {

    public static void main(String[] args) {

        CampanhaService campanhaService = new CampanhaService();
        AgendamentoService agendamentoService = new AgendamentoService();
      
        new TelaInicial(
                campanhaService,
                agendamentoService
        );
    }
}
