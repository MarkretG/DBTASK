package accountInfo;
import java.sql.*;
import java.util.HashMap;
import dbManagement.DBUtil;
public class AccountManagement{
    public static void getAccountInfo(long id)
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
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Account a = new Account();
                    a.setCustomer_id(rs.getInt(1));
                    a.setAccount_no(rs.getLong(2));
                    a.setBalance(rs.getInt(3));
                    accountInfo = DBUtil.info.get(rs.getLong(1));
                    if (accountInfo == null)
                    {
                        accountInfo= new HashMap<Long, Account>();
                    }
                    accountInfo.put(rs.getLong(2), a);
                    DBUtil.info.put(id, accountInfo);
                }
                if (accountInfo==null) {
                    System.out.println("Info does not exist in the table");
                }
                else {
                    System.out.println(DBUtil.info.get(id));
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
}
