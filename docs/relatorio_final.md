Primeira seção do relatório: relato sobre o desenvolvimento da biblioteca e do programa de teste

Ronnald Willian:

- Atuação desenvolvendo a parte 01:
  - Criado o arquivo NoArvore.java: Nele foi desenvolvido o campo *altura* no *NoArvore.java*, pois na *ArvoreAVL* será necessário saber a altura de cada nó individualmente. Para calcular o fator de balanceamento e decidir quando e como rotacionar. Se esse campo não existir no nó, a AVL teria que calcular a altura recursivamente toda vez, o que degradaria o desempenho ou forçaria uma refatoração da classe.
  - O construtor de ArvoreBinaria, "Comparator": O comparador acessível em todos os métodos de ArvoreBinaria sem precisar ser declarado novamente. É ele que determina como dois objetos são comparados em toda operação da árvore.
    - Ainda dentro de ArvoreBinaria:
      - Método adicionar
      - Método pesquisar
      - Método remover
      - Método altura
      - Método caminharEmNível
      - Método caminharEmOrdem