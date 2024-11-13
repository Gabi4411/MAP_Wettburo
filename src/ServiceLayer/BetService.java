package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage betting operations.
 */
public class BetService {

    /** Repository for Bet objects. */
    private final repo<Bet> betRepo;

    /** Repository for Event objects. */
    private final repo<Event> eventRepo;

    /** Repository for Odds objects. */
    private final repo<Odds> oddsRepo;

    /** Repository for PlayerBet objects. */
    private final repo<PlayerBet> playerBetrepo;

    /**
     * Constructs a new BetService object.
     *
     * @param betRepo      Repository for Bet objects.
     * @param eventRepo    Repository for Event objects.
     * @param oddsRepo     Repository for Odds objects.
     * @param playerBetrepo Repository for PlayerBet objects.
     */
    public BetService(repo<Bet> betRepo, repo<Event> eventRepo, repo<Odds> oddsRepo, repo<PlayerBet> playerBetrepo) {
        this.betRepo = betRepo;
        this.eventRepo = eventRepo;
        this.oddsRepo = oddsRepo;
        this.playerBetrepo = playerBetrepo;
    }

    /**
     * Calculates the total odds for a given bet.
     *
     * @param betID The ID of the bet to calculate odds for.
     * @return The total odds, or 1.0 if the bet is not found.
     */
    public double calculateOdd(Integer betID) {
        Bet bet = betRepo.get(betID);
        if (bet == null) {
            System.out.println("Bet Not Found");
            return 1.0;
        }

        double totalOdds = 1.0;
        for (Event event : bet.getEvent()) {
            List<Double> odds = event.getOdds();
            for (double odd : odds) {
                totalOdds *= odd;
            }
        }
        return totalOdds;
    }

    /**
     * Calculates the potential winnings for a given bet.
     *
     * @param betID The ID of the bet to calculate potential winnings for.
     * @return The potential winnings, or 0 if the bet is not found.
     */
    public double calculatePotentialWinning(Integer betID) {
        Bet bet = betRepo.get(betID);
        if (bet == null) {
            System.out.println("Bet Not Found");
            return 0;
        }

        return calculateOdd(betID) * bet.getAmount();
    }

    /**
     * Retrieves a list of all available bets.
     *
     * @return A list of available bets.
     */
    public List<Bet> getAvailableBets() {
        return betRepo.getAll();
    }

    /**
     * Validates a bet by checking if the bet amount is greater than zero.
     *
     * @param betID The ID of the bet to validate.
     * @return True if the bet is valid, false otherwise.
     */
    public boolean isValidBet(Integer betID) {
        Bet bet = betRepo.get(betID);
        if (bet.getAmount() <= 0) {
            System.out.println("Sum can't be 0.");
            return false;
        }
        return true;
    }

    /**
     * Adds a new bet to the repository.
     *
     * @param event  The list of events associated with the bet.
     * @param amount The amount of the bet.
     */
    public void addBet(List<Event> event, int amount) {
        int lastBet;
        if (betRepo.getAll().size() == 0) {
            lastBet = 0;
        } else {
            lastBet = betRepo.getAll().getLast().getBet_id();
        }
        Bet newBet = new Bet(lastBet + 1, event, amount, LocalDateTime.now());
        betRepo.create(newBet);
    }


    /**
     * Adds a new event to the repository.
     *
     * @param eventName The name of the event.
     * @param eventType The type of the event.
     */
    public void addEvent(String eventName, String eventType) {
        int lastEvent;

        if (eventRepo.getAll().size() == 0) {
            lastEvent = 0;
        } else {
            lastEvent = eventRepo.getAll().getLast().getEvent_id();
        }

        Odds odds = null;
        for (Odds odd : oddsRepo.getAll()) {
            if (odd.getEventType().equals(eventType)) {
                odds = odd;
            }
        }
        if (odds != null) {
            List<Double> oddList = odds.getOdd_value();
            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
            eventRepo.create(newEvent);
        }
    }
}

