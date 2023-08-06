/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.News;
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
public class DAONews {

    Connection connection = null;

    public void connect() {
        connection = new DBContext().connection;
    }
    //Start News

//    public List<News> getAllNews() {
//        connect();
//        List<News> list = new ArrayList<>();
//
//        try {
//            String sql = "select [NewID]\n"
//                    + "      ,[NewsGroupID]\n"
//                    + "      ,[NewTitle]\n"
//                    + "      ,[NewsHeading]\n"
//                    + "      ,[NewsImage]\n"
//                    + "      ,[NewsContent]\n"
//                    + "      ,[CreatedDate]\n"
//                    + "      ,[ModifiedDate]\n"
//                    + "      ,[CreatedBy]\n"
//                    + "      ,[ModifiedBy]\n"
//                    + "      ,[ModifiedHistory]\n"
//                    + "      ,[IsDelete] \n"
//                    + "      ,IsPresent \n"
//                    + "from News" + IS_DELETE_FALSE_WITH_WHERE;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                News c = getNews(rs.getInt(1));
//                list.add(c);
//            }
//        } catch (Exception e) {
//            System.out.println("getAllNews: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//
//        return list;
//    }
    public News getNews(int NewID) {
        connect();
        try {
            String sql = "select [NewID]\n"
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
                    + "      ,[IsDelete] \n"
                    + "      ,[IsPresent] \n"
                    + "      ,[IsSlider] \n"
                    + "from News where newId = " + NewID + ""
                    + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = new News();
                DAONewsGroup dao = new DAONewsGroup();
                DAO daoAccount = new DAO();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(dao.getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(daoAccount.getAccount(rs.getInt(9)));
                c.setModifiedBy(daoAccount.getAccount(rs.getInt(10)));
                c.setIsDelete(rs.getBoolean(12));
                c.setIsPresent(rs.getBoolean(13));
                c.setIsSlider(rs.getBoolean(14));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public News getNewsC(int NewID) {
        connect();
        try {
            String sql = "select [NewID]\n"
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
                    + "      ,[IsDelete] \n"
                    + "      ,[IsPresent] \n"
                    + "from News where newId = " + NewID + ""
                    + IS_DELETE_FALSE_WITHOUT_WHERE
                    + IS_PRESENT_TRUE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = new News();
                DAONewsGroup dao = new DAONewsGroup();
                DAO daoAccount = new DAO();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(dao.getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(daoAccount.getAccount(rs.getInt(9)));
                c.setModifiedBy(daoAccount.getAccount(rs.getInt(10)));
                c.setIsDelete(rs.getBoolean(12));
                c.setIsPresent(rs.getBoolean(13));
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNewsC: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public void createNews(News c) {
        connect();
        try {
            String sql = "INSERT INTO News (NewsGroupID, NewTitle, NewsHeading, NewsImage, NewsContent, "
                    + "CreatedDate, ModifiedDate, CreatedBy, ModifiedBy, ModifiedHistory, IsDelete, IsPresent, IsSlider) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getNewsGroupID().getNewsGroupID());
            ps.setString(2, c.getNewTitle());
            ps.setString(3, c.getNewsHeading());
            ps.setString(4, c.getNewsImage());
            ps.setString(5, c.getNewsContent());
            ps.setDate(6, Base.Util.Formatter.formatDate(c.getCreatedDate()));
            ps.setDate(7, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(8, c.getCreateBy().getAccountId());
            ps.setInt(9, c.getModifiedBy().getAccountId());
            ps.setString(10, c.getModifiedHistory());
            ps.setBoolean(11, c.isIsDelete());
            ps.setBoolean(12, c.isIsPresent());
            ps.setBoolean(13, c.isIsSlider());
            ps.execute();
        } catch (Exception e) {
            System.out.println("createNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateNews(News c) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET NewsGroupID = ?,\n"
                    + "    NewTitle = ?,\n"
                    + "    NewsHeading = ?,\n"
                    + "    NewsImage = ?,\n"
                    + "    NewsContent = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = ?,\n"
                    + "    ModifiedHistory = ?,\n"
                    + "    IsDelete = ?,\n"
                    + "    IsPresent = ?,\n"
                    + "    IsSlider = ?\n"
                    + "WHERE NewID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c.getNewsGroupID().getNewsGroupID());
            ps.setString(2, c.getNewTitle());
            ps.setString(3, c.getNewsHeading());
            ps.setString(4, c.getNewsImage());
            ps.setString(5, c.getNewsContent());
            ps.setDate(6, Base.Util.Formatter.formatDate(c.getModifiedDate()));
            ps.setInt(7, c.getModifiedBy().getAccountId());
            ps.setString(8, c.getModifiedHistory());
            ps.setBoolean(9, c.isIsDelete());
            ps.setBoolean(10, c.isIsPresent());
            ps.setBoolean(11, c.isIsSlider());
            ps.setInt(12, c.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
    
    public void updateNewsStatus(News c) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET "
                    + "    IsPresent = ?,\n"
                    + "    IsSlider = ?\n"
                    + "WHERE NewID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, c.isIsPresent());
            ps.setBoolean(2, c.isIsSlider());
            ps.setInt(3, c.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("DAOupdateNewsStatus: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void deleteNews(String newId) {
        connect();
        try {
            String sql = "UPDATE News SET IsDelete = 1 where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public String getAllNewsByFilter(String filter, String search) {
        if (search.isEmpty()) {
            return "";
        }
        String query = " and ";
        switch (filter) {
            case "All":
                query += "(NewID like ('%" + search + "%') "
                        + " or NewTitle like ('%" + search + "%') \n"
                        + " or NewsHeading like ('%" + search + "%')) \n";
                break;
            case "Title":
                query += "NewTitle like (N'%" + search + "%')\n";
                break;
            case "Heading":
                query += "NewsHeading  like (N'%" + search + "%')\n";
                break;
        }
        return query;
    }

    public int countSearch(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from News \n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + IS_PRESENT_TRUE_WITH_WHERE
                    + search;
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchNewsC: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public int countSearchA(String search) {
        connect();
        try {
            String strSelect = "select Count(*)\n"
                    + "from News \n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + search;
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchNewsA: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public List<News> searchNewsByA(String search, int index, int entry, String isAsc) {
        List<News> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select *\n"
                    + "from News\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + search
                    + "order by NewID desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = new News();
                DAONewsGroup dao = new DAONewsGroup();
                DAO daoAccount = new DAO();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(dao.getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(daoAccount.getAccount(rs.getInt(9)));
                c.setModifiedBy(daoAccount.getAccount(rs.getInt(10)));
                c.setIsDelete(rs.getBoolean(12));
                c.setIsPresent(rs.getBoolean(13));
                c.setIsSlider(rs.getBoolean(14));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchNewsBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<News> searchNewsByC(String search, int index, int entry, String isAsc) {
        List<News> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select *\n"
                    + "from News\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + IS_PRESENT_TRUE_WITH_WHERE
                    + search
                    + "order by NewID desc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = new News();
                DAONewsGroup dao = new DAONewsGroup();
                DAO daoAccount = new DAO();
                c.setNewId(rs.getInt(1));
                c.setNewsGroupID(dao.getNewsGroup(rs.getInt(2)));
                c.setNewTitle(rs.getString(3));
                c.setNewsHeading(rs.getString(4));
                c.setNewsImage(rs.getString(5));
                c.setNewsContent(rs.getString(6));
                c.setCreatedDate(rs.getDate(7));
                c.setModifiedDate(rs.getDate(8));
                c.setCreateBy(daoAccount.getAccount(rs.getInt(9)));
                c.setModifiedBy(daoAccount.getAccount(rs.getInt(10)));
                c.setIsDelete(rs.getBoolean(12));
                c.setIsPresent(rs.getBoolean(13));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("searchNewsBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<News> pagingNews(int index, int entries) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select * from News\n"
                    + IS_DELETE_FALSE_WITH_WHERE + IS_PRESENT_TRUE_WITH_WHERE
                    + "order by NewID\n"
                    + "offset ? rows fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entries);
            ps.setInt(2, entries);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = getNews(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<News> pagingNews(int index) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select * from News\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + IS_PRESENT_TRUE_WITH_WHERE
                    + "order by NewID\n"
                    + "offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = getNews(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countTableItems() {
        connect();
        try {
            String sql = "select count(*) from News" + IS_DELETE_FALSE_WITH_WHERE + IS_PRESENT_TRUE_WITH_WHERE;
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

    public int countTableItemsA() {
        connect();
        try {
            String sql = "select count(*) from News" + IS_DELETE_FALSE_WITH_WHERE;
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

    public List<News> getRecentNews() {
        connect();
        List<News> list = new ArrayList<>();
        String sql = "select top 3 [NewID]\n"
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
                + "      ,[IsDelete] from News \n"
                + IS_DELETE_FALSE_WITH_WHERE + IS_PRESENT_TRUE_WITH_WHERE
                + "order by CreatedDate\n"
                + "desc";
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
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<News> getPolicy() {
        connect();
        List<News> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM News\n"
                + "JOIN newsgroup ON news.newsGroupID = newsgroup.newsGroupID\n"
                + "WHERE news.IsDelete = 0 AND newsgroup.newsGroupName LIKE '%chính sách%';";
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
            System.out.println("getPolicyOf: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int countNews() {
        connect();
        try {
            String sql = "select count(*) from News " + IS_DELETE_FALSE_WITH_WHERE + IS_PRESENT_TRUE_WITH_WHERE;
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

    public List<News> pagingNewsByAccount(int AuthorId, int index) {
        connect();
        List<News> list = new ArrayList<>();
        try {
            String sql = "select * from News\n"
                    + IS_DELETE_FALSE_WITH_WHERE
                    + IS_PRESENT_TRUE_WITH_WHERE
                    + "and CreatedBy = ?\n"
                    + "                    order by NewID\n"
                    + "                   offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, AuthorId);
            ps.setInt(2, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News c = getNews(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
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
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public List<News> getNewsByGroup(int NewsGroupID) {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select [NewID]\n"
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
                    + "      ,[IsDelete]\n"
                    + "      ,[IsPresent]from News\n"
                    + "where NewsGroupID = " + NewsGroupID + ""
                    + "and isdelete = 0"
                    + IS_PRESENT_TRUE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
                c.setIsDelete(rs.getBoolean(11));
                c.setIsPresent(rs.getBoolean(12));
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getNewsByGroup: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
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
                + IS_DELETE_FALSE_WITH_WHERE + IS_PRESENT_TRUE_WITH_WHERE
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
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public News getNextNews(int NewsId) {
        connect();
        String sql = "SELECT TOP 1  [NewID]\n"
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
                + "      ,[IsDelete]\n"
                + "      ,[IsPresent]FROM news \n"
                + "WHERE [NewID] > " + NewsId + " and Isdelete=0\n"
                + IS_PRESENT_TRUE_WITH_WHERE
                + "ORDER BY [NewID] ASC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            //st.setInt(1, NewsId);

            while (rs.next()) {
                News c = getNewsC(rs.getInt(1));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;

    }

    public News getPrevNews(int NewsId) {
        connect();
        String sql = "SELECT TOP 1 [NewID]\n"
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
                + "      ,[IsDelete]\n"
                + "      ,[IsPresent]FROM news \n"
                + "WHERE [NewID] < " + NewsId + " and Isdelete=0\n"
                + IS_PRESENT_TRUE_WITH_WHERE
                + "ORDER BY [NewID] DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News c = getNewsC(rs.getInt(1));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;

    }
    // End News

    public List<News> getNewsByAccount(int AuthorId) {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select [NewID]\n"
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
                    + "      ,[IsDelete] from News\n"
                    + "WHERE CreatedBy = ? and IsDelete = 0"
                    + IS_PRESENT_TRUE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, AuthorId);
            ResultSet rs = ps.executeQuery();
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
                c.setIsDelete(rs.getBoolean(11));
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getNewsByAccount: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    // ADMINROLES MANAGEMENT
}
