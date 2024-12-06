package PresentationLayer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("4. SportTypeFilter");
            System.out.println("5. ViewBetOdd");
            System.out.println("6. Filter Odds");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> playerController.viewEvents();
                case 2 -> playerController.viewBetHistory(getPlayerId(scanner));
                case 3 -> playerController.viewPlayerBets(getPlayerId(scanner));
                case 4 -> playerController.SportTypeFilter(getSportType(scanner));
                case 5 -> playerController.view_Bet_Odd(getBetID(scanner));
                case 6 -> playerController.oddFilter(getOddName(scanner));
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


    private String getSportType(Scanner scanner) {
        System.out.println("Enter Sport: ");
        return scanner.nextLine();
    }


    private String getOddName(Scanner scanner) {
        System.out.println("Enter OddName: ");
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

    public static void InitalizeRepo(
            repo<Event> eventRepo,
            repo<Bet> betRepo,
            repo<Player> playerRepo,
            repo<FootballOdds> footballOddsRepo,
            repo<TennisOdds> tennisOddsRepo,
            repo<BasketOdds> basketOddsRepo,
            repo<Transactions> transactionsRepo,
            repo<Admin> adminRepo
    ) {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", odds, LocalDateTime.now(), "Football");
        Event event2 = new Event(2, "UCluj vs Galati", odds, LocalDateTime.now(), "Football");
        Event event3 = new Event(3, "Simona vs Nadal", odds, LocalDateTime.now(), "Tennis");
        eventRepo.create(event1);
        eventRepo.create(event2);
        eventRepo.create(event3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        Bet bet1 = new Bet(1, events, 20, LocalDateTime.now(),"active");
        Bet bet2 = new Bet(2, events, 30, LocalDateTime.now(),"ended");
        Bet bet3 = new Bet(3, events, 40, LocalDateTime.now(),"ended");
        betRepo.create(bet1);
        betRepo.create(bet2);
        betRepo.create(bet3);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);

        Player player1 = new Player(1, "Gabi", "1234", "moldovangabi@yahoo.com", 100.0, bets, bets, 0, "Active");
        Player player2 = new Player(2, "Lapa", "5678", "lapadtuandrei@yahoo.com", 4000, bets, bets, 0, "Active");
        playerRepo.create(player1);
        playerRepo.create(player2);

        footballOddsRepo.create(new FootballOdds(odds, "Peste 5"));
        footballOddsRepo.create(new FootballOdds(odds, "Sub 5"));

        tennisOddsRepo.create(new TennisOdds(odds, "Most aces"));
        tennisOddsRepo.create(new TennisOdds(odds, "Minim un set"));

        basketOddsRepo.create(new BasketOdds(odds, "Peste 90 puncte"));
        basketOddsRepo.create(new BasketOdds(odds, "Triple double in match"));

        transactionsRepo.create(new Transactions(1, player1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
        transactionsRepo.create(new Transactions(2, player2, 100, LocalDateTime.now(), "Deposit", "Completed"));

        adminRepo.create(new Admin(1, "Sefu1", "123456789", "sefu@tau.com", 5000, 3, "Support"));
        adminRepo.create(new Admin(2, "Sefu2", "987654321", "sefusefilor@tau.com", 10000, 2, "Support"));
    }



    /**
     * The main method that initializes the application and starts the console menu.
     * Creates the necessary repositories, services, and controllers, then displays the menu.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
//        boolean useFiles = false;

        repo<Bet> betRepo;
        repo<Event> eventRepo;
        repo<Player> playerRepo;
        repo<FootballOdds> footballOddsRepo;
        repo<TennisOdds> tennisOddsRepo;
        repo<BasketOdds> basketOddsRepo;
        repo<Transactions> transactionsRepo;
        repo<Admin> adminRepo;

//        if(useFiles){
//            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/Files";
//            betRepo = new FileRepository<>(filePath + "bets.txt");
//            eventRepo = new FileRepository<>(filePath + "events.txt");
//            playerRepo = new FileRepository<>(filePath + "players.txt");
//            footballOddsRepo = new FileRepository<>(filePath + "footballOdds.txt");
//            tennisOddsRepo = new FileRepository<>(filePath + "tennisOdds.txt");
//            basketOddsRepo = new FileRepository<>(filePath + "basketOdds.txt");
//            transactionsRepo = new FileRepository<>(filePath + "transactins.txt");
//            adminRepo = new FileRepository<>(filePath + "admins.txt");
//        }
//        else {
            //Repositories for inMemory
            betRepo = new inMemoryRepo<>();
            eventRepo = new inMemoryRepo<>();
            playerRepo = new inMemoryRepo<>();
            footballOddsRepo = new inMemoryRepo<>();
            tennisOddsRepo = new inMemoryRepo<>();
            basketOddsRepo = new inMemoryRepo<>();
            transactionsRepo = new inMemoryRepo<>();
            adminRepo = new inMemoryRepo<>();
//        }

        //Create service objects
        BetService betService = new BetService(betRepo, eventRepo, footballOddsRepo, tennisOddsRepo, basketOddsRepo, playerRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        // Create UserController and Console objects
        PlayerController playerController1 = new PlayerController(betService, userService);
        PlayerConsole playerConsole = new PlayerConsole(playerController1);

        //Initialize repos
        InitalizeRepo(eventRepo, betRepo, playerRepo, footballOddsRepo, tennisOddsRepo, basketOddsRepo, transactionsRepo, adminRepo);

        // Start the console menu
        playerConsole.displayMenu();



    }
}
