package dados;

import tree.Nodo;
import tree.arvoreB;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import tree.arvoreB.*;
import tree.Nodo.*;


public class  readCsv {
    static List<Elemento> elementos = new ArrayList<Elemento>();

    // Método para ler o arquivo CSV e armazenar os dados em instâncias da classe Elemento
    public static List<Elemento> lerCsv(String arquivoCsv) {
        String linha;
        String divisor = ";"; // Usando ponto e vírgula como delimitador
        // arvoreB<Elemento> arvoreb = new arvoreB<>(3);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCsv))) {

            // Lê e imprime os cabeçalhos
            br.readLine();

            // Lê e imprime os dados
            while ((linha = br.readLine()) != null) {
                String[] dadosLinha = linha.split(divisor);

                // Supondo que o arquivo contém exatamente 7 colunas
                if (dadosLinha.length >= 7) {
                    int ano = Integer.parseInt(dadosLinha[0].trim());
                    int mes = Integer.parseInt(dadosLinha[1].trim());
                    String empresa = dadosLinha[2].trim();
                    String origem = dadosLinha[3].trim();
                    String destino = dadosLinha[4].trim();
                    String tarifaString = dadosLinha[5].replace(',', '.');
                    double tarifa = Double.parseDouble(tarifaString);
                    int assent = Integer.parseInt(dadosLinha[6].trim());

                    Elemento elemento = new Elemento(ano,mes,empresa,origem,destino,tarifa,assent);
                    elementos.add(elemento);
                } else {
                    System.out.println("Linha com número de colunas inesperado: " + linha);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos;
    }

}
