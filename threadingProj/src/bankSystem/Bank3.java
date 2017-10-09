package bankSystem;

public class Bank3 implements AccountsI {

    private double balance;
    private int accNumber;

    public Bank3(int acc, double bal){
        accNumber = acc;
        balance = bal;
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
        return balance;
    }

    @Override
    public void printBal() {

    }

    @Override
    public void createAcc() {

    }

    @Override
    public void deleteAcc() {

    }

    @Override
    public void editAcc() {

    }
}
