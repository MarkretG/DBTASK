package accountInfo;
import dbManagement.ResultSetAreStoredInHashMap;
import java.util.HashMap;
import java.util.Map;
public class GetAccountInfo {
    public static void getAccountInfo(int id)
    {
        int flag=0;
        for (Map.Entry<Integer, HashMap<Long, Account>> information:ResultSetAreStoredInHashMap.info.entrySet()) {
            if (information.getKey() == id) {
                HashMap<Long, Account> a = information.getValue();
                for (Account account : a.values()) {
                    if (account.getCustomer_id() == id)
                    {
                        flag=1;
                        System.out.println(account.toString());
                    }
                }
            }
        }
        if(flag==0)
            System.out.println("entered info is does not exit in the table");
    }
}
