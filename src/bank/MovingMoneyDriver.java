package bank;

public class MovingMoneyDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Account p;
    private static Account l;
    private static Customer x;
    private static Customer v;

    public static void main(String args[]){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();

        a = new CurrentAccount(400);
        c.requestNewAccount(a);
        BankSystem.getBank().tellMeAboutBank();

        x = new Customer("Ororo",  BankSystem.getBank().getEmployee(0));
        p = new PlatinumAccount(5000);
        BankSystem.getBank().addCustomer(x);
        x.requestNewAccount(p);
        BankSystem.getBank().tellMeAboutBank();

        v = new Customer("Kurt", BankSystem.getBank().getEmployee(0));
        l = new CurrentAccount(20);
        BankSystem.getBank().addCustomer(v);
        v.requestNewAccount(l);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new MovingMoneyDriver());
        Thread t1 = new Thread(new MovingMoneyDriver());
        t0.start();
        t1.start();

    }

    @Override
    public void run() {
        c.printBalance(a);
        c.deposit(a, 300);
        c.printBalance(a);
        try {
            x.printBalance(p);
            x.transfer(p, 200, a.getAccountNumber());
            x.printBalance(p);
            c.transfer(a, 100, l.getAccountNumber());
            c.printBalance(a);
            v.printBalance(l);
            v.withdraw(l, 10);
            v.printBalance(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //deposit transfer pay interest and withdraw with overdraft
    }

}
