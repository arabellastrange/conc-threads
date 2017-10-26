package bank;

public class SavingAccount extends Account {
    double upperLimit;
    
    public SavingAccount(double initialBalance){
        // must have minimum initial balance of £20
        super(initialBalance);
        setInterestRate(0);
        setInterestLength(0);
    }

    @Override
    public boolean deposit(double dep) {
        //should have upper limit aprox. 10,000!!
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
