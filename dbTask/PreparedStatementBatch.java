package dbTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class PreparedStatementBatch {
    public static void preparedStatement() {
        Connection con = null;
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        String query = "insert into customer_info (customer_id, Name,mail,Age,phone) values (?,?,?,?,?)";
        String query1 = "insert into account-info (customer_id,AccountNo,Balance) values (?,?,?)";

        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement(query);
            ps1 = con.prepareStatement(query1);
            int[] id={101,102,103,104,105,106,107,108,109,110};
            String[] names={"mark","vino","kavini","kalai","kani","mani","hari","dhatchu","deepak","vjay"};
            String[] mails={"mark@gmail.com","vino@gmail.com","kavin@gmail.com","kalai@gmail.com","kani@gmail.com","mani@gmail.com","hari@gmail.com","dhatchu@gmail.com","deepak@gmail.com","vijay@gmail.com"};
            int[]   ages={21,22,24,21,23,25,24,21,23,25};
            String[]  phone={"7854674213","7854674713","9854674312","9854674313","7855374313","7865674313","78984674313","9854674313","8854674313","7854674214"};

            int[] ids={101,102,102,103,105,106,107,108,109,110,101,102,103,104,105};
            int[]   balance={8000000,1000000,2000000,5000000,4000000,6000000,1000000,7000000,2000000,3000000,6000000,1000000,7000000,2000000,3000000};
            String[]  account={"78546742131","78546747132","79546743133","98546743134","78553743139","78656743131","78984674313","98546743139","88546743135","48546742146","48546742131","48546747139","59546743136","48546743132","38553743135"};


            for (int i = 0; i < id.length; i++) {
                ps.setInt(1,id[i]);
                ps.setString(2, names[i]);
                ps.setString(3,mails[i]);
                ps.setInt(4,ages[i]);
                ps.setString(5,phone[i]);
                ps.addBatch();
            }
            ps.executeBatch();

            for (int i = 0; i < ids.length; i++) {
                ps1.setInt(1,ids[i]);
                ps1.setString(2, account[i]);
                ps1.setInt(3,balance[i]);
                ps1.addBatch();
            }
            ps1.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                ps1.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
