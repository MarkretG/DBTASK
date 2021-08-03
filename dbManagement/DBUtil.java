package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class DBUtil {
    static Connection con=null;
      HashMap<Long, HashMap<Long,Account>> info = new HashMap<>();
      HashMap<Long, Customer> customerHashmap = new HashMap<>();
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
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

        return con;
    }

    public static void insertRowsAccount(Account account)
    {
        PreparedStatement ps=null;
        String query = "insert into account_info(customer_id,account_no,balance) values(?,?,?)";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setLong(1,account.getCustomer_id());
            ps.setLong(2,account.getAccount_no());
            ps.setFloat(3,account.getBalance());
            ps.addBatch();
            ps.executeBatch();


        }

        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void insertRowsCustomer(Customer customer)
    {
        PreparedStatement ps=null;
        String query = "insert into customer_info(customer_id,name,mail,age,phone) values(?,?,?,?,?)";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setLong(1,customer.getCustomer_id());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getMail());
            ps.setInt(4,customer.getAge());
            ps.setLong(5,customer.getPhone());
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   public  void customerInfo() {
        for (Customer customer:customers)
        {
            customerHashmap.put(customer.getCustomer_id(),customer);
        }
        customers.clear();

   }


    public void accountInfo(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from  account_info";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next())
            {
                       Account a=new Account();
                       a.setCustomer_id(rs.getLong(1));
                       a.setAccount_no(rs.getLong(2));
                       a.setBalance(rs.getFloat(3));
                       HashMap accountHashMap = info.get(rs.getLong(1));
                       if (accountHashMap == null) {
                           accountHashMap = new HashMap<Long, Account>();
                       }
                       accountHashMap.put(rs.getLong(2), a);
                       info.put(rs.getLong(1), accountHashMap);
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

    public  long getId(String name) {
        for (Customer customer : customerHashmap.values()) {
            if (customer.getName().equals(name)) {
                return customer.getCustomer_id();
            }
        }
        return 0;
    }
    public  void accountDetails(long id)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from account_info where customer_id=?";
        info.remove(id);
        HashMap accountInfo=info.get(id);
        if(accountInfo==null)
        {
            try {
                Connection con = DBUtil.getConnection();
                ps = con.prepareStatement(query);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Account a = new Account();
                    a.setCustomer_id(rs.getInt(1));
                    a.setAccount_no(rs.getLong(2));
                    a.setBalance(rs.getInt(3));
                    accountInfo = info.get(rs.getLong(1));
                    if (accountInfo == null)
                    {
                        accountInfo= new HashMap<Long, Account>();
                    }
                    accountInfo.put(rs.getLong(2), a);
                    info.put(id, accountInfo);
                }
                if (accountInfo==null) {
                    System.out.println("Info does not exist in the table");
                }
                else {
                    System.out.println(info.get(id));
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                if ((rs != null) || (ps != null))
                {
                    try {
                        rs.close();
                        ps.close();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

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
