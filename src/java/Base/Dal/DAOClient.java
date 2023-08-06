/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.*;
import Base.Model.CartItem;
import Base.Model.Customer;
import Base.Model.Product;
import static Base.Util.Utilities.*;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Giang Minh
 */
public class DAOClient {

    Connection connection;
//    

    private void connect() {
        connection = new DBContext().connection;
    }

    public Customer getCustomer(String email, String encryptedPass, String decryptedPass) {
//        System.out.println("email " + email);
//        System.out.println("encryptedPass " + encryptedPass);
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
        }
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
            System.out.println("checkExistCustomer " + e.getMessage());
        }
        return null;
    }

    public void checkoutOrder(Customer cus, Cart cart, String paymentMethod) {
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
            stm.setString(6, cus.getAddress());
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
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("checkoutOrder: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Product getProductIdByProductSeriId(int pSeriId) {
        connect();
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
                p.setProductSerieId(new DaoSerie().getProductSeri(rs.getString(2)));
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

    public List<ProductSeri> getTrendySerie(int num) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String strSelect = "select top " + num + " ProductSeries.ProductSeriID, count(Products.ProductID)\n"
                    + "from ProductSeries left join Products \n"
                    + "on Products.ProductSeriID=ProductSeries.ProductSeriID\n"
                    + "where Sold=1 and ProductSeries.isDelete = 0 and Products.isDelete = 0\n"
                    + "group by ProductSeries.ProductSeriID\n"
                    + "order by count(Products.ProductID) desc";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = new DaoSerie().getProductSeri(rs.getString(1));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("getTrendySerie: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Color> getAllColor() {
        connect();
        List<Color> list = new ArrayList<>();
        try {
            String strSelect = "select colors.*, count(productSeriID) \n"
                    + "from colors left join ProductSeries\n"
                    + "on ProductSeries.color = colors.colorId\n"
                    + "and isDelete=0\n"
                    + "group by colors.colorId,colors.colorName";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Color c = new Color(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllColor: " + e.getMessage());
        }
        return list;
    }

    public List<Filter> getAllSize() {
        connect();
        List<Filter> list = new ArrayList<>();
        try {
            String strSelect = "select distinct(Size), count(productSeriID) from ProductSeries\n"
                    + "where isDelete = 0\n"
                    + "group by size";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String size = rs.getString(1);
                int quantity = rs.getInt(2);
                Filter f = new Filter(size, quantity);
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println("getAllSize: " + e.getMessage());
        }
        return list;
    }

    public List<Image> getImageList(String serieId) {
        connect();
        List<Image> list = new ArrayList<>();
        try {
            String strSelect = "select * from ProductSeriImages join Images\n"
                    + "on ProductSeriImages.ImageId=Images.ImageId\n"
                    + "where ProductSeriImages.ProductSeriID=?";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setString(1, serieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image i = new DaoImage().getImage(rs.getString(2));
                list.add(i);
            }
        } catch (Exception e) {
            System.out.println("getImageList: " + e.getMessage());
        }
        return list;
    }

    public List<ProductSeri> getAllProductSerieByFilter(String category, String search, int index) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String strSelect = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "ProductName like ('%" + search + "%')\n"
                    + "and CategoryID like '%" + category + "%'\n"
                    + "Order By ProductSeriID asc \n"
                    + "offset ? rows fetch next 6 rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = new DaoSerie().getProductSeri(rs.getString(1));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getAllProductSerieByFilter: " + e.getMessage());
        }
        return list;
    }

    public int countSearch(String category, String price, String color, String size, String search) {
        connect();
        try {
            String strSelect = "select COUNT(*) \n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "ProductName like ('%" + search + "%')\n"
                    + "and (" + price + ")\n"
                    + "and (" + category + ")\n"
                    + "and (" + size + ")\n"
                    + "and (" + color + ")\n";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearch: " + e.getMessage());
        }
        return 0;
    }

    public List<ProductSeri> getAllProductSerieByFilter2(String category, String price, String color, String size, String search, String orderBy, int index) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String strSelect = "select ProductSeries.*\n"
                    + "from ProductSeries\n"
                    + "where isDelete = 0 and\n"
                    + "ProductName like ('%" + search + "%')\n"
                    + "and (" + price + ")\n"
                    + "and (" + category + ")\n"
                    + "and (" + size + ")\n"
                    + "and (" + color + ")\n"
                    + "Order By " + orderBy + "\n"
                    + "offset ? rows fetch next 6 rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri p = new DaoSerie().getProductSeri(rs.getString(1));
                System.out.println(p.toString());
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("getAllProductSerieByFilter2: " + e.getMessage());
        }
        return list;
    }

    public List<ProductSeri> getArrivedProduct(int num) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String strSelect = "select top " + num + " ProductSeries.*\n"
                    + "from ProductSeries \n"
                    + "where ProductSeries.isDelete = 0 \n"
                    + "order by ProductSeries.createdDate desc";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = new DaoSerie().getProductSeri(rs.getString(1));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("getArrivedProduct: " + e.getMessage());
        }
        return list;
    }

    public String getStartPrice() {
        connect();
        try {
            String strSelect = "select TOP(1)Price from ProductSeries\n"
                    + "where IsDelete = 0\n"
                    + "order by Price asc";
            PreparedStatement ps = connection.prepareStatement(strSelect);
//            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("getStartPrice: " + e.getMessage());
        }
        return "0";
    }

    public String getEndPrice() {
        connect();
        try {
            String strSelect = "select TOP(1)Price from ProductSeries\n"
                    + "where IsDelete = 0\n"
                    + "order by Price desc";
            PreparedStatement ps = connection.prepareStatement(strSelect);
//            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("getStartPrice: " + e.getMessage());
        }
        return "0";
    }

    public List<ProductSeri> topBarSearch(String search) {
        connect();
        List<ProductSeri> list = new ArrayList<>();
        try {
            String sql = "select * from ProductSeries\n"
                    + "where isDelete = 0 and ProductName like ('%" + search + "%')\n"
                    + "order by ProductName asc\n"
                    + "offset 0 rows fetch next 4 rows only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSeri s = new DaoSerie().getProductSeri(rs.getString(1));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("pagingCustomer: " + e.getMessage());
        }
        return list;
    }

    public News getInfoNews() {
        connect();
        String sql = "select top 1 * from news\n"
                + "where NewsGroupID = \n"
                + "(select NewsGroupID from NewsGroup\n"
                + "where NewsGroupName like '%footer%')\n"
                + "and NewTitle like '%info%'" + IS_DELETE_FALSE_WITHOUT_WHERE;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News c = new News();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(new DAONewsGroup().getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(new DaoAccount().getAccount(rs.getInt(9)));
                c.setModifiedBy(new DaoAccount().getAccount(rs.getInt(10)));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<News> getSliderNews() {
        connect();
        List<News> list = new ArrayList<>();
        String sql = "select NewID from news\n"
                + "where IsSlider = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News c = new DAONews().getNews(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getSliderNews: " + e);
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public Order getNewesetOrderByCustomer(int CustomerID) {
        connect();
        try {
            String sql = "select top 1 * from Orders \n"
                    + "where CustomerID = ? \n"
                    + "order by OrderDate desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, CustomerID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt(1));
                o.setCustomer(new DaoCustomer().getCustomer(rs.getInt(2)));
                o.setOrderDate(rs.getDate(3));
                o.setTotalPrice(rs.getDouble(4));
                o.setStatus(new DAOOrder().getOrderStatus(rs.getString(5)));
                o.setPaymentDate(rs.getDate(6));
                o.setPaymentMethod(rs.getString(7));
                o.setCashReceive(rs.getDouble(8));
                o.setCashBack(rs.getDouble(9));
                o.setAddress(rs.getString(10));
                return o;
            }
        } catch (Exception e) {
            System.out.println("getNewesetOrderByCustomer: " + e.getMessage());
        }
        return null;
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
                o.setStatus(new DAOOrder().getOrderStatus(rs.getString(5)));
                o.setPaymentDate(rs.getDate(6));
                o.setPaymentMethod(rs.getString(7));
                o.setCashReceive(rs.getDouble(8));
                o.setCashBack(rs.getDouble(9));
                o.setAddress(rs.getString(10));
                olist.add(o);
            }
        } catch (Exception e) {
            System.out.println("getOrderByCustomer: " + e.getMessage());
        }
        return olist;
    }

    public List<News> searchNewsByAll(String search) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "SELECT [NewID]\n"
                    + "      ,[NewsGroupID]\n"
                    + "      ,[NewTitle]\n"
                    + "      ,[NewsHeading]\n"
                    + "      ,[NewsImage]\n"
                    + "      ,[NewsContent]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] FROM News WHERE isDelete = 0 and NewTitle <> 'Ecomputer' and NewsContent LIKE (N'%" + search + "%') OR NewTitle LIKE (N'%" + search + "%')";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new DAONews().getNews(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsByAll: " + e.getMessage());
        }
        return list;
    }

    public List<News> getOnTopNews() {
        connect();
        List<News> list = new ArrayList<>();
        String sql = "SELECT TOP 4 [NewID]\n"
                + "      ,[NewsGroupID]\n"
                + "      ,[NewTitle]\n"
                + "      ,[NewsHeading]\n"
                + "      ,[NewsImage]\n"
                + "      ,[NewsContent]\n"
                + "      ,[CreatedDate]\n"
                + "      ,[ModifiedDate]\n"
                + "      ,[CreatedBy]\n"
                + "      ,[ModifiedBy]\n"
                + "      ,[ModifiedHistory]\n"
                + "      ,[IsDelete] FROM News\n"
                + "	where IsDelete = 0 and NewTitle <> 'Ecomputer'\n"
                + "	ORDER BY NEWID();";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News c = new News();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(new DAONewsGroup().getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(new DaoAccount().getAccount(rs.getInt(9)));
                c.setModifiedBy(new DaoAccount().getAccount(rs.getInt(10)));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
