package dbTask;
public class MainClass {
    public static void main(String[] args){
         DbConnection.getConnection();
        PreparedStatementBatch.initialCustomer();
        PreparedStatementBatch.initialAccountsInformation();
        PreparedStatementBatch.preparedStatement();

        
    }
}
