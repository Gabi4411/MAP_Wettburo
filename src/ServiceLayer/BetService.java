package ServiceLayer;
import ModelLayer.*;
import RepoLayerInterface.*;

import java.time.LocalDateTime;
import java.util.List;


public class BetService{

    private final repo<Bet> betRepo;
    private final repo<Event> eventRepo;
    private final repo<Odds> oddsRepo;


    public BetService(repo<Bet> betRepo, repo<Event> eventRepo, repo<Odds> oddsRepo) {
        this.betRepo = betRepo;
        this.eventRepo = eventRepo;
        this.oddsRepo = oddsRepo;
    }
    /**
     * calculeaza cota biletului dupa ce au fost introduse eventuri cu cote specifice
    */
    public double calculateOdd(Integer betID){
        Bet bet = betRepo.get(betID);
        if (bet == null){
            System.out.println("Bet Not Found");
        }

        double totalOdds = 1.0;

        for(Event event : bet.getEvent()){
            List<Double> odds = event.getOdds();
            for(double odd : odds){
                totalOdds *= odd;
            }
        }
        return totalOdds;
    }



    public double calculatePotentialWinning(Integer betID){
        Bet bet = betRepo.get(betID);
        if (bet == null){
            System.out.println("Bet Not Found");
        }

        double potentialWin = calculateOdd(betID) * bet.getAmount();
        return potentialWin;

    }

    public List<Bet> getAvailableBets(){return betRepo.getAll();}

    public boolean isValidBet(Integer betID){

        Bet bet = betRepo.get(betID);
        if (bet.getAmount() <= 0) {
            System.out.println("Sum cant be 0.");
            return false;
        }

        return true;

    }

//    public void updateBalance(int playerID) {}

//    public void createOdds (int event_id, List<Double> odd_value, String eventType) {
//        int lastOdds = oddsRepo.getAll().getLast().getOdd_id();
//        Odds newOdd = new Odds(lastOdds + 1, odd_value, eventType);
//        oddsRepo.create(newOdd);
//    }

    public void addBet(List<Event> event, int amount) {
        int lastBet = betRepo.getAll().getLast().getBet_id();
        Bet newBet = new Bet(lastBet + 1, event, amount, LocalDateTime.now());
        betRepo.create(newBet);
    }

    public void addEvent(String eventName, String eventType) {
        int lastEvent = eventRepo.getAll().getLast().getEvent_id();
        Odds odds = null;
        for (Odds odd : oddsRepo.getAll()){
            if (odd.getEventType().equals(eventType)){
                odds = odd;
            }
        }
        List<Double> oddList = odds.getOdd_value();
        Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
        eventRepo.create(newEvent);
    }

}
