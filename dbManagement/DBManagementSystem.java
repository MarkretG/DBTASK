package dbManagement;
import accountInfo.Account;
import java.sql.SQLException;
import java.util.HashMap;
public class DBManagementSystem {
    public static void main(String[] args) throws SQLException {
        GeneralResource generalResource=GeneralResource.getInstance();
        DBUtil dbUtil=new DBUtil();
        System.out.println("welcome to db Management system");
        System.out.println("1.Initially add rows in customer table and account table\n2.account info for given customer_id\n3.account info for given name\n4.Do you want to insert additional rows\n5.exit");
        while (true)
        {
            int choice=generalResource.getIntFromUser();
            switch (choice)
            {
                case 1:
                    dbUtil.addCustomersInfoInDb();
                    dbUtil.addAccountsInfoInDb();
                    break;
                case 2:
                    System.out.println("enter customer id");
                    long id = generalResource.getLongFromUser();
                    HashMap<Long, Account> account=dbUtil.getAccountInfo(id);
                    System.out.println(account.toString());
                    break;
                case 3:
                    System.out.println("enter name");
                    String name = generalResource.getStringFromUser();
                    long givenId=dbUtil.getId(name);
                    HashMap<Long,Account> accounts=dbUtil.getAccountInfo(givenId);
                    System.out.println(accounts.toString());
                    break;
                case 4:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = generalResource.getIntFromUser();
                        switch (ch) {
                            case 1:
                                dbUtil.addCustomersInfoInDb();
                                break;
                            case 2:
                                dbUtil.addAccountsInfoInDb();
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 5:
                    generalResource.closeScanner();
                    dbUtil.closeConnection();
                    System.exit(0);
            }
        }
    }

}
