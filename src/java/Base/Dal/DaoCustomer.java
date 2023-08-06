/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Customer;
import Base.Model.Order;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Giang Minh
 */
public class DaoCustomer {

    Connection connection = null;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    public List<Customer> getAllCustomers() {
        connect();
        List<Customer> list = new ArrayList<>();

        try {
            String sql = "SELECT CustomerID\n"
                    + "FROM Customers" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllCustomers: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public Customer getCustomer(int customerId) {
        connect();
        try {
            String sql = "SELECT CustomerID, FirstName, LastName, Gender, Email, UserName, "
                    + "[Password], [Address], Phone, CreatedDate, [Image], ModifiedHistory, IsDelete\n"
                    + "FROM Customers where CustomerID = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
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
                c.setPassword(Base.Util.EncryptionDecryptionAES.enDecrypt(rs.getString(7), c.getEmail(), false));
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
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public Customer getCustomerByWarranty(String warranty) {
        connect();
        try {
            // query to get customerId
            String sql = "select CustomerID from Orders\n"
                    + "where CustomerID = \n"
                    + "(select CustomerID from Orders\n"
                    + "where OrderID = (select oi.OrderID from Warranties w\n"
                    + "join OrderItems oi on w.ProductID = oi.ProductID\n"
                    + "where w.WarrantyID = ?))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warranty);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getCustomerByWarranty: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
            ps.setString(6, Base.Util.EncryptionDecryptionAES.enDecrypt(c.getPassword(), c.getEmail(), true));
            ps.setString(7, c.getAddress());
            ps.setString(8, c.getPhone());
            ps.setDate(9, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setString(10, c.getImage());
            ps.setString(11, c.getModifiedHistory());
            ps.setBoolean(12, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
            ps.setString(6, Base.Util.EncryptionDecryptionAES.enDecrypt(c.getPassword(), c.getEmail(), true));
            ps.setString(7, c.getAddress());
            ps.setString(8, c.getPhone());
            ps.setDate(9, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setString(10, c.getImage());
            ps.setString(11, c.getModifiedHistory());
            ps.setBoolean(12, c.isIsDelete());
            ps.setInt(13, c.getCustomerId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("DaoUpdateCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateCustomerPassword(Customer c) {
        connect();
        try {
            String sql = "UPDATE Customers\n"
                    + "set password = ?\n"
                    + "WHERE CustomerID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Base.Util.EncryptionDecryptionAES.enDecrypt(c.getPassword(), c.getEmail(), true));
            ps.setInt(2, c.getCustomerId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCustomerDao: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

//    public List<Customer> getAllCustomerByFilter(String filter, String search) {
//        List<Customer> list;
//        switch (filter) {
//            case "All":
//                list = searchCustomerByAll(search);
//                break;
//            case "Name":
//                list = searchCustomerByName(search);
//                break;
//            default:
//                list = searchCustomer(search, filter);
//                break;
//        }
//        return list;
//    }
    public String getAllCustomerByFilter(String filter, String search) {
        if (search.isEmpty()) {
            return "";
        }
        String query = " and ";
        switch (filter) {
            case "All":
                query += ""
                        + "LastName + ' ' + FirstName like ('%" + search + "%')\n"
//                        + "        or FirstName like ('%" + search + "%') \n"
//                        + "        or LastName like ('%" + search + "%')\n"
                        + "        or Email like ('%" + search + "%')\n"
                        + "        or UserName like ('%" + search + "%')\n"
                        + "        or Phone like ('%" + search + "%') \n";
                break;
            case "Name":
                query += "FirstName like ('%" + search + "%') \n"
                        + "        or LastName like ('%" + search + "%')\n"
                        + "        or LastName + ' ' + FirstName like ('%" + search + "%')\n";
                break;
            default:
                query += filter + " like ('%" + search + "%')\n";
                break;
        }
        return query;
    }

    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from Customers \n"
                    + IS_DELETE_FALSE_WITH_WHERE + " \n"
                    + search;
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public List<Customer> searchCustomerBy(String search, int index, int entry, String isAsc) {
        List<Customer> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select Customers.*\n"
                    + "from Customers\n"
                    + IS_DELETE_FALSE_WITH_WHERE + "\n"
                    + search
                    + "\norder by CustomerId desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
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
                    + "        or LastName + ' ' + FirstName like ('%" + search + "%')\n"
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Customer> searchCustomer(String search, String filter) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select Customers.*\n"
                    + "from Customers \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + filter + "         like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByEmail: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
                    + "        or LastName + ' ' + FirstName like ('%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByName: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    // order by CustomerID
    public List<Customer> pagingCustomer(int index, int entries) {
        connect();
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select * from Customers\n" + IS_DELETE_FALSE_WITH_WHERE
                    + "order by CustomerID\n"
                    + "offset ? rows fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entries);
            ps.setInt(2, entries);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<String> getAllCustomerEmail() {
        connect();
        List<String> list = new ArrayList<>();

        try {
            String sql = "select Email from Customers\n"
                    + "where Email is not null" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("getAllCustomerEmail: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<String> getAllCustomerUserName() {
        connect();
        List<String> list = new ArrayList<>();

        try {
            String sql = "select UserName from Customers\n"
                    + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("getAllCustomerUserName: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from Customers" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getNumeberOf: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public Customer checkExistCustomer(String email) {
        connect();
        try {
            String sql = "select * from Customers where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(1));
                c.setFirstName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setGender(rs.getBoolean(4));
                c.setEmail(rs.getString(5));
                c.setUserName(rs.getString(6));
                c.setPassword(Base.Util.EncryptionDecryptionAES.enDecrypt(rs.getString(7), email, false));
                c.setAddress(rs.getString(8));
                c.setPhone(rs.getString(9));
                c.setCreatedDate(rs.getDate(10));
                c.setImage(rs.getString(11));
                c.setModifiedHistory(rs.getString(12));
                c.setIsDelete(rs.getBoolean(13));
                return c;
            }
        } catch (Exception e) {
            System.out.println("checkExistCustomer " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return null;
    }

    public Customer getCustomer(String email, String encryptedPass, String decryptedPass) throws Exception {
        connect();
        try {
            String sql = "select * from Customers where Email = ? and [Password] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, encryptedPass);
//            System.out.println("daoclient encypt:" + encryptedPass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt(1));
                c.setFirstName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setGender(rs.getBoolean(4));
                c.setEmail(rs.getString(5));
                c.setUserName(rs.getString(6));
                c.setPassword(decryptedPass);
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
            throw new Exception("DaoCustomerGetCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public void createClientCustomer(Customer c, String sql) {
        connect();
        int temp = 1;
        try {
//            String sql = "INSERT INTO Customers (FirstName, LastName, Email, UserName, [Password], [Address], Phone, CreatedDate, Image, ModifiedHistory, IsDelete) VALUES"
//                    + "(?,?,?,?,?,?,?,?,?,?,?)";
            connection = (new DBContext()).connection;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(temp++, c.getFirstName());
            ps.setString(temp++, c.getLastName());
            ps.setString(temp++, c.getEmail());
            ps.setString(temp++, c.getUserName());
            ps.setString(temp++, c.getPassword());

            if (!c.getPhone().isEmpty()) {
                ps.setString(temp++, c.getPhone());
            }
            ps.setDate(temp++, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setString(temp++, c.getModifiedHistory());
            ps.setBoolean(temp++, c.isIsDelete());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createClientCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public Customer getCustomerByProductId(String productId) {
        connect();
        try {
            String sql = "select * from OrderItems\n"
                    + "  where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new DAOOrder().getOrder(rs.getString(2));
                return o.getCustomer();
            }
        } catch (Exception e) {
            System.out.println("getCustomerByProductId: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public void updateCustomerPassword(String pass, int customerId) {
        connect();
        try {
            String sql = "UPDATE Customers\n"
                    + "SET [Password] = ?\n"
                    + "WHERE CustomerID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setInt(2, customerId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCustomerPassword: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public Customer getCustomerByEmail(String email) {
        connect();

        try {
            String sql = "select * from customers where email = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return getCustomer(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("getCustomerByEmail: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }
}
