package bank;

public class DeletingAccountDriver implements Runnable{
    private static Customer c;
    private static Account a;
    public static void main(String[] args){

        c = new Customer("Mary", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        a = new CurrentAccount(380, 0.02, 1);
        c.requestNewAccount(a);
        BankSystem.getBank().tellMeAboutBank();

        (new Thread(new DeletingAccountDriver())).start();
    }

    public void run(){
        c.requestAccountDeletion(a.getAccountNumber());
        BankSystem.getBank().tellMeAboutBank();
    }


}