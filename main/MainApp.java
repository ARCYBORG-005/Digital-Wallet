package main;

import java.util.Scanner;
import model.User;
import service.UserService;
import service.WalletService;

public class MainApp {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        WalletService walletService = new WalletService();
        
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. Register User");
            System.out.println("2. View Balance");
            System.out.println("3. Transfer Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1: 
                    // Register a new user
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.println("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    
                    User user = new User(name, email, password, balance);
                    boolean isRegistered = userService.registerUser(user);
                    if (isRegistered) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User registration failed!");
                    }
                    break;
                
                case 2:
                    // View balance
                    System.out.println("Enter User ID to check balance: ");
                    int userId = scanner.nextInt();
                    User loggedInUser = userService.loginUserById(userId);  // Add this method to your UserService to fetch the user.
                    if (loggedInUser != null) {
                        System.out.println("User Balance: " + loggedInUser.getBalance());
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                
                case 3:
                    // Perform a transfer
                    System.out.println("Enter your User ID: ");
                    int fromUserId = scanner.nextInt();
                    System.out.println("Enter Recipient User ID: ");
                    int toUserId = scanner.nextInt();
                    System.out.println("Enter Amount to Transfer: ");
                    double amount = scanner.nextDouble();
                    
                    boolean transferSuccess = walletService.transferMoney(fromUserId, toUserId, amount);
                    if (transferSuccess) {
                        System.out.println("Money transferred successfully!");
                    } else {
                        System.out.println("Transfer failed!");
                    }
                    break;
                
                case 4:
                    // Exit the program
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }

        scanner.close();
    }
}