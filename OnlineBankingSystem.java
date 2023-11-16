package Npackage;
import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add("Transfer to " + recipient.getAccountNumber() + ": -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber);
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class OnlineBankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account1 = new BankAccount("456789", "Nitin Rai");
        BankAccount account2 = new BankAccount("5678901","Nitin Rai");

        System.out.println("Welcome to the Online Banking System");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account1.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: $" + account1.getBalance());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = sc.nextDouble();
                    account1.withdraw(withdrawalAmount);
                    System.out.println("Withdrawal successful. New balance: $" + account1.getBalance());
                    break;
                case 3:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccountNumber = sc.next();
                    BankAccount recipientAccount = (recipientAccountNumber.equals(account2.getAccountNumber())) ? account2 : null;
                    if (recipientAccount != null) {
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = sc.nextDouble();
                        account1.transfer(recipientAccount, transferAmount);
                        System.out.println("Transfer successful. New balance: $" + account1.getBalance());
                    } else {
                        System.out.println("Recipient account not found!");
                    }
                    break;
                case 4:
                    System.out.println("Current balance: $" + account1.getBalance());
                    break;
                case 5:
                    account1.displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the Online Banking System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    sc.close();
            }
        }
    }
}
