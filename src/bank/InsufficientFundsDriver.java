package bank;

public class InsufficientFundsDriver implements Runnable {
    private static Customer c;
    private static Account a;
    private static Account p;

    public static void main(String[] args){
        BankSystem.getBank().tellMeAboutBank();

        c = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        BankSystem.getBank().tellMeAboutBank();


        a = new CurrentAccount(400);
        p = new PlatinumAccount(5000);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        BankSystem.getBank().tellMeAboutBank();

        Thread t0 = new Thread(new InsufficientFundsDriver());
        Thread t1 = new Thread(new InsufficientFundsDriver());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        c.printBalance(a);
        c.withdraw(a, 200);
        c.printBalance(a);
        c.withdraw(a, 200);
        c.printBalance(a);
        c.withdraw(a, 200); //here will need to add waiting for top up
    }
}
