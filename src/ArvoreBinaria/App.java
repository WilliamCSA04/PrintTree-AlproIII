package ArvoreBinaria;

import java.util.ArrayList;
import java.util.Random;

public class App {

    public static void main(String[] args) {

        //Cria numeros aleatorios para o vetor.
        Random r = new Random(System.currentTimeMillis());
        ArrayList<Integer> valores = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int numero = r.nextInt(100000);
            if (!valores.contains(numero)) {
                valores.add(numero);
            } else {
                i--;
            }
        }
        ArvoreBinariaDePesquisa abp = new ArvoreBinariaDePesquisa();
        //Insere os valores do vetor na arvore
        for (int i = 0; i < 10000; i++) {

            abp.inserir(valores.get(i));

            System.out.println("Operações: " + i +" de 9999");
            abp.printPorNivel();
        }
        new Complexidade().criarTabela();
    }
}
