package dbTask;
import java.sql.*;
import java.util.ArrayList;
public class InsertTableRows{
    static ArrayList<Customer> customers=new ArrayList<>();
    static ArrayList<Account> accounts=new ArrayList<>();
    public static void initialiseCustomer()
    {
        customers.add(new Customer(101,"mark","mark@gmail.com",21,7854674212L));
        customers.add(new Customer(102,"vino","vino@gmail.com",23,9854674221L));
        customers.add(new Customer(103,"vijay","vijay@gmail.com",25,6854674223L));
        customers.add(new Customer(104,"aishu","aishu@gmail.com",21,8854674234L));
        customers.add(new Customer(105,"kavi","kavi@gmail.com",28,7854674245L));
        customers.add(new Customer(106,"kavin","kavin@gmail.com",27,7854674256L));
        customers.add(new Customer(107,"kalai","kalai@gmail.com",26,7854674267L));
        customers.add(new Customer(108,"kani","kani@gmail.com",21,9854674278L));
        customers.add(new Customer(109,"mani","mani@gmail.com",26,8854674289L));
        customers.add(new Customer(110,"vini","vini@gmail.com",27,7854674210L));
    }
    public static  void initialiseAccountsInformation()
    {
        accounts.add(new Account(101,44567891231L,9000000));
        accounts.add(new Account(102,34567891232L,8000000));
        accounts.add(new Account(109,44567891233L,7000000));
        accounts.add(new Account(102,34567891234L,6000000));
        accounts.add(new Account(110,34567891235L,5000000));
        accounts.add(new Account(102,34567891236L,4000000));
        accounts.add(new Account(103,64567891237L,3000000));
        accounts.add(new Account(104,34567891238L,2000000));
        accounts.add(new Account(105,74567891239L,1000000));
        accounts.add(new Account(101,34567891223L,8000000));
        accounts.add(new Account(104,44567891234L,9000000));
        accounts.add(new Account(101,53567891234L,1000000));
        accounts.add(new Account(106,65567891234L,6000000));
        accounts.add(new Account(107,23567891234L,5000000));
        accounts.add(new Account(108,79567891234L,4000000));

    }
    public static void insertRowsInMysql(){
        Connection con = null;
       PreparedStatement ps=null;
       PreparedStatement ps1=null;
       String query = "insert into customer_info (customer_id, name,mail,age,phone) values (?,?,?,?,?)";
       String query1 = "insert into account_info (customer_id,account_no,balance) values (?,?,?)";

        try {
            con = DbConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps1 = con.prepareStatement(query1);
            for(Customer customer:customers)
            {
                ps.setInt(1,customer.getCustomer_id());
                ps.setString(2,customer.getName());
                ps.setString(3,customer.getMail());
                ps.setInt(4,customer.getAge());
                ps.setLong(5, customer.getPhone());
                ps.addBatch();
            }


            for (Account account:accounts)
            {
                ps1.setInt(1,account.getCustomer_id());
                ps1.setLong(2,account.getAccount_no());
                ps1.setInt(3,account.getBalance());
                ps1.addBatch();
            }
            ps.executeBatch();
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


















