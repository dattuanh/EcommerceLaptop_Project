/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.News;
import Base.Model.NewsGroup;
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
 * @author MSI
 */
public class DaoDisplay {

    Connection connection;

    public void connect() {
        connection = (new DBContext()).connection;
    }

    public List<NewsGroup> getAllNavigations() {
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
                    + "      ,[IsDelete] from NewsGroup "
                    + IS_DELETE_FALSE_WITH_WHERE
                    + " AND NewsGroupName = 'Navigation Bar'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsGroup c = getNavigation();
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNavigations: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<News> getAllNavBars() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where NewsGroupName = 'Navigation Bar'\n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNavBars: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    public List<News> getAllNavBarLevel_1() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where NewsGroupName = 'Navigation Bar'\n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNavBarLevel_1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    public NewsGroup getNavBarLevel_1(String NewsGroupID) {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, NewsGroupID);
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
            System.out.println("getAllNavBarLevel_1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public News getNavBarLevel_2(String NewID) {
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
                    + "      ,[IsDelete] from News where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, NewID);
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
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNavBarLevel_2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public List<News> getAllNavBarLevel_2(String id) {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where News.NewsGroupID = " + id + "\n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllNavBarLevel_2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

//    public NewsGroup getAllNavBarLevel_2(int NewsGroupID) {
//        connect();
//        try {
//            String sql = "select [NewsGroupID]\n"
//                    + "      ,[NewsGroupName]\n"
//                    + "      ,[CreatedDate]\n"
//                    + "      ,[ModifiedDate]\n"
//                    + "      ,[CreatedBy]\n"
//                    + "      ,[ModifiedBy]\n"
//                    + "      ,[ModifiedHistory]\n"
//                    + "      ,[IsDelete] from NewsGroup where NewsGroupID = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, NewsGroupID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                DAO dao = new DAO();
//                NewsGroup ng = new NewsGroup();
//                ng = new NewsGroup();
//                ng.setNewsGroupID(rs.getInt(1));
//                ng.setNewsGroupName(rs.getString(2));
//                ng.setCreatedDate(rs.getDate(3));
//                ng.setModifiedDate(rs.getDate(4));
//                ng.setCreatedBy(dao.getAccount(rs.getInt(5)));
//                ng.setModifiedBy(dao.getAccount(rs.getInt(6)));
//                ng.setModifiedHistory(rs.getString(7));
//                ng.setIsDelete(rs.getBoolean(8));
//                return ng;
//            }
//        } catch (Exception e) {
//            System.out.println("getNewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//
//        return null;
//    }
    public NewsGroup getNavigation() {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupName = 'Navigation Bar' ";
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
            System.out.println("getNavigation: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public NewsGroup getFooter() {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupName = 'Footer' ";
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
            System.out.println("getFooter: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

//    public void createNewsGroup(NewsGroup c) {
//        connect();
//        try {
//            String sql = "INSERT INTO NewsGroup (NewsGroupName, CreatedDate, ModifiedDate, CreatedBy, ModifiedBy, ModifiedHistory, IsDelete) VALUES \n"
//                    + "(?,?,?,?,?,?,?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, c.getNewsGroupName());
//            ps.setDate(2, Base.Util.Formatter.formatDate(c.getCreatedDate()));
//            ps.setDate(3, Base.Util.Formatter.formatDate(c.getModifiedDate()));
//            ps.setInt(4, c.getCreatedBy().getAccountId());
//            ps.setInt(5, c.getModifiedBy().getAccountId());
//            ps.setString(6, c.getModifiedHistory());
//            ps.setBoolean(7, c.isIsDelete());
//            ps.execute();
//        } catch (Exception e) {
//            System.out.println("createNewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//    }
//
//    public void deleteNewsGroup(String newsGroupID) {
//        connect();
//        try {
//            String sql = "UPDATE NewsGroup SET IsDelete = 1 where NewsGroupID = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, newsGroupID);
//            ps.execute();
////            deleteNews(newsGroupID);
//        } catch (Exception e) {
//            System.out.println("deletenewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//    }
//
//    public void updateNewsGroup(NewsGroup c) {
//        connect();
//
//        try {
//            String sql = "UPDATE NewsGroup\n"
//                    + "SET NewsGroupName = ?,\n"
//                    + "    ModifiedDate = ?,\n"
//                    + "    ModifiedBy = ?,\n"
//                    + "    ModifiedHistory = ?,\n"
//                    + "    IsDelete = 0\n"
//                    + "WHERE NewsGroupID = ?;";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, c.getNewsGroupName());
//            ps.setDate(2, Base.Util.Formatter.formatDate(c.getModifiedDate()));
//            ps.setInt(3, c.getModifiedBy().getAccountId());
//            ps.setString(4, c.getModifiedHistory());
//            ps.setInt(5, c.getNewsGroupID());
//            ps.execute();
//        } catch (Exception e) {
//            System.out.println("updateNewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//    }
//
//    public List<NewsGroup> getAllNewsGroupByFilter(String filter, String search, int index) {
//        connect();
//        List<NewsGroup> list = new ArrayList<>();
//        try {
//            String sql = "select * from NewsGroup\n"
//                    + "where IsDelete = 0\n"
//                    + "                    order by NewsGroupID\n"
//                    + "                    offset ? rows fetch next 5 rows only";
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 5);
//            ResultSet rs = ps.executeQuery();
//            switch (filter) {
//                case "All":
//                    return searchNewsGroupByAll(search);
//                case "Name":
//                    return searchNewsGroupByName(search);
//            }
//            while (rs.next()) {
//                NewsGroup c = getNewsGroup(rs.getInt(1));
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            System.out.println("pagingNewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    public List<NewsGroup> searchNewsGroupByAll(String search) {
//        connect();
//        List<NewsGroup> list = new ArrayList<>();
//        try {
//            String sql = "select NewsGroup.*\n"
//                    + "from NewsGroup \n"
//                    + "where isDelete = 0 and \n"
//                    + "        (\n"
//                    + "        NewsGroupID like ('%" + search + "%')\n"
//                    + "        or NewsGroupName  like (N'%" + search + "%')\n"
//                    + "        )";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                NewsGroup n = getNewsGroup(rs.getInt(1));
//                list.add(n);
//            }
//        } catch (Exception e) {
//            System.out.println("searchCategoryByAll: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    public List<NewsGroup> searchNewsGroupByName(String search) {
//        connect();
//        List<NewsGroup> list = new ArrayList<>();
//        try {
//            String sql = "select NewsGroup.*\n"
//                    + "from NewsGroup \n"
//                    + "where isDelete = 0 and \n"
//                    + "        (\n"
//                    + "        NewsGroupName  like (N'%" + search + "%')\n"
//                    + "        )";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                NewsGroup n = getNewsGroup(rs.getInt(1));
//                list.add(n);
//            }
//        } catch (Exception e) {
//            System.out.println("searchNewsGroupByName: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
//
//    public List<NewsGroup> pagingNewsGroup(int index) {
//        connect();
//        List<NewsGroup> list = new ArrayList<>();
//        try {
//            String sql = "select * from NewsGroup\n"
//                    + "order by NewsGroupID\n"
//                    + "offset ? rows fetch next 5 rows only";
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, (index - 1) * 5);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                NewsGroup c = getNewsGroup(rs.getInt(1));
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            System.out.println("pagingNewsGroup: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//
//        return list;
//    }
//
//    public int countTableItems() {
//        connect();
//        try {
//            String sql = "select count(*) from NewsGroup";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("getNumeberOf: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return 0;
//    }
//
//    public List<NewsGroup> getPopularNewsGroup() {
//        connect();
//        List<NewsGroup> list = new ArrayList<>();
//        String sql = "SELECT TOP 4 newsgroupId, COUNT(*) AS newsCount\n"
//                + "FROM news\n"
//                + "where IsDelete = 0\n"
//                + "GROUP BY newsgroupId\n"
//                + "ORDER BY newsCount DESC";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                NewsGroup c = getNewsGroup(rs.getInt(1));
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//        return list;
//    }
    public void updateNavBar(News n) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET NewsHeading = ?,\n"
                    + "    ModifiedDate = ?,\n"
                    + "    ModifiedBy = 1,\n"
                    + "    ModifiedHistory = ?\n"
                    + "WHERE NewID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, n.getNewsHeading());
            ps.setDate(2, Base.Util.Formatter.formatDate(n.getModifiedDate()));
//            ps.setInt(, n.getModifiedBy().getAccountId());
            ps.setString(3, getNews(n.getNewId()).getModifiedHistory() + n.getModifiedHistory());
            ps.setInt(4, n.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateNavBar: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

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
                    + "      ,[IsDelete] from News where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, NewID);
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
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNews: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public int addNavLevel1(NewsGroup c) {
        connect();
        try {
            String sql = "INSERT INTO NewsGroup (NewsGroupName, IsDelete) VALUES \n"
                    + "(?,0)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNewsGroupName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("addNavLevel1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public int addNavLevel2(News n) {
        connect();
        try {
            String sql = "INSERT INTO News (NewsGroupID, NewTitle, NewsContent, IsDelete) VALUES"
                    + "(?,?,?,0)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, n.getNewsGroupID() == null ? null : n.getNewsGroupID().getNewsGroupID() + "");
            ps.setString(2, n.getNewTitle());
            ps.setString(3, n.getNewsContent());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("addNavLevel2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public void removeNavLevel1(String id) {
        connect();
        try {
            String sql = "Delete from NewsGroup where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception e) {
            System.out.println("removeNavLevel1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void removeNavLevel2Level1ByLevel1(String id) {
        connect();
        try {
            String sql = "Delete from News where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception e) {
            System.out.println("removeNavLevel2Level1ByLevel1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateNavBarLevel2(News n) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET NewsHeading = ?,\n"
                    + "    NewsGroupID = ? \n"
                    //                    + "    ModifiedDate = ?,\n"
                    //                    + "    ModifiedBy = 1,\n"
                    //                    + "    ModifiedHistory = ?\n"
                    + "WHERE NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, n.getNewsHeading());
            System.out.println(n.getNewsGroupID().getNewsGroupID());
            ps.setInt(2, n.getNewsGroupID().getNewsGroupID());
//            ps.setDate(3, Base.Util.Formatter.formatDate(n.getModifiedDate()));
//            ps.setInt(, n.getModifiedBy().getAccountId());

//            ps.setString(4, getNews(n.getNewId()).getModifiedHistory() + n.getModifiedHistory());
            ps.setInt(3, n.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateNavBarLevel2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
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
                    + "      ,[IsDelete] from NewsGroup where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, NewsGroupID);
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

    public NewsGroup getNewsGroup(String name) {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupName = '" + name + "'";
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

    public void removeNavLevel2(String id) {
        connect();
        try {
            String sql = "Delete from News where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception e) {
            System.out.println("removeNavLevel2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public List<News> getAllFooters() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where NewsGroupName = 'Footer'\n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllFooters: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    public NewsGroup getFooterLevel_1(String NewsGroupID) {
        connect();
        try {
            String sql = "select [NewsGroupID]\n"
                    + "      ,[NewsGroupName]\n"
                    + "      ,[CreatedDate]\n"
                    + "      ,[ModifiedDate]\n"
                    + "      ,[CreatedBy]\n"
                    + "      ,[ModifiedBy]\n"
                    + "      ,[ModifiedHistory]\n"
                    + "      ,[IsDelete] from NewsGroup where NewsGroupID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, NewsGroupID);
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
            System.out.println("getFooterLevel_1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public News getFooterLevel_2(String NewID) {
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
                    + "      ,[IsDelete] from News where NewID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, NewID);
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
                return c;
            }
        } catch (Exception e) {
            System.out.println("getNavBarLevel_2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public List<News> getAllFooterLevel_2(String id) {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where News.NewsGroupID = " + id + " \n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllFooterLevel_2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    public List<News> getAllFooterLevel_1() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where NewsGroupName = 'Footer'\n"
                    + "and NewsGroup.IsDelete = 0\n"
                    + "order by NewsHeading";
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
                news.add(c);
            }
        } catch (Exception e) {
            System.out.println("getAllFooterLevel_1: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return news;
    }

    public int addFooterLevel2(News n) {
        connect();
        try {
            String sql = "INSERT INTO News (NewsGroupID, NewTitle, NewsContent, IsDelete) VALUES"
                    + "(?,?,?,0)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, n.getNewsGroupID() == null ? null : n.getNewsGroupID().getNewsGroupID() + "");
            ps.setString(2, n.getNewTitle());
            ps.setString(3, n.getNewsContent());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("addFooterLevel2: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }

    public News getCompanyInformation() {
        connect();
        try {
            String sql = "select News.* from NewsGroup join News\n"
                    + "on NewsGroup.NewsGroupID = News.NewsGroupID\n"
                    + "where NewsGroupName = 'Company Information'\n"
                    + "order by NewsHeading";
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
                return c;
            }
        } catch (Exception e) {
            System.out.println("getCompanyInformation: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public void updateCompanyInfor(News n) {
        connect();
        try {
            String sql = "UPDATE News\n"
                    + "SET NewsHeading = ?,\n"
                    + "    NewsImage = ?,\n"
                    + "    NewsContent = ?\n"
                    + "WHERE NewID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, n.getNewsHeading());
            ps.setString(2, n.getNewsImage());
            ps.setString(3, n.getNewsContent());
            ps.setInt(4, n.getNewId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateCompanyInfor: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

}
