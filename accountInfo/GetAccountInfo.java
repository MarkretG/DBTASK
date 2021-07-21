package accountInfo;
import dbManagement.ResultSetAreStoredInHashMap;
import java.util.HashMap;
import java.util.Map;
public class GetAccountInfo {
    public static void getAccountInfo(int id)
    {
        for (Map.Entry<Integer, HashMap<Long, Account>> information:ResultSetAreStoredInHashMap.info.entrySet()) {
            if (information.getKey() == id) {
                HashMap<Long, Account> a = information.getValue();
                for (Account account : a.values()) {
                    if (account.getCustomer_id() == id)
                        System.out.println(account.toString());
                }
            }
        }
    }
}
