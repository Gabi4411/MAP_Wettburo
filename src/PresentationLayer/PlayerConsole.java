package PresentationLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ModelLayer.Odds;
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

    /**
     * Displays the main menu and handles user input for different actions.
     * This method continuously displays the menu and processes user choices until the user exits.
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-- Betting Console Menu --");
            System.out.println("1. View Events");
            System.out.println("2. View Bet History");
            System.out.println("3. View Player active Bets");
            System.out.println("4. View BetOdd");
            System.out.println("5. Filter Odd");
            System.out.println("6. Filter Event by date");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> playerController.viewEvents();
                case 2 -> playerController.viewBetHistory(getPlayerId(scanner));
                case 3 -> playerController.viewPlayerBets(getPlayerId(scanner));
                case 4 -> playerController.SportTypeFilter();
                case 5 -> playerController.view_Bet_Odd(getBetID(scanner));
                case 6 -> playerController.oddFilter();
                case 7-> {
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

//    private List<Odds> getOdds(Scanner scanner){
//        List<String> odds = new ArrayList<>();
//        odds.add(1,"GG");
//        odds.add(2,"Correct Score");
//    }

//    private void getEvent(Scanner scanner) {
//         List<> events = new ArrayList<>();
//         events.add(1,"Football");
//         events.add(2,"Football");
//         events.add(3,"Basketball");
//         events.add(4,"Tennis");
//
//
//    }

    private String getSportType(Scanner scanner) {
        System.out.println("Enter Sport: ");
        return scanner.nextLine();
    }


    private double getValue(Scanner scanner) {
        System.out.println("Enter Value: ");
        return Double.parseDouble(scanner.nextLine());
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
        PlayerController playerController1 = new PlayerController(betService, userService);
        PlayerConsole playerConsole = new PlayerConsole(playerController1);

        // Start the console menu
        playerConsole.displayMenu();
    }
}
