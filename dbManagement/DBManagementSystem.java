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
        dbUtil.initialiseCustomerTable();
        dbUtil.initialiseAccountTable();
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
                    ArrayList<Account> account1=dbUtil.getExistingCustomerVerifyForAddNewAccount(accounts);
                    dbUtil.insertRowsInAccountTable(account1);
                    dbUtil.storeAccountInfoInAccountsInfoHashMap(account1);
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


}
