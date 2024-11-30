package br.com.aula.produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por estabelecer a conexão com o banco de dados.
 * Utiliza o driver JDBC para conectar-se a um banco MySQL.
 */
public class ConexaoDB {

    /**
     * Método estático para conectar ao banco de dados.
     *
     * @return Objeto `Connection` que representa a conexão com o banco de dados.
     *         Retorna `null` caso a conexão falhe.
     */
    public static Connection conectar() {
        // Objeto Connection para gerenciar a conexão com o banco
        Connection conexao = null;

        try {
            // URL do banco de dados, incluindo endereço, porta e nome do schema
            String url = "jdbc:mysql://localhost:3306/produto_db";
            // Usuário para autenticação no banco
            String usuario = "root";
            // Senha do usuário para autenticação
            String senha = "HLmNquScxu66ev3";

            // Estabelece a conexão com o banco de dados usando DriverManager
            conexao = DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            // Captura exceções relacionadas a SQL e exibe uma mensagem de erro
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        // Retorna a conexão estabelecida ou null em caso de falha
        return conexao;
    }
}
