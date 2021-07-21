package customerInfo;
import dbManagement.InsertRowsInCustomerTable;
import java.util.Scanner;
public class GetInsertRowsInformationForCustomerTable {
    static Scanner sc = new Scanner(System.in);
    public static void getInsertRowsInformationForCustomerTable() {

        System.out.println("which number of rows you want to insert customer table");
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
            InsertRowsInCustomerTable.insertRowsInCustomerTable(customer_id, name, mail, age, phone);
        }
        System.out.println("successfully inserted in customer table");
    }
}
