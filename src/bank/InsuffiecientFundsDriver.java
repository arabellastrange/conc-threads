package bank;

public class InsuffiecientFundsDriver implements Runnable {
    public static void main(String[] args){
        Thread t0 = new Thread(new InsuffiecientFundsDriver());
        Thread t1 = new Thread(new InsuffiecientFundsDriver());
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        BankSystem bank = BankSystem.getBank();
        bank.getBank().tellMeAboutBank();

        Customer c = new Customer("Jean", bank.getBank().getEmployee(0));
        bank.getBank().addCustomer(c);
        bank.getBank().tellMeAboutBank();


        Account a = new CurrentAccount(400, 0.01, 1);
        Account p = new PlatinumAccount(5000, 0.2, 0.25, 70, 1);
        c.requestNewAccount(a);
        c.requestNewAccount(p);
        bank.getBank().tellMeAboutBank();



    }
}
