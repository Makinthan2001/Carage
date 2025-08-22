
package classes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private String username;
    private String password;

    public User() {
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean userAuth(Connection con){
        try {
            PreparedStatement pstmt=con.prepareStatement("SELECT * FROM users WHERE username=?");
            pstmt.setString(1, username);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                if(password.equals(rs.getString("password"))){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}


