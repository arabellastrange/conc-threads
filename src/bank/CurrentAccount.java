package bank;

public class CurrentAccount implements AccountsI {

    private double balance;
    private int accNumber;
    private String customerName;
    private double openingBal;

    public CurrentAccount(int acc, double bal, double openingBal){
        accNumber = acc;
        balance = bal;
        openingBal = 0;

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
    public void transfer(double amount) {
        if(balance >= amount) {
            balance = balance - amount;

        }
    }

    @Override
    public double checkBal() {
        return balance;
    }

    @Override
    public void printBal() {
    System.out.print("Account number " + accNumber + " has the balance of " + balance);
    }

    @Override
    public int getAccountNumber() {
        return 0;
    }

    @Override
    public int getAccountSort() {
        return 0;
    }

    @Override
    public void setInterestRate(double interestRate) {

    }

    @Override
    public double getInterestRate() {
        return 0;
    }


}
