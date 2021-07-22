package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.util.Scanner;
public class DBManagementSystem {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("welcome to db Management system");
        System.out.println("Initially insert all rows in customer table and Account table");
        CustomerManagement.setCustomerInfo();
        AccountManagement.setAccountInfo();
        DBUtil.getCustomer();
        DBUtil.getAccount();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    int id = sc.nextInt();
                    AccountManagement.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    int givenId=CustomerManagement.getId(name);
                    AccountManagement.getAccountInfo(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                CustomerManagement.setCustomerInfo();
                                DBUtil.getCustomer();
                                break;
                            case 2:
                                AccountManagement.setAccountInfo();
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    DBUtil.closeConnection();
                    System.exit(0);
            }
        }
    }
}

