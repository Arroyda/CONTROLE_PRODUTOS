package br.com.aula.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por ler e exibir todos os produtos da tabela 'produtos_tb' no banco de dados.
 * Utiliza uma consulta SQL para selecionar os dados e imprime as informações de cada produto.
 */
public class Select {
    public static void main(String[] args) {

        // Tenta estabelecer a conexão com o banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {  // Se a conexão for bem-sucedida
                // SQL para selecionar todos os produtos da tabela 'produtos_tb'
                String sql = "SELECT id, nome, isAlimento, dataValidade, infoNutricionais, tamanho, cor, material FROM produtos_tb";

                // Prepara a consulta SQL e executa
                try (PreparedStatement stmt = conexao.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    // Exibe uma mensagem de cabeçalho
                    System.out.println("Lista de produtos:");

                    // Itera sobre os resultados retornados pela consulta
                    while (rs.next()) {
                        // Obtém os dados de cada produto a partir do ResultSet
                        int id = rs.getInt("id");
                        String nome = rs.getString("nome");
                        boolean isAlimento = rs.getBoolean("isAlimento");
                        String dataValidade = rs.getString("dataValidade");
                        String infoNutricionais = rs.getString("infoNutricionais");
                        String tamanho = rs.getString("tamanho");
                        String cor = rs.getString("cor");
                        String material = rs.getString("material");

                        // Exibe as informações do produto
                        System.out.println("ID: " + id);
                        System.out.println("Nome: " + nome);
                        System.out.println("É alimento: " + (isAlimento ? "Sim" : "Não"));

                        // Se for um alimento, exibe as informações relacionadas
                        if (isAlimento) {
                            System.out.println("Data de Validade: " + dataValidade);
                            System.out.println("Informações Nutricionais: " + infoNutricionais);
                        } else {  // Se não for um alimento, exibe as informações de tamanho, cor e material
                            System.out.println("Tamanho: " + tamanho);
                            System.out.println("Cor: " + cor);
                            System.out.println("Material: " + material);
                        }
                        // Separa cada produto com uma linha de divisão
                        System.out.println("------------");
                    }
                }
            }
        } catch (SQLException e) {  // Captura e exibe erros de SQL
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }
    }
}
