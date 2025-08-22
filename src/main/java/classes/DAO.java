package classes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    private String driver="com.mysql.cj.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/garage?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String dbuser="root";
    private String psw="";
    
    public Connection getConnection(){
        Connection con=null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url, dbuser, psw);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
