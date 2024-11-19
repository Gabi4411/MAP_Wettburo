package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class to manage betting operations.
 */
public class BetService {

    /** Repository for Bet objects. */
    private final repo<Bet> betRepo;

    /** Repository for Event objects. */
    private final repo<Event> eventRepo;

    /** Repository for Odds objects. */
    private final repo<FootballOdds> footballOddsRepo;

    private final repo<TennisOdds> tennisOddsRepo;

    private final repo<BasketOdds> basketOddsRepo;

    /**
     * Constructs a new BetService object.
     *
     * @param betRepo      Repository for Bet objects.
     * @param eventRepo    Repository for Event objects.
     * @param footballOddsRepo    Repository for Odds objects.
     */
    public BetService(repo<Bet> betRepo, repo<Event> eventRepo, repo<FootballOdds> footballOddsRepo, repo<TennisOdds> tennisOddsRepo, repo<BasketOdds> basketOddsRepo) {
        this.betRepo = betRepo;
        this.eventRepo = eventRepo;
        this.footballOddsRepo = footballOddsRepo;
        this.tennisOddsRepo = tennisOddsRepo;
        this.basketOddsRepo = basketOddsRepo;
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
        if (betRepo.getAll().isEmpty()) {
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

        if (eventRepo.getAll().isEmpty()) {
            lastEvent = 0;
        } else {
            lastEvent = eventRepo.getAll().getLast().getEvent_id();
        }

        if (Objects.equals(eventType, "Football")) {
            FootballOdds footballOdds = footballOddsRepo.getAll().getFirst();
            List<Double> oddList = footballOdds.getOdd_value();
            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
            eventRepo.create(newEvent);
        }
        else if (Objects.equals(eventType, "Tennis")) {
            TennisOdds tennisOdds = tennisOddsRepo.getAll().getFirst();
            List<Double> oddList = tennisOdds.getOdd_value();
            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
            eventRepo.create(newEvent);
        }
        else if (Objects.equals(eventType, "Basket")) {
            BasketOdds basketOdds = basketOddsRepo.getAll().getFirst();
            List<Double> oddList = basketOdds.getOdd_value();
            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
            eventRepo.create(newEvent);
        }
    }

    public void addOdds(List<Double> odds, String eventType, String type) {
        if (Objects.equals(type, "Football")) {
            FootballOdds FootballOdd = new FootballOdds(odds, eventType);
            footballOddsRepo.create(FootballOdd);
        }
        else if (Objects.equals(type, "Tennis")) {
            TennisOdds TennisOdd = new TennisOdds(odds, eventType);
            tennisOddsRepo.create(TennisOdd);
        }
        else if (Objects.equals(type, "Basket")) {
            BasketOdds BasketOdd = new BasketOdds(odds, eventType);
            basketOddsRepo.create(BasketOdd);
        }
    }

    public List<Event> getAvailableEvents() {
        return eventRepo.getAll();
    }

    public List<FootballOdds> getFootballOdds() {
        return footballOddsRepo.getAll();
    }

    public List<TennisOdds> getTennisOdds() {
        return tennisOddsRepo.getAll();
    }

    public List<BasketOdds> getBasketOdds() {
        return basketOddsRepo.getAll();
    }




    public List<Event> filterbySportsType(List<Event> events, String type) {
        return events.stream().filter(event -> event.getSports_type().equals(type)).collect(Collectors.toList());
    }

    public List<Odds> filterbyOdds(List<Odds> odds, double value) {
            return odds.stream().filter(odd -> odd.getOdd_value().equals(value)).collect(Collectors.toList());
    }
}

