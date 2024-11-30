package br.com.aula.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Classe responsável por deletar um registro do banco de dados com base no ID informado pelo usuário.
 */
public class Deletar {
    public static void main(String[] args) {
        // Scanner para capturar a entrada do usuário
        Scanner scan = new Scanner(System.in);

        // Conecta ao banco de dados usando a classe ConexaoDB
        Connection conexao = ConexaoDB.conectar();

        // Verifica se a conexão foi estabelecida com sucesso
        if (conexao != null) {
            // Query SQL para deletar um registro na tabela 'alunos' com base no ID
            String sql = "DELETE FROM alunos WHERE id = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                // Solicita ao usuário o ID do registro a ser excluído
                System.out.print("Digite o ID a ser excluído: ");
                int id = scan.nextInt(); // Captura o ID do usuário

                // Define o valor do parâmetro na query SQL
                stmt.setInt(1, id);

                // Executa a instrução SQL e retorna o número de registros afetados
                int rowsAffected = stmt.executeUpdate();

                // Verifica se algum registro foi excluído
                if (rowsAffected > 0) {
                    System.out.println("Dados deletados com sucesso!");
                } else {
                    System.out.println("Nenhum dado foi encontrado para excluir.");
                }
            } catch (SQLException e) {
                // Exibe uma mensagem de erro em caso de problemas ao executar a query
                System.err.println("Erro ao deletar dados: " + e.getMessage());
            } finally {
                // Garante que a conexão com o banco seja fechada
                try {
                    if (conexao != null) conexao.close();
                } catch (SQLException e) {
                    // Exibe uma mensagem de erro em caso de falha ao fechar a conexão
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}
