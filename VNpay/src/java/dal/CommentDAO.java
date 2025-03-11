
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;

/**
 *
 * @author Asus
 */
public class CommentDAO {

    private final DBContext db;

    public CommentDAO() throws Exception {
        db = new DBContext();
    }

    public void add(int dish_id, int customer_id, String comment_content, int order_id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Comments (dish_id, customer_id, comment_content, comment_date,"
                    + "order_id) VALUES (?, ?, ?, ?,?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setInt(1, dish_id);
            ps.setInt(2, customer_id);
            ps.setString(3, comment_content);

            // Lấy ngày và giờ hiện tại
            Date currentDate = new Date();
            Timestamp timestamp = new Timestamp(currentDate.getTime());

            ps.setTimestamp(4, timestamp); // Đặt thời gian vào trường comment_date
            ps.setInt(5, order_id);
            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
//--------------get list comment by dish_id-------------

    public List<Comment> getCommentByDishId(int dish_id) {
        List<Comment> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT c.comment_id, c.dish_id, c.customer_id, c.comment_content, c.comment_date, cu.username\n"
                    + "FROM Comments c\n"
                    + "LEFT JOIN Customer cu ON c.customer_id = cu.customer_id\n"
                    + "WHERE c.dish_id = ?;";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, dish_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();

                comment.setComment_id(rs.getInt("comment_id"));
                comment.setDish_id(rs.getInt("dish_id"));
                comment.setCustomer_id(rs.getInt("customer_id"));
                comment.setComment_content(rs.getString("comment_content"));
                comment.setComment_date(rs.getTimestamp("comment_date"));
                comment.setUsername(rs.getString("username"));
                list.add(comment);
            }

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public Comment getCommentByOrderId(int order_id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT c.comment_id, c.dish_id, c.customer_id, c.comment_content, c.comment_date, cu.username, c.order_id\n" +
"                    FROM Comments c\n" +
"                  LEFT JOIN Customer cu ON c.customer_id = cu.customer_id\n" +
"                  WHERE c.order_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();

                comment.setComment_id(rs.getInt("comment_id"));
                comment.setDish_id(rs.getInt("dish_id"));
                comment.setCustomer_id(rs.getInt("customer_id"));
                comment.setComment_content(rs.getString("comment_content"));
                comment.setComment_date(rs.getTimestamp("comment_date"));
                comment.setUsername(rs.getString("username"));
                comment.setOrder_id(rs.getInt("order_id"));
                return comment;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi ở đây nếu cần thiết, ví dụ: throw một ngoại lệ tùy chỉnh
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên trong khối finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
