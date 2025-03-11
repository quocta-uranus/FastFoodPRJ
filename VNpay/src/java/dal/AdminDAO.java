/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.dal;

import connectDB.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;

public class AdminDAO extends DBContext {
 private final DBContext db;

    public AdminDAO() throws Exception {
        db = new DBContext();
    }
    // Get Admin
    public Admin getAdmin(String username, String password) {
        
        try {
            String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?;";
             Connection connection = null;
            
            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            Admin admin = new Admin();

            while (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
               
                
                // return lap tuc!
                return admin;
            }

            rs.close();
            ps.close();      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
