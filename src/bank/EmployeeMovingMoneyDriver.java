package bank;

import bank.system.*;

public class EmployeeMovingMoneyDriver implements Runnable {
    private static Customer customer;
    private static Employee EmployeeA;
    private static Employee employeeB;
    private static Account accountA;
    private static Account accountB;


    public static void main(String[] args) {
        customer = new Customer("Rachel Grey", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(customer);
        accountA = new CurrentAccount(380);
        accountB = new PlatinumAccount(4500);
        customer.requestNewAccount(accountA);
        customer.requestNewAccount(accountB);
        BankSystem.getBank().printBankSystemInfo();

        EmployeeA = new Employee();
        employeeB = new Employee();
        BankSystem.getBank().hireEmployee(EmployeeA);
        BankSystem.getBank().hireEmployee(employeeB);

        Thread threadA = new Thread(new EmployeeMovingMoneyDriver());
        Thread threadB = new Thread(new EmployeeMovingMoneyDriver());
        threadA.start();
        threadB.start();
    }

    @Override
    public void run() {
        customer.printBalance(accountA);
        EmployeeA.depositInterest(accountA);
        customer.printBalance(accountA);
        employeeB.depositInterest(accountA);
        customer.printBalance(accountA);
        customer.printBalance(accountB);
        try {
            EmployeeA.chargeFee((PlatinumAccount) accountB);
            customer.printBalance(accountB);
            employeeB.grantOverdraft((UnlimitedAccounts) accountA, 800);
            customer.printBalance(accountA);
        } catch (InterruptedException e2) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
    }
}
