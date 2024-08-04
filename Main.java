import java.io.IOException;
import java.io.PrintWriter;
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
        String arquivoCsv =  "src/dados/anac_174932488/202402.CSV";
        List<Elemento> elementos = readCsv.lerCsv(arquivoCsv);
//        int[] values = {45, -10, 67, 2, 33, 99};
//        for (int value : values) {
//            bTree.inserir(value);
//        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir um valor");
            System.out.println("2. Buscar um valor");
            System.out.println("3. Remover um valor");
            System.out.println("4. Imprimir a árvore");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
//                    System.out.print("Digite o valor a ser inserido: ");
//                    Elemento insertValue = scanner.nextInt();
//                    bTree.inserir(insertValue);
                    for (Elemento elemento : elementos) {
                        bTree.inserir(elemento); // Insere o elemento na árvore
                    }
                    System.out.println("arvore inserida");
//                    System.out.println("Valor inserido com sucesso.");
                    break;

                case 2:
//                    System.out.print("Digite o valor a ser buscado: ");
//                    int searchValue = scanner.nextInt();
//                   // Elemento elementoBuscado = new Elemento(searchValue);
//                    boolean found = bTree.buscar(searchValue);
//                    if (found) {
//                        System.out.println("Valor encontrado na árvore.");
//                    } else {
//                        System.out.println("Valor não encontrado na árvore.");
//                    }
                    break;

                case 3:
//                    System.out.print("Digite o valor a ser removido: ");
//                    int deleteValue = scanner.nextInt();
//                    boolean pass = bTree.buscar(deleteValue);
//                    if (pass) {
//                        bTree.remover(deleteValue);
//                        System.out.println("Valor deletado da árvore.");
//                    } else {
//                        System.out.println("Valor não encontrado na árvore, não é possível remover");
//                    }
                    break;

                case 4:
                    System.out.println("Imprimindo a árvore:");
                    bTree.printarArvore();
//                    bTree.imprimirParaArquivo("src/dados/arquivoSaida.txt");

                    //ler.lerCsv(arquivoCsv);

                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
