package ControllerLayer;

import java.util.List;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

public class AdminController {
    private final UserService userService;
    private final BetService betService;

    public AdminController(UserService userService, BetService betService) {
        this.userService = userService;
        this.betService = betService;
    }

    public void adminLogin(String username, String password) {
        if (userService.AdminLogin(username, password)) {
            System.out.println("Admin logged in, enjoy!");
        }
        else {
            System.out.println("Couldn't find Admin, please create account first!");
        }
    }

    public void createAdminAccount(String username, String password, String email) {
        if(userService.addAdmin(username, password, email)) {
            System.out.println("Admin account created!");
        }
        else {
            System.out.println("Couldn't create new account, because one already exist with this username or email. Please try again!");
        }
    }

    public void createEvent(String eventName, String eventType) {
        betService.addEvent(eventName, eventType);
        System.out.println("New Bet Event for " + eventType + ": " + eventName + " will be available for betting soon!");
    }

    public void createOdds(List<Double> odds, String eventType, String type) {}

    public void viewPlayers() {
        StringBuilder output = new StringBuilder("Available Players: ");
        userService.getAllPlayers().forEach(player -> output.append(player.toString()).append("/n"));
        System.out.println(output);
    }

    public void viewEvents() {
        StringBuilder output = new StringBuilder("Available Events: ");
        betService.getAvailableEvents().forEach(event -> output.append(event.toString()).append("/n"));
        System.out.println(output);
    }

    public void viewOdds() {
        StringBuilder output = new StringBuilder("Available Odds: ");
        betService.getBasketOdds().forEach(odd -> output.append(odd.toString()).append("/n"));
        System.out.println(output);
        betService.getFootballOdds().forEach(odd -> output.append(odd.toString()).append("/n"));
        System.out.println(output);
        betService.getTennisOdds().forEach(odd -> output.append(odd.toString()).append("/n"));
        System.out.println(output);
    }

    public void viewBets() {
        StringBuilder output = new StringBuilder("Available Bets: ");
        betService.getAvailableBets().forEach(bet -> output.append(bet.toString()).append("/n"));
        System.out.println(output);
    }

    public void viewAdmins() {
        StringBuilder output = new StringBuilder("Available Admins: ");
        userService.getAllAdmins().forEach(admin -> output.append(admin.toString()).append("/n"));
        System.out.println(output);
    }

    public void updateAdmin(Integer adminId, int accesLevel) {
        userService.updateAccesLevelAdmin(adminId, accesLevel);
        System.out.println("Admin " + adminId + " updated to access level: " + accesLevel);
    }
}
