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
        as = new CurrentAccount(600);
        BankSystem.getBank().addCustomer(x);
        BankSystem.getBank().tellMeAboutBank();

    }

    @Override
    public void run() {

    }
}
