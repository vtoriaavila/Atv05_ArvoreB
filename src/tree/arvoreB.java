package tree;

public class arvoreB<Elemento extends Comparable<Elemento>>{
    private Nodo<Elemento> raiz = null;
    private int ordem, index, treeSize;
    private final int meioNumero;
    public final Nodo<Elemento> nullBTNode = new Nodo<Elemento>();


    public arvoreB(int order) {
        if (order < 3) {
            try {
                throw new Exception("Ordem da arvore nao pode ser maior que 3");
            } catch (Exception e) {
                e.printStackTrace();
            }
            order = 3;
        }
        this.ordem = order;
        meioNumero = (order - 1) / 2;
    }


    public boolean isEmpty() {
        return raiz == null;
    }


    public Nodo<Elemento> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<Elemento> raiz) {
        this.raiz = raiz;
    }



    public int getTreeSize() {
        return treeSize;
    }

    public int getMeioNumero() {
        return meioNumero;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }


    public int getAlt() {
        if (isEmpty()) {
            return 0;
        } else {
            return getAlt(raiz);
        }
    }

    public static void getAlts(){
        return;
    }

    public int getAlt(Nodo<Elemento> node) {
        int altura = 0;
        Nodo<Elemento> noAtual = node;
        while (!noAtual.equals(nullBTNode)) {
            noAtual = noAtual.getFilho(0);
            altura++;
        }
        return altura;
    }


    public Nodo<Elemento> getNode(Elemento chave) {
        if (isEmpty()) {
            return nullBTNode;
        }
        Nodo<Elemento> noAtual = raiz;
        while (!noAtual.equals(nullBTNode)) {
            int i = 0;
            while (i < noAtual.getTam()) {
                if (noAtual.getChave(i).equals(chave)) {
                    index = i;
                    return noAtual;
                } else if (noAtual.getChave(i).compareTo(chave) > 0) {
                    noAtual = noAtual.getFilho(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            if (!noAtual.isNull()) {
                noAtual = noAtual.getFilho(noAtual.getTam());
            }
        }
        return nullBTNode;
    }


    private Nodo<Elemento> getMeiaChaves(Elemento key, Nodo<Elemento> nodoT) {
        int tamTotalN = nodoT.getTam();

        for (int i = 0; i < tamTotalN; i++) {
            if (nodoT.getChave(i).compareTo(key) > 0) {
                nodoT.addChave(i, key);
                break;
            }
        }
        if (tamTotalN == nodoT.getTam())
            nodoT.addChave(tamTotalN, key);



        return getMeiaChaves(nodoT);
    }


    private Nodo<Elemento> getMeiaChaves(Nodo<Elemento> totalN) {
        Nodo<Elemento> novoNodo = new Nodo<Elemento>(ordem);
        for (int i = 0; i < meioNumero; i++) {
            novoNodo.addChave(i, totalN.getChave(0));
            totalN.removeChave(0);
        }
        return novoNodo;
    }


    private Nodo<Elemento> getRestoChaves(Nodo<Elemento> metadeNodo) {
        Nodo<Elemento> novoNodo = new Nodo<Elemento>(ordem);
        int metTamNodo = metadeNodo.getTam();
        for (int i = 0; i < metTamNodo; i++) {
            if (i != 0) {
                novoNodo.addChave(i - 1, metadeNodo.getChave(1));
                metadeNodo.removeChave(1);
            }
            novoNodo.addFilho(i, metadeNodo.getFilho(0));
            metadeNodo.removeFilho(0);
        }
        return novoNodo;
    }


    private void uniaoPai(Nodo<Elemento> nodoFilho, int index) {
        nodoFilho.getPai().addChave(index, nodoFilho.getChave(0));
        nodoFilho.getPai().removeFilho(index);
        nodoFilho.getPai().addFilho(index, nodoFilho.getFilho(0));
        nodoFilho.getPai().addFilho(index + 1, nodoFilho.getFilho(1));
    }

    private void uniaoPai(Nodo<Elemento> nodoFilho) {
        int paiNodoTam = nodoFilho.getPai().getTam();
        for (int i = 0; i < paiNodoTam; i++) {
            if (nodoFilho.getPai().getChave(i).compareTo(nodoFilho.getChave(0)) > 0) {
                uniaoPai(nodoFilho, i);
                break;
            }
        }
        if (paiNodoTam == nodoFilho.getPai().getTam()) {
            uniaoPai(nodoFilho, paiNodoTam);
        }
        for (int i = 0; i <= nodoFilho.getPai().getTam(); i++)
            nodoFilho.getPai().getFilho(i).setPai(nodoFilho.getPai());
    }


    private void setPaiMeioNodo(Nodo<Elemento> node) {
        for (int i = 0; i <= node.getTam(); i++)
            node.getFilho(i).setPai(node);
    }


    private void processOverflow(Nodo<Elemento> noAtual) {
        Nodo<Elemento> novoNodo = getMeiaChaves(noAtual);
        for (int i = 0; i <= novoNodo.getTam(); i++) {
            novoNodo.addFilho(i, noAtual.getFilho(0));
            noAtual.removeFilho(0);
        }
        Nodo<Elemento> originalNode = getRestoChaves(noAtual);
        noAtual.addFilho(0, novoNodo);
        noAtual.addFilho(1, originalNode);
        originalNode.setPai(noAtual);
        novoNodo.setPai(noAtual);
        setPaiMeioNodo(originalNode);
        setPaiMeioNodo(novoNodo);


    }


    public void inserir(Elemento chave) {
        if (isEmpty()) {
            raiz = new Nodo<Elemento>(ordem);
            raiz.addChave(0, chave);
            treeSize++;
            raiz.setPai(nullBTNode);
            raiz.addFilho(0, nullBTNode);
            raiz.addFilho(1, nullBTNode);


            return;
        }

        Nodo<Elemento> noAtual = raiz;


        while (!noAtual.ultimoNodoIn()) {
            int i = 0;
            while (i < noAtual.getTam()) {
                if (noAtual.ultimoNodoIn()) {
                    i = noAtual.getTam();
                } else if (noAtual.getChave(i).compareTo(chave) > 0) {
                    noAtual = noAtual.getFilho(i);
                    i = 0;
                } else {
                    i++;
                }
            }
            if (!noAtual.ultimoNodoIn())
                noAtual = noAtual.getFilho(noAtual.getTam());
        }

        if (!noAtual.isFull()) {
            int i = 0;
            while (i < noAtual.getTam()) {

                if (noAtual.getChave(i).compareTo(chave) > 0) {
                    noAtual.addChave(i, chave);
                    noAtual.addFilho(noAtual.getTam(), nullBTNode);
                    treeSize++;


                    return;
                } else {
                    i++;
                }
            }

            noAtual.addChave(noAtual.getTam(), chave);
            noAtual.addFilho(noAtual.getTam(), nullBTNode);
            treeSize++;

        } else {

            Nodo<Elemento> novoNoFilho = getMeiaChaves(chave, noAtual);
            for (int i = 0; i < meioNumero; i++) {
                novoNoFilho.addFilho(i, noAtual.getFilho(0));
                noAtual.removeFilho(0);
            }
            novoNoFilho.addFilho(meioNumero, nullBTNode);


            Nodo<Elemento> originPaiNodo = getRestoChaves(noAtual);
            noAtual.addFilho(0, novoNoFilho);
            noAtual.addFilho(1, originPaiNodo);
            originPaiNodo.setPai(noAtual);
            novoNoFilho.setPai(noAtual);
            treeSize++;


            if (!noAtual.getPai().equals(nullBTNode)) {
                while (!noAtual.getPai().isOverflow() && !noAtual.getPai().equals(nullBTNode)) {
                    boolean flag = noAtual.getTam() == 1 && !noAtual.getPai().isOverflow();
                    if (noAtual.isOverflow() || flag) {
                        uniaoPai(noAtual);
                        noAtual = noAtual.getPai();


                        if (noAtual.isOverflow()) {
                            processOverflow(noAtual);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }


    private int buscaFilho(Nodo<Elemento> node) {
        if (!node.equals(raiz)) {
            Nodo<Elemento> fatherNode = node.getPai();

            for (int i = 0; i <= fatherNode.getTam(); i++) {
                if (fatherNode.getFilho(i).equals(node))
                    return i;
            }
        }
        return -1;
    }


    private Nodo<Elemento> balancearNoDelete(Nodo<Elemento> node) {
        boolean flag;
        int nodeIndex = buscaFilho(node);
        Elemento pair;
        Nodo<Elemento> paiNodo = node.getPai();
        Nodo<Elemento> atualNodo;
        if (nodeIndex == 0) {
            atualNodo = paiNodo.getFilho(1);

            flag = true;
        } else {
            atualNodo = paiNodo.getFilho(nodeIndex - 1);
            flag = false;
        }

        int tamAtual = atualNodo.getTam();
        if (tamAtual > meioNumero) {
            if (flag) {
                pair = paiNodo.getChave(0);
                node.addChave(node.getTam(), pair);
                paiNodo.removeChave(0);
                pair = atualNodo.getChave(0);
                atualNodo.removeChave(0);
                node.addFilho(node.getTam(), atualNodo.getFilho(0));
                atualNodo.removeFilho(0);
                paiNodo.addChave(0, pair);
                if (node.ultimoNodoIn()) {
                    node.removeFilho(0);
                }

            } else {
                pair = paiNodo.getChave(nodeIndex - 1);
                node.addChave(0, pair);
                paiNodo.removeChave(nodeIndex - 1);
                pair = atualNodo.getChave(tamAtual - 1);
                atualNodo.removeChave(tamAtual - 1);
                node.addFilho(0, atualNodo.getFilho(tamAtual));
                atualNodo.removeFilho(tamAtual);
                paiNodo.addChave(nodeIndex - 1, pair);
                if (node.ultimoNodoIn()) {
                    node.removeFilho(0);
                }
            }
            return node;
        } else {
            if (flag) {
                atualNodo.addChave(0, paiNodo.getChave(0));
                paiNodo.removeChave(0);
                paiNodo.removeFilho(0);
                if (raiz.getTam() == 0) {
                    raiz = atualNodo;
                    atualNodo.setPai(nullBTNode);
                }
                if (node.getTam() == 0) {
                    atualNodo.addFilho(0, node.getFilho(0));
                    atualNodo.getFilho(0).setPai(atualNodo);
                }
                for (int i = 0; i < node.getTam(); i++) {
                    atualNodo.addChave(i, node.getChave(i));
                    atualNodo.addFilho(i, node.getFilho(i));
                    atualNodo.getFilho(i).setPai(atualNodo);
                }

            } else {
                atualNodo.addChave(atualNodo.getTam(), paiNodo.getChave(nodeIndex - 1));
                paiNodo.removeChave(nodeIndex - 1);
                paiNodo.removeFilho(nodeIndex);
                if (raiz.getTam() == 0) {
                    raiz = atualNodo;
                    atualNodo.setPai(nullBTNode);
                }
                int tamNoAtual = atualNodo.getTam();
                if (node.getTam() == 0) {
                    atualNodo.addFilho(tamNoAtual, node.getFilho(0));
                    atualNodo.getFilho(tamNoAtual).setPai(atualNodo);
                }
                for (int i = 0; i < node.getTam(); i++) {
                    atualNodo.addChave(tamNoAtual + i, node.getChave(i));
                    atualNodo.addFilho(tamNoAtual + i, node.getFilho(i));
                    atualNodo.getFilho(tamNoAtual + i).setPai(atualNodo);
                }


            }
            return paiNodo;
        }
    }


    private Nodo<Elemento> subsNodo(Nodo<Elemento> node) {
        Nodo<Elemento> atualNodo = node.getFilho(index + 1);
        while (!atualNodo.ultimoNodoIn()) {
            atualNodo = atualNodo.getFilho(0);
        }

        if (atualNodo.getTam() - 1 < meioNumero) {

            atualNodo = node.getFilho(index);
            int tamAtualNo = atualNodo.getTam();
            while (!atualNodo.ultimoNodoIn()) {
                atualNodo = atualNodo.getFilho(tamAtualNo);
            }
            node.addChave(index, atualNodo.getChave(tamAtualNo - 1));
            atualNodo.removeChave(tamAtualNo - 1);
            atualNodo.addChave(tamAtualNo - 1, node.getChave(index + 1));
            node.removeChave(index + 1);
            index = atualNodo.getTam() - 1;


        } else {

            node.addChave(index + 1, atualNodo.getChave(0));
            atualNodo.removeChave(0);
            atualNodo.addChave(0, node.getChave(index));
            node.removeChave(index);
            index = 0;


        }
        return atualNodo;
    }


    public void remover(Elemento key) {

        Nodo<Elemento> node = getNode(key);
        Nodo<Elemento> deleteNode = null;
        if (node.equals(nullBTNode))
            return;

        if (node.equals(raiz) && node.getTam() == 1 && node.ultimoNodoIn()) {
            raiz = null;
            treeSize--;

        } else {
            boolean flag = true;
            boolean isReplaced = false;

            if (!node.ultimoNodoIn()) {
                node = subsNodo(node);
                deleteNode = node;
                isReplaced = true;
            }


            if (node.getTam() - 1 < meioNumero) {

                node = balancearNoDelete(node);
                if (isReplaced) {
                    for (int i = 0; i <= node.getTam(); i++) {
                        for (int j = 0; i < node.getFilho(i).getTam(); j++) {
                            if (node.getFilho(i).getChave(j).equals(key)) {
                                deleteNode = node.getFilho(i);
                                break;
                            }
                        }
                    }
                }
            } else if (node.ultimoNodoIn()) {

                node.removeFilho(0);
            }

            while (!node.getFilho(0).equals(raiz) && node.getTam() < meioNumero && flag) {

                if (node.equals(raiz)) {
                    for (int i = 0; i <= raiz.getTam(); i++) {
                        if (raiz.getFilho(i).getTam() == 0) {
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    node = balancearNoDelete(node);
                }
            }

            if (deleteNode == null) {

                node = getNode(key);
            } else {
                node = deleteNode;
            }

            if (!node.equals(nullBTNode)) {

                for (int i = 0; i < node.getTam(); i++) {
                    if (node.getChave(i) == key) {
                        node.removeChave(i);
                    }
                }
                treeSize--;


            }
        }
    }

    public void printarArvore() {
        printarArvore(raiz, "", true);
    }

    private void printarArvore(Nodo<Elemento> node, String indent, boolean last) {
        if (node != null && !node.equals(nullBTNode)) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "|   ";
            }
            System.out.println(node.getChave());

            for (int i = 0; i < node.getFilho().size(); i++) {
                printarArvore(node.getFilho(i), indent, i == node.getFilho().size() - 1);
            }
        }
    }

    public boolean buscar(Elemento key) {
        if (isEmpty()) {
            return false; // A árvore está vazia
        }

        Nodo<Elemento> atualNodo = raiz;

        while (!atualNodo.equals(nullBTNode)) {
            int i = 0;

            // Procura pela chave no nó atual
            while (i < atualNodo.getTam() && atualNodo.getChave(i).compareTo(key) < 0) {
                i++;
            }

            // Verifica se a chave foi encontrada
            if (i < atualNodo.getTam() && atualNodo.getChave(i).equals(key)) {
                return true; // Chave encontrada
            }

            // Se for um nó folha, a chave não está presente na árvore
            if (atualNodo.ultimoNodoIn()) {
                return false; // Chave não encontrada
            }

            // Move para o filho apropriado
            atualNodo = atualNodo.getFilho(i);
        }

        return false; // A chave não está presente
    }


}
