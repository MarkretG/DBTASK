package dbManagement;
import accountInfo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRowsInAccountTable {
    public static void insertRowsInAccountTable(int customer_id,long account_no,int balance)
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
}
