import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStream;
import java.util.Scanner;

import javax.xml.ws.WebFault;

import junit.framework.Assert;

class Test {
    @org.junit.jupiter.api.Test
    void testBank() {
        Bank bank = new Bank();
        bank.initialize();
        //bank.addCustomer();

        CreditAgency creditAgency = new CreditAgency();
        creditAgency.initialize();
        Customer customer = new Customer();
        customer = bank.customers.get(1234);
        System.out.println(customer);
        bank.openAccpunt(customer, creditAgency);

        
        }
    }
