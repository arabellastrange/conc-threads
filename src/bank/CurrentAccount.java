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
        if(overdraft <= 1000){
            this.overdraft = overdraft;
            hasOverdraft = true;
        }
        else{
            System.out.println("You cannot set an overdraft of over £1000 with a current account, you may instead use a platinum account");
        }
    }

}
