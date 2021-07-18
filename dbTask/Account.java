package dbTask;
public class Account {
    int customer_id;
    long account_no;
    int balance;

    public Account(int customer_id, long account_no, int balance) {
        this.customer_id = customer_id;
        this.account_no = account_no;
        this.balance = balance;
    }
    public Account(int customer_id, int balance) {
        this.customer_id = customer_id;
        this.balance = balance;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public long getAccount_no() {
        return account_no;
    }

    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
