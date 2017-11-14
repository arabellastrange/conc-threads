package bank;

import bank.system.*;

public class MovingMoneyDriver implements Runnable{
    private static Customer customer;
    private static Account accountA;
    private static Account accountB;
    private static Account accountC;
    private static Customer customerA;
    private static Customer customerB;

    public static void main(String args[]){
        BankSystem.getBank().printBankSystemInfo();

        customer = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        BankSystem.getBank().printBankSystemInfo();

        accountA = new CurrentAccount(400);
        customer.requestNewAccount(accountA);
        BankSystem.getBank().printBankSystemInfo();

        customerA = new Customer("Ororo", BankSystem.getBank().getEmployee(0));

        accountB = new PlatinumAccount(5000);
        BankSystem.getBank().addCustomer(customerA);
        customerA.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        customerB = new Customer("Kurt", BankSystem.getBank().getEmployee(0));
        accountC = new CurrentAccount(20);
        BankSystem.getBank().addCustomer(customerB);
        customerB.requestNewAccount(accountC);
        BankSystem.getBank().printBankSystemInfo();

        Thread t0 = new Thread(new MovingMoneyDriver());
        Thread t1 = new Thread(new MovingMoneyDriver());
        t0.start();
        t1.start();

    }

    @Override
    public void run() {
        customer.printBalance(accountA);
        customer.deposit(accountA, 300);
        customer.printBalance(accountA);
        try {
            customerA.printBalance(accountB);
            customerA.transfer(accountB, 200, accountA.getAccountNumber());
            customerA.printBalance(accountB);
            customer.transfer(accountA, 100, accountC.getAccountNumber());
            customer.printBalance(accountA);
            customerB.printBalance(accountC);
            customerB.withdraw(accountC, 10);
            customerB.printBalance(accountC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //deposit transfer pay interest and withdraw with overdraft
    }

}
