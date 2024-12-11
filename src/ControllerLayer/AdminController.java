package ControllerLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.Event;
import ModelLayer.Player;
import ModelLayer.Suport;
import ServiceLayer.BetService;
import ServiceLayer.UserService;
import Exceptions.CustomExceptions;

public class AdminController {
    private final UserService userService;
    private final BetService betService;

    public AdminController(UserService userService, BetService betService) {
        this.userService = userService;
        this.betService = betService;
    }

    public void adminLogin(String username, String password) {
        if (!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password)) {
            System.exit(0);
        }

        if (userService.AdminLogin(username, password)) {
            System.out.println("Admin logged in, enjoy!\n");
        }
        else {
            System.out.println("Couldn't find Admin, please create account first!\n");
        }
    }

    public boolean createAdminAccount(String username, String password, String email) {
        if (!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password) || !CustomExceptions.checkIfEmpty(email)) {
            System.exit(0);
        }

        if(userService.addAdmin(username, password, email)) {
            System.out.println("Admin account created!\n");
            return true;
        }
        else {
            System.out.println("Couldn't create new account, because one already exist with this username or email. Please try again!\n");
            return false;
        }
    }

    public void createEvent(String eventName, String eventType, String eventDate,  String oddName, Double value) {
        if (!CustomExceptions.checkIfEmpty(eventName) || !CustomExceptions.checkIfEmpty(eventType)) {
            System.exit(0);
        }

        betService.addEvent(eventName, eventType, eventDate, oddName, value);
        System.out.println("New Bet Event for " + eventType + ": " + eventName + " will be available for betting soon!\n");
    }

    public void viewPlayers() {
        StringBuilder output = new StringBuilder("Available Players: \n");
        userService.getAllPlayers().forEach(player -> output.append(player.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewEvents() {
        StringBuilder output = new StringBuilder("Available Events: \n");
        betService.getAvailableEvents().forEach(event -> output.append(event.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewOdds() {
        StringBuilder output = new StringBuilder("Available Odds: \n");
        betService.getAvailableOdds().forEach(event -> output.append(event.toString()).append("\n"));
    }

    public void viewBets() {
        StringBuilder output = new StringBuilder("Available Bets: \n");
        betService.getAvailableBets().forEach(bet -> output.append(bet.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewAdmins() {
        StringBuilder output = new StringBuilder("Available Admins: \n");
        userService.getAllAdmins().forEach(admin -> output.append(admin.toString()).append("\n"));
        System.out.println(output);
    }

    public void updateAdmin(Integer adminId, int accesLevel) {
        if (!CustomExceptions.checkIfEmpty(adminId) || !CustomExceptions.idCheck(adminId)) {
            System.exit(0);
        }

        userService.updateAccesLevelAdmin(adminId, accesLevel);
        System.out.println("Admin " + adminId + " updated to access level: " + accesLevel + "\n");
    }

    public void sortEventsByDateController(boolean ascending) {
        List<Event> events = betService.getAvailableEvents();
        List<Event> events1 = betService.sortEventsByDate(events, ascending);
        System.out.println("Events sorted by date (" + (ascending ? "earliest to latest" : "latest to earliest") + "):\n");
        events1.forEach(event1 -> System.out.println(event1.getEvent_date() + " - " + event1.getEvent_name() + "\n"));
    }

    public void sortPlayersByNameController(boolean ascending) {
        List<Player> players = userService.getAllPlayers();
        List<Player> players1 = betService.sortPlayersByName(players, ascending);
        System.out.println("Players sorted by name (" + (ascending ? "A-Z" : "Z-A") + "):\n");
        players1.forEach(player1 -> System.out.println(player1.getUser_name() + " - " + player1.getEmail() + "\n"));
    }

    public void whatProblemsAreActive(List<Integer> playersIds) {
        List<Integer> allProblems = new ArrayList<>();

        for (Integer playerId : playersIds) {
            if(userService.getSupportStatus(playerId)) {
                allProblems.add(playerId);
            }
        }

        System.out.println("This are the players who asked for help in Support:" + allProblems + "\n");
    }

    public void addStatistic(int eventId, String eventDescription, String eventPrediction) {
        if(!CustomExceptions.checkIfEmpty(eventId) || !CustomExceptions.checkIfEmpty(eventDescription) || !CustomExceptions.checkIfEmpty(eventPrediction)) {
            System.exit(0);
        }

        if (userService.createStatistic(eventId, eventDescription, eventPrediction)) {
            System.out.println("Statistic added!\n");
        }
        else {
            System.out.println("Statistic already exists for this event!\n");
        }
    }
}
