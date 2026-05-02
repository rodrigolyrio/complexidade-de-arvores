package br.ifes.tpa.util;

public class ResultadoMedicao<T> {
    private final T resultado;
    private final long tempoNano;

    public ResultadoMedicao(T resultado, long tempoNano) {
        this.resultado = resultado;
        this.tempoNano = tempoNano;
    }

    public T getResultado() {
        return resultado;
    }

    public long getTempoNano() {
        return tempoNano;
    }

    public double getTempoMillis() {
        return tempoNano / 1_000_000.0;
    }
}