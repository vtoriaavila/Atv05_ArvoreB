package dados;

public class Elemento implements Comparable<Elemento> {
    private static int nextKey = 1; // Variável estática para manter o controle das chaves
    private int chave;
    private int ano;
    private int mes;
    private String empresa;
    private String origem;
    private String destino;
    private double tarifa;
    private int assent;

    public Elemento(int ano, int mes, String empresa, String origem, String destino, double tarifa, int assent) {
        this.chave = getNextKey(); // Obtém a próxima chave única
        this.ano = ano;
        this.mes = mes;
        this.empresa = empresa;
        this.origem = origem;
        this.destino = destino;
        this.tarifa = tarifa;
        this.assent = assent;
    }

    private static synchronized int getNextKey() {
        return nextKey++;
    }

    // Getters e setters
    public int getChave() {
        return chave;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public int getAssent() {
        return assent;
    }

    public void setAssent(int assent) {
        this.assent = assent;
    }

    @Override
    public int compareTo(Elemento other) {
        return Integer.compare(this.chave, other.chave);
    }

    public String toString() {
        return "Chave" + chave + "Tarifa: " + tarifa;
    }
}
