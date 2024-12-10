package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class to manage betting operations.
 */
public class BetService {

    /**
     * Repository for Bet objects.
     */
    private final repo<Bet> betRepo;

    private final repo<Transactions> transactionsRepo;;

    private final repo<Player> playerRepo;

    /**
     * Repository for Event objects.
     */
    private final repo<Event> eventRepo;

    private final repo<Odds> oddsRepo;

    /**
     * Repository for Odds objects.
     */
//    private final repo<FootballOdds> footballOddsRepo;
//
//    private final repo<TennisOdds> tennisOddsRepo;
//
//    private final repo<BasketOdds> basketOddsRepo;

    /**
     * Constructs a new BetService object.
     *
     * @param betRepo          Repository for Bet objects.
     * @param eventRepo        Repository for Event objects.
     * //@param footballOddsRepo Repository for Odds objects.
     */
    public BetService(repo<Bet> betRepo, repo<Event> eventRepo,repo<Transactions> transactionsRepo/* ,repo<FootballOdds> footballOddsRepo, repo<TennisOdds> tennisOddsRepo, repo<BasketOdds> basketOddsRepo*/, repo<Player> playerRepo, repo<Odds> oddsRepo) {
        this.betRepo = betRepo;
        this.eventRepo = eventRepo;
//        this.footballOddsRepo = footballOddsRepo;
//        this.tennisOddsRepo = tennisOddsRepo;
//        this.basketOddsRepo = basketOddsRepo;
        this.transactionsRepo = transactionsRepo;
        this.playerRepo = playerRepo;
        this.oddsRepo = oddsRepo;
    }
//
//    /**
//     * Calculates the total odds for a given bet.
//     *
//     * @param betID The ID of the bet to calculate odds for.
//     * @return The total odds, or 1.0 if the bet is not found.
//     */
//    public double calculateOdd(Integer betID) {
//        Bet bet = betRepo.get(betID);
//        if (bet == null) {
//            System.out.println("Bet Not Found");
//            return 1.0;
//        }
//
//        double totalOdds = 1.0;
//        for (Event event : bet.getEvent()) {
//            List<Odds> odds = event.getOddsList();
//            for (Odds odd : odds) {
//                odd = totalOdds * odd
//            }
//        }
//        return totalOdds;
//    }


//    public double calculateTotalOdds(Map<Event, Odds> selectedBets) {
//        double totalOdds = 1.0;
//
//        for (Odds odd : selectedBets.values()) {
//            for (double singleOdd : odd.getOddValue()) { // Assuming getOddValue() returns List<Double>
//                totalOdds *= singleOdd; // Multiply each odd value into totalOdds// Assuming `getOddValue()` retrieves the odd's numerical value
//            }
//        }
//        return totalOdds;
//    };


    /**
     * Calculates the potential winnings for a given bet.
     *
     * @param betID The ID of the bet to calculate potential winnings for.
     * @return The potential winnings, or 0 if the bet is not found.
     */
//    public double calculatePotentialWinning(Integer betID) {
//        Bet bet = betRepo.get(betID);
//        if (bet == null) {
//            System.out.println("Bet Not Found");
//            return 0;
//        }
//
//        return calculateOdd(betID) * bet.getAmount();
//    }

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

//
//    /**
//     * Adds a new bet to the repository.
//     *
//     * @param event  The list of events associated with the bet.
//     * @param amount The amount of the bet.
//     */
//    public void createBet(Integer playerId, List<Event> event, int amount) {
//        int lastBet;
//        if (betRepo.getAll().isEmpty()) {
//            lastBet = 0;
//        } else {
//            lastBet = betRepo.getAll().getLast().getBet_id();
//        }
//        Bet newBet = new Bet(lastBet + 1, event, amount, LocalDateTime.now(), "active");
//        betRepo.create(newBet);
//
//        Player player = playerRepo.get(playerId);
//        player.setBalance(player.getBalance() - amount);
//        player.getActiveBets().add(newBet);
//        player.getAllBets().add(newBet);
//        playerRepo.update(player);
//    }


    public String getPlayerBetHistory(int playerId) {

        Player player = playerRepo.get(playerId);
        if (player == null || player.getAllBets().isEmpty()) {
            return "No bets placed by this player";
        }

        StringBuilder betHistory = new StringBuilder();
        betHistory.append("Bet History for Player: ").append(player.getUser_name()).append("\n");

        for (Bet bet : player.getAllBets()) {
            if (bet != null && bet.getBetstatus().equals("ended")) {
                betHistory.append("BetID: ").append(bet.getBet_id())
                        .append(" | Bet Amount: ").append(bet.getAmount())
                        .append(" | Bet Date: ").append(bet.getBet_date())
                        .append("\n");

                for (Event event : bet.getEvent()) {
                    betHistory.append(" - Event: ").append(event.getEvent_name())
                            .append(" | Event Date: ").append(event.getEvent_date())
                            .append(" | Event Odds: ").append(event.getOddsList())
                            .append("\n");
                }

                betHistory.append("\n");
            }
        }

        return betHistory.toString();
    }


//    /**
//     * Adds a new event to the repository.
//     *
//     * //@param eventName The name of the event.
//     * //@param eventType The type of the event.
//     */
//    public void addEvent(Event event) {
//        int lastEvent;
//
//        if (eventRepo.getAll().isEmpty()) {
//            lastEvent = 0;
//        } else {
//            lastEvent = eventRepo.getAll().getLast().getEvent_id();
//        }
//
//        if (event.getSports_type().equals( "Football")) {
//            Odds footballOdds = oddsRepo.getAll().getFirst();
//            List<Double> oddList = footballOdds.getOdd_value();
//            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
//            eventRepo.create(newEvent);
//        } else if (Objects.equals(eventType, "Tennis")) {
//            TennisOdds tennisOdds = tennisOddsRepo.getAll().getFirst();
//            List<Double> oddList = tennisOdds.getOdd_value();
//            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
//            eventRepo.create(newEvent);
//        } else if (Objects.equals(eventType, "Basket")) {
//            BasketOdds basketOdds = basketOddsRepo.getAll().getFirst();
//            List<Double> oddList = basketOdds.getOdd_value();
//            Event newEvent = new Event(lastEvent + 1, eventName, oddList, LocalDateTime.now(), eventType);
//            eventRepo.create(newEvent);
//        }
//    }

    public void addOdds(Odds odds) {
        if (odds.getEventType().equals("Football")) {
            Odds odds1 = new Odds(odds.getOdd_id(), odds.getOddName(), odds.getOddName());
            oddsRepo.create(odds1);
        } else if (odds.getEventType().equals("Tennis")) {
            Odds odds2 = new Odds(odds.getOdd_id(), odds.getOddName(), odds.getEventType());
            oddsRepo.create(odds2);
        } else if (odds.getEventType().equals("Basket")) {
            Odds odds3 = new Odds(odds.getOdd_id(), odds.getOddName(), odds.getEventType());
            oddsRepo.create(odds3);
        }
    }

    public List<Event> getAvailableEvents() {
        return eventRepo.getAll();
    }

    public List<Odds> getAvailableOdds(){return oddsRepo.getAll();}

    public List<Event> filterbySportsType(List<Event> events, String type) {
        return events.stream().filter(event -> event.getSports_type().equals(type)).collect(Collectors.toList());
    }

    public List<Transactions> getAvailableTransaction(){return transactionsRepo.getAll();}



    public List<Transactions> filterbyTransactionType(List<Transactions> transactions, String type) {
        return transactions.stream().filter(transaction -> transaction.getTransaction_type().equals(type)).collect(Collectors.toList());
    }



    public List<Event> sortEventsByDate(List<Event> events, boolean ascending) {
        List<Event> mutableEvents = new ArrayList<>(events);

        if (mutableEvents == null || mutableEvents.isEmpty()) {
            System.out.println("No events to sort.");
            return mutableEvents;
        }

        if (ascending) {
            mutableEvents.sort((event1, event2) -> {
                if (event1.getEvent_date() == null && event2.getEvent_date() == null) return 0;
                if (event1.getEvent_date() == null) return 1;
                if (event2.getEvent_date() == null) return -1;
                return event1.getEvent_date().compareTo(event2.getEvent_date());
            });
        } else {
            mutableEvents.sort((event1, event2) -> {
                if (event1.getEvent_date() == null && event2.getEvent_date() == null) return 0;
                if (event1.getEvent_date() == null) return 1;
                if (event2.getEvent_date() == null) return -1;
                return event2.getEvent_date().compareTo(event1.getEvent_date());
            });
        }

        return mutableEvents;
    }

    public List<Player> sortPlayersByName(List<Player> players, boolean ascending) {
        List<Player> mutablePlayers = new ArrayList<>(players);

        if (mutablePlayers == null || mutablePlayers.isEmpty()) {
            System.out.println("No players to sort.");
            return mutablePlayers;
        }

        if (ascending) {
            mutablePlayers.sort((player1, player2) -> {
                if (player1.getUser_name() == null && player2.getUser_name() == null) return 0;
                if (player1.getUser_name() == null) return 1;
                if (player2.getUser_name() == null) return -1;
                return player1.getUser_name().compareTo(player2.getUser_name());
            });
        } else {
            mutablePlayers.sort((player1, player2) -> {
                if (player1.getUser_name() == null && player2.getUser_name() == null) return 0;
                if (player1.getUser_name() == null) return 1;
                if (player2.getUser_name() == null) return -1;
                return player2.getUser_name().compareTo(player1.getUser_name());
            });
        }

        return mutablePlayers;
    }


//    public double getOddsValueByName(Event event, String oddName) {
//        Map<String, Double> odds = event.getOddsList();
//        if (odds.containsKey(oddName)) {
//            return odds.get(oddName);
//        } else {
//            throw new IllegalArgumentException("No odds found for name " + oddName);
//        }
//    }



//    public void setDefaultOdds(Odds odds,Event event) {
//        if (odds.getEventType().equals("Football")) {
//            event.oddsList.add(new Odds(1,"1",0.0, "Football"));  // Cota pentru echipa 1
//            event.oddsList.add(new Odds(2,"X",0.0, "Football"));
//            event.oddsList.add(new Odds(3,"2",0.0, "Football"));
//            event.oddsList.add(new Odds(4,"GG",0.0, "Football"));
//            event.oddsList.add(new Odds(5,"Hattrick in Match",0.0, "Football"));
//            event.oddsList.add(new Odds(6,"over 2.5 goals",0.0, "Football"));
//
//        }else if (odds.getEventType().equals("Tennis")) {
//
//            event.oddsList.add(new Odds(50,"1",0.0, "Tennis"));
//            event.oddsList.add(new Odds(51,"2",0.0, "Tennis"));
//            event.oddsList.add(new Odds(52,"both to win a set",0.0, "Tennis"));
//            event.oddsList.add(new Odds(53,"6-0 set win in match",0.0, "Tennis"));
//            event.oddsList.add(new Odds(54,"games 1st set over 8.5",0.0, "Tennis"));
//
//        }else if (odds.getEventType().equals("Basket")) {
//
//            event.oddsList.add(new Odds(100,"1",0.0, "Basket"));
//            event.oddsList.add(new Odds(101,"X",0.0, "Basket"));
//            event.oddsList.add(new Odds(102,"2",0.0, "Basket"));
//            event.oddsList.add(new Odds(103,"Triple Double in Match",0.0, "Basket"));
//            event.oddsList.add(new Odds(104,"over 177.5 Points",0.0, "Basket"));
//            event.oddsList.add(new Odds(105,"2OT",0.0, "Basket"));
//
//        }
//    }

    public void placeBet(Player player,Event event) {
        Scanner scanner = new Scanner(System.in);

        // Afișează evenimentele disponibile
        System.out.println("Available Events:");
        for (Event eventt : event.getEventList()) { // `events` este o listă globală de evenimente
            System.out.println(eventt.getEvent_id() + ": " + eventt.getEvent_name());
        }

        // Selectează evenimente
        List<Event> selectedEvents = new ArrayList<>();
        while (true) {
            System.out.print("Enter Event ID to add to your bet (or 0 to finish): ");
            int eventId = scanner.nextInt();
            if (eventId == 0) break;

            Event selectedEvent = getAvailableEvents().stream()
                    .filter(e -> e.getEvent_id() == eventId)
                    .findFirst()
                    .orElse(null);
            if (selectedEvent == null) {
                System.out.println("Invalid Event ID. Try again.");
            } else if (selectedEvents.contains(selectedEvent)) {
                System.out.println("You have already selected this event.");
            } else {
                selectedEvents.add(selectedEvent);
                System.out.println("Event added: " + selectedEvent.getEvent_name());
            }
        }

        if (selectedEvents.isEmpty()) {
            System.out.println("No events selected. Bet cancelled.");
            return;
        }

        // Afișează cotele pentru fiecare eveniment selectat
        double totalOdds = 1.0;
        for (Event eventt : selectedEvents) {
            System.out.println("Available Odds for " + eventt.getEvent_name() + ":");

            // Afișează toate cotele și valorile lor
            event.getOddsList().forEach((odd, value) ->
                    System.out.println(odd.getOdd_id() + ": " + odd.getOddName() + " - " + value)
            );

            // Solicită utilizatorului să aleagă o cotă
            System.out.print("Enter the ID of the odd you want to select: ");
            int oddId = scanner.nextInt();

            // Găsește Odds-ul selectat după ID
            Odds selectedOdd = event.getOddsList().keySet().stream()
                    .filter(o -> o.getOdd_id() == oddId)
                    .findFirst()
                    .orElse(null);

            if (selectedOdd == null) {
                System.out.println("Invalid Odd ID. Bet cancelled.");
                return; // Ieșire din buclă
            }

            // Obține valoarea cotei selectate
            Double selectedValue = event.getOddsList().get(selectedOdd);
            if (selectedValue == null) {
                System.out.println("Value for the selected odd is missing. Bet cancelled.");
                return; // Ieșire din buclă
            }

            // Actualizează cota totală
            totalOdds *= selectedValue;
            System.out.println("Selected Odd: " + selectedOdd.getOddName() + " - " + selectedValue);
        }

        // Solicită suma pariată de utilizator
        System.out.print("Enter the bet amount: ");
        double betAmount = scanner.nextDouble();


        // Introdu suma pariată
        System.out.print("Enter your bet amount: ");
        int amount = scanner.nextInt();
        if (player.getBalance() < amount) {
            System.out.println("Insufficient balance. Bet cancelled.");
            return;
        }

        // Creează pariul


        LocalDateTime betDate = LocalDateTime.now();
        int lastBet;
        if (betRepo.getAll().isEmpty()) {
            lastBet = 0;
        } else {
            lastBet = betRepo.getAll().getLast().getBet_id();
        }
        Bet newBet = new Bet(player.getUser_id(),lastBet + 1,selectedEvents,amount,betDate,"active" );
        betRepo.create(newBet);

        // Actualizează balanța pariorului
        player.setBalance(player.getBalance() - amount);

        // Calculează câștigul potențial
        double potentialWinning = amount * totalOdds;
        System.out.println("Total Odds: " + totalOdds);
        System.out.println("Potential Win: " + potentialWinning);
        System.out.println("Bet placed successfully!");
        System.out.println("Potential Winning: " + potentialWinning);


        // Adaugă pariu în lista activă a pariorului
        player.getActiveBets().add(newBet);
    }





}



