package dbTask;
public class Customer {
    int customer_id;
    String Name,Mail;
    int age;
    String phone;
    public Customer(int customer_id, String name, String mail, int age, String phone) {
        this.customer_id = customer_id;
        Name = name;
        Mail = mail;
        this.age = age;
        this.phone = phone;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
