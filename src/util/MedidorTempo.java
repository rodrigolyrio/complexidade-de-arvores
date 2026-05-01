package br.ifes.tpa.util;

import java.util.function.Supplier;

public class MedidorTempo {

    public static long medir(Runnable acao) {
        long inicio = System.nanoTime(); // Marca o tempo inicial em nanossegundos
        acao.run(); // Executa a ação passada como parâmetro
        long fim = System.nanoTime(); // Marca o tempo final após execução
        return fim - inicio;
    }

    public static <T> ResultadoMedicao<T> medirComRetorno(Supplier<T> acao) {
        long inicio = System.nanoTime(); // Marca o tempo inicial
        T resultado = acao.get(); // Executa a ação e armazena o resultado retornado
        long fim = System.nanoTime(); // Marca o tempo final
        return new ResultadoMedicao<T>(resultado, fim - inicio);
    }

    public static void imprimirTempo(String descricao, long tempoNano) {
        System.out.println(descricao + ": " + (tempoNano / 1_000_000.0) + " ms\n");
    }
}