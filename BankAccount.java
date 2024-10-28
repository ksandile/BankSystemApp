import java.util.Scanner;

public class BankAccount {
    private String accountHolder;
    private double balance;

    // Bank charge for each withdrawal
    private static final double BANK_CHARGE = 10.0;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited R%.2f. New balance: R%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        double totalAmount = amount + BANK_CHARGE;

        if (amount > 0) {
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

    // Method to display balance
    public void displayBalance() {
        System.out.printf("Current balance: R%.2f%n", balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setting up the account (in a real scenario, user data would be stored in a database)
        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        
        BankAccount account = new BankAccount(accountHolder, initialDeposit);

        // Console menu for account operations
        while (true) {
            System.out.println("\n---- Bank Account Management System ----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
