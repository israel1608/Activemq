/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filaveiculo;

/**
 *
 * @author israe
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.jms.JMSException;

public class H2ConnectionExample {
    String jdbcUrl = "jdbc:h2:tcp://localhost/~/test"; // URL de conexão com o banco de dados H2
    String username = "sa"; // Nome de usuário do banco de dados (se necessário)
    String password = "";
    
    public void start() {
        // Estabelecendo a conexão com o banco de dados e inserindo os dados do veículo
        try {
            Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            
        // Verificar se a tabela "veiculos" existe
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "veiculo", null);

        if (!tables.next()) {
            // Tabela não existe, criar a tabela "veiculos"
            String createTableQuery = "CREATE TABLE veiculo (id INT PRIMARY KEY AUTO_INCREMENT, nome_cliente VARCHAR(50), marca_modelo_veiculo VARCHAR(50), ano_modelo INT, valor_venda DOUBLE,cadastro VARCHAR(50))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
            statement.close();
            System.out.println("Tabela veiculos criada com sucesso.");
        }
   
        //connection.close();
        System.out.println("Conexão com o banco de dados fechada.");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
        }
   }
    
    public void insert(Veiculo par){
        String nomeCliente = par.getNomeCliente();
        String marcaModeloVeiculo = par.getMarcaModeloVeiculo();
        int anoModelo = par.getAnoModelo();
        double valorVenda = par.getValorVenda();
        Date data = par.getDataPublicacao();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String dataString = formato.format(data);
       // String dataPublicacao = par.getDataPublicacao();

        // Estabelecendo a conexão com o banco de dados e inserindo os dados do veículo
        try {
            Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");

            // Preparando a declaração SQL para inserção de dados
            String insertQuery = "INSERT INTO veiculo (nome_cliente, marca_modelo_veiculo, ano_modelo, valor_venda,cadastro) VALUES (?,?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
           // preparedStatement.setInt(1, 0);
            preparedStatement.setString(1, nomeCliente);
            preparedStatement.setString(2, marcaModeloVeiculo);
            preparedStatement.setInt(3, anoModelo);
            preparedStatement.setDouble(4, valorVenda);
            preparedStatement.setString(5, dataString);
            

            // Executando a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados do veículo foram inseridos com sucesso no banco de dados.");
            }

            // Fechando a conexão e o PreparedStatement
            preparedStatement.close();
            connection.close();
            System.out.println("Conexão com o banco de dados fechada.");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
        }

    }
}


