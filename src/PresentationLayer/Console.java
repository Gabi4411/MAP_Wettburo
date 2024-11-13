package PresentationLayer;
import java.util.Scanner;
import java.util.stream.IntStream;
import RepoLayerInterface.*;
import ModelLayer.*;
import ServiceLayer.*;
import ControllerLayer.*;
public class Console {
    private final  UserController userController;

    public Console(UserController userController) {
        this.userController = userController;
    }

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
                case 2 -> userController.updateAdmin(getAdminId(scanner),getAccessLevel(scanner));
                case 3 -> userController.viewPlayerBets(getPlayerId(scanner));
                case 4 -> userController.new_betEvent(getEventID(scanner),getEventType(scanner));
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getPlayerId(Scanner scanner) {
        System.out.println("Enter Player ID: ");
        return Integer.parseInt(scanner.nextLine());
    }
    private static String getEventID(Scanner scanner) {
        System.out.print("Enter Event ID: ");
        return scanner.nextLine();
    }
    private static String getEventType(Scanner scanner) {
        System.out.print("Enter Event Type: ");
        return scanner.nextLine();
    }


    private static Integer getAdminId(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int getAccessLevel(Scanner scanner) {
        System.out.print("Enter New Access Level: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) {
        inMemoryRepo<ModelLayer.Bet> betRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Event> eventRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Odds> oddsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.PlayerBet> playerbetRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Admin> adminRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Transactions> transactionsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Player> playerRepo = new inMemoryRepo<>();

        BetService betService = new BetService(betRepo, eventRepo, oddsRepo, playerbetRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        UserController userController1 = new UserController(betService,userService);
        Console console = new Console(userController1);


        console.displayMenu();


    }
}


