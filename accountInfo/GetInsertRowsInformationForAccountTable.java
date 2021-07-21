package accountInfo;
import java.util.Scanner;
import dbManagement.*;
public class GetInsertRowsInformationForAccountTable{
    static Scanner sc=new Scanner(System.in);
    public  static void getInsertRowsInformationForAccountTable()
    {
         System.out.println("which number of rows you want to insert account table");
         int rows=sc.nextInt();
           for(int i=0;i<rows;i++) {
            System.out.println("enter customer_id");
            int customer_id=sc.nextInt();
            System.out.println("enter account number");
            long account_no=sc.nextLong();
            System.out.println("enter balance");
            int balance=sc.nextInt();
            InsertRowsInAccountTable.insertRowsInAccountTable(customer_id,account_no,balance);
        }
        System.out.println("successfully inserted in account table");

    }
}
