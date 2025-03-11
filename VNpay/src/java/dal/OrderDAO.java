/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.dal;

import com.entity.Cart;
import com.entity.CartItem;
import connectDB.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.DailyTotal;
import model.MonthCount;
import model.MonthlyTotal;
import model.Order;
import model.OrderDetail;
import model.YearCount;

/**
 *
 * @author Asus
 */
public class OrderDAO {

    private final DBContext db;

    public OrderDAO() throws Exception {
        db = new DBContext();
    }
//  ---------------------add order to cart (for customer)-----------------------

    public void addOrder(Customer c, Cart cart, int store_id,
            String receiver_name, String receiver_phone, String receiver_address, String pStatus) {
        LocalDate currDate = LocalDate.now();
        String date = currDate.toString();
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            String sql = "Insert into [Order] (customer_id,store_id,total_price,"
                    + "date,status,receiver_name, receiver_phone,receiver_address,pStatus) values(?,?,?,?,?,?,?,?,?)";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getCustomer_id());
            ps.setInt(2, store_id);
            ps.setDouble(3, cart.getTotal());
            ps.setString(4, date);
            ps.setString(5, "Pending");
            ps.setString(6, receiver_name);
            ps.setString(7, receiver_phone);
            ps.setString(8, receiver_address);
            ps.setString(9, pStatus);
            ps.executeUpdate();
            String sql1 = "Select top 1  order_id from [Order] order by order_id desc";
            ps1 = connection.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("order_id");
                for (CartItem i : cart.getCartItems()) {
                    String sql2 = "insert into [OrderDetail] values (?,?,?,?,?,?) ";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getDish().getDish_id());
                    st2.setInt(3, i.getQuantity());
                    st2.setInt(4, i.getDish().getPrice());
                    st2.setString(5, i.getDish().getName());
                    st2.setString(6, i.getDish().getImage());
                    st2.executeUpdate();
                }
            }

        } catch (Exception ex) {

        }
    }
//------------------------ get item by order id--------------------------

    public List<OrderDetail> getItem(int id) {
        List<OrderDetail> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "  SELECT od.order_id, od.dish_id, od.numberOfDish, d.name, od.price\n"
                    + "FROM OrderDetail od\n"
                    + "JOIN Dish d ON od.dish_id = d.dish_id\n"
                    + "WHERE od.order_id = ?;";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setOrder_id(rs.getInt("order_id"));
                orderDetail.setProduct_id(rs.getInt("dish_id"));
                orderDetail.setQuantity(rs.getInt("numberOfDish"));
                orderDetail.setPrice(rs.getInt("price"));
                orderDetail.setName(rs.getString("name"));
                list.add(orderDetail);
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
//---------------- get all order ------------------

    public ArrayList<Order> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = " Select order_id, customer_id, store_id, total_price, date, status from [KFCStore].[dbo].[Order]";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            ArrayList<Order> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                list.add(order);
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
//--------------------------- get item by order_id --------------------------

    public ArrayList<OrderDetail> getItemById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select order_id, dish_id, numberOfDish, price,name, image from OrderDetail where order_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ArrayList<OrderDetail> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_id(rs.getInt("order_id"));
                orderDetail.setProduct_id(rs.getInt("dish_id"));
                orderDetail.setQuantity(rs.getInt("numberOfDish"));
                orderDetail.setPrice(rs.getInt("price"));
                orderDetail.setName(rs.getString("name"));
                orderDetail.setImage(rs.getString("image"));
                list.add(orderDetail);
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
//--------------------- get name of dish by order_id --------------------

    public String getNameById(int dish_id, int order_id) {
        try {
            String name = null;
            String sqlCellphone = "SELECT d.name\n"
                    + "FROM OrderDetail od\n"
                    + "JOIN Dish d ON od.dish_id = d.dish_id\n"
                    + "where d.dish_id = ? and od.order_id = ?";

            Connection connection = null;
            ResultSet rs = null;

            connection = db.getConnection();
            try ( PreparedStatement ps = connection.prepareStatement(sqlCellphone)) {
                ps.setInt(1, dish_id);
                ps.setInt(2, order_id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("d.name");

                }
                return name;
            } catch (SQLException ex) {
                Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DishDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//-------------- set status (for admin)----------------------------

    public void resetStatus(int order_id, String status) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "update [KFCStore].[dbo].[Order] set status = ? where order_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, status);
            ps.setInt(2, order_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
//----------------------------- display order by status (pending, succeed, cancel)---------------

    public List<Order> getOrderByStatus(String status) {
        try ( Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement("SELECT order_id, customer_id, store_id, total_price, date, status FROM [KFCStore].[dbo].[Order] WHERE status=?")) {
            ps.setString(1, status);

            try ( ResultSet rs = ps.executeQuery()) {
                List<Order> list = new ArrayList<>();

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setCustomer_id(rs.getInt("customer_id"));
                    order.setStore_id(rs.getInt("store_id"));
                    order.setTotalmoney(rs.getInt("total_price"));
                    order.setDate(rs.getString("date"));
                    order.setStatus(rs.getString("status"));
                    list.add(order);
                }

                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
//----------------------getOrderByStatusID (order history)-----------------

    public List<Order> getOrderByStatusID(String status, int customer_id) {
        try ( Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement("SELECT order_id, customer_id, store_id,"
                + " total_price, date, status FROM [KFCStore].[dbo].[Order] WHERE status=? and customer_id =?")) {
            ps.setString(1, status);
            ps.setInt(2, customer_id);

            try ( ResultSet rs = ps.executeQuery()) {
                List<Order> list = new ArrayList<>();

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setCustomer_id(rs.getInt("customer_id"));
                    order.setStore_id(rs.getInt("store_id"));
                    order.setTotalmoney(rs.getInt("total_price"));
                    order.setDate(rs.getString("date"));
                    order.setStatus(rs.getString("status"));
                    list.add(order);
                }

                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
//------------------------getOrderByCusId (status: pending, cancel, succeed for order tracking)----------------

    public ArrayList<Order> getOrderByCusId(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select order_id, store_id, total_price, date, status from [KFCStore].[dbo].[Order] where customer_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ArrayList<Order> list = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                list.add(order);
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
//------------------ cancel order-----------------------------

//--------------------------------- total money (revenue) for all store------------------
    public int sumOrder() {
        String sql = "SELECT SUM(total_price) as total_succeed_price\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE pStatus = 'Paid'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql);  ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("total_succeed_price");
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // khong tim thay ket qua
    }
//--------------------- total revenue by store-------------------

    public int sumOrderByStore(int store_id) {
        String sql = "SELECT SUM(total_price) as total_succeed_price\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE pStatus = 'Paid' and store_id = ?";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, store_id); // Gán tham số store_id
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_succeed_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }
    // sum cancel order
     public int sumOrderCus(int customer_id, String status) {
        String sql = "SELECT SUM(total_price) as total_succeed_price\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE status = ? and customer_id = ?";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, customer_id); // Gán tham số store_id
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_succeed_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }
//---------------------------list succeed order by store (store statistic)-------------

    public ArrayList<Order> getOrderByStoreId(int store_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "Select order_id, total_price, date, customer_id, store_id,"
                    + " status,  receiver_name, receiver_phone,receiver_address, pStatus"
                    + " from [KFCStore].[dbo].[Order] where store_id = ? and status  = 'Succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, store_id);

            ArrayList<Order> listDetail = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStore_id(rs.getInt("store_id"));
                order.setReceiver_phone(rs.getString("receiver_phone"));
                order.setReceiver_name(rs.getString("receiver_name"));
                order.setReceiver_address(rs.getString("receiver_address"));
                order.setStatus(rs.getString("status"));

                order.setPaymentStatus(rs.getString("pStatus"));
                listDetail.add(order);
            }

            return listDetail;
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

    //---------------------------------------------------------------
    public ArrayList<Order> getOrderByStore_idStatus(int store_id, String status) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "Select order_id, total_price, date, customer_id, store_id,"
                    + " status, receiver_name, receiver_phone,receiver_address, pStatus"
                    + " from [KFCStore].[dbo].[Order] where store_id = ? and status  = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, store_id);
            ps.setString(2, status);
            ArrayList<Order> listDetail = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStore_id(rs.getInt("store_id"));
                order.setReceiver_phone(rs.getString("receiver_phone"));
                order.setReceiver_name(rs.getString("receiver_name"));
                order.setReceiver_address(rs.getString("receiver_address"));
                order.setStatus(rs.getString("status"));
                order.setPaymentStatus(rs.getString("pStatus"));
                listDetail.add(order);
            }

            return listDetail;
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

    //----------get list order according to date admin-------------------
    public ArrayList<Order> getOrderByDate(String date) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id, customer_id, store_id, total_price, date, status "
                    + "FROM [KFCStore].[dbo].[Order] "
                    + "WHERE CONVERT(DATE, date) = ? AND status = 'Succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, date);

            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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

    //-------------------------------------------------------
    public ArrayList<Order> getOrderOfStoreByDate(String date, int store_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id, customer_id, store_id, total_price, date, status,"
                    + "pStatus "
                    + "FROM [KFCStore].[dbo].[Order] "
                    + "WHERE CONVERT(DATE, date) = ? AND status = 'Succeed' AND store_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, store_id);

            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));
                order.setPaymentStatus(rs.getString("pStatus"));
                listOrder.add(order);
            }

            return listOrder;
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

    //--------------------get order according to month-------------------
    public ArrayList<Order> getOrderByMonth(int month, int year) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND MONTH(date) = ? AND status = 'succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, month);
            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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

    //---------------------------------------------------------
    public ArrayList<Order> getOrderOfStoreByMonth(int month, int year, int store_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status,"
                    + "pStatus\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND MONTH(date) = ? AND status = 'Succeed' AND store_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, store_id);
            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));
                order.setPaymentStatus(rs.getString("pStatus"));

                listOrder.add(order);
            }

            return listOrder;
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
//------------------ get order according to year------------------

    public ArrayList<Order> getOrderByYear(int year) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND status = 'succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);

            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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
//----------------------------------------------------------

    public ArrayList<Order> getOrderOfStoreByYear(int year, int store_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status,"
                    + "pStatus\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND status = 'succeed' AND store_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, store_id);

            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));
                order.setPaymentStatus(rs.getString("pStatus"));
                listOrder.add(order);
            }

            return listOrder;
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
    //---------------------- total revenue by date--------------------

    public int sumOrderByDate(String date) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + "COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE CONVERT(DATE, date) = ? \n"
                + "AND status = 'Succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //---------------------------------------------------------------
    public int sumOrderOfStoreByDate(String date, int store_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + "COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE CONVERT(DATE, date) = ? \n"
                + "AND status = 'succeed' "
                + "AND store_id = ?";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            statement.setInt(2, store_id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //----------------------total revenue by month --------------------
    public int sumOrderByMonth(int month, int year) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE YEAR(date) = ? AND MONTH(date) = ? \n"
                + " AND status = 'succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            statement.setInt(2, month);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //-----------------------------------------------------
    public int sumOrderOfStoreByMonth(int month, int year, int store_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE YEAR(date) = ? AND MONTH(date) = ? \n"
                + " AND status = 'Succeed'"
                + "AND store_id = ?";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            statement.setInt(2, month);
            statement.setInt(3, store_id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //------------------------ revenue by year
    public int sumOrderByYear(int year) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + " FROM [KFCStore].[dbo].[Order]\n"
                + " WHERE YEAR(date) = ? \n"
                + "AND status = 'succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);

            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    public int sumOrderOfStoreByYear(int year, int store_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + " FROM [KFCStore].[dbo].[Order]\n"
                + " WHERE YEAR(date) = ? \n"
                + "AND status = 'succeed'"
                + "AND store_id = ?";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            statement.setInt(2, store_id);

            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //---------------------for customer---------------------
    //----------get list order according to date-------------------
    public ArrayList<Order> getOrderByDateCus(String date, int customer_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id, customer_id, store_id, total_price, date, status "
                    + "FROM [KFCStore].[dbo].[Order] "
                    + "WHERE CONVERT(DATE, date) = ? AND customer_id = ? AND status = 'succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, customer_id);
            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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

    //--------------------get order according to month-------------------
    public ArrayList<Order> getOrderByMonthCus(int month, int year, int customer_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND MONTH(date) = ? AND customer_id = ? AND status = 'succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, customer_id);
            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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
//------------------ get order according to year------------------

    public ArrayList<Order> getOrderByYearCus(int year, int customer_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT order_id,customer_id,store_id,total_price,date,status\n"
                    + "FROM [KFCStore].[dbo].[Order]\n"
                    + "WHERE YEAR(date) = ? AND status = 'succeed'";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);

            ArrayList<Order> listOrder = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));

                listOrder.add(order);
            }

            return listOrder;
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

    //---------------------- total revenue by date--------------------
    public int sumOrderByDateCus(String date, int customer_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + "COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE CONVERT(DATE, date) = ? and customer_id = ? \n"
                + "AND status = 'succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            statement.setInt(2, customer_id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //----------------------total revenue by month --------------------
    public int sumOrderByMonthCus(int month, int year, int customer_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + "FROM [KFCStore].[dbo].[Order]\n"
                + "WHERE YEAR(date) = ? AND MONTH(date) = ? AND customer_id = ?\n"
                + " AND status = 'succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            statement.setInt(2, month);
            statement.setInt(3, customer_id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // Xử lý lỗi nếu cần
            ex.printStackTrace();
        }

        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
    }

    //------------------------ revenue by year
    public int sumOrderByYearCus(int year, int customer_id) {
        String sql = "SELECT \n"
                + "SUM(total_price) AS total_price,\n"
                + " COUNT(order_id) AS order_count\n"
                + " FROM [KFCStore].[dbo].[Order]\n"
                + " WHERE YEAR(date) = ? \n"
                + "AND status = 'succeed'";

        try ( Connection connection = db.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            statement.setInt(2, customer_id);

            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_price");
                }
            }

        } catch (SQLException ex) {
            // loi
            ex.printStackTrace();
        }

        return 0;
    }
//-------- check buy or not----

    public boolean checkBuyOrNot(int customer_id, int dish_id) {
        String sql = "SELECT od.order_id, od.dish_id, od.numberOfDish, od.price, od.name, od.image "
                + "FROM [KFCStore].[dbo].[OrderDetail] od "
                + "INNER JOIN [KFCStore].[dbo].[Order] o ON od.order_id = o.order_id "
                + "WHERE o.customer_id = ? "
                + "AND od.dish_id = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();
            // Get your database connection here;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setInt(2, dish_id);
            rs = ps.executeQuery();

            // Kiểm tra xem có dữ liệu trả về hay không
            return rs.next();
        } catch (SQLException ex) {
            // Xử lý lỗi SQL
            ex.printStackTrace();
            return false;
        } finally {
            // Đóng tất cả tài nguyên
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Order getOrderById(int order_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;

        try {
            String sql = "SELECT order_id, customer_id, store_id, total_price, date, status, pStatus, receiver_name,"
                    + "receiver_address, receiver_phone FROM [KFCStore].[dbo].[Order] WHERE order_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();

            if (rs.next()) {

                order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setStore_id(rs.getInt("store_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStatus(rs.getString("status"));
                order.setReceiver_address(rs.getString("receiver_address"));
                order.setReceiver_name(rs.getString("receiver_name"));
                order.setReceiver_phone(rs.getString("receiver_phone"));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Xử lý lỗi đóng ResultSet
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // Xử lý lỗi đóng PreparedStatement
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Xử lý lỗi đóng Connection
                }
            }
        }

        return order;
    }

    // get order by store and status
    public ArrayList<Order> getOrderByStoreIdAndStatus(int store_id, String status) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = " Select order_id, total_price, date, customer_id, store_id,\n"
                    + "                  status, receiver_name, receiver_phone,receiver_address, pStatus \n"
                    + "              from [KFCStore].[dbo].[Order] where store_id = ? and (status  !=  'Succeed'\n"
                    + "                   and status != ?) ";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, store_id);
            ps.setString(2, status);

            ArrayList<Order> listDetail = new ArrayList<>();

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setTotalmoney(rs.getInt("total_price"));
                order.setDate(rs.getString("date"));
                order.setStore_id(rs.getInt("store_id"));

                order.setStatus(rs.getString("status"));
                order.setReceiver_phone(rs.getString("receiver_phone"));
                order.setReceiver_name(rs.getString("receiver_name"));
                order.setReceiver_address(rs.getString("receiver_address"));
                order.setPaymentStatus(rs.getString("pStatus"));
                listDetail.add(order);
            }

            return listDetail;
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

    // reset payment status
    public void resetStatusPaymentStatus(int order_id, String pStatus) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "update [KFCStore].[dbo].[Order] set pStatus = ? where order_id = ?";
            connection = db.getConnection();
            ps = connection.prepareStatement(sql);

            // Kiểm tra và đặt các giá trị đầu vào
            ps.setString(1, pStatus);
            ps.setInt(2, order_id);

            // Thực hiện truy vấn
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection trong khối finally để đảm bảo giải phóng tài nguyên
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng PreparedStatement
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // Xử lý lỗi đóng Connection
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
// get order by payment status 

    public List<Order> getOrderByPStatus(int store_id, String pStatus) {
        try ( Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement("SELECT order_id, customer_id,"
                + "store_id, total_price, date,  receiver_name, receiver_phone,receiver_address, status, pStatus FROM [KFCStore].[dbo].[Order] WHERE store_id = ? "
                + "AND pStatus=?")) {
            ps.setInt(1, store_id);
            ps.setString(2, pStatus);

            try ( ResultSet rs = ps.executeQuery()) {
                List<Order> list = new ArrayList<>();

                while (rs.next()) {
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setCustomer_id(rs.getInt("customer_id"));
                    order.setStore_id(rs.getInt("store_id"));
                    order.setTotalmoney(rs.getInt("total_price"));
                    order.setDate(rs.getString("date"));
                    order.setReceiver_phone(rs.getString("receiver_phone"));
                    order.setReceiver_name(rs.getString("receiver_name"));
                    order.setReceiver_address(rs.getString("receiver_address"));
                    order.setStatus(rs.getString("status"));
                    order.setPaymentStatus(rs.getString("pStatus"));
                    list.add(order);
                }

                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
//--------------total revenue of store per day of system----------------

    public List<MonthlyTotal> sumOfMonthByYear(int year) {
        String sql = "	WITH AllMonths AS (\n"
                + "  SELECT 1 AS [Month]\n"
                + "  UNION ALL SELECT 2\n"
                + "  UNION ALL SELECT 3\n"
                + "  UNION ALL SELECT 4\n"
                + "  UNION ALL SELECT 5\n"
                + "  UNION ALL SELECT 6\n"
                + "  UNION ALL SELECT 7\n"
                + "  UNION ALL SELECT 8\n"
                + "  UNION ALL SELECT 9\n"
                + "  UNION ALL SELECT 10\n"
                + "  UNION ALL SELECT 11\n"
                + "  UNION ALL SELECT 12\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    am.[Month] AS [Month],\n"
                + "    ISNULL(SUM(dt.[MonthlyTotal]), 0) AS [MonthlyTotal]\n"
                + "FROM\n"
                + "    AllMonths am\n"
                + "LEFT JOIN (\n"
                + "    SELECT\n"
                + "        MONTH([date]) AS [Month],\n"
                + "        SUM([total_price]) AS [MonthlyTotal]\n"
                + "    FROM\n"
                + "        [KFCStore].[dbo].[Order]\n"
                + "    WHERE\n"
                + "        YEAR([date]) = ?\n"
                + "        AND pStatus = 'Paid'\n"
                + "       \n"
                + "    GROUP BY\n"
                + "        MONTH([date])\n"
                + ") dt ON am.[Month] = dt.[Month]\n"
                + "GROUP BY\n"
                + "    am.[Month]\n"
                + "ORDER BY\n"
                + "    am.[Month];";

        List<MonthlyTotal> monthlyTotalList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int month = resultSet.getInt("Month");
                int monthlyTotal = resultSet.getInt("MonthlyTotal");
                MonthlyTotal monthlyTotalObj = new MonthlyTotal(month, year, monthlyTotal);
                monthlyTotalList.add(monthlyTotalObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return monthlyTotalList;
    }

    // total monthly of store by year
    public List<MonthlyTotal> sumOfMonthByYearS(int year, int store_id) {
        String sql = "WITH AllMonths AS (\n"
                + "  SELECT 1 AS [Month]\n"
                + "  UNION ALL SELECT 2\n"
                + "  UNION ALL SELECT 3\n"
                + "  UNION ALL SELECT 4\n"
                + "  UNION ALL SELECT 5\n"
                + "  UNION ALL SELECT 6\n"
                + "  UNION ALL SELECT 7\n"
                + "  UNION ALL SELECT 8\n"
                + "  UNION ALL SELECT 9\n"
                + "  UNION ALL SELECT 10\n"
                + "  UNION ALL SELECT 11\n"
                + "  UNION ALL SELECT 12\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    am.[Month] AS [Month],\n"
                + "    ISNULL(SUM(dt.[MonthlyTotal]), 0) AS [MonthlyTotal]\n"
                + "FROM\n"
                + "    AllMonths am\n"
                + "LEFT JOIN (\n"
                + "    SELECT\n"
                + "        MONTH([date]) AS [Month],\n"
                + "        SUM([total_price]) AS [MonthlyTotal]\n"
                + "    FROM\n"
                + "        [KFCStore].[dbo].[Order]\n"
                + "    WHERE\n"
                + "        YEAR([date]) = ?\n"
                + "        AND pStatus = 'Paid'\n"
                + "        AND store_id = ?\n"
                + "    GROUP BY\n"
                + "        MONTH([date])\n"
                + ") dt ON am.[Month] = dt.[Month]\n"
                + "GROUP BY\n"
                + "    am.[Month]\n"
                + "ORDER BY\n"
                + "    am.[Month];\n";

        List<MonthlyTotal> monthlyTotalList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, store_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int month = resultSet.getInt("Month");
                int monthlyTotal = resultSet.getInt("MonthlyTotal");
                MonthlyTotal monthlyTotalObj = new MonthlyTotal(month, year, monthlyTotal);
                monthlyTotalList.add(monthlyTotalObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return monthlyTotalList;
    }

    // total daily in month
    public List<DailyTotal> sumOfDayByMonth(int year, int month) {
        String sql = "	 WITH AllDays AS (\n"
                + "  SELECT 1 AS [Day]\n"
                + "  UNION ALL SELECT 2\n"
                + "  UNION ALL SELECT 3\n"
                + "  UNION ALL SELECT 4\n"
                + "  UNION ALL SELECT 5\n"
                + "  UNION ALL SELECT 6\n"
                + "  UNION ALL SELECT 7\n"
                + "  UNION ALL SELECT 8\n"
                + "  UNION ALL SELECT 9\n"
                + "  UNION ALL SELECT 10\n"
                + "  UNION ALL SELECT 11\n"
                + "  UNION ALL SELECT 12\n"
                + "  UNION ALL SELECT 13\n"
                + "  UNION ALL SELECT 14\n"
                + "  UNION ALL SELECT 15\n"
                + "  UNION ALL SELECT 16\n"
                + "  UNION ALL SELECT 17\n"
                + "  UNION ALL SELECT 18\n"
                + "  UNION ALL SELECT 19\n"
                + "  UNION ALL SELECT 20\n"
                + "  UNION ALL SELECT 21\n"
                + "  UNION ALL SELECT 22\n"
                + "  UNION ALL SELECT 23\n"
                + "  UNION ALL SELECT 24\n"
                + "  UNION ALL SELECT 25\n"
                + "  UNION ALL SELECT 26\n"
                + "  UNION ALL SELECT 27\n"
                + "  UNION ALL SELECT 28\n"
                + "  UNION ALL SELECT 29\n"
                + "  UNION ALL SELECT 30\n"
                + "  UNION ALL SELECT 31\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    ad.[Day] AS [Day],\n"
                + "    ISNULL(SUM(dt.[DailyTotal]), 0) AS [DailyTotal]\n"
                + "FROM\n"
                + "    AllDays ad\n"
                + "LEFT JOIN (\n"
                + "    SELECT\n"
                + "        DAY([date]) AS [Day],\n"
                + "        SUM([total_price]) AS [DailyTotal]\n"
                + "    FROM\n"
                + "        [KFCStore].[dbo].[Order]\n"
                + "    WHERE\n"
                + "        YEAR([date]) = ?\n"
                + "        AND MONTH([date]) = ?\n"
                + "        AND pStatus = 'Paid'\n"
                + "    GROUP BY\n"
                + "        DAY([date])\n"
                + ") dt ON ad.[Day] = dt.[Day]\n"
                + "GROUP BY\n"
                + "    ad.[Day]\n"
                + "ORDER BY\n"
                + "    ad.[Day];";

        List<DailyTotal> dailyTotalList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int date = resultSet.getInt("Day");

                int dailyTotal = resultSet.getInt("DailyTotal");
                DailyTotal dailyTotalObj = new DailyTotal(date, month, dailyTotal);
                dailyTotalList.add(dailyTotalObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return dailyTotalList;
    }

    // total revenue daily of store by month
    public List<DailyTotal> sumOfDayByMonthS(int year, int month, int store_id) {
        String sql = "	 WITH AllDays AS (\n"
                + "  SELECT 1 AS [Day]\n"
                + "  UNION ALL SELECT 2\n"
                + "  UNION ALL SELECT 3\n"
                + "  UNION ALL SELECT 4\n"
                + "  UNION ALL SELECT 5\n"
                + "  UNION ALL SELECT 6\n"
                + "  UNION ALL SELECT 7\n"
                + "  UNION ALL SELECT 8\n"
                + "  UNION ALL SELECT 9\n"
                + "  UNION ALL SELECT 10\n"
                + "  UNION ALL SELECT 11\n"
                + "  UNION ALL SELECT 12\n"
                + "  UNION ALL SELECT 13\n"
                + "  UNION ALL SELECT 14\n"
                + "  UNION ALL SELECT 15\n"
                + "  UNION ALL SELECT 16\n"
                + "  UNION ALL SELECT 17\n"
                + "  UNION ALL SELECT 18\n"
                + "  UNION ALL SELECT 19\n"
                + "  UNION ALL SELECT 20\n"
                + "  UNION ALL SELECT 21\n"
                + "  UNION ALL SELECT 22\n"
                + "  UNION ALL SELECT 23\n"
                + "  UNION ALL SELECT 24\n"
                + "  UNION ALL SELECT 25\n"
                + "  UNION ALL SELECT 26\n"
                + "  UNION ALL SELECT 27\n"
                + "  UNION ALL SELECT 28\n"
                + "  UNION ALL SELECT 29\n"
                + "  UNION ALL SELECT 30\n"
                + "  UNION ALL SELECT 31\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    ad.[Day] AS [Day],\n"
                + "    ISNULL(SUM(dt.[DailyTotal]), 0) AS [DailyTotal]\n"
                + "FROM\n"
                + "    AllDays ad\n"
                + "LEFT JOIN (\n"
                + "    SELECT\n"
                + "        DAY([date]) AS [Day],\n"
                + "        SUM([total_price]) AS [DailyTotal]\n"
                + "    FROM\n"
                + "        [KFCStore].[dbo].[Order]\n"
                + "    WHERE\n"
                + "        YEAR([date]) = ?\n"
                + "        AND MONTH([date]) = ?\n"
                + "        AND pStatus = 'Paid'"
                + "AND store_id = ?\n"
                + "    GROUP BY\n"
                + "        DAY([date])\n"
                + ") dt ON ad.[Day] = dt.[Day]\n"
                + "GROUP BY\n"
                + "    ad.[Day]\n"
                + "ORDER BY\n"
                + "    ad.[Day];";

        List<DailyTotal> dailyTotalList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);
            preparedStatement.setInt(3, store_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int date = resultSet.getInt("Day");

                int dailyTotal = resultSet.getInt("DailyTotal");
                DailyTotal dailyTotalObj = new DailyTotal(date, month, dailyTotal);
                dailyTotalList.add(dailyTotalObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return dailyTotalList;
    }

    // total num of order of each month in year
    public List<YearCount> getNumberOfOrderByYear(int year) {
        String sql = "WITH Months AS (\n"
                + "                    SELECT 1 AS MonthNumber\n"
                + "                    UNION ALL\n"
                + "                   SELECT MonthNumber + 1\n"
                + "                   FROM Months\n"
                + "                  WHERE MonthNumber < 12\n"
                + "                )\n"
                + "               \n"
                + "                SELECT Months.MonthNumber, COALESCE(COUNT(O.order_id), 0) AS OrderCount\n"
                + "                FROM Months\n"
                + "                LEFT JOIN [KFCStore].[dbo].[Order] O\n"
                + "                ON Months.MonthNumber = MONTH(O.date)\n"
                + "                AND YEAR(O.date) = ?\n"
                + "               AND o.status = 'Succeed' \n"
                + "                GROUP BY Months.MonthNumber\n"
                + "                ORDER BY Months.MonthNumber;";
        List<YearCount> countList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("MonthNumber");

                int count = resultSet.getInt("OrderCount");
                YearCount countObj = new YearCount(month, year, count);
                countList.add(countObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return countList;
    }

    //-------- number of order in year by store
    public List<YearCount> getNumberOfOrderByYearS(int year, int store_id) {
        String sql = "WITH Months AS (\n"
                + "    SELECT 1 AS MonthNumber\n"
                + "    UNION ALL\n"
                + "    SELECT MonthNumber + 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthNumber < 12\n"
                + ")\n"
                + "\n"
                + "SELECT Months.MonthNumber, COALESCE(COUNT(O.order_id), 0) AS OrderCount\n"
                + "FROM Months\n"
                + "LEFT JOIN [KFCStore].[dbo].[Order] O\n"
                + "ON Months.MonthNumber = MONTH(O.date)\n"
                + "AND YEAR(O.date) = ?\n"
                + "AND O.store_id = ? \n"
                + "AND o.status = 'Succeed' "
                + "GROUP BY Months.MonthNumber\n"
                + "ORDER BY Months.MonthNumber;";
        List<YearCount> countList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, store_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("MonthNumber");

                int count = resultSet.getInt("OrderCount");
                YearCount countObj = new YearCount(month, year, count);
                countList.add(countObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return countList;
    }

    // get number of order of day in month
    public List<MonthCount> getNumberOfOrderByMonth(int year, int month) {
        String sql = "WITH Days AS (\n"
                + "    SELECT 1 AS DayNumber\n"
                + "    UNION ALL\n"
                + "    SELECT DayNumber + 1\n"
                + "    FROM Days\n"
                + "    WHERE DayNumber < 31 \n"
                + ")\n"
                + "\n"
                + "SELECT Days.DayNumber, COALESCE(COUNT(O.order_id), 0) AS OrderCount\n"
                + "FROM Days\n"
                + "LEFT JOIN [KFCStore].[dbo].[Order] O\n"
                + "ON DAY(O.date) = Days.DayNumber\n"
                + "AND MONTH(O.date) = ? \n"
                + "AND YEAR(O.date) = ?\n"
                + "AND O.status='Succeed'\n"
                + "GROUP BY Days.DayNumber\n"
                + "ORDER BY Days.DayNumber;";
        List<MonthCount> countList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int date = resultSet.getInt("DayNumber");

                int count = resultSet.getInt("OrderCount");
                MonthCount countObj = new MonthCount(year, month, date, count);
                countList.add(countObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return countList;
    }

    // get number of order of day in month by store
    public List<MonthCount> getNumberOfOrderByMonthS(int year, int month, int store_id) {
        String sql = "WITH Days AS (\n"
                + "    SELECT 1 AS DayNumber\n"
                + "    UNION ALL\n"
                + "    SELECT DayNumber + 1\n"
                + "    FROM Days\n"
                + "    WHERE DayNumber < 31 \n"
                + ")\n"
                + "\n"
                + "SELECT Days.DayNumber, COALESCE(COUNT(O.order_id), 0) AS OrderCount\n"
                + "FROM Days\n"
                + "LEFT JOIN [KFCStore].[dbo].[Order] O\n"
                + "ON DAY(O.date) = Days.DayNumber\n"
                + "AND MONTH(O.date) = ? \n"
                + "AND YEAR(O.date) = ?\n"
                + "AND O.status='Succeed'"
                + "AND store_id = ?\n"
                + "GROUP BY Days.DayNumber\n"
                + "ORDER BY Days.DayNumber;";
        List<MonthCount> countList = new ArrayList<>();

        try ( Connection connection = db.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, store_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int date = resultSet.getInt("DayNumber");

                int count = resultSet.getInt("OrderCount");
                MonthCount countObj = new MonthCount(year, month, date, count);
                countList.add(countObj);
            }
        } catch (SQLException e) {
            // Handle database connection or query errors here
            e.printStackTrace();
        }

        return countList;
    }
}
