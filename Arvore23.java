public class Arvore23 {


    class No { // cria o No
        private int elemento1; 
        private int elemento2;
        private boolean noDuplo; // pra saber se o No é duplo
        private No esquerda, meio, direita; // Nos que apontam pra cada possivel NO

        No(int elemento){ // Construtor do No que precisa receber um valor
            this.elemento1 = elemento; // 
            this.noDuplo = false; // inicialmente é criado como No simples
            this.esquerda = null; // Inicialmente não precisa apontar pra nenhum outro NO
            this.meio = null;
            this.direita = null;
        }
    }

     /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------INSERT---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    private No raiz; // cria uma  referencia apenas

    public void inserir(int elemento) { // metodo chamado no Main
        No inserido = inserirNO(raiz, elemento); 
        if (inserido != null) { // garante que não passe um valor null
            raiz = inserido;
        }
    }
    
    private No inserirNO(No atual, int elemento) {
        if (atual == null) { // se não ouver No cria um
            return new No(elemento);
        }
    
        // Caso seja uma folha
        if (atual.esquerda == null) {
            if (!atual.noDuplo) {
                // Insere o novo elemento na posição correta dentro do nó
                if (elemento < atual.elemento1) {
                    atual.elemento2 = atual.elemento1;
                    atual.elemento1 = elemento;
                } else {
                    atual.elemento2 = elemento;
                }
                atual.noDuplo = true;
                return null;
            } else {
                // chama o metodo que divide um no de 3 elementos caso o No acabe ficando com 3 elementos
                return dividir3Valores(atual.elemento1, atual.elemento2, elemento);
            }
        }
    
        // Caso não seja folha — segue para o filho correto
        No promovido = null;
        if (elemento < atual.elemento1) {
            promovido = inserirNO(atual.esquerda, elemento);
            if (promovido != null) return tratarPromocao(atual, promovido, true);
        } else if (!atual.noDuplo || elemento < atual.elemento2) {
            promovido = inserirNO(atual.meio, elemento);
            if (promovido != null) return tratarPromocao(atual, promovido, false);
        } else {
            promovido = inserirNO(atual.direita, elemento);
            if (promovido != null) return tratarPromocao(atual, promovido, false);
        }
    
        return null;
    }
    

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------Tratamentos---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    // dividir No com 3 valores
    private No dividir3Valores(int elemento1, int elemento2, int elementoInserido) {
        int menor, meio, maior;
    
        // verifica se elemento1 é o menor entre os três
        if (elemento1 < elemento2 && elemento1 < elementoInserido) {
            menor = elemento1; // seta menor como elemento1
            // compara os outros dois e seta qual é o meio e maior
            if (elemento2 < elementoInserido) {
                meio = elemento2;
                maior = elementoInserido;
            } else {
                meio = elementoInserido;
                maior = elemento2;
            }
    
        // cverifica se valor2 é o menor entre os três
        } else if (elemento2 < elemento1 && elemento2 < elementoInserido) {
            menor = elemento2;
    
            if (elemento1 < elementoInserido) {
                meio = elemento1;
                maior = elementoInserido;
            } else {
                meio = elementoInserido;
                maior = elemento1;
            }
    

        // se nenhum é o menor então o menor é o elementoInserido
        } else {
            menor = elementoInserido;
    
            if (elemento1 < elemento2) {
                meio = elemento1;
                maior = elemento2;
            } else {
                meio = elemento2;
                maior = elemento1;
            }
        }
    
        // cria os dois nós com os valores menor e maior
        No esquerda = new No(menor);
        No direita = new No(maior);
    
        // cria o NO promovido com o valor do meio e liga os filhos
        No promovido = new No(meio);
        promovido.esquerda = esquerda;
        promovido.direita = direita;
    
        return promovido;
    }
    
    

    // tentativa de balancear a arvore quando precisa subir um NO
    private No tratarPromocao(No atual,No promovido, boolean isEsquerda){
        if (!atual.noDuplo){ // se o No for simples
            if(isEsquerda) { // se o valor veio da esquerda
                No antigoDireita = atual.direita; // salva o valor da direita
                atual.elemento2 = atual.elemento1; // o elemento 2 se torna o 1
                atual.elemento1 = promovido.elemento1; // elemento1 do no atual vira o elemento1 do no que vai ser promovido
                atual.direita = atual.meio; // o elemento da direita vira igual ao valor do meio agora
                atual.meio = promovido.direita; // o meio do atual aponta para o filho direito do promovido
                atual.esquerda = promovido.esquerda; // o valor da esquerda se torna o valor da esquerda do promovido 
                if (atual.direita == null){ // faz a restauração da direita caso haja sobrescrição nula
                    atual.direita = antigoDireita;
                }   
            } else {
                atual.elemento2 = promovido.elemento1; // o valor atual vira o valor do elemento1 do promovido
                atual.meio= promovido.esquerda; // o valor do meio vira
                atual.direita = promovido.direita; // o valor da direita vira o valor da direita do promovido
            }
            atual.noDuplo = true; // torna o No atual Duplo
            return null;
        } else {
            return dividirNoComum(atual, promovido, isEsquerda); // chama o metodo que tenta balancear a arvore quando é necessario fazer uma divisão
        }
    }

    // tentativa de balancear o No quando é necessario dividir um No em outras partes
    private No dividirNoComum(No atual,No promovido, boolean isEsquerda){
        int menor,meio,maior; // variaveis temporareas pra fazer o manuseio dos elementos
        No filhoEsquerdo,filhoMeio,filhoDireito,filhoExtra; // No's para ser criados na divisão

        if(isEsquerda){ // se o valor dor da Esquerda
            menor = promovido.elemento1;
            meio = atual.elemento1;
            maior = atual.elemento2;
            filhoEsquerdo = promovido.esquerda;
            filhoMeio = promovido.direita;
            filhoDireito = atual.meio;
            filhoExtra = atual.direita;
        } else{ // se não veio da esquerda
            if (promovido.elemento1 < atual.elemento2){ // se elemento1 do promovido for maior que o elemento2 do atual
                menor = atual.elemento1;
                meio = promovido.elemento1;
                maior = atual.elemento2;
                filhoEsquerdo = atual.esquerda;
                filhoMeio = promovido.esquerda;
                filhoDireito = promovido.direita;
                filhoExtra = atual.direita;
            } else {
                menor = atual.elemento1;
                meio = atual.elemento2;
                maior = promovido.elemento1;
                filhoEsquerdo = atual.esquerda;
                filhoMeio = atual.meio;
                filhoDireito = promovido.esquerda;
                filhoExtra = promovido.direita;
            }
        }

        No esquerda = new No(menor); // cria um novo No pro valor menor
        esquerda.esquerda = filhoEsquerdo; // aponta pra esquerda
        esquerda.meio = filhoMeio; //

        No direita = new No(maior); // cria um novo No pro Valor Maior
        direita.esquerda = filhoDireito;
        direita.meio = filhoExtra;

        No novoPromovido = new No(meio); // cria um No e e aponta esse po pros outros criados
        novoPromovido.esquerda = esquerda;
        novoPromovido.direita = direita;

        return novoPromovido;

    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------REMOVER---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    public void remover(int elemento){ // metodo chamado pelo Main
        raiz = removerNo(raiz,elemento);
    }

    private No removerNo(No no, int elemento) { // metodo que remove verdadeiramente
        if (no == null) return null;
    
        // Caso base: nó folha
        if (no.esquerda == null) { // se aponta nada pra esquerda
            if (no.noDuplo) { // e se for um no duplo
                if (elemento == no.elemento1) {
                    no.elemento1 = no.elemento2;
                    no.noDuplo = false;
                    return no;
                } else if (elemento == no.elemento2) {
                    no.noDuplo = false;
                    return no;
                }
            } else if (elemento == no.elemento1) { 
                return null; // remove nó simples
            }
            return no; // valor não estava nesse nó
        }
    
        // procura qual elemento do NO é correspondente ao elemento informado pra remover
        if (elemento == no.elemento1 || elemento == no.elemento2) { 
            if (elemento == no.elemento1) {
                No sucessor = encontrarMenor(no.meio);
                if (sucessor != null) {
                    no.elemento1 = sucessor.elemento1;
                    no.meio = removerNo(no.meio, sucessor.elemento1);
                }
            } else { // se elemento pra remover for igual ao elemento2 do no
                No sucessor = encontrarMenor(no.direita);
                if (sucessor != null) {
                    no.elemento2 = sucessor.elemento1;
                    no.direita = removerNo(no.direita, sucessor.elemento1);
                }
            }
            return no;
        }
    
        // desce a arvore até o elemento
        if (elemento < no.elemento1) {
            no.esquerda = removerNo(no.esquerda, elemento);
        } else if ( elemento < no.elemento2) {
            no.meio = removerNo(no.meio, elemento);
        } else {
            no.direita = removerNo(no.direita, elemento);
        }
    
        return no;
    }
    

    // metodo pra procurar o menor valor
    private No encontrarMenor(No no){
        if (no == null) return null;
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------DESENHO---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    public void desenhar(){ // metodo que é chamado no Main
        imprimir(raiz,0);
    }


    private void imprimir(No no, int nivel){
        if (no == null) return; // garantindo que n tente imprimir algo que n existe

        if(no.direita != null) imprimir(no.direita, nivel+1); // se o No não for duplo e nem o elemento da direita for
        if(no.meio != null) imprimir(no.meio, nivel+1); // se o no for duplo e no.meio for null

        String texto = no.noDuplo ? no.elemento1 + ","+ no.elemento2: String.valueOf(no.elemento1); // tem que ver se isso aqui pode
        imprimirEspacos(nivel,texto); // tentando imprimir aquele espaço que da efeito de arvore

        if(no.esquerda != null) imprimir(no.esquerda, nivel+1); // se aponta pra null na esquerda

    }

    private void imprimirEspacos(int nivel,String texto){
        for (int i = 0; i < nivel; i++) System.out.print("    ");
        System.out.println(texto);
    }


}

