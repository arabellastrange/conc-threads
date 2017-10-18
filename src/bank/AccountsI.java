package bank;

public interface AccountsI { // change to inheritance to avoid code repeat?
    //BETTER ACCOUNT NUMBER AND SORT CODE CREATION

    //move money - write operations
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer(double amount);
    // needs to take in an account number to transfer to as well
    //public void transfer(int toAccountNum, double amount);

    //balance info - read operations
    public double checkBal();
    public void printBal();

    //account info getters and setters
    public int getAccountNumber();
    public int getAccountSort();
    public void setInterestRate(double interestRate);
    public double getInterestRate();
}