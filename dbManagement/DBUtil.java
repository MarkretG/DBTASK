package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.*;
import java.util.HashMap;
public class DBUtil {
    static public HashMap<Long, Account> accountHashMap = new HashMap<>();
    static public HashMap<Integer, HashMap<Long, Account>> info = new HashMap<>();
    static public HashMap<Integer, Customer> customerHashmap = new HashMap<>();
    public static Connection getConnection() {
        String url="jdbc:mysql://localhost/info";
        String uname="root";
        String pass="Password@1";
        Connection con=null;
        try {
            // load the Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create the connection now
           con = DriverManager.getConnection(url,uname,pass);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return con;
    }

    public static void setRowsAccount(int customer_id,long account_no,int balance)
    {
        Account account=new Account();
        account.setCustomer_id(customer_id);
        account.setAccount_no(account_no);
        account.setBalance(balance);
        PreparedStatement ps=null;
        String query = "insert into account_info(customer_id,account_no,balance) values(?,?,?)";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,account.getCustomer_id());
            ps.setLong(2,account.getAccount_no());
            ps.setInt(3,account.getBalance());
            ps.addBatch();
            ps.executeBatch();

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
    public static void setRowsCustomer(int customer_id,String name,String mail,int age,long phone)
    {
        Customer customer=new Customer();
        customer.setCustomer_id(customer_id);
        customer.setName(name);
        customer.setMail(mail);
        customer.setAge(age);
        customer.setPhone(phone);
        PreparedStatement ps=null;
        String query = "insert into customer_info(customer_id,name,mail,age,phone) values(?,?,?,?,?)";
        try {
            Connection con = DBUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,customer.getCustomer_id());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getMail());
            ps.setInt(4,customer.getAge());
            ps.setLong(5,customer.getPhone());
            ps.addBatch();
            ps.executeBatch();
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
    public static void getCustomer() {
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
    public static void getAccount() {
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
