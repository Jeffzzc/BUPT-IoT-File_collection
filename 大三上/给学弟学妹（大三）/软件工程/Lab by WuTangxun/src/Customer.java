
public class Customer {
    private int customerID;
    private boolean creditStatus;
    private String name;
    private String address;
    private Date bornDate;

    public Customer(){

    }
    public Customer(int customerID, String name, Date bornDate){
        this.customerID = customerID;
        this.name = name;
        this.bornDate = bornDate;
    }

    public boolean confirmCreditStatus(int customerID){
        if(address == "Beijing"){
            creditStatus = true;
        }
        else creditStatus = false;

        return creditStatus;
    }

    public void addAccount(BankAccount x){
        BankControl.accounts.add(x);
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public boolean getCreditStatus() {
        return creditStatus;
    }
    public Date getBornDate() {
        return bornDate;
    }
    public int getCustomerID() {
        return customerID;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", credit=" + creditStatus + ", name=" + name + ", birth=" + bornDate.getDate()
                + ", address=" + address + "]";
    }

}

