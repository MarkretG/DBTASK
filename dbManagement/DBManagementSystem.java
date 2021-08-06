package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class DBManagementSystem {
    public static void main(String[] args) throws SQLException {
        GeneralResource generalResource=GeneralResource.getInstance();
        DBUtil dbUtil=new DBUtil();
        System.out.println("welcome to db Management system");
        System.out.println("1.Add rows in customer table for new User\n2.Add rows in account table for existing user\n3.Account info for given customer_id\n4.Account info for given name\n5.exit");
        while (true)
        {
            int choice=generalResource.getIntFromUser();
            switch (choice)
            {
                case 1:
                    ArrayList<Customer> customers=dbUtil.getCustomersInfoFromUser();
                    dbUtil.insertRowsInCustomerTable(customers);
                    dbUtil.storeCustomerInfoInCustomerHashMap(customers);
                    break;
                case 2:
                    ArrayList<Account> accounts=dbUtil.getAccountsInfoFromUser();
                    if(dbUtil.verifyExistingCustomerForAddNewAccount(accounts)) {
                        dbUtil.insertRowsInAccountTable(accounts);
                        dbUtil.storeAccountInfoInAccountsInfoHashMap(accounts);
                    }
                    break;
                case 3:
                    System.out.println("enter customer id");
                    long customerId = generalResource.getLongFromUser();
                    HashMap<Long, Account> account=dbUtil.getAccountsInfoFromAccountsInfoHashMap(customerId);
                    System.out.println(account.toString());
                    break;
                case 4:
                    System.out.println("enter name");
                    String name = generalResource.getStringFromUser();
                    long givenId=dbUtil.getCustomerIdFromCustomerHashMap(name);
                    HashMap<Long,Account> a=dbUtil.getAccountsInfoFromAccountsInfoHashMap(givenId);
                    System.out.println(a.toString());
                    break;
                case 5:
                    generalResource.closeScanner();
                    dbUtil.closeConnection();
                    System.exit(0);
            }
        }
    }
    public static  void  initialise()
    {
        ArrayList<Customer> customers=new ArrayList<>();
        ArrayList<Account> accounts=new ArrayList<>();
        Customer[] customerObj=new Customer[3];
        customerObj[0].setCustomer_id(101);
        customerObj[0].setName("mark");
        customerObj[0].setMail("mark@gmail.com");
        customerObj[0].setAge(21);
        customerObj[0].setPhone(9987653131L);
        customerObj[1].setCustomer_id(102);
        customerObj[1].setName("vino");
        customerObj[1].setMail("vino@gmail.com");
        customerObj[1].setAge(22);
        customerObj[1].setPhone(9987651131L);
        customerObj[2].setCustomer_id(103);
        customerObj[2].setName("abi");
        customerObj[2].setMail("abik@gmail.com");
        customerObj[2].setAge(21);
        customerObj[2].setPhone(7787653131L);
        customers.add(customerObj[0]);
        customers.add(customerObj[1]);
        customers.add(customerObj[2]);
    }

}
