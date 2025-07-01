public class CurrentAccount extends BankAccount {

    public static double overdraft = 500.0;

    public CurrentAccount(int accNo, double balance, int pin) {
        super(accNo, balance, pin);
    }

    public double getOverdraft(){
        return overdraft;
    }
    public double getBalance() { 
        return balance;   //check balance
    }

    public boolean withdraw(int accNo, int pin, double money){
        boolean success = false;
        if(!suspended){
            balance = balance - money; //withdraw money from the account
            if(balance < 0){
                System.out.println("Doing withdraw...Now the account is in overdraft state.");
                if(balance < -500){
                    System.out.println("Withdraw failed. It is over the limit of overdraft (500).");
                    balance = balance + money;
                    success = true;
                }
            }
            System.out.println("After withdraw, the balance is: "+ balance);
        }
        else{
            System.out.println("The account is suspended now. You can't withdraw money.");
        }
        return success;
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
}
