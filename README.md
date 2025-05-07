# Projeto: Árvore 2-3 em Java

Este projeto implementa uma Árvore 2-3 em Java. O código realiza inserção, remoção, busca e impressão da árvore.

## 📌 Regras do Projeto

- Proibido usar arrays, listas, `compareTo()` e outras funções utilitárias.
- Permitido apenas: `String`, tipos primitivos (`int`, `float`), `try-catch` e métodos de input/output.

## 🧠 Estrutura da Árvore 2-3

A árvore é composta por nós que podem conter:
- 1 elemento (`elemento1`) → nó 2
- 2 elementos (`elemento1` e `elemento2`) → nó 3

Cada nó pode ter até 3 filhos:
- `esquerda`: valores menores que `elemento1`
- `meio`: valores entre `elemento1` e `elemento2`
- `direita`: valores maiores que `elemento2` (em nós com dois elementos)

## ⚙️ Funcionalidades

### Inserção
Insere um valor na árvore chamando o método `inserir(String valor)`. O valor percorre os nós seguindo as regras de comparação:
- Se o nó for folha e tiver espaço, o valor é inserido em ordem.
- Se o nó estiver cheio (dois elementos), ele é dividido e o elemento do meio sobe para o nível anterior.
- A inserção é recursiva e pode causar divisões em cadeia até a raiz.

### Remoção
Remove um valor chamando `remover(String valor)`. A lógica segue:
- Se o valor estiver em uma folha, é removido diretamente.
- Se estiver em um nó interno, é substituído pelo sucessor imediato (menor valor da subárvore direita).
- Após a remoção, verifica se o nó ficou com menos de um valor. Se sim, realiza fusões ou redistribuições com irmãos.

### Busca
Realiza uma busca chamando `buscar(String valor)`:
- Compara o valor com os elementos do nó atual.
- Se for igual a `elemento1` ou `elemento2`, retorna verdadeiro.
- Caso contrário, segue para a subárvore esquerda, meio ou direita, dependendo do valor.
- Caso percorra toda a árvore e o elemento não seja encontrado, retorna falso.

### Impressão
O método `imprimir()` imprime a árvore com indentação por nível:
- Visita os nós em ordem, exibindo os elementos.
- A indentação aumenta conforme a profundidade, facilitando a visualização da estrutura hierárquica da árvore.

### Estrutura das Classes

- `No`: representa um nó da árvore, contendo até dois elementos e três ponteiros para filhos.
- `Arvore23`: classe principal com a raiz e os métodos de inserção, remoção, busca e impressão.
- `Main`: classe que controla a execução dos métodos e já possui uma lógica implementada para demonstrar todas as funcionalidades.

###  Como Executar

- Execute a classe `Main`. Nela já existe uma lógica programada que insere, remove, busca e imprime elementos da árvore, permitindo testar o comportamento da estrutura.
