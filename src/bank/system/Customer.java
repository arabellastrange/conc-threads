package bank.system;


public class Customer {
    private String name;
    private Employee employee;

    public Customer(String customerName, Employee employee) {
        name = customerName;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void requestNewAccount(Account a) {
        employee.createAccount(this, a);
    }

    public void requestAccountDeletion(int accNumber) {
        employee.deleteAcc(this, accNumber);
    }

    public void requestOverdraft(UnlimitedAccounts a, double amount) {
        if (verifyAccount(a)) {
            employee.grantOverdraft(a, amount);
        }
    }
    public void savingEnoughMoney(SavingAccount a) throws InterruptedException {
        if (verifyAccount(a)) {
            employee.makeSavingsAccount(a);
        }
    }

    public void printBalance(Account a) {
        if (verifyAccount(a)) {
            a.printBalance();
        }
    }

    public void deposit(Account a, double amount) {
        if (verifyAccount(a)) {
            a.deposit(amount);
        }
    }

    public void withdraw(Account a, double amount) throws InterruptedException {
        if (verifyAccount(a)) {
            a.withdraw(amount);
        }
    }

    public void transfer(Account a, double amount, int AccNo) throws InterruptedException {
        if (verifyAccount(a)) {
            a.transfer(amount, AccNo);
        }
    }

    public void requestJointAccount(Account a, Customer secondary) {
        if (verifyAccount(a)) {
            employee.makeJoint(this, secondary, a);
        }
    }

    public void requestNoLongerJoint(Account a, Customer secondary) {
        if (verifyAccount(a)) {
            employee.makeUnjoint(secondary, a);
        }
    }

    public void requestJointAccountDeletion(Account a, Customer secondary) {
        if (verifyAccount(a)) {
            employee.deleteJointAcc(a, this, secondary);
        }

    }

    public boolean verifyAccount(Account a) {
        if (BankSystem.getBank().getCustomerAccounts(this).contains(a)) {
            return true;
        }
        System.out.println("Customer: " + this.toString() + " cannot access this account");
        return false;
    }

    @Override
    public String toString() {
        return "{Name: " + name + ", Contact: " + employee + "}";
    }
}