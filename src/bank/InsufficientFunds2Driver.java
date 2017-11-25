package bank;

import bank.system.*;

public class InsufficientFunds2Driver implements Runnable {

    private static Customer customer;
    private static Account accountA;
    private static Account accountB;
    private static Account accountC;


    public InsufficientFunds2Driver() {
        BankSystem.getBank().printBankSystemInfo();

        customer = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        BankSystem.getBank().printBankSystemInfo();


        accountA = new CurrentAccount(400);
        accountC = new PlatinumAccount(5000);
        accountB = new CurrentAccount(800);

        customer.requestNewAccount(accountA);
        customer.requestNewAccount(accountC);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();
    }

    @Override
    public void run() {
        customer.printBalance(accountA);
        try {
            Thread.sleep(100);
            customer.withdraw(accountA, 200);
            customer.printBalance(accountA);
            customer.withdraw(accountA, 200);
            customer.printBalance(accountA);
            customer.deposit(accountA, 300);
            customer.printBalance(accountA);
            customer.withdraw(accountA, 200);
            customer.printBalance(accountA);
            customer.deposit(accountA, 300);
            customer.printBalance(accountA);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }

        try {
            Thread.sleep(1500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
