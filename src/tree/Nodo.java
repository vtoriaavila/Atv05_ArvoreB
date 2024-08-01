package tree;

import java.util.ArrayList;

public class Nodo<Elemento extends Comparable<Elemento>>{


    private int fullNumber;
    private Nodo<Elemento> pai;
    private ArrayList<Nodo<Elemento>> filho = new ArrayList<Nodo<Elemento>>();
    private ArrayList<Elemento> chave = new ArrayList<>();

    public Nodo() {
    }

    public Nodo(int order) {
        fullNumber = order - 1;
    }


    public boolean ultimoNodoIn() {
        if (chave.size() == 0)
            return false;
        for (Nodo<Elemento> node : filho)
            if (node.chave.size() != 0)
                return false;
        return true;
    }


    public Nodo<Elemento> getPai() {
        return pai;
    }


    public void setPai(Nodo<Elemento> pai) {
        this.pai = pai;
    }

    public ArrayList<Nodo<Elemento>> getFilho() {
        return filho;
    }


    public Nodo<Elemento> getFilho(int index) {
        return filho.get(index);
    }


    public void addFilho(int index, Nodo<Elemento> node) {
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

    public ArrayList<Elemento> getChave() {
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
