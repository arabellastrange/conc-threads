package bank.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public final class BankSystem {

    private static BankSystem bank = null;
    private ConcurrentMap<Customer, ArrayList<Account>> customers = new ConcurrentHashMap<>();
    private static List<Employee> employees = new ArrayList<>();

    private BankSystem() {

    }

    // not thread safe
    public static BankSystem getBank() {
        if (bank == null) {
            Employee emp = new Employee();
            employees.add(emp);
            bank = new BankSystem();
        }

        return bank;
    }

    public void tellMeAboutBank() {
        if (!customers.entrySet().isEmpty()) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is checking the bank");
            System.out.println("\t This bank has the following customers");

            for (ConcurrentHashMap.Entry c : customers.entrySet()) {
                System.out.println("\t \t Customer: " + c.getKey().toString() + " their accounts are " + c.getValue().toString());
            }

            System.out.println("This bank has the following employees");
            for (Employee e : employees) {
                System.out.println("\t \t Employee: " + e.toString());
            }

        } else {
            System.out.println("\t Nothing in bank yet!");
        }
    }

    public void addAccount(Customer c, Account a) {
        if (!containsCustomer(c)) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is adding an account\n" + "\t No such customer exists");
            return;
        }

        if (!customers.get(c).contains(a)) {
            customers.get(c).add(a);
            System.out.println("Thread " + Thread.currentThread().getId() + " is adding an account\n" + "\t Success!");
        }

        System.out.println("Thread " + Thread.currentThread().getId() + " is adding an account\n" + "\t Account already exists");

    }

    public void removeAccount(Customer c, Account a) {
        if (!containsCustomer(c)) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account \n" + "\t No such customer exists");
            return;
        }


        if (customers.get(c).contains(a)) {

            customers.get(c).remove(a);

            System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account\n" + "\t Success!");
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account \n" + "\t No such account exists");
        }

    }

    public void removeJointAccount(Account a, Customer customer, Customer secondary) {
        if (containsCustomer(customer) && containsCustomer(secondary)) {
            if (customers.get(customer).contains(a) && customers.get(secondary).contains(a)) {
                customers.get(customer).remove(a);
                customers.get(secondary).remove(a);
                System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account\n" + "\t Success!");
            } else {
                System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account \n" + "\t No such account exists in one or both customer profiles");
            }
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + " is removing an account \n" + "\t One or both customers do not exist");
        }
    }

    public void addCustomer(Customer c) {
        if (!containsCustomer(c)) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is adding a customer \n" + "\t Success!");
            customers.put(c, new ArrayList<Account>());
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + " is adding a customer \n" + "\t Customer already in bank!");
        }
    }

    public void removeCustomer(Customer c) {
        if (containsCustomer(c)) {
            customers.remove(c);
            System.out.println("Thread " + Thread.currentThread().getId() + " is removing a customer \n" + "\t Success!");
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + " is removing a customer \n" + "\t No such customer");
        }
    }

    public void hireEmployee(Employee e) {
        employees.add(e);
    }

    public void fireEmployee(Employee e) {
        employees.remove(e);
    }

    public ArrayList<Account> getCustomerAccounts(Customer c) {
        return customers.get(c);
    }

    public Account getAccount(int AccNo) {
        for (ArrayList<Account> as : customers.values()) {
            for (Account a : as) {
                if (a.getAccountNumber() == AccNo) {
                    return a;
                }
            }
        }
        return null;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public Boolean verifyEmployee(Employee e) {
        return employees.contains(e);
    }

    public boolean containsCustomer(Customer c) {
        return customers.containsKey(c);
    }


}
