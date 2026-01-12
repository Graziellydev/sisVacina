package Servico;

import Modelo.Parente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParenteService {

    private final List<Parente> parentes = new ArrayList<>();

    public void filiarParente(String cpfResponsavel, String cpfParente, String nome) {
        parentes.add(new Parente(cpfResponsavel, cpfParente, nome));
    }

    public List<Parente> listarParentes(String cpfResponsavel) {
        return parentes.stream()
                .filter(p -> p.getCpfResponsavel().equals(cpfResponsavel))
                .collect(Collectors.toList());
    }

    public Parente buscarPorNome(String cpfResponsavel, String nome) {
        return parentes.stream()
                .filter(p -> p.getCpfResponsavel().equals(cpfResponsavel))
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
