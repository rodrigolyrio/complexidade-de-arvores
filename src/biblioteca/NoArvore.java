package br.ifes.tpa.biblioteca;

/**
 * Representa um nó de uma Árvore Binária genérica.
 * Armazena um valor e referências para os filhos esquerdo e direito.
 */
class NoArvore<T> {
    T valor;
    NoArvore<T> esquerda;
    NoArvore<T> direita;

    /** Altura do nó: Utilizada somente pela ArvoreAVL para balanceamento. */
    int altura;

    public NoArvore(T valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 0;
    }
}