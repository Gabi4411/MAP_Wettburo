package PresentationLayer;

import ControllerLayer.AdminController;
import ControllerLayer.PlayerController;
import ModelLayer.*;
import RepoLayerInterface.FileRepository;
import RepoLayerInterface.inMemoryRepo;
import RepoLayerInterface.repo;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class CombinedConsoles {
    private final PlayerConsole playerConsole;
    private final AdminConsole adminConsole;

    public CombinedConsoles(
            repo<Bet> betRepo,
            repo<Event> eventRepo,
            repo<Transactions> transactionsRepo,
            repo<Player> playerRepo,
            repo<Admin> adminRepo,
            repo<Odds> oddsRepo,
            repo<Suport> suportRepo,
            repo<Statistics> statisticsRepo
    ) {
        BetService betService = new BetService(betRepo, eventRepo, transactionsRepo, playerRepo, oddsRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo, suportRepo, statisticsRepo);

        PlayerController playerController = new PlayerController(betService, userService);
        AdminController adminController = new AdminController(userService, betService);

        this.playerConsole = new PlayerConsole(playerController, this);
        this.adminConsole = new AdminConsole(adminController, this);
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Player Console");
            System.out.println("2. Admin Console");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: \n");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> playerConsole.welcomeMenu();
                case 2 -> adminConsole.welcomeMenu();
                case 3 -> {
                    System.out.println("Exiting application...\n");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static Integer chooseTypeOfRepo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a type of repo: ");
            System.out.println("1. Files");
            System.out.println("2. Memory");
            System.out.println("3. Database");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return 1; // File repo
                case 2:
                    return 2; // Memory repo
                case 3:
                    return 3; // Database repo
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
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
        Odds odd3 = new Odds(3, "Hattrick in match", "Football");
        oddsRepo.create(odd1);
        oddsRepo.create(odd2);
        oddsRepo.create(odd3);
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map2.put(odd2, 1.5);
        Map<Odds, Double> map3 = new HashMap<Odds, Double>();
        map3.put(odd3, 2.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
        Event event2 = new Event(2, "UCluj vs Galati", map2, "12.03.2024", "Football");
        Event event3 = new Event(3, "Liverpool vs Gaz Metan Medias", map3, "03.03.2024", "Football");
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

        transactionsRepo.create(new Transactions(1, 1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
        transactionsRepo.create(new Transactions(2, 2, 100, LocalDateTime.now(), "Deposit", "Completed"));

        adminRepo.create(new Admin(1, "Sefu1", "123456789", "sefu@tau.com", 5000, 3, "Support"));
        adminRepo.create(new Admin(2, "Sefu2", "987654321", "sefusefilor@tau.com", 10000, 2, "Support"));

        Odds odd4 = new Odds(4, "Peste 1.5 goluri", "Football");
        Odds odd5 = new Odds(5, "Sub 1.5 goluri", "Football");
        Odds odd6 = new Odds(6, "GG", "Football");
        oddsRepo.create(odd4);
        oddsRepo.create(odd5);
        oddsRepo.create(odd6);
        Map<Odds, Double> map4 = new HashMap<Odds, Double>();
        map4.put(odd4, 3.5);
        Map<Odds, Double> map5 = new HashMap<Odds, Double>();
        map5.put(odd5, 1.0);
        Map<Odds, Double> map6 = new HashMap<Odds, Double>();
        map6.put(odd6, 4.0);
        Event event4 = new Event(1, "Bayern vs BVB", map1, "11.11.2024", "Football");
        Event event5 = new Event(2, "Barcelona vs PSG", map2, "12.03.2024", "Football");
        Event event6 = new Event(3, "Real VS Madrid", map3, "03.03.2024", "Football");
        eventRepo.create(event4);
        eventRepo.create(event5);
        eventRepo.create(event6);

        Suport suport1 = new Suport(1, 1, "Problems when withdrawing", LocalDateTime.now(), "Active");
        Suport suport2 = new Suport(2, 2, "Problems when depositing", LocalDateTime.now(), "Ended");

        suportrepo.create(suport1);
        suportrepo.create(suport2);

        Statistics statistics1 = new Statistics(1,1, "The best Football Match in History", "Steaua has a 70% change of winning!");
        Statistics statistics2 = new Statistics(2,1, "The match will be fully booked", "UCluj will win!");

        statisticsrepo.create(statistics1);
        statisticsrepo.create(statistics2);
    }

    public static void main(String[] args) {
        int repoChoice = chooseTypeOfRepo();

        repo<Bet> betRepo;
        repo<Event> eventRepo;
        repo<Player> playerRepo;
        repo<Transactions> transactionsRepo;
        repo<Admin> adminRepo;
        repo<Odds> oddsRepo;
        repo<Suport> suportRepo;
        repo<Statistics> statisticsRepo;

        if (repoChoice == 1) {
            System.out.println("Using Files\n");
            String filePath = "/Users/gabimoldovan/Documents/Facultate/an2_sem1/MAP/Bet/MAP_Wettburo/src/main/java/Files/";

            betRepo = new FileRepository<>(filePath + "bets.txt");
            eventRepo = new FileRepository<>(filePath + "events.txt");
            playerRepo = new FileRepository<>(filePath + "players.txt");
            transactionsRepo = new FileRepository<>(filePath + "transactions.txt");
            adminRepo = new FileRepository<>(filePath + "admins.txt");
            oddsRepo = new FileRepository<>(filePath + "odds.txt");
            suportRepo = new FileRepository<>(filePath + "suport.txt");
            statisticsRepo = new FileRepository<>(filePath + "statistics.txt");
        }
        else if (repoChoice == 2) {
            System.out.println("Using Memory\n");
            betRepo = new inMemoryRepo<>();
            eventRepo = new inMemoryRepo<>();
            playerRepo = new inMemoryRepo<>();
            transactionsRepo = new inMemoryRepo<>();
            adminRepo = new inMemoryRepo<>();
            oddsRepo = new inMemoryRepo<>();
            suportRepo = new inMemoryRepo<>();
            statisticsRepo = new inMemoryRepo<>();
        }
        else {
            System.out.println("Invalid repo choice");
            return;
        }

        InitalizeRepo(eventRepo, betRepo, playerRepo, transactionsRepo, adminRepo, oddsRepo, suportRepo, statisticsRepo);

        CombinedConsoles combinedConsoles = new CombinedConsoles(betRepo, eventRepo, transactionsRepo, playerRepo, adminRepo, oddsRepo, suportRepo, statisticsRepo);

        combinedConsoles.showMainMenu();
    }
}
