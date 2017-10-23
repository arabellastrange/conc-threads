package bank;


public class Customer {
    String  name;
    Employee contact;

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
}


