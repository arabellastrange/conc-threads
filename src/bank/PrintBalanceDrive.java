package bank;

import bank.system.*;

public class PrintBalanceDrive implements Runnable {
    private static Account accountA;
    private static Account accountB;
    private static Account accountC;
    private static Customer customerA;
    private static Customer customerB;

    public static void main(String[] args) {
        BankSystem.getBank().printBankSystemInfo();

        customerA = new Customer("Jean", BankSystem.getBank().getEmployee(0));
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

        Thread threadA = new Thread(new PrintBalanceDrive());
        Thread threadB = new Thread(new PrintBalanceDrive());
        threadA.start();
        threadB.start();
    }

    @Override
    public void run() {
        customerA.printBalance(accountB);
        customerA.printBalance(accountA);
        customerA.printBalance(accountC);
    }
}
