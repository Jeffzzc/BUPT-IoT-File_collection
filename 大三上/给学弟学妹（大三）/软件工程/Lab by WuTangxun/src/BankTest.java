import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest{
    
	@Test
	public void test(){

		BankControl openBank = new BankControl();
		openBank.addCustomer1();
		openBank.addCustomer2();
		openBank.addCustomer3();
		openBank.addCustomer4();

		System.out.println();

		//检验添加用户成功，并且按指定条件创建多个账户成功
		//type1. Current Account ; type2. Saver Account ; type3.Junior Account
		System.out.println("For opening account from customer...");
		assertEquals(true,openBank.openAccount(BankControl.customers.get(0), 1));
		assertEquals(true,openBank.openAccount(BankControl.customers.get(0), 2));
		assertEquals(false,openBank.openAccount(BankControl.customers.get(0), 3));

		assertEquals(false,openBank.openAccount(BankControl.customers.get(1), 1));
		assertEquals(false,openBank.openAccount(BankControl.customers.get(1), 2));
		assertEquals(false,openBank.openAccount(BankControl.customers.get(1), 3));

		assertEquals(true,openBank.openAccount(BankControl.customers.get(2), 1));
		assertEquals(true,openBank.openAccount(BankControl.customers.get(2), 2));
		assertEquals(true,openBank.openAccount(BankControl.customers.get(2), 3));
		
		assertEquals(true,openBank.openAccount(BankControl.customers.get(3), 3));
		System.out.println();

		//检验Cash(已经cleared，即直接payIn转入的)存钱功能
		//此时无论是什么类型的账户，存入效果都是一样的，所以只用父类方法测了一次
		System.out.println("For depositing money...");
		BankControl.accounts.get(0).payIn(1, 40.0);
		assertEquals(540.0,BankControl.accounts.get(0).getBalance(),0.0);
		System.out.println();

		//检验Cash(已经cleared，即直接withdraw取出的)取钱功能
		//此时运用了三个不同的账户类型来测试：
		System.out.println("For withdrawing money...");
		System.out.println();

		//账号3是CurrentAccount，本金500. 
		System.out.println("1. CurrentAccount:");
		BankControl.accounts.get(2).withdraw(3, 1003, 400.0); //正常取钱时
		BankControl.accounts.get(2).withdraw(3, 1003, 300.0); //赊账时
		BankControl.accounts.get(2).withdraw(3, 1003, 400.0); //超过赊账范围时
		System.out.println();

		//账号2是SaverAccount，本金500
		System.out.println("2. SaverAccount:");
		//没到period时：
		BankControl.accounts.get(1).setLastNoticeDate(new Date("2023.05.13"));
		BankControl.accounts.get(1).withdraw(2, 1002, 400.0); //正常取钱时
		BankControl.accounts.get(1).withdraw(2, 1002, 300.0); //赊账时

		//过了period时：
		BankControl.accounts.get(1).setLastNoticeDate(new Date("2023.05.01"));
		BankControl.accounts.get(1).withdraw(2, 1002, 600.0); //赊账时
		BankControl.accounts.get(1).withdraw(2, 1002, 400.0); //正常取钱时

		//该次取钱机会已用完时 测试Saver账户不可以时限内再取钱
		BankControl.accounts.get(1).withdraw(2, 1002, 50.0); 
		System.out.println();

		//账号5是JuniorAccount，本金150
		System.out.println("3. JuniorAccount:");
		BankControl.accounts.get(4).withdraw(5, 1005, 90.0); //正常取钱时

		//测试Junior账户6 100元/day的条件下 当天是否可以取大于100元
		BankControl.accounts.get(5).withdraw(6, 1006, 30.0); 
		BankControl.accounts.get(5).withdraw(6, 1006, 60.0); //同日取的总额在100元以内时
		BankControl.accounts.get(5).withdraw(6, 1006, 20.0); //同日取的总额大于100元时

		//测试Junior账户6 100元/day的条件下 换一天是否可以取出累计大于100元
		BankControl.accounts.get(5).setLastWithdrawDate(new Date("2023.05.13"));
		BankControl.accounts.get(5).withdraw(6, 1006, 20.0); 

		//测试Junior账户6 是否可以赊账
		BankControl.accounts.get(5).withdraw(6, 1006, 50.0);
		System.out.println();

		//检验transaction(还未cleared，需要手动进行cleared)功能

		//此时需要先获取取出账户方的用户ID才可进行transfer
		System.out.println("For getting customer ID...");
		openBank.findCustomerID("Peter", "2012.05.14");
		assertEquals(10003,openBank.findCustomerID("Peter", "2012.05.14"));
		System.out.println();

		//测试SaverAccount(账号4)和JuniorAccount（账号5）的转钱过程：
		//此时账号4剩500，账号5剩60
		System.out.println("For Transaction from one to another account...");
		//还未cleared时：
		BankAccount.transfer(0, 50, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), false);
		BankAccount.transfer(1, 50, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), false);
		//cleared后：

		//把钱从Saver(账号4)中取出，存入Junior(账号5)中，有Saver取钱的限制(period和赊账)：
		//type0：检测取出功能
		BankControl.accounts.get(3).setLastNoticeDate(new Date("2023.05.13"));
		BankAccount.transfer(0, 50, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		
		//成功取钱
		BankControl.accounts.get(3).setLastNoticeDate(new Date("2023.05.09"));
		BankAccount.transfer(0, 50, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(450.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(110.0,BankControl.accounts.get(4).balance,0.0);

		//同天内不可以再次从Saver账户取钱
		BankAccount.transfer(0, 480, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(450.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(110.0,BankControl.accounts.get(4).balance,0.0);

		//不可以在Saver账户赊账
		BankControl.accounts.get(3).setLastNoticeDate(new Date("2023.05.10"));
		BankAccount.transfer(0, 480, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(450.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(110.0,BankControl.accounts.get(4).balance,0.0);
		System.out.println();

		//把钱从Junior中取出，存入Saver中，有Junior取钱的限制(同天最高100元和赊账)
		//此时账号4剩450，账号5剩110
		//type1：检测存入功能
		System.out.println("For Transaction from one to another account...");
		//成功取钱
		BankControl.accounts.get(4).setLastWithdrawDate(new Date("2023.05.11"));
		BankAccount.transfer(1, 60, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(510.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(50.0,BankControl.accounts.get(4).balance,0.0);

		//同天限制不能取多于100元
		BankAccount.transfer(1, 45, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(510.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(50.0,BankControl.accounts.get(4).balance,0.0);

		//不能赊账
		BankControl.accounts.get(4).setLastWithdrawDate(new Date("2023.05.12"));
		BankAccount.transfer(1, 51, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), true);
		assertEquals(510.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(50.0,BankControl.accounts.get(4).balance,0.0);
		System.out.println();

		//检验吊销账户
		System.out.println("For suspending account...");
		BankControl.accounts.get(0).setSuspened(true);

		BankControl.accounts.get(0).withdraw(1, 1003, 400.0);
		assertEquals(540.0,BankControl.accounts.get(0).balance,0.0);
		BankControl.accounts.get(0).payIn(1, 400.0);
		assertEquals(540.0,BankControl.accounts.get(0).balance,0.0);
		BankAccount.transfer(0, 400, BankControl.accounts.get(0), 10001, BankControl.accounts.get(4), true);
		assertEquals(540.0,BankControl.accounts.get(0).balance,0.0);
		System.out.println();

		//检验重启账户
		//此时账户1剩540
		System.out.println("For reinstating account...");
		BankControl.accounts.get(0).setSuspened(false);

		BankControl.accounts.get(0).withdraw(1, 1001, 400.0);
		assertEquals(140.0,BankControl.accounts.get(0).balance,0.0);
		BankControl.accounts.get(0).payIn(1, 400.0);
		assertEquals(540.0,BankControl.accounts.get(0).balance,0.0);
		BankAccount.transfer(0, 400, BankControl.accounts.get(0), 10001, BankControl.accounts.get(4), true);
		assertEquals(140.0,BankControl.accounts.get(0).balance,0.0);
		System.out.println();

		//检验balance<0时不能关闭账户
		//此时账户1剩140
		System.out.println("For closing account when balance < 0...");
		BankControl.accounts.get(0).withdraw(1, 1001, 200.0);
		assertEquals(-60.0,BankControl.accounts.get(0).balance,0.0);
		assertEquals(false,openBank.closeAccount(BankControl.accounts.get(0).getAccNo()));
		assertEquals(true,openBank.findAccByNo(1));
		System.out.println();

		//检验存在uncleared时不能关闭账户
		//此时账号3剩510
		System.out.println("For closing account when there is uncleared money...");
		BankAccount.transfer(1, 110, BankControl.accounts.get(3), 10003, BankControl.accounts.get(4), false);
		assertEquals(510.0,BankControl.accounts.get(3).balance,0.0);
		assertEquals(110.0,BankControl.accounts.get(3).unclearedMoney,0.0);
		assertEquals(false,openBank.closeAccount(BankControl.accounts.get(3).getAccNo()));
		assertEquals(true,openBank.findAccByNo(4));
		System.out.println(); 

		//检验关闭账户成功
		//此时账号1赊账60
		System.out.println("For closing account successfully...");
		BankControl.accounts.get(0).payIn(1, 100.0);
		assertEquals(true,openBank.closeAccount(BankControl.accounts.get(0).getAccNo()));
		assertEquals(false,openBank.findAccByNo(1));
		System.out.println();
	}
}
