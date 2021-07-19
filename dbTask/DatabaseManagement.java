package dbTask;
import java.util.Scanner;
public class DatabaseManagement {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        DbConnectionUtil.getConnection();
        InsertTableRows.initialiseCustomer();
        InsertTableRows.initialiseAccountsInformation();
        InsertTableRows.insertRowsInMysql();
        ResultSetInHashMap.resultInHashMap();
        System.out.println("welcome to DB management system");
        System.out.println("1.enter customer_id\n2.enter name\n3.exit");
        while(true)
        {
            int choice= sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    int id = sc.nextInt();
                    DatabaseManagement.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    sc.nextLine();
                    String name=sc.nextLine();
                    int givenId=DatabaseManagement.check(name);
                    DatabaseManagement.getAccountInfo(givenId);
                    break;
                case 3: System.exit(0);
                  break;


            }
        }

    }

    public static void getAccountInfo(int id)
    {
        int flag=0;
        for (Account account: ResultSetInHashMap.accountHashMap.values())
        {
            if(account.getCustomer_id()==id) {
                flag=1;
                System.out.println(account.toString());
            }
        }
        for (Customer customer: ResultSetInHashMap.customerHashmap.values())
        {
            if(customer.getCustomer_id()==id) {
                System.out.println(customer.toString());
                break;
            }
        }
        if(flag==0)
            System.out.println("enter information is not available in the table");

    }
    public static int check(String name)
    {

        for(Customer customer:ResultSetInHashMap.customerHashmap.values())
        {
            if(customer.getName().equals(name)) {
                return customer.getCustomer_id();
            }
        }
        return 0;
    }

}