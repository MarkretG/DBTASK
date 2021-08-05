package dbManagement;
import accountInfo.*;
import customerInfo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class DBUtil {
    static Connection con = null;
    GeneralResource newGeneralResource = GeneralResource.getInstance();
    static HashMap<Long, HashMap<Long, Account>> accountsInfo = new HashMap<>();
    static HashMap<Long, Customer> customerHashmap = new HashMap<>();
    public static Connection getConnection() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost/info";
                String uname = "root";
                String pass = "Root@123";
                // load the Driver Class
                Class.forName("com.mysql.cj.jdbc.Driver");
                // create the connection now
                con = DriverManager.getConnection(url, uname, pass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return con;
    }
    //insert rows in account table in db
    public ArrayList<Account> insertRowsInAccountTable(ArrayList<Account> account) throws SQLException {
        Connection con = getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into account_info(customer_id,account_no,balance) values(?,?,?)")) {
            for (Account accounts : account) {
                preparedStatement.setLong(1, accounts.getCustomer_id());
                preparedStatement.setLong(2, accounts.getAccount_no());
                preparedStatement.setFloat(3, accounts.getBalance());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
        return account;
    }

    //insert rows in customer table in db
    public void insertRowsInCustomerTable(ArrayList<Customer> customers) throws SQLException {
        Connection con =getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into customer_info(customer_id,name,mail,age,phone) values(?,?,?,?,?)")) {
            for (Customer customer : customers) {
                preparedStatement.setLong(1, customer.getCustomer_id());
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
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from  customer_info where customer_id in (?)");
             ResultSet resultSet = preparedStatement.executeQuery()) {
             Array array=con.createArrayOf("BIGINT",customerId.toArray());
             preparedStatement.setArray(1,array);
                while (resultSet.next()) {
                    Customer customer=new Customer();
                    customer.setCustomer_id(resultSet.getLong(1));
                    customer.setName(resultSet.getString(2));
                    customer.setMail(resultSet.getString(3));
                    customer.setAge(resultSet.getInt(4));
                    customer.setPhone(resultSet.getLong(5));
                    customerHashmap.put(resultSet.getLong(1), customer);
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

    //get customers info from user and insert rows in customer table and store table info in hashmap
    public ArrayList<Customer> addCustomersInfoInDb() throws SQLException {
        ArrayList<Customer> customer = newGeneralResource.getCustomersInfo();
        insertRowsInCustomerTable(customer);
        //System.out.println("successfully insert in customer table");
        //storeCustomerInfoInCustomerHashMap(customer);
        return customer;
    }
    //get accounts info from user and insert rows in account table and store table info in hashmap
    public ArrayList<Account> addAccountsInfoInDb() throws SQLException {
        ArrayList<Account> account = newGeneralResource.getAccountsInfo();
        insertRowsInAccountTable(account);
        //System.out.println("successfully insert in account table");
        //storeAccountInfoInAccountsInfoHashMap(account);
        return account;
    }
    //find given customer id for given customer name and return customer_id
    public long getCustomerIdFromCustomerHashMap(String name) {
        for (Customer customer : customerHashmap.values()) {
            if (customer.getName().equals(name)) {
                return customer.getCustomer_id();
            }
        }
        return 0;
    }
    //return value for given customer id
    public HashMap<Long, Account> getAccountsInfoFromAccountsInfoHashMap(long id) {
        return accountsInfo.get(id);
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

