package dbManagement;
import accountInfo.Account;
import customerInfo.*;
import java.sql.SQLException;
public class DBManagementSystem {
    private static GeneralResource generalResource=new GeneralResource();
    public static void main(String[] args) throws SQLException {
        System.out.println("welcome to db Management system");
        System.out.println("get customer info for insert table in db then set rows in table");
        System.out.println("How many number of rows");
        int customerRows=generalResource.scanner.nextInt();
        setCustomerInfoInDb(customerRows);
        System.out.println("get account info for insert table in db then set rows in table");
        System.out.println("How many number of rows");
        int accountRows=generalResource.scanner.nextInt();
        setAccountInfoInDb(accountRows);
        System.out.println("Store customer table info in customer hashmap");
        DBUtil.storeCustomerInfoHashmap();
        System.out.println("Store account table info in in info hashmap");
        DBUtil.storeAccountInfoHashMap();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=generalResource.scanner.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    long id = generalResource.scanner.nextLong();
                    DBUtil.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    String name = generalResource.scanner.nextLine();
                    long givenId=DBUtil.getId(name);
                    DBUtil.getAccountInfo(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = generalResource.scanner.nextInt();
                        switch (ch) {
                            case 1:
                                System.out.println("How many number of rows");
                                int rows=generalResource.scanner.nextInt();
                                setCustomerInfoInDb(rows);
                                DBUtil.storeCustomerInfoHashmap();
                                break;
                            case 2:
                                System.out.println("How many number of rows");
                                int rowsAccount=generalResource.scanner.nextInt();
                                setAccountInfoInDb(rowsAccount);
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    generalResource.scanner.close();
                    System.exit(0);
            }
        }
    }
    public  static void setCustomerInfoInDb(int customerRows) throws SQLException
    {
        for(int i=0;i<customerRows;i++)
        {
            Customer customer=generalResource.getCustomerInfo();
            DBUtil.insertRowsCustomer(customer);
        }
        System.out.println("successfully insert in customer table");
    }
    public  static void setAccountInfoInDb(int accountRows) throws SQLException
    {
        for(int i=0;i<accountRows;i++)
        {
            Account account=generalResource.getAccountInfo();
            DBUtil.insertRowsAccount(account);
        }
        System.out.println("successfully insert in account table");
    }
}

