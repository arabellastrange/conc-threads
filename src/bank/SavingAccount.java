package bank;

public class SavingAccount extends Account {
    double upperLimit;
    
    public SavingAccount(double intialBalance, double interestRt, double interestLen){
        super(intialBalance, interestRt, interestLen);
    }

    @Override
    public boolean deposit(double dep) {
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

    @Override
    public boolean transfer(double amount, int AccNum) {
        if (amount <= checkBal()) {
            withdraw(amount);
            System.out.print("\nTransfer successful. Transferred: £" + amount);
        } else {
            System.out.print("\nTransfer failed, not enough balance!");
        }

        return true;
    }

}
