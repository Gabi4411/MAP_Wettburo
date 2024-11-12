package ServiceLayer;
import ModelLayer.*;
import RepoLayerInterface.*;

import java.util.Date;
import java.util.List;


public class BetService{

    private final repo<Bet> betRepo;
    private final repo<EventOdds> eventOddsRepo;


    public BetService(repo<Bet> betRepo, repo<EventOdds> eventOddsRepo) {
        this.betRepo = betRepo;
        this.eventOddsRepo = eventOddsRepo;
    }
    /**
     * calculeaza cota biletului dupa ce au fost introduse eventuri cu cote specifice
    */
    public void calculateOdd(Integer betID){
        Bet bet = betRepo.get(betID);
        if (bet == null){
            System.out.println("Bet Not Found");
        }

        double totalOdds = 1.0;

        for(EventOdds eventOdds : bet.getEvent_odds()){
            double odd = eventOdds.getOdds();
            totalOdds *= odd;
        }
        bet.setTotal_odd(totalOdds);
        betRepo.update(bet);

    }



    public double calculate_PotentialWinning(Integer betID){

        Bet bet = betRepo.get(betID);
        if (bet == null){
            System.out.println("Bet Not Found");
        }

        double potentialWin = bet.getTotal_odd() * bet.getAmount();
        return potentialWin;

    }

    public List<Bet> getAvailableBets(){return betRepo.getAll();}

    public boolean isValidBet(Integer betID){

        Bet bet = betRepo.get(betID);
        if (bet.getAmount() <= 0) {
            System.out.println("Sum cant be 0.");
            return false;
        }

        for (EventOdds eventOdds : bet.getEvent_odds()){
            Event event = eventOdds.getEvent();
            Date event_date = event.getEvent_date();
            Date current_date = new Date();
             if(event_date.before(current_date)){
                 System.out.println("Event has already started");
                 return false;
             }
        }


        EventOdds eventOdds = eventOddsRepo.get(betID);
        if(!eventOdds.getStatus()){
            System.out.println("Event Odd failed, Bet Lost");
            return false;
        }

        return true;

    }


}
