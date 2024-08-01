import java.util.Scanner;

import dados.readCsv;
import tree.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arvoreB<Integer> bTree = new arvoreB<>(3); // Cria uma árvore B de ordem 3
        String arquivoCsv =  "src/dados/anac_174932488/202401.CSV";


        int[] values = {45, -10, 67, 2, 33, 99};
        for (int value : values) {
            bTree.inserir(value);
        }

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
                    System.out.print("Digite o valor a ser inserido: ");
                    int insertValue = scanner.nextInt();
                    bTree.inserir(insertValue);
                    System.out.println("Valor inserido com sucesso.");
                    break;

                case 2:
                    System.out.print("Digite o valor a ser buscado: ");
                    int searchValue = scanner.nextInt();
                    boolean found = bTree.buscar(searchValue);
                    if (found) {
                        System.out.println("Valor encontrado na árvore.");
                    } else {
                        System.out.println("Valor não encontrado na árvore.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o valor a ser removido: ");
                    int deleteValue = scanner.nextInt();
                    boolean pass = bTree.buscar(deleteValue);
                    if (pass) {
                        bTree.remover(deleteValue);
                        System.out.println("Valor deletado da árvore.");
                    } else {
                        System.out.println("Valor não encontrado na árvore, não é possível remover");
                    }
                    break;

                case 4:
                    System.out.println("Imprimindo a árvore:");
                    //bTree.printarArvore();
                    readCsv.lerCsv(arquivoCsv);
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
