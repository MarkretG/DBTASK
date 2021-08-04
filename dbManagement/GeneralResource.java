package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.util.Scanner;
public class GeneralResource {
    Scanner scanner = new Scanner(System.in);
    public Customer getCustomerInfo(){
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
        return customer;
    }
    public Account getAccountInfo(){
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
        return account;
    }
}
