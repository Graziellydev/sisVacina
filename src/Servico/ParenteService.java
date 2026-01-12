package Servico;

import Modelo.Parente;

import java.util.ArrayList;
import java.util.List;

public class ParenteService {

    private List<Parente> parentes = new ArrayList<>();

    public void filiar(Parente p) {
        parentes.add(p);
    }

    public List<Parente> listarPorResponsavel(String cpfResponsavel) {
        List<Parente> lista = new ArrayList<>();

        for (Parente p : parentes) {
            if (p.getCpfResponsavel().equals(cpfResponsavel)) {
                lista.add(p);
            }
        }
        return lista;
    }
}
