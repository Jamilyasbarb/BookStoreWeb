package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerencia a conexão com o banco de dados. Possui as instruções necessários para 
 * conectar ao banco.
 * @author Jamily
 * */
public class MySQLConnection {
    
    
    //define Strings de conexão com o banco

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = System.getenv("BANCO_URL");
    private static final String  USER = System.getenv("BANCO_USER");
    private static final String  PASS = System.getenv("BANCO_PASSWORD");
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE,null,ex);
            throw new RuntimeException("Erro na Conexão com BD.Verifique", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE,null,ex);
            throw new RuntimeException("Erro na carga do BD.Verifique", ex);
        }
    }
    
    /**
    *Fecha uma conexão
    @param conn
    */
    public static void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    /**
     * Fecha a conexão com a DB
     * @param conn
     * @param stmt 
     */
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        closeConnection(conn);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        closeConnection(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
