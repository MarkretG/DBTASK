package dbManagement;
import accountInfo.Account;
import customerInfo.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class DBUtil {
    private static Connection con = null;
    private HashMap<Long, HashMap<Long, Account>> accountsInfo = new HashMap<>();
    private HashMap<Long, String> customerHashmap = new HashMap<>();
    public static Connection getConnection() {
        if (con!=null) {
            return  con;
        }
            try {
                String url = "jdbc:mysql://localhost/info";
                String userName = "root";
                String password = "Root@123";
                // load the Driver Class
                Class.forName("com.mysql.cj.jdbc.Driver");
                // create the connection now
                con = DriverManager.getConnection(url, userName, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
    }
    public void initialiseCustomerTable() throws SQLException
    {
        ArrayList<Customer> customers=GeneralResource.getInstance().getCustomersInfo();
        insertRowsInCustomerTable(customers);
        storeCustomerInfoInCustomerHashMap(customers);
    }

    public void initialiseAccountTable() throws SQLException
    {
        ArrayList<Account> accounts=GeneralResource.getInstance().getAccountsInfo();
        insertRowsInAccountTable(accounts);
        storeAccountInfoInAccountsInfoHashMap(accounts);
    }
    //insert rows in account table in db
    public void insertRowsInAccountTable(ArrayList<Account> account) throws SQLException {
        Connection con = getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into account_info(customer_id,balance) values(?,?)")) {
            for (Account accounts : account) {
                preparedStatement.setLong(1, accounts.getCustomer_id());
                preparedStatement.setFloat(3, accounts.getBalance());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    //insert rows in customer table in db
    public void insertRowsInCustomerTable(ArrayList<Customer> customers) throws SQLException {
        Connection con =getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into customer_info(name,mail,age,phone) values(?,?,?,?)")) {
            for (Customer customer : customers) {
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setString(3, customer.getMail());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.setLong(5, customer.getPhone());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
    public ArrayList<Long> getCustomersIdFromAccountList(ArrayList<Account> accounts)
    {
        ArrayList<Long> customerId=new ArrayList<>();
        for (Account account:accounts)
        {
            customerId.add(account.getCustomer_id());
        }
        return customerId;
    }

    public ArrayList<Long> getCustomersIdFromCustomerList(ArrayList<Customer> customers)
    {
        ArrayList<Long> customerId=new ArrayList<>();
        for (Customer customer:customers)
        {
            customerId.add(customer.getCustomer_id());
        }
        return customerId;
    }
    //customer table all rows  are stored in customer hashmap
    public void storeCustomerInfoInCustomerHashMap(ArrayList<Customer> customers) throws SQLException {
        ArrayList<Long> customerId=getCustomersIdFromCustomerList(customers);
        Connection con = getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("select from customer_id,name where customer_id in (?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Array array=con.createArrayOf("BIGINT",customerId.toArray());
            preparedStatement.setArray(1,array);
            while (resultSet.next()) {
                customerHashmap.put(resultSet.getLong(1),resultSet.getString(2));
            }
        }
    }
    //account table all rows are stored in accounts Info hashmap
    public void storeAccountInfoInAccountsInfoHashMap(ArrayList<Account> accounts) throws SQLException {
        Connection con = getConnection();
        ArrayList<Long> customerId=getCustomersIdFromAccountList(accounts);
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from  account_info where customer_id in (?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Array array=con.createArrayOf("BIGINT",customerId.toArray());
            preparedStatement.setArray(1,array);
            while (resultSet.next()) {
                Account account=new Account();
                account.setCustomer_id(resultSet.getLong(1));
                account.setAccount_no(resultSet.getLong(2));
                account.setBalance(resultSet.getFloat(3));
                HashMap accountHashMap = accountsInfo.get(resultSet.getLong(1));
                if (accountHashMap == null) {
                    accountHashMap = new HashMap<Long, Account>();
                }
                accountHashMap.put(resultSet.getLong(2), account);
                accountsInfo.put(resultSet.getLong(1), accountHashMap);
            }
        }
    }

    public ArrayList<Customer> getCustomersInfoFromUser()
    {
        return GeneralResource.getInstance().getCustomersInfo();
    }
    public ArrayList<Account> getAccountsInfoFromUser()
    {
        return GeneralResource.getInstance().getAccountsInfo();
    }
    //return value for given customer id
    public HashMap<Long, Account> getAccountsInfoFromAccountsInfoHashMap(long id) {
        return accountsInfo.get(id);
    }
    /*public ArrayList<Account> getExistingCustomerVerifyForAddNewAccount(ArrayList<Account> accounts)
    {
        ArrayList<Account> accounts1=new ArrayList<>();
        for (Account account:accounts)
        {
            for (Long key:customerHashmap.keySet())
            {
                if (account.getCustomer_id()==key)
                {
                    accounts1.add(account);
                    break;
                }
            }

        }
        return accounts1;
    }

     */
    public void handleNewUser() throws SQLException
    {
        ArrayList<Customer> customers=getCustomersInfoFromUser();
        insertRowsInCustomerTable(customers);
        storeCustomerInfoInCustomerHashMap(customers);
        ArrayList<Account> account=getAccountsInfoFromUser();
        insertRowsInAccountTable(account);
        storeAccountInfoInAccountsInfoHashMap(account);
    }
    //close db connection
    public void closeConnection(){
        if (con!=null)
        {
            try {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
