package dbTask;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreparedStatementBatch {
    static ArrayList<Customer> customers=new ArrayList<>();
    static ArrayList<Account> accounts=new ArrayList<>();
    public static void initialCustomer()
    {
        customers.add(new Customer(101,"mark","mark@gmail.com",21,"7854674212"));
        customers.add(new Customer(102,"vino","vino@gmail.com",23,"9854674221"));
        customers.add(new Customer(103,"vijay","vijay@gmail.com",25,"6854674223"));
        customers.add(new Customer(104,"aishu","aishu@gmail.com",21,"8854674234"));
        customers.add(new Customer(105,"kavi","kavi@gmail.com",28,"7854674245"));
        customers.add(new Customer(106,"kavin","kavin@gmail.com",27,"7854674256"));
        customers.add(new Customer(107,"kalai","kalai@gmail.com",26,"7854674267"));
        customers.add(new Customer(108,"kani","kani@gmail.com",21,"9854674278"));
        customers.add(new Customer(109,"mani","mani@gmail.com",26,"8854674289"));
        customers.add(new Customer(110,"vini","vini@gmail.com",27,"7854674210"));
    }
    public static  void initialAccountsInformation()
    {
        accounts.add(new Account(101,"34567891234",9000000));
        accounts.add(new Account(102,"34567891234",8000000));
        accounts.add(new Account(109,"34567891234",7000000));
        accounts.add(new Account(102,"34567891234",6000000));
        accounts.add(new Account(110,"34567891234",5000000));
        accounts.add(new Account(102,"34567891234",4000000));
        accounts.add(new Account(103,"34567891234",3000000));
        accounts.add(new Account(104,"34567891234",2000000));
        accounts.add(new Account(105,"34567891234",1000000));
        accounts.add(new Account(101,"34567891234",8000000));
        accounts.add(new Account(104,"34567891234",9000000));
        accounts.add(new Account(101,"34567891234",1000000));
        accounts.add(new Account(106,"34567891234",6000000));
        accounts.add(new Account(107,"34567891234",5000000));
        accounts.add(new Account(108,"34567891234",4000000));
        
    }
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
            int count=0;
            for(Customer customer:customers)
            {
                ps.setInt(1,customer.getCustomer_id());
                ps.setString(2,customer.getName());
                ps.setString(3,customer.getMail());
                ps.setInt(4,customer.getAge());
                ps.setString(5, customer.getPhone());
                ps.addBatch();
            }
            ps.executeBatch();
            for (Account account:accounts)
            {
                ps1.setInt(1,account.getCustomer_id());
                ps1.setString(2,account.getAccountNo());
                ps1.setInt(3,account.getBalance());
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
