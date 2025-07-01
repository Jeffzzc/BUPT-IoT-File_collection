public class SaverAccount extends BankAccount{
    int from1;
    int to1;

    public SaverAccount(int accNo, double balance, int pin) {
        super(accNo, balance, pin);
    }

    public void setLastNoticeDate(Date lastNoticeDate){
        this.lastNoticeDate = lastNoticeDate;
        from1 = lastNoticeDate.getTime(); 
        to1 = BankControl.nowDate.getTime(); 
        days = to1 - from1; 
    }

    public double getBalance() { 
        return balance;   //check balance
    }
    public int getDays() { 
        return days;   //check balance
    }

    public void payIn(int accNo, double money){
        if(!suspended){
            balance = balance + money;  //pay money into the account
            System.out.println("After paying in, the balance is: " + balance);
        }
        else{
            System.out.println("The account is suspended now. You can't pay in money.");
        }
    }

    public boolean withdraw(int accNo, int pin, double money){
        boolean success = false;
        if(!suspended){
            if(days > noticePeriod){
                if(balance < money){
                    System.out.println("This account doesn't have the overdraft function. Redo the action again.");
                }
                else{
                    balance = balance - money;                    
                    System.out.println("After withdrawing, the balance is: "+ balance);
                    lastNoticeDate = BankControl.nowDate;
                    from1 = to1;
                    days = to1 - from1; 
                    success = true;
                }
            }
            else{
                System.out.println("Didn't achieve the minimum notice period. You can't withdraw money.");
            }
        }
        else{
            System.out.println("The account is suspended now. You can't withdraw money.");
        }
        return success;
    }
}
