package dbManagement;
import accountInfo.Account;
import java.sql.SQLException;
import java.util.HashMap;
public class DBManagementSystem {
    public static void main(String[] args) throws SQLException {
        GeneralResource generalResource=GeneralResource.getInstance();
        DBUtil dbUtil=new DBUtil();
        System.out.println("welcome to db Management system");
        System.out.println("1.Add rows in customer table\n2.Add rows in account table\n3.Account info for given customer_id\n4.Account info for given name\n5.exit");
        while (true)
        {
            int choice=generalResource.getIntFromUser();
            switch (choice)
            {
                case 1:
                    dbUtil.storeCustomerInfoInCustomerHashMap(dbUtil.addCustomersInfoInDb());
                    //dbUtil.addCustomersInfoInDb();

                    break;
                case 2:
                    dbUtil.storeAccountInfoInAccountsInfoHashMap(dbUtil.addAccountsInfoInDb());
                    //dbUtil.addAccountsInfoInDb();
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
                    HashMap<Long,Account> accounts=dbUtil.getAccountsInfoFromAccountsInfoHashMap(givenId);
                    System.out.println(accounts.toString());
                    break;
                case 5:
                    generalResource.closeScanner();
                    dbUtil.closeConnection();
                    System.exit(0);
            }
        }
    }

}
