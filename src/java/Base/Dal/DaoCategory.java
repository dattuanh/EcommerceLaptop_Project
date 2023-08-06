/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Category;
import Base.Model.ProductSeri;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author admin
 */
public class DaoCategory {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void connect() {
        connection = (new DBContext()).connection;
    }

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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return null;
    }

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public Category getCategory(int categoryId) {
        DaoAccount daoAccount = new DaoAccount();
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
                c.setCreatedBy(daoAccount.getAccount(rs.getInt(5)));
                c.setModifiedBy(daoAccount.getAccount(rs.getInt(6)));
                c.setModifiedHistory(rs.getString(7));
                return c;
            }
        } catch (Exception e) {
            System.out.println("GetCategory: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
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
                    + "      ,ModifiedHistory = ?\n"
                    + "where CategoryID=?";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps = connection.prepareStatement(strUpdate);
            ps.setString(1, c.getCategoryName());
            ps.setDate(2, new java.sql.Date(c.getModifiedDate().getTime()));
            ps.setInt(3, c.getModifiedBy().getAccountId());
            ps.setString(4, getCategory(c.getCategoryID()).getModifiedHistory() + c.getModifiedHistory());
            ps.setInt(5, c.getCategoryID());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCategory: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void removeCategory(Category c) {
        DaoSerie daoSerie = new DaoSerie();
        connect();
        try {
            String strDelete = " select * from ProductSeries\n"
                    + "  where isDelete = 0 and CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setInt(1, c.getCategoryID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = new ProductSeri();
                p.setProductSeriId(rs.getInt(1));
                p.setModifiedBy(c.getModifiedBy());
                p.setModifiedDate(c.getModifiedDate());
                p.setModifiedHistory(c.getModifiedHistory());
                daoSerie.removeSerie(p);
            }

            strDelete = "Update Categories \n"
                    + "SET\n"
                    + "      isDelete = 1\n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ?\n"
                    + "where CategoryID=?";
            ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(c.getModifiedDate().getTime()));
            ps.setInt(2, c.getModifiedBy().getAccountId());
            ps.setString(3, getCategory(c.getCategoryID()).getModifiedHistory() + c.getModifiedHistory());
            ps.setInt(4, c.getCategoryID());
            ps.execute();

        } catch (Exception e) {
            System.out.println("removeCategory: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    //SEARCH
//    public List<Category> getAllCategoryByFilter(String filter, String search) {
//        List<Category> list = new ArrayList<>();
//        switch (filter) {
//            case "All":
//                return searchCategoryByAll(search);
//            case "Name":
//                return searchCategoryByName(search);
//            case "Quantity":
//                return searchCategoryByQuantity(search);
//        }
//        return list;
//    }
    public String getAllCategoryByFilter(String filter, String search) {
        String s = "";
        switch (filter) {
            case "All":
                s = "CategoryName  like ('%" + search + "%') \n"
                        + "or (select count(*) as Quantity\n"
                        + "		from ProductSeries\n"
                        + "		where Categories.categoryID=ProductSeries.CategoryID\n"
                        + "             and ProductSeries.isDelete = 0\n"
                        + "	) like ('%" + search + "%')\n";
                return s;
            case "Name":
                s = "	CategoryName like ('%" + search + "%')\n";
                return s;
            case "Quantity":
                s = "   (select count(*) as Quantity\n"
                        + "		from ProductSeries\n"
                        + "		where Categories.categoryID=ProductSeries.CategoryID\n"
                        + "             and ProductSeries.isDelete = 0\n"
                        + "	) like ('%" + search + "%') \n ";
                return s;
        }
        return null;
    }

    public List<Category> searchCategoryBy(String search, int index, String order, int entry) {
        List<Category> list = new ArrayList<>();
        connect();
        try {
            String sql = "select Categories.*\n"
                    + "from Categories \n"
                    + "where isDelete = 0 and \n"
                    + "(\n"
                    + search
                    + ")\n"
                    + "order by CategoryId asc\n"
                    + "offset ? rows fetch next " + entry + " rows only";
            System.out.println(entry);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from Categories \n"
                    + "where isDelete = 0 and \n"
                    + "(\n"
                    + search
                    + ") \n";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearch: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public List<Category> searchCategoryByAll(String search) {
        List<Category> list = new ArrayList<>();
        connect();
        try {
            String sql = "select Categories.*\n"
                    + "from Categories \n"
                    + "where isDelete = 0 and ("
                    + "CategoryName  like ('%" + search + "%') \n"
                    + "or (select count(*) as Quantity\n"
                    + "		from ProductSeries\n"
                    + "		where Categories.categoryID=ProductSeries.CategoryID\n"
                    + "	) like ('%" + search + "%')"
                    + ") ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getCategory(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Category> searchCategoryByName(String search) {
        List<Category> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Category> searchCategoryByQuantity(String search) {
        List<Category> list = new ArrayList<>();
        connect();
        try {
            String sql = "select Categories.*\n"
                    + "from Categories \n"
                    + "where isDelete = 0 "
                    + "and "
                    + "(select count(*) as Quantity\n"
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

//    public HashMap<String, List<String>> getCategoriesAndSeri() {
//        connect();
//        HashMap<String, List<String>> list = new HashMap<>();
//        try {
//            String sql = "select * from Categories\n"
//                    + "where IsDelete = 0";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Category c = getCategory(rs.getInt(1));
//                list.put(c.getCategoryName(), new DaoSerie().getProductSerieNameByCategory(c.getCategoryID()));
//            }
//        } catch (Exception e) {
//            System.out.println("getCategoriesAndSeri: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
}
