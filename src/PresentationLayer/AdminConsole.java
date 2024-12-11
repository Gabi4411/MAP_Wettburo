package PresentationLayer;

import ControllerLayer.AdminController;
import ModelLayer.*;
import RepoLayerInterface.*;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;


public class AdminConsole {
    private final AdminController adminController;

    public AdminConsole(AdminController adminController) {
        this.adminController = adminController;
    }

    public void welcomeMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
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
        System.out.println("Give me the event id:");
        int eventId = Integer.parseInt(scanner.nextLine());
        System.out.println("Give me the desciption:");
        String desc = scanner.nextLine();
        System.out.println("Give me the prediction:");
        String prediction = scanner.nextLine();
        adminController.addStatistic(eventId, desc, prediction);
        showMenu(scanner);
    }
}
