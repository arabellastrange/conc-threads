package bank;

public class SavingAccount extends Account {
    double upperLimit;
    
    public SavingAccount(double initialBalance, double interestRt, double interestLen){
        super(initialBalance, interestRt, interestLen);
    }

    @Override
    public boolean deposit(double dep) {
        //should have upper limit!!
        setBalance(checkBal() + dep);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (checkBal() >= amount) {
            setBalance(checkBal() - amount);
            return true;
        } else
            System.out.println("Insufficient funds.");
        return false;
    }



}
