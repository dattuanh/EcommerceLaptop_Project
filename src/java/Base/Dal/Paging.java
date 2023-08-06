/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Dal;

import Base.Model.Customer;
import static Base.Util.Utilities.IS_DELETE_FALSE_WITH_WHERE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giang Minh
 */
public class Paging extends DAO {

    public int getNumeberOf(String obj) {
        connect();
        try {
            String sql = "select count(*) from " + obj + IS_DELETE_FALSE_WITH_WHERE;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getNumeberOf: " + e.getMessage());
        }
        return 0;
    }

    // order by CustomerID
    public List<Customer> pagingCustomer(int index) {
        List<Customer> list = new ArrayList<>();

        try {
            String sql = "select * from Customers\n"
                        + "order by CustomerID\n"
                        + "offset ? rows fetch next 5 rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = getCustomer(rs.getInt(1));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("pagingCustomer: " + e.getMessage());
        }

        return list;
    }
}
