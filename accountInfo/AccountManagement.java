package accountInfo;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import dbManagement.DBUtil;
public class AccountManagement{
    static Scanner sc=new Scanner(System.in);
    public  static void setAccountInfo()
    {
         System.out.println("How many number of rows");
         int rows=sc.nextInt();
           for(int i=0;i<rows;i++) {
            System.out.println("enter customer_id");
            int customer_id=sc.nextInt();
            System.out.println("enter account number");
            long account_no=sc.nextLong();
            System.out.println("enter balance");
            int balance=sc.nextInt();
            DBUtil.insertRowsAccount(customer_id,account_no,balance);
        }
        System.out.println("successfully inserted in account table");
    }
    public static void getAccountInfo(int id)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from account_info where customer_id=?";
        DBUtil.info.remove(id);
        HashMap accountInfo=DBUtil.info.get(id);
        if(accountInfo==null)
        {
            try {
                Connection con = DBUtil.getConnection();
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Account a = new Account();
                    a.setCustomer_id(rs.getInt(1));
                    a.setAccount_no(rs.getLong(2));
                    a.setBalance(rs.getInt(3));
                    accountInfo = DBUtil.info.get(rs.getInt(1));
                    if (accountInfo == null)
                    {
                        accountInfo= new HashMap<Long, Account>();
                    }
                    accountInfo.put(rs.getLong(2), a);
                    DBUtil.info.put(id, accountInfo);
                }
                if (accountInfo==null)
                    System.out.println("Info does not exit in the table");
                else
                    System.out.println(DBUtil.info.get(id));

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
}
