package bank;


public class CurrentAccount extends Account {
    double overdraft;
    boolean hasOverdraft = false;


    public CurrentAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.01);
        setInterestLength(1);
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
        if(overdraft <= 1000){
            this.overdraft = overdraft;
            hasOverdraft = true;
        }
        else{
            System.out.println("You cannot set an overdraft of over £1000 with a current account, you may instead use a platinum account");
        }
    }

}
