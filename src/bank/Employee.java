package bank;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private BankSystem workingAt;
    private int employeeID;
    private static final AtomicInteger genID = new AtomicInteger();  // concurrent and thread safe data type

    public Employee(){
        employeeID = genID.incrementAndGet();
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void createAcc(Customer cust, Account a){
        workingAt.getBank().addAccount(cust, a);
    }

    public void deleteAcc(Customer c, int accNum){
        workingAt.getBank().removeAccount(c, workingAt.getBank().getAccount(accNum));
    }

    public void makeJoint(Customer c, Customer secondary, Account a){
        if(!BankSystem.getBank().containsCustomer(secondary)){
            BankSystem.getBank().addCustomer(secondary);
        }

        BankSystem.getBank().addAccount(secondary, a);
    }

    public void depositInterest(Account account){
        account.deposit(account.checkBal() * account.getInterestRate() * account.getInterestLength());
    }

    public void chargeFee(PlatinumAccount account) throws InterruptedException {
        account.withdraw(account.getAccountFee());
    }

    public void grantOverdraft(UnlimitedAccounts a, double amount){
            a.verifyOverdraft(amount);
    }

    @Override
    public String toString() {
        return "ID: " + employeeID + "";
    }
}
