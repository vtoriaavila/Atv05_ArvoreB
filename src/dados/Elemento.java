package dados;

public class Elemento {
    private int ano;
    private int mes;
    private String empresa;
    private String origem;
    private String destino;
    private double tarifa;
    private int assento;

    public Elemento(int ano, int mes, String empresa, String origem, String destino, double tarifa, int assento) {
        this.ano = ano;
        this.mes = mes;
        this.empresa = empresa;
        this.origem = origem;
        this.destino = destino;
        this.tarifa = tarifa;
        this.assento = assento;
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

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public int getAssento() {
        return assento;
    }

    public void setAssento(int assento) {
        this.assento = assento;
    }
}

