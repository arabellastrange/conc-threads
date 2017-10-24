package bank;

public class DeletingAccountDriver {
    public static void main(String[] args){
        (new Thread(new ExampleDriver())).start();
    }

    public void run(){
        BankSystem bank = BankSystem.getBank();
        Customer c = new Customer("Mary", bank.getBank().getEmployee(0));
        bank.getBank().addCustomer(c);
        Account a = new CurrentAccount(380, 0.02, 1);
        bank.getBank().tellMeAboutBank();
        bank.removeAccount(c,a);



    }


}
