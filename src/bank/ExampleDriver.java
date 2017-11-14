package bank;

import bank.system.Account;
import bank.system.BankSystem;
import bank.system.CurrentAccount;
import bank.system.Customer;

public class ExampleDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Account as;

    public static void main(String[] args){
        //set up bank: accounts and customers all created
        BankSystem.getBank().tellMeAboutBank();
        c = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        a = new CurrentAccount(400);
        c.requestNewAccount(a);
        BankSystem.getBank().tellMeAboutBank();
        as = new CurrentAccount(500);
        c.requestNewAccount(as);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new ExampleDriver());
        Thread t1 = new Thread(new ExampleDriver());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        // preform your actions: deposit, transfer and withdraw
        c.printBalance(a);
        c.deposit(a,10);
        c.printBalance(a);
        c.printBalance(as);
        try {
            Thread.sleep(100);
            c.transfer(a, 20, as.getAccountNumber());
        }
        catch (InterruptedException e){
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }
        c.printBalance(a);
        c.printBalance(as);
    }
}
