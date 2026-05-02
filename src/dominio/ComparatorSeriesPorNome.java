package br.ifes.tpa.dominio;
import java.util.Comparator;


public class ComparatorSeriesPorNome implements Comparator<Series> {

    /**
     * Compara duas séries pelo nome, sem diferenciação de maiúsculas/minúsculas.
     **/
    @Override
    public int compare(Series s1, Series s2) {
        return s1.getNome().compareToIgnoreCase(s2.getNome());
    }
}