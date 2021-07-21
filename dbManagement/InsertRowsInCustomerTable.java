package dbManagement;
import customerInfo.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertRowsInCustomerTable {
    public static void insertRowsInCustomerTable(int customer_id,String name,String mail,int age,long phone)
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
}
