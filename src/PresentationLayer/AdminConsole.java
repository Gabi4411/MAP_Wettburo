package PresentationLayer;

import ControllerLayer.AdminController;
import ModelLayer.*;
import RepoLayerInterface.*;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("2. Create Odds");
        System.out.println("3. View Events");
        System.out.println("4. View Odds");
        System.out.println("5. View Players");
        System.out.println("6. View Admins");
        System.out.println("7. View Bets");
        System.out.println("8. Update Admin Access Level");
        System.out.println("9. Sorted Events By Date");
        System.out.println("10. Sorted Players By Name");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                createEvent(scanner);
                break;
            case 2:
                createOdds(scanner);
                break;
            case 3:
                viewEvents(scanner);
                break;
            case 4:
                viewOdds(scanner);
                break;
            case 5:
                viewPlayers(scanner);
                break;
            case 6:
                viewAdmins(scanner);
                break;
            case 7:
                viewBets(scanner);
                break;
            case 8:
                updateAdminAccesLevel(scanner);
                break;
            case 9:
                sortedEventsbyDate(scanner);
                break;
            case 10:
                sortedPlayersByName(scanner);
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
        adminController.createEvent(eventName, eventType);
        showMenu(scanner);
    }

    private void createOdds(Scanner scanner) {
        System.out.println("Enter doubles separated by spaces (type 'done' to finish):");
        List<Double> oddsNumber = inputDoubles(scanner);
        System.out.println("Enter what are the odds for: ");
        String oddsString = scanner.nextLine();
        System.out.println("Enter event type: ");
        String eventType = scanner.nextLine();
        adminController.createOdds(oddsNumber, oddsString, eventType);
        showMenu(scanner);
    }

    private static List<Double> inputDoubles(Scanner scanner) {
        List<Double> doubleList = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] tokens = input.split(" ");
            try {
                for (String token : tokens) {
                    double value = Double.parseDouble(token);
                    doubleList.add(value);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid double values or type 'done' to finish.");
            }
        }

        return doubleList;
    }

    private void viewEvents(Scanner scanner) {
        adminController.viewEvents();
        showMenu(scanner);
    }

    private void viewOdds(Scanner scanner) {
        adminController.viewOdds();
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

    public static void main(String[] args) {
        boolean useFiles = true;

        repo<Bet> betRepo;
        repo<Event> eventRepo;
        repo<Player> playerRepo;
        repo<FootballOdds> footballOddsRepo;
        repo<TennisOdds> tennisOddsRepo;
        repo<BasketOdds> basketOddsRepo;
        repo<Transactions> transactionsRepo;
        repo<Admin> adminRepo;

        if(useFiles){
            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/Files/";
            betRepo = new FileRepository<>(filePath + "bets.txt");
            eventRepo = new FileRepository<>(filePath + "events.txt");
            playerRepo = new FileRepository<>(filePath + "players.txt");
            footballOddsRepo = new FileRepository<>(filePath + "footballOdds.txt");
            tennisOddsRepo = new FileRepository<>(filePath + "tennisOdds.txt");
            basketOddsRepo = new FileRepository<>(filePath + "basketOdds.txt");
            transactionsRepo = new FileRepository<>(filePath + "transactions.txt");
            adminRepo = new FileRepository<>(filePath + "admins.txt");
        }
        else {
            //Repositories for inMemory
            betRepo = new inMemoryRepo<>();
            eventRepo = new inMemoryRepo<>();
            playerRepo = new inMemoryRepo<>();
            footballOddsRepo = new inMemoryRepo<>();
            tennisOddsRepo = new inMemoryRepo<>();
            basketOddsRepo = new inMemoryRepo<>();
            transactionsRepo = new inMemoryRepo<>();
            adminRepo = new inMemoryRepo<>();
        }
        //Create service objects
        BetService betService = new BetService(betRepo, eventRepo, footballOddsRepo, tennisOddsRepo, basketOddsRepo, playerRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        //Create Admin Controller and Console Object
        AdminController adminController1 = new AdminController(userService, betService);
        AdminConsole adminConsole = new AdminConsole(adminController1);

        //Initialize repos
        InitalizeRepo(eventRepo, betRepo, playerRepo, footballOddsRepo, tennisOddsRepo, basketOddsRepo, transactionsRepo, adminRepo);


        adminConsole.welcomeMenu();
    }
}
