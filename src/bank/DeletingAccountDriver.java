package bank;

public class DeletingAccountDriver implements Runnable{
    public static void main(String[] args){
        (new Thread(new DeletingAccountDriver())).start();
    }

    public void run(){
        Customer c = new Customer("Mary", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        Account a = new CurrentAccount(380, 0.02, 1);
        BankSystem.getBank().addAccount(c,a);
        BankSystem.getBank().tellMeAboutBank();
        BankSystem.getBank().removeAccount(c,a);
        BankSystem.getBank().tellMeAboutBank();



    }


}