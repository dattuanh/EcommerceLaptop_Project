package Base.Dal;

import Base.Model.Customer;
import Base.Model.Order;
import Base.Model.Warranty;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author MSI
 */
public class DaoWarranties {

    Connection connection = null;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    //REPOSITORY FOR WARRANTY
    public List<Warranty> getAllWarranty() {
        connect();
        List<Warranty> list = new ArrayList<>();

        try {
            String sql = "select [WarrantyID]\n"
                    + "      ,[ProductID]\n"
                    + "      ,[ErrorID]\n"
                    + "      ,[WarrantyDate]\n"
                    + "      ,[Status]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete]\n"
                    + "      ,[Content] \n"
                    + "      ,WarrantyTime\n"
                    + "      ,WarrantyPrice\n"
                    + "      from Warranties" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty c = getWarranties(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllWarranties: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public Warranty getWarranties(int WarrantyId) {
        connect();
        try {
            String sql = "select [WarrantyID]\n"
                    + "      ,[ProductID]\n"
                    + "      ,[ErrorID]\n"
                    + "      ,[WarrantyDate]\n"
                    + "      ,[Status]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete]\n"
                    + "      ,[Content]\n"
                    + "      ,WarrantyTime\n"
                    + "      ,WarrantyPrice\n"
                    + "      from Warranties where WarrantyID = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, WarrantyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DAO dao = new DAO();
                DaoError daoError = new DaoError();
                Warranty c = new Warranty();
                c.setWarrantyId(rs.getInt(1));
                c.setProductId(dao.getProduct(rs.getString(2)));
                c.setErrorId(daoError.getErrors(rs.getInt(3)));
                c.setWarrantyDate(rs.getDate(4));
                c.setStatus(rs.getBoolean(5));
                c.setCreatedDate(rs.getDate(6));
                c.setCreatedBy(dao.getAccount(rs.getInt(7)));
                c.setModifiedDate(rs.getDate(8));
                c.setModifiedBy(dao.getAccount(rs.getInt(9)));
                c.setModifiedHistory(rs.getString(10));
                c.setIsDelete(rs.getBoolean(11));
                c.setContent(rs.getString(12));
                c.setWarrantyTime(rs.getInt(13));
                c.setWarrantyPrice(rs.getDouble(14));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getWarranties: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateWarranty(Warranty c) throws Exception {
        connect();
        try {
            String sql = "UPDATE Warranties SET ProductID=?, ErrorID=?, WarrantyDate=?, [Status]=?, ModifiedDate=?, ModifiedBy=?, "
                    + "ModifiedHistory=? , Content=?, WarrantyTime=?, WarrantyPrice=?\n"
                    + " WHERE WarrantyID= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getProductId().getProductId());
            ps.setInt(2, c.getErrorId().getErrorId());
            ps.setDate(3, Base.Util.Formatter.formatDate(c.getWarrantyDate()));
            ps.setBoolean(4, c.isStatus());
            ps.setDate(5, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(6, c.getModifiedBy().getAccountId());
            ps.setString(7, c.getModifiedHistory());
            ps.setString(8, c.getContent());
            ps.setInt(9, c.getWarrantyTime());
            ps.setDouble(10, c.getWarrantyPrice());
            ps.setInt(11, c.getWarrantyId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateWarranty " + e.getMessage());
            throw new Exception("updateWarranty " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void createWarranties(Warranty c) throws Exception {
        connect();
        try {
            String sql = "INSERT INTO Warranties (ProductID, ErrorID, WarrantyDate, [Status], CreatedDate, CreatedBy, "
                    + "ModifiedDate, ModifiedBy, ModifiedHistory, IsDelete, Content, WarrantyTime, WarrantyPrice) VALUES\n"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getProductId().getProductId());
            ps.setInt(2, 3);
            ps.setDate(3, Base.Util.Formatter.formatDate(c.getWarrantyDate()));
            ps.setBoolean(4, c.isStatus());
            ps.setDate(5, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setInt(6, c.getCreatedBy().getAccountId());
            ps.setDate(7, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(8, c.getModifiedBy().getAccountId());
            ps.setString(9, c.getModifiedHistory());
            ps.setBoolean(10, c.isIsDelete());
            ps.setString(11, c.getContent());
            ps.setInt(12, c.getWarrantyTime());
            ps.setDouble(13, c.getWarrantyPrice());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createWarranty " + e.getMessage());
            throw new Exception("createWarranty " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

//    public List<Warranty> getAllWarrantyByFilter(String filter, String search) {
//        List<Warranty> list = new ArrayList<>();
//        switch (filter) {
//            case "All":
//                list = searchWarrantyByAll(search);
//                break;
//            case "Product":
//                list = searchWarrantyByProductName(search);
//                break;
//            case "Error":
//                list = searchWarrantyByError(search);
//                break;
//            case "Customer":
//                list = searchWarrantyByCustomerName(search);
//                break;
//        }
//        return list;
//    }
    private List<Warranty> searchWarrantyByError(String search) {
        connect();
        List<Warranty> list = new ArrayList<>();
        try {
            String sql = "select [WarrantyID]\n"
                    + "      ,[ProductID]\n"
                    + "      ,[ErrorID]\n"
                    + "      ,[WarrantyDate]\n"
                    + "      ,[Status]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete]\n"
                    + "      ,[Content] \n"
                    + "from Warranties\n"
                    + "where IsDelete =0\n"
                    + "and ErrorID in\n"
                    + "(select ErrorID from Errors\n"
                    + "where ErrorMessage like N'%" + search + "%')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty o = getWarranties(rs.getInt(1));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyByError: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Warranty> searchWarrantyByAll(String search) {
        connect();
        List<Warranty> list = new ArrayList<>();
        try {
            String sql = "select [WarrantyID]\n"
                    + "                        ,[ProductID]\n"
                    + "                        ,[ErrorID]\n"
                    + "                       ,[WarrantyDate]\n"
                    + "                        ,[Status]\n"
                    + "                         ,[CreatedDate]\n"
                    + "                         ,[CreatedBy]\n"
                    + "                         ,[ModifiedDate]\n"
                    + "                        ,[ModifiedBy]\n"
                    + "                         ,[ModifiedHistory]\n"
                    + "                         ,[IsDelete]\n"
                    + "                         ,[Content] \n"
                    + "                    from Warranties\n"
                    + "                    where IsDelete =0\n"
                    + "                    and [ErrorID] like ('%" + search + "%')\n"
                    + "                     or [Status] like ('%" + search + "%')\n"
                    + "                    or [ProductID] like ('%" + search + "%')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty n = getWarranties(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Warranty> searchWarrantyByProductName(String search) {
        connect();
        List<Warranty> list = new ArrayList<>();
        try {
            String sql = "select * from Warranties w\n"
                    + "join Products p on w.ProductID = p.ProductID\n"
                    + "where ProductSeriID in\n"
                    + "(select ProductSeriID from ProductSeries\n"
                    + "where ProductName like N'%" + search + "%')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty n = getWarranties(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyByCustomer: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Warranty> searchWarrantyByCustomerName(String search) {
        connect();
        List<Warranty> list = new ArrayList<>();
        try {
            String sql = "select * from Warranties\n"
                    + "where ProductID in\n"
                    + "(select ProductID from OrderItems\n"
                    + "where OrderID in (\n"
                    + "select OrderID from Orders\n"
                    + "where CustomerID in (select CustomerID from Customers\n"
                    + "where LastName + ' ' + FirstName like N'%" + search + "%')))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty n = getWarranties(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyByCustomerName: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Warranty> pagingWarrantys(int index) {
        connect();
        List<Warranty> list = new ArrayList<>();
        try {
            String sql = "select [WarrantyID]\n"
                    + "      ,[ProductID]\n"
                    + "      ,[ErrorID]\n"
                    + "      ,[WarrantyDate]\n"
                    + "      ,[Status]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete]\n"
                    + "      ,[Content] from Warranties\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + "                    order by WarrantyID\n"
                    + "                    offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty c = getWarranties(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingWarranty: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from Warranties" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("countTableItems: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    // END REPOSITORY FOR WARRANTY
    // WARRANTY QUALITY EVALUATION
    public HashMap<Base.Model.Error, Integer> countFaults(String productSeriId) {
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
                DaoError dao = new DaoError();
                h.put(dao.getErrors(rs.getInt(1)), rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("countFaults " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return h;
    }

    public int countFaults(String productSeriId, String errorId) {
        connect();

        try {
            String sql = "select count(w.ErrorID) from Warranties w\n"
                    + "join Products p\n"
                    + "on w.ProductID = p.ProductID\n"
                    + "where w.ErrorID = ?\n"
                    + "and p.ProductSeriID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, errorId);
            ps.setString(2, productSeriId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countFaults " + e.getMessage());
        }

        return 0;
    }

    public static String getQualityEvaluationEnum(int count) {
        int op = (count == 0) ? 0 : (count < 5) ? 1 : 2;
        switch (op) {
            case 0:
                return "Good";
            case 1:
                return "Warning";
            case 2:
                return "Unsatisfactory";
            default:
                throw new AssertionError();
        }
    }

//    public static int getQualityEvaluationEnum(String evaluation) {
//        switch (evaluation) {
//            case "Good":
//                return 
//                break;
//            default:
//                throw new AssertionError();
//        }
//    }
    public static String getButton(int count) {
        int op = (count == 0) ? 0 : (count < 5) ? 1 : 2;
        switch (op) {
            case 0:
                return "btn btn-outline-success btn-md";
            case 1:
                return "btn btn-outline-warning btn-md";
            case 2:
                return "btn btn-outline-danger btn-md";
            default:
                throw new AssertionError();
        }
    }

    public Customer getCustomerByWarrantyId(String warrantyId) {
        connect();
        try {
            String sql = "select OrderID from Orders\n"
                    + "where OrderID = \n"
                    + "(select OrderID from OrderItems\n"
                    + "where ProductID = \n"
                    + "(select ProductID from Warranties\n"
                    + "where WarrantyID = ?))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warrantyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new DAOOrder().getOrder(rs.getString(1));
                return o.getCustomer();
            }
        } catch (Exception e) {
            System.out.println("getCustomerByWarrantyId: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public Order getOrderByWarrantyId(String warrantyId) {
        connect();
        try {
            String sql = "select * from OrderItems\n"
                    + "where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, warrantyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new DAOOrder().getOrder(rs.getString(2));
                return o;
            }
        } catch (Exception e) {
            System.out.println("getOrderByWarrantyId: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return null;
    }

    public String getAllWarrantyByFilter(String filter, String search) {
        String query = "";
        switch (filter) {
            case "All":
                return "select * from warranties\n";
            case "Product":
                query += "select * from Warranties w\n"
                        + "join Products p on w.ProductID = p.ProductID\n"
                        + "where ProductSeriID in\n"
                        + "(select ProductSeriID from ProductSeries\n"
                        + "where ProductName like N'%" + search + "%')\n";
                break;
            case "Error":
                query += "select [WarrantyID]\n"
                        + "      ,[ProductID]\n"
                        + "      ,[ErrorID]\n"
                        + "      ,[WarrantyDate]\n"
                        + "      ,[Status]\n"
                        + "      ,[CreatedDate]\n"
                        + "      ,[CreatedBy]\n"
                        + "      ,[ModifiedDate]\n"
                        + "      ,[ModifiedBy]\n"
                        + "      ,[ModifiedHistory]\n"
                        + "      ,[IsDelete]\n"
                        + "      ,[Content] \n"
                        + "from Warranties\n"
                        + "where IsDelete =0\n"
                        + "and ErrorID in\n"
                        + "(select ErrorID from Errors\n"
                        + "where ErrorMessage like N'%" + search + "%')";
                break;
            case "Customer":
                query += "select * from Warranties\n"
                        + "where ProductID in\n"
                        + "(select ProductID from OrderItems\n"
                        + "where OrderID in (\n"
                        + "select OrderID from Orders\n"
                        + "where CustomerID in (select CustomerID from Customers\n"
                        + "where LastName + ' ' + FirstName like N'%" + search + "%')))\n";
                break;
            default:
                return "";
        }
        return query;
    }

    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from Warranties \n"
                    + IS_DELETE_FALSE_WITH_WHERE + "\n"
                    + search;
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchWarranty: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public List<Warranty> searchWarrantyBy(String search, int index, int entry, String isAsc) {
        List<Warranty> list = new ArrayList<>();
        connect();
        try {
            String strSelect = ""
                    + search
                    + "\norder by WarrantyID desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty w = getWarranties(rs.getInt(1));
                list.add(w);
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public Warranty getWarrantyByProductId(String productId) {
        connect();
        try {
            String str = "select * from Warranties where ProductId = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return getWarranties(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("searchWarrantyBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        
        return null;
    }
}
