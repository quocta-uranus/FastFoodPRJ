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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;

/**
 *
 * @author Asus
 */
public class StaffDAO {

    private final DBContext db;

    public StaffDAO() throws Exception {
        db = new DBContext();
    }

    // Get staff
    public Staff getStaff(String username, String password) {

        try {
            String sql = "SELECT * FROM Staff WHERE username = ? AND password = ? ;";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            Staff staff = new Staff();

            while (rs.next()) {
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setStore_id(rs.getInt("store_id"));

                // return lap tuc!
                return staff;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // add staff
    public void addStaff(String username, String password, String role, int store_id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Staff (username, password, role, store_id) VALUES (?, ?, ?,?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setInt(4, store_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //get all staff
    public ArrayList<Staff> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT staff_id, username, password, role, store_id FROM Staff";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Staff> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                staff.setStore_id(rs.getInt("store_id"));

                list.add(staff);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    public Staff getStaffByUsername(String username) {

        try {
            String sql = "SELECT * FROM Staff WHERE username = ? ;";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Staff staff = new Staff();

            while (rs.next()) {
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));

                // return lap tuc!
                return staff;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void delete(int staff_id) {
        try {

            String sqlCellphone = "DELETE FROM Staff WHERE staff_id = ?;";
            Connection connection = null;

            connection = db.getConnection();
            try ( PreparedStatement ps = connection.prepareStatement(sqlCellphone)) {
                ps.setInt(1, staff_id);
                ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int staff_id,  String password) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "  update Staff\n"
                    + "  set password = ?\n"
                    + "  where staff_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, password);
            ps.setInt(2, staff_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
