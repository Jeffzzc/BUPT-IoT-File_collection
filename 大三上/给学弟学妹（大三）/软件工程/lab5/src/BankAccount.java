import java.util.Random;

public class BankAccount {
    private int accountNumber;
    private int customerID;
    private int pin;
    private double balance;
    private boolean activity;
    private float overDraftLimit;

    
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean isActivity() {
        return activity;
    }
    public void setActivity(boolean activity) {
        this.activity = activity;
    }
    public float getOverDraftLimit() {
        return overDraftLimit;
    }
    public void setOverDraftLimit(float overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }
    public BankAccount() {

    }
    public BankAccount(int customerID, int pin, double balance, boolean activity) {
        this.customerID = customerID;
        this.pin = pin;
        this.balance = balance;
        this.activity = activity;
    }

    public int generation() {
        int accountNumber;
        Random random = new Random();
        accountNumber = random.nextInt(10000);
        return accountNumber;
    }
    @Override
    public String toString() {
        return "BankAccount [accountNumber=" + accountNumber + ", customerID=" + customerID + ", pin=" + pin
                + ", balance=" + balance + ", activity=" + activity + ", overDraftLimit=" + overDraftLimit + "]";
    }
    
    
}
