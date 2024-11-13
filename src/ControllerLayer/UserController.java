package ControllerLayer;
import ModelLayer.Event;
import RepoLayerInterface.*;
import ModelLayer.Bet;
import ModelLayer.Player;
import ModelLayer.PlayerBet;
import ServiceLayer.BetService;
import ServiceLayer.UserService;

public class UserController {
    private final BetService betService;
    private final UserService userService;

    public UserController(BetService betService, UserService userService) {
        this.betService = betService;
        this.userService = userService;
    }

    public void viewPlayers(int player_id){
        StringBuilder output = new StringBuilder("Available Players: ");
        userService.getAllPlayers().forEach(player -> output.append(player.toString()).append("/n"));
        System.out.println(output);
    }

    public void updateAdmin(int admin_id,int accesslevel){
        userService.updateAccesLevelAdmin(admin_id,accesslevel);
        System.out.println("Admin"+admin_id+"updated to acces Level:"+accesslevel);
    }

    public void viewPlayerBets(Integer player_id){
        StringBuilder output = new StringBuilder("Available Bets: ");
        betService.getAvailableBets().forEach(player -> output.append(player.toString()).append("/n"));
        System.out.println(output);
    }

    public void new_betEvent(String name, String eventType){
        betService.addEvent(name,eventType);
        System.out.println("New Bet EventType for"+eventType+name+"will be available for betting  soon!: ");
    }

}

