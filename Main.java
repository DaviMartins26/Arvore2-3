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

        System.out.println("\nRemovendo.5\n");
        arvore.remover(5);
        arvore.desenhar();
        System.out.println("\nRemovendo.8\n");
        arvore.remover(8);
        arvore.desenhar();

        System.out.println("Buscar 15: " + arvore.buscar(15)); // Retonra se o elemento existe em boolean
        System.out.println("Buscar 255: " + arvore.buscar(255));
    }
}