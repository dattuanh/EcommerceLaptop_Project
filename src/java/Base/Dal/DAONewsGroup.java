/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.NewsGroup;
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
 * @author MSI
 */
public class DAONewsGroup {

    Connection connection;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    public List<NewsGroup> getAllNewsGroup() {
        connect();
        List<NewsGroup> list = new ArrayList<>();

        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup c = getNewsGroup(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNewGroup: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public NewsGroup getNewsGroup(int NewsGroupID) {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupID = "+NewsGroupID+"" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DAO dao = new DAO();
                NewsGroup ng = new NewsGroup();
                ng = new NewsGroup();
                ng.setNewsGroupID(rs.getInt(1));
                ng.setNewsGroupName(rs.getString(2));
                ng.setCreatedDate(rs.getDate(3));
                ng.setModifiedDate(rs.getDate(4));
                ng.setCreatedBy(dao.getAccount(rs.getInt(5)));
                ng.setModifiedBy(dao.getAccount(rs.getInt(6)));
                ng.setModifiedHistory(rs.getString(7));
                ng.setIsDelete(rs.getBoolean(8));
                return ng;
            }
        } catch (Exception e) {
            System.out.println("getNewsGroup: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void deleteNewsGroup(String newsGroupID) {
        connect();
        try {
            String sql = "UPDATE NewsGroup SET IsDelete = 1 where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newsGroupID);
            ps.execute();
//            deleteNews(newsGroupID);
        } catch (Exception e) {
            System.out.println("deletenewsGroup: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public String getAllNewsGroupByFilter(String filter, String search, int index) {
        String query;
        switch (filter) {
            case "All":
                query = " NewsGroupID like ('%" + search + "%')\n"
                    + "        or NewsGroupName  like (N'%" + search + "%')\n";
                break;
            case "Name":
                query = "NewsGroupName  like (N'%" + search + "%')";
                break;
            default:
                query = filter + " like ('%" + search + "%')\n";
                break;
        }
        return query;
    }
    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from NewsGroup \n"
                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
                    + "(\n"
                    + search
                    + " )";
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
    public List<NewsGroup> searchNewsBy(String search, int index, int entry, String isAsc) {
        List<NewsGroup> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select NewsGroup.*\n"
                    + "from NewsGroup\n"
                    + IS_DELETE_FALSE_WITH_WHERE + " and\n"
                    + "(\n"
                    + search
                    + " )"
                    + "order by NewsGroupID desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup c = getNewsGroup(rs.getInt(1));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchNewsGroupBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
                    + "        or NewsGroupName  like (N'%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup n = getNewsGroup(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchCategoryByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
                    + "        NewsGroupName  like (N'%" + search + "%')\n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup n = getNewsGroup(rs.getInt(1));
                list.add(n);
            }
        } catch (Exception e) {
            System.out.println("searchNewsGroupByName: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<NewsGroup> pagingNewsGroup(int index,int entries) {
        connect();
        List<NewsGroup> list = new ArrayList<>();
        try {
            String sql = "select * from NewsGroup\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + "order by NewsGroupID\n"
                    + "offset ? rows fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entries);
            ps.setInt(2, entries);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup c = getNewsGroup(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingNewsGroup: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from NewsGroup"+ IS_DELETE_FALSE_WITH_WHERE;
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
