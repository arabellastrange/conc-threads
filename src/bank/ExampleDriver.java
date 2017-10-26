package bank;

public class ExampleDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Account as;

    public static void main(String[] args){
        //set up bank: accounts and customers all created
        BankSystem.getBank().tellMeAboutBank();
        c = new Customer("Jean", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        a = new CurrentAccount(400, 0.01, 1);
        c.requestNewAccount(a);
        BankSystem.getBank().tellMeAboutBank();
        as = new CurrentAccount(500, 0.01, 0.5);
        c.requestNewAccount(as);
        BankSystem.getBank().tellMeAboutBank();

        (new Thread(new ExampleDriver())).start();
    }

    @Override
    public void run() {
        // preform your actions: deposit, transfer and withdraw
        c.printBalance(a);
        c.deposit(a,10);
        c.printBalance(a);
    }
}
