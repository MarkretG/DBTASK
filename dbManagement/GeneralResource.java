package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.util.ArrayList;
import java.util.Scanner;
public class GeneralResource {   //singleton class
    private static GeneralResource singleInstance=null;
    public static synchronized GeneralResource getInstance()
    {
        // To ensure only one instance is created
        if (singleInstance == null)
        {
            singleInstance= new GeneralResource();
        }
        return singleInstance;
    }
    Scanner scanner = new Scanner(System.in);
    String name;
    int row;
    long id;
    public String getStringFromUser()
    {
        name = scanner.nextLine();
        return name;
    }
    public int getIntFromUser()
    {
        row = scanner.nextInt();
        return row;
    }
    public long getLongFromUser()
    {
        id = scanner.nextLong();
        return id;
    }
    //get input from user and add list of customer in arraylist then return arraylist
    public ArrayList<Customer> getCustomersInfo ()
    {
        ArrayList<Customer> customers=new ArrayList<>();
        System.out.println("How many number of rows");
        int customerRows =scanner.nextInt();
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
    //get input from user and add list of accounts in arraylist then return arraylist
    public ArrayList<Account> getAccountsInfo () {
        ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("How many number of rows");
        int accountRows = scanner.nextInt();
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
    public ArrayList<Customer>  initialiseCustomerInfo()
    {
        ArrayList<Customer> customers=new ArrayList<>();
        Customer[] customerObj=new Customer[3];
        customerObj[0].setCustomer_id(101);
        customerObj[0].setName("mark");
        customerObj[0].setMail("mark@gmail.com");
        customerObj[0].setAge(21);
        customerObj[0].setPhone(9987653131L);
        customerObj[1].setCustomer_id(102);
        customerObj[1].setName("vino");
        customerObj[1].setMail("vino@gmail.com");
        customerObj[1].setAge(22);
        customerObj[1].setPhone(9987651131L);
        customerObj[2].setCustomer_id(103);
        customerObj[2].setName("abi");
        customerObj[2].setMail("abi@gmail.com");
        customerObj[2].setAge(21);
        customerObj[2].setPhone(7787653131L);
        for (Customer customer:customerObj)
        {
           customers.add(customer);
         }
        return customers;
    }
    public ArrayList<Account>  initialiseAccountInfo()
    {
        ArrayList<Account> accounts=new ArrayList<>();
        Account[] accountObj=new Account[3];
        accountObj[0].setCustomer_id(101L);
        accountObj[0].setAccount_no(23415678912L);
        accountObj[0].setBalance(150000);
        accountObj[1].setCustomer_id(101);
        accountObj[1].setAccount_no(44415678912L);
        accountObj[1].setBalance(170000);
        accountObj[2].setCustomer_id(102);
        accountObj[2].setAccount_no(23415678111L);
        accountObj[2].setBalance(200000);
        for(Account account:accountObj)
        {
            accounts.add(account);
        }
        return accounts;
    }
    public  void closeScanner()
    {
        scanner.close();
    }


}



