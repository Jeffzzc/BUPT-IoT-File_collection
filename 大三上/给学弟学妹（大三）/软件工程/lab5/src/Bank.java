import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Console.*;

import junit.framework.Assert;

public class Bank {
    HashMap<Integer, BankAccount> accounts = new HashMap<>();
    HashMap<Integer, Customer> customers = new HashMap<>();
    ArrayList<Transaction> trasactions = new ArrayList<>();
    ZonedDateTime zonedDateTime = ZonedDateTime.now();

    void initialize() {
        Customer customer = new Customer();
        customer.setCustomerID(1234);
        customer.name.setFisrtName("wu");
        customer.name.setLastName("wei");
        customer.setBirth(new Date(2002, 1, 11));
        customer.address.setCuntry("china");
        customer.address.setCity("nanjing");
        customers.put(customer.getCustomerID(), customer);
        System.out.println(customers);
    }

    Customer login() {
        int customerID = 0;
        Scanner scanner = new Scanner(System.in);
        String str;

        System.out.println("Please input your ID:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customerID = Integer.parseInt(str);
        }
        // System.out.println(customers.get(customerID));
        return customers.get(customerID);
    }

    void addCustomer() {
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        int year = 0;
        int month = 0;
        int day = 0;
        String str;

        System.out.println("Please input your ID:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customer.setCustomerID(Integer.parseInt(str));
        }
        System.out.println(customer.getCustomerID());

        System.out.println("Please input your first name:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customer.name.setFisrtName(str);
            // Assert.assertEquals(str,"wu");
        }

        System.out.println("Please input your last name:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customer.name.setLastName(str);
        }
        System.out.println(customer.name.toString());

        System.out.println("Please input your year of birth:");
        if (scanner.hasNext()) {
            str = scanner.next();
            year = Integer.parseInt(str);
        }

        System.out.println("Please input your month of birth:");
        if (scanner.hasNext()) {
            str = scanner.next();
            month = Integer.parseInt(str);
        }

        System.out.println("Please input your day of birth:");
        if (scanner.hasNext()) {
            str = scanner.next();
            day = Integer.parseInt(str);
        }
        customer.setBirth(new Date(year, month, day));
        System.out.println(customer.getBirth());

        System.out.println("Please input your country:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customer.address.setCuntry(str);
        }

        System.out.println("Please input your city:");
        if (scanner.hasNext()) {
            str = scanner.next();
            customer.address.setCity(str);
        }
        System.out.println(customer.address.toString());

        System.out.println(customer.toString());

        scanner.close();

        customers.put(customer.getCustomerID(), customer);
        System.out.println(customers);
    }

    void openAccpunt(Customer customer, CreditAgency creditAgency) {
        if (confirmCreditStatus(customer, creditAgency)) {
            System.out.println(customer);
            BankAccount account = new BankAccount();
            Scanner scanner = new Scanner(System.in);
            String str;
            int choice = 0;

            if (checkAge(customer)) {
                System.out.println(
                        "Please input the account you want to open:\n 1. Current Account\n 2. Saver Account \n 3.Junior Account");

                if (scanner.hasNext()) {
                    str = scanner.next();
                    choice = Integer.parseInt(str);
                }
                switch (choice) {
                    case 1:
                        openCurrentAccount(account, customer);
                        break;
                    case 2:
                        account = new SaverAccount();
                        break;
                    case 3:
                        account = new JuniorAccount();
                        break;
                }
                System.out.println("Sucess");
                scanner.close();
            } else {
                System.out.println(
                        "Please input the account you want to open:\n 1. Current Account\n 2. Saver Account");
                if (scanner.hasNext()) {
                    str = scanner.next();
                    choice = Integer.parseInt(str);
                }
                switch (choice) {
                    case 1:
                        account = new CurrentAccont();
                        break;
                    case 2:
                        account = new SaverAccount();
                        break;
                }
                System.out.println("Sucess");
                scanner.close();
            }
        } else {
            System.out.println("According to your credit condition, you cannot open account.");
        }
    }

    boolean confirmCreditStatus(Customer customer, CreditAgency creditAgency) {
        boolean credit = true;
        credit = creditAgency.credits.get(1234);
        customer.setCredit(credit);
        return credit;
    }

    public boolean checkAge(Customer customer) {
        boolean flag = true;
        Date date = customer.getBirth();
        if ((zonedDateTime.getYear() - date.getYear()) > 16) {
            flag = true;
        } else {
            flag = false;
        }
        // System.out.println(date.getYear() - zonedDateTime.getYear());
        return flag;
    }

    public void openCurrentAccount(BankAccount account, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        String str;
        account = new CurrentAccont();
        System.out.print(account.getClass());
        account.setAccountNumber(account.generation());
        account.setCustomerID(customer.getCustomerID());
        System.out.println("Please input the PIN:");
        if (scanner.hasNext()) {
            str = scanner.next();
            account.setPin(Integer.parseInt(str));
        }
        System.out.println(account);
        accounts.put(account.getAccountNumber(), account);
        customer.accounts.add(account);
        System.out.println(accounts);
        System.out.println(customer.accounts);
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        CreditAgency creditAgency = new CreditAgency();
        bank.initialize();
        creditAgency.initialize();
        Customer customer = new Customer();
        customer = bank.login();

        System.out.println(customer);

        bank.openAccpunt(customer, creditAgency);
        // bank.addCustomer();
    }
}
