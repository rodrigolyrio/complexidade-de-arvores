package br.ifes.tpa.listaencadeada;

import br.ifes.tpa.biblioteca.IColecao;

import java.util.ArrayList;

public class ListaEncadeadaArrayList<T> implements IColecao<T> {

    private ArrayList<T> lista;

    public ListaEncadeadaArrayList() {
        this.lista = new ArrayList<>();
    }

    @Override
    public void adicionar(T novoValor) {
        this.lista.add(0, novoValor);
    }

    @Override
    public T pesquisar(T valor) {
        int indice = this.lista.indexOf(valor);

        if (indice != -1) {
            return this.lista.get(indice);
        }

        return null; // 'valor' não foi encontrado.
    }

    @Override
    public boolean remover(T valor) {
        return this.lista.remove(valor);
    }

    @Override
    public int quantidadeNos() {
        return this.lista.size();
    }

    public String toString () {
        return this.lista.toString();
    }
}