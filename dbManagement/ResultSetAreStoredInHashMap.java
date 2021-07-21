package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
public class ResultSetAreStoredInHashMap {
    static public HashMap<Long, Account> accountHashMap = new HashMap<>();
    static public HashMap<Integer, HashMap<Long, Account>> info = new HashMap<>();
    static public HashMap<Integer, Customer> customerHashmap = new HashMap<>();
    public static void customerTableResultSetAreStoredInHashMap() {
        Statement ps = null;
        ResultSet rs = null;
        String query = "select customer_id, name, mail, age, phone from  customer_info";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.createStatement();
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomer_id(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setMail(rs.getString(3));
                c.setAge(rs.getInt(4));
                c.setPhone(rs.getLong(5));
                customerHashmap.put(rs.getInt(1), c);
                info.put(rs.getInt(1), accountHashMap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if ((ps != null && rs != null))
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }
        public static void accountTableResultSetAreStoredInHashMap() {
            Statement ps = null;
            ResultSet rs = null;
            String query = "select customer_id, account_no, balance  from  account_info";
            try {
                Connection con = DBUtil.getConnection();
                ps = con.createStatement();
                rs = ps.executeQuery(query);
               while (rs.next()) {
                    //System.out.println(rs1.getInt(1)+":"+rs1.getLong(2)+":"+rs1.getInt(3));
                    Account a = new Account();
                    a.setCustomer_id(rs.getInt(1));
                    a.setAccount_no(rs.getLong(2));
                    a.setBalance(rs.getInt(3));
                    accountHashMap.put(rs.getLong(2), a);
                }

            }


            catch (SQLException e) {
                e.printStackTrace();
            } finally
            {

                if ((ps!=null&&rs!=null))
                    try {
                        ps.close();
                        rs.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

            }
    }
}

