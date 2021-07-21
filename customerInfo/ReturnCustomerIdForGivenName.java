package customerInfo;
import dbManagement.ResultSetAreStoredInHashMap;
public class ReturnCustomerIdForGivenName {
    public static int returnCustomerIdForGivenName(String name) {
        for (Customer customer : ResultSetAreStoredInHashMap.customerHashmap.values()) {
            if (customer.getName().equals(name)) {
                return customer.getCustomer_id();
            }
        }
        return 0;
    }
}
