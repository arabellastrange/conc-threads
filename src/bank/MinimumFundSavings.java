package bank;

import bank.system.*;

public class MinimumFundSavings implements Runnable {
    private static Customer customer;
    private static SavingAccount accountA;
    private static SavingAccount accountB;
    private static Employee employeeA;
    private static Employee employeeB;

    public static void main(String[] args) {

        BankSystem.getBank().printBankSystemInfo();
        customer = new Customer("Alex", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);

        accountA = new SavingAccount(20);
        customer.requestNewAccount(accountA);
        BankSystem.getBank().printBankSystemInfo();

        accountB = new SavingAccount(19);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        employeeA = new Employee();
        employeeB = new Employee();
        BankSystem.getBank().hireEmployee(employeeA);
        BankSystem.getBank().hireEmployee(employeeB);


        Thread threadA = new Thread(new MinimumFundSavings());
        Thread threadB = new Thread(new MinimumFundSavings());
        threadA.start();
        threadB.start();
    }

    @Override
    public void run() {
        System.out.println("********************");
        try {
            Thread.sleep(100);
            customer.savingEnoughMoney(accountA);
            Thread.sleep(100);
            customer.savingEnoughMoney(accountB);
        } catch (InterruptedException e) {
            System.out.println();
        }
    }
}