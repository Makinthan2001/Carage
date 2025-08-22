
package classes;

import classes.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String gender;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public List<Customer> getAllCustomers(Connection con){
        List<Customer> customerList=new ArrayList<>();
        String query="SELECT * FROM customer";
        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Customer c=new Customer();
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setGender(rs.getString("gender"));
                c.setId(rs.getInt("id"));
                customerList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerList;
    }
    
    public boolean updateDetails(Connection con){
        String query="UPDATE customer SET name=?, address=?, phone=?, gender=? WHERE id=?";
        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setString(4, gender);
            pstmt.setInt(5, id);
            return pstmt.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteDetails(Connection con){
        String query="DELETE FROM customer WHERE id=?";
        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addCustomer(Connection con){
        String query="INSERT INTO customer (name, address, phone, gender) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setString(4, gender);
            return pstmt.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
