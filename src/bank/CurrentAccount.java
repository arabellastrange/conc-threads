package bank;


public class CurrentAccount extends Account {
    double overdraft;
    boolean hasOverdraft = false;


    public CurrentAccount(double intialBalance, double interestRt, double intrestLn){
        super(intialBalance, interestRt, intrestLn);


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
                return false;
            }
        } else {
            if (checkBal() <= 0) {
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
