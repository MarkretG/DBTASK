package accountInfo;
import java.util.Scanner;
import dbManagement.DBUtil;
public class AccountManagement{
    static Scanner sc=new Scanner(System.in);
    public  static void setAccountInfo()
    {
         System.out.println("How many number of rows");
         int rows=sc.nextInt();
           for(int i=0;i<rows;i++) {
            System.out.println("enter customer_id");
            int customer_id=sc.nextInt();
            System.out.println("enter account number");
            long account_no=sc.nextLong();
            System.out.println("enter balance");
            int balance=sc.nextInt();
            DBUtil.insertRowsAccount(customer_id,account_no,balance);
        }
        System.out.println("successfully inserted in account table");

    }
    public static void getAccountInfo(int id)
    {
        System.out.println(DBUtil.info.get(id));
    }
}
