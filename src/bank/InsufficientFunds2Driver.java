package bank;

import bank.system.*;

public class InsufficientFunds2Driver implements Runnable{

    private static Customer c;
    private static Account a;
    private static Account n;
    private static Account p;

    public static void main(String[] args){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();


        a = new CurrentAccount(400);
        p = new PlatinumAccount(5000);
        n =  new CurrentAccount(800);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        c.requestNewAccount(n);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new InsufficientFunds2Driver());
        Thread t1 = new Thread(new InsufficientFunds2Driver());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        c.printBalance(a);
        try {
            Thread.sleep(100);
            c.withdraw(a, 200);
            c.printBalance(a);
            c.withdraw(a, 200);
            c.printBalance(a);
            c.deposit(a, 300);
            c.printBalance(a);
            c.withdraw(a, 200);
            c.printBalance(a);
            c.deposit(a, 300);
            c.printBalance(a);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }

    }
}
