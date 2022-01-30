/**
 * Composite is a structural design pattern that lets you compose objects into tree structures and then work with these
 * structures as if they were individual objects.
 *
 * Compose objects into tree structures to represent whole-part hierarchies. Composite lets clients treat individual
 * objects and compositions of objects uniformly.
 *
 * Recursive composition.
 *
 * "Directories contain entries, each of which could be a directory."
 *
 * https://youtu.be/EWDmWbJ4wRA
 * https://youtu.be/AIyTWtOqrfs
 * https://refactoring.guru/design-patterns/composite
 */

package Structural;

import java.util.ArrayList;
import java.util.List;

// Component
interface Account {
    float getBalance();
}

// Leaf
class SavingsAccount implements Account {
    private String accountNo;
    private float accountBalance;

    public SavingsAccount(String accountNo, float accountBalance) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public float getBalance() {
        return accountBalance;
    }
}

// Leaf (One leaf is enough for example)
class DepositAccount implements Account {
    private String accountNo;
    private float accountBalance;

    public DepositAccount(String accountNo, float accountBalance) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public float getBalance() {
        return accountBalance;
    }

}

// Composite
// Can contain leaf or another composite
class CompositeAccount implements Account {
    private float totalBalance;
    private List<Account> accountList = new ArrayList<>();

    public float getBalance() {
        totalBalance = 0;
        for (Account f : accountList) {
            totalBalance = totalBalance + f.getBalance();
        }
        return totalBalance;
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void removeAccount(Account account) {
        accountList.remove(account);
    }
}

public class CompositeDemo {

    public static void main(String[] args) {
        CompositeAccount component = new CompositeAccount();

        component.addAccount(new DepositAccount("DA001", 100));
        component.addAccount(new DepositAccount("DA002", 150));
        component.addAccount(new SavingsAccount("SA001", 200));

        float totalBalance = component.getBalance();
        System.out.println("Total Balance : " + totalBalance);
    }

}