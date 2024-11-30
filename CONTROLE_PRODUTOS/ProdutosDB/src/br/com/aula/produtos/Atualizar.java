package br.com.aula.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Atualizar {
    public static void main(String[] args) {
        // Scanner para entrada de dados pelo usuário
        Scanner scan = new Scanner(System.in);

        // Estabelece conexão com o banco de dados utilizando a classe ConexaoDB
        Connection conexao = ConexaoDB.conectar();

        // Verifica se a conexão foi estabelecida com sucesso
        if (conexao != null) {
            // Query SQL para atualizar os dados na tabela 'produtos_tb'
            String sql = "UPDATE produtos_tb SET nome = ?, precoCusto = ?, precoVenda = ?, isAlimento = ?, dataValidade = ?, infoNutricionais = ?, tamanho = ?, cor = ?, material = ? WHERE id = ?";
            
            try {
                // Prepara a instrução SQL para evitar injeção de SQL
                PreparedStatement stmt = conexao.prepareStatement(sql);

                // Solicita o ID do produto que será atualizado
                System.out.print("Informe o ID do Produto: ");
                int id = scan.nextInt(); // Lê o ID do produto
                scan.nextLine(); // Consome a quebra de linha pendente

                // Solicita o novo nome do produto
                System.out.print("Digite o NOME do produto novo: ");
                String nome = scan.nextLine();

                // Solicita o preço de custo do produto
                System.out.print("Digite o preço de CUSTO do produto novo: ");
                int precoCusto = scan.nextInt();
                scan.nextLine();

                // Solicita o preço de venda do produto
                System.out.print("Digite o preço de VENDA do produto novo: ");
                int precoVenda = scan.nextInt();
                scan.nextLine();

                // Solicita se o produto é um alimento
                System.out.println("O produto é um alimento?\nSIM [S]\nNAO [N]");
                String val = scan.nextLine();
                boolean isAlimento = val.equalsIgnoreCase("S");

                // Variáveis para armazenar dados específicos de alimentos ou outros produtos
                String dataValidade = null;
                String infoNutricionais = null;
                String tamanho = null, cor = null, material = null;

                // Caso o produto seja um alimento, solicita informações adicionais
                if (isAlimento) {
                    System.out.print("Digite a DATA DE VALIDADE do produto novo (yyyy-MM-dd): ");
                    dataValidade = scan.nextLine();

                    System.out.print("Digite as INFORMAÇÕES NUTRICIONAIS do produto novo: ");
                    infoNutricionais = scan.nextLine();
                } else {
                    // Caso contrário, solicita informações de produtos não alimentícios
                    System.out.print("Digite o TAMANHO do produto novo (PP - P - M - G - GG): ");
                    tamanho = scan.nextLine();

                    System.out.print("Digite a COR do produto novo: ");
                    cor = scan.nextLine();

                    System.out.print("Digite o MATERIAL do produto novo: ");
                    material = scan.nextLine();
                }

                // Define os valores dos parâmetros na instrução SQL
                stmt.setString(1, nome); // Nome do produto
                stmt.setInt(2, precoCusto); // Preço de custo
                stmt.setInt(3, precoVenda); // Preço de venda
                stmt.setBoolean(4, isAlimento); // Indica se é alimento
                stmt.setString(5, dataValidade); // Data de validade (para alimentos)
                stmt.setString(6, infoNutricionais); // Informações nutricionais (para alimentos)
                stmt.setString(7, tamanho); // Tamanho (para produtos não alimentícios)
                stmt.setString(8, cor); // Cor (para produtos não alimentícios)
                stmt.setString(9, material); // Material (para produtos não alimentícios)
                stmt.setInt(10, id); // ID do produto a ser atualizado

                // Executa a atualização no banco de dados
                stmt.executeUpdate();
                System.out.println("Dados atualizados com sucesso!");
            } catch (SQLException e) {
                // Exibe mensagem de erro caso ocorra uma exceção durante a execução da query
                System.err.println("Erro ao atualizar dados: " + e.getMessage());
            }
        }
    }
}
