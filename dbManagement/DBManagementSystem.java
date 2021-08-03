package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class DBManagementSystem {
    private static HashMap<Long, Customer> customerHashmap = new HashMap<>();
    private static ArrayList<Customer> customers=new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("welcome to db Management system");
        System.out.println("Initially insert all rows in customer table and Account table");
        DBManagementSystem.setCustomerInfo(scanner);
        DBManagementSystem.setAccountInfo(scanner);
        DBManagementSystem.customerInfo();
        DBUtil.accountInfo();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=scanner.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    long id = scanner.nextInt();
                    DBUtil.accountDetails(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    long givenId=DBUtil.getId(name);
                    DBUtil.accountDetails(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = scanner.nextInt();
                        switch (ch) {
                            case 1:
                                DBManagementSystem.setCustomerInfo(scanner);
                                DBManagementSystem.customerInfo();
                                break;
                            case 2:
                                DBManagementSystem.setAccountInfo(scanner);
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    DBUtil.closeConnection();
                    scanner.close();
                    System.exit(0);
            }
        }
    }
    public static void  setCustomerInfo(Scanner sc) {
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
    public  static void setAccountInfo(Scanner sc)
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
    public static void customerInfo() {
        for (Customer customer:DBManagementSystem.customers)
        {
            customerHashmap.put(customer.getCustomer_id(),customer);
        }
        DBManagementSystem.customers.clear();

    }

}

