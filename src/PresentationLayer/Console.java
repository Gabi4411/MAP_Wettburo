package PresentationLayer;

import java.util.Scanner;
import RepoLayerInterface.*;
import ModelLayer.*;
import ServiceLayer.*;
import ControllerLayer.*;

/**
 * A console interface for interacting with the betting system.
 * This class displays a menu and processes user input to perform various actions related to betting.
 */
public class Console {

    /** The controller that manages user-related actions. */
    private final UserController userController;

    /**
     * Constructs a new Console object with the specified UserController.
     *
     * @param userController The UserController that manages user operations.
     */
    public Console(UserController userController) {
        this.userController = userController;
    }

    /**
     * Displays the main menu and handles user input for different actions.
     * This method continuously displays the menu and processes user choices until the user exits.
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-- Betting Console Menu --");
            System.out.println("1. View Players");
            System.out.println("2. Update Admin Access Level");
            System.out.println("3. View Player Bets");
            System.out.println("4. Create New Bet Event");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> userController.viewPlayers(getPlayerId(scanner));
                case 2 -> userController.updateAdmin(getAdminId(scanner), getAccessLevel(scanner));
                case 3 -> userController.viewPlayerBets(getPlayerId(scanner));
                case 4 -> userController.new_betEvent(getEventID(scanner), getEventType(scanner));
                case 5 -> {
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

    /**
     * The main method that initializes the application and starts the console menu.
     * Creates the necessary repositories, services, and controllers, then displays the menu.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Repositories for different models
        inMemoryRepo<ModelLayer.Bet> betRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Event> eventRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Odds> oddsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.PlayerBet> playerbetRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Admin> adminRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Transactions> transactionsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Player> playerRepo = new inMemoryRepo<>();

        // Create service objects
        BetService betService = new BetService(betRepo, eventRepo, oddsRepo, playerbetRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        // Create UserController and Console objects
        UserController userController1 = new UserController(betService, userService);
        Console console = new Console(userController1);

        // Start the console menu
        console.displayMenu();
    }
}
