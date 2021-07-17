package dbTask;
public class Account {
    int customer_id;
    String accountNo;
    int balance;

    public Account(int customer_id, String accountNo, int balance) {
        this.customer_id = customer_id;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
