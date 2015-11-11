package ArvoreBinaria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
public class Complexidade {

    private static ArrayList<Integer> complexidadePorExecucao = new ArrayList<>();

    public static void adicionarValor(int z) {
        complexidadePorExecucao.add(z);
    }

    public void criarTabela()  {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Complexidade.csv"))) {
                for (Integer c : complexidadePorExecucao) {
                    bw.append(Integer.toString(c));
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Complexidade.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
