package bank.system;


public class Customer {
    private String  name;
    private Employee contact;

    public Customer(String cName, Employee conc){
        name = cName;
        contact = conc;
    }

    public String getName(){
        return name;
    }

    public void requestAccountDeletion(int accNumber){
        contact.deleteAcc(this, accNumber);
    }

    public void requestNewAccount(Account a){
        contact.createAcc(this, a);
    }

    public void requestOverdraft(UnlimitedAccounts a, double amount){
        if(verifyAccount(a)){
            contact.grantOverdraft(a, amount);
        }
    }

    public void printBalance(Account a){
        if(verifyAccount(a)){
            a.printBal();
        }
    }

    public void deposit(Account a, double amount){
        if(verifyAccount(a)){
            a.deposit(amount);
        }
    }

    public void withdraw(Account a, double amount) throws InterruptedException {
        if(verifyAccount(a)){
            a.withdraw(amount);
        }
    }

    public void transfer(Account a, double amount, int AccNo) throws InterruptedException {
        if(verifyAccount(a)){
            a.transfer(amount, AccNo);
        }
    }

    public void requestJointAccount(Account a, Customer secondary ){
        if(verifyAccount(a)){
            contact.makeJoint(this, secondary, a);
        }
    }

    public void requestNoLongerJoint(Account a, Customer secondary){
        if(verifyAccount(a)){
            contact.makeUnjoint( secondary, a);
        }
    }

    public void requestJointAccountDeletion(Account a, Customer secondary){
        if(verifyAccount(a)){
            contact.deleteJointAcc(a, this, secondary);
        }

    }

    public boolean verifyAccount(Account a){
        if(BankSystem.getBank().getCustomerAccounts(this).contains(a)){
            return true;
        }
        System.out.println("Customer: " + this.toString() + " cannot access this account");
        return false;
    }

    @Override
    public String toString(){
        return "{Name: " + name + ", Contact: " + contact + "}";
    }
}

