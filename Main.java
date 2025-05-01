public class Main {
    public static void main(String[] args) {
        Arvore23 arvore = new Arvore23();

        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(12);
        arvore.inserir(18);
        arvore.inserir(1);
        arvore.inserir(8);
        arvore.inserir(6);
        arvore.inserir(13);
        arvore.inserir(17);
        arvore.inserir(20);
        arvore.inserir(21);
        arvore.inserir(22);
        arvore.inserir(23);
        arvore.inserir(24);
        arvore.inserir(25);

        System.out.println("\n\nÁrvore 2-3 final:");
        arvore.desenhar();

        System.out.println("\nRemovendo 10, 18, 7, 20 e 1:\n");
        arvore.remover(10);
        arvore.remover(18);
        arvore.remover(7);
        arvore.remover(20);
        arvore.remover(1);

        arvore.desenhar();
    }
}