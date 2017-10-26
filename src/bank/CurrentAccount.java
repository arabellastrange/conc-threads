package bank;


public class CurrentAccount extends Account {
    double overdraft;
    boolean hasOverdraft = false;


    public CurrentAccount(double initialBalance, double interestRt, double interestLn){
        super(initialBalance, interestRt, interestLn);
    }

    @Override
    public boolean deposit(double dep) {
        setBalance(checkBal() + dep);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (hasOverdraft = true) {
            if(checkBal() - overdraft <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        } else {
            if (checkBal() <= 0) {
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }
        setBalance(checkBal() - amount);
        return true;
    }

    public void setOverdraft(double overdraft) {
       this.overdraft = overdraft;
        hasOverdraft = true;

    }

}
