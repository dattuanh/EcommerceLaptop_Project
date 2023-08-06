/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Image;
import Base.Model.Product;
import Base.Model.ProductSeri;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author admin
 */
public class DaoSerie {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
//    DaoAccount daoAccount = new DaoAccount();
//    DaoProduct daoProduct = new DaoProduct();
//    DaoCategory daoCategory = new DaoCategory();
//    DaoColor daoColor = new DaoColor();
    Paging paging = new Paging();

    public void connect() {
        connection = new DBContext().connection;
    }

    public ProductSeri getProductSeri(String productSeriId) {
        DaoAccount daoAccount = new DaoAccount();
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
                s.setCategoryId(new DaoCategory().getCategory(rs.getInt(6)));
                s.setManufacturer(rs.getString(7));
                s.setWarrantyTime(rs.getInt(8));
                s.setSize(rs.getString(9));
                s.setColor(new DaoColor().getColor(rs.getString(10)));
                s.setCreatedDate(rs.getDate(11));
                s.setModifiedDate(rs.getDate(12));
                s.setCreatedBy(daoAccount.getAccount(rs.getInt(13)));
                s.setModifiedBy(daoAccount.getAccount(rs.getInt(14)));
                s.setModifiedHistory(rs.getString(15));
                s.setIsDelete(rs.getBoolean(16));
                s.setStock(getProductStock(rs.getInt(1)));
                return s;
            }
        } catch (Exception e) {
            System.out.println("getProductSeri: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return null;
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public List<ProductSeri> getAllProductSerie() {
        List<ProductSeri> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

//    public ProductSeri getProductSeries(String serieID) {
//        connect();
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
        } finally {
            DbUtils.closeQuietly(connection);
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
                    + "      ,ModifiedHistory = ?"
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
            ps.setString(11, s.getModifiedHistory());
            ps.setInt(12, s.getCategoryId().getCategoryID());
            ps.setInt(13, s.getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateSerie: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateSerieByProduct(Product p, String action) {
        connect();
        try {
            String strUpdate = "UPDATE ProductSeries\n"
                    + "   SET ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ?\n"
                    + " WHERE ProductSeriID = ? ";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setString(3, getProductSeri(String.valueOf(p.getProductSerieId().getProductSeriId())).getModifiedHistory() + "<br> " + action);
            ps.setInt(4, p.getProductSerieId().getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateSerieByProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void removeSerie(ProductSeri p) {
        DaoProduct daoProduct = new DaoProduct();
//        String history = getProductSeri(String.valueOf(p.getProductSeriId())).getModifiedHistory() + p.getModifiedHistory() + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Series <br>";
//        String history = p.getModifiedHistory() + p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Series <br>";
//        String history = p.getModifiedHistory();

        String history = "";
        Boolean check = true;
        if (p.getModifiedHistory() == null) {
            check = false;
            history += p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Series <br>";
        } else {
            history += p.getModifiedHistory() + "Remove Series <br>";
        }
        connect();
        try {
//            p.setModifiedHistory(history);
            String strDelete = "Update ProductSeries\n"
                    + "Set isDelete = 1 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ?\n"
                    + "WHERE productSeriID = ? ";
            ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setString(3, getProductSeri(String.valueOf(p.getProductSeriId())).getModifiedHistory() + history);
            ps.setInt(4, p.getProductSeriId());
            ps.execute();

            p.setModifiedHistory(history);

            daoProduct.removeProductBySerie(p);
        } catch (Exception e) {
            System.out.println("removeSerie: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

//    public List<ProductSeri> getAllProductSerieByFilter(String filter, String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        switch (filter) {
//            case "All":
//                return searchProductSerieByAll(search);
//            case "Name":
//                return searchProductSerieByName(search);
//            case "Price":
//                return searchProductSerieByPrice(search);
//            case "Manufacturer":
//                return searchProductSerieByManufacturer(search);
//            case "WarrantyTime":
//                return searchProductSerieByWarrantyTime(search);
//            case "Size":
//                return searchProductSerieBySize(search);
//            case "Color":
//                return searchProductSerieByColor(search);
//            case "Quantity":
//                return searchProductSerieByQuantity(search);
//            case "Removed":
//                return searchRemovedProductSerie(search);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByAll(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	ProductName like ('%" + search + "%')\n"
//                    + "	or Price  like ('%" + search + "%')\n"
//                    + "	or Manufacturer like ('%" + search + "%')\n"
//                    + "	or WarrantyTime  like ('%" + search + "%')\n"
//                    + "	or Size like ('%" + search + "%')\n"
//                    + "	or Color like ('%" + search + "%')\n"
//                    + "	or (\n"
//                    + "		select count(*) from Products\n"
//                    + "		where Products.productSeriID = ProductSeries.ProductSeriID\n"
//                    + "	) like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByName(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	ProductName like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByName: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByPrice(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	Price  like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByPrice: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByManufacturer(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	Manufacturer like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByManufacturer: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByWarrantyTime(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	WarrantyTime  like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByWarrantyTime: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieBySize(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	Size like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieBySize: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByColor(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries join Colors\n"
//                    + "on ProductSeries.color = Colors.colorID\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "(\n"
//                    + "ColorName like ('%%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByColor: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByQuantity(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "(\n"
//                    + "(\n"
//                    + "select count(*) from Products\n"
//                    + "where Products.productSeriID = ProductSeries.ProductSeriID\n"
//                    + "and Products.Sold = 0\n"
//                    + ") like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchRemovedProductSerie(String search) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_TRUE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	ProductName like ('%" + search + "%')\n"
//                    + "	or Price  like ('%" + search + "%')\n"
//                    + "	or Manufacturer like ('%" + search + "%')\n"
//                    + "	or WarrantyTime  like ('%" + search + "%')\n"
//                    + "	or Size like ('%" + search + "%')\n"
//                    + "	or Color like ('%" + search + "%')\n"
//                    + "	or (\n"
//                    + "		select count(*) from Products\n"
//                    + "		where Products.productSeriID = ProductSeries.ProductSeriID\n"
//                    + "	) like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
    public List<ProductSeri> pagingSeries(int index) {
        List<ProductSeri> list = new ArrayList<>();
        int line = 9;
        if (paging.getNumeberOf("ProductSeries") - 9 * index < 0) {
            line = paging.getNumeberOf("ProductSeries") - 9 * (index - 1);
        }
        connect();
        try {
            String sql = "select * from ProductSeries\n"
                    + IS_DELETE_FALSE_WITH_WHERE + " \n"
                    + "order by ProductSeriId desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 9);
            ps.setInt(2, line);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = getProductSeri(rs.getString(1));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("pagingSeries: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public String getAllProductSerieByFilter(String filter, String search) {
        String s = "";
        switch (filter) {
            case "All":
                s = "ProductName like ('%" + search + "%')\n"
                        + "	or Price  like ('%" + search + "%')\n"
                        + "	or Manufacturer like ('%" + search + "%')\n"
                        + "	or WarrantyTime  like ('%" + search + "%')\n"
                        + "	or Size like ('%" + search + "%')\n"
                        + "	or Color like ('%" + search + "%')\n"
                        + "	or (\n"
                        + "		select count(*) from Products\n"
                        + "		where Products.productSeriID = ProductSeries.ProductSeriID\n"
                        + "	) like ('%" + search + "%')\n";
                return s;
            case "Name":
                s = "	ProductName like ('%" + search + "%')\n";
                return s;
            case "Price":
                s = "	Price  like ('%" + search + "%')\n";
                return s;
            case "Manufacturer":
                s = "	Manufacturer like ('%" + search + "%')\n";
                return s;
            case "WarrantyTime":
                s = "	WarrantyTime  like ('%" + search + "%')\n";
                return s;
            case "Size":
                s = "	Size like ('%" + search + "%')\n";
                return s;
            case "Color":
                s = "	(select Colors.ColorName from Colors\n"
                        + "	where ProductSeries.color = Colors.colorID) like ('%" + search + "%')\n";
                return s;
            case "Quantity":
                s = "select count(*) from Products\n"
                        + "where Products.productSeriID = ProductSeries.ProductSeriID\n"
                        + "and Products.Sold = 0\n"
                        + ") like ('%" + search + "%')\n";
                return s;
        }
        return null;
    }
//
//    private List<ProductSeri> searchProductSerieByAll(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        System.out.println(index);
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + search
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByName(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        System.out.println(index);
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + search
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByName: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByPrice(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + search
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByPrice: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByManufacturer(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + search
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByManufacturer: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByWarrantyTime(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	WarrantyTime  like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByWarrantyTime: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieBySize(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "	(\n"
//                    + "	Size like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieBySize: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByColor(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + "and\n"
//                    + "(\n"
//                    + search
//                    + ")\n"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByColor: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    private List<ProductSeri> searchProductSerieByQuantity(String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        connect();
//        try {
//            String sql = "select ProductSeries.*\n"
//                    + "from ProductSeries\n"
//                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
//                    + "(\n"
//                    + "(\n"
//                    + "select count(*) from Products\n"
//                    + "where Products.productSeriID = ProductSeries.ProductSeriID\n"
//                    + "and Products.Sold = 0\n"
//                    + ") like ('%" + search + "%')\n"
//                    + ")"
//                    + "order by ProductSeriId desc\n"
//                    + "offset ? rows fetch next 9 rows only";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 9);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductSeri p = getProductSeri(rs.getString(1));
//                list.add(p);
//            }
//        } catch (Exception e) {
//            System.out.println("searchProductSerieByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }

    public void restoreSerie(ProductSeri p) {
        connect();
        try {
            String strDelete = "Update ProductSeries\n"
                    + "Set isDelete = 0 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ?\n"
                    + "WHERE productSeriID = ? ";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
            ps.setString(3, p.getModifiedHistory());
            ps.setInt(4, p.getProductSeriId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("restoreSerie: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
//
//    public List<ProductSeri> getAllProductSerieByFilter2(String filter, String search, int index) {
//        List<ProductSeri> list = new ArrayList<>();
//        System.out.println(filter);
//        switch (filter) {
//            case "All":
//                return searchProductSerieByAll(search);
//            case "Color":
//                return searchProductSerieByColor(search);
//            case "Quantity":
//                return searchProductSerieByQuantity(search);
//            case "Removed":
//                return searchRemovedProductSerie(search);
//            default:
//                return searchProductSerieBy(filter + " like ('%" + search + "%') \n",index);
//        }
//    }
//    

    public List<ProductSeri> searchProductSerieBy(String search, int index, String order, int entry) {
        List<ProductSeri> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select ProductSeries.*\n"
                    + "from ProductSeries join Colors\n"
                    + "on ProductSeries.color = Colors.colorID\n"
                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
                    + "(\n"
                    + search
                    + "\n)\n"
                    + "order by ProductSeriId desc\n"
                    + "offset ? rows fetch next " + entry + " rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public int countSearch(String search) {
        List<ProductSeri> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from ProductSeries \n"
                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
                    + "(\n"
                    + search
                    + "\n)";
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

    public List<Image> getProductSerieImages(String productSerieId) {
        List<Image> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select * from Images\n"
                    + "where ImageId in\n"
                    + "(select ImageId from ProductSeriImages\n"
                    + "where ProductSeriID = ?)";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setString(1, productSerieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image i = new DaoImage().getImage(rs.getString(1));
                list.add(i);
            }
        } catch (Exception e) {
            System.out.println("searchProductSerieBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<ProductSeri> getProductSerieNameByCategory(int categoryId) {
        List<ProductSeri> list = new ArrayList<>();
        connect();
        try {
            String sql = "select ProductSeriID, ProductName from ProductSeries\n"
                    + "where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = new ProductSeri();
                s.setProductSeriId(rs.getInt(1));
                s.setProductSeriName(rs.getString(2));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("getProductSerieNameByCategory: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }
}
