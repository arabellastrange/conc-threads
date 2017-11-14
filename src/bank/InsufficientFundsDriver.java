package bank;

import bank.system.*;

public class InsufficientFundsDriver implements Runnable {
    private static Customer customer;
    private static Account accountA;
    private static Account accountB;

    public static void main(String[] args) {
        BankSystem.getBank().printBankSystemInfo();

        customer = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        BankSystem.getBank().printBankSystemInfo();


        accountA = new CurrentAccount(400);
        accountB = new PlatinumAccount(5000);
        customer.requestNewAccount(accountA);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        Thread threadA = new Thread(new InsufficientFundsDriver());
        Thread threadB = new Thread(new InsufficientFundsDriver());
        threadA.start();
        threadB.start();
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
            customer.withdraw(accountA, 200);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }

    }
}
