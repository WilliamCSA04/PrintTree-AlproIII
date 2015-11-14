package ArvoreBinaria;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @see #printPorNivel()
 *
 */
public class ArvoreBinariaDePesquisa {

    private class Nodo {

        public int chave;

        public int altura;

        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(int chave) {
            this.chave = chave;
            altura = 0;

            esquerdo = direito = null;
        }
    }
    private Nodo raiz;

    /**
     * Insere uma nova chave na árvore.
     *
     * Em caso de duplicatas, uma exceção é gerada.
     *
     * @param chave o valor da nova chave
     */
    public void inserir(int chave) {
        raiz = inserir0(raiz, chave);
    }

    /**
     *
     * @param nodo
     * @param chave
     * @return
     */
    private Nodo inserir0(Nodo nodo, int chave) {
        if (nodo == null) {
            return new Nodo(chave);
        }

        // if (chave < nodo.chave) {
        if (chave < nodo.chave) {
            nodo.esquerdo = inserir0(nodo.esquerdo, chave);
//            if (h(nodo.esquerdo) - h(nodo.direito) == 2) {
//                // if (chave < nodo.esquerdo.chave) {
//                if (chave < nodo.esquerdo.chave) {
//                    nodo = rotacionarComFilhoEsquerdo(nodo);
//                } else {
//                    nodo = duplaComFilhoEsquerdo(nodo);
//                }
//            }
            // } else if (chave > nodo.chave) {
        } else if (chave > nodo.chave) {
            nodo.direito = inserir0(nodo.direito, chave);
//            if (h(nodo.esquerdo) - h(nodo.direito) == -2) {
//                // if (chave > nodo.direito.chave) {
//                if (chave > nodo.direito.chave) {
//                    nodo = rotacionarComFilhoDireito(nodo);
//                } else {
//                    nodo = duplaComFilhoDireito(nodo);
//                }
//            }
        } else {
            throw new IllegalArgumentException("Chave duplicada");
        }

        nodo.altura = Math.max(h(nodo.esquerdo), h(nodo.direito)) + 1;
        return nodo;
    }

    private Nodo duplaComFilhoDireito(Nodo k1) {
        k1.direito = rotacionarComFilhoEsquerdo(k1.direito);
        return rotacionarComFilhoDireito(k1);
    }

    private Nodo duplaComFilhoEsquerdo(Nodo k3) {
        k3.esquerdo = rotacionarComFilhoDireito(k3.esquerdo);
        return rotacionarComFilhoEsquerdo(k3);
    }

    private Nodo rotacionarComFilhoDireito(Nodo k1) {
        Nodo k2 = k1.direito;
        k1.direito = k2.esquerdo;
        k2.esquerdo = k1;

        k1.altura = Math.max(h(k1.esquerdo), h(k1.direito)) + 1;
        k2.altura = Math.max(k1.altura, h(k2.direito)) + 1;

        return k2;
    }

    private Nodo rotacionarComFilhoEsquerdo(Nodo k2) {
        Nodo k1 = k2.esquerdo;
        k2.esquerdo = k1.direito;
        k1.direito = k2;

        k2.altura = Math.max(h(k2.esquerdo), h(k2.direito)) + 1;
        k1.altura = Math.max(h(k1.esquerdo), k2.altura) + 1;

        return k1;
    }

    private int h(Nodo nodo) {

        return nodo == null ? -1 : nodo.altura;
    }

    public void preOrdem() {
        System.out.println("PRE=");
        preOrdem0(raiz);
        System.out.println();
    }

    private void preOrdem0(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print(" " + nodo.chave);

        preOrdem0(nodo.esquerdo);
        preOrdem0(nodo.direito);

    }

    public void central() {
        System.out.println("CENTRAL=");
        central0(raiz);
        System.out.println();
    }

    private void central0(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        central0(nodo.esquerdo);

        System.out.print(" " + nodo.chave);

        central0(nodo.direito);
    }

    public void posOrdem() {
        System.out.println("POS=");
        posOrdem0(raiz);
        System.out.println();
    }

    private void posOrdem0(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        posOrdem0(nodo.esquerdo);
        posOrdem0(nodo.direito);

        System.out.print(" " + nodo.chave);
    }

    @Override
    public String toString() {
        return String.format("ArvoreBinariaDePesquisa [raiz=%s]",
                toString0(raiz));
    }

    /**
     * Pré-ordem
     *
     * @param nodo
     * @return
     */
    private String toString0(Nodo nodo) {
        if (nodo == null) {
            return " # ";
        }

        int delta = getAltura0(nodo.esquerdo) - getAltura0(nodo.direito);
        String msg = String
                .format("%s  nível= ? altura= %d delta= %d grau= %d pai= ? tio = ? %n[%s]%n[%s]",
                        nodo.chave, getAltura0(nodo), delta, grau(nodo),
                        toString0(nodo.esquerdo), toString0(nodo.direito));

        return msg;
    }

    private int grau(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int g = 0;
        if (nodo.esquerdo != null) {
            g++;
        }
        if (nodo.direito != null) {
            g++;
        }

        return g++;
    }

    public boolean consultar(int chave) {
        return consultar0(raiz, chave);
    }

    private boolean consultar0(Nodo nodo, int chave) {
        if (nodo == null) {
            return false;
        }

        // if (chave < nodo.chave)
        if (chave < nodo.chave) {
            return consultar0(nodo.esquerdo, chave);
        } else if (chave > nodo.chave) {
            return consultar0(nodo.direito, chave);
        } else {
            return true;
        }
    }

    public int getAltura() {
        return getAltura0(raiz);
    }

    /**
     * Pós-ordem
     *
     * @param nodo
     * @return
     */
    private int getAltura0(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int ae = getAltura0(nodo.esquerdo);
        int ad = getAltura0(nodo.direito);

        return 1 + Math.max(ae, ad);
    }

    public boolean isEquilibrada() {
        return isEquilibrada0(raiz);
    }

    private boolean isEquilibrada0(Nodo nodo) {
        if (nodo == null) {
            return true;
        }
        int delta = getAltura0(nodo.esquerdo) - getAltura0(nodo.direito);

        if (delta == 2 || delta == -2) {
            return false;
        }

        return isEquilibrada0(nodo.esquerdo) && isEquilibrada0(nodo.direito);
    }

    public List getCaminho(int chave) {
        List r = new ArrayList<>();
        return getCaminho0(raiz, chave, r);
    }

    private List getCaminho0(Nodo nodo, int chave, List r) {
        if (nodo == null) {
            return null;
        }

        r.add(nodo.chave);

        if (chave < nodo.chave) {
            return getCaminho0(nodo.esquerdo, chave, r);
        } else if (chave > nodo.chave) {
            return getCaminho0(nodo.direito, chave, r);
        } else {
            return r;
        }

    }

    public void printSequenciasDePares() {
        printSequenciasDePares0(raiz);
    }

    private void printSequenciasDePares0(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        printSequenciasDePares0(nodo.esquerdo);
        // System.out.println(nodo.chave);
        List<Integer> caminho = getCaminho(nodo.chave);
        boolean temImpar = false;
        for (int t : caminho) {
            if (t % 2 != 0) {
                temImpar = true;
                break;
            }
        }
        if (!temImpar) {
            System.out.println(caminho);
        }
        printSequenciasDePares0(nodo.direito);
    }

    public void printNivelDeImpares() {
        printNivelDeImpares0(raiz);
    }

    private void printNivelDeImpares0(Nodo nodo) {
        int altura = getAltura();
        for (int i = 0; i < altura; i++) {
            List<Integer> nivel = getNivel(i);
            boolean temPar = false;
            for (int t : nivel) {
                if (t % 2 == 0) {
                    temPar = true;
                    break;
                }
            }
            if (!temPar) {
                System.out.println(nivel);
            }
        }
    }

    public List getNivel(int n) {
        List r = new ArrayList<>();
        getNivel0(raiz, n, r, 0);
        return r;
    }

    private List<Nodo> getNivel(int n, Nodo nodo) {
        List r = new ArrayList<>();
        getNivel0(nodo, n, r, 0);
        return r;
    }

    private void getNivel0(Nodo nodo, int n, List r, int atual) {
        if (nodo == null) {
            return;
        }

        if (n == atual) {
            r.add(nodo);
        } else if (atual < n) {
            getNivel0(nodo.esquerdo, n, r, atual + 1);
            getNivel0(nodo.direito, n, r, atual + 1);
        }
    }

    public String printTree() {
        if (getAltura() == -1) { //See if the tree is empty
            return "Arvore Vazia";
        }
        if (getAltura() == 0) { //Check if only have one node in the tree;
            return raiz.chave + "";
        }
        return printTree0(raiz);
    }

    //Sendo feito...
    private String printTree0(Nodo nodo) {
        Queue<Nodo> q = new LinkedList<>();
        for (int i = 0; i <= getAltura(); i++) {
            q.addAll(getNivel(i));
        }
        String arvore = "";
        String linha = "";

        Queue<Integer> espacos = new LinkedList<>();
        espacos.add(0);
        Queue<Nodo> nodosNoNivel = new LinkedList<>();
        int nivel = 0;
        nodosNoNivel.addAll(getNivel(nivel));
        while (!q.isEmpty()) {
            nodo = q.remove();
            boolean esquerdo = nodo.esquerdo != null;
            boolean direito = nodo.direito != null;
            int espaco = espacos.poll();
            int tamanhoNodoAnterior = 1;
            if (!esquerdo && !direito) {
                int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior);

                for (int i = 0; i < (espaco - count); i++) {
                    arvore += " ";
                }
                arvore += nodo.chave;
            } else {
                if (esquerdo && direito) {

                    int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior);
                    int subarvore = tamanhoSubarvores(nodo.esquerdo);
                    if (nodo.equals(raiz)) {
                        for (int i = 0; i < subarvore; i++) {
                            arvore += " ";
                        }
                        arvore += "|";
                        for (int i = 0; i < subarvore; i++) {
                            arvore += "-";
                        }
                        arvore += Integer.toString(nodo.chave);
                        subarvore = tamanhoSubarvores(nodo.direito);
                        for (int i = 0; i < subarvore+1; i++) {
                            arvore += "-";
                        }
                        arvore += "|";
                    } else {
                        if (espaco % 2 == 0) {
                            espaco = espaco / 2 - 1;
                        } else {
                            espaco /= 2;
                        }
                        for (int i = 0; i < espaco; i++) {
                            arvore += " ";
                        }
                        arvore += "|";
                        for (int i = 0; i < espaco - count; i++) {
                            arvore += "-";
                        }
                        arvore += Integer.toString(nodo.chave);
                        for (int i = 0; i < espaco - count+1; i++) {
                            arvore += "-";
                        }
                        arvore += "|";
                    }

                } else {
                    if (esquerdo) {
                        int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior);
                        if (nodo.equals(raiz)) {
                            int subarvore = tamanhoSubarvores(nodo.esquerdo);
                            for (int i = 0; i < subarvore; i++) {
                                arvore += " ";
                            }
                            arvore += "|";
                            for (int i = 0; i < subarvore; i++) {
                                arvore += "-";
                            }
                        } else {
                            if (espaco % 2 == 0) {
                                espaco = espaco / 2 - 1;
                            } else {
                                espaco /= 2;
                            }
                            for (int i = 0; i < espaco; i++) {
                                arvore += " ";
                            }
                            arvore += "|";
                            for (int i = 0; i < espaco-count; i++) {
                                arvore += "-";
                            }
                        }

                        arvore += Integer.toString(nodo.chave);
                    } else {
                        if (direito) {
                            if (nodo.equals(raiz)) {
                                int subarvore = tamanhoSubarvores(nodo.direito);
                                for (int i = 0; i < subarvore; i++) {
                                    arvore += " ";

                                }
                                arvore += Integer.toString(nodo.chave);
                                for (int i = 0; i < subarvore; i++) {
                                    arvore += "-";
                                }
                                arvore += "|";

                            }else{
                                for (int i = 0; i < espaco - 1; i++) {
                                    arvore += " ";

                                }
                                arvore += Integer.toString(nodo.chave);
                                if (espaco % 2 == 0) {
                                    espaco = espaco / 2 - 1;
                                } else {
                                    espaco /= 2;
                                }
                                for (int i = 0; i < espaco; i++) {
                                    arvore += "-";
                                }
                                arvore += "|";
                            }

                        }

                    }
                }

            }
            nodosNoNivel.poll();
            if (nodosNoNivel.isEmpty()) {
                if (nodo.equals(raiz)) {
                    linha = arvore;
                    for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) {
                        espacos.add(i);
                        linha = linha.substring(i + 1);
                    }
                } else {
                    linha = arvore.substring(arvore.lastIndexOf("\n") + 1);
                    for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) {
                        espacos.add(i);
                        linha = linha.substring(i + 1);
                    }

                }
                arvore += "\n";

                nivel++;
                nodosNoNivel.addAll(getNivel(nivel));
            }
            tamanhoNodoAnterior = Integer.toString(nodo.chave).length();
        }

        return arvore;
    }

    private int tamanhoSubarvores(Nodo nodo) {
        int size = Integer.toString(nodo.chave).length();
        if (nodo.esquerdo != null) {
            size += tamanhoSubarvores(nodo.esquerdo);
        }
        if (nodo.direito != null) {
            size += tamanhoSubarvores(nodo.direito);
        }

        return size;
    }

    private int calculaAlinhamento(char[] linha, int tamanhoNodoAnterior) {
        int count = 0;
        for (int i = linha.length - 1; i >= 0; i--) {
            char c = linha[i];
            if (c == '|') {
                count++;
                c = linha[--i];
                while (c == '-') {
                    count++;
                    c = linha[--i];
                }
                count++;
                break;
            } else {
                if (c >= '0' || c <= '9') {
                    count = tamanhoNodoAnterior;
                    break;
                }
            }
        }
        return count;
    }

    //Segunda versão... Funciona mas o alinhamento nunca fica correto.
    private String printTree2(Nodo nodo) {
        Queue<Nodo> q = new LinkedList<>();
        for (int i = 0; i <= getAltura(); i++) {
            q.addAll(getNivel(i));
        }
        String arvore = "";
        String linha = "";
        //arvore += primeiraLinha(nodo);
        //String linha = arvore.substring(0, arvore.indexOf("\n"));
        Queue<Integer> espacos = new LinkedList<>();
        espacos.add(0);
//        for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) {
//            espacos.add(i);
//
//            linha = linha.substring(i + 1);
//        }
        int nivel = 0;
        Queue<Nodo> nodosNoNivel = new LinkedList<>();
        nodosNoNivel.addAll(getNivel(nivel));
        Nodo nodoEsquerdo = new Nodo(Integer.MAX_VALUE);
        Nodo nodoDireito = new Nodo(Integer.MAX_VALUE);
        int tamanhoNodoAnterior = 1;
        while (!q.isEmpty()) {
            nodo = q.remove();
            boolean esquerdo = nodo.esquerdo != null;
            boolean direito = nodo.direito != null;
            int espaco = espacos.poll();

            if (!esquerdo && !direito) {
                for (int i = 0; i < espaco - (tamanhoNodoAnterior - 1); i++) {
                    arvore += " ";
                }
                arvore += nodo.chave;
            } else {
                if (esquerdo) {
                    int subarvore = tamanhoSubarvores(nodo.esquerdo);
                    if (nodoDireito.equals(nodo)) {
                        if (nodo.direito == null) {
                            for (int i = 0; i < espaco - tamanhoSubarvores(nodo); i++) {
                                arvore += " ";
                            }
                        } else {
                            for (int i = 0; i < espaco - tamanhoSubarvores(nodo.direito) - 2; i++) {
                                arvore += " ";
                            }
                        }

                    } else {
                        if (nodoEsquerdo.equals(nodo)) {
                            for (int i = 0; i < espaco - subarvore / 2; i++) {
                                arvore += " ";
                            }
                        } else {
                            if (nodo.esquerdo.direito != null) {
                                for (int i = 0; i < subarvore / 2; i++) {
                                    arvore += " ";
                                }
                            } else {
                                for (int i = 0; i < subarvore; i++) {
                                    arvore += " ";
                                }
                            }

                        }
                    }

                    arvore += "|";
                    arvore += "-";
                    if (direito) {
                        for (int i = 0; i < tamanhoSubarvores(nodo.direito); i++) {
                            arvore += "-";
                        }
                    } else {
                        if (nodo.esquerdo.direito != null) {
                            for (int i = 1; i < subarvore / 2; i++) {
                                arvore += "-";
                            }
                        } else {
                            for (int i = 1; i < Integer.toString(nodo.esquerdo.chave).length(); i++) {
                                arvore += "-";
                            }
                        }
                    }

                } else {
                    for (int i = 0; i < espaco; i++) {
                        arvore += " ";
                    }
                }
                arvore += nodo.chave;
                if (direito) {
                    int subarvore = tamanhoSubarvores(nodo.direito);
                    arvore += "-";
                    if (esquerdo) {
                        for (int i = 0; i < tamanhoSubarvores(nodo.esquerdo); i++) {
                            arvore += "-";
                        }
                    } else {
                        for (int i = 1; i < Integer.toString(nodo.direito.chave).length(); i++) {
                            arvore += "-";
                        }
                    }
                    arvore += "|";

                    for (int i = 0; i < subarvore; i++) {
                        arvore += " ";
                    }
                    if (nodo.direito.esquerdo != null) {
                        nodoDireito = nodo.direito;
                        if (nodo.direito.esquerdo.esquerdo != null) {
                            nodoEsquerdo = nodo.direito.esquerdo;
                        }
                    }

                }

            }
            nodosNoNivel.poll();
            if (nodosNoNivel.isEmpty()) {
                linha = arvore.substring(arvore.lastIndexOf("\n") + 1);
                for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) {
                    espacos.add(i);
                    linha = linha.substring(i + 1);
                }
                arvore += "\n";
                nivel++;
                nodosNoNivel.addAll(getNivel(nivel));
            }
            tamanhoNodoAnterior = Integer.toString(nodo.chave).length();
        }
        return arvore;
    }

    //Primeira versão... Não funciona.
    private String printTree1() {
        String arvore = ""; //String with the binary tree
        //String linha = ""; 
        Queue<Nodo> q = new LinkedList<>();
        for (int i = 0; i <= getAltura(); i++) {
            q.addAll(getNivel(i));
        }

        //Print first element
        Queue<Integer> espacos = new LinkedList<>();
        Nodo n = q.remove();
        if (n.esquerdo != null) { //Check if this node has a left son.
            int subarvores = tamanhoSubarvores(n.esquerdo); //Do the math to see how many ASCII character are need to put in this side of the tree.
            System.out.println("subarvore:" + subarvores);
            for (int i = 0; i < subarvores; i++) {
                arvore += " ";
            }
            arvore += "|";
            espacos.add(arvore.lastIndexOf("|"));
            for (int i = 0; i < subarvores; i++) {
                arvore += "-";
            }
        }
        arvore += n.chave;
        if (n.direito != null) { //Check if this node has a right son.
            int subarvores = tamanhoSubarvores(n.direito); //Do the math to see how many ASCII character are need to put in this side of the tree.
            for (int i = 0; i < subarvores; i++) {
                arvore += "-";
            }
            arvore += "|";
            espacos.add(arvore.lastIndexOf("|") - espacos.peek());
            for (int i = 0; i < subarvores; i++) {
                arvore += " ";
            }
        }
        arvore += "\n";
        //Fim da inserção do primeiro

        String linhaAnterior = "";
        int nivel = 1;
        Queue<Nodo> nodosNoNivel = new LinkedList<>();
        nodosNoNivel.addAll(getNivel(nivel));
        nodosNoNivel.poll();
        boolean first = true;
        Nodo nodoAnterior = null;
        while (!q.isEmpty()) {
            nodosNoNivel.poll();
            n = q.remove();
            int e = espacos.remove();
            int espacoDireito = e;
            if (n.direito == null && n.esquerdo == null) {
                for (int i = 0; i < e - arvore.substring(arvore.lastIndexOf("\n")).lastIndexOf("|") - 1; i++) {
                    arvore += " ";
                }
                arvore += n.chave;
            } else {
                if (n.esquerdo != null) { //Check if this node has a left son.
                    int subarvores = tamanhoSubarvores(n.esquerdo); //Do the math to see how many ASCII character are need to put in this side of the tree.
                    System.out.println("Nodo: " + n.chave + ", Subarvore: " + subarvores);

                    if (n.esquerdo.esquerdo == null) {
                        arvore += "|";
                        linhaAnterior = arvore.substring(arvore.lastIndexOf("\n") + 1);
                        espacos.add(linhaAnterior.lastIndexOf("|"));
                        for (int i = 0; i < e / 2; i++) {
                            arvore += "-";
                        }
                    } else {
//                        if(e==1){
//                            arvore+=" ";
//                        }
                        for (int i = 0; i < subarvores - 1; i++) {
                            arvore += " ";
                        }
                        e -= subarvores;
                        arvore += "|";
                        linhaAnterior = arvore.substring(arvore.lastIndexOf("\n") + 1);
                        espacos.add(linhaAnterior.lastIndexOf("|"));

                        if (e % 2 == 0) {
                            e += -1;
                        }
                        for (int i = 0; i < e; i++) {
                            arvore += "-";

                        }
                    }

                } else {
                    for (int i = 0; i < e; i++) {
                        arvore += " ";
                    }

                }
                arvore += n.chave;
                if (n.direito != null) {

                    int subarvores = tamanhoSubarvores(n.direito); //Do the math to see how many ASCII character are need to put in this side of the tree.
                    for (int i = 0; i < subarvores; i++) {
                        arvore += "-";
                    }
                    arvore += "|";
                    linhaAnterior = arvore.substring(arvore.lastIndexOf("\n") + 1);
                    espacos.add(linhaAnterior.lastIndexOf("|"));
                    for (int i = 0; i < subarvores; i++) {
                        arvore += " ";
                    }

                }

            }

            if (nodosNoNivel.isEmpty()) {
                arvore += "\n";
                nivel++;
                nodosNoNivel.addAll(getNivel(nivel));
            }

        }

        return arvore;

    }

    public String printPorNivel() {
        if (raiz == null) {
            return "Arvore Vazia";
        }
        return printPorNivel(raiz);
    }

    private String printPorNivel(Nodo nodo) {
        int z = 0;
        Queue<Nodo> fila = new LinkedList<>();
        z++;
        fila.add(nodo);
        z++;
        String resultado = "";
        z += 2;
        while (!fila.isEmpty()) {
            z++;
            nodo = fila.remove();
            z += 2;
            if (nodo.esquerdo != null) {
                fila.add(nodo.esquerdo);
            }
            if (nodo.direito != null) {
                fila.add(nodo.direito);
            }
            z += 6;
            resultado += nodo.chave;
            z += 3;
            if (fila.peek() != null && fila.peek().altura != nodo.altura) {
                resultado += "\n";
            } else {
                resultado += " ";
            }
            z += 7;
        }
        z++;
        Complexidade.adicionarValor(z); //Não esta sendo contado, o ultimo z++ é para o return
        return resultado;
    }

}
