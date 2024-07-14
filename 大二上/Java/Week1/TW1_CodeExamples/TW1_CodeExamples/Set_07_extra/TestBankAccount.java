public class TestBankAccount {
  public static void main(String[] args) {
    BankAccount myAccount = new BankAccount(12345, "Paula");
    System.out.println("myAccount just created --> " + myAccount);
    myAccount.withdraw(80);

    System.out.println("myBalance after withdrawing 80 yuan -- " + myAccount.getBalance());
    System.out.println("myAccount after withdrawing 80 yuan --> " + myAccount);        
  }
}