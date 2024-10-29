import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

// Superclass: Account
abstract class Account {
    protected String accountHolder;
    protected double balance;

    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Abstract methods for subclasses to implement
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void displayBalance();
}

// Subclass: BankAccount
class BankAccount extends Account {
    private static final double BANK_CHARGE = 10.0;

    public BankAccount(String accountHolder, double initialBalance) {
        super(accountHolder, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited R%.2f. New balance: R%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            // Total amount includes bank charges
            double totalAmount = amount + BANK_CHARGE;
            if (balance >= totalAmount) {
                balance -= totalAmount;
                System.out.printf("Successfully withdrew R%.2f (including R%.2f bank charge). New balance: R%.2f%n", amount, BANK_CHARGE, balance);
            } else {
                System.out.println("Insufficient funds. Make sure you have enough balance including bank charges.");
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    @Override
    public void displayBalance() {
        System.out.printf("Current balance: R%.2f%n", balance);
    }
}

// Subclass: SavingsAccount
class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String accountHolder, double initialBalance) {
        super(accountHolder, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited R%.2f into savings. New balance: R%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance - amount >= MINIMUM_BALANCE) {
                balance -= amount;
                System.out.printf("Successfully withdrew R%.2f from savings. New balance: R%.2f%n", amount, balance);
            } else {
                System.out.println("Withdrawal denied. Minimum balance requirement not met.");
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    @Override
    public void displayBalance() {
        System.out.printf("Current savings balance: R%.2f%n", balance);
    }
}

// Main class: BankApplication
public class BankApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("---- Welcome to the Bank Account Management System ----");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Clear buffer

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        transaction("deposit");
                        break;
                    case 3:
                        transaction("withdraw");
                        break;
                    case 4:
                        displayBalance();
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    // Method to create a new account
    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        // Ensure initial deposit is valid
        if (initialDeposit <= 0) {
            System.out.println("Initial deposit must be positive.");
            return;
        }

        System.out.print("Choose account type (1: Bank Account, 2: Savings Account): ");
        int accountType = scanner.nextInt();

        Account account;
        if (accountType == 1) {
            account = new BankAccount(accountHolder, initialDeposit);
        } else if (accountType == 2) {
            account = new SavingsAccount(accountHolder, initialDeposit);
        } else {
            System.out.println("Invalid account type. Account creation failed.");
            return;
        }

        accounts.put(accountHolder, account);
        System.out.println("Account created successfully for " + accountHolder);
    }

    // Method for deposit and withdraw transactions
    private static void transaction(String type) {
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();

        Account account = accounts.get(accountHolder);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (type.equals("deposit")) {
            account.deposit(amount);
        } else if (type.equals("withdraw")) {
            account.withdraw(amount);
        }
    }

    // Method to display account balance
    private static void displayBalance() {
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();

        Account account = accounts.get(accountHolder);
        if (account != null) {
            account.displayBalance();
        } else {
            System.out.println("Account not found.");
        }
    }
}
