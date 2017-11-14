package bank;

import bank.system.*;

public class PrintBalanceDrive implements Runnable {
    private static Customer c;
    private static Account a;
    private static Account p;
    private static Account s;
    private static Customer x;

    public static void main(String[] args){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();

        a = new CurrentAccount(400);
        p = new PlatinumAccount(5000);
        s = new SavingAccount(1000);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        c.requestNewAccount(s);
        BankSystem.getBank().tellMeAboutBank();

        x = new Customer("Ororo",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(x);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new PrintBalanceDrive());
        Thread t1 = new Thread(new PrintBalanceDrive());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        c.printBalance(p);
        c.printBalance(a);
        c.printBalance(s);
    }
}
