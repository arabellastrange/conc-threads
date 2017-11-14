package bank;

import bank.system.*;

public class DeletingAccountDriver implements Runnable {
    private static Customer customer;
    private static Employee employeeA;
    private static Employee employeeB;
    private static Account accountA;
    private static Account accountB;

    public static void main(String[] args) {
        customer = new Customer("Mary", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        accountA = new CurrentAccount(380);
        accountB = new SavingAccount(450);
        customer.requestNewAccount(accountA);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        employeeA = new Employee();
        employeeB = new Employee();
        BankSystem.getBank().hireEmployee(employeeA);
        BankSystem.getBank().hireEmployee(employeeB);

        Thread threadA = new Thread(new DeletingAccountDriver());
        Thread threadB = new Thread(new DeletingAccountDriver());

        threadA.start();
        threadB.start();
    }

    public void run() {
        employeeA.deleteAcc(customer, accountA.getAccountNumber());
        BankSystem.getBank().printBankSystemInfo();

        employeeB.deleteAcc(customer, accountB.getAccountNumber());
    }

}