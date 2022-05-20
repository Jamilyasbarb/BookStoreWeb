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
import local.jamily.bookstoreweb.model.bean.User;
import local.jamily.bookstoreweb.model.bean.User;
import local.jamily.bookstoreweb.model.bean.User;
import local.jamily.bookstoreweb.model.bean.User;

public class UserDAO {
    
    private static final String SQL_INSERT = "INSERT INTO user(email, "
            + "password, fullname) "
            + "VALUES (?,?,?)";
    
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_AUTHENTIC = "SELECT fullname FROM user "
            + "WHERE email = ? and password = ?";
    private static final String SQL_SELECT_ID = "SELECT * FROM user "
            + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE user SET email = ?,"
            +"password = ?, fullname = ?,"
            + "WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ? ";
    
    public User checkLogin(String email, String password){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_AUTHENTIC);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
        
            
            if (rs.next()) {
               u = new User();
               u.setFullName(rs.getString("fullname"));
               u.setEmail(rs.getString("email"));
               u.setPassword(rs.getString("password"));                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return u;
    } 
    
     public void create (User u){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullName());
            
            //Executa a Query
            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "inclusao: " + auxRetorno);
        } catch (SQLException  sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally{
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
     
      public List<User> getResults(){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User b = null;
        List<User> listaUser = null;
         
        try {
            //Prepara a string de SELECT e executa a query
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dados do ResultsSet rs, converte em Produto e 
            //adiciona na lista de retorno
            listaUser = new ArrayList<>();
            
            while (rs.next()) {
                b = new User();
                b.setId(rs.getInt("id"));
                b.setEmail(rs.getString("email"));
                b.setPassword(rs.getString("password"));
                b.setFullName(rs.getString("fullname"));
                listaUser.add(b);
            }
        } catch (SQLException err) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, err);
        }
        
        return listaUser;
    }
      
      public User getResultById(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                u = new User();
                u.setId((rs.getInt("id")));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullName(rs.getString("fullname"));
            }
        } catch (SQLException err) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, err);
        }finally{
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        
        return u;
    }
       public void update(User b){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, b.getEmail());
            stmt.setString(2, b.getPassword());
            stmt.setString(3, b.getFullName());
            stmt.setInt(6, b.getId());
            
            //Executa Query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno );
        } catch (SQLException err) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, err);
        }finally{
            //Encerra a conexão com o banco e o statement
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
       
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
