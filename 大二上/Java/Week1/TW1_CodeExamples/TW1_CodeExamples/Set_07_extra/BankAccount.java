public class BankAccount {
  private int accNo;
  private String accName;
  private double balance;
  // private double overLimit;

  // public void printMessage() {
  //   System.out.println("Invalid operation: not enough money!");
  // }


  public BankAccount(int accNo, String accName) {
    this.accNo = accNo;
    this.accName = accName;
    this.balance = 0.0;
    // this.overLimit = 100.0;
  }

  public BankAccount(String accName, int accNo) {
    this.accNo = accNo;
    this.accName = accName;
    this.balance = 10.0;
    // this.overLimit = 100.0;
  }

  public int getAccountNo() { return accNo; }
  public String getAccountName() { return accName; }
  public double getBalance() { return balance; }
  // public double getOverLimit() { return overLimit; }

  public void setAccountName(String accName) {
    this.accName = accName;
  }

  public void deposit(double amount) {
    balance = balance + amount;
  }

  void withdraw(double amount) {
    // if (balance + overLimit >= amount) {
      balance = balance - amount;
    // }
    // else printMessage();
  }

  public String toString() {
    return "Account balance = " + balance;
  }
}