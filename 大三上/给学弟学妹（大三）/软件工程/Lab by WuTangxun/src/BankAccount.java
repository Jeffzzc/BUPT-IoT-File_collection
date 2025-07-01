public class BankAccount {
    private int accNo;
    protected double balance;
    protected boolean suspended = false;
    private int customerID;
    private int pin;
    static int noticePeriod = 3; //the minimum period of notice is 3 day
    protected int days;
    protected Date lastNoticeDate;
    protected double unclearedMoney;
    protected Date lastWithdrawDate;

    public BankAccount(int accNo, double balance, int pin){
        this.accNo = accNo;
        this.balance = balance;
        this.pin = pin;
    }

    public int getAccNo() { 
        return accNo; 
    }
    public double getCustomerID() { 
        return customerID;   //check balance
    }
    public int getPin(){
        return pin;
    }
    public void setSuspened(boolean suspended){
        this.suspended = suspended;
    }
    public double getBalance() { 
        return balance;   //check balance
    }
    public double getUnclearedMoney() { 
        return unclearedMoney;  //check uncleared money
    }
    public void setLastNoticeDate(Date lastNoticeDate){
        this.lastNoticeDate = lastNoticeDate;
    }
    public void setLastWithdrawDate(Date lastWithdrawDate){
        this.lastWithdrawDate = lastWithdrawDate;
    }

    //存入本账户的钱
    public void payIn(int accNo, double money){
        if(!suspended){
            balance = balance + money;  //pay money into the account
            System.out.println("After paying in, the balance is: " + balance);
        }
        else{
            System.out.println("The account is suspended now. You can't pay in money.");
        }
    }

    //取出本账户的钱
    public boolean withdraw(int accNo, int pin, double money){
        boolean success = false;
        balance = balance - money;  //withdraw money from the account
        return success;
    }

    //对账户的钱对另一个账户进行转入和转出 此时需要进行clear判断
    public static void transfer(int type, double money, BankAccount A, int CustomerID, BankAccount B, boolean cleared){
        Transaction transfer1 = new Transaction();
        transfer1.setCleared(cleared);
        if (type == 1){ //A进行存入
            transfer1.payIn(A,CustomerID,B,money);
        }
        if (type == 0){ //A进行取出
            transfer1.withdraw(A,CustomerID,B,money);
        }
    }

}
