package br.ifes.tpa.listaencadeada;

import br.ifes.tpa.biblioteca.IColecao;
import br.ifes.tpa.listaencadeada.No;

public class ListaEncadeada<T> implements IColecao<T> {

    private No<T> inicio;
    private int totalNos;
    // No fim, totalNos vai permitir com que a operacao de percorrer a lista encadeada migre de O(n) para O(1).

    public ListaEncadeada() { // CONSTRUTOR DA CLASSE
        this.inicio = null;
        this.totalNos = 0;
        // A especificacao pede para que a lista comece vazia.
    }

    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);
        novoNo.setProximo(this.inicio); // Nó atual conecta com o nó anterior
        this.inicio = novoNo; // Atualiza o nó inicial = novo nó inserido
        this.totalNos++; // Atualiza a quantidade de nós da lista encadeada
    }

    @Override
    public T pesquisar(T valor) {
        No<T> atual = this.inicio;

        while (atual != null) {
            if (atual.getValor().equals(valor)) { // Vamos lidar com objetos (como Alunos ou Strings, etc) por isso usamos .equals() ao invés de ==
                return atual.getValor(); // Retorna o obj encontrado
            }
            atual = atual.getProximo();
        }

        return null; // Se chegou aqui, obj ta fora do loop
    }

    @Override
    public boolean remover(T valor) {
        if (this.inicio == null)
            return false; // Lista encadeada vazia, não tem o que remover

        // CASE 1: O VALOR A SER REMOVIDO ESTÁ NO PRIMEIRO NÓ DA LISTA

        if (this.inicio.getValor().equals(valor)) {
            this.inicio = this.inicio.getProximo();
            this.totalNos--;
            return true;
        }

        // CASE 2: O VALOR A SER REMOVIDO ESTÁ NO MEIO OU FIM DA LISTA

        No<T> anterior = this.inicio;
        No<T> atual = this.inicio.getProximo();

        while (atual != null) {
            if (atual.getValor().equals(valor)) {
                anterior.setProximo(atual.getProximo()); // O nó anterior aponta para o próximo do nó atual, já que o atual será removido
                this.totalNos--;
                return true;
            }
            // Continua percorrendo a lista até encontrar o obj procurado...
            anterior = atual;
            atual = atual.getProximo();
        }

        return false;
    }

    @Override
    public int quantidadeNos() {
        return totalNos; // para O(n) -> O(1)
    }

    @Override
    public String toString() { // Possibilita traduzir o endereço de memória para texto
        if (this.inicio == null) {
            return "A Lista está vazia";
        }

        StringBuilder builder = new StringBuilder(); // Classe do java que monta textos longos (evitar lentidão da concatenação de strings)
        No<T> atual = this.inicio;

        while (atual != null) {
            builder.append(atual.getValor());

            if (atual.getProximo() != null) {
                builder.append(", ");
            }

            atual = atual.getProximo();
        }

        return builder.toString();
    }
}