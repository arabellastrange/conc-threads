package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public final class BankSystem {
    private static BankSystem bank = null;
    private Map<Customer, ArrayList<Account>> cusomters = new HashMap<Customer, ArrayList<Account>>();
    private static ArrayList<Employee> employees = new ArrayList<>();

    private BankSystem(){

    }

    // not thread safe
    public static BankSystem getBank(){
        if (bank == null){
            Employee emp =  new Employee();
            employees.add(emp);
            bank = new BankSystem();
        }
        return bank;
    }

    public void tellMeAboutBank(){
        if(!cusomters.entrySet().isEmpty()) {
            System.out.println("This bank has the following customers");
            for (Map.Entry c : cusomters.entrySet()) {
                System.out.println("Cusomter: " + c.getKey().toString() + " their accounts are " + c.getValue().toString());
            }
        }
        else {
            System.out.println("Nothing in bank yet!");
        }
    }

    public void addAccount(Customer c, Account a){
       if(cusomters.containsKey(c)) {
           cusomters.get(c).add(a);
       }
       else{
           System.out.println("No such customer exists");
       }
    }

    public void removeAccount(Customer c, Account a){
        if(cusomters.containsKey(c)) {
            cusomters.get(c).remove(a);
        }
        else{
            System.out.println("No such customer exists");
        }
    }

    public void addCustomer(Customer c){
        if(!containsCustomer(c)){
            cusomters.put(c, new ArrayList<Account>());
        }
        else {
            System.out.println("Customer already in bank!");
        }
    }

    public void removesCustomer(Customer c){
       cusomters.remove(c);
    }

    public void hireEmployee(Employee e){
        employees.add(e);
    }

    public void fireEmployee(Employee e){
        employees.remove(e);
    }

   public ArrayList<Account> getCustomerAccounts(Customer c){
        return cusomters.get(c);
   }

   public Account getAccount(int AccNo){
       for(ArrayList<Account> as : cusomters.values()){
           for(Account a : as){
               if(a.getAccountNumber() == AccNo){
                   return a;
               }
           }
       }
       return null;
   }

    public Employee getEmployee(int id){
        return employees.get(id);
    }

    public boolean containsCustomer(Customer c){
        if(cusomters.containsKey(c)){
            return true;
        }
        return false;
    }
}
