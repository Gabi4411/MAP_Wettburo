package ControllerLayer;

import ServiceLayer.BetService;
import ServiceLayer.UserService;

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
        StringBuilder output = new StringBuilder("Available Bets: ");
        betService.getAvailableBets().forEach(bet -> output.append(bet.toString()).append("/n"));
        System.out.println(output);
    }

    public void view_Bet_Odd(Integer betID){
        StringBuilder output = new StringBuilder("Bets Odds: ");
        betService.calculateOdd(betID);
        System.out.println(output);
    }

    public void viewEvents() {
        StringBuilder output = new StringBuilder("Available Events: ");
        betService.getAvailableEvents().forEach(event -> output.append(event.toString()).append("/n"));
        System.out.println(output);
    }

    public void createBet() {}

    public void withdraw(String username, String password, Integer amount) {
        if(userService.withdraw(username, password, amount)) {
            System.out.println("Withdraw Successful");
        }
        else {
            System.out.println("Withdraw Failed");
        }
    }

    public void deposit(String username, String password, Integer amount) {
        if(userService.deposit(username, password, amount)) {
            System.out.println("Deposit Successful");
        }
        else {
            System.out.println("Deposit Failed");
        }
    }

    public void playerLogin(String username, String password) {
        if(userService.Login(username, password)) {
            System.out.println("Logged in, have fun!");
        }
        else {
            System.out.println("Wrong password, try again, or create account if you don't have one!");
        }
    }

    public void createPlayerAccount(String username, String password, String email) {
        if(userService.addPlayer(username, password, email)) {
            System.out.println("Logged in, have fun!");
        }
        else {
            System.out.println("Couldn't create new account, because one already exist with this username or email. Please try again!");
        }
    }

    public void getTransactions() {
        StringBuilder output = new StringBuilder("All Transactions: ");
        userService.getAllTransactions().forEach(transaction -> output.append(transaction.toString()).append("/n"));
        System.out.println(output);
    }
}