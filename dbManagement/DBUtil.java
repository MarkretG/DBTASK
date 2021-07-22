package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.*;
import java.util.HashMap;
public class DBUtil {
    static Connection con=null;
    static public HashMap<Integer, HashMap<Long,Account>> info = new HashMap<>();
    static public HashMap<Integer, Customer> customerHashmap = new HashMap<>();
    public static Connection getConnection() {
                if(con==null)
                {
                    try{
                    String url = "jdbc:mysql://localhost/info";
                    String uname = "root";
                    String pass = "Password@1";
                    // load the Driver Class
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // create the connection now
                    con = DriverManager.getConnection(url, uname, pass);
                   }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

        return con;
    }

    public static void insertRowsAccount(int customer_id,long account_no,int balance)
    {
        Account account=new Account();
        account.setCustomer_id(customer_id);
        account.setAccount_no(account_no);
        account.setBalance(balance);
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        ResultSet rs=null;
        String query = "insert into account_info(customer_id,account_no,balance) values(?,?,?)";
        String query1="select * from where customer_id=?";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps1=con.prepareStatement(query1);
            ps.setInt(1,account.getCustomer_id());
            ps.setLong(2,account.getAccount_no());
            ps.setInt(3,account.getBalance());
            ps.addBatch();
            ps.executeBatch();
            ps1.setInt(1,account.getCustomer_id());
            rs=ps1.executeQuery();

            if(info.containsKey(customer_id))
            {
                info.remove(customer_id);
            }

            while (rs.next()) {
                Account a = new Account();
                a.setCustomer_id(rs.getInt(1));
                a.setAccount_no(rs.getLong(2));
                a.setBalance(rs.getInt(3));
                HashMap accountHashMap = info.get(rs.getInt(1));
                if (accountHashMap == null) {
                    accountHashMap = new HashMap<Long, Account>();
                }
                accountHashMap.put(rs.getLong(2), a);
                info.put(rs.getInt(1), accountHashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps!=null)
                try {
                    ps.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void insertRowsCustomer(int customer_id,String name,String mail,int age,long phone)
    {
        Customer customer=new Customer();
        customer.setCustomer_id(customer_id);
        customer.setName(name);
        customer.setMail(mail);
        customer.setAge(age);
        customer.setPhone(phone);
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        ResultSet rs=null;
        String query = "insert into customer_info(customer_id,name,mail,age,phone) values(?,?,?,?,?)";
        String query1="select * from customer_info where customer_id=?";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps1=con.prepareStatement(query1);
            ps.setInt(1,customer.getCustomer_id());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getMail());
            ps.setInt(4,customer.getAge());
            ps.setLong(5,customer.getPhone());
            ps.addBatch();
            ps.executeBatch();
            ps1.setInt(1,customer_id);
            rs=ps1.executeQuery();
            while (rs.next())
            {
                customerHashmap.put(rs.getInt(1),customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ((rs!=null)||(ps!=null)) {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  /*  public static void getCustomer(int customer_id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from  customer_info where customer_id=?";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,customer_id);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomer_id(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setMail(rs.getString(3));
                c.setAge(rs.getInt(4));
                c.setPhone(rs.getLong(5));
                customerHashmap.put(rs.getInt(1), c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if ( (rs != null)||(ps!=null)) {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   */
    /*public static void getAccount() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from  account_info where customer_id=?";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,customer_id);
            rs = ps.executeQuery(query);
            if(DBUtil.info.containsKey(customer_id))
            {
                DBUtil.info.remove(customer_id);
            }

                while (rs.next()) {
                    Account a = new Account();
                    a.setCustomer_id(rs.getInt(1));
                    a.setAccount_no(rs.getLong(2));
                    a.setBalance(rs.getInt(3));
                    HashMap accountHashMap = info.get(rs.getInt(1));
                    if (accountHashMap == null) {
                        accountHashMap = new HashMap<Long, Account>();
                    }
                    accountHashMap.put(rs.getLong(2), a);
                    info.put(rs.getInt(1), accountHashMap);
                }


        }


        catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            if ((rs!=null)||(ps!=null)) {
                try {
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

     */
    public static void closeConnection()
    {
        if(con!=null)
        {
            try {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
