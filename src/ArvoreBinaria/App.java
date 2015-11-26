package ArvoreBinaria;
//
import java.util.ArrayList;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        ArvoreBinariaDePesquisa abp = new ArvoreBinariaDePesquisa();
        abp.inserir(50);
        abp.inserir(60);
        abp.inserir(40);
        abp.inserir(65);
        abp.inserir(55);
        abp.inserir(45);
        abp.inserir(35);
        abp.inserir(15);
        abp.inserir(37);
        abp.inserir(42);
        abp.inserir(17);
        abp.inserir(47);
        abp.inserir(52);
        abp.inserir(57);
        abp.inserir(63);
        abp.inserir(67);
        abp.inserir(66);
        abp.inserir(64);
        System.out.println(abp.printTree());
//        complexidade();
        
    }
    
    private static void complexidade(){
        //Cria numeros aleatorios para o vetor.
        Random r = new Random(System.currentTimeMillis());
        ArrayList<Integer> valores = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            int numero = r.nextInt(900);
            if (!valores.contains(numero)) {
                valores.add(numero);
            } else {
                i--;
            }
        }
        ArvoreBinariaDePesquisa abp = new ArvoreBinariaDePesquisa();
        //Insere os valores do vetor na arvore
        for (int i = 0; i < 500; i++) {

            abp.inserir(valores.get(i));

            System.out.println("Operações: " + i +" de 499");
            abp.printTree();
        }
        new Complexidade().criarTabela();
    }
}
