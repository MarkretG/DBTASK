package accountInfo;
public class Account {
    private  long customer_id;
    private long account_no;
    private float balance;

    @Override
    public String toString() {
        return "Account{" +
                "customer_id=" + customer_id +
                ", account_no=" + account_no +
                ", balance=" + balance +
                '}';
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getAccount_no() {
        return account_no;
    }

    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
