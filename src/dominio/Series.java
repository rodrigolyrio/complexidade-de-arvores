package br.ifes.tpa.dominio;

import java.util.Comparator;

public class Series {
    private String nome;
    private int anoLancamento;

    public Series(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
    }

    public String getNome() { return nome; }
    public int getAnoLancamento() { return anoLancamento; }

    @Override
    public String toString() {
        return String.format("Série: %-20s | Ano: %d", nome, anoLancamento);
    }
}