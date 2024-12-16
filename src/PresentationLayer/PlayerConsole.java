package PresentationLayer;

import java.time.LocalDateTime;
import java.util.*;

import ModelLayer.*;
import RepoLayerInterface.*;
import ServiceLayer.*;
import ControllerLayer.*;

/**
 * A console interface for interacting with the betting system.
 * This class displays a menu and processes user input to perform various actions related to betting.
 */
public class PlayerConsole {

    /** The controller that manages user-related actions. */
    private final PlayerController playerController;

    /**
     * Constructs a new Console object with the specified UserController.
     *
     * @param playerController The UserController that manages user operations.
     */
    public PlayerConsole(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void welcomeMenu() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("---Welcome to Player Console---");
            System.out.println("What would you like to do?");
            System.out.println("1. Create Player Account");
            System.out.println("2. Login Player");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    playerLogin(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, try another one!");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        if (playerController.createPlayerAccount(username, password, email)) {
            displayMenu(scanner);
        }
        else {
            welcomeMenu();
        }
    }

    private void playerLogin(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if(playerController.playerLogin(username, password)){
            displayMenu(scanner);
        }
        else{
            welcomeMenu();
        }
    }

    /**
     * Displays the main menu and handles user input for different actions.
     * This method continuously displays the menu and processes user choices until the user exits.
     */
    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n-- Betting Console Menu --");
            System.out.println("1. View Events");
            System.out.println("2. View Bet History");
            System.out.println("3. View Player active Bets");
            System.out.println("4. SportTypeFilter");
            System.out.println("5. Filter Transactions");
            System.out.println("6. Deposit");
            System.out.println("7. Withdraw");
            System.out.println("8. Place Bet");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> playerController.viewEvents();
                case 2 -> playerController.viewBetHistory(getPlayerId(scanner));
                case 3 -> playerController.viewPlayerBets(getPlayerId(scanner));
                case 4 -> playerController.SportTypeFilter(getSportType(scanner));
                case 5 -> playerController.transactionFilter(getTransactionType(scanner));
                case 6 -> playerController.deposit(getUserName(scanner),getPassword(scanner),getAmount(scanner));
                case 7 -> playerController.withdraw(getUserName(scanner), getPassword(scanner),getAmount(scanner));
                case 8 -> playerController.placeNewBet(getPlayerId(scanner));
                ////withdraw(updateBalance)
                //viewBalance
                case 0-> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter a Player ID.
     *
     * @param scanner The scanner to read input from the user.
     * @return The entered Player ID.
     */
    private static int getPlayerId(Scanner scanner) {
        System.out.println("Enter Player ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static  String getUserName(Scanner scanner) {
        System.out.println("Enter your username: ");
        return scanner.nextLine();
    }

    private static String getPassword(Scanner scanner) {
        System.out.println("Enter your password: ");
        return scanner.nextLine();
    }

    private static int getAmount(Scanner scanner) {
        System.out.println("Enter your amount: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Prompts the user to enter an Event ID.
     *
     * @param scanner The scanner to read input from the user.
     * @return The entered Event ID.
     */
    private static String getEventID(Scanner scanner) {
        System.out.print("Enter Event ID: ");
        return scanner.nextLine();
    }


    private String getSportType(Scanner scanner) {
        System.out.println("Enter Sport: ");
        return scanner.nextLine();
    }


    private String getTransactionType(Scanner scanner) {
        System.out.println("Enter Transction Type: ");
        return scanner.nextLine();
    }

    private static Integer getBetID(Scanner scanner) {
        System.out.print("Enter Bet ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Prompts the user to enter an Event Type.
     *
     * @param scanner The scanner to read input from the user.
     * @return The entered Event Type.
     */
    private static String getEventType(Scanner scanner) {
        System.out.print("Enter Event Type: ");
        return scanner.nextLine();
    }

    /**
     * Prompts the user to enter an Admin ID.
     *
     * @param scanner The scanner to read input from the user.
     * @return The entered Admin ID.
     */
    private static Integer getAdminId(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Prompts the user to enter a new access level for an admin.
     *
     * @param scanner The scanner to read input from the user.
     * @return The entered access level.
     */
    private static int getAccessLevel(Scanner scanner) {
        System.out.print("Enter New Access Level: ");
        return Integer.parseInt(scanner.nextLine());
    }
}