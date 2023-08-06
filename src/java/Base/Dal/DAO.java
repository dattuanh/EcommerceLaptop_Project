/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Giang Minh
 */
public class DAO {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void connect() {
        connection = new DBContext().connection;
    }

    public int getNumeberOf(String obj) {
        connect();
        try {
            String sql = "select count(*) from " + obj;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getNumeberOf: " + e.getMessage());
        }
        return 0;
    }

    public int countTableItems(String tableName) {
        connect();
        try {
            String sql = "select count(*) from " + tableName;
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

    public int countLastMonthTableItems(String tableName) {
        connect();

        try {
            LocalDate currentdate = LocalDate.now().minusMonths(1);
            String sql = "select count(*) from " + tableName + " where CreatedDate >= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(currentdate));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countLastMonthTableItems: " + e.getMessage());
        }
        return 0;
    }

    public int countSearch(String search, String tableName) {
        connect();
        try {
            String sql = "select Count(*)\n"
                    + "from " + tableName + " \n"
                    + IS_DELETE_FALSE_WITH_WHERE + "\n"
                    + search;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchInDao: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    // REPOSITORY FOR CUSTOMER
    public List<Customer> getAllCustomers() {
        connect();
        List<Customer> list = new ArrayList<>();

        try {
            connection = (new DBContext()).connection;
            String sql = "SELECT CustomerID, FirstName, LastName, Gender, Email, UserName, "
                    + "[Password], [Address], Phone, CreatedDate, [Image], ModifiedHistory, IsDelete\n"
                    + "FROM Customers" + IS_DELETE_FALSE_WITH_WHERE;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllCustomers: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return list;
    }

    public Customer getCustomer(int customerId) {
        connect();
        try {
            String sql = "SELECT CustomerID, FirstName, LastName, Gender, Email, UserName, "
                    + "[Password], [Address], Phone, CreatedDate, [Image], ModifiedHistory, IsDelete\n"
                    + "FROM Customers where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(1));
                c.setFirstName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setGender(rs.getBoolean(4));
                c.setEmail(rs.getString(5));
                c.setUserName(rs.getString(6));
                c.setPassword(rs.getString(7));
                c.setAddress(rs.getString(8));
                c.setPhone(rs.getString(9));
                c.setCreatedDate(rs.getDate(10));
                c.setImage(rs.getString(11));
                c.setModifiedHistory(rs.getString(12));
                c.setIsDelete(rs.getBoolean(13));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getCustomer: " + e.getMessage());
        }

        return null;
    }

    public void createCustomer(Customer c) {
        connect();
        try {
            String sql = "INSERT INTO Customers (FirstName, LastName, Gender, Email, UserName, [Password], [Address], Phone, CreatedDate, Image, ModifiedHistory, IsDelete) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setBoolean(3, c.isGender());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getUserName());
            ps.setString(6, c.getPassword());
            ps.setString(7, c.getAddress());
            ps.setString(8, c.getPhone());
            ps.setDate(9, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setString(10, c.getImage());
            ps.setString(11, c.getModifiedHistory());
            ps.setBoolean(12, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createCustomer: " + e.getMessage());
        }
    }

    public void updateCustomer(Customer c) {
        connect();
        try {
            String sql = "UPDATE Customers\n"
                    + "SET FirstName = ?,\n"
                    + "    LastName = ?,\n"
                    + "    Gender = ?,\n"
                    + "    Email = ?,\n"
                    + "    UserName = ?,\n"
                    + "    [Password] = ?,\n"
                    + "    [Address] = ?,\n"
                    + "    Phone = ?,\n"
                    + "    CreatedDate = ?,\n"
                    + "    [Image] = ?,\n"
                    + "    ModifiedHistory = ?,\n"
                    + "    IsDelete = ?\n"
                    + "WHERE CustomerID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setBoolean(3, c.isGender());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getUserName());
            ps.setString(6, c.getPassword());
            ps.setString(7, c.getAddress());
            ps.setString(8, c.getPhone());
            ps.setDate(9, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setString(10, c.getImage());
            ps.setString(11, c.getModifiedHistory());
            ps.setBoolean(12, c.isIsDelete());
            ps.setInt(13, c.getCustomerId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCustomer: " + e.getMessage());
        }
    }

    public void deleteCustomer(String customerId) {
        connect();
        try {
            String sql = "update customers set isdelete = 1 where customerId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteCustomer: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomerByFilter(String filter, String search) {
        switch (filter) {
            case "All":
                return searchCustomerByAll(search);
            case "ID":
                return searchCustomerByID(search);
            case "Name":
                return searchCustomerByName(search);
            case "Email":
                return searchCustomerByEmail(search);
            case "Phone":
                return searchCustomerByPhone(search);
            case "Address":
                return searchCustomerByAddress(search);
        }
        return null;
    }

    public List<Customer> searchCustomerByAll(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        CustomerID  like ('%" + search + "%')\n"
                    + "        or FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%')\n"
                    + "        or Email like ('%" + search + "%')\n"
                    + "        or Phone like ('%" + search + "%') \n"
                    + "        or address like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Customer> searchCustomerByID(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        CustomerID  like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByID: " + e.getMessage());
        }
        return list;
    }

    public List<Customer> searchCustomerByEmail(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        Email like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByEmail: " + e.getMessage());
        }
        return list;
    }

    public List<Customer> searchCustomerByName(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByName: " + e.getMessage());
        }
        return list;
    }

    public List<Customer> searchCustomerByPhone(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        Phone like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByPhone: " + e.getMessage());
        }
        return list;
    }

    public List<Customer> searchCustomerByAddress(String search) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        Address like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByPhone: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR CUSTOMER
    // REPOSITORY FOR ROLE
    public Role getRole(String roleId) {
        connect();
        try {
            String sql = "select * from Roles where RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt(1));
                r.setRoleName(rs.getString(2));
                r.setDescription(rs.getString(3));
                return r;
            }
        } catch (Exception e) {
            System.out.println("getRole: " + e.getMessage());
        }

        return null;
    }

    public List<Role> getAllRoles() {
        connect();
        List<Role> list = new ArrayList<>();

        try {
            String sql = "select * from Roles";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = getRole(rs.getString(1));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("getAllAccounts: " + e.getMessage());
        }

        return list;
    }

    // END OF REPOSITORY FOR ROLE
    // REPOSITORY FOR ACCOUNT
    public Account getAccount(int accountId) {
        connect();
        try {
            String sql = "select * from Accounts where AccountID = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setFirstName(rs.getString(4));
                a.setLastName(rs.getString(5));
                a.setGender(rs.getBoolean(6));
                a.setDateOfBirth(rs.getDate(7));
                a.setPhoneNumber(rs.getString(8));
                a.setImage(rs.getString(9));
                a.setIsDelete(rs.getBoolean(10));
                return a;
            }
        } catch (Exception e) {
            System.out.println("getAccount_accountId: " + e.getMessage());
        }

        return null;
    }

    public Account getAccount(String userName, String password) {
        connect();
        connect();
        try {
            String sql = "select * from Accounts where Username = ? and [Password] = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setFirstName(rs.getString(4));
                a.setLastName(rs.getString(5));
                a.setGender(rs.getBoolean(6));
                a.setDateOfBirth(rs.getDate(7));
                a.setPhoneNumber(rs.getString(8));
                a.setImage(rs.getString(9));
                a.setIsDelete(rs.getBoolean(10));
                return a;
            }
        } catch (Exception e) {
            System.out.println("getAccount_userName_password: " + e.getMessage());
        }

        return null;
    }

    public List<Account> getAllAccounts() {
        connect();
        List<Account> list = new ArrayList<>();

        try {
            String sql = "select * from Accounts" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getString(2), rs.getString(3));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("getAllAccounts: " + e.getMessage());
        }

        return list;
    }

    public int createAccount(Account a) {
        connect();
        try {
            String sql = "INSERT INTO Accounts (Username, [Password], FirstName, LastName, DateOfBirth, PhoneNumber, [Image], IsDelete) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFirstName());
            ps.setString(4, a.getLastName());
            ps.setDate(5, Base.Util.Formatter.formatDate(a.getDateOfBirth()));
            ps.setString(6, a.getPhoneNumber());
            ps.setString(7, a.getImage());
            ps.setBoolean(8, false);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("createAccount: " + e.getMessage());
        }
        return -1;
    }

    public void updateAccount(Account a) {
        connect();
        try {
            String sql = "UPDATE Accounts\n"
                    + "SET Username = ?, [Password] = ?, FirstName = ?, LastName = ?, \n"
                    + "DateOfBirth = ?, PhoneNumber = ?, [Image] = ?\n"
                    + "WHERE AccountID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFirstName());
            ps.setString(4, a.getLastName());
            ps.setDate(5, Base.Util.Formatter.formatDate(a.getDateOfBirth()));
            ps.setString(6, a.getPhoneNumber());
            ps.setString(7, a.getImage());
            ps.setInt(8, a.getAccountId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateAccount: " + e.getMessage());
        }
    }

    public void deleteAccount(String accountId) {
        connect();
        try {
            String sql = "UPDATE Accounts set IsDelete = 1 where AccountID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteAccount: " + e.getMessage());
        }
    }

    public List<Account> getAllAccountByFilter(String filter, String search) {
        switch (filter) {
            case "All":
                return searchAccountByAll(search);
            case "ID":
                return searchAccountByID(search);
            case "Name":
                return searchAccountByName(search);
            case "Username":
                return searchAccountByUsername(search);
            case "Phone":
                return searchAccountByPhone(search);
        }
        return null;
    }

    public List<Account> searchAccountByAll(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        AccountID  like ('%" + search + "%') \n"
                    + "        or Username  like ('%" + search + "%') \n"
                    + "        or FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%') \n"
                    + "        or PhoneNumber like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Account> searchAccountByName(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Account> searchAccountByID(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        AccountID  like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Account> searchAccountByUsername(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        Username  like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Account> searchAccountByPhone(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        PhoneNumber like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR ACCOUNT
    // REPOSITORY FOR ORDERSTATUS
    public OrderStatus getOrderStatus(String orderStatusId) {
        connect();
        try {
            String sql = "select * from OrderStatus where orderStatusId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderStatusId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setOrderStatusId(rs.getInt(1));
                os.setOrderStatusName(rs.getString(2));
                return os;
            }
        } catch (Exception e) {
            System.out.println("getOrderStatus: " + e.getMessage());
        }
        return null;
    }

    public List<OrderStatus> getAllOrderStatus() {
        connect();
        List<OrderStatus> list = new ArrayList<>();
        try {
            String sql = "select * from OrderStatus";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderStatus os = getOrderStatus(rs.getString(1));
                list.add(os);
            }
        } catch (Exception e) {
            System.out.println("getAllOrderStatus: " + e.getMessage());
        }
        return list;
    }

    // END REPOSITORY FOR ORDERSTATUS
    // REPOSITORY FOR Order
    public Order getOrder(String orderId) {
        connect();
        try {
            String sql = "select * from Orders \n"
                    + "where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt(1));
                o.setCustomer(getCustomer(rs.getInt(2)));
                o.setOrderDate(rs.getDate(3));
                o.setTotalPrice(rs.getDouble(4));
                o.setStatus(getOrderStatus(rs.getString(5)));
                o.setPaymentDate(rs.getDate(6));
                o.setPaymentMethod(rs.getString(7));
                o.setCashReceive(rs.getDouble(8));
                o.setCashBack(rs.getDouble(9));
                o.setAddress(rs.getString(10));
                return o;
            }
        } catch (Exception e) {
            System.out.println("getOrder: " + e.getMessage());
        }
        return null;
    }

    // lấy tất cả đơn hàng 
    public List<Order> getAllOrders() {
        connect();
        List<Order> list = new ArrayList<>();

        try {
            String sql = "select * from Orders";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("getAllOrders: " + e.getMessage());
        }

        return list;
    }

    // lấy danh sách sản phẩm trong 1 đơn hàng
    public List<OrderItem> getOrderItemByOrderId(String orderId) {
        connect();
        List<OrderItem> list = new ArrayList<>();

        try {
            String sql = "select * from OrderItems o, Products p\n"
                    + "where o.ProductID = p.ProductID and OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem();
                oi.setOrderItemId(Integer.parseInt(orderId));
                oi.setOrderId(getOrder(rs.getString(2)));
                oi.setProductId(getProduct(rs.getString(3)));
                oi.setQuantity(rs.getInt(4));
                list.add(oi);
            }
        } catch (Exception e) {
            System.out.println("getOrderItemById: " + e.getMessage());
        }

        return list;
    }

    // xóa đơn hàng
    public void deleteOrder(String orderId) {
        connect();
        try {
            // 4 is CLOSED STATUS 
            String sql = "UPDATE Orders SET OrderStatusId = 4 where OrderID = " + orderId;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteOrder: " + e.getMessage());
        }
    }

    // xóa orderItem
    public void deleteOrderItem(String orderId) {
        connect();
        try {
            String sql = "UPDATE OrderItems SET IsDelete = 1 where OrderID = " + orderId;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteOrderItem: " + e.getMessage());
        }
    }

    // update đơn hàng
    public void editOrder(String orderDate, String status, String paymentMethod, String cashReceive, String cashBack, String address, String orderId) {
        connect();
        try {
            String sql = "update Orders set OrderDate = ?, OrderStatusId = ?, PaymentMethod = ?, CashReceive = ?, CashBack = ?, [Address] = ? \n"
                    + "where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderDate);
            stm.setString(2, status);
            stm.setString(3, paymentMethod);
            stm.setDouble(4, Double.parseDouble(cashReceive));
            stm.setDouble(5, Double.parseDouble(cashBack));
            stm.setString(6, address);
            stm.setString(7, orderId);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("editOrder: " + e.getMessage());
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
        }
    }

    // search đơn hàng theo address và payment Method
    public List<Order> searchOrderByFilter(String search) {
        connect();
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select * from Orders\n"
                    + "where PaymentMethod like ? or [Address] like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            stm.setString(2, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByFilter: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR ORDER
    // CATEGORY REPOSITORY
    public Category getCategory(String categoryId) {
        connect();
        try {
            String sql = "select * from Categories \n"
                    + "where CategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, categoryId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt(1));
                c.setCategoryName(rs.getString(2));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getCategory: " + e.getMessage());
        }
        return null;
    }

    public List<Category> getAllCategories() {
        connect();
        List<Category> list = new ArrayList<>();

        try {
            String sql = "select * from Categories\n"
                    + "where IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("GetAllCategories: " + e.getMessage());
        }

        return list;
    }

    public List<Order> getAllOrderByFilter(String filter, String search) {
        connect();
        switch (filter) {
            case "All":
                return searchOrderByAll(search);
            case "ID":
                return searchOrderByID(search);
            case "Status":
                return searchOrderByStatus(search);
        }
        return null;
    }

    private List<Order> searchOrderByAll(String search) {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + " from Orders \n"
                    + " where  \n"
                    + "        (\n"
                    + "        OrderID like ('%" + search + "%') \n"
                    + "        or OrderStatusId like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByAll: " + e.getMessage());
        }
        return list;
    }

    private List<Order> searchOrderByID(String search) {
        List<Order> list = new ArrayList<>();
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
        }
        return list;
    }

    private List<Order> searchOrderByStatus(String search) {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Orders \n"
                    + "where  \n"
                    + "        (\n"
                    + "       OrderStatusId like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = getOrder(rs.getString(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchOrderByStatus: " + e.getMessage());
        }
        return list;
    }

    public Category getCategory(int categoryId) {
        connect();
        try {
            String sql = "select * from Categories \n"
                    + "where CategoryID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt(1));
                c.setCategoryName(rs.getString(2));
                c.setProductQuantity(getSerieQuantity(rs.getInt(1)));
                c.setCreatedDate(Base.Util.Formatter.formatDate(rs.getDate(3)));
                c.setModifiedDate(Base.Util.Formatter.formatDate(rs.getDate(4)));
                c.setCreatedBy(getAccount(rs.getInt(5)));
                c.setModifiedBy(getAccount(rs.getInt(6)));
                c.setModifiedHistory(rs.getString(7));
                return c;
            }
        } catch (Exception e) {
            System.out.println("GetCategory: " + e.getMessage());
        }

        return null;
    }

    public void createCategory(Category c) {
        connect();
        try {
            String sql = "INSERT INTO Categories(CategoryName,CreatedDate,CreatedBy,ModifiedDate,ModifiedBy,ModifiedHistory,IsDelete) "
                    + "values (?,?,?,?,?,?,0)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCategoryName());
            ps.setDate(2, new java.sql.Date(c.getCreatedDate().getTime()));
            ps.setInt(3, c.getCreatedBy().getAccountId());
            ps.setDate(4, new java.sql.Date(c.getCreatedDate().getTime()));
            ps.setInt(5, c.getCreatedBy().getAccountId());
            ps.setString(6, c.getCreatedDate().toString() + " - " + c.getCreatedBy().getUserName() + ": Create new category <br>");
            ps.execute();
        } catch (Exception e) {
            System.out.println("createCategory: " + e.getMessage());
        }
    }

    public int getSerieQuantity(int categoryId) {
        connect();
        try {
            String sql = "select Count(*) from ProductSeries \n"
                    + "where CategoryID = ?\n"
                    + "and isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getSerieQuantity: " + e.getMessage());
        }
        return 0;
    }

    public void updateCategory(Category c) {
        connect();
        try {
            String strUpdate = "update Categories "
                    + "set CategoryName=? \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + cast ('" + c.getModifiedDate().toString() + " - " + c.getModifiedBy().getUserName() + ": Update category <br>" + "' as nvarchar(255))\n"
                    + "where CategoryID=?";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps = connection.prepareStatement(strUpdate);
            ps.setString(1, c.getCategoryName());
            ps.setDate(2, new java.sql.Date(c.getModifiedDate().getTime()));
            ps.setInt(3, c.getModifiedBy().getAccountId());
            ps.setInt(4, c.getCategoryID());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCategory: " + e.getMessage());
        }
    }

    public void removeCategory(Category c) {
        connect();
        try {
            String strDelete = " select * from ProductSeries\n"
                    + "  where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setInt(1, c.getCategoryID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = new ProductSeri();
                p.setProductSeriId(rs.getInt(1));
                p.setModifiedBy(c.getModifiedBy());
                p.setModifiedDate(c.getModifiedDate());
                removeSerie(p);
            }
            strDelete = "Update Categories \n"
                    + "SET\n"
                    + "      isDelete = 1\n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + cast ('" + c.getModifiedDate().toString() + " - " + c.getModifiedBy().getUserName() + ": Remove Category <br>" + "' as nvarchar(255))\n"
                    + "where CategoryID=?";
            ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(c.getModifiedDate().getTime()));
            ps.setInt(2, c.getModifiedBy().getAccountId());
            ps.setInt(3, c.getCategoryID());
            ps.execute();
        } catch (Exception e) {
            System.out.println("removeCategory: " + e.getMessage());
        }
    }

    //SEARCH
    public List<Category> getAllCategoryByFilter(String filter, String search) {
        connect();
        List<Category> list = new ArrayList<>();
        switch (filter) {
            case "All":
                return searchCategoryByAll(search);
            case "Name":
                return searchCategoryByName(search);
            case "Quantity":
                return searchCategoryByQuantity(search);
        }
        return list;
    }

    public List<Category> searchCategoryByAll(String search) {
        connect();
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select Categories.*\n"
                    + "from Categories \n"
                    + "where isDelete = 0 and (CategoryName  like ('%" + search + "%') \n"
                    + "or (select count(*) as Quantity\n"
                    + "		from ProductSeries\n"
                    + "		where Categories.categoryID=ProductSeries.CategoryID\n"
                    + "	) like ('%" + search + "%')) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<Category> searchCategoryByName(String search) {
        connect();
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select Categories.* \n"
                    + "from Categories \n"
                    + "where isDelete = 0 \n"
                    + "and CategoryName like ('%" + search + "%') ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByName: " + e.getMessage());
        }
        return list;
    }

    public List<Category> searchCategoryByQuantity(String search) {
        connect();
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select Categories.*\n"
                    + "from Categories \n"
                    + "where isDelete = 0 "
                    + "and (select count(*) as Quantity\n"
                    + "		from ProductSeries\n"
                    + "		where Categories.categoryID=ProductSeries.CategoryID\n"
                    + "	) like ('%" + search + "%') \n ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByQuantity: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR CATEGORY
    // PRODUCTSERI REPOSITORY
    public ProductSeri getProductSeri(String productSeriId) {
        connect();
        try {
            String sql = "select * from ProductSeries \n"
                    + "where ProductSeriID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productSeriId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductSeri s = new ProductSeri();
                s.setProductSeriId(rs.getInt(1));
                s.setProductSeriName(rs.getString(2));
                s.setPrice(rs.getDouble(3));
                s.setDescription(rs.getString(4));
                s.setImagePreview(rs.getString(5));
                s.setCategoryId(getCategory(rs.getInt(6)));
                s.setManufacturer(rs.getString(7));
                s.setWarrantyTime(rs.getInt(8));
                s.setSize(rs.getString(9));
                s.setColor(getColor(rs.getString(10)));
                s.setCreatedDate(rs.getDate(11));
                s.setModifiedDate(rs.getDate(12));
                s.setCreatedBy(getAccount(rs.getInt(13)));
                s.setModifiedBy(getAccount(rs.getInt(14)));
                s.setModifiedHistory(rs.getString(15));
                s.setIsDelete(rs.getBoolean(16));
                s.setStock(getProductStock(rs.getInt(1)));
                return s;
            }
        } catch (Exception e) {
            System.out.println("getOrder: " + e.getMessage());
        }
        return null;
    }

    public List<ProductSeri> getAllProductSerie() {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String strSelect = "select * from ProductSeries" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = getProductSeri(rs.getString(1));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("getAllProductSerie: " + e.getMessage());
        }
        return list;
    }

//    public ProductSeri getProductSeries(String serieID) {
//        try {
//            String sql = "select ProductSeries.*, CategoryName from Categories \n"
//                    + "left join ProductSeries\n"
//                    + "on  Categories.categoryID=ProductSeries.CategoryID\n"
//                    + "where ProductSeries.ProductSeriID = ? and ProductSeries.isDelete = 0";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri s = new ProductSeri();
//                s.setProductSeriId(rs.getInt(1));
//                s.setProductSeriName(rs.getString(2));
//                s.setPrice(rs.getDouble(3));
//                s.setDescription(rs.getString(4));
//                s.setImagePreview(rs.getString(5));
//                s.setImage(getImage(rs.getString(6)));
//                s.setCategoryId(getCategory(rs.getInt(7)));
//                s.setManufacturer(rs.getString(8));
//                s.setWarrantyTime(rs.getInt(9));
//                s.setSize(rs.getString(10));
//                s.setColor(getColor(rs.getString(11)));
//                s.setCreatedDate(rs.getDate(12));
//                s.setModifiedDate(rs.getDate(13));
//                s.setCreatedBy(getAccount(rs.getInt(14)));
//                s.setModifiedBy(getAccount(rs.getInt(15)));
//                s.setModifiedHistory(rs.getString(16));
//                s.setIsDelete(rs.getBoolean(17));
//                return s;
//            }
//        } catch (Exception e) {
//            System.out.println("getProductSeries: " + e.getMessage());
//        }
//        return null;
//    }
    public int createSerie(ProductSeri s) {
        connect();
        try {
            String sql = "INSERT INTO ProductSeries(ProductName,Price,Description,ImagePreview,CategoryID,Manufacturer,WarrantyTime,Size,Color,CreatedDate,CreatedBy,ModifiedDate,ModifiedBy,ModifiedHistory,IsDelete) \n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getProductSeriName());
            ps.setDouble(2, s.getPrice());
            ps.setString(3, s.getDescription());
            ps.setString(4, s.getImagePreview());
            ps.setInt(5, s.getCategoryId().getCategoryID());
            ps.setString(6, s.getManufacturer());
            ps.setInt(7, s.getWarrantyTime());
            ps.setString(8, s.getSize());
            ps.setInt(9, s.getColor().getColorId());
            ps.setDate(10, new java.sql.Date(s.getCreatedDate().getTime()));
            ps.setInt(11, s.getCreatedBy().getAccountId());
            ps.setDate(12, new java.sql.Date(s.getCreatedDate().getTime()));
            ps.setInt(13, s.getCreatedBy().getAccountId());
            ps.setString(14, s.getModifiedHistory());
            ps.setBoolean(15, s.isIsDelete());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("createSerie: " + e.getMessage());
        }
        return 0;
    }

    public void updateSerie(ProductSeri s) {
        connect();
        try {
            String strUpdate = "UPDATE ProductSeries\n"
                    + "   SET ProductName = ? \n"
                    + "      ,Price = ? \n"
                    + "      ,Description = ? \n"
                    + "      ,ImagePreview = ? \n"
                    + "      ,Manufacturer = ? \n"
                    + "      ,WarrantyTime = ? \n"
                    + "      ,Size = ? \n"
                    + "      ,Color = ? \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + cast ('" + s.getModifiedDate().toString() + " - " + s.getModifiedBy().getUserName() + ": Update Serie <br>' as nvarchar(255))\n"
                    + "      ,CategoryID = ? \n"
                    + " WHERE ProductSeriID = ? ";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps.setString(1, s.getProductSeriName());
            ps.setDouble(2, s.getPrice());
            ps.setString(3, s.getDescription());
            ps.setString(4, s.getImagePreview());
            ps.setString(5, s.getManufacturer());
            ps.setInt(6, s.getWarrantyTime());
            ps.setString(7, s.getSize());
            ps.setInt(8, s.getColor().getColorId());
            ps.setDate(9, new java.sql.Date(s.getModifiedDate().getTime()));
            ps.setInt(10, s.getModifiedBy().getAccountId());
//            ps.setString(11, s.getModifiedDate().toString() + " - " + s.getModifiedBy().getUserName() + ": Create new Serie \n");
            ps.setInt(11, s.getCategoryId().getCategoryID());
            ps.setInt(12, s.getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateSerie: " + e.getMessage());
        }
    }

    public void updateSerieByProduct(Product p, String action) {
        connect();
        try {
            String strUpdate = "UPDATE ProductSeries\n"
                    + "   SET ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + "
                    + "cast ('" + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName()
                    + ": " + action + " <br>' as nvarchar(255))\n"
                    + " WHERE ProductSeriID = ? ";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setInt(3, p.getProductSerieId().getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateSerieByProduct: " + e.getMessage());
        }
    }

    public void removeSerie(ProductSeri p) {
        connect();
        try {
            String strDelete = "Update Products\n"
                    + "Set isDelete = 1 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + cast ('" + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Serie <br>" + "' as nvarchar(255))\n"
                    + "WHERE productSeriID = ? ";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setInt(3, p.getProductSeriId());
            ps.execute();
            strDelete = "Update ProductSeries\n"
                    + "Set isDelete = 1 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + cast ('" + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Reomve Serie <br>" + "' as nvarchar(255))\n"
                    + "WHERE productSeriID = ? ";
            ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setInt(3, p.getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("getRemoveSerie: " + e.getMessage());
        }
    }

    public List<ProductSeri> getAllProductSerieByFilter(String filter, String search) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        switch (filter) {
            case "All":
                return searchProductSerieByAll(search);
            case "Name":
                return searchProductSerieByName(search);
            case "Price":
                return searchProductSerieByPrice(search);
            case "Manufacturer":
                return searchProductSerieByManufacturer(search);
            case "WarrantyTime":
                return searchProductSerieByWarrantyTime(search);
            case "Size":
                return searchProductSerieBySize(search);
            case "Color":
                return searchProductSerieByColor(search);
            case "Quantity":
                return searchProductSerieByQuantity(search);
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByAll(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	ProductName like ('%" + search + "%')\n"
                    + "	or Price  like ('%" + search + "%')\n"
                    + "	or Manufacturer like ('%" + search + "%')\n"
                    + "	or WarrantyTime  like ('%" + search + "%')\n"
                    + "	or Size like ('%" + search + "%')\n"
                    + "	or Color like ('%" + search + "%')\n"
                    + "	or (\n"
                    + "		select count(*) from Products\n"
                    + "		where Products.productSeriID = ProductSeries.ProductSeriID\n"
                    + "	) like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByAll: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByName(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	ProductName like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByName: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByPrice(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	Price  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByPrice: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByManufacturer(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	Manufacturer like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByManufacturer: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByWarrantyTime(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	WarrantyTime  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByWarrantyTime: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieBySize(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	Size like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieBySize: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByColor(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	Color like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByColor: " + e.getMessage());
        }
        return list;
    }

    private List<ProductSeri> searchProductSerieByQuantity(String search) {
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "	(\n"
                    + "	(\n"
                    + "		select count(*) from Products\n"
                    + "		where Products.productSeriID = ProductSeries.ProductSeriID\n"
                    + "	) like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieByAll: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR PRODUCTSERI
    // PRODUCT REPOSITORY
    public Product getProduct(String productId) {
        connect();
        try {
            String sql = "select * from Products \n"
                    + "where productId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt(1));
                p.setProductSerieId(getProductSeri(rs.getString(2)));
                p.setStatus(rs.getBoolean(3));
                return p;
            }
        } catch (Exception e) {
            System.out.println("getOrder: " + e.getMessage());
        }
        return null;
    }

    public List<Product> getAllProducts() {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Products \n"
                    + "where IsDelete = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getAllProducts: " + e.getMessage());
        }
        return list;
    }

    public int getProductStock(int serieID) {
        connect();
        try {
            String sql = "select count(ProductID) from Products \n"
                    + "join ProductSeries\n"
                    + "on ProductSeries.ProductSeriID = Products.ProductSeriID\n"
                    + "where Products.isDelete = 0 and ProductSeries.ProductSeriID = ? "
                    + "and sold = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getProductStock: " + e.getMessage());
        }
        return 0;
    }

    public void addProduct(Product p) {
        connect();
        try {
            String sql = "INSERT INTO Products(ProductSeriID,Sold,BatchSerial,CreatedDate,CreatedBy,ModifiedHistory,isDelete)\n"
                    + "VALUES (?,0,?,?,?,?,0)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getProductSerieId().getProductSeriId());
            ps.setString(2, p.getBatchSerial());
            ps.setDate(3, new java.sql.Date(p.getCreatedDate().getTime()));
            ps.setInt(4, p.getCreatedBy().getAccountId());
            ps.setString(5, p.getCreatedDate().toString() + " - " + p.getCreatedBy().getUserName() + ": Added Product <br>");
            ps.execute();
        } catch (Exception e) {
            System.out.println("addProduct: " + e.getMessage());
        }
    }

    public List<Product> getProductListBySerie(String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String strSelect = "select * from Products\n"
                    + "where ProductSeriID = ? and isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getProductListBySerie: " + e.getMessage());
        }
        return list;
    }

    public List<Product> getProductListBySerie() {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String strSelect = "select * from Products\n"
                    + "where isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getProductListBySerie: " + e.getMessage());
        }
        return list;
    }

    public Product getProduct(int productID) {
        connect();
        try {
            String sql = "select * from Products\n"
                    + "where ProductID = ? and isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt(1));
                p.setProductSerieId(getProductSeri(rs.getString(2)));
                p.setStatus(rs.getBoolean(3));
                p.setBatchSerial(rs.getString(4));
                p.setCreatedDate(rs.getDate(5));
                p.setCreatedBy(getAccount(rs.getInt(6)));
                p.setModifiedDate(rs.getDate(7));
                p.setModifiedBy(getAccount(rs.getInt(8)));
                p.setModifiedHistory(rs.getString(9));
                return p;
            }
        } catch (Exception e) {
            System.out.println("getProduct: " + e.getMessage());
        }

        return null;
    }

    public void updateProduct(Product p) {
        connect();
        try {
            String strUpdate = "UPDATE Products\n"
                    + "SET Sold = ? \n"
                    + "      ,BatchSerial = ?\n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + "
                    + "cast ('" + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Update Product <br>" + "' as nvarchar(255))\n"
                    + "WHERE ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps.setBoolean(1, p.isStatus());
            ps.setString(2, p.getBatchSerial());
            ps.setDate(3, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(4, p.getModifiedBy().getAccountId());
            ps.setInt(5, p.getProductId());
            ps.execute();
            String action = "Update Product, ProductId: " + p.getProductId();
            updateSerieByProduct(p, action);
        } catch (Exception e) {
            System.out.println("updateProduct: " + e.getMessage());
        }
    }

    public void removeProduct(Product p) {
        connect();
        try {
            String strDelete = "Update Products\n"
                    + "Set isDelete = 1 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = cast( ModifiedHistory as nvarchar(255)) + "
                    + "cast ('" + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Product <br>" + "' as nvarchar(255))\n"
                    + "WHERE productID = ? ";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setInt(3, p.getProductId());
            ps.execute();
            String action = "Remove Product, ProductId: " + p.getProductId();
            updateSerieByProduct(p, action);
        } catch (Exception e) {
            System.out.println("removeProduct: " + e.getMessage());
        }
    }

    public List<Product> getAllProductByFilter(String filter, String serieID, String search) {
        List<Product> list = new ArrayList<>();
        switch (filter) {
            case "All":
                return searchProductByAll(search, serieID);
            case "Name":
                return searchProductByID(search, serieID);
            case "Price":
                return searchProductByBatchSerial(search, serieID);
            case "Sold":
                return searchSoldProduct(search, serieID);
            case "Available":
                return searchAvailableProduct(search, serieID);
            case "ActivatedWarranty":
                return searchActivatedWarranty(search, serieID);
            case "ExpiredWarranty":
                return searchExpiredWarranty(search, serieID);
        }
        return list;

    }

    private List<Product> searchProductByAll(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products\n"
                    + "where isDelete = 0 and ProductSeriID like ('" + serieID + "') and\n"
                    + "	(\n"
                    + "	ProductID like ('%" + search + "%')\n"
                    + "	or BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductByAll: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchProductByID(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products\n"
                    + "whereisDelete = 0 and ProductSeriID like ('" + serieID + "') and\n"
                    + "	(\n"
                    + "	ProductID like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductByID: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchProductByBatchSerial(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products\n"
                    + "where isDelete = 0 and ProductSeriID like ('" + serieID + "') and\n"
                    + "	(\n"
                    + "	BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductByBatchSerial: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchSoldProduct(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products\n"
                    + "where isDelete = 0 and ProductSeriID like ('" + serieID + "') and Sold = 1 \n"
                    + " and (\n"
                    + "	Products.ProductID like ('%" + search + "%')\n"
                    + "	or BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchSoldProduct: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchAvailableProduct(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products\n"
                    + "where isDelete = 0 and ProductSeriID like ('" + serieID + "') and Sold = 0\n"
                    + "	and (\n"
                    + "	Products.ProductID like ('%" + search + "%')\n"
                    + "	or BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchAvailableProduct: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchActivatedWarranty(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products join Warranties\n"
                    + "on Products.ProductID=Warranties.ProductID\n"
                    + "where ProductSeriID like ('" + serieID + "') \n"
                    + "and Products.isDelete = 0 \n"
                    + "and Warranties.Status = 0\n"
                    + "and (\n"
                    + "	Products.ProductID like ('%" + search + "%')\n"
                    + "	or BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchActivatedWarranty: " + e.getMessage());
        }
        return list;
    }

    private List<Product> searchExpiredWarranty(String search, String serieID) {
        connect();
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select Products.*\n"
                    + "from Products join Warranties\n"
                    + "on Products.ProductID=Warranties.ProductID\n"
                    + "where ProductSeriID like ('" + serieID + "') \n"
                    + "and Products.isDelete = 0 \n"
                    + "and Warranties.Status = 1\n"
                    + "and (\n"
                    + "Products.ProductID like ('%" + search + "%')\n"
                    + "or BatchSerial  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println("{" + search + "}");
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchExpiredWarranty: " + e.getMessage());
        }
        return list;
    }

    // END OF REPOSITORY FOR PRODUCT
    //REPOSITORY FOR NewsGroup
    public List<NewsGroup> getAllNewsGroup() {
        connect();
        List<NewsGroup> list = new ArrayList<>();

        try {
            String sql = "select * from NewsGroup where IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup c = getNewsGroup(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNewGroup: " + e.getMessage());
        }

        return list;
    }

    public NewsGroup getNewsGroup(int NewsGroupID) {
        connect();
        try {
            String sql = "select * from NewsGroup where NewsGroupID = ? and IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, NewsGroupID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup ng = new NewsGroup();
                ng.setNewsGroupID(rs.getInt(1));
                ng.setNewsGroupName(rs.getString(2));
                ng.setCreatedDate(rs.getDate(3));
                ng.setModifiedDate(rs.getDate(4));
                ng.setCreatedBy(getAccount(rs.getInt(5)));
                ng.setModifiedBy(getAccount(rs.getInt(6)));
                ng.setModifiedHistory(rs.getString(7));
                ng.setIsDelete(rs.getBoolean(8));
                return ng;
            }
        } catch (Exception e) {
            System.out.println("getNewsGroup: " + e.getMessage());
        }

        return null;
    }

    public void createNewsGroup(NewsGroup c) {
        connect();
        try {
            String sql = "INSERT INTO NewsGroup (NewsGroupName, CreatedDate, ModifiedDate, CreatedBy, ModifiedBy, ModifiedHistory, IsDelete) VALUES \n"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getNewsGroupName());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setDate(3, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(4, c.getCreatedBy().getAccountId());
            ps.setInt(5, c.getModifiedBy().getAccountId());
            ps.setString(6, c.getModifiedHistory());
            ps.setBoolean(7, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createNewsGroup: " + e.getMessage());
        }
    }

    public void deleteNewsGroup(String newsGroupID) {
        connect();
        try {
            String sql = "UPDATE NewsGroup SET IsDelete = 1 where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newsGroupID);
            ps.execute();
            deleteNews(newsGroupID);
        } catch (Exception e) {
            System.out.println("deletenewsGroup: " + e.getMessage());
        }
    }

    public void updateNewsGroup(NewsGroup c) {
        connect();
        try {
            String sql = "UPDATE NewsGroup\n"
                    + "SET NewsGroupName = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = ?,\n"
                    + "    ModifiedHistory = ?,\n"
                    + "    IsDelete = 0\n"
                    + "WHERE NewsGroupID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getNewsGroupName());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(3, c.getModifiedBy().getAccountId());
            ps.setString(4, c.getModifiedHistory());
            ps.setInt(5, c.getNewsGroupID());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateNewsGroup: " + e.getMessage());
        }
    }

    public List<NewsGroup> getAllNewsGroupByFilter(String filter, String search) {
        connect();
        List<NewsGroup> list = new ArrayList<>();
        switch (filter) {
            case "All":
                return searchNewsGroupByAll(search);
            case "Name":
                return searchNewsGroupByName(search);
        }
        return list;
    }

    public List<NewsGroup> searchNewsGroupByAll(String search) {
        connect();
        List<NewsGroup> list = new ArrayList<>();
        try {
            String sql = "select NewsGroup.*\n"
                    + "from NewsGroup \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        NewsGroupID like ('%" + search + "%')\n"
                    + "        or NewsGroupName  like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup n = getNewsGroup(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        }
        return list;
    }

    public List<NewsGroup> searchNewsGroupByName(String search) {
        connect();
        List<NewsGroup> list = new ArrayList<>();
        try {
            String sql = "select NewsGroup.*\n"
                    + "from NewsGroup \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        NewsGroupName  like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup n = getNewsGroup(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsGroupByName: " + e.getMessage());
        }
        return list;
    }
    //END OF REPOSITORY FOR NewsGroups

    //Start News
    public List<News> getAllNews() {
        connect();
        List<News> list = new ArrayList<>();

        try {
            String sql = "select * from News where IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = getNews(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNews: " + e.getMessage());
        }

        return list;
    }

    public News getNews(int NewID) {
        connect();
        try {
            String sql = "select * from News where NewID = ? and IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, NewID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = new News();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(getAccount(rs.getInt(9)));
                c.setModifiedBy(getAccount(rs.getInt(10)));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNews: " + e.getMessage());
        }

        return null;
    }

    public void createNews(News c) {
        connect();
        try {
            String sql = "INSERT INTO News (NewsGroupID, NewTitle, NewsHeading, NewsImage, NewsContent, CreatedDate, ModifiedDate, CreatedBy, ModifiedBy, ModifiedHistory, IsDelete) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getNewsGroupID().getNewsGroupID());
            ps.setString(2, c.getNewTitle());
            ps.setString(3, c.getNewsHeading());
            ps.setString(4, c.getNewsImage());
            ps.setString(5, c.getNewsContent());
            ps.setDate(6, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setDate(7, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(8, c.getCreateBy().getAccountId());
            ps.setInt(9, c.getModifiedBy().getAccountId());
            ps.setString(10, c.getModifiedHistory());
            ps.setBoolean(11, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createNews: " + e.getMessage());
        }
    }

    public void updateNews(News c) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET NewsGroupID = ?,\n"
                    + "    NewTitle = ?,\n"
                    + "    NewsHeading = ?,\n"
                    + "    NewsImage = ?,\n"
                    + "    NewsContent = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = ?,\n"
                    + "    ModifiedHistory = ?,\n"
                    + "    IsDelete = 0\n"
                    + "WHERE NewID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getNewsGroupID().getNewsGroupID());
            ps.setString(2, c.getNewTitle());
            ps.setString(3, c.getNewsHeading());
            ps.setString(4, c.getNewsImage());
            ps.setString(5, c.getNewsContent());
            ps.setDate(6, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(7, c.getModifiedBy().getAccountId());
            ps.setString(8, c.getModifiedHistory());
            ps.setInt(9, c.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateNews: " + e.getMessage());
        }
    }

    public void deleteNews(String newId) {
        connect();
        try {
            String sql = "UPDATE News SET IsDelete = 1 where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteNews: " + e.getMessage());
        }
    }

    public List<News> getAllNewsByFilter(String filter, String search) {
        List<News> list = new ArrayList<>();
        switch (filter) {
            case "All":
                return searchNewsByAll(search);
            case "Title":
                return searchNewsByTitle(search);
            case "Heading":
                return searchNewsByHeading(search);
            case "Writer":
                return searchNewsByWriter(search);
//            case "NewsGroup":
//                return searchNewsByNewsGroup(search);
        }
        return list;
    }

    public List<News> searchNewsByAll(String search) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select News.*\n"
                    + "from News\n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        NewTitle like ('%" + search + "%')\n"
                    + "        or NewsHeading  like ('%" + search + "%')\n"
                    + "        or (select FirstName from Accounts\n"
                    + "        where createdBy = AccountID\n"
                    + "        ) like ('%" + search + "%')\n"
                    + "        or (select LastName from Accounts\n"
                    + "        where createdBy = AccountID\n"
                    + "        ) like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = getNews(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsByAll: " + e.getMessage());
        }
        return list;
    }

    public List<News> searchNewsByTitle(String search) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select News.*\n"
                    + "from News\n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        NewTitle like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = getNews(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsByTitle: " + e.getMessage());
        }
        return list;
    }

    private List<News> searchNewsByHeading(String search) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select News.*\n"
                    + "from News\n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        NewsHeading  like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = getNews(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsByHeading: " + e.getMessage());
        }
        return list;
    }

    private List<News> searchNewsByWriter(String search) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select News.*\n"
                    + "from News\n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        (select FirstName from Accounts\n"
                    + "        where createdBy = AccountID\n"
                    + "        ) like ('%" + search + "%')\n"
                    + "        or (select LastName from Accounts\n"
                    + "        where createdBy = AccountID\n"
                    + "        ) like ('%" + search + "%')\n"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = getNews(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsByWriter: " + e.getMessage());
        }
        return list;
    }
    // End News

    // ADMINROLES MANAGEMENT
    public List<String> getAccountRoles(Account a) {
        connect();
        List<String> roleList = new ArrayList<>();
        try {
            String sql = "select * from adminroles\n"
                    + "where accountId = ? and status = 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccountId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roleList.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("getAccountRoles: " + e.getMessage());
        }

        return roleList;
    }

    public List<AdminRole> getAdminRole(String accountId) {
        connect();
        List<AdminRole> list = new ArrayList<>();
        try {
            String sql = "select * from AdminRoles "
                    + "where AccountID = ?" + STATUS_TRUE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdminRole ar = new AdminRole();
                ar.setAccountId(getAccount(rs.getInt(1)));
                ar.setRoleId(getRole(rs.getString(2)));
                ar.setModifiedBy(getAccount(rs.getInt(3)));
                ar.setModifiedHistory(rs.getString(4));
                ar.setStatus(rs.getBoolean(5));
                list.add(ar);
            }
        } catch (Exception e) {
            System.out.println("getAdminRole: " + e.getMessage());
        }
        return list;
    }

    public void createAdminRole(int accountId, int roleId, int modifyAccount, boolean status) {
        connect();
        try {
            String sql = "INSERT INTO AdminRoles (AccountID, RoleID, ModifiedBy, ModifiedHistory, Status)\n"
                    + "VALUES (?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, roleId);
            ps.setInt(3, modifyAccount);
            ps.setString(4, new java.sql.Date(Base.Util.Formatter.getCurrentDate().getTime()).toString());
            ps.setBoolean(5, status);
            ps.execute();
        } catch (Exception e) {
            System.out.println("createAdminRole: " + e.getMessage());
        }
    }

    public void updateAdminRole(int accountId, String roleId, boolean status) {
        connect();
        try {
            String sql = "update AdminRoles\n"
                    + "set status = ?\n"
                    + "where AccountID = ? and RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, accountId);
            ps.setString(3, roleId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateAdminRole: " + e.getMessage());
        }
    }

    // END ADMINROLES MANAGEMENT
//REPOSITORY FOR ERROR
    public List<Base.Model.Error> getAllErrors() {
        connect();
        List<Base.Model.Error> list = new ArrayList<>();

        try {
            String sql = "select * from Errors where IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Base.Model.Error c = getErrors(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllErrors: " + e.getMessage());
        }

        return list;
    }

    public Base.Model.Error getErrors(int ErrorId) {
        connect();
        try {
            String sql = "select * from Errors where ErrorID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ErrorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Base.Model.Error c = new Base.Model.Error();
                c.setErrorId(rs.getInt(1));
                c.setErrorMessage((rs.getString(2)));
                c.setCreatedDate(rs.getDate(3));
                c.setCreatedBy(getAccount(rs.getInt(4)));
                c.setModifiedDate(rs.getDate(5));
                c.setModifiedBy(getAccount(rs.getInt(6)));
                c.setModifiedHistory(rs.getString(7));
                c.setIsDelete(rs.getBoolean(8));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getErrors: " + e.getMessage());
        }

        return null;
    }

    public void createError(Base.Model.Error c) {
        connect();
        try {
            String sql = "INSERT INTO Errors (ErrorMessage, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy, ModifiedHistory, IsDelete) VALUES "
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getErrorMessage());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setInt(3, c.getCreatedBy().getAccountId());
            ps.setDate(4, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(5, c.getModifiedBy().getAccountId());
            ps.setString(6, c.getModifiedHistory());
            ps.setBoolean(7, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createError: " + e.getMessage());
        }
    }

    public void updateError(Base.Model.Error c) {
        connect();
        try {
            String sql = "UPDATE Errors\n"
                    + "SET ErrorMessage = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = ?,\n"
                    + "    ModifiedHistory = ?\n"
                    + "    WHERE ErrorID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getErrorMessage());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(3, c.getModifiedBy().getAccountId());
            ps.setString(4, c.getModifiedHistory());
            ps.setInt(5, c.getErrorId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateErrors " + e.getMessage());
        }
    }

    public void deleteError(String errorId) {
        connect();
        try {
            String sql = "UPDATE Errors SET IsDelete = 1 where ErrorID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, errorId);

            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteErrors: " + e.getMessage());
        }
    }

    public List<Base.Model.Error> getAllErrorByFilter(String filter, String search) {
        connect();
        switch (filter) {
            case "All":
                return searchErrorByAll(search);
            case "ID":
                return searchErrorByID(search);
            case "Name":
                return searchErrorByName(search);
        }
        return null;
    }

    //END REPOSITORY FOR ERROR
    private List<Base.Model.Error> searchErrorByAll(String search) {
        connect();
        List<Base.Model.Error> list = new ArrayList<>();
        try {
            String sql = "select * from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorID like ('%" + search + "%') or "
                    + "ErrorMessage like ('%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Base.Model.Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        }
        return list;
    }

    private List<Base.Model.Error> searchErrorByID(String search) {
        connect();
        List<Base.Model.Error> list = new ArrayList<>();
        try {
            String sql = "select * from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorID like ('%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Base.Model.Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        }
        return list;
    }

    private List<Base.Model.Error> searchErrorByName(String search) {
        connect();
        List<Base.Model.Error> list = new ArrayList<>();
        try {
            String sql = "select * from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorMessage like ('%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Base.Model.Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchErrorByName: " + e.getMessage());
        }
        return list;
    }

    //REPOSITORY FOR WARRANTY
    public List<Warranty> getAllWarranty() {
        connect();
        List<Warranty> list = new ArrayList<>();

        try {
            String sql = "select * from Warranties where IsDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty c = getWarranties(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllWarranties: " + e.getMessage());
        }

        return list;
    }

    public Warranty getWarranties(int WarrantyId) {
        connect();
        try {
            String sql = "select * from Warranties where WarrantyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, WarrantyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty c = new Warranty();
                c.setWarrantyId(rs.getInt(1));
                c.setProductId(getProduct(rs.getString(2)));
                c.setErrorId(getErrors(rs.getInt(3)));
                c.setWarrantyDate(rs.getDate(4));
                c.setStatus(rs.getBoolean(5));
                c.setCreatedDate(rs.getDate(6));
                c.setCreatedBy(getAccount(rs.getInt(7)));
                c.setModifiedDate(rs.getDate(8));
                c.setModifiedBy(getAccount(rs.getInt(9)));
                c.setModifiedHistory(rs.getString(10));
                c.setIsDelete(rs.getBoolean(11));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getWarranties: " + e.getMessage());
        }

        return null;
    }

    public void deleteWarranty(String warrantyId) {
        connect();
        try {
            String sql = "UPDATE Warranties SET IsDelete = 1 where WarrantyID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warrantyId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteWarranty: " + e.getMessage());
        }
    }

    public void updateWarranty(Warranty c) {
        connect();
        try {
            String sql = "UPDATE Warranties SET ProductID=?, ErrorID=?, WarrantyDate=?, [Status]=?, ModifiedDate=?, ModifiedBy=?, ModifiedHistory=?\n"
                    + " WHERE WarrantyID= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getProductId().getProductId());
            ps.setInt(2, c.getErrorId().getErrorId());
            ps.setDate(3, Base.Util.Formatter.formatDate(c.getWarrantyDate()));
            ps.setBoolean(4, c.isStatus());
            ps.setDate(5, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(6, c.getModifiedBy().getAccountId());
            ps.setString(7, c.getModifiedHistory());
            ps.setInt(8, c.getWarrantyId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateWarranty " + e.getMessage());
        }
    }

    public void createWarranties(Warranty c) {
        connect();
        try {
            String sql = "INSERT INTO Warranties (ProductID, ErrorID, WarrantyDate, [Status], CreatedDate, CreatedBy, "
                    + "ModifiedDate, ModifiedBy, ModifiedHistory, IsDelete) VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getProductId().getProductId());
            ps.setInt(2, c.getErrorId().getErrorId());
            ps.setDate(3, Base.Util.Formatter.formatDate(c.getWarrantyDate()));
            ps.setBoolean(4, c.isStatus());
            ps.setDate(5, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setInt(6, c.getCreatedBy().getAccountId());
            ps.setDate(7, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(8, c.getModifiedBy().getAccountId());
            ps.setString(9, c.getModifiedHistory());
            ps.setBoolean(10, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createWarranty " + e.getMessage());
        }
    }
    // END REPOSITORY FOR WARRANTY

    // WARRANTY QUALITY EVALUATION
    public HashMap<Base.Model.Error, Integer> countFaults(String productSeriId, String errorId) {
        connect();
        HashMap<Base.Model.Error, Integer> h = new HashMap<>();
        try {
            String sql = "select e.ErrorID, count(p.ProductSeriID) NumOfProducts from Errors e\n"
                    + "left join Warranties w on e.ErrorID = w.ErrorID\n"
                    + "left join Products p on w.ProductID = p.ProductID and p.ProductSeriID = ?\n"
                    + "group by e.ErrorID\n"
                    + "order by e.ErrorID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productSeriId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                h.put(getErrors(rs.getInt(1)), rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("countFaults " + e.getMessage());
        }
        return h;
    }

    // IMAGE REPOSITORY
    public Image getImage(String imageId) {
        connect();
        try {
            String sql = "select ImageId, ImageName from Images where ImageId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, imageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image i = new Image();
                i.setImageId(rs.getInt(1));
                i.setImageName(rs.getString(2));
                return i;
            }
        } catch (Exception e) {
            System.out.println("getImage: " + e.getMessage());
        }

        return null;
    }

    public int createImage(Image i) {
        connect();
        try {
            String sql = "Insert into Images (ImageName) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, i.getImageName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("createImage " + e.getMessage());
        }
        return 0;
    }

    public void createSeriImage(int newSeriId, int imageName) {
        connect();
        try {
            String sql = "Insert into ProductSeriImages (ProductSeriID, ImageId) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newSeriId);
            ps.setInt(2, imageName);
            ps.execute();
        } catch (Exception e) {
            System.out.println("createSeriImage " + e.getMessage());
        }
    }

    // COLOR REPOSITORY
    public Color getColor(String colorId) {
        connect();
        try {
            String sql = "select ColorId, ColorName from Colors where ColorId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, colorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setColorId(rs.getInt(1));
                c.setColorName(rs.getString(2));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getColor: " + e.getMessage());
        }

        return null;
    }

    public List<Color> getAllColors() {
        connect();
        List<Color> list = new ArrayList<>();

        try {
            String sql = "select ColorId, ColorName from Colors";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setColorId(rs.getInt(1));
                c.setColorName(rs.getString(2));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getColor: " + e.getMessage());
        }

        return list;
    }

}
