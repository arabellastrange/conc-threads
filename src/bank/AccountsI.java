package bank;

public interface AccountsI {
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer(double amount);

    void transfer();

    public double checkBal();
    public void printBal();

}
