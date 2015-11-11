/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreTestes;

import ArvoreBinaria.ArvoreBinariaDePesquisa;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class ArvoreBinariaTestes {
    
    public ArvoreBinariaTestes() {
    }

    @Test
    public void testeSimples1() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(90);
        String expected = "50\n"
                + "40 90 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste S1: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeSimples2() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        String expected = "50 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste S2: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeSimples3() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        String expected = "50\n40 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste S3: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeSimples4() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(90);
        String expected = "50\n90 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste S4: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeSimples5() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(90);
        arvore.inserir(30);
        arvore.inserir(45);
        arvore.inserir(80);
        arvore.inserir(95);
        String expected = "50\n40 90\n30 45 80 95 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste S5: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeDificil1() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(35);
        arvore.inserir(30);
        arvore.inserir(45);
        arvore.inserir(10);
        arvore.inserir(15);
        String expected = "50\n40\n35\n45\n30\n10\n15 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste D1: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeDificil2() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(400);
        arvore.inserir(350);
        arvore.inserir(300);
        arvore.inserir(450);
        arvore.inserir(100);
        arvore.inserir(150);
        String expected = "50\n400\n350\n450\n300\n100\n150 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste D2: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    @Test
    public void testeDificil3() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(35);
        arvore.inserir(30);
        arvore.inserir(45);
        arvore.inserir(10);
        arvore.inserir(15);
        arvore.inserir(400);
        arvore.inserir(350);
        arvore.inserir(300);
        arvore.inserir(450);
        arvore.inserir(100);
        arvore.inserir(150);
        String expected = "50\n40 400\n35\n45\n350\n450\n30 300\n10 100\n15 150 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste D3: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeDificil4() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(35);
        arvore.inserir(350);
        arvore.inserir(30);
        arvore.inserir(45);
        arvore.inserir(450);
        arvore.inserir(10);
        String expected = "50\n40\n350\n35\n45 450\n30\n10 ";
        String result = arvore.printPorNivel();
        assertEquals(expected,result);
        System.out.println("Teste D4: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
    
    @Test
    public void testeDificil5() {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();
        arvore.inserir(50);
        arvore.inserir(1050);
        arvore.inserir(900);
        arvore.inserir(40);
        arvore.inserir(35);
        arvore.inserir(350);
        arvore.inserir(30);
        arvore.inserir(45);
        arvore.inserir(450);
        arvore.inserir(10);
        String expected = "50\n40 1050\n35\n45\n900\n30 350\n10 450 ";
        String result = arvore.printPorNivel();
        System.out.println("Teste D5: Resultado: \n" + result + "\nResultado esperado: \n" + expected);
    }
}
