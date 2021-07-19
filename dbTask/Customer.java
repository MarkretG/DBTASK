package dbTask;
public class Customer {
    int customer_id;
    String name,mail;
    int age;
    long phone;
    public Customer(int customer_id, String name, String mail, int age, long phone) {
        this.customer_id = customer_id;
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "" +
                "customer_id=" + customer_id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                ", phone=" + phone;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
