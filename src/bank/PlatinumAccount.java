package bank;

public class PlatinumAccount extends Account{
    private double overdraft;
    private double fee;
    private double feeLength;
    private boolean hasOverdraft = false;

    public PlatinumAccount(double initialBalance, double interestRt, double interestLn, double accFee, double fLength){
        super(initialBalance, interestRt, interestLn);
        fee = accFee;
        feeLength = fLength;
    }

    @Override
    public boolean deposit(double dep) {
        setBalance(checkBal() + dep);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if(hasOverdraft){
            if(checkBal() - overdraft <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }
        else{
            if(checkBal() <= 0){
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

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public double getAccountFee(){
        return fee;
    }
}
