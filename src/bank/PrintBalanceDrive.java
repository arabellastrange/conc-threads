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
        BankSystem bank = BankSystem.getBank();
        bank.getBank().tellMeAboutBank();

        Customer c = new Customer("Jean", bank.getBank().getEmployee(0));
        bank.getBank().addCustomer(c);
        Account a = new CurrentAccount(400, 0.01, 1);
        bank.getBank().tellMeAboutBank();

        c.requestNewAccount(a);
        a.printBal();
        Account p = new PlatinumAccount(5000, 0.2, 0.25, 70, 1);
        c.requestNewAccount(p);
        p.printBal();

        bank.getBank().tellMeAboutBank();

        Customer x = new Customer("Ororo", bank.getBank().getEmployee(0));
        bank.getBank().addCustomer(x);
        bank.getBank().tellMeAboutBank();
    }
}
