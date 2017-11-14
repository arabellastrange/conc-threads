package bank;

import bank.system.*;

public class EmployeeMovingMoneyDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Employee e;
    private static Employee e1;
    private static Account p;

    public static void main(String[] args){
        c = new Customer("Rachel Grey" , BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        a = new CurrentAccount(380);
        p = new PlatinumAccount(4500);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        BankSystem.getBank().tellMeAboutBank();

        e = new Employee();
        e1 = new Employee();
        BankSystem.getBank().hireEmployee(e);
        BankSystem.getBank().hireEmployee(e1);

        Thread t = new Thread(new EmployeeMovingMoneyDriver());
        Thread t1 = new Thread(new EmployeeMovingMoneyDriver());
        t.start();
        t1.start();
    }

    @Override
    public void run() {
        c.printBalance(a);
        e.depositInterest(a);
        c.printBalance(a);
        e1.depositInterest(a);
        c.printBalance(a);
        c.printBalance(p);
        try {
            e.chargeFee((PlatinumAccount) p);
            c.printBalance(p);
            e1.grantOverdraft((UnlimitedAccounts) a,800);
            c.printBalance(a);
        } catch (InterruptedException e2) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
    }
}
