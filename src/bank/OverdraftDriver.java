package bank;

import bank.system.*;

public class OverdraftDriver implements Runnable {
    private static Customer customer;
    private static Account account;

    public static void main(String[] args){
        BankSystem.getBank().printBankSystemInfo();

        customer = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        BankSystem.getBank().printBankSystemInfo();

        account = new CurrentAccount(400);
        customer.requestNewAccount(account);
        BankSystem.getBank().printBankSystemInfo();

        Thread threadA = new Thread(new OverdraftDriver());
        Thread threadB = new Thread(new OverdraftDriver());
        threadA.start();
        threadB.start();
    }

    @Override
    public void run() {
        customer.printBalance(account);

        customer.deposit(account, 10);
        customer.printBalance(account);
        customer.requestOverdraft((UnlimitedAccounts) account, 800);
        try {
            customer.withdraw(account, 1000);
            customer.printBalance(account);
            customer.deposit(account, 100);

            customer.withdraw(account, 300);
            customer.printBalance(account);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
    }
}
