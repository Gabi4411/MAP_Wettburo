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
        boolean running = true;

        while(running) {
            System.out.println("===Welcome to Player Console===");
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
                    adminLogin(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
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

    private void adminLogin(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        playerController.playerLogin(username, password);
        displayMenu(scanner);
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

    public static void InitalizeRepo(
            repo<Event> eventRepo,
            repo<Bet> betRepo,
            repo<Player> playerRepo,
            repo<Transactions> transactionsRepo,
            repo<Admin> adminRepo,
            repo<Odds> oddsRepo,
            repo<Suport> suportrepo,
            repo<Statistics> statisticsrepo
    ) {
        Odds odd1 = new Odds(1, "Gol in minutul 10", "Football");
        Odds odd2 = new Odds(2, "Gol in minutul 90", "Football");
        Odds odd3 = new Odds(3, "Ace din prima", "Tennis");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map2.put(odd2, 1.5);
        Map<Odds, Double> map3 = new HashMap<Odds, Double>();
        map3.put(odd3, 2.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
        Event event2 = new Event(2, "UCluj vs Galati", map2, "12.03.2024", "Football");
        Event event3 = new Event(3, "Simona vs Nadal", map3, "03.03.2024", "Tennis");
        eventRepo.create(event1);
        eventRepo.create(event2);
        eventRepo.create(event3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        Bet bet1 = new Bet(1, 1, events, 30, LocalDateTime.now(), "Active");
        Bet bet2 = new Bet(2, 2, events, 100, LocalDateTime.now(), "Ended");
        Bet bet3 = new Bet(1, 3, events, 10, LocalDateTime.now(), "Active");
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

        transactionsRepo.create(new Transactions(1, player1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
        transactionsRepo.create(new Transactions(2, player2, 100, LocalDateTime.now(), "Deposit", "Completed"));

        adminRepo.create(new Admin(1, "Sefu1", "123456789", "sefu@tau.com", 5000, 3, "Support"));
        adminRepo.create(new Admin(2, "Sefu2", "987654321", "sefusefilor@tau.com", 10000, 2, "Support"));

        Odds odd4 = new Odds(4, "Peste 1.5 goluri", "Football");
        Odds odd5 = new Odds(5, "Sub 1.5 goluri", "Football");
        Odds odd6 = new Odds(6, "6-4 al doilea set", "Tennis");

        oddsRepo.create(odd4);
        oddsRepo.create(odd5);
        oddsRepo.create(odd6);

        Suport suport1 = new Suport(1, 1, "Problems when withdrawing", LocalDateTime.now(), "Active");
        Suport suport2 = new Suport(2, 2, "Problems when depositing", LocalDateTime.now(), "Ended");

        suportrepo.create(suport1);
        suportrepo.create(suport2);

        Statistics statistics1 = new Statistics(1, "The best Football Match in History", "Steaua has a 70% change of winning!");
        Statistics statistics2 = new Statistics(2, "The match will be fully booked", "UCluj will win!");

        statisticsrepo.create(statistics1);
        statisticsrepo.create(statistics2);
    }



    /**
     * The main method that initializes the application and starts the console menu.
     * Creates the necessary repositories, services, and controllers, then displays the menu.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean useFiles = false;

        if (useFiles) {
            System.out.println("Using FileRepository\n");
        } else {
            System.out.println("Using inMemoryRepo\n");
        }

        repo<Bet> betRepo;
        repo<Event> eventRepo;
        repo<Player> playerRepo;
        repo<Transactions> transactionsRepo;
        repo<Admin> adminRepo;
        repo<Odds> oddsRepo;
        repo<Suport> suportRepo;
        repo<Statistics> statisticsRepo;

        if (useFiles) {
            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/Files/";

            betRepo = new FileRepository<>(
                    filePath + "bets.txt",
                    Bet::fromCSV,
                    Bet::toCSV,
                    Bet::getBet_id
            );

            eventRepo = new FileRepository<>(
                    filePath + "events.txt",
                    Event::fromCSV,
                    Event::toCSV,
                    Event::getEvent_id
            );

            playerRepo = new FileRepository<>(
                    filePath + "players.txt",
                    Player::fromCSV,
                    Player::toCSV,
                    Player::getUser_id
            );

            transactionsRepo = new FileRepository<>(
                    filePath + "transactions.txt",
                    Transactions::fromCSV,
                    Transactions::toCSV,
                    Transactions::getTransaction_id
            );

            adminRepo = new FileRepository<>(
                    filePath + "admins.txt",
                    Admin::fromCSV,
                    Admin::toCSV,
                    Admin::getUser_id
            );

            oddsRepo = new FileRepository<>(
                    filePath + "odds.txt",
                    Odds::fromCSV,
                    Odds::toCSV,
                    Odds::getOdd_id
            );

            suportRepo = new FileRepository<>(
                    filePath + "suport.txt",
                    Suport::fromCSV,
                    Suport::toCSV,
                    Suport::getSuport_id
            );

            statisticsRepo = new FileRepository<>(
                    filePath + "statistics.txt",
                    Statistics::fromCSV,
                    Statistics::toCSV,
                    Statistics::getEventId
            );
        }
        else {
            //Repositories for inMemory
            betRepo = new inMemoryRepo<>();
            eventRepo = new inMemoryRepo<>();
            playerRepo = new inMemoryRepo<>();
            transactionsRepo = new inMemoryRepo<>();
            adminRepo = new inMemoryRepo<>();
            oddsRepo = new inMemoryRepo<>();
            suportRepo = new inMemoryRepo<>();
            statisticsRepo = new inMemoryRepo<>();
        }
        //Create service objects
        BetService betService = new BetService(betRepo, eventRepo,transactionsRepo, playerRepo, oddsRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo, suportRepo, statisticsRepo);

        //Create Admin Controller and Console Object
        PlayerController playerController1 = new PlayerController(betService, userService);
        PlayerConsole playerConsole = new PlayerConsole(playerController1);

        //Initialize repos
        InitalizeRepo(eventRepo, betRepo, playerRepo, transactionsRepo, adminRepo, oddsRepo, suportRepo, statisticsRepo);


        playerConsole.welcomeMenu();
    }
}
