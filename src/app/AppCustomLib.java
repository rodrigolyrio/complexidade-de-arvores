// App que usa a biblioteca desenvolvida.
// Esse código é o antigo Main.java do trabalho anterior. Falta inserir a lógica nova no codigo.

package br.ifes.tpa.app;

import br.ifes.tpa.biblioteca.ArvoreBinaria;       // Importa a árvore binária
import br.ifes.tpa.biblioteca.IColecao;
import br.ifes.tpa.dominio.ComparatorSeriesPorNome; // Importa o Comparator
import br.ifes.tpa.dominio.Series;
import br.ifes.tpa.util.MedidorTempo;
import br.ifes.tpa.util.ResultadoMedicao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * Aplicativo principal que gerencia uma coleção de Séries usando
 * uma Árvore Binária de Busca (BST) como estrutura de dados.
 **/
public class AppCustomLib {

    // Carregamento de dados do arquivo

    public static void carregarDados(IColecao<Series> colecao) {
        try {
            ResultadoMedicao<Integer> resultado = MedidorTempo.medirComRetorno(() -> {
                int quantidade = 0;

                try (BufferedReader buff = new BufferedReader(new FileReader("series_100k.txt"))) {
                    System.out.println("Carregando dados do arquivo...");
                    String linha;

                    while ((linha = buff.readLine()) != null) {
                        if (linha.trim().isEmpty()) continue;

                        String[] partes = linha.split(";");

                        if (partes.length == 3) {
                            String nome = partes[0];
                            int ano    = Integer.parseInt(partes[1]);
                            String pais = partes[2];

                            colecao.adicionar(new Series(nome, ano, pais));
                            quantidade++;
                        }
                    }

                } catch (IOException | NumberFormatException e) {
                    throw new RuntimeException(e);
                }

                return quantidade;
            });

            System.out.println("Dados carregados com sucesso.");
            System.out.println("Quantidade de séries carregadas: " + resultado.getResultado());
            MedidorTempo.imprimirTempo("Tempo de leitura e inserção na árvore", resultado.getTempoNano());

        } catch (RuntimeException e) {
            if (e.getCause() instanceof FileNotFoundException) {
                System.out.println("Arquivo 'series_100k.txt' não encontrado. Iniciando com árvore vazia.\n");
            } else {
                System.out.println("Erro ao ler o arquivo: " + e.getCause().getMessage());
            }
        }
    }

    // Menu principal

    public static void main(String[] args) {


        // ArvoreBinariaBase implementa IColecao, e ArvoreBinaria estende ArvoreBinariaBase.


        ArvoreBinaria<Series> colecao = new ArvoreBinaria<>(new ComparatorSeriesPorNome());

        carregarDados(colecao);

        int ano, resp;
        String nome, pais;
        Scanner scanner = new Scanner(System.in);

        try {
            do {
                String menu =
                        "**********************\n" +
                                "Escolha uma opção:\n"     +
                                "1) Adicionar uma Série\n" +
                                "2) Remover uma Série\n"   +
                                "3) Pesquisar uma Série\n" +
                                "4) Listar Séries (em ordem)\n" +
                                "5) Quantidade de nós\n"   +
                                "6) Altura da árvore\n"    +
                                "7) Caminhamento em Nível\n" +
                                "0) Sair\n";

                System.out.println(menu);
                System.out.print("Sua opção: ");
                resp = scanner.nextInt();
                scanner.nextLine();

                if (resp < 0 || resp > 7) {
                    System.out.println("Opção inválida. Tente novamente.\n");
                    continue;
                }

                switch (resp) {

                    case 1: // Adicionar
                        System.out.println("Digite o nome da Série:");
                        nome = scanner.nextLine();

                        System.out.println("Digite o ano da Série:");
                        ano = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Digite o país da Série:");
                        pais = scanner.nextLine();

                        Series novaSerie = new Series(nome, ano, pais);
                        long tempoAdicionar = MedidorTempo.medir(() -> colecao.adicionar(novaSerie));

                        System.out.println("Série adicionada com sucesso.");
                        MedidorTempo.imprimirTempo("Tempo de inserção", tempoAdicionar);
                        break;

                    case 2: // Remover
                        System.out.println("Digite o nome da Série a ser removida:");
                        nome = scanner.nextLine();

                        // Chave de busca: apenas o nome precisa estar preenchido, pois o Comparator só usa getNome().
                        Series chaveRemocao = new Series(nome, 0, "");

                        ResultadoMedicao<Boolean> resultadoRemocao =
                                MedidorTempo.medirComRetorno(() -> colecao.remover(chaveRemocao));

                        System.out.println(resultadoRemocao.getResultado()
                                ? "Série removida com sucesso."
                                : "Série não encontrada na árvore.");
                        MedidorTempo.imprimirTempo("Tempo de remoção", resultadoRemocao.getTempoNano());
                        break;

                    case 3: // Pesquisar
                        System.out.println("Digite o nome da Série a ser procurada:");
                        nome = scanner.nextLine();

                        // Mesma lógica da chave parcial usada no remover.
                        Series chavePesquisa = new Series(nome, 0, "");

                        ResultadoMedicao<Series> resultadoPesquisa =
                                MedidorTempo.medirComRetorno(() -> colecao.pesquisar(chavePesquisa));

                        Series encontrada = resultadoPesquisa.getResultado();
                        System.out.println(encontrada == null
                                ? "Série não encontrada na árvore."
                                : "Série encontrada: " + encontrada);
                        MedidorTempo.imprimirTempo("Tempo de pesquisa", resultadoPesquisa.getTempoNano());
                        break;

                    case 4: // Listar em ordem
                        System.out.println("Séries em ordem alfabética:");
                        // toString() chama caminharEmOrdem() — definido em ArvoreBinariaBase
                        System.out.println(colecao);
                        System.out.println();
                        break;

                    case 5: // Quantidade de nós
                        ResultadoMedicao<Integer> resultadoQtd =
                                MedidorTempo.medirComRetorno(colecao::quantidadeNos);

                        System.out.println("Quantidade de nós: " + resultadoQtd.getResultado());
                        MedidorTempo.imprimirTempo("Tempo de quantidadeNos", resultadoQtd.getTempoNano());
                        break;

                    case 6: // Altura da árvore
                        ResultadoMedicao<Integer> resultadoAltura =
                                MedidorTempo.medirComRetorno(colecao::altura);

                        System.out.println("Altura da árvore: " + resultadoAltura.getResultado());
                        MedidorTempo.imprimirTempo("Tempo de altura", resultadoAltura.getTempoNano());
                        break;

                    case 7: // Caminhamento em nível
                        ResultadoMedicao<String> resultadoNivel =
                                MedidorTempo.medirComRetorno(colecao::caminharEmNivel);

                        System.out.println("Caminhamento em nível:");
                        System.out.println(resultadoNivel.getResultado());
                        MedidorTempo.imprimirTempo("Tempo de caminharEmNivel", resultadoNivel.getTempoNano());
                        break;

                    case 0:
                        System.out.println("Saindo do programa...");
                        break;
                }

            } while (resp != 0);

        } catch (Exception e) {
            System.out.println("ERRO! " + e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("Programa encerrado.");
    }
}