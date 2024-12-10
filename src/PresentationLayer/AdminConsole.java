package PresentationLayer;

import ControllerLayer.AdminController;
import ModelLayer.*;
import RepoLayerInterface.*;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import java.time.LocalDateTime;
import java.util.*;


public class AdminConsole {
    private final AdminController adminController;

    public AdminConsole(AdminController adminController) {
        this.adminController = adminController;
    }

    public void welcomeMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("===Welcome to Admin Console===");
            System.out.println("What would you like to do?");
            System.out.println("1. Create Admin");
            System.out.println("2. Login Admin");
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
        if (adminController.createAdminAccount(username, password, email)) {
            showMenu(scanner);
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
        adminController.adminLogin(username, password);
        showMenu(scanner);
    }

    private void showMenu(Scanner scanner) {
        System.out.println("===Menu===");
        System.out.println("1. Create Event");
        System.out.println("2. View Events");
        System.out.println("3. View Players");
        System.out.println("4. View Admins");
        System.out.println("5. View Bets");
        System.out.println("6. Update Admin Access Level");
        System.out.println("7. Sorted Events By Date");
        System.out.println("8. Sorted Players By Name");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                createEvent(scanner);
                break;
            case 2:
                viewEvents(scanner);
                break;
            case 3:
                viewPlayers(scanner);
                break;
            case 4:
                viewAdmins(scanner);
                break;
            case 5:
                viewBets(scanner);
                break;
            case 6:
                updateAdminAccesLevel(scanner);
                break;
            case 7:
                sortedEventsbyDate(scanner);
                break;
            case 8:
                sortedPlayersByName(scanner);
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid choice, try another one!");
        }
    }

    public void createEvent(Scanner scanner) {
        System.out.println("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.println("Enter event type: ");
        String eventType = scanner.nextLine();
        System.out.println("Enter event date: ");
        String eventDate = scanner.nextLine();
        System.out.println("Enter odd name: ");
        String oddName = scanner.nextLine();
        System.out.println("Enter odd value: ");
        Double value = Double.valueOf(scanner.nextLine());
        adminController.createEvent(eventName, eventType, eventDate, oddName, value);
        showMenu(scanner);
    }

    private void viewEvents(Scanner scanner) {
        adminController.viewEvents();
        showMenu(scanner);
    }

    private void viewPlayers(Scanner scanner) {
        adminController.viewPlayers();
        showMenu(scanner);
    }

    private void viewAdmins(Scanner scanner) {
        adminController.viewAdmins();
        showMenu(scanner);
    }

    private void viewBets(Scanner scanner) {
        adminController.viewBets();
        showMenu(scanner);
    }

    private void updateAdminAccesLevel(Scanner scanner) {
        System.out.println("Enter admin id: ");
        int adminId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter admin access level(1, 2, 3): ");
        int accessLevel = Integer.parseInt(scanner.nextLine());
        adminController.updateAdmin(adminId, accessLevel);
        showMenu(scanner);
    }

    private void sortedEventsbyDate(Scanner scanner) {
        System.out.println("How do you want the list to be sorted: ascending(1) or descending(2)?");
        int answear = scanner.nextInt();
        boolean ascending;
        if(answear == 1) {
            ascending = true;
        }
        else {
            ascending = false;
        }
        adminController.sortEventsByDateController(ascending);
        showMenu(scanner);
    }

    private void sortedPlayersByName(Scanner scanner) {
        System.out.println("How do you want the list to be sorted: ascending(1) or descending(2)?");
        int answear = scanner.nextInt();
        boolean ascending;
        if(answear == 1) {
            ascending = true;
        }
        else {
            ascending = false;
        }
        adminController.sortPlayersByNameController(ascending);
        showMenu(scanner);
    }

    public static void InitalizeRepo(
            repo<Event> eventRepo,
            repo<Bet> betRepo,
            repo<Player> playerRepo,
            repo<Transactions> transactionsRepo,
            repo<Admin> adminRepo,
            repo<Odds> oddsRepo
    ) {
        Odds odd1 = new Odds(1, "Gol in minutul 10", "Football");
        Odds odd2 = new Odds(2, "Gol in minutul 90", "Football");
        Odds odd3 = new Odds(3, "Ace din prima", "Tennis");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.5);
        Map<Odds, Double> map3 = new HashMap<Odds, Double>();
        map1.put(odd3, 2.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
        Event event2 = new Event(2, "UCluj vs Galati", map2, "12.03.2024", "Football");
        Event event3 = new Event(3, "Simona vs Nadal", map3, "03.03.2024", "Tennis");
        eventRepo.create(event1);
        eventRepo.create(event2);
        eventRepo.create(event3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

//        (int player_id,int bet_id, List<Event> event, int amount, LocalDateTime bet_date, String betstatus)
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
    }

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

        if (useFiles) {
            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/Files/";

            betRepo = new FileRepository<>(filePath + "bets.txt", Bet.class);
            eventRepo = new FileRepository<>(filePath + "events.txt", Event.class);
            playerRepo = new FileRepository<>(filePath + "players.txt", Player.class);
            transactionsRepo = new FileRepository<>(filePath + "transactions.txt", Transactions.class);
            adminRepo = new FileRepository<>(filePath + "admins.txt", Admin.class);
            oddsRepo = new FileRepository<>(filePath + "odds.txt", Odds.class);
        }
        else {
            //Repositories for inMemory
            betRepo = new inMemoryRepo<>();
            eventRepo = new inMemoryRepo<>();
            playerRepo = new inMemoryRepo<>();
            transactionsRepo = new inMemoryRepo<>();
            adminRepo = new inMemoryRepo<>();
            oddsRepo = new inMemoryRepo<>();
        }
        //Create service objects
        BetService betService = new BetService(betRepo, eventRepo,transactionsRepo, playerRepo, oddsRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        //Create Admin Controller and Console Object
        AdminController adminController1 = new AdminController(userService, betService);
        AdminConsole adminConsole = new AdminConsole(adminController1);

        //Initialize repos
        InitalizeRepo(eventRepo, betRepo, playerRepo, transactionsRepo, adminRepo, oddsRepo);


        adminConsole.welcomeMenu();
    }
}
