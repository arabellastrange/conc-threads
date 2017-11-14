package bank;

import bank.system.*;

public class DeletingAccountDriver implements Runnable{
    private static Customer c;
    private static Account a;
    private static Employee e;
    private static Employee e1;
    private static Account s;

    public static void main(String[] args){
        c = new Customer("Mary", BankSystem.getBank().getEmployee(0));
        BankSystem.getBank().addCustomer(c);
        a = new CurrentAccount(380);
        s = new SavingAccount(450);
        c.requestNewAccount(a);
        c.requestNewAccount(s);
        BankSystem.getBank().printBankSystemInfo();

        e = new Employee();
        e1 = new Employee();
        BankSystem.getBank().hireEmployee(e);
        BankSystem.getBank().hireEmployee(e1);

        Thread t = new Thread(new DeletingAccountDriver());
        Thread t1 = new Thread(new DeletingAccountDriver());
        t.start();
        t1.start();
    }

    public void run(){
        e.deleteAcc(c,a.getAccountNumber());
        BankSystem.getBank().printBankSystemInfo();

        e1.deleteAcc(c, s.getAccountNumber());
        BankSystem.getBank().printBankSystemInfo();

        e1.deleteAcc(c,a.getAccountNumber());
    }

}