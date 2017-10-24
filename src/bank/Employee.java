package bank;
public class Employee {
    BankSystem workingAt;
    int employeeID;
    int genID = 0;

    public Employee(){
        employeeID = genID++;
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

    public void depositInterest(Account account){
        account.deposit(account.checkBal() * account.getInterestRate() * account.getInterestLength());
    }

    public void chargeFee(PlatinumAccount account){
        account.withdraw(account.getAccountFee());
    }

    @Override
    public String toString() {
        return employeeID + "";
    }
}
