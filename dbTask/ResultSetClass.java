package dbTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResultSetClass {
    static HashMap<Integer, HashMap<String,Account>> info=new HashMap<>();
    static HashMap<String,Account> accountHashMap=new HashMap<>();
    static Scanner sc=new Scanner(System.in);
    public static void getResult() {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        String query = "select *from account_info where customer_id=?";
        // String query1 = "insert into account_info (customer_id,account_no,Balance) values (?,?,?)";

        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement(query);
            //  ps1 = con.prepareStatement(query1);
            System.out.println("enter the customer id");
            int id=sc.nextInt();
            int flag=0;
            for(Map.Entry<Integer,HashMap<String,Account>> account:info.entrySet())
            {
                if(id == account.getKey())
                {
                    ps.setInt(1,id);
                    flag=1;
                    break;
                }
            }
            if(flag==0)
                System.out.println("enter id is doesn't exit in the table");
            ResultSet res=ps.executeQuery(query);
            String data="";
            while (res.next())
            {
                 data=res.getInt(1)+":"+res.getString(2)+":"+res.getInt(3);
                System.out.println(data);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
               // ps1.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
