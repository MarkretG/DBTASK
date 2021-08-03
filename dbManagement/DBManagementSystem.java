package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.util.ArrayList;
import java.util.Scanner;
public class DBManagementSystem {
    static Scanner sc=new Scanner(System.in);
    static ArrayList<Customer> customers=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("welcome to db Management system");
        System.out.println("Initially insert all rows in customer table and Account table");
        DBManagementSystem.setCustomerInfo();
        DBManagementSystem.setAccountInfo();
        DBUtil.customerInfo();
        DBUtil.accountInfo();
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
                    long givenId=DBUtil.getId(name);
                    AccountManagement.getAccountInfo(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                DBManagementSystem.setCustomerInfo();
                                DBUtil.customerInfo();
                                break;
                            case 2:
                                DBManagementSystem.setAccountInfo();
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    DBUtil.closeConnection();
                    sc.close();
                    System.exit(0);
            }
        }
    }
    public static void setCustomerInfo() {
        System.out.println("How many number of rows");
        int rows=sc.nextInt();
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter customer_id");
            long customer_id = sc.nextLong();
            System.out.println("enter name");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("enter mail id");
            String mail = sc.nextLine();
            System.out.println("enter age");
            int age = sc.nextInt();
            System.out.println("enter phone Number");
            long phone = sc.nextLong();
            Customer customer=new Customer();
            customer.setCustomer_id(customer_id);
            customer.setName(name);
            customer.setAge(age);
            customer.setMail(mail);
            customer.setPhone(phone);
            customers.add(customer);
            DBUtil.insertRowsCustomer(customer);
        }
        System.out.println("successfully inserted in customer table");
    }
    public  static void setAccountInfo()
    {
        System.out.println("How many number of rows");
        int rows=sc.nextInt();
        for(int i=0;i<rows;i++) {
            System.out.println("enter customer_id");
            long customer_id=sc.nextLong();
            System.out.println("enter account number");
            long account_no=sc.nextLong();
            System.out.println("enter balance");
            float balance=sc.nextInt();
            Account account=new Account();
            account.setCustomer_id(customer_id);
            account.setAccount_no(account_no);
            account.setBalance(balance);
            DBUtil.insertRowsAccount(account);
        }
        System.out.println("successfully inserted in account table");
    }
}

