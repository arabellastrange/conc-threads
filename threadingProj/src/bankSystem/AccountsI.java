package bankSystem;

public interface AccountsI {
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer(double amount);
    public double checkBal();
    public void printBal();

}
