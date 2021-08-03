package dbManagement;
import java.util.Scanner;
public class GeneralResource {
    Scanner sc=new Scanner(System.in);
    public  String getString()
    {
       String s= sc.nextLine();
       return s;
    }
    public int getInt()
    {
        int info=sc.nextInt();
        return info;
    }
    public long getLong()
    {
        long info=sc.nextLong();
        return info;
    }
    public float getFloat()
    {
        float info=sc.nextFloat();
        return info;
    }

}
