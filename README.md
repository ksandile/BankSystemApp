
# Bank Account Management System

This is a simple console-based Java application that simulates basic bank account management functionalities, allowing users to deposit, withdraw, and check their balance. The program also accounts for a bank charge for each withdrawal.

## Features

- **Deposit**: Allows users to deposit funds into their account.
- **Withdraw**: Allows users to withdraw funds with a fixed bank charge.
- **Balance Check**: Displays the current account balance.

## Requirements

- Java Development Kit (JDK) 8 or higher

## Setup and Installation

1. **Clone or download** this repository to your local machine.
2. Open a terminal or command prompt in the directory containing the `BankAccount.java` file.

## How to Run

### Compilation

Compile the Java file using the following command:

```bash
javac BankApplication.java

```

### Execution

Run the compiled program with:

```bash
java BankApplication

```

## Usage

When the program starts, you’ll be prompted to enter your account holder name and initial deposit amount.

A menu will then appear with options:
   - `1`: Deposit funds
   - `2`: Withdraw funds (includes a R10 bank charge per withdrawal)
   - `3`: Check your current balance
   - `4`: Exit the program

### Example Operation:

If you deposit R1000 initially and choose to withdraw R300, the program deducts R310 (R300 + R10 bank charge) from your balance.

### Example Output

```
Enter account holder's name: John Doe
Enter initial deposit amount: 1000

---- Bank Account Management System ----
1. Deposit
2. Withdraw
3. Display Balance
4. Exit
Choose an option: 2
Enter withdrawal amount: 300
Successfully withdrew R300.00 (including R10.00 bank charge). New balance: R690.00

---- Bank Account Management System ----
1. Deposit
2. Withdraw
3. Display Balance
4. Exit
Choose an option: 3
Current balance: R690.00
```

## Notes

- The program only works in a console environment.
- Future versions could implement database storage or integration with APIs for more persistent data handling.

## License

This project is open-source and free to use.
