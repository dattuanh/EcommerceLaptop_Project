/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Account;
import Base.Model.Role;
import static Base.Util.Utilities.IS_DELETE_FALSE_WITHOUT_WHERE;
import static Base.Util.Utilities.IS_DELETE_FALSE_WITH_WHERE;
import Dal.DBContext;
import java.nio.charset.StandardCharsets;
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
 * @author Giang Minh
 */
public class DaoAccount {

    Connection connection;

    public void connect() {
        connection = new DBContext().connection;
    }

    public Account getAccount(int accountId) {
        connect();
        try {
            String sql = "select * from Accounts where AccountID = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println("base: " + rs.getString(12));
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setPassword(Base.Util.EncryptionDecryptionAES.enDecrypt(rs.getString(3), rs.getString(12), false));
                a.setFirstName(rs.getString(4));
                a.setLastName(rs.getString(5));
                a.setGender(rs.getBoolean(6));
                a.setDateOfBirth(rs.getDate(7));
                a.setPhoneNumber(rs.getString(8));
                a.setImage(rs.getString(9));
                a.setIsDelete(rs.getBoolean(10));
                a.setSalt(rs.getString(12));
                return a;
            }
        } catch (Exception e) {
            System.out.println("getAccount_accountId: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public Account getAccount(String userName, String password) {
        connect();
        try {
            String sql = "select * from Accounts where Username = ? and [Password] = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, Base.Util.EncryptionDecryptionAES.enDecrypt(password, getAccountSalt(userName), true));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setPassword(Base.Util.EncryptionDecryptionAES.enDecrypt(rs.getString(3), rs.getString(12), false));
                a.setFirstName(rs.getString(4));
                a.setLastName(rs.getString(5));
                a.setGender(rs.getBoolean(6));
                a.setDateOfBirth(rs.getDate(7));
                a.setPhoneNumber(rs.getString(8));
                a.setImage(rs.getString(9));
                a.setIsDelete(rs.getBoolean(10));
                a.setSalt(rs.getString(12));
                return a;
            }
        } catch (Exception e) {
            System.out.println("getAccount_userName_password: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }
    
    public String getAccountSalt(String userName) {
        connect();
        try {
            String sql = "select * from Accounts where Username = ?" + IS_DELETE_FALSE_WITHOUT_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(12);
            }
        } catch (Exception e) {
            System.out.println("getAccountSalt: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return "";
    }

    public List<Account> getAllAccounts() {
        connect();
        List<Account> list = new ArrayList<>();

        try {
            String sql = "select * from Accounts" + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getString(2), rs.getString(3));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("getAllAccounts: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public List<Account> pagingAccounts(int index, int entries) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select * from Accounts\n" + IS_DELETE_FALSE_WITH_WHERE
                    + "order by AccountID\n"
                    + "offset ? rows fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * entries);
            ps.setInt(2, entries);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("pagingAccounts: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    public int createAccount(Account a) throws Exception {
        connect();
        try {
            String sql = "INSERT INTO Accounts (Username, [Password], FirstName, LastName, DateOfBirth, PhoneNumber, [Image], IsDelete, Salt) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getUserName());
            ps.setString(2, Base.Util.EncryptionDecryptionAES.enDecrypt(a.getPassword(), a.getSalt(), true));
            ps.setString(3, a.getFirstName());
            ps.setString(4, a.getLastName());
            ps.setDate(5, Base.Util.Formatter.formatDate(a.getDateOfBirth()));
            ps.setString(6, a.getPhoneNumber());
            ps.setString(7, a.getImage());
            ps.setBoolean(8, false);
            ps.setString(9, a.getSalt());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("createAccount: " + e.getMessage());
            throw new Exception(e.getMessage());

        } finally {
            DbUtils.closeQuietly(connection);
        }
        return -1;
    }

    public void updateAccount(Account a) throws Exception {
        connect();
        try {
            String sql = "UPDATE Accounts\n"
                    + "SET Username = ?, [Password] = ?, FirstName = ?, LastName = ?, \n"
                    + "Gender = ?, DateOfBirth = ?, PhoneNumber = ?, [Image] = ?, IsDelete = ?\n"
                    + "WHERE AccountID = ?";
            System.out.println("1");
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getUserName());
            System.out.println("2");
            System.out.println("pass: " + a.getPassword());
            System.out.println("salt: " + a.getSalt());
            ps.setString(2, Base.Util.EncryptionDecryptionAES.enDecrypt(a.getPassword(), a.getSalt(), true));
            System.out.println("3");
            ps.setString(3, a.getFirstName());
            ps.setString(4, a.getLastName());
            ps.setBoolean(5, a.isGender());
            System.out.println("4");
            ps.setDate(6, Base.Util.Formatter.formatDate(a.getDateOfBirth()));
            System.out.println("5");
            ps.setString(7, a.getPhoneNumber());
            ps.setString(8, a.getImage());
            ps.setBoolean(9, a.isIsDelete());
            System.out.println("5");
            ps.setInt(10, a.getAccountId());
            System.out.println("6");
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateAccount: " + e.getMessage());
            throw new Exception("DaoupdateAccount");
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void deleteAccount(String accountId) {
        connect();
        try {
            String sql = "UPDATE Accounts set IsDelete = 1 where AccountID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("deleteAccount: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public String getAllAccountByFilter(String filter, String search) {
        if (search.isEmpty()) {
            return "";
        }
        String query = " and ";
        switch (filter) {
            case "All":
                query += ""
                        + "        Username  like ('%" + search + "%') \n"
                        + "        or LastName + ' ' + FirstName like ('%" + search + "%') \n"
//                        + "        or LastName like ('%" + search + "%') \n"
                        + "        or PhoneNumber like ('%" + search + "%') \n";
                break;
            case "Name":
                query += "FirstName like ('%" + search + "%') \n"
                        + "        or LastName like ('%" + search + "%') \n"
                        + "        or LastName + ' ' + FirstName like ('%" + search + "%') \n";
                break;
            default:
                query += filter + " like ('%" + search + "%')\n";
                break;
        }
        return query;
    }

    public List<Account> searchAccountBy(String search, int index, int entry, String isAsc) {
        List<Account> list = new ArrayList<>();
        connect();
        try {
            String strSelect = "select *\n"
                    + "from Accounts\n"
                    + IS_DELETE_FALSE_WITH_WHERE + "\n"
                    + search
                    + "\norder by AccountId asc\n"
                    + "offset ? rows fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, (index - 1) * entry);
            ps.setInt(2, entry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchAccountBy: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Account> searchAccountByAll(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        AccountID  like ('%" + search + "%') \n"
                    + "        or Username  like ('%" + search + "%') \n"
                    + "        or FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%') \n"
                    + "        or PhoneNumber like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchAccountByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Account> searchAccountByName(String search) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + "        FirstName like ('%" + search + "%') \n"
                    + "        or LastName like ('%" + search + "%') \n"
                    + "        or LastName + ' ' + FirstName like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchAccountByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    public List<Account> searchAccount(String search, String filter) {
        connect();
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select Accounts.*\n"
                    + "from Accounts \n"
                    + "where isDelete = 0 and \n"
                    + "        (\n"
                    + filter + "          like ('%" + search + "%') \n"
                    + "        )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = getAccount(rs.getInt(1));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("searchAccountByAll: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return list;
    }

    // REPOSITORY FOR ROLE
    public Role getRole(String roleId) {
        connect();
        try {
            String sql = "select * from Roles where RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRoleId(rs.getInt(1));
                r.setRoleName(rs.getString(2));
                r.setDescription(rs.getString(3));
                return r;
            }
        } catch (Exception e) {
            System.out.println("getRole: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return null;
    }

    public List<Role> getAllRoles() {
        connect();
        List<Role> list = new ArrayList<>();

        try {
            String sql = "select * from Roles";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role r = getRole(rs.getString(1));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("getAllAccounts: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return list;
    }

    // END OF REPOSITORY FOR ROLE
    // ADMINROLES MANAGEMENT
    public List<String> getAccountRoles(Account a) {
        connect();
        List<String> roleList = new ArrayList<>();
        try {
            String sql = "select * from adminroles\n"
                    + "where accountId = ? and status = 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getAccountId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roleList.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("getAccountRoles: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }

        return roleList;
    }
    
//    public List<AdminRole> getAdminRole(String accountId) {
//        connect();
//        List<AdminRole> list = new ArrayList<>();
//        try {
//            String sql = "select * from AdminRoles "
//                    + "where AccountID = ?" + STATUS_TRUE_WITHOUT_WHERE;
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, accountId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                AdminRole ar = new AdminRole();
//                ar.setAccountId(getAccount(rs.getInt(1)));
//                ar.setRoleId(getRole(rs.getString(2)));
//                ar.setModifiedBy(getAccount(rs.getInt(3)));
//                ar.setModifiedHistory(rs.getString(4));
//                ar.setStatus(rs.getBoolean(5));
//                list.add(ar);
//            }
//        } catch (Exception e) {
//            System.out.println("getAdminRole: " + e.getMessage());
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return list;
//    }
    public void createAdminRole(int accountId, int roleId, int modifyAccount, boolean status) throws Exception {
        connect();
        try {
            String sql = "INSERT INTO AdminRoles (AccountID, RoleID, ModifiedBy, ModifiedHistory, Status)\n"
                    + "VALUES (?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, roleId);
            ps.setInt(3, modifyAccount);
            ps.setString(4, new java.sql.Date(Base.Util.Formatter.getCurrentDate().getTime()).toString());
            ps.setBoolean(5, status);
            ps.execute();
        } catch (Exception e) {
            System.out.println("createAdminRole: " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void updateAdminRole(int accountId, String roleId, boolean status) {
        connect();
        try {
            String sql = "update AdminRoles\n"
                    + "set status = ?\n"
                    + "where AccountID = ? and RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, accountId);
            ps.setString(3, roleId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("updateAdminRole: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    // END ADMINROLES MANAGEMENT
    
    public int countSearch(String search) {
        connect();
        try {
            String sql = "select Count(*)\n"
                    + "from Accounts\n"
                    + IS_DELETE_FALSE_WITH_WHERE + "\n"
                    + search;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countSearchInDao: " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return 0;
    }
}
