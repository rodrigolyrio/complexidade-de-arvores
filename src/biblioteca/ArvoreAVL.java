package br.ifes.tpa.biblioteca;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {


    /**
    ArvoreAVL: Construtor.
    **/
    public ArvoreAVL(Comparator<T> comparador) { 
        super(comparador); // Define qual elemento é maior ou menor, identificando onde o elemento deve ser inserido na árvore
    }

    /**
    getAltura: Identifica a altura do nó.
    **/ 
    private int getAltura(NoArvore<T> no) {
        if (no == null) { // Verifica se o nó existe
            return -1; 
        } else {
            return no.altura;
        }
    }

    /**
    getFatorialBalanceamento: Verifica a necessidade de se balancear a árvore
    **/
    private int getFatorBalanceamento(NoArvore<T> no) {
        if (no == null) { // Não há desequilibrio, pois não existe nó.
            return 0; 
        } else {
            return getAltura(no.esquerda) - getAltura(no.direita); // Se retornar 0, está em equilibrio.
        }
    }

    /**
    atualizarAltura: Identifica a altura do maior nó filho e incremente +1, atualizando a altura do próprio nó.
    **/
    private void atualizarAltura(NoArvore<T> no) {
        int alturaEsquerda = getAltura(no.esquerda);
        int alturaDireita = getAltura(no.direita);
        no.altura = 1 + Math.max(alturaEsquerda, alturaDireita); 
    }


    /**
    rotacaoDireita: Redistribui o peso quando o lado esquerdo da árvore fica mais pesado.
    **/
    private NoArvore<T> rotacaoDireita(NoArvore<T> y) {
        NoArvore<T> x = y.esquerda; // x é nó filho esquerdo de y.
        NoArvore<T> T2 = x.direita; // T2 é nó filho direito de x.

        // ROTACIONA PARA A DIREITA...
        x.direita = y; 
        y.esquerda = T2; 
        // Ao realizar essa rotação, x se torna nó pai do nó y e do nó T2.

        atualizarAltura(y);
        atualizarAltura(x);

        return x; // Retorna a nova raiz (ou o novo nó pai)
    }


    /**
    rotacaoEsquerda: Utiliza a mesma lógica do método acima, porém move-se para a esquerda.
    **/
    private NoArvore<T> rotacaoEsquerda(NoArvore<T> x) {
        NoArvore<T> y = x.direita;
        NoArvore<T> T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    /**
    adicionar: 
    **/
    
    public void adicionar(T novoValor) {
        int[] inserido = {0};
        this.raiz = adicionarRecursivo(this.raiz, novoValor, inserido);
        this.totalNos += inserido[0];
    }

    private NoArvore<T> adicionarRecursivo(NoArvore<T> no, T valor, int[] inserido) {

        if (no == null) {
            inserido[0] = 1;
            return new NoArvore<>(valor);
        }

        int cmp = comparador.compare(valor, no.valor);

        if (cmp < 0) {
            no.esquerda = adicionarRecursivo(no.esquerda, valor, inserido);
        } else if (cmp > 0) {
            no.direita = adicionarRecursivo(no.direita, valor, inserido);
        } else {
            return no;
        }

        atualizarAltura(no);
        int balanceamento = getFatorBalanceamento(no);

        if (balanceamento > 1 && comparador.compare(valor, no.esquerda.valor) < 0) {
            return rotacaoDireita(no);
        }

        if (balanceamento > 1 && comparador.compare(valor, no.esquerda.valor) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && comparador.compare(valor, no.direita.valor) > 0) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento < -1 && comparador.compare(valor, no.direita.valor) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoArvore<T> getNoMinimo(NoArvore<T> no) {
        NoArvore<T> atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public boolean remover(T valor) {
        int[] removido = {0};
        this.raiz = removerRecursivo(this.raiz, valor, removido);
        this.totalNos -= removido[0];
        return removido[0] == 1;
    }

    private NoArvore<T> removerRecursivo(NoArvore<T> no, T valor, int[] removido) {
        if (no == null) {
            return null;
        }

        int cmp = comparador.compare(valor, no.valor);

        if (cmp < 0) {
            no.esquerda = removerRecursivo(no.esquerda, valor, removido);
        } else if (cmp > 0) {
            no.direita = removerRecursivo(no.direita, valor, removido);
        } else {
            removido[0] = 1;

            if (no.esquerda == null || no.direita == null) {
                NoArvore<T> temp = (no.esquerda != null) ? no.esquerda : no.direita;

                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoArvore<T> temp = getNoMinimo(no.direita);
                no.valor = temp.valor;
                no.direita = removerRecursivo(no.direita, temp.valor, removido);
            }
        }

        if (no == null) {
            return no;
        }

        atualizarAltura(no);
        int balanceamento = getFatorBalanceamento(no);

        if (balanceamento > 1) {
            if (getFatorBalanceamento(no.esquerda) >= 0) {
                return rotacaoDireita(no);
            } else {
                no.esquerda = rotacaoEsquerda(no.esquerda);
                return rotacaoDireita(no);
            }
        }

        if (balanceamento < -1) {
            if (getFatorBalanceamento(no.direita) <= 0) {
                return rotacaoEsquerda(no);
            } else {
                no.direita = rotacaoDireita(no.direita);
                return rotacaoEsquerda(no);
            }
        }

        return no;
    }
}
