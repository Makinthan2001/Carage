package classes;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vehicle {
    private String vehicleNo;
    private String owner;
    private String type;
    private String model;
    private int vehicle_id;
    private String brand;
    private String contact_no;
    private LocalDateTime created_at;


    public int getVehicle_id() {
        return vehicle_id;
    }

    public String getBrand() {
        return brand;
    }

    public String getContact_no() {
        return contact_no;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    public Vehicle() {
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public List<Vehicle> getAllVehicles(Connection con){
        List<Vehicle> vehicle=new ArrayList<>();
        try {
            String query="SELECT * FROM vehicles";
            PreparedStatement pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Vehicle v=new Vehicle();
                v.setVehicle_id(rs.getInt("vehicle_id"));
                v.setVehicleNo(rs.getString("vehicle_number"));
                v.setOwner(rs.getString("owner_name"));
                v.setType(rs.getString("vehicle_type"));
                v.setModel(rs.getString("model"));
                vehicle.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicle;
    }
    
    public boolean updateDetails(Connection con){
        try {
            String query="UPDATE vehicles SET owner_name = ?, vehicle_number = ?, vehicle_type = ?, model = ? WHERE vehicle_id = ?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, owner);
            pstmt.setString(2, vehicleNo);
            pstmt.setString(3, type);
            pstmt.setString(4, model);
            pstmt.setInt(5, vehicle_id);
            return pstmt.executeUpdate()>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteVehicle(Connection con){
        String query="DELETE FROM vehicles WHERE vehicle_id=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);            
            pstmt.setInt(1, vehicle_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addNewVehicle(Connection con){
        
        String query = "INSERT INTO vehicles(owner_name, vehicle_number, vehicle_type, brand, model, contact_number) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);            
            pstmt.setString(1, owner);
            pstmt.setString(2, vehicleNo);
            pstmt.setString(3, type);
            pstmt.setString(4, brand);
            pstmt.setString(5, model);
            pstmt.setString(6, contact_no);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
