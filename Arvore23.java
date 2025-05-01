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

    public void inserir(int elemento){ // metodo que é chamado pelo Main
        No inserido = inserirNO(raiz,elemento);
        if (inserido != null){ // se a promocao vou subindo ate a raiz precisa fazer uma nova raiz com esse if
            No novaRaiz = new No(inserido.elemento1);
            novaRaiz.noDuplo = false;
            novaRaiz.esquerda = inserido.esquerda;
            novaRaiz.direita = inserido.direita;
            raiz = novaRaiz;
        }
    }

    private No inserirNO(No atual,int elemento){ // parte responsavel por colocar a No na arvore
        if (atual == null){ // caso não haja nenhum No um Novo é inserido
            return new No(elemento);
        }
        if (atual.esquerda == null){ // caso o NO atual não aponte nada pra esquerda
            if (!atual.noDuplo){ // e nâo seje um No duplo
                if(elemento < atual.elemento1){ // comprara se o elemento informado é menor que o elemento1 presente no No
                    atual.elemento2 = atual.elemento1; // faz o elemento2 do no ser o elemento1
                    atual.elemento1 = elemento; // e o elemento informado passa a ser o elemento1
                } else { // se elemento informado maior que elemento1 do No
                    atual.elemento2 =  elemento; //elemento informado passa a ser elemento2 do No
                }
                atual.noDuplo = true; // agora o No é um No duplo
                return null;
            } else { // caso seja um No Duplo
                return dividirFolha(atual,elemento); // chama o metodo que tenta balancear a arvore dividindo as folhas
            }
        }

        No promovido = null; // cria uma referencia de no para promover
        if (elemento < atual.elemento1){ // se elemento informado for menor que elemento1 do No
            promovido = inserirNO(atual.esquerda, elemento); // para um insert com No com o valor atual da esquerda e o da direira é o elemento inserido
            // esses promovido != null é pra garantir que num valor null seja passadado no return, oq estava acontecendo
            if (promovido != null) return tratarPromocao(atual,promovido, true); // chama o metodo que trata o balanceamento
        } else if (!atual.noDuplo || elemento < atual.elemento2) { // Se não for noDuplo ou elemento inserido for menor que o elemento2
            promovido = inserirNO(atual.meio, elemento); // pepara um insert com NO 
            if (promovido != null) return tratarPromocao(atual,promovido,false); // chama o metodo que vai tratar o balanceamento
        } else { // ultimo caso
            promovido = inserirNO(atual.direita, elemento);
            if (promovido !=null) return tratarPromocao(atual,promovido,false); // chama o metodo que tenta balancear a arvore
        }
        return null; // quando não ha mais caminho a percorrer, acredito eu
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------Tratamentos---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    // quando for necessario fazer 3 folhas
    private No dividirFolha(No atual, int elemento){
        int menor, meio, maior; // variaveis temporarias pra manipulação
        if (elemento < atual.elemento1){ // se o valor informado for menor que o elemento1
            menor = elemento; // menor valor é o insert
            meio = atual.elemento1; // o valor do meio é o elemento1
            maior = atual.elemento2; // maior é o elemento2
        }  else if (elemento < atual.elemento2){ // se o valor informado for menor que o elemento2
            menor = atual.elemento1; // o elemento1 é o menor
            meio = elemento; // meio é o insert
            maior = atual.elemento2; // maior é o elemento2
        } else { // se insert for maior que os 2
            menor = atual.elemento1;
            meio = atual.elemento2;
            maior = elemento;
        }
        // cria um No pra cada nova folha
        No esquerda = new No(menor); // esquerda sempre é o Menor
        No direita = new No(maior); // direita é sempre o Maior
        No promovido = new No(meio); // o do meio 
        // O no pronmovido do meio aponta para os dois novos filhos menor e maior
        promovido.esquerda = esquerda;
        promovido.direita = direita;
        return promovido; 
    }

    // tentativa de balancear a arvore quando precisa subir um NO
    private No tratarPromocao(No atual,No promovido, boolean isEsquerda){
        if (!atual.noDuplo){ // se o No for simples
            if(isEsquerda) { // se o valor veio da esquerda
                atual.elemento2 = atual.elemento1; // o elemento 2 se torna o 1
                atual.elemento1 = promovido.elemento1; // elemento1 do no atual vira o elemento1 do no que vai ser promovido
                atual.direita = atual.meio; // o elemento da direita vira igual ao valor do meio agora
                atual.meio = promovido.direita; // o meio do atual aponta para o filho direito do promovido
                atual.esquerda = promovido.esquerda; // o valor da esquerda se torna o valor da esquerda do promovido    
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

        No novoPromovido = new No(meio);
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

    private No removerNo(No no,int elemento){ // metodo que faz a remocao
        if (no == null) return null; // verifica se o no não é null,importante

        if(no.esquerda == null){ // se a esquerda não apontar pra nada
            if(no.noDuplo){ // e se for noDuplo
                if(elemento == no.elemento1){ // e elemento pra remover for igual o elemento do No
                    no.elemento1 = no.elemento2; // o elemento1 do No fica igual ao elemento2
                    no.noDuplo = false; // deixa de ser No duplo
                } else if (elemento == no.elemento2){ // se o elemento a remover for igual o elemento2
                    no.noDuplo = false;// deixa de ser No duplo
                }
            } else if (elemento == no.elemento1){ 
                return null;
            }
            return null;
            }
        // se elelmento pra ser removido for igual ao elemento1 do no ou o No é duplo e o elemento a ser removido é igual ao elemento2 do NO
        if (elemento == no.elemento1 || (no.noDuplo && elemento == no.elemento2)){
            if (elemento == no.elemento1){ // se o elemento pra remover for igual ao elemento1 do no
                No sucessor = encontrarMenor(no.meio); // procuira o menor passando o valor do meio do no como paramentro
                if (sucessor != null){ // garantindo um erro
                    no.elemento1 = sucessor.elemento1; // // o elemento1 do NO fica igual ao elemento1 do sucessor
                    no.meio = removerNo(no.meio, sucessor.elemento1); // chama o proprio metodo pra remover o elemento
                }
            } else { // se elemento pra remover não for igual ao elemenmto1 do no
                No sucessor = encontrarMenor(no.direita); // procuira o menor passando o valor direito do no como paramentro
                if (sucessor != null){
                    no.elemento2 = sucessor.elemento1;
                    no.direita = removerNo(no.direita, sucessor.elemento1);
                }
            }
            return no; // retorna o No criado
        }
        // se o elemento pra remover for menor que o elemento1 do No
        if (elemento < no.elemento1){
            no.esquerda = removerNo(no.esquerda, elemento); // chama novamente a função
        } else if(!no.noDuplo || elemento < no.elemento2){
            no.meio = removerNo(no.meio, elemento);
        } else {
            no.direita = removerNo(no.direita, elemento);
        }
        return no;// retorna o no criado
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

        String texto = no.noDuplo ? no.elemento1 + ","+ no.elemento2: String.valueOf(no.elemento1); // tem que ver se isso aqui pode
        imprimirEspacos(nivel,texto); // tentando imprimir aquele espaço que da efeito de arvore

        if(no.esquerda != null) imprimir(no.esquerda, nivel+1); // se aponta pra null na esquerda
        if(no.noDuplo && no.meio != null) imprimir(no.meio, nivel+1); // se o no for duplo e no.meio for null
        if(no.noDuplo && no.direita != null) imprimir(no.direita, nivel+1); // se o No não for duplo e nem o elemento da direita for
    }

    private void imprimirEspacos(int nivel,String texto){
        for (int i = 0; i < nivel; i++) System.out.print("    ");
        System.out.println(texto);
    }


}

