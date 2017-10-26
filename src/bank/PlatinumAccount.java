package bank;

public class PlatinumAccount extends Account{
    private double overdraft;
    private double fee;
    private boolean hasOverdraft = false;

    public PlatinumAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.2);
        setInterestLength(0.25);
        fee = 75;
    }

    @Override
    public boolean deposit(double dep) {
        System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to deposit");
        balanceLock.lock();
        try {
            setBalance(checkBal() + dep);
            System.out.println("Deposit successful, deposited: £" + dep);
            return true;
        }
        finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw");
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
        balanceLock.lock();
        try {
            setBalance(checkBal() - amount);
            System.out.println("Withdrawal Successful, withdrew: £" + amount);
            return true;
        }
        finally {
            balanceLock.unlock();
        }
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
