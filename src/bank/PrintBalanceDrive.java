package bank;

import bank.system.*;

public class PrintBalanceDrive implements Runnable {
    private Account accountA;
    private Account accountB;
    private Account accountC;
    private Customer customerA;
    private Customer customerB;

    public PrintBalanceDrive(){
        BankSystem.getBank().printBankSystemInfo();

        customerA = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customerA);
        BankSystem.getBank().printBankSystemInfo();

        accountA = new CurrentAccount(400);
        accountB = new PlatinumAccount(5000);
        accountC = new SavingAccount(1000);
        customerA.requestNewAccount(accountA);
        customerA.requestNewAccount(accountB);
        customerA.requestNewAccount(accountC);
        BankSystem.getBank().printBankSystemInfo();

        customerB = new Customer("Ororo", BankSystem.getBank().getEmployee(0));

        BankSystem.getBank().addCustomer(customerB);
        BankSystem.getBank().printBankSystemInfo();
    }

    @Override
    public void run() {
        customerA.printBalance(accountB);
        customerA.printBalance(accountA);
        customerA.printBalance(accountC);
        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
