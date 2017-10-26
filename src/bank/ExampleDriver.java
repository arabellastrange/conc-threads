package bank;

public class ExampleDriver implements Runnable{

    public static void main(String[] args){
        (new Thread(new ExampleDriver())).start();
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
        c.printBalance(a);
        c.deposit(a,10);
        c.printBalance(a);
        Account as = new CurrentAccount(500, 0.01, 0.5);
        c.requestNewAccount(as);
        bank.getBank().tellMeAboutBank();

    }
}
