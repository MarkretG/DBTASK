package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class DBManagementSystem {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        Connection con=DBUtil.getConnection();
        System.out.println("welcome to db Management system");
        System.out.println("Initially insert all rows in customer table and Account table");
        GetUtilCustomer.getInfo();
        GetUtilAccount.getInfo();
        DBUtil.getAccount();
        DBUtil.getCustomer();
        System.out.println("1.account info for given customer_id\n2.account info for given name\n3.Do you want to insert additional rows\n4.exit");
        while (true)
        {
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("enter customer id");
                    int id = sc.nextInt();
                    GetUtilAccount.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    int givenId=GetUtilCustomer.getId(name);
                    GetUtilAccount.getAccountInfo(givenId);
                    break;
                case 3:
                    System.out.println("1.Do you want insert row in customer table\n2.Do you want to insert row in accountTable\n3.exit");
                    boolean end=true;
                    while (end) {
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                GetUtilCustomer.getInfo();
                                DBUtil.getCustomer();
                                break;
                            case 2:
                                GetUtilAccount.getInfo();
                                DBUtil.getAccount();
                                DBUtil.getCustomer();
                                break;
                            case 3:
                                System.out.println("updated");
                                end = false;
                        }
                    }
                    break;

                case 4:
                    if(con!=null)
                    {
                        try {
                            con.close();
                        }
                        catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    System.exit(0);
            }
        }
    }
}

