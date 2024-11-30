package br.com.aula.produtos;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Classe principal que oferece uma interface de linha de comando (CLI) para o gerenciamento de produtos.
 * O usuário pode escolher entre várias opções para inserir, atualizar, deletar ou visualizar dados na tabela de produtos.
 */
public class ProdutosCLI {

    // Scanner para capturar a entrada do usuário
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        // Loop principal que exibe o menu e processa as escolhas do usuário
        while (true) {
            exibirMenu();  // Exibe o menu de opções
            int opcao = scanner.nextInt();  // Captura a opção escolhida
            scanner.nextLine();  // Consome a linha de quebra de entrada

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case 1: // Inserir dados
                    Inserir.main(args);  // Chama o método principal da classe Inserir para adicionar um produto
                    break;
                case 2: // Atualizar dados
                    Atualizar.main(args);  // Chama o método principal da classe Atualizar para modificar um produto
                    break;
                case 3: // Deletar dados
                    Deletar.main(args);  // Chama o método principal da classe Deletar para excluir um produto
                    break;
                case 4: // Ler dados
                    Select.main(args);  // Chama o método principal da classe Select para visualizar os produtos
                    break;

                case 0: // Sair
                    System.out.println("Saindo...");  // Exibe uma mensagem de despedida e encerra o programa
                    return;  // Encerra o loop e o programa
                default: // Caso o usuário insira uma opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Exibe o menu de opções para o usuário.
     * O menu permite ao usuário escolher entre inserir, atualizar, deletar ou ler dados de produtos.
     */
    private static void exibirMenu() {
        // Tenta estabelecer uma conexão com o banco de dados antes de exibir o menu
        Connection conexao = ConexaoDB.conectar();
        
        // Se a conexão for bem-sucedida, exibe o menu
        if (conexao != null) {
            System.out.println("\n=== Gerenciamento da Tabela de Produtos ===");
            System.out.println("1 - Inserir Dados");
            System.out.println("2 - Atualizar Dados");
            System.out.println("3 - Deletar Dados");
            System.out.println("4 - Ler Dados");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
        } else {
            // Se a conexão não for bem-sucedida, exibe um erro
            System.err.println("Erro ao conectar ao banco de dados. Não é possível exibir o menu.");
        }
    }
}
