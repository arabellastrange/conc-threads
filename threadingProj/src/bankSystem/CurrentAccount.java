package bankSystem;

public class CurrentAccount implements AccountsI {

    private double balance;
    private int accNumber;
    private String customerName;
    private double openingBal;
    private int numAccounts;

    public CurrentAccount(int acc, double bal){
        accNumber = acc;
        balance = bal;
        numAccounts = 0;
    }

    public int getNumAccounts(){
        return numAccounts;
    }

    @Override
    public void deposit(double dep) {
        balance = balance + dep;
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance >= amount) {
            balance = balance - amount;
            return true;
        }
        else
            return false;
    }

    @Override
    public void transfer() {

    }

    @Override
    public double checkBal() {
        return 0;
    }

    @Override
    public void printBal() {

    }


}
