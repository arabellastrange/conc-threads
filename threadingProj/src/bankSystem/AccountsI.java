package bankSystem;

public interface AccountsI {
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer();
    public double checkBal();
    public void printBal();
}
