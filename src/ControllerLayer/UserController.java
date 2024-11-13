package ControllerLayer;

import ModelLayer.Event;
import RepoLayerInterface.*;
import ModelLayer.Bet;
import ModelLayer.Player;
import ModelLayer.PlayerBet;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

/**
 * Controller class responsible for handling user-related actions such as viewing players, updating admin access levels,
 * viewing player bets, and creating new bet events.
 * It acts as an intermediary between the service layer and the user interface (or external input).
 */
public class UserController {
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
    public UserController(BetService betService, UserService userService) {
        this.betService = betService;
        this.userService = userService;
    }

    /**
     * Displays all available players in the system.
     *
     * @param player_id the ID of the player (not used in this method, but kept for potential future use)
     */
    public void viewPlayers(int player_id) {
        StringBuilder output = new StringBuilder("Available Players: ");
        userService.getAllPlayers().forEach(player -> output.append(player.toString()).append("/n"));
        System.out.println(output);
    }

    /**
     * Updates the access level of an admin.
     *
     * @param admin_id the ID of the admin to update
     * @param accesslevel the new access level for the admin
     */
    public void updateAdmin(int admin_id, int accesslevel) {
        userService.updateAccesLevelAdmin(admin_id, accesslevel);
        System.out.println("Admin " + admin_id + " updated to access level: " + accesslevel);
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

    /**
     * Creates a new bet event and adds it to the system.
     *
     * @param name the name of the new event
     * @param eventType the type of the event (e.g., sports, game, etc.)
     */
    public void new_betEvent(String name, String eventType) {
        betService.addEvent(name, eventType);
        System.out.println("New Bet Event for " + eventType + ": " + name + " will be available for betting soon!");
    }
}
