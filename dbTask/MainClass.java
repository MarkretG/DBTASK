package dbTask;
import sun.tools.jar.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MainClass {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Long, Account> accountHashMap = new HashMap<>();
    static HashMap<Integer, HashMap<Long, Account>> info = new HashMap<>();
    static HashMap<Integer, Customer> customerHashmap = new HashMap<>();
    public static void main(String[] args) {
        DbConnectionUtil.getConnection();
        MainClass.initial();
        InsertTable.initialCustomer();
        InsertTable.initialAccountsInformation();
        InsertTable.preparedStatement();
        System.out.println("print details");
        System.out.println("1.enter customer_id\n2.enter customer Name\n3.exit");
        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("enter customer id");
                    int id = sc.nextInt();
                    MainClass.getAccountInfo(id);
                    break;
                case 2:
                    System.out.println("enter your name");
                    sc.nextLine();
                    String name = sc.nextLine();
                    for (Customer c : customerHashmap.values()) {
                        if (c.getName().equals(name)) {
                            MainClass.getAccountInfo(c.getCustomer_id());
                            break;
                        }
                    }
                    break;
                case 3:
                    System.exit(0);

            }

        }


    }
    public static void getAccountInfo(int id)
    {
        for (Map.Entry<Integer, HashMap<Long, Account>> a : info.entrySet()) {
            if (a.getKey() == id) {
                HashMap<Long,Account> res=a.getValue();
                for(Map.Entry<Long,Account> ans: res.entrySet())
                {
                    System.out.println(ans.getValue().toString()+" "+ans.getKey());
                    break;
                }
            }
        }
    }
    public  static void initial()
    {
        Connection con = null;
        String query = "delete from customer_info";
        String query1 = "delete from account_info ";

        try {
            con = DbConnectionUtil.getConnection();
            Statement ps = con.createStatement();
            Statement  ps1 = con.createStatement();
            ps.executeQuery(query);
            ps1.executeQuery(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    }