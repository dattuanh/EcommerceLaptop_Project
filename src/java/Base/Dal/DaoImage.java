/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Image;
import Dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author admin
 */
public class DaoImage {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void connect() {
        connection = (new DBContext()).connection;
    }

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
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
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
                Image i = getImage(rs.getString(2));
                System.out.println("ten anh: " + i.getImageName());
                list.add(i);
            }
        } catch (Exception e) {
            System.out.println("getImageList: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Integer> getImageIdsByProductSeri(String serieId) {
        connect();
        List<Integer> list = new ArrayList<>();
        try {
            String strSelect = "select * from ProductSeriImages join Images\n"
                    + "on ProductSeriImages.ImageId=Images.ImageId\n"
                    + "where ProductSeriImages.ProductSeriID=?";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setString(1, serieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("getImageIdsByProductSeri: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public void removeImageProductSeri(int imageId, String productSeriId) {
        connect();
        try {
            String str = "delete from ProductSeriImages where ProductSeriID = ? and ImageId = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, productSeriId);
            ps.setInt(2, imageId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("removeImageProductSeri: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
