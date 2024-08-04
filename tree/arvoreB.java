package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class arvoreB<Elemento>{
    private Nodo raiz = null;
    private int ordem, index, treeSize;
    private final int meioNumero;
    public final Nodo nullBTNode = new Nodo();


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


    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
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

    public int getAlt(Nodo node) {
        int altura = 0;
        Nodo noAtual = node;
        while (!noAtual.equals(nullBTNode)) {
            noAtual = noAtual.getFilho(0);
            altura++;
        }
        return altura;
    }


    public Nodo getNode(Elemento chave) {
        if (isEmpty()) {
            return nullBTNode;
        }
        Nodo noAtual = raiz;
        while (!noAtual.equals(nullBTNode)) {
            int i = 0;
            while (i < noAtual.getTam()) {
                if (noAtual.getChave(i).equals(chave)) {
                    index = i;
                    return noAtual;
                } else if (noAtual.getChave(i).compareTo((dados.Elemento) chave) > 0) {
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


    private Nodo getMeiaChaves(Elemento key, Nodo nodoT) {
        int tamTotalN = nodoT.getTam();

        for (int i = 0; i < tamTotalN; i++) {
            if (nodoT.getChave(i).compareTo((dados.Elemento) key) > 0) {
                nodoT.addChave(i, (dados.Elemento) key);
                break;
            }
        }
        if (tamTotalN == nodoT.getTam())
            nodoT.addChave(tamTotalN, (dados.Elemento) key);



        return getMeiaChaves(nodoT);
    }


    private Nodo getMeiaChaves(Nodo totalN) {
        Nodo novoNodo = new Nodo(ordem);
        for (int i = 0; i < meioNumero; i++) {
            novoNodo.addChave(i, totalN.getChave(0));
            totalN.removeChave(0);
        }
        return novoNodo;
    }


    private Nodo getRestoChaves(Nodo metadeNodo) {
        Nodo novoNodo = new Nodo(ordem);
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


    private void uniaoPai(Nodo nodoFilho, int index) {
        nodoFilho.getPai().addChave(index, nodoFilho.getChave(0));
        nodoFilho.getPai().removeFilho(index);
        nodoFilho.getPai().addFilho(index, nodoFilho.getFilho(0));
        nodoFilho.getPai().addFilho(index + 1, nodoFilho.getFilho(1));
    }

    private void uniaoPai(Nodo nodoFilho) {
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


    private void setPaiMeioNodo(Nodo node) {
        for (int i = 0; i <= node.getTam(); i++)
            node.getFilho(i).setPai(node);
    }


    private void processOverflow(Nodo noAtual) {
        Nodo novoNodo = getMeiaChaves(noAtual);
        for (int i = 0; i <= novoNodo.getTam(); i++) {
            novoNodo.addFilho(i, noAtual.getFilho(0));
            noAtual.removeFilho(0);
        }
        Nodo originalNode = getRestoChaves(noAtual);
        noAtual.addFilho(0, novoNodo);
        noAtual.addFilho(1, originalNode);
        originalNode.setPai(noAtual);
        novoNodo.setPai(noAtual);
        setPaiMeioNodo(originalNode);
        setPaiMeioNodo(novoNodo);


    }


    public void inserir(Elemento chave) {
        if (isEmpty()) {
            raiz = new Nodo(ordem);
            raiz.addChave(0, (dados.Elemento) chave);
            treeSize++;
            raiz.setPai(nullBTNode);
            raiz.addFilho(0, nullBTNode);
            raiz.addFilho(1, nullBTNode);


            return;
        }

        Nodo noAtual = raiz;


        while (!noAtual.ultimoNodoIn()) {
            int i = 0;
            while (i < noAtual.getTam()) {
                if (noAtual.ultimoNodoIn()) {
                    i = noAtual.getTam();
                } else if (noAtual.getChave(i).compareTo((dados.Elemento) chave) > 0) {
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

                if (noAtual.getChave(i).compareTo((dados.Elemento) chave) > 0) {
                    noAtual.addChave(i, (dados.Elemento) chave);
                    noAtual.addFilho(noAtual.getTam(), nullBTNode);
                    treeSize++;


                    return;
                } else {
                    i++;
                }
            }

            noAtual.addChave(noAtual.getTam(), (dados.Elemento) chave);
            noAtual.addFilho(noAtual.getTam(), nullBTNode);
            treeSize++;

        } else {

            Nodo novoNoFilho = getMeiaChaves(chave, noAtual);
            for (int i = 0; i < meioNumero; i++) {
                novoNoFilho.addFilho(i, noAtual.getFilho(0));
                noAtual.removeFilho(0);
            }
            novoNoFilho.addFilho(meioNumero, nullBTNode);


            Nodo originPaiNodo = getRestoChaves(noAtual);
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


    private int buscaFilho(Nodo node) {
        if (!node.equals(raiz)) {
            Nodo fatherNode = node.getPai();

            for (int i = 0; i <= fatherNode.getTam(); i++) {
                if (fatherNode.getFilho(i).equals(node))
                    return i;
            }
        }
        return -1;
    }


    private Nodo balancearNoDelete(Nodo node) {
        boolean flag;
        int nodeIndex = buscaFilho(node);
        dados.Elemento pair;
        Nodo paiNodo = node.getPai();
        Nodo atualNodo;
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


    private Nodo subsNodo(Nodo node) {
        Nodo atualNodo = node.getFilho(index + 1);
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

        Nodo node = getNode(key);
        Nodo deleteNode = null;
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

    private void printarArvore(Nodo node, String indent, boolean last) {
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

            List<Nodo> filhos = node.getFilho();
            for (int i = 0; i < filhos.size(); i++) {
                printarArvore(filhos.get(i), indent, i == filhos.size() - 1);
            }
        }
    }
    public void printarMediaG(){
        double mediaTarifas = calcularMediaTarifas();
        System.out.printf("Média das Tarifas: %.2f%n", mediaTarifas);

    }
    public void printarMediaEmp(){
        analisarTarifasAereasEmp(raiz);


    }
    public void printarMediaTemp(){
        analisarTarifasAereasAnoMes(raiz);

    }
    public double calcularMediaTarifas() {
        float[] resultado = calcularSomaETotalTarifas(raiz);
        float soma = resultado[0];
        int total = (int) resultado[1];
        return total == 0 ? 0 : soma / total;
    }

    private float[] calcularSomaETotalTarifas(Nodo node) {
        if (node == null) {
            return new float[]{0, 0};
        }

        float soma = 0;
        int total = 0;

        for (dados.Elemento elemento : node.getChave()) {
            if (elemento != null) {
                soma += elemento.getTarifa();
                total++;
            }
        }

        for (Nodo filho : node.getFilho()) {
            float[] resultadoFilho = calcularSomaETotalTarifas(filho);
            soma += resultadoFilho[0];
            total += (int) resultadoFilho[1];
        }

        return new float[]{soma, total};
    }


    public void analisarTarifasAereasAnoMes(Nodo node) {
        // Agrupar elementos por ano e mês
        Map<Integer, Map<Integer, List<dados.Elemento>>> elementosPorAnoEMes = new HashMap<>();

        // Método auxiliar para processar os nós recursivamente
        processarNodoRecursivoTime(node, elementosPorAnoEMes);

        // Calcular a média das tarifas por ano e mês
        Map<String, Double> mediaTarifasPorAnoEMes = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<dados.Elemento>>> anoEntry : elementosPorAnoEMes.entrySet()) {
            int ano = anoEntry.getKey();
            for (Map.Entry<Integer, List<dados.Elemento>> mesEntry : anoEntry.getValue().entrySet()) {
                int mes = mesEntry.getKey();
                List<dados.Elemento> elementosDoMes = mesEntry.getValue();
                double somaTarifas = 0;
                for (dados.Elemento e : elementosDoMes) {
                    somaTarifas += e.getTarifa();
                }
                double mediaTarifas = somaTarifas / elementosDoMes.size();
                String anoMesKey = String.format("%d-%02d", ano, mes);
                mediaTarifasPorAnoEMes.put(anoMesKey, mediaTarifas);
            }
        }

        // Encontrar o ano e mês com a tarifa média mais baixa e mais alta
        String anoMesTarifaMaisBaixa = null;
        String anoMesTarifaMaisAlta = null;
        double menorTarifa = Double.MAX_VALUE;
        double maiorTarifa = Double.MIN_VALUE;

        for (Map.Entry<String, Double> entry : mediaTarifasPorAnoEMes.entrySet()) {
            String anoMes = entry.getKey();
            double mediaTarifa = entry.getValue();
            if (mediaTarifa < menorTarifa) {
                menorTarifa = mediaTarifa;
                anoMesTarifaMaisBaixa = anoMes;
            }
            if (mediaTarifa > maiorTarifa) {
                maiorTarifa = mediaTarifa;
                anoMesTarifaMaisAlta = anoMes;
            }
        }

        // Imprimir os resultados
        System.out.println("Média das tarifas por ano e mês:");
        for (Map.Entry<String, Double> entry : mediaTarifasPorAnoEMes.entrySet()) {
            System.out.printf("Ano-Mês: %s, Média de Tarifa: %.2f%n", entry.getKey(), entry.getValue());
        }

        System.out.printf("\nAno e mês com a tarifa média mais baixa: %s (Tarifa: %.2f)%n", anoMesTarifaMaisBaixa, menorTarifa);
        System.out.printf("Ano e mês com a tarifa média mais alta: %s (Tarifa: %.2f)%n", anoMesTarifaMaisAlta, maiorTarifa);
    }

    private void processarNodoRecursivoTime(Nodo node, Map<Integer, Map<Integer, List<dados.Elemento>>> elementosPorAnoEMes) {
        if (node == null) return;

        for (dados.Elemento elemento : node.getChave()) {
            int ano = elemento.getAno();
            int mes = elemento.getMes();
            elementosPorAnoEMes.computeIfAbsent(ano, k -> new HashMap<>())
                    .computeIfAbsent(mes, k -> new ArrayList<>())
                    .add(elemento);
        }

        for (Nodo filho : node.getFilho()) {
            processarNodoRecursivoTime(filho, elementosPorAnoEMes);
        }
    }

    public void analisarTarifasAereasEmp(Nodo node) {
        // Agrupar elementos por empresa
        Map<String, List<dados.Elemento>> elementosPorEmpresa = new HashMap<>();

        // Método auxiliar para processar os nós recursivamente
        processarNodoRecursivo(node, elementosPorEmpresa);

        // Calcular a média das tarifas por empresa
        Map<String, Double> mediaTarifasPorEmpresa = new HashMap<>();
        for (Map.Entry<String, List<dados.Elemento>> entry : elementosPorEmpresa.entrySet()) {
            String empresa = entry.getKey();
            List<dados.Elemento> elementosDaEmpresa = entry.getValue();
            double somaTarifas = 0;
            for (dados.Elemento e : elementosDaEmpresa) {
                somaTarifas += e.getTarifa();
            }
            double mediaTarifas = somaTarifas / elementosDaEmpresa.size();
            mediaTarifasPorEmpresa.put(empresa, mediaTarifas);
        }

        // Encontrar a empresa com a tarifa média mais baixa e mais alta
        String empresaTarifaMaisBaixa = null;
        String empresaTarifaMaisAlta = null;
        double menorTarifa = Double.MAX_VALUE;
        double maiorTarifa = Double.MIN_VALUE;

        for (Map.Entry<String, Double> entry : mediaTarifasPorEmpresa.entrySet()) {
            String empresa = entry.getKey();
            double mediaTarifa = entry.getValue();
            if (mediaTarifa < menorTarifa) {
                menorTarifa = mediaTarifa;
                empresaTarifaMaisBaixa = empresa;
            }
            if (mediaTarifa > maiorTarifa) {
                maiorTarifa = mediaTarifa;
                empresaTarifaMaisAlta = empresa;
            }
        }

        // Imprimir os resultados
        System.out.println("Média das tarifas por empresa:");
        for (Map.Entry<String, Double> entry : mediaTarifasPorEmpresa.entrySet()) {
            System.out.printf("Empresa: %s, Média de Tarifa: %.2f%n", entry.getKey(), entry.getValue());
        }

        System.out.printf("\nEmpresa com a tarifa média mais baixa: %s (Tarifa: %.2f)%n", empresaTarifaMaisBaixa, menorTarifa);
        System.out.printf("Empresa com a tarifa média mais alta: %s (Tarifa: %.2f)%n", empresaTarifaMaisAlta, maiorTarifa);

    }

    private void processarNodoRecursivo(Nodo node, Map<String, List<dados.Elemento>> elementosPorEmpresa) {
        if (node == null) {
            return;
        }

        for (dados.Elemento elemento : node.getChave()) {
            String empresa = elemento.getEmpresa();
            if (!elementosPorEmpresa.containsKey(empresa)) {
                elementosPorEmpresa.put(empresa, new ArrayList<>());
            }
            elementosPorEmpresa.get(empresa).add(elemento);
        }

        for (Nodo filho : node.getFilho()) {
            processarNodoRecursivo(filho, elementosPorEmpresa);
        }
    }




    public boolean buscar(Elemento key) {
        if (isEmpty()) {
            return false; // A árvore está vazia
        }

        Nodo atualNodo = raiz;

        while (!atualNodo.equals(nullBTNode)) {
            int i = 0;

            // Procura pela chave no nó atual
            while (i < atualNodo.getTam() && atualNodo.getChave(i).compareTo((dados.Elemento) key) < 0) {
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

    public void imprimirParaArquivo(String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            imprimirNodo(raiz, writer, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void imprimirNodo(Nodo nodo, BufferedWriter writer, String prefixo) throws IOException {
        if (nodo == null || nodo == nullBTNode) {
            return;
        }
        writer.write(prefixo + nodo.getChave() + "\n");
        for (Nodo filho : nodo.getFilho()) {
            imprimirNodo(filho, writer, prefixo);
        }
    }

    public void printarArvoreParaArquivo(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            printarArvoreParaArquivo(raiz, "", true, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printarArvoreParaArquivo(Nodo node, String indent, boolean last, BufferedWriter writer) throws IOException {
        if (node != null && !node.equals(nullBTNode)) {
            writer.write(indent);
            if (last) {
                writer.write("└── ");
                indent += "    ";
            } else {
                writer.write("├── ");
                indent += "|   ";
            }

            writer.write(node.getChave().toString());
            writer.newLine();

            for (int i = 0; i < node.getFilho().size(); i++) {
                printarArvoreParaArquivo(node.getFilho(i), indent, i == node.getFilho().size() - 1, writer);
            }
        }
    }




}
