package bank;

public class MakeJointAccountDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Account p;
    private static Account as;
    private static Customer x;

    public static void main(String args[]){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean",  BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();

        a = new CurrentAccount(400);
        p = new PlatinumAccount(5000);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        BankSystem.getBank().tellMeAboutBank();

        x = new Customer("Ororo",  BankSystem.getBank().getEmployee(0));
        as = new SavingAccount(600);
        BankSystem.getBank().addCustomer(x);
        x.requestNewAccount(as);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new MakeJointAccountDriver());
        Thread t1 = new Thread(new MakeJointAccountDriver());
        t0.start();
        t1.start();

    }

    @Override
    public void run() {
        x.requestJointAccount(as, c);
        BankSystem.getBank().tellMeAboutBank();
        c.printBalance(as);
        c.deposit(as, 20);
        c.printBalance(as);
        try {
            x.withdraw(as, 10);
            x.printBalance(as);
        } catch (InterruptedException e) {
            System.out.println("Balance too low, never replenished, can't wait anymore");
        }

    }
}
