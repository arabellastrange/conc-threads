package bankSystem;

public interface AccountsI {
    public void deposit(double dep);
    public boolean withdraw(double amount);
    public void transfer(); //takes in two account numbers?
    public double checkBal();
    public void printBal();

    // not sure should be in this class maybe in the customer class?
    public void createAcc();
    public void deleteAcc();
    public void editAcc();
}
