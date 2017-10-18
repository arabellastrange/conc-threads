package bank;

import java.util.ArrayList;

public class Customer {
    String  name;
    String address;
    // date of birth date or int object
    ArrayList<AccountsI> myAccounts = new ArrayList<>();

    public Customer(String cName, String cAddress){
        name = cName;
        address = cAddress;
    }

    //needs to be more detailed - must edit each field not just account in general
    public void editAcc(){

    }

    public String getName(){
        return name;
    }

    public ArrayList<AccountsI> getMyAccounts() {
        return myAccounts;
    }

    public void requestAccountDeletion(Employee contact, int accNumber){
        contact.deleteAcc(accNumber);
    }

    public void requestOpenAccount( Employee contact,  String accountType){
        myAccounts.add(contact.createAcc(accountType));
    }
}


