public class CurrentAccont extends BankAccount{
    private int accountNumber;
    int customerID;
    int pin;
    double balance = 0;
    boolean activity = true;
    float overDraftLimit = -500;
    
    public CurrentAccont() {

    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "CurrentAccont [accountNumber=" + accountNumber + ", customerID=" + customerID + ", pin=" + pin
                + ", balance=" + balance + ", activity=" + activity + ", overDraftLimit=" + overDraftLimit + "]";
    }
    
}
