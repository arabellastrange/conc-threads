package bank;

public class SavingAccount extends Account {
    double upperLimit;
    
    public SavingAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0);
        setInterestLength(0);
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
