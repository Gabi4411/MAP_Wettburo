package PresentationLayer;

import ControllerLayer.AdminController;
import RepoLayerInterface.inMemoryRepo;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import javax.swing.plaf.synth.SynthTextAreaUI;
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
        adminController.sortEventsByDateController();
        showMenu(scanner);
    }

    private void sortedPlayersByName(Scanner scanner) {
        adminController.sortPlayersByNameController();
        showMenu(scanner);
    }

    public static void main(String[] args) {
        //Repositories for inMemory
        inMemoryRepo<ModelLayer.Bet> betRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Event> eventRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Player> playerRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.FootballOdds> footballOddsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.TennisOdds> tennisOddsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.BasketOdds> basketOddsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Transactions> transactionsRepo = new inMemoryRepo<>();
        inMemoryRepo<ModelLayer.Admin> adminRepo = new inMemoryRepo<>();

        //Create service objects
        BetService betService = new BetService(betRepo, eventRepo, footballOddsRepo, tennisOddsRepo, basketOddsRepo, playerRepo);
        UserService userService = new UserService(playerRepo, adminRepo, transactionsRepo);

        //Create Admin Controller and Console Object
        AdminController adminController1 = new AdminController(userService, betService);
        AdminConsole adminConsole = new AdminConsole(adminController1);

        adminConsole.welcomeMenu();
    }
}
