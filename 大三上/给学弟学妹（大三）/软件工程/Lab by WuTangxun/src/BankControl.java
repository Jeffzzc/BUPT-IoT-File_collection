import java.util.ArrayList;

public class BankControl {

    static Date nowDate = new Date("2023.05.14");
    static int AccNumber = 0;
    static int Accpin = 1000;
    static boolean success = true;

    public static ArrayList<BankAccount> accounts = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();

    void addCustomer1() {
        Customer customer1 = new Customer(10001,"Sarah",new Date("2002.05.14"));
        customer1.setAddress("Beijing");
        System.out.println(customer1);
        BankControl.customers.add(customer1);
    }
    void addCustomer2() {
        Customer customer2 = new Customer(10002,"Steven",new Date("2001.05.14"));
        customer2.setAddress("Fujian");
        System.out.println(customer2);
		BankControl.customers.add(customer2);
    }
    void addCustomer3() {
        Customer customer3 = new Customer(10003,"Peter",new Date("2012.05.14"));
        customer3.setAddress("Beijing");
        System.out.println(customer3);
        BankControl.customers.add(customer3);
    }
    void addCustomer4() {
        Customer customer4 = new Customer(10004,"John",new Date("2020.05.14"));
        customer4.setAddress("Beijing");
        System.out.println(customer4);
        BankControl.customers.add(customer4);
    }

    boolean openAccount(Customer customer, int type) { //type1. Current Account ; type2. Saver Account ; type3.Junior Account
        success = true;
        if (customer.confirmCreditStatus(customer.getCustomerID())){
            BankAccount account;
            if(type == 1){
                account = new CurrentAccount(++AccNumber,500.0,++Accpin);
                accounts.add(account);
                System.out.println("Open account succeeds! The account number is " + account.getAccNo()+ ", the pin number is " + account.getPin());
            }
            if(type == 2){
                account = new SaverAccount(++AccNumber,500.0,++Accpin);
                accounts.add(account);
                System.out.println("Open account succeeds! The account number is " + account.getAccNo()+ ", the pin number is " + account.getPin());
            }
            else if(type == 3){
                if((nowDate.getTime()-(customer.getBornDate()).getTime())<16*365){ // age < 16 years old
                    account = new JuniorAccount(++AccNumber,150.0,++Accpin);
                    accounts.add(account);
                    System.out.println("Open account succeeds! The account number is " + account.getAccNo()+ ", the pin number is " + account.getPin());
                }
                else{
                    System.out.println("According to your age condition, you cannot open JuniorAccount.");
                    success = false;
                }
            }
        }
         else {
            System.out.println("According to your credit condition, you cannot open any account.");
            success = false;
        }
        return success;
    }

    public boolean closeAccount(int accNo) {
        for (BankAccount account : accounts) {
            if (account.getAccNo() == accNo) {
                if(account.getUnclearedMoney() <= 0.0){
                    if(findBalanceByNo(accNo)>=0){
                        accounts.remove(account);
                        System.out.println("The account which account number is "+  account.getAccNo() +" has been closed.");
                        success = true;
                        break;
                    }
                    else {success = false; System.out.println("The balance < 0. You can't close the account. "); break;}
                }
                else {success = false; System.out.println("There is uncleared money in your account. You can't close the account. "); break;}
            }
            else {success = false;}
        }
        return success;
    }

    public boolean findAccByNo(int accNo){
        for (BankAccount account : accounts) {
            if (account.getAccNo() == accNo) {
                System.out.println("There is an account which account number is "+  accNo +".");
                success = true;
                break;
            }
            else success = false;
        }
        if(!success){
            System.out.println("There isn't an account which account number is "+  accNo +".");
        }
        return success;
    }

    public double findBalanceByNo(int accNo){
        double balance = 0.0;
        for (BankAccount account : accounts) {
            if (account.getAccNo() == accNo) {
                balance = account.getBalance();
                System.out.println("The balance for this account is " + balance);
                break;
            }
        }
        return balance;
    }

    public int findCustomerID(String name, String date){
        int ID = 0;
        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getBornDate().getDate().equals(date)) {
                    System.out.println("The customer ID is " + customer.getCustomerID());
                    ID = customer.getCustomerID();
                break;
            }
        }
        return ID;
    }

    /*public static void main(String[] args) {
        //open new accounts
        CurrentAccount acc1 = new CurrentAccount(1,9000.0,1010);
        System.out.println("the default balance now: "+ acc1.getBalance()); //get the amount of balance

        //operate on accounts
        acc1.withdraw(1,1010,9090);
        acc1.withdraw(1,1010,500);

        acc1.suspend(1);
        acc1.payIn(1,10000);

        acc1.suspend(0);
        acc1.payIn(1,10000);

        JuniorAccount acc2 = new JuniorAccount(2,1000.0,1011);
        System.out.println("the default balance now: "+ acc2.getBalance()); //get the amount of balance
        acc2.withdraw(2,1011,500);
        acc2.withdraw(2,1011,2000);
    }*/
}
