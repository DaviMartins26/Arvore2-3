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

        arvore.desenhar();

        System.out.println("\nRemovendo...\n");
        arvore.remover(15);/* 
        arvore.remover(18);
        arvore.remover(7);
        arvore.remover(20);
        arvore.remover(1);
 */
        arvore.desenhar();
    }
}