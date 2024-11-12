package ServiceLayer;
import ModelLayer.*;
import RepoLayerInterface.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class BetService{

    private final repo<Bet> betRepo;
    private final repo<EventOdds> eventOddsRepo;
    private final repo<Event> eventRepo;


    public BetService(repo<Bet> betRepo, repo<EventOdds> eventOddsRepo , repo<Event> eventRepo) {
        this.betRepo = betRepo;
        this.eventOddsRepo = eventOddsRepo;
        this.eventRepo = eventRepo;
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



    public double calculatePotentialWinning(Integer betID){
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
            LocalDateTime event_date = event.getEvent_date();
            LocalDateTime current_date = LocalDateTime.now();
             if(event_date.isBefore(current_date)){
                 System.out.println("Event has already started");
                 return false;
             }
        }

        return true;

    }

    public void betWon(Integer betID) {
        EventOdds eventOdds = eventOddsRepo.get(betID);
        if (!eventOdds.getStatus()) {
            System.out.println("Event Odd failed, Bet Lost");
        }else{
            Bet bet = betRepo.get(betID);
            double amount = calculatePotentialWinning(betID);
            bet.getPlayer().setBalance(bet.getPlayer().getBalance()+amount);
        }
    }

    public void addBet() {

    }

    public void addEvent(String eventName, List<Odds> odds, String type) {
        int lastEvent = eventRepo.getAll().getLast().getEvent_id();
        Event newEvent = new Event(lastEvent + 1, eventName, odds, LocalDateTime.now(), type);
        eventRepo.create(newEvent);
    }

    public void removeEvent(int eventID) {
        eventRepo.delete(eventID);
    }


}
