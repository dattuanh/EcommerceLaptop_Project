/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Cart;
import Base.Model.CartItem;
import Base.Model.Customer;
import Base.Model.Order;
import Base.Model.OrderItem;
import Base.Model.OrderStatus;
import Base.Model.Product;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author ADMIN
 */
public class DAOOrder {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    // REPOSITORY FOR ORDERSTATUS
    public OrderStatus getOrderStatus(String orderStatusId) {
        connect();
        try {
            String sql = "select OrderStatusId, OrderStatusName from OrderStatus "
                    + "where orderStatusId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderStatusId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setOrderStatusId(rs.getInt(1));
                os.setOrderStatusName(rs.getString(2));
                return os;
            }
        } catch (Exception e) {
            System.out.println("getOrderStatus: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return null;
    }

    public List<OrderStatus> getAllOrderStatus() {
        List<OrderStatus> list = new ArrayList<>();
        connect();
        try {
            String sql = "select OrderStatusId, OrderStatusName from OrderStatus";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderStatus os = getOrderStatus(rs.getString(1));
                list.add(os);
            }
        } catch (Exception e) {
            System.out.println("getAllOrderStatus: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    // END REPOSITORY FOR ORDERSTATUS
    // REPOSITORY FOR Order
    public Order getOrder(String orderId) {
        DAO dao = new DAO();
        connect();
        //Order o = null;
        try {
            String sql = "select OrderID, CustomerID, OrderDate, TotalPrice,"
                    + " OrderStatusId, PaymentDate, PaymentMethod, CashReceive, "
                    + "CashBack, [Address], ModifiedDate, AdminModified, ModifiedHistory from Orders \n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt(1));
                o.setCustomer(dao.getCustomer(rs.getInt(2)));
                o.setOrderDate(rs.getDate(3));
                o.setTotalPrice(rs.getDouble(4));
                o.setStatus(getOrderStatus(rs.getString(5)));
                o.setPaymentDate(rs.getDate(6));
                o.setPaymentMethod(rs.getString(7));
                o.setCashReceive(rs.getDouble(8));
                o.setCashBack(rs.getDouble(9));
                o.setAddress(rs.getString(10));
                o.setModifiedDate(rs.getDate(11));
                o.setModifiedAccount(dao.getAccount(rs.getInt(12)));
                o.setModifiedHistory(rs.getString(13));
                return o;
            }
        } catch (Exception e) {
            System.out.println("getOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return null;
    }

    // lấy tất cả đơn hàng 
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select OrderID, CustomerID, OrderDate, TotalPrice,"
                    + " OrderStatusId, PaymentDate, PaymentMethod, CashReceive, "
                    + "CashBack, [Address] from Orders";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("getAllOrders: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    public List<Order> getAllDeletedOrders(String status) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select OrderID, CustomerID, OrderDate, "
                    + "TotalPrice, OrderStatusId, PaymentDate, PaymentMethod from Orders "
                    + status;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("getAllDeletedOrders: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    // lấy danh sách sản phẩm trong 1 đơn hàng
    public List<OrderItem> getOrderItemByOrderId(String orderId) {
        DAO dao = new DAO();
        List<OrderItem> list = new ArrayList<>();
        connect();
        try {
            String sql = "select OrderItemID, OrderID, o.ProductID, Quantity"
                    + " from OrderItems o, Products p\n"
                    + "where o.ProductID = p.ProductID and OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem();
                oi.setOrderItemId(Integer.parseInt(orderId));
                oi.setOrderId(getOrder(rs.getString(2)));
                oi.setProductId(dao.getProduct(rs.getString(3)));
                oi.setQuantity(rs.getInt(4));
                list.add(oi);
            }
        } catch (Exception e) {
            System.out.println("getOrderItemById: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    // xóa đơn hàng
    public void deleteOrder(String orderId) {
        connect();
        try {
            String sql = "UPDATE Orders SET isDelete = 1 where OrderID = " + orderId;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    // xóa orderItem
    public void deleteOrderItem(String orderId) {
        connect();
        try {
            String sql = "UPDATE OrderItems SET IsDelete = 1 where OrderID = " + orderId;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteOrderItem: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    // update đơn hàng
    public void editOrder(String orderDate, String status, String paymentMethod, String cashReceive, String cashBack, String address, Date modifiedDate, int acc, String modifiedHistory, String orderId) {
        connect();
        DAO dao = new DAO();
        try {
            String sql = "update Orders set OrderDate = ?, OrderStatusId = ?, PaymentMethod = ?, CashReceive = ?,"
                    + " CashBack = ?, [Address] = ?, ModifiedDate = ?, AdminModified = ?, "
                    + "ModifiedHistory = cast( modifiedHistory as nvarchar(255)) + cast ('" + Base.Util.Formatter.dateToString(modifiedDate) + " - " + dao.getAccount(acc).getUserName() + ": Update Order <br>' as nvarchar(255))\n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, orderDate);
            ps.setString(2, status);
            ps.setString(3, paymentMethod);
            ps.setDouble(4, Double.parseDouble(cashReceive));
            ps.setDouble(5, Double.parseDouble(cashBack));
            ps.setString(6, address);
            ps.setDate(7, new java.sql.Date(modifiedDate.getTime()));
            ps.setInt(8, acc);
            //ps.setString(9, modifiedHistory);
            ps.setString(9, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("editOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    // add đơn hàng
    public void createOrder(Order o) {
        connect();
        try {
            String sql = "INSERT INTO Orders (CustomerID, OrderDate, TotalPrice, OrderStatusId, PaymentDate, PaymentMethod, CashReceive, CashBack, Address, ModifiedDate, AdminModified, ModifiedHistory) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, o.getCustomer().getCustomerId());
            ps.setDate(2, Base.Util.Formatter.formatDate(o.getOrderDate()));
            ps.setDouble(3, o.getTotalPrice());
            ps.setInt(4, o.getStatus().getOrderStatusId());
            ps.setDate(5, Base.Util.Formatter.formatDate(o.getPaymentDate()));
            ps.setString(6, o.getPaymentMethod());
            ps.setDouble(7, o.getCashReceive());
            ps.setDouble(8, o.getCashBack());
            ps.setString(9, o.getAddress());
            ps.setDate(10, Base.Util.Formatter.formatDate(o.getModifiedDate()));
            ps.setInt(11, o.getModifiedAccount().getAccountId());
            ps.setString(12, o.getModifiedHistory());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    // search đơn hàng theo address và payment Method
    public List<Order> searchOrderByFilter(String search) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select * from Orders\n"
                    + "where PaymentMethod like ? or [Address] like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByFilter: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }
    // END OF REPOSITORY FOR ORDER

    // REPOSITORY FOR SEARCH ORDER
    public String getAllOrderByFilter(String filter, String search) {
        String query;
        switch (filter) {
            case "All":
                query = "OrderID like ('%"+ search +"%')\n" 
                        +"or UserName like ('%"+ search +"%')\n" 
                        +"or TotalPrice like ('%"+ search +"%')\n" 
                        +"or OrderStatusName like ('%"+ search+"%')\n" 
                        +"or PaymentMethod like ('%"+ search +"%') \n";
                break;
            case "Status":
                query = " OrderStatusName like ('%"+ search+"%')\n";
                //list = searchOrderByStatus(search);
                break;
            case "CustomerName":
                query = " UserName like ('%"+ search +"%')\n";
                //list = searchOrderByCustomerName(search);
                break;
            default:
                query = filter + " like ('%" + search + "%')\n"
                        + "        ";
                //list = searchOrder(search, filter);
                break;
        }
        return query;
    }
    
    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from Orders o join Customers c\n" 
                    + "on o.CustomerID = c.CustomerID\n" 
                    + "join OrderStatus s on o.OrderStatusId = s.OrderStatusId\n"
                    + "where o.IsDelete = 0 and\n"
                    + "(\n"
                    + search
                    + " )";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return 0;
    }
    
    
    public List<Order> searchOrderBy(String search, int index, int entry, String isAsc) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select o.OrderID, c.UserName, o.OrderDate, o.TotalPrice, o.OrderStatusId,\n" 
                    + "o.PaymentDate, o.PaymentMethod, o.IsDelete, s.* from Orders o join Customers c\n" 
                    + "on o.CustomerID = c.CustomerID\n" 
                    + "join OrderStatus s on o.OrderStatusId = s.OrderStatusId\n"
                    + "where o.IsDelete = 0 and\n"
                    + "(\n"
                    + search
                    + " )"
                    + "order by OrderID desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }
    
//    public List<Order> getAllOrderByFilter1(String filter, String search) {
//        List<Order> list;
//        switch (filter) {
//            case "All":
//                list = searchOrderByAll(search);
//                break;
//            case "Status":
//                list = searchOrderByStatus(search);
//                break;
//            case "CustomerName":
//                list = searchOrderByCustomerName(search);
//                break;
//            default:
//                list = searchOrder(search, filter);
//                break;
//        }
//        return list;
//    }

    private List<Order> searchOrderByAll(String search) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select *\n"
                    + " from Orders \n"
                    + " where isDelete = 0 and \n"
                    + "        (\n"
                    + "        OrderID like ('%" + search + "%') \n"
                    + "        or OrderStatusName like ('%" + search + "%') \n"
                    + "        or PaymentMethod like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }
    
    public List<Order> searchOrder(String search, String filter) {
        connect();
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Orders \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + filter + "         like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order c = getOrder(rs.getString(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }
    
    
    private List<Order> searchOrderByCustomerName(String search) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select *\n"
                    + "from Orders o join Customers c\n"
                    + "on o.CustomerID = c.CustomerID\n"
                    + "where  \n"
                    + "        (\n"
                    + "       c.UserName like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByCustomerName: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Order> searchOrderByID(String search) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select *\n"
                    + "from Orders \n"
                    + "where  \n"
                    + "        (\n"
                    + "        OrderID like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByID: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Order> searchOrderByStatus(String search) {
        List<Order> list = new ArrayList<>();
        connect();
        try {
            String sql = "select *\n"
                    + "from Orders o join OrderStatus s\n"
                    + "on o.OrderStatusId = s.OrderStatusId\n"
                    + "where  \n"
                    + "        (\n"
                    + "       s.OrderStatusName like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByStatus: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }
    // END OF REPOSITORY FOR SEARCH ORDER

    // order by CustomerID
    public List<Order> pagingOrder(int index, int entries, String isDelete) {
        List<Order> list = new ArrayList<>();
        connect();
        //Order o = null;
        try {
            String sql = "select * from Orders\n"
                    + isDelete + "\n"
                    + "order by OrderID\n"
                    + "offset ? rows fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entries);
            ps.setInt(2, entries);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1)); //getCustomer(rs.getInt(1));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println("pagingOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    public void restoreOrder(String orderId) {
        connect();
        try {
            String sql = "update Orders set isDelete = 0 where OrderID = " + orderId;
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("restoreOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from Orders" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countTableItems: " + e.getMessage());
        }
        return 0;
    }

    public void editOrder(Order o) {
        connect();
        DaoAccount dao = new DaoAccount();
        try {
            String sql = "update Orders set OrderStatusId = ?, ModifiedDate = ?, AdminModified = ?,\n"
                    + " ModifiedHistory = ?\n"
                    + " where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, o.getStatus().getOrderStatusId());
            ps.setDate(2, new java.sql.Date(o.getModifiedDate().getTime()));
            ps.setInt(3, o.getModifiedAccount().getAccountId());
            ps.setString(4, o.getModifiedHistory());
            ps.setInt(5, o.getOrderId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("editOrder: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    public List<Order> getOrderByCustomer(int CustomerID) {
        connect();
        List<Order> olist = new ArrayList<>();
        try {
            String sql = "select * from Orders where CustomerID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, CustomerID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt(1));
                o.setCustomer(new DaoCustomer().getCustomer(rs.getInt(2)));
                o.setOrderDate(rs.getDate(3));
                o.setTotalPrice(rs.getDouble(4));
                o.setStatus(getOrderStatus(rs.getString(5)));
                o.setPaymentDate(rs.getDate(6));
                o.setPaymentMethod(rs.getString(7));
                o.setCashReceive(rs.getDouble(8));
                o.setCashBack(rs.getDouble(9));
                o.setAddress(rs.getString(10));
                olist.add(o);
            }
        } catch (Exception e) {
            System.out.println("getOrderByCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return olist;
    }
    
    
    public void checkoutOrder(Customer cus, Cart cart, String paymentMethod, String address) {
        connect();
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            connection = (new DBContext()).connection;
            String sql = "INSERT INTO Orders (CustomerID, OrderDate, TotalPrice, OrderStatusId, PaymentMethod, Address)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cus.getCustomerId());
            stm.setString(2, date);
            stm.setDouble(3, cart.getTotalMoney());
            stm.setInt(4, 1); // set pending status
            stm.setString(5, paymentMethod);
            stm.setString(6, address);
            stm.executeUpdate();
            String sql1 = " select top 1 OrderID from Orders order by OrderID desc";
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (CartItem cartItem : cart.getListItem()) {
                    for (int i = 0; i < cartItem.getQuantity(); i++) {
                        // lấy 1 product id mới có sold = 0
                        Product p = getProductIdByProductSeriId(cartItem.getProductSeri().getProductSeriId());
                        int pId = p.getProductId();

                        // update lại trang thái sold = 1
                        String sql3 = " UPDATE Products set Sold = 1\n"
                                + "where ProductID = ?";
                        stm = connection.prepareStatement(sql3);
                        stm.setInt(1, pId);
                        stm.executeUpdate();
                        
                        String sql4 = " INSERT INTO OrderItems (OrderID, ProductID, Quantity)"
                                + " VALUES (?,?,?)";
                        stm = connection.prepareStatement(sql4);
                        stm.setInt(1, oid);
                        stm.setInt(2, pId);
                        stm.setInt(3, 1);
                        stm.executeUpdate();

                        String sql5 = "INSERT INTO Warranties (ProductID, WarrantyDate, [Status], CreatedDate, "
                                + "IsDelete, Content, WarrantyTime, WarrantyPrice) VALUES\n"
                                + "(?,?,?,?,?,?,?,?)";
                        stm = connection.prepareStatement(sql5);
                        stm.setInt(1, pId);   // product Id
                        stm.setString(2, date);//Warranty Date
                        stm.setBoolean(3, true);//Status
                        stm.setString(4, date);       //Created Date
                        stm.setBoolean(5, false);    // is Delete
                        stm.setString(6, "Bảo hành được khởi tạo khi khách hàng checkout");     // Content
                        stm.setInt(7, 12);
                        stm.setDouble(8, 0);
                        stm.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("checkoutOrder: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Product getProductIdByProductSeriId(int pSeriId) {
        try {
            String sql2 = " select top 1 ProductID from Products\n"
                    + "where Sold = 0 and isDelete = 0 and ProductSeriID = ?";
            PreparedStatement stm = connection.prepareStatement(sql2);
            stm.setInt(1, pSeriId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt(1));
                return p;
            }
        } catch (Exception e) {
            System.out.println("getProductIdByProductSeriId: " + e.getMessage());
        }
        return null;
    }
}
