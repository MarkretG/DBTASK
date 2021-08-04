    package dbManagement;
import accountInfo.Account;

import java.sql.SQLException;
import java.util.HashMap;

public class DBManagementSystem {
    public static void main(String[] args) throws SQLException {
        GeneralResource generalResource=GeneralResource.getInstance();
        DBUtil dbUtil=new DBUtil();
        System.out.println("welcome to db Management system");
        System.out.println("get customer info for insert table in db then set rows in table");
        dbUtil.setCustomerInfoInDb();
        System.out.println("get account info for insert table in db then set rows in table");
        dbUtil.setAccountInfoInDb();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=generalResource.getInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    long id = generalResource.getLong();
                    HashMap<Long,Account> account=dbUtil.getAccountInfo(id);
                    System.out.println(account.toString());
                    break;
                case 2:
                    System.out.println("enter name");
                    String name = generalResource.getString();
                    long givenId=dbUtil.getId(name);
                    HashMap<Long,Account> accounts=dbUtil.getAccountInfo(givenId);
                    System.out.println(accounts.toString());
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = generalResource.getInt();
                        switch (ch) {
                            case 1:
                                dbUtil.setCustomerInfoInDb();
                                break;
                            case 2:
                                dbUtil.setAccountInfoInDb();
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    generalResource.closeScanner();
                    dbUtil.closeConnection();
                    System.exit(0);
            }
        }
    }

}
