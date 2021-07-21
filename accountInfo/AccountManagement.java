package accountInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import dbManagement.DBUtil;
public class AccountManagement{
    static Scanner sc=new Scanner(System.in);
    public  static void getInfoAccount()
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
            DBUtil.setRowsAccount(customer_id,account_no,balance);
        }
        System.out.println("successfully inserted in account table");

    }
    public static void getAccountInfo(int id)
    {
        int flag=0;
        for (Map.Entry<Integer, HashMap<Long, Account>> information: DBUtil.info.entrySet()) {
            if (information.getKey() == id) {
                flag=1;
                HashMap<Long,Account> getInfo=information.getValue();
                for (Account account:getInfo.values())
                    System.out.println(account.toString());
                break;
            }
        }
        if (flag==0)
            System.out.println("entered info  does not exit in the account table");
    }
}
