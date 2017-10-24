package bank;


public class CurrentAccount extends Account {
    double overdraft;
    boolean hasOverdraft = false;


    public CurrentAccount(double intialBalance, double interestRt, double intrestLn){
        super(intialBalance, interestRt, intrestLn);


    }

    @Override
    public boolean deposit(double dep) {
        balance = balance + dep;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (hasOverdraft = true) {
            if (balance - overdraft <= 0) {
                return false;
            }
        } else {
            if (balance <= 0) {
                return false;
            }
        }
        balance = balance - amount;
        return true;
    }

    public void setOverdraft(double overdraft) {
       this.overdraft = overdraft;
        hasOverdraft = true;

    }


}
