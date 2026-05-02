package br.ifes.tpa.listaencadeada;

public class No<T> { // O <T> é o Generics, permitindo o nó carregar qualquer tipo de dado (string, numero...)

    private T valor; // Conteudo generico salvo
    private No<T> proximo; // Liga ao proximo nó

    public No(T valor) { // CONSTRUTOR DA CLASSE
        this.valor = valor;
        this.proximo = null; // o nó acaba de ser criado, então o prox é vazio
    }

    public T getValor() { // devolve o objeto do tipo T presente no nó
        return valor;
    }

    public void setValor(T valor) { // muda o conteudo y do nó atual para um conteúdo x
        this.valor = valor;
    }

    public No<T> getProximo() { // descobre o proximo da lista
        return proximo;
    }

    public void setProximo(No<T> proximo) { // liga um nó ao outro
        this.proximo = proximo;
    }

}