package bank;

import bank.system.Account;
import bank.system.BankSystem;
import bank.system.CurrentAccount;
import bank.system.Customer;

public class ExampleDriver implements Runnable {
    private static Customer customer;
    private static Account accountA;
    private static Account accountB;

    public static void main(String[] args) {
        //set up bank: accounts and customers all created
        BankSystem.getBank().printBankSystemInfo();
        customer = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        accountA = new CurrentAccount(400);
        customer.requestNewAccount(accountA);
        BankSystem.getBank().printBankSystemInfo();
        accountB = new CurrentAccount(500);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        Thread threadA = new Thread(new ExampleDriver());
        Thread threadB = new Thread(new ExampleDriver());
        threadA.start();
        threadB.start();
    }

    @Override
    public void run() {
        // preform your actions: deposit, transfer and withdraw
        customer.printBalance(accountA);
        customer.deposit(accountA, 10);
        customer.printBalance(accountA);
        customer.printBalance(accountB);
        try {
            Thread.sleep(100);
            customer.transfer(accountA, 20, accountB.getAccountNumber());
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
        customer.printBalance(accountA);
        customer.printBalance(accountB);
    }
}
