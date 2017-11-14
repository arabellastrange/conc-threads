package bank;

import bank.system.*;

public class OverdraftDriver implements Runnable {
    private static Customer c;
    private static Account a;

    public static void main(String[] args){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();

        a = new CurrentAccount(400);
        c.requestNewAccount(a);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new OverdraftDriver());
        Thread t1 = new Thread(new OverdraftDriver());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        c.printBalance(a);
        c.deposit(a,10);
        c.printBalance(a);
        c.requestOverdraft((UnlimitedAccounts) a, 800);
        try {
            c.withdraw(a,1000);
            c.printBalance(a);
            c.deposit(a,100);
            c.withdraw(a, 300);
            c.printBalance(a);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
    }
}
