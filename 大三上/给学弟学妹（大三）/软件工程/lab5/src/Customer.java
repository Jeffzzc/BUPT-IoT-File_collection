import java.util.ArrayList;

public class Customer {
    private int customerID;
    private boolean credit;
    Name name = new Name();
    private Date birth = new Date();
    Address address = new Address();
    ArrayList<BankAccount> accounts = new ArrayList<>();

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", credit=" + credit + ", name=" + name + ", birth=" + birth
                + ", address=" + address + ", accounts=" + accounts + "]";
    }


}

class Name {
    private String fisrtName;
    private String lastName;

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Name [fisrtName=" + fisrtName + ", lastName=" + lastName + "]";
    }
    
}

class Address {
    private String cuntry;
    private String city;
    public void setCuntry(String cuntry) {
        this.cuntry = cuntry;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Address [cuntry=" + cuntry + ", city=" + city + "]";
    }

    
}
