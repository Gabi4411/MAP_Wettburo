package Tests;

import ModelLayer.*;
import ControllerLayer.*;
import RepoLayerInterface.inMemoryRepo;
import ServiceLayer.*;

import RepoLayerInterface.repo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ApplicationTest {
    private UserService userService;
    private BetService betService;
    private repo<Bet> betRepo;
    private repo<Player> playerRepo;
    private repo<Event> eventRepo;
    private repo<Transactions> transactionRepo;
    private repo<Admin> adminRepo;
    private repo<Odds> oddsRepo;
    private repo<Suport> suportRepo;
    private repo<Statistics> statisticsrepo;

    @BeforeEach
    void setUp() {
        betRepo = new inMemoryRepo<>();
        playerRepo = new inMemoryRepo<>();
        eventRepo = new inMemoryRepo<>();
        transactionRepo = new inMemoryRepo<>();
        adminRepo = new inMemoryRepo<>();
        oddsRepo = new inMemoryRepo<>();
        suportRepo = new inMemoryRepo<>();
        statisticsrepo = new inMemoryRepo<>();

        InitializeRepo(
                eventRepo,
                betRepo,
                playerRepo,
                transactionRepo,
                adminRepo,
                oddsRepo
        );

        userService = new UserService(playerRepo, adminRepo, transactionRepo, suportRepo, statisticsrepo);
        betService = new BetService(betRepo, eventRepo, transactionRepo, playerRepo, oddsRepo);
    }

    public static void InitializeRepo(
            repo<Event> eventRepo,
            repo<Bet> betRepo,
            repo<Player> playerRepo,
            repo<Transactions> transactionsRepo,
            repo<Admin> adminRepo,
            repo<Odds> oddsRepo
    ) {
        Odds odd1 = new Odds(1, "Gol in minutul 10", "Football");
        Odds odd2 = new Odds(2, "Gol in minutul 90", "Football");
        Odds odd3 = new Odds(3, "Ace din prima", "Tennis");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.5);
        Map<Odds, Double> map3 = new HashMap<Odds, Double>();
        map1.put(odd3, 2.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
        Event event2 = new Event(2, "UCluj vs Galati", map2, "12.03.2024", "Football");
        Event event3 = new Event(3, "Simona vs Nadal", map3, "03.03.2024", "Tennis");
        eventRepo.create(event1);
        eventRepo.create(event2);
        eventRepo.create(event3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        Bet bet1 = new Bet(1, 1, events, 30, LocalDateTime.now(), "Active");
        Bet bet2 = new Bet(2, 2, events, 100, LocalDateTime.now(), "Ended");
        Bet bet3 = new Bet(1, 3, events, 10, LocalDateTime.now(), "Active");
        betRepo.create(bet1);
        betRepo.create(bet2);
        betRepo.create(bet3);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);

        Player player1 = new Player(1, "Gabi", "1234", "moldovangabi@yahoo.com", 100.0, bets, bets, 0, "Active");
        Player player2 = new Player(2, "Lapa", "5678", "lapadtuandrei@yahoo.com", 4000, bets, bets, 0, "Active");
        playerRepo.create(player1);
        playerRepo.create(player2);

        transactionsRepo.create(new Transactions(1, 1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
        transactionsRepo.create(new Transactions(2, 2, 100, LocalDateTime.now(), "Deposit", "Completed"));

        adminRepo.create(new Admin(1, "Sefu1", "123456789", "sefu@tau.com", 5000, 3, "Support"));
        adminRepo.create(new Admin(2, "Sefu2", "987654321", "sefusefilor@tau.com", 10000, 2, "Support"));
    }

    //Tests for UserService
    @Test
    void testPlayerLogin() {
        assertTrue(userService.Login("Gabi", "1234"));
        assertFalse(userService.Login("Gabi", "123456"));
    }

    @Test
    void testAdminLogin() {
        assertTrue(userService.AdminLogin("Sefu1", "123456789"));
        assertFalse(userService.AdminLogin("Sefu2", "98765"));
    }

    @Test
    void testAddPlayer() {
        assertTrue(userService.addPlayer("Gabriel", "1111", "moldovangabi@yahoo.com"));
        assertFalse(userService.addPlayer("Gabriel", "1111", "moldovangabi@yahoo.com"));
    }

    @Test
    void testRemovePlayer() {
        userService.removePlayer(3);
        userService.getAllPlayers();
    }

    @Test
    void testAddAdmin() {
        assertTrue(userService.addAdmin("Lapadatu", "2222", "lapadatu@yahoo.com"));
        assertFalse(userService.addAdmin("Lapadatu", "2222", "lapadatu@yahoo.com"));
    }

    @Test
    void testRemoveAdmin() {
        userService.removeAdmin(3);
        userService.getAllAdmins();
    }

    @Test
    void testDeposit() {
        assertTrue(userService.deposit("Gabi", "1234", 100));
        assertFalse(userService.deposit("Gabriel", "1111", 100));
        Player player = playerRepo.get(1);
        assertEquals(200, player.getBalance());
    }

    @Test
    void testWithdraw() {
        assertTrue(userService.withdraw("Gabi", "1234", 100));
        assertFalse(userService.withdraw("Gabriel", "1111", 100));
        Player player = playerRepo.get(1);
        assertEquals(0.0, player.getBalance());
    }

//    Tests for BetService
//    @Test
//    void testCreateBet() {
//        betService.placeBet(1);
//        betService.getAvailableBets();
//    }

    @Test
    void testCreateEvent() {
        betService.addEvent("Caini VS Pisici", "Football", "01.01.2023", "Peste 1.5 goluri", 1.5);
        betService.getAvailableBets();
    }

    @Test
    void testFilterBySportTyoe() {
        Odds odd1 = new Odds(1, "Peste 1.5 goluri", "Football");
        Odds odd2 = new Odds(2, "Sub 1.5 goluri", "Football");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 1.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.0);
        Event event1 = new Event(1, "Caini VS Pisici", map1, "01.01.2023", "Football");
        Event event2 = new Event(2, "Ronaldo VS Messi", map2, "02.02.2023", "Football");
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        List<Event> eventsBasket = new ArrayList<>();
        eventsBasket.add(event1);
        eventsBasket.add(event2);
        assertEquals(eventsBasket, betService.filterbySportsType(events, "Football"));
        assertNotEquals(eventsBasket, betService.filterbySportsType(events, "Tennis"));
    }

    @Test
    void testFilterByTransactionType() {
        Odds odd1 = new Odds(3, "Peste 3.5 goluri", "Football");
        Odds odd2 = new Odds(4, "Sub 3.5 goluri", "Football");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.5);
        Event event1 = new Event(3, "Otel VS Galati", map1, "11.04.2024", "Football");
        Event event2 = new Event(4, "M VS M", map2, "10.03.2024", "Football");
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        Bet bet1 = new Bet(5, 1, events, 300, LocalDateTime.now(), "Active");
        Bet bet2 = new Bet(6, 2, events, 10, LocalDateTime.now(), "Ended");
        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        Player player1 = new Player(3, "Meli", "1234666", "melisa@yahoo.com", 100.0, bets, bets, 0, "Active");
        Player player2 = new Player(4, "Maria", "567887", "maria@yahoo.com", 4000, bets, bets, 0, "Active");
        Transactions transaction1 =  new Transactions(3, 3, 150, LocalDateTime.now(), "Withdraw", "Completed");
        Transactions transaction2 =  new Transactions(4, 4, 50, LocalDateTime.now(), "Withdraw", "Completed");
        List<Transactions> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        assertEquals(transactions, betService.filterbyTransactionType(transactions, "Withdraw"));
        assertNotEquals(transactions, betService.filterbyTransactionType(transactions, "Deposit"));
    }

    @Test
    void testSortEventsByDate() {
        Odds odd1 = new Odds(5, "Peste 3.5 goluri", "Football");
        Odds odd2 = new Odds(6, "Sub 3.5 goluri", "Football");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.5);
        Event event1 = new Event(5, "Otel VS Galati", map1, "11.04.2024", "Football");
        Event event2 = new Event(6, "M VS M", map2, "10.03.2024", "Football");
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        assertEquals(events, betService.sortEventsByDate(events, false));
        assertNotEquals(events, betService.sortEventsByDate(events, true));
    }

    @Test
    void testSortPlayersByName() {
        Odds odd1 = new Odds(7, "Peste 3.5 goluri", "Football");
        Odds odd2 = new Odds(8, "Sub 3.5 goluri", "Football");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);
        Map<Odds, Double> map2 = new HashMap<Odds, Double>();
        map1.put(odd2, 1.5);
        Event event1 = new Event(7, "Gazu VS Dumbraveni", map1, "03.03.2024", "Football");
        Event event2 = new Event(8, "Ploiesti vs Galati", map2,"04.04.2022", "Football");
        eventRepo.create(event1);
        eventRepo.create(event2);
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        Bet bet1 = new Bet(3, 3, events,100 ,LocalDateTime.now(),"active");
        Bet bet2 = new Bet(4, 4, events,200 ,LocalDateTime.now(),"ended");
        betRepo.create(bet1);
        betRepo.create(bet2);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player(3, "Mufasa", "123456", "mufasa@yahoo.com", 100.0, bets, bets, 0, "Active");
        Player player2 = new Player(4, "George", "56789", "george@yahoo.com", 400.0, bets, bets, 0, "Active");
        players.add(player1);
        players.add(player2);

        assertEquals(players, betService.sortPlayersByName(players, false));
        assertNotEquals(players, betService.sortPlayersByName(players, true));
    }
}


