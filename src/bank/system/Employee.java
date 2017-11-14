package bank.system;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private BankSystem workingAt;
    private int employeeID;
    private static final AtomicInteger genID = new AtomicInteger();  // concurrent and thread safe data type

    public Employee() {
        employeeID = genID.incrementAndGet();
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void createAccount(Customer cust, Account a) {
        if (verifyEmployee(this)) {
            workingAt.getBank().addAccount(cust, a);
        }
    }

    public void deleteAcc(Customer c, int accNum) {
        if (verifyEmployee(this)) {
            workingAt.getBank().removeAccount(c, workingAt.getBank().getAccount(accNum));
        }
    }

    public void makeJoint(Customer c, Customer secondary, Account a) {
        if (verifyEmployee(this)) {
            if (!BankSystem.getBank().containsCustomer(secondary)) {
                BankSystem.getBank().addCustomer(secondary);
            }

            BankSystem.getBank().addAccount(secondary, a);
        }
    }

    public void makeUnjoint(Customer secondary, Account a) {
        if (verifyEmployee(this)) {
            BankSystem.getBank().removeAccount(secondary, a);
        }
    }

    public void deleteJointAcc(Account a, Customer customer, Customer secondary) {
        if (verifyEmployee(this)) {
            BankSystem.getBank().removeJointAccount(a, customer, secondary);
        }
    }

    public void depositInterest(Account account) {
        if (verifyEmployee(this)) {
            account.deposit(account.checkBal() * account.getInterestRate() * account.getInterestLength());
        }
    }

    public void chargeFee(PlatinumAccount account) throws InterruptedException {
        if (verifyEmployee(this)) {
            account.withdraw(account.getAccountFee());
        }
    }

    public void grantOverdraft(UnlimitedAccounts a, double amount) {
        if (verifyEmployee(this)) {
            a.verifyOverdraft(amount);
        }
    }

    @Override
    public String toString() {
        return "ID: " + employeeID + "";
    }

    public Boolean verifyEmployee(Employee e) {
        if (BankSystem.getBank().verifyEmployee(e)) {
            return true;
        } else {
            System.out.println("This employee does not have permission to complete this action");
            return false;
        }
    }
}
