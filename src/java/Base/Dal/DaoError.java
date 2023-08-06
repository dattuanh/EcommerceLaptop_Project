/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import static Base.Util.Utilities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import Base.Model.Error;
import Dal.DBContext;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class DaoError {

    //REPOSITORY FOR ERROR
    Connection connection = null;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    public List<Error> getAllErrors() {
        connect();
        List<Error> list = new ArrayList<>();

        try {
            String sql = "select [ErrorID] from Errors" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error c = getErrors(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllErrors: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public Error getErrors(int ErrorId) {
        connect();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete], RepairTime, RepairPrice, RepairStatus, RepairProduct from Errors where ErrorID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ErrorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error c = new Error();
                DAO dao = new DAO();
                c.setErrorId(rs.getInt(1));
                c.setErrorMessage((rs.getString(2)));
                c.setCreatedDate(rs.getDate(3));
                c.setCreatedBy(dao.getAccount(rs.getInt(4)));
                c.setModifiedDate(rs.getDate(5));
                c.setModifiedBy(dao.getAccount(rs.getInt(6)));
                c.setModifiedHistory(rs.getString(7));
                c.setContent(rs.getString(8));
                c.setIsDelete(rs.getBoolean(9));
                c.setRepairDate(rs.getDate(10));
                c.setMoney(rs.getDouble(11));
                c.setRepairStatus(rs.getInt(12));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getErrors: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public void createError(Error c) {
        connect();
        try {
            String sql = "INSERT INTO Errors (ErrorMessage, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy, ModifiedHistory, "
                    + "Content, IsDelete, RepairTime, RepairPrice, RepairStatus) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getErrorMessage());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setInt(3, c.getCreatedBy().getAccountId());
            ps.setDate(4, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(5, c.getModifiedBy().getAccountId());
            ps.setString(6, c.getModifiedHistory());
            ps.setString(7, c.getContent());
            ps.setBoolean(8, c.isIsDelete());
            ps.setDate(9, Base.Util.Formatter.formatDate(c.getRepairDate()));
            ps.setDouble(10, c.getMoney());
            ps.setInt(11, c.getRepairStatus());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createError: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateError(Error c) {
        connect();
        try {
            String sql = "UPDATE Errors\n"
                    + "SET ErrorMessage = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = ?,\n"
                    + "    ModifiedHistory = ?,\n"
                    + "    Content = ?,\n"
                    + "    RepairTime=?, RepairPrice=?, RepairStatus=?\n"
                    + "    WHERE ErrorID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getErrorMessage());
            ps.setDate(2, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(3, c.getModifiedBy().getAccountId());
            ps.setString(4, c.getModifiedHistory());
            ps.setString(5, c.getContent());
            ps.setDate(6, Base.Util.Formatter.formatDate(c.getRepairDate()));
            ps.setDouble(7, c.getMoney());
            ps.setInt(8, c.getRepairStatus());
            ps.setInt(9, c.getErrorId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateErrors " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public List<Error> getAllErrorByFilter(String filter, String search, int index) {
        connect();
        List<Error> list = new ArrayList<>();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete] from Errors\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + "order by ErrorID\n"
                    + "offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            switch (filter) {
                case "All":
                    return searchErrorByAll(search);
                case "ID":
                    return searchErrorByID(search);
                case "Name":
                    return searchErrorByName(search);
            }
            while (rs.next()) {
                Error c = getErrors(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingErrors: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    //END REPOSITORY FOR ERROR
    private List<Error> searchErrorByAll(String search) {
        connect();
        List<Error> list = new ArrayList<>();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete] from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorID like ('%" + search + "%') or "
                    + "ErrorMessage like (N'%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    private List<Error> searchErrorByID(String search) {
        connect();
        List<Error> list = new ArrayList<>();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete] from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorID like ('%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    private List<Error> searchErrorByName(String search) {
        connect();
        List<Error> list = new ArrayList<>();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete] from Errors\n"
                    + "where IsDelete = 0 and\n"
                    + "(ErrorMessage like (N'%" + search + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error e = getErrors(rs.getInt(1));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("searchCustomerByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Error> pagingNewsGroup(int index) {
        connect();
        List<Error> list = new ArrayList<>();
        try {
            String sql = "select [ErrorID]\n"
                    + "      ,[ErrorMessage]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[Content]\n"
                    + "      ,[IsDelete] from Errors\n"
                    + "order by ErrorID\n"
                    + "offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Error c = getErrors(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingErrors: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from Errors";
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

}
