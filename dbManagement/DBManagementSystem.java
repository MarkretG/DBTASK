package dbManagement;
import java.sql.SQLException;
public class DBManagementSystem {
    public static void main(String[] args) throws SQLException {
        GeneralResource generalResource=GeneralResource.generalResource();
        DBUtil dbUtil=new DBUtil();
        System.out.println("welcome to db Management system");
        System.out.println("get customer info for insert table in db then set rows in table");
        System.out.println("How many number of rows");
        int customerRows=generalResource.getInt();
        dbUtil.setCustomerInfoInDb(customerRows);
        System.out.println("get account info for insert table in db then set rows in table");
        System.out.println("How many number of rows");
        int accountRows=generalResource.getInt();
        dbUtil.setAccountInfoInDb(accountRows);
        System.out.println("Store customer table info in customer hashmap");
        dbUtil.storeCustomerInfoHashmap();
        System.out.println("Store account table info in in info hashmap");
        dbUtil.storeAccountInfoHashMap();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=generalResource.getInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    long id = generalResource.getLong();
                    DBUtil.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    String name = generalResource.getString();
                    long givenId=dbUtil.getId(name);
                    DBUtil.getAccountInfo(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = generalResource.getInt();
                        switch (ch) {
                            case 1:
                                System.out.println("How many number of rows");
                                int rows=generalResource.getInt();
                                dbUtil.setCustomerInfoInDb(rows);
                                dbUtil.storeCustomerInfoHashmap();
                                break;
                            case 2:
                                System.out.println("How many number of rows");
                                int rowsAccount=generalResource.getInt();
                                dbUtil.setAccountInfoInDb(rowsAccount);
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    generalResource.closeScanner();
                    System.exit(0);
            }
        }
    }

}

