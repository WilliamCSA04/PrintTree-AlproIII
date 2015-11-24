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
        if (getAltura() == -1) { 
            return "Arvore Vazia";
        }
        if (getAltura() == 0) { 
            return raiz.chave + "";
        }
        return printTree0(raiz);
    }

    /**
     * @see #tamanhoSubarvores(ArvoreBinaria.ArvoreBinariaDePesquisa.Nodo) 
     * @see #calculaAlinhamento(char[], int) 
     * @param nodo
     * @return 
     */
    private String printTree0(Nodo nodo) {
        int z=0;
        Queue<Nodo> q = new LinkedList<>(); //Criando fila dos nodos
        z+=3;
        for (int i = 0; i <= getAltura(); i++) { //Armazenando todos os nodos dentro da fila q           
            q.addAll(getNivel(i));
            z+=5;
        }
        String arvore = ""; //Arvore printada, vai ser retornada no final
        String linha = ""; //Linha atual que será usada para determinar os espaços necessarios.
        z+=2;
        Queue<Integer> espacos = new LinkedList<>(); //Fila com os espaços necessarios.
        espacos.add(0); //Como o nodo raiz não tem pai (;-;) é adicionado 0 como espaço padrao, só para a fila ter um valor inicial
        z+=2;
        Queue<Nodo> nodosNoNivel = new LinkedList<>(); //Fila com os nodos no nivel atual da arvore
        int nivel = 0; //Nivel atual da arvore;
        z+=2;
        nodosNoNivel.addAll(getNivel(nivel)); //Adicionar o raiz a arvore.
        z+=2;
        int tamanhoNodoAnterior=1;
        z+=2;
        while (!q.isEmpty()) { //While principal, quando a fila de Nodos estiver vazia o codigo terá terminado e a arvore será retornada
            z++;
            nodo = q.remove(); //Obtem o nodo atual
            boolean esquerdo = nodo.esquerdo != null; //Testa se tem filho da esquerda
            boolean direito = nodo.direito != null; //Testa se tem filho da direita
            int espaco = espacos.poll(); //Pega o espaço necessario da fila;
            z+=13;
            if (!esquerdo && !direito) { //Testa se o nodo não tem filhos
                int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior); //Metodo para fazer calculos de alinhamento
                z+=8;
                for (int i = 0; i < (espaco-count); i++) { //Coloca os espaços necessarios até chegar abaixo do "|" da linha anterior
                    arvore += " ";
                    z+=6;
                }
                arvore += nodo.chave; //Coloca o numero
                z+=3;
            } else {
                z++;
                if (esquerdo && direito) { //Testa se tem os dois filhos

                    int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior); //Metodo para fazer calculos de alinhamento
                    z+=6;
                    if (nodo.equals(raiz)) { //Execução para quando o numero é o raiz
                        int subarvore = tamanhoSubarvores(nodo.esquerdo); //Metodo que calcula o tamanho de todas as subarvores do nodo passado por paramentro, alem do tamanho do proprio nodo
                        z+=4;
                        for (int i = 0; i < subarvore; i++) {
                            arvore += " ";
                            z+=5;
                        }
                        arvore += "|";
                        z+=4;
                        for (int i = 0; i < subarvore; i++) {
                            arvore += "-";
                            z+=5;
                        }
                        arvore += Integer.toString(nodo.chave);
                        z+=4;
                        subarvore = tamanhoSubarvores(nodo.direito);
                        z+=5;
                        for (int i = 0; i < subarvore+1; i++) {
                            arvore += "-";
                            z+=6;
                        }
                        arvore += "|";
                        z+=2;
                    } else {//Caso não seja o raiz
                        z+=2;
                        if (espaco % 2 == 0) { //Faz alguns ajustes no espaco, distribuindo mais corretamente os caracteres como "-" e " "
                            espaco = espaco / 2 - 1;
                            z+=3;
                        } else {
                            espaco /= 2;
                            z+=2;
                        }
                        z+=2;
                        for (int i = 0; i < espaco; i++) {
                            arvore += " ";
                            z+=5;
                        }
                        arvore += "|";
                        z+=5;
                        for (int i = 0; i < espaco - count; i++) {
                            arvore += "-";
                            z+=6;
                        }
                        arvore += Integer.toString(nodo.chave);
                        z+=6;
                        for (int i = 0; i < espaco - count+1; i++) {
                            arvore += "-";
                            z+=7;
                        }
                        arvore += "|";
                        z++;
                    }

                } else {
                    if (esquerdo) { //Se só tem filho na esquerda
                        //O funcionamento interno dos ifs e metodos são os mesmo do anterior
                        int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior);
                        z+=6;
                        if (nodo.equals(raiz)) {
                            int subarvore = tamanhoSubarvores(nodo.esquerdo);
                            z+=4;
                            for (int i = 0; i < subarvore; i++) {
                                arvore += " ";
                                z+=5;
                            }
                            arvore += "|";
                            z+=4;
                            for (int i = 0; i < subarvore; i++) {
                                arvore += "-";
                                z+=5;
                            }
                        } else {
                            z+=2;
                            if (espaco % 2 == 0) {
                                espaco = espaco / 2 - 1;
                                z+=3;
                            } else {
                                espaco /= 2;
                                z+=2;
                            }
                            z+=2;
                            for (int i = 0; i < espaco; i++) {
                                arvore += " ";
                                z+=5;
                            }
                            arvore += "|";
                            z+=5;
                            for (int i = 0; i < espaco-count; i++) {
                                arvore += "-";
                                z+=6;
                            }
                        }

                        arvore += Integer.toString(nodo.chave);
                        z+=4;
                    } else {
                        //O funcionamento interno dos ifs e metodos são os mesmo do anterior
                        if (direito) {
                            int count = calculaAlinhamento(arvore.substring(arvore.lastIndexOf("\n") + 1).toCharArray(), tamanhoNodoAnterior);
                            z+=6;
                            if (nodo.equals(raiz)) {
                                int subarvore = tamanhoSubarvores(nodo.direito);
                                z+=4;
                                for (int i = 0; i < subarvore; i++) {
                                    arvore += " ";
                                    z+=5;
                                }
                                arvore += Integer.toString(nodo.chave);
                                z+=4;
                                for (int i = 0; i < subarvore; i++) {
                                    arvore += "-";
                                    z+=5;
                                }
                                arvore += "|";
                                z+=2;

                            }else{
                                z+=3;
                                for (int i = 0; i < espaco-count; i++) {
                                    arvore += " ";
                                    z+=6;
                                }
                                arvore += Integer.toString(nodo.chave);
                                z+=6;
                                if (espaco % 2 == 0) {
                                    espaco = espaco / 2 - 1;
                                    z+=3;
                                } else {
                                    espaco /= 2;
                                    z+=2;
                                }
                                z+=3;
                                for (int i = 0; i < espaco-count; i++) {
                                    arvore += "-";
                                    z+=6;
                                }
                                arvore += "|";
                                z+=2;
                            }

                        }

                    }
                }

            }
            nodosNoNivel.poll();//Retira um nodo da fila
            z+=2;
            if (nodosNoNivel.isEmpty()) { //Testa se há nodos nessa fila, se não houver, é porque a linha acabou e o proximo nodo pertence a outro nivel
                z++;
                if (nodo.equals(raiz)) {
                    linha = arvore; //Iguala a linha atual a arvore, já que ela só tem uma linha atualmente
                    z+=5;
                    for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) { //For vai calculando a distancia entre o inicio da linha e o caracter "|" para que o nodo seguinte fique em baixo dele
                        espacos.add(i); //Adiciona o resultado aos espacos
                        linha = linha.substring(i + 1); //Reduz a string para pegar o proximo index de "|";
                        z+=8;
                    }
                } else { //Faz a mesma coisa que o anterior, só muda a priemira linha, pois a String arvore já tem mais de uma linha (Provavelmente da para otimizar)
                    linha = arvore.substring(arvore.lastIndexOf("\n") + 1); 
                    z+=8;
                    for (int i = linha.indexOf("|"); i != -1; i = linha.indexOf("|")) {
                        espacos.add(i);
                        linha = linha.substring(i + 1);
                        z+=8;
                    }

                }
                arvore += "\n"; 
                z+=2;
                nivel++; //Atualiza o nivel da arvore
                z+=2;
                nodosNoNivel.addAll(getNivel(nivel));//Adiciona todos os nodos do nivel da arvore
                z+=2;
            }
            tamanhoNodoAnterior = Integer.toString(nodo.chave).length(); //Tem que arrumar isso aqui de uma maneira melhor, talvez resolva os problemas dos alinhamentos sem ter que ficar fazendo tanto calculo.
            z+=2;
        }
        z++;
        Complexidade.adicionarValor(z);
        return arvore;
    }

    private int tamanhoSubarvores(Nodo nodo) { //Calcula o tamanho do nodo atual e de todos os seus filhos
        int size = Integer.toString(nodo.chave).length();
        if (nodo.esquerdo != null) {
            size += tamanhoSubarvores(nodo.esquerdo);
        }
        if (nodo.direito != null) {
            size += tamanhoSubarvores(nodo.direito);
        }

        return size;
    }

    private int calculaAlinhamento(char[] linha, int tamanhoNodoAnterior) { //Faz um calculo para ajuste no alinhamento
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
                    count = tamanhoNodoAnterior-1;
                    break;
                }
            }
        }
        return count;
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
