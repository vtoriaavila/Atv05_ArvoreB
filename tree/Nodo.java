package tree;

import java.util.ArrayList;
import java.util.List;
import dados.Elemento;

public class Nodo {

    private int fullNumber;
    private Nodo pai;
    private List<Nodo> filho = new ArrayList<>();
    private List<Elemento> chave = new ArrayList<>();

    public Nodo() {
    }

    public Nodo(int order) {
        fullNumber = order - 1;
    }

    public boolean ultimoNodoIn() {
        if (chave.size() == 0)
            return false;
        for (Nodo node : filho)
            if (node.chave.size() != 0)
                return false;
        return true;
    }

    public Nodo getPai() {
        return pai;
    }

    public void setPai(Nodo pai) {
        this.pai = pai;
    }

    public List<Nodo> getFilho() {
        return filho;
    }

    public Nodo getFilho(int index) {
        return filho.get(index);
    }

    public void addFilho(int index, Nodo node) {
        filho.add(index, node);
    }

    public void removeFilho(int index) {
        filho.remove(index);
    }

    public Elemento getChave(int index) {
        return chave.get(index);
    }

    public void addChave(int index, Elemento element) {
        chave.add(index, element);
    }

    public void removeChave(int index) {
        chave.remove(index);
    }

    public List<Elemento> getChave() {
        return chave;
    }

    public boolean isFull() {
        return fullNumber == chave.size();
    }

    public boolean isOverflow() {
        return fullNumber < chave.size();
    }

    public boolean isNull() {
        return chave.isEmpty();
    }

    public int getTam() {
        return chave.size();
    }
}
