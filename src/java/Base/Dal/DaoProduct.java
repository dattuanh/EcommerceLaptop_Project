/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Product;
import Base.Model.ProductSeri;
import static Base.Util.Utilities.IS_DELETE_FALSE_WITHOUT_WHERE;
import static Base.Util.Utilities.IS_DELETE_FALSE_WITH_WHERE;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author admin
 */
public class DaoProduct {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
//    DaoSerie daoSerie = new DaoSerie();
//    DaoAccount daoAccount =  new DaoAccount();
//    DaoProduct daoProduct = new DaoProduct();

    public void connect() {
        connection = (new DBContext()).connection;
    }

    public Product getProduct(String productId) {
        DaoSerie daoSerie = new DaoSerie();
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
                p.setProductSerieId(daoSerie.getProductSeri(rs.getString(2)));
                p.setStatus(rs.getBoolean(3));
                return p;
            }
        } catch (Exception e) {
            System.out.println("getProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    public List<Product> getProductListBySerie(String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    public List<Product> getProductListBySerie() {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    public Product getProduct(int productID) {
        DaoSerie daoSerie = new DaoSerie();
        DaoAccount daoAccount = new DaoAccount();
        DaoWarranties daoWarranty = new DaoWarranties();
        connect();
        try {
            String sql = "select Products.*,WarrantyID from Products\n"
                    + "left join Warranties\n"
                    + "on Products.ProductID = Warranties.ProductID\n"
                    + "where Products.ProductId = ? and Products.isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt(1));
                p.setProductSerieId(daoSerie.getProductSeri(rs.getString(2)));
                p.setStatus(rs.getBoolean(3));
                p.setBatchSerial(rs.getString(4));
                p.setCreatedDate(rs.getDate(5));
                p.setCreatedBy(daoAccount.getAccount(rs.getInt(6)));
                p.setModifiedDate(rs.getDate(7));
                p.setModifiedBy(daoAccount.getAccount(rs.getInt(8)));
                p.setModifiedHistory(rs.getString(9));
                if (rs.getString(11) != null) {
                    p.setWarranty(daoWarranty.getWarranties(rs.getInt(11)));
                }
                return p;
            }
        } catch (Exception e) {
            System.out.println("getProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return null;
    }

    public void updateProduct(Product p) {
        DaoSerie daoSerie = new DaoSerie();
        connect();
        try {
            String strUpdate = "UPDATE Products\n"
                    + "SET Sold = ? \n"
                    + "      ,BatchSerial = ?\n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ? \n"
                    + "WHERE ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(strUpdate);
            ps.setBoolean(1, p.isStatus());
            ps.setString(2, p.getBatchSerial());
            ps.setDate(3, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(4, p.getModifiedBy().getAccountId());
            ps.setString(5, getProduct(p.getProductId()).getModifiedHistory() + p.getModifiedHistory());
            ps.setInt(6, p.getProductId());
            ps.execute();
            String action = p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Update Product, ProductId: " + p.getProductId() + " <br>";
            daoSerie.updateSerieByProduct(p, action);
        } catch (Exception e) {
            System.out.println("updateProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
    }

    public void removeProduct(Product p) {
        DaoSerie daoSerie = new DaoSerie();
        String history = "";
        Boolean check = true;
        if (p.getModifiedHistory() == null) {
            check = false;
            history += p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Product <br>";
        } else {
            history += p.getModifiedHistory() + "Remove Product, ProductId: " + p.getProductId() + " <br>";
        }
        connect();
        try {
            String strDelete = "Update Products\n"
                    + "Set isDelete = 1 \n"
                    + "      ,ModifiedDate = ?\n"
                    + "      ,ModifiedBy = ?\n"
                    + "      ,ModifiedHistory = ?\n"
                    + "WHERE productID = ? ";
            PreparedStatement ps = connection.prepareStatement(strDelete);
            ps.setDate(1, new java.sql.Date(p.getModifiedDate().getTime()));
            ps.setInt(2, p.getModifiedBy().getAccountId());
//            ps.setString(3, getProduct(p.getProductId()).getModifiedHistory() + (p.getModifiedHistory() == null ? "" : p.getModifiedHistory())+ p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Product <br>");
            ps.setString(3, getProduct(p.getProductId()).getModifiedHistory() + history);
            ps.setInt(4, p.getProductId());
            ps.execute();

            String action = "";
            if (check) {
                action += "Remove Product, ProductId: " + p.getProductId() + " <br>";
            } else {
                action += p.getModifiedDate().toString() + " - " + p.getModifiedBy().getUserName() + ": Remove Product, ProductId: " + p.getProductId() + " <br>";
            }
            daoSerie.updateSerieByProduct(p, action);
        } catch (Exception e) {
            System.out.println("removeProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
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
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchProductByID(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchProductByBatchSerial(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchSoldProduct(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchAvailableProduct(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchActivatedWarranty(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    private List<Product> searchExpiredWarranty(String search, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
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
//            ps.setString(1, serieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchExpiredWarranty: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }

    public String getAllProductByFilter(String filter, String search) {
        String s = "";
        switch (filter) {
            case "All":
                s = "   Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n";
                return s;
            case "Name":
                s = "	ProductName like ('%" + search + "%')\n";
                return s;
            case "ID":
                s = "	Products.ProductID like ('%" + search + "%')\n";
                return s;
            case "Batch":
                s = "	BatchSerial  like ('%" + search + "%')\n";
                return s;
            case "Sold":
                s = "   Sold = 1 \n"
                        + " and (\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + ")";
                return s;
            case "Available":
                s = "   Sold = 0 \n"
                        + " and (\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + ")";
                return s;
            case "NotActivatedWarranty":
                s = "	WarrantyID is null and\n"
                        + "	(\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + "	)\n";
                return s;
            case "ActivatedWarranty":
                s = "	WarrantyID is not null and\n"
                        + "	(\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + "	)\n";
                return s;
            case "OnWarranty":
                s = "	Status = 1 and\n"
                        + "	(\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + "	)\n";
                return s;
            case "ExpiredWarranty":
                s = "	Status = 0 and\n"
                        + "	(\n"
                        + "	Products.ProductID like ('%" + search + "%')\n"
                        + "	or BatchSerial  like ('%" + search + "%')\n"
                        + "	)\n";
                return s;
        }
        return null;
    }

    public List<Product> searchProductBy(String search, int index, String order, int entry, String serieID) {
        List<Product> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select distinct(Products.ProductID) from Products\n"
                    + "left join Warranties\n"
                    + "on Products.ProductID = Warranties.ProductID and Warranties.isDelete = 0\n"
                    + "where Products.isDelete = 0 and\n"
                    + "ProductSeriID like ('" + serieID + "')\n"
                    + "and \n"
                    + "( \n"
                    + search
                    + ")\n"
                    + "order by ProductId asc\n"
                    + "offset ? rows fetch next " + entry + " rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getInt(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("searchProductBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public int countSearch(String search, String serieID) {
        List<ProductSeri> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select count(*) from Products\n"
                    + "left join Warranties\n"
                    + "on Products.ProductID = Warranties.ProductID and Warranties.isDelete = 0\n"
                    + "where Products.isDelete = 0 and\n"
                    + "ProductSeriID like ('" + serieID + "')\n"
                    + "and \n"
                    + "( \n"
                    + search
                    + ")";
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

    public void removeProductBySerie(ProductSeri s) {
        connect();
        try {
            String strSelect = "select * from Products\n"
                    + "where ProductSeriID = ? and isDelete = 0";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, s.getProductSeriId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt(1));
                p.setProductSerieId(s);
                p.setModifiedBy(s.getModifiedBy());
                p.setModifiedDate(s.getModifiedDate());
                p.setModifiedHistory(s.getModifiedHistory());
                removeProduct(p);
            }
        } catch (Exception e) {
            System.out.println("removeProductBySerie: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public List<Product> getSoldProduct(String productId) {
        List<Product> list = new ArrayList<>();
        connect();
        try {
            String sql = "select * from Products \n"
                    + "where productId like '%" + productId + "%' and Sold = 1" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = getProduct(productId);
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getSoldProduct: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }
    
    public List<Product> getAllSoldProducts() {
        List<Product> list = new ArrayList<>();
        connect();
        try {
            String sql = "select * from Products \n"
                    + "where IsDelete = 0 and sold = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = getProduct(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getAllProducts: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }
        return list;
    }
}
