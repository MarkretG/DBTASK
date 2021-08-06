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
        System.out.println("1.New user\n2.Add rows in account table for existing user\n3.Account info for given customer_id\n5.exit");
        while (true)
        {
            int choice=generalResource.getIntFromUser();
            switch (choice)
            {
                case 1:
                    ArrayList<Customer> customers=dbUtil.getCustomersInfoFromUser();
                    dbUtil.insertRowsInCustomerTable(customers);
                    dbUtil.storeCustomerInfoInCustomerHashMap(customers);
                    ArrayList<Account> account=dbUtil.getAccountsInfoFromUser();
                    dbUtil.insertRowsInAccountTable(account);
                    dbUtil.storeAccountInfoInAccountsInfoHashMap(account);
                    break;
                case 2:
                    ArrayList<Account> accounts=dbUtil.getAccountsInfoFromUser();
                   // ArrayList<Account> account1=dbUtil.getExistingCustomerVerifyForAddNewAccount(accounts);
                    dbUtil.insertRowsInAccountTable(accounts);
                    dbUtil.storeAccountInfoInAccountsInfoHashMap(accounts);
                    break;
                case 3:
                    System.out.println("enter customer id");
                    long customerId = generalResource.getLongFromUser();
                    HashMap<Long, Account> accountHashMap=dbUtil.getAccountsInfoFromAccountsInfoHashMap(customerId);
                    System.out.println(accountHashMap.toString());
                    break;
                case 4:
                    generalResource.closeScanner();
                    dbUtil.closeConnection();
                    System.exit(0);
            }
        }
    }


}
