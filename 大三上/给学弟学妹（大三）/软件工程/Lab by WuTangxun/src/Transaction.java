public class Transaction { //用于不同账户的存取问题，即从所在的账户存入钱从另一个账户/取出钱到另一个账户
    double amount;
    Date date;
    boolean cleared = false;
    BankAccount account1; 
    BankAccount account2; 

    public void setCleared(boolean cleared){ //periodically doing clear, set cleared as true to do transaction
        this.cleared = cleared;
    }

    //把B钱存入A
    public double payIn(BankAccount A, int customerBID, BankAccount B,double money){
        boolean success;
        this.account1 = A; //此时A为所在账户 把钱存入A
        this.account2 = B; //此时B为目的账户 从B中取钱
        if(cleared){
            if(account2 instanceof JuniorAccount){
                success = account2.withdraw(customerBID, (1000+customerBID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
            if(account2 instanceof CurrentAccount){
                success = account2.withdraw(customerBID, (1000+customerBID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
            if(account2 instanceof SaverAccount){
                success = account2.withdraw(customerBID, (1000+customerBID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
        }
        else {
            System.out.println("The money didn't be cleared. Try again.");
            account1.unclearedMoney = money;
            account2.unclearedMoney = money;
        }
        return account1.balance;
    }

    //把A钱存入B
    public double withdraw(BankAccount A, int customerAID, BankAccount B,double money){
        this.account1 = B; //此时B为目的账户 把钱存入B
        this.account2 = A; //此时A为所在账户 从A里取钱
        boolean success;
        if(cleared){
            if(account2 instanceof JuniorAccount){
                success = account2.withdraw(customerAID, (1000+customerAID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
            if(account2 instanceof CurrentAccount){
                success = account2.withdraw(customerAID, (1000+customerAID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
            if(account2 instanceof SaverAccount){
                success = account2.withdraw(customerAID, (1000+customerAID-10000), money);
                if(success){
                    account1.balance += money;
                }
            }
        }
        else {
            System.out.println("The money didn't be cleared. Try again.");
            account1.unclearedMoney = money;
            account2.unclearedMoney = money;
        }
        return account2.balance;
    }
}
