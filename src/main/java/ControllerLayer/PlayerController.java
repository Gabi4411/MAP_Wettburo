package ControllerLayer;

import ModelLayer.*;
import ServiceLayer.*;
import Exceptions.CustomExceptions;

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
        if (CustomExceptions.checkIfEmpty(player_id) || CustomExceptions.idCheck(player_id)) {
            System.out.println("Wrong player id! Please try again!\n");
        }

        StringBuilder output = new StringBuilder("Available Bets: \n");
        betService.getAvailableBets().forEach(bet -> output.append(bet.toString()).append("\n"));
        System.out.println(output);
    }



    public void viewEvents() {
        StringBuilder output = new StringBuilder("Available Events: \n");
        betService.getAvailableEvents().forEach(event -> output.append(event.toString()).append("\n"));
        System.out.println(output);
    }


    public void withdraw(String username, String password, Integer amount) {
        if (CustomExceptions.checkIfEmpty(username) || CustomExceptions.checkIfEmpty(password) || CustomExceptions.checkIfEmpty(amount)) {
            System.out.println("Wrong username or password! Please try again!\n");
        }

        if(userService.withdraw(username, password, amount)) {
            System.out.println("Withdraw Successful\n");
        }
        else {
            System.out.println("Withdraw Failed\n");
        }
    }

    public void deposit(String username, String password, Integer amount) {
        if (CustomExceptions.checkIfEmpty(username) || CustomExceptions.checkIfEmpty(password) || CustomExceptions.checkIfEmpty(amount)) {
            System.out.println("Wrong username or password! Please try again!\n");
        }

        if(userService.deposit(username, password, amount)) {
            System.out.println("Deposit Successful\n");
        }
        else {
            System.out.println("Deposit Failed\n");
        }
    }

    public boolean playerLogin(String username, String password) {
        if (CustomExceptions.checkIfEmpty(username) || CustomExceptions.checkIfEmpty(password)) {
            System.out.println("Wrong data input! Please try again!\n");
            return false;
        }
        else if(userService.Login(username, password)) {
            System.out.println("Logged in, have fun!\n");
            return true;
        }
        else {
            System.out.println("Wrong password, try again, or create account if you don't have one!\n");
            return false;
        }
    }

    public boolean createPlayerAccount(String username, String password, String email) {
        if(CustomExceptions.checkIfEmpty(username) || CustomExceptions.checkIfEmpty(password)) {
            System.out.println("Wrong input! Try again!\n");
            return false;
        }

        if(userService.addPlayer(username, password, email)) {
            System.out.println("Logged in, have fun!\n");
        }
        else {
            System.out.println("Couldn't create new account, because one already exist with this username or email. Please try again!\n");
        }
        return false;
    }

    public void getTransactions() {
        StringBuilder output = new StringBuilder("All Transactions: \n");
        userService.getAllTransactions().forEach(transaction -> output.append(transaction.toString()).append("\n"));
        System.out.println(output);
    }

    public void viewBetHistory(int playerID){
        if (CustomExceptions.checkIfEmpty(playerID) || CustomExceptions.idCheck(playerID)) {
            System.out.println("Wrong player id! Please try again!\n");
        }
        else {
            String bethistory = betService.getPlayerBetHistory(playerID);
            System.out.println(bethistory);
        }
    }

    public void transactionFilter(String type){
        List<Transactions> transactions = betService.getAvailableTransaction();
        List<Transactions> filteredTransaction = betService.filterbyTransactionType(transactions,type);
        StringBuilder output = new StringBuilder("Filtered Transactions: ");
        filteredTransaction.forEach(transacation -> output.append(transacation.getTransaction_type().toString())
                .append(transacation.getTransaction_status().toString())
                .append("\n"));
        System.out.println(output);
    }

    public void SportTypeFilter(String type){
        List<Event> events = betService.getAvailableEvents();
        StringBuilder output = new StringBuilder("SportsType: \n");
        List<Event> filteredSportstype = betService.filterbySportsType(events,type);
        filteredSportstype.forEach(event -> output.append(event.getEvent_name().toString())
                .append(event.getSports_type())
                .append("\n"));

        System.out.println(output);

    }

    public void placeNewBet(int playerID) {
//        if (CustomExceptions.idCheck(playerID)) {
//            System.out.println("Wrong player id! Please try again!\n");
//        }
//        else {
            betService.placeBet(playerID);
            System.out.println("Bet has been created. You can see it from now on in your Bet History! Thank you for your Bet!\n");
//        }
    }
}
