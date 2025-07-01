public class JuniorAccount extends BankAccount{

    double maxAmount = 100;
    int age;
    double lastWithdrawMoney;

    public JuniorAccount(int accNo, double balance, int pin) {
        super(accNo, balance, pin);
    }

    public double getBalance() { 
        return balance;   //check balance
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
            if(lastWithdrawDate != BankControl.nowDate){
                maxAmount = 100;
                if(balance < money){
                    System.out.println("This account doesn't have the overdraft function. Redo the action again.");
                }
                else if(money > maxAmount){
                    System.out.println("Withdrawing this amount of money is not allowed. Redo the action again.");
                }
                else{
                    balance = balance - money;
                    System.out.println("After withdrawing, the balance is: "+ balance);
                    lastWithdrawDate = BankControl.nowDate;
                    lastWithdrawMoney = money;
                    success = true;
                }
            }
            else{
                maxAmount -= lastWithdrawMoney;
                if(maxAmount < money){
                    System.out.println("You cannot withdraw money over 100 per day. Redo the action again.");
                }
                else if(balance < money){
                    System.out.println("This account doesn't have the overdraft function. Redo the action again.");
                }
                else{
                    balance = balance - money;
                    System.out.println("After withdrawing, the balance is: "+ balance);
                    lastWithdrawDate = BankControl.nowDate;
                    lastWithdrawMoney = money;
                    success = true;
                }
            }
        }
        else{
            System.out.println("The account is suspended now. You can't withdraw money.");
        }
        return success;
    }
}
