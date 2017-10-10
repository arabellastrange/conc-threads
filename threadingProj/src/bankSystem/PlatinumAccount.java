package bankSystem;

public class PlatinumAccount implements AccountsI{
    int accountNumber;
    int sortCode;
    double balance;
    double overdraft;
    double interestRate;
    boolean hasOverdraft = false;

    public PlatinumAccount(int accNum, int sort, double intRate){
        accountNumber = accNum;
        sortCode = sort;
        interestRate = intRate;
    }

    @Override
    public void deposit(double dep) {
        balance += dep;
    }

    @Override
    public boolean withdraw(double amount) {
        if(hasOverdraft){
            if(balance - overdraft <= 0){
                return false;
            }
        }
        else{
            if(balance <= 0){
                return false;
            }
        }
        balance -= amount;
        return true;

    }

    //takes in an account number gets an account by that number makes a deposit to it and withdraws an equal amount from this.acc
    @Override
    public void transfer(double amount) {

    }

    @Override
    public double checkBal() {
        return balance;
    }

    @Override
    public void printBal() {
        System.out.println(balance);
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public int getAccountSort() {
        return sortCode;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
        hasOverdraft = true;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return interestRate;
    }
}