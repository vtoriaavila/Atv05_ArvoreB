package dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class readCsv {

    // Método para ler o arquivo CSV e armazenar os dados em instâncias da classe Elemento
       public static void lerCsv(String arquivoCsv) {
            String linha;
            String divisor = ";"; // Usando ponto e vírgula como delimitador

            try (BufferedReader br = new BufferedReader(new FileReader(arquivoCsv))) {

                // Lê e imprime os cabeçalhos
                String[] cabecalhos = br.readLine().split(divisor);
                System.out.println("Cabeçalhos:");
                for (String cabecalho : cabecalhos) {
                    System.out.print(cabecalho + " ");
                }
                System.out.println();

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

                        // Exibe os valores para conferência
                        System.out.println("Ano: " + ano);
                        System.out.println("Mês: " + mes);
                        System.out.println("Empresa: " + empresa);
                        System.out.println("Origem: " + origem);
                        System.out.println("Destino: " + destino);
                        System.out.println("Tarifa: " + tarifa);
                        System.out.println("Assentos: " + assent);
                        System.out.println();
                    } else {
                        System.out.println("Linha com número de colunas inesperado: " + linha);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
