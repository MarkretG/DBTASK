package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.util.ArrayList;
import java.util.Scanner;
public class GeneralResource {   //singleton class
    private static GeneralResource generalResource=null;
    public static GeneralResource generalResource()
    {
        // To ensure only one instance is created
        if (generalResource == null)
        {
            generalResource= new GeneralResource();
        }
        return generalResource;
    }
       Scanner scanner = new Scanner(System.in);
       String name;
       int row;
       long id;
        public String getString ()
        {
             name = scanner.nextLine();
            return name;
        }
        public int getInt ()
        {
            row = scanner.nextInt();
            return row;
        }
        public long getLong ()
        {
            id = scanner.nextLong();
            return id;
        }
        public ArrayList<Customer> getCustomerInfo ()
        {
            ArrayList<Customer> customers=new ArrayList<>();
            System.out.println("How many number of rows");
            int customerRows = generalResource.getInt();
            for (int i = 0; i < customerRows; i++) {
                System.out.println("Enter customer_id");
                long customer_id = scanner.nextLong();
                System.out.println("enter name");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("enter mail id");
                String mail = scanner.nextLine();
                System.out.println("enter age");
                int age = scanner.nextInt();
                System.out.println("enter phone Number");
                long phone = scanner.nextLong();
                Customer customer = new Customer();
                customer.setCustomer_id(customer_id);
                customer.setName(name);
                customer.setAge(age);
                customer.setMail(mail);
                customer.setPhone(phone);
                customers.add(customer);
            }
            return customers;
        }
        public ArrayList<Account> getAccountInfo () {
            ArrayList<Account> accounts = new ArrayList<>();
            System.out.println("How many number of rows");
            int accountRows = generalResource.getInt();
            for (int i = 0; i < accountRows; i++) {
                System.out.println("enter customer_id");
                long customer_id = scanner.nextLong();
                System.out.println("enter account number");
                long account_no = scanner.nextLong();
                System.out.println("enter balance");
                float balance = scanner.nextInt();
                Account account = new Account();
                account.setCustomer_id(customer_id);
                account.setAccount_no(account_no);
                account.setBalance(balance);
                accounts.add(account);
            }
            return accounts;
        }
    public  void closeScanner()
    {
        scanner.close();
    }

}
