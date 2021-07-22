package customerInfo;
import dbManagement.DBUtil;
import java.util.Scanner;
public class CustomerManagement {
    static Scanner sc = new Scanner(System.in);
    public static void setCustomerInfo() {
        System.out.println("How many number of rows");
        int rows=sc.nextInt();
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter customer_id");
            int customer_id = sc.nextInt();
            System.out.println("enter name");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("enter mail id");
            String mail = sc.nextLine();
            System.out.println("enter age");
            int age = sc.nextInt();
            System.out.println("enter phone Number");
            long phone = sc.nextLong();
            DBUtil.insertRowsCustomer(customer_id, name, mail, age, phone);
        }
        System.out.println("successfully inserted in customer table");
    }
    public static int getId(String name) {
        for (Customer customer : DBUtil.customerHashmap.values()) {
            if (customer.getName().equals(name)) {
                return customer.getCustomer_id();
            }
        }
        return 0;
    }
}
