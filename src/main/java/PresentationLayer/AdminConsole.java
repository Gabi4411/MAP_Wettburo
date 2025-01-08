package PresentationLayer;

import ControllerLayer.AdminController;
import ModelLayer.*;
import RepoLayerInterface.*;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import PresentationLayer.CombinedConsoles;


public class AdminConsole {
    private final AdminController adminController;
    private final CombinedConsoles combinedConsoles;

    public AdminConsole(AdminController adminController, CombinedConsoles combinedConsoles) {

        this.adminController = adminController;
        this.combinedConsoles = combinedConsoles;

    }

    public void welcomeMenu() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("---Welcome to Admin Console---");
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
                     combinedConsoles.showMainMenu();
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
        if(adminController.adminLogin(username, password)) {
            showMenu(scanner);
        }
        else {
            welcomeMenu();
        }
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
        System.out.println("9. Get Active Supports");
        System.out.println("10. Create new Statistic for Event");
        System.out.println("11. Exit");
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
                activeSupports(scanner);
                break;
            case 10:
                createStatistic(scanner);
                break;
            case 11:
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

    private void activeSupports(Scanner scanner) {
        System.out.println("What players do you want to check? (Stop - 0)");
        List<Integer> playersIds = new ArrayList<>();
        while (true) {
            int input = scanner.nextInt();

            if (input == 0) {
                break;
            }

            playersIds.add(input);
        }
        System.out.println("Here are the IDs for the players who have an active Problem: ");
        adminController.whatProblemsAreActive(playersIds);
        showMenu(scanner);
    }

    private void createStatistic(Scanner scanner) {
       System.out.println("Give stat id: ");
       int statId = Integer.parseInt(scanner.nextLine());
        System.out.println("Give me the event id:");
        int eventId = Integer.parseInt(scanner.nextLine());
        System.out.println("Give me the desciption:");
        String desc = scanner.nextLine();
        System.out.println("Give me the prediction:");
        String prediction = scanner.nextLine();
        adminController.addStatistic(statId,eventId, desc, prediction);
        showMenu(scanner);
    }

//    public static void InitalizeRepo(
//            repo<Event> eventRepo,
//            repo<Bet> betRepo,
//            repo<Player> playerRepo,
//            repo<Transactions> transactionsRepo,
//            repo<Admin> adminRepo,
//            repo<Odds> oddsRepo,
//            repo<Suport> suportrepo,
//            repo<Statistics> statisticsrepo
//    ) {
//        Odds odd1 = new Odds(1, "Gol in minutul 10", "Football");
//        Odds odd2 = new Odds(2, "Gol in minutul 90", "Football");
//        Odds odd3 = new Odds(3, "Ace din prima", "Tennis");
//        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
//        map1.put(odd1, 2.5);
//        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
//        map2.put(odd2, 1.5);
//        Map<Odds, Double> map3 = new HashMap<Odds, Double>();
//        map3.put(odd3, 2.0);
//        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
//        Event event2 = new Event(2, "UCluj vs Galati", map2, "12.03.2024", "Football");
//        Event event3 = new Event(3, "Simona vs Nadal", map3, "03.03.2024", "Tennis");
//        eventRepo.create(event1);
//        eventRepo.create(event2);
//        eventRepo.create(event3);
//
//        List<Event> events = new ArrayList<>();
//        events.add(event1);
//        events.add(event2);
//
//        Bet bet1 = new Bet(1, 1, events, 30, LocalDateTime.now(), "Active");
//        Bet bet2 = new Bet(2, 2, events, 100, LocalDateTime.now(), "Ended");
//        Bet bet3 = new Bet(1, 3, events, 10, LocalDateTime.now(), "Active");
//        betRepo.create(bet1);
//        betRepo.create(bet2);
//        betRepo.create(bet3);
//
//        List<Bet> bets = new ArrayList<>();
//        bets.add(bet1);
//        bets.add(bet2);
//        bets.add(bet3);
//
//        Player player1 = new Player(1, "Gabi", "1234", "moldovangabi@yahoo.com", 100.0, bets, bets, 0, "Active");
//        Player player2 = new Player(2, "Lapa", "5678", "lapadtuandrei@yahoo.com", 4000, bets, bets, 0, "Active");
//        playerRepo.create(player1);
//        playerRepo.create(player2);
//
////        transactionsRepo.create(new Transactions(1, player1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
////        transactionsRepo.create(new Transactions(2, player2, 100, LocalDateTime.now(), "Deposit", "Completed"));
//
//        adminRepo.create(new Admin(1, "Sefu1", "123456789", "sefu@tau.com", 5000, 3, "Support"));
//        adminRepo.create(new Admin(2, "Sefu2", "987654321", "sefusefilor@tau.com", 10000, 2, "Support"));
//
//        Odds odd4 = new Odds(4, "Peste 1.5 goluri", "Football");
//        Odds odd5 = new Odds(5, "Sub 1.5 goluri", "Football");
//        Odds odd6 = new Odds(6, "6-4 al doilea set", "Tennis");
//
//        oddsRepo.create(odd4);
//        oddsRepo.create(odd5);
//        oddsRepo.create(odd6);
//
//        Suport suport1 = new Suport(1, 1, "Problems when withdrawing", LocalDateTime.now(), "Active");
//        Suport suport2 = new Suport(2, 2, "Problems when depositing", LocalDateTime.now(), "Ended");
//
//        suportrepo.create(suport1);
//        suportrepo.create(suport2);
//
//        Statistics statistics1 = new Statistics(1, 1,"The best Football Match in History", "Steaua has a 70% change of winning!");
//        Statistics statistics2 = new Statistics(2, 1,"The match will be fully booked", "UCluj will win!");
//
//        statisticsrepo.create(statistics1);
//        statisticsrepo.create(statistics2);
//    }
//
//    public static void main(String[] args) {
//        boolean useFiles = false;
//
//        if (useFiles) {
//            System.out.println("Using FileRepository\n");
//        } else {
//            System.out.println("Using inMemoryRepo\n");
//        }
//
//        repo<Bet> betRepo;
//        repo<Event> eventRepo;
//        repo<Player> playerRepo;
//        repo<Transactions> transactionsRepo;
//        repo<Admin> adminRepo;
//        repo<Odds> oddsRepo;
//        repo<Suport> suportRepo;
//        repo<Statistics> statisticsRepo;
//
//        if (useFiles) {
//            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/Files/";
//
//            betRepo = new FileRepository<>(
//                    filePath + "bets.txt",
//                    Bet::fromCSV,
//                    Bet::toCSV,
//                    Bet::getBet_id
//            );
//
//            eventRepo = new FileRepository<>(
//                    filePath + "events.txt",
//                    Event::fromCSV,
//                    Event::toCSV,
//                    Event::getEvent_id
//            );
//
//            playerRepo = new FileRepository<>(
//                    filePath + "players.txt",
//                    Player::fromCSV,
//                    Player::toCSV,
//                    Player::getUser_id
//            );
//
//            transactionsRepo = new FileRepository<>(
//                    filePath + "transactions.txt",
//                    Transactions::fromCSV,
//                    Transactions::toCSV,
//                    Transactions::getTransaction_id
//            );
//
//            adminRepo = new FileRepository<>(
//                    filePath + "admins.txt",
//                    Admin::fromCSV,
//                    Admin::toCSV,
//                    Admin::getUser_id
//            );
//
//            oddsRepo = new FileRepository<>(
//                    filePath + "odds.txt",
//                    Odds::fromCSV,
//                    Odds::toCSV,
//                    Odds::getOdd_id
//            );
//
//            suportRepo = new FileRepository<>(
//                    filePath + "suport.txt",
//                    Suport::fromCSV,
//                    Suport::toCSV,
//                    Suport::getSuport_id
//            );
//
//            statisticsRepo = new FileRepository<>(
//                    filePath + "statistics.txt",
//                    Statistics::fromCSV,
//                    Statistics::toCSV,
//                    Statistics::getStat_id
//            );
//        }
//        else {
//            //Repositories for inMemory
//            betRepo = new inMemoryRepo<>();
//            eventRepo = new inMemoryRepo<>();
//            playerRepo = new inMemoryRepo<>();
//            transactionsRepo = new inMemoryRepo<>();
//            adminRepo = new inMemoryRepo<>();
//            oddsRepo = new inMemoryRepo<>();
//            suportRepo = new inMemoryRepo<>();
//            statisticsRepo = new inMemoryRepo<>();
//        }
//        //Create service objects
//        BetService betService = new BetService(betRepo, eventRepo,transactionsRepo, playerRepo, oddsRepo);
//        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo, suportRepo, statisticsRepo);
//
//        //Create Admin Controller and Console Object
//        AdminController adminController1 = new AdminController(userService, betService);
//        AdminConsole adminConsole = new AdminConsole(adminController1);
//
//        //Initialize repos
//        InitalizeRepo(eventRepo, betRepo, playerRepo, transactionsRepo, adminRepo, oddsRepo, suportRepo, statisticsRepo);
//
//
//        adminConsole.welcomeMenu();
//    }
}
