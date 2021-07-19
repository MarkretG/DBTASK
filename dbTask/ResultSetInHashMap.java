package dbTask;
import java.sql.*;
import java.util.HashMap;
public class ResultSetInHashMap {
    static HashMap<Long,Account> accountHashMap=new HashMap<>();
    static HashMap<Integer,Customer> customerHashmap=new HashMap<>();
    public static void resultInHashMap() {
        Connection con = null;
        String  query="select *from customer_info";
        String query1="select *from account_info";

        try {
            con = DbConnectionUtil.getConnection();
             Statement ps = con.createStatement();
             Statement ps1 = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            ResultSet rs1 = ps1.executeQuery(query1);

            while (rs1.next()) {
                Account a = new Account(rs1.getInt(1), rs1.getLong(2), rs1.getInt(3));
                accountHashMap.put(rs1.getLong(2), a);
            }
            while (rs.next()) {
                Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getLong(5));
                customerHashmap.put(rs.getInt(1), c);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

