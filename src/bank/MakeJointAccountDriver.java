package bank;

import bank.system.*;

public class MakeJointAccountDriver implements Runnable {
    private static Account accountA;
    private static Account accountB;
    private static Account accountC;
    private static Customer customerA;
    private static Customer customerB;

    public static void main(String args[]) {
        BankSystem.getBank().printBankSystemInfo();

        customerA = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customerA);
        BankSystem.getBank().printBankSystemInfo();

        accountA = new CurrentAccount(400);
        accountB = new PlatinumAccount(5000);
        customerA.requestNewAccount(accountA);
        customerA.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        customerB = new Customer("Ororo", BankSystem.getBank().getEmployee(0));
        accountC = new SavingAccount(600);
        BankSystem.getBank().addCustomer(customerB);
        customerB.requestNewAccount(accountC);
        BankSystem.getBank().printBankSystemInfo();

        Thread threadA = new Thread(new MakeJointAccountDriver());
        Thread threadB = new Thread(new MakeJointAccountDriver());
        threadA.start();
        threadB.start();

    }

    @Override
    public void run() {
        customerB.requestJointAccount(accountC, customerA);
        BankSystem.getBank().printBankSystemInfo();
        customerA.printBalance(accountC);
        customerA.deposit(accountC, 20);
        customerA.printBalance(accountC);
        try {
            customerB.withdraw(accountC, 10);
            customerB.printBalance(accountC);
            customerB.requestJointAccountDeletion(accountC, customerA);
            BankSystem.getBank().printBankSystemInfo();
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }

    }
}
