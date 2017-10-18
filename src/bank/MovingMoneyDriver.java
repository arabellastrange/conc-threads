package bank;

public class MovingMoneyDriver implements Runnable{
    //really messy in terms of staying up to date between employee and bank
    // sort out link between account and customer

    public static void main(String[] args){
        (new Thread(new MovingMoneyDriver())).start();
    }

    @Override
    public void run() {
        BankSystem bank = new BankSystem("testBank", "12 orchid blvd");
        Employee emp = new Employee(0, "grant", bank);
        Customer c = new Customer("Jean", "2331 friend fnd");
        bank.hireEmployee(emp);
        c.requestOpenAccount(emp, "Current");
        System.out.println(c.getMyAccounts().get(0).checkBal());
        c.getMyAccounts().get(0).deposit(10);
        System.out.println(c.getMyAccounts().get(0).checkBal());

    }
}
