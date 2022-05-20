package local.jamily.bookstoreweb.model.dao;

import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.jamily.bookstoreweb.model.bean.Book;
/**
 *
 * @author devsys-a
 */
public class BookDAO {
    private static final String SQL_INSERT = "INSERT INTO book(titulo, "
            + "autor, preco) "
            + "VALUES (?,?,?)";
    
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    private static final String SQL_SELECT_ID = "SELECT * FROM book "
            + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE book SET titulo = ?,"
            +"autor = ?, preco = ? "
            + "WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM book WHERE id = ? ";
    
    /**
    * Insere um usuario na base de dados Produto
    * @param p
    */
    
    public void create (Book p){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getAutor());
            stmt.setDouble(3, p.getPreco());
            
            //Executa a Query
            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null, "inclusao: " + auxRetorno);
        } catch (SQLException  sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally{
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Retorna todos os requisitos da tabela produto
     * @return
     */
    
    public List<Book> getResults(){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        List<Book> listaBook = null;
        
        try {
            //Prepara a string de SELECT e executa a query
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dados do ResultsSet rs, converte em Produto e 
            //adiciona na lista de retorno
            listaBook = new ArrayList<>();
            
            while (rs.next()) {
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("autor"));
                b.setPreco(rs.getDouble("preco"));
                listaBook.add(b);
            }
        } catch (SQLException err) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, err);
        }
        
        return listaBook;
    }
    
    public Book getResultById(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                b = new Book();
                b.setId((rs.getInt("id")));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("autor"));
                b.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException err) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, err);
        }finally{
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        
        return b;
    }
    /**
     * Atualiza um registro na tabela produto
     * @param b Produto a ser atualizado
     */
    public void update(Book b){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, b.getTitulo());
            stmt.setString(2, b.getAutor());
            stmt.setDouble(3, b.getPreco());
            stmt.setInt(4, b.getId());
            
            //Executa Query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno );
        } catch (SQLException err) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, err);
        }finally{
            //Encerra a conexão com o banco e o statement
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Exclui um produto com base do ID fornecido
     * @param id Identificação do produto
     */
    
    public void delete(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            //Executa Query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null, "Delete! " + auxRetorno);
        } catch (SQLException err) {
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, err);
            
        }finally{
            MySQLConnection.closeConnection(conn, stmt);
        }
        
    }
}
