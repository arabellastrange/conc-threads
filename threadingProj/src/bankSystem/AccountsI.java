package bankSystem;

public interface AccountsI {
    //move money - write operations
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer(double amount); // needs to take in an account number to transfer too as well

    //balance info - read operations
    public double checkBal();
    public void printBal();

    //account info getters and setters
    public int getAccountNumber();
    public int getAccountSort();
}
