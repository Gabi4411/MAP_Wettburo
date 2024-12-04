package ControllerLayer;

import ModelLayer.*;
import ServiceLayer.*;
import Exceptions.CustomExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for handling user-related actions such as viewing players, updating admin access levels,
 * viewing player bets, and creating new bet events.
 * It acts as an intermediary between the service layer and the user interface (or external input).
 */
public class PlayerController {
    /**
     * Service for handling betting-related operations.
     */
    private final BetService betService;

    /**
     * Service for handling user-related operations, including players and admins.
     */
    private final UserService userService;

    /**
     * Constructs a UserController with the specified BetService and UserService.
     *
     * @param betService the BetService used for handling bets and events
     * @param userService the UserService used for handling user operations
     */
    public PlayerController(BetService betService, UserService userService) {
        this.betService = betService;
        this.userService = userService;
    }

    /**
     * Displays all available bets for a specific player.
     *
     * @param player_id the ID of the player whose bets are to be displayed
     */
    public void viewPlayerBets(Integer player_id) {
        if (!CustomExceptions.checkIfEmpty(player_id)) {
            System.exit(0);
        }

        StringBuilder output = new StringBuilder("Available Bets: \n");
        betService.getAvailableBets().forEach(bet -> output.append(bet.toString()).append("\n"));
        System.out.println(output);
    }

    public void view_Bet_Odd(Integer betID){
        if (!CustomExceptions.checkIfEmpty(betID)) {
            System.exit(0);
        }

        StringBuilder output = new StringBuilder("Bets Odds: \n");
        betService.calculateOdd(betID);
        System.out.println(output);
    }

    public void viewEvents() {
        StringBuilder output = new StringBuilder("Available Events: \n");
        betService.getAvailableEvents().forEach(event -> output.append(event.toString()).append("\n"));
        System.out.println(output);
    }


    public void withdraw(String username, String password, Integer amount) {
        if (!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password) || !CustomExceptions.checkIfEmpty(amount)) {
            System.exit(0);
        }

        if(userService.withdraw(username, password, amount)) {
            System.out.println("Withdraw Successful\n");
        }
        else {
            System.out.println("Withdraw Failed\n");
        }
    }

    public void deposit(String username, String password, Integer amount) {
        if (!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password) || !CustomExceptions.checkIfEmpty(amount)) {
            System.exit(0);
        }

        if(userService.deposit(username, password, amount)) {
            System.out.println("Deposit Successful\n");
        }
        else {
            System.out.println("Deposit Failed\n");
        }
    }

    public void playerLogin(String username, String password) {
        if (!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password)) {
            System.exit(0);
        }

        if(userService.Login(username, password)) {
            System.out.println("Logged in, have fun!\n");
        }
        else {
            System.out.println("Wrong password, try again, or create account if you don't have one!\n");
        }
    }

    public void createPlayerAccount(String username, String password, String email) {
        if(!CustomExceptions.checkIfEmpty(username) || !CustomExceptions.checkIfEmpty(password)) {
            System.exit(0);
        }

        if(userService.addPlayer(username, password, email)) {
            System.out.println("Logged in, have fun!\n");
        }
        else {
            System.out.println("Couldn't create new account, because one already exist with this username or email. Please try again!\n");
        }
    }

    public void getTransactions() {
        StringBuilder output = new StringBuilder("All Transactions: \n");
        userService.getAllTransactions().forEach(transaction -> output.append(transaction.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewBetHistory(int playerID){
        if (!CustomExceptions.checkIfEmpty(playerID)) {
            System.exit(0);
        }

        String bethistory = betService.getPlayerBetHistory(playerID);
        System.out.println(bethistory);
    }

    public void oddFilter(){
        List<FootballOdds> odds = betService.getFootballOdds();
        String type = "Peste 5";
        List<FootballOdds> filteredOdds = betService.filterbyOdds(odds,type);
        StringBuilder output = new StringBuilder("Filtered Odds: ");
        filteredOdds.forEach(odd -> output.append(odd.getEventType().toString())
                .append("\n"));
        System.out.println(output);
    }

    public void SportTypeFilter(){
        List<Event> events = betService.getAvailableEvents();
        String type = "Football";
        StringBuilder output = new StringBuilder("SportsType: \n");
        List<Event> filteredSportstype = betService.filterbySportsType(events,type);
        filteredSportstype.forEach(event -> output.append(event.getEvent_name().toString())
                .append(event.getSports_type())
                .append("\n"));

        System.out.println(output);

    }

    public void placeBet(int playerID, List<Event> events, int amount) {
        if (!CustomExceptions.checkIfEmpty(playerID) || !CustomExceptions.checkIfEmpty(events) || !CustomExceptions.checkIfEmpty(amount)) {
            System.exit(0);
        }

        betService.createBet(playerID, events, amount);
        System.out.println("Bet has been created. You can see it from now on in your Bet History! Thank you for your Bet!\n");
    }
}
