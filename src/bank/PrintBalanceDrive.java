package bank;

public class PrintBalanceDrive implements Runnable {

    public static void main(String[] args){
        Thread t0 = new Thread(new PrintBalanceDrive());
        Thread t1 = new Thread(new PrintBalanceDrive());
        t0.start();
        //t1.start();
    }

    @Override
    public void run() {
        BankSystem.getBank().tellMeAboutBank();

        Customer c = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        Account a = new CurrentAccount(400, 0.01, 1);
        BankSystem.getBank().tellMeAboutBank();

        c.requestNewAccount(a);
        a.printBal();
        Account p = new PlatinumAccount(5000, 0.2, 0.25, 70, 1);
        c.requestNewAccount(p);
        p.printBal();

        BankSystem.getBank().tellMeAboutBank();

        Customer x = new Customer("Ororo",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(x);
        BankSystem.getBank().tellMeAboutBank();
    }
}
