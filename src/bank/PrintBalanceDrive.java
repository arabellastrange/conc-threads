package bank;

public class PrintBalanceDrive implements Runnable {
    private static Customer c;
    private static Account a;
    private static Account p;
    private static Customer x;

    public static void main(String[] args){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();

        a = new CurrentAccount(400, 0.01, 1);
        p = new PlatinumAccount(5000, 0.2, 0.25, 70, 1);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
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
    }
}
