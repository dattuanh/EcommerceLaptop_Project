/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Color;
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
public class DaoColor {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void connect() {
        connection = (new DBContext()).connection;
    }

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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return null;
    }

    public List<Color> getAllColors() {
        List<Color> list = new ArrayList<>();
        connect();
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
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return list;
    }

}
