import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize account balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew ₹" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
        return false;
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to connect the ATM to the user's account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display options to the user
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*** ATM Menu ***");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Method to check balance
    private void checkBalance() {
        System.out.println("Your current balance is ₹" + account.getBalance());
    }

    // Method to deposit money
    private void depositMoney(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Method to withdraw money
    private void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

// Main class to run the ATM system
public class ATMInterface {
    public static void main(String[] args) {
        // Initialize a bank account with an initial balance of ₹5000
        BankAccount userAccount = new BankAccount(5000);

        // Create an ATM instance and pass the user's account
        ATM atm = new ATM(userAccount);

        // Show the ATM menu
        atm.showMenu();
    }
}
