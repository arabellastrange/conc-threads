package bank;

public class SavingAccount implements AccountsI {
    @Override
    public void deposit(double dep) {

    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public void transfer(double amount) {

    }

    @Override
    public double checkBal() {
        return 0;
    }

    @Override
    public void printBal() {

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
