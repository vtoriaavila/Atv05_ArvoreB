import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import dados.Elemento;
import dados.readCsv;
import tree.*;

public class Main {
    // static readCsv ler = new readCsv();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arvoreB<Elemento> bTree = new arvoreB<>(3); // Cria uma árvore B de ordem 3
        List<String> arquivosCsv = new ArrayList<>();


        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("src/dados/anac_174932488"), "*.CSV")) {
            for (Path entry : stream) {
                arquivosCsv.add(entry.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        arquivosCsv.add("src/dados/anac_174932488/202406.CSV");

        // Ler e inserir elementos de cada arquivo CSV
//        for (String arquivoCsv : arquivosCsv) {
//            List<Elemento> elementos = readCsv.lerCsv(arquivoCsv);
//            for (Elemento elemento : elementos) {
//                bTree.inserir(elemento); // Insere o elemento na árvore
//            }
//        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir um valor");
            System.out.println("2. Calcular média");
            System.out.println("3. Imprimir a árvore");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    for (String arquivoCsv : arquivosCsv) {
                        List<Elemento> elementos = readCsv.lerCsv(arquivoCsv);
                        for (Elemento elemento : elementos) {
                            bTree.inserir(elemento); // Insere o elemento na árvore
                        }
                    }
                    System.out.println("Valores inseridos com sucesso.");
                    break;

                case 2:
                    System.out.println("Calculo Media");
                    System.out.println("1- Media geral");
                    System.out.println("2- Media por empresa");
                    System.out.println("3- Media por tempo");
                    int escolha = scanner.nextInt();
                    switch (escolha) {
                        case 1:
                            bTree.printarMediaG();
                            break;

                        case 2:
                            bTree.printarMediaEmp();
                            break;

                        case 3:
                            bTree.printarMediaTemp();
                            break;

                    };

                    break;

                case 3:

                    System.out.println("Imprimindo a árvore:");
                    bTree.printarArvore();


                    break;


                case 4:

                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
