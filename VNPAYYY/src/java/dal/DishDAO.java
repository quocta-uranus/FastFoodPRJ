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
import model.Dish;

public class DishDAO extends DBContext {

    private final DBContext db;

    public DishDAO() throws Exception {
        db = new DBContext();
    }

    public void add(String name, String infor, int price, String image, String comment, String typeOfDish) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Dish (name, infor, price, image, comment, typeOfDish) VALUES (?, ?, ?, ?, ?, ?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, name);
            ps.setString(2, infor);
            ps.setInt(3, price);
            ps.setString(4, image);
            ps.setString(5, comment);
            ps.setString(6, typeOfDish);

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

    public ArrayList<Dish> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT dish_id, name, infor, price, image, comment, typeOfDish FROM Dish";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Dish> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setDish_id(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setInfor(rs.getString("infor"));
                dish.setPrice(rs.getInt("price"));
                dish.setImage(rs.getString("image"));
                dish.setComment(rs.getString("comment"));
                dish.setTypeOfDish(rs.getString("typeOfDish"));
                list.add(dish);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    public void delete(int dish_id) {
        try {

            String sqlCellphone = "DELETE FROM Dish WHERE dish_id = ?;";
            Connection connection = null;

            connection = db.getConnection();
            try ( PreparedStatement ps = connection.prepareStatement(sqlCellphone)) {
                ps.setInt(1, dish_id);
                ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Dish getDish(int dish_id) {
        try {
            String sql = "select dish_id, name,infor,price,image,comment,typeOfDish from Dish where dish_id = ?";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dish_id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Dish dish = new Dish();

            dish.setDish_id(rs.getInt("dish_id"));
            dish.setName(rs.getString("name").trim());
            dish.setInfor(rs.getString("infor").trim());
            dish.setPrice(rs.getInt("price"));
            dish.setImage(rs.getString("image").trim());
            dish.setComment(rs.getString("comment").trim());
            dish.setTypeOfDish(rs.getString("typeOfDish").trim());

            rs.close();
            ps.close();

            return dish;

        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Dish getDishDetail(int dish_id) {
        try {
            String sql = "select dish_id, name,infor,price,image,comment,typeOfDish from Dish where dish_id = ?";
            Connection connection = null;

            connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dish_id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Dish dish = new Dish();
            dish.setDish_id(rs.getInt("dish_id"));
            dish.setName(rs.getString("name").trim());
            dish.setInfor(rs.getString("infor").trim());
            dish.setPrice(rs.getInt("price"));
            dish.setImage(rs.getString("image").trim());
            dish.setComment(rs.getString("comment").trim());
            dish.setTypeOfDish(rs.getString("typeOfDish").trim());

            rs.close();
            ps.close();

            return dish;
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    // seach by type

    public ArrayList<Dish> getDishByType(String type) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT dish_id, name, infor, price, image, comment, typeOfDish FROM Dish where typeOfDish = ? ";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ArrayList<Dish> list = new ArrayList<>();

            rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setDish_id(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setInfor(rs.getString("infor"));
                dish.setPrice(rs.getInt("price"));
                dish.setImage(rs.getString("image"));
                dish.setComment(rs.getString("comment"));
                dish.setTypeOfDish(rs.getString("typeOfDish"));
                list.add(dish);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }
    // Update

    public void update(int dish_id, String name, String infor, int price, String image, String comment, String typeOfDish) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE Dish "
                    + "SET name = ?, infor = ?, price = ?, image = ?, comment = ?, typeOfDish = ? "
                    + "WHERE dish_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, name);
            ps.setString(2, infor);
            ps.setInt(3, price);
            ps.setString(4, image);
            ps.setString(5, comment);
            ps.setString(6, typeOfDish);
            ps.setInt(7, dish_id);

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

    // search name
    public ArrayList<Dish> searchByName(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT dish_id, name, infor, price, image, comment, typeOfDish\n"
                    + "FROM Dish\n"
                    + "WHERE LOWER(name) LIKE ?";

            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Dish> list = new ArrayList<>();
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setDish_id(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setInfor(rs.getString("infor"));
                dish.setPrice(rs.getInt("price"));
                dish.setImage(rs.getString("image"));
                dish.setComment(rs.getString("comment"));
                dish.setTypeOfDish(rs.getString("typeOfDish"));
                list.add(dish);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    //----- asc by price
    public ArrayList<Dish> getDishByPrice(String compare) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT dish_id, name, infor, price, image, comment, typeOfDish FROM Dish "
                    + "ORDER BY price " + compare;
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Dish> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setDish_id(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setInfor(rs.getString("infor"));
                dish.setPrice(rs.getInt("price"));
                dish.setImage(rs.getString("image"));
                dish.setComment(rs.getString("comment"));
                dish.setTypeOfDish(rs.getString("typeOfDish"));
                list.add(dish);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

}
