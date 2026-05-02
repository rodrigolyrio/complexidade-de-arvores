package br.ifes.tpa.listaencadeada;

import br.ifes.tpa.biblioteca.IColecao;

import java.util.LinkedList; // Importamos a classe já presente no Java de LinkedList.

public class ListaEncadeadaLinkedList<T> implements IColecao<T> {

    private LinkedList<T> listaInterna;

    public ListaEncadeadaLinkedList() { // CONSTRUTOR
        this.listaInterna = new LinkedList<>();
    }

    @Override
    public void adicionar(T novoValor) {
        this.listaInterna.addFirst(novoValor); // Adiciona o 1º valor no Linked List
    }

    @Override
    public int quantidadeNos() {
        return this.listaInterna.size(); // Retorna o tamanho do Linked List
    }

    @Override
    public T pesquisar(T valor) {
        int indice = this.listaInterna.indexOf(valor); // Procura a posição do 'valor' no Linked List

        if (indice != -1) {
            return this.listaInterna.get(indice); // Achou a posição
        }

        return null; // Se o indice for -1, não encontrou a posição do 'valor'.
    }

    @Override
    public boolean remover(T valor) {
        return this.listaInterna.remove(valor); // Remove valor na LinkedList
    }

    @Override
    public String toString() {
        if (this.listaInterna.isEmpty()) {
            return "A lista está vazia";
        }

        StringBuilder builder = new StringBuilder(); // Permite montar textos, como inserimos em ListaEncadeada.java

        for (int i = 0; i < this.listaInterna.size(); i++) {
            builder.append(this.listaInterna.get(i)); // Insere cada valor no builder

            if (i < this.listaInterna.size() - 1) {
                builder.append(", "); // Permite inserir a virgula apenas entre elementos, não no final do texto pronto
            }
        }

        return builder.toString();
    }
}