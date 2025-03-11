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
import model.Customer;

public class CustomerDAO extends DBContext {

    private final DBContext db;

    public CustomerDAO() throws Exception {
        db = new DBContext();
    }

    public void add(String username, String password, String email, String phoneNumber,
            String role, String customer_name, String customer_address) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Customer (username, password, email, "
                    + "phoneNumber, role,customer_name,customer_address) VALUES (?, ?, ?, ?, ?,?,?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setString(5, role);
            ps.setString(6, customer_name);
            ps.setString(7, customer_address);
            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Customer> getAllCustomer() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT customer_id, username, password, email, phoneNumber, role FROM Customer";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Customer> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setRole(rs.getString("role"));
                list.add(customer);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    public Customer getCustomer(String username, String password) {

        try {
            String sql = "SELECT * FROM Customer WHERE username = ? AND password = ?;";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));

                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerUserName(String username) {

        try {
            String sql = "SELECT customer_id, username, email,password,phoneNumber,customer_address, customer_name FROM Customer WHERE username = ? ;";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getNString("customer_address"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setCustomer_name(rs.getString("customer_name"));

                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerEmail(String email) {

        try {
            String sql = "SELECT customer_id, username, password, email, phoneNumber, "
                    + "customer_address, role, customer_name FROM Customer WHERE email = ?;";

            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setUsername(rs.getString("username"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setPassword(rs.getString("password"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setRole(rs.getString("role"));
                customer.setEmail(rs.getString("email"));
                // return lap tuc!
                return customer;
            }

            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void editProfile(int customer_id, String password, String phoneNumber, String address, String customer_name) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "update Customer \n"
                    + "  set password = ?, phoneNumber = ?, customer_address = ?, customer_name= ? \n"
                    + "  where customer_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, password);
            ps.setString(2, phoneNumber);
            ps.setString(3, address);
            ps.setString(4, customer_name);

            ps.setInt(5, customer_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Customer getCustomer(int customer_id) {
        try {
            String sql = "select customer_id, username,password,email,phoneNumber,role,\n"
                    + "customer_name,customer_address from Customer where customer_id = ?";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Customer customer = new Customer();

            customer.setCustomer_id(rs.getInt("customer_id"));
            customer.setUsername(rs.getString("username").trim());
            customer.setPassword(rs.getString("password"));
            customer.setEmail(rs.getString("email"));
            customer.setCustomer_name(rs.getString("customer_name").trim());
            customer.setPhoneNumber(rs.getString("phoneNumber").trim());
            customer.setAddress(rs.getString("customer_address").trim());
            customer.setRole(rs.getString("role"));

            rs.close();
            ps.close();

            return customer;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //-----------------------------------------
    public void editPassword(int customer_id, String password) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "update Customer \n"
                    + "  set password = ? where customer_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, password);

            ps.setInt(2, customer_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
