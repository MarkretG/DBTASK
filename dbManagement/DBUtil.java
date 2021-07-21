package dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
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
}
