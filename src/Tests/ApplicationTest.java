package Tests;

import ModelLayer.*;
import ControllerLayer.*;
import ServiceLayer.*;

import RepoLayerInterface.repo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
    private final AdminController adminController;
    private final PlayerController playerController;
    private final UserService userService;
    private final BetService betService;
    private final repo<Bet> betRepo;
    private final repo<Player> playerRepo;
    private final repo<Event> eventRepo;
    private final repo<FootballOdds> footballOddsRepo;
    private final repo<TennisOdds> tennisOddsRepo;
    private final repo<BasketOdds> basketOddsRepo;
    private final repo<Transactions> transactionRepo;
    private final repo<Admin> adminRepo;

    public ApplicationTest(AdminController adminController, PlayerController playerController, UserService userService, BetService betService, repo<Bet> betRepo, repo<Event> eventRepo, repo<FootballOdds> footballOddsRepo, repo<TennisOdds> tennisOddsRepo, repo<BasketOdds> basketOddsRepo, repo<Player> playerRepo, repo<Transactions> transactionRepo, repo<Admin> adminRepo) {
        this.adminController = adminController;
        this.playerController = playerController;
        this.userService = userService;
        this.betService = betService;
        this.betRepo = betRepo;
        this.eventRepo = eventRepo;
        this.footballOddsRepo = footballOddsRepo;
        this.tennisOddsRepo = tennisOddsRepo;
        this.basketOddsRepo = basketOddsRepo;
        this.playerRepo = playerRepo;
        this.transactionRepo = transactionRepo;
        this.adminRepo = adminRepo;
    }


    //Initialize
    public static void InitalizeRepo(
            repo<Event> eventRepo,
            repo<Bet> betRepo,
            repo<Player> playerRepo,
            repo<FootballOdds> footballOddsRepo,
            repo<TennisOdds> tennisOddsRepo,
            repo<BasketOdds> basketOddsRepo,
            repo<Transactions> transactionsRepo,
            repo<Admin> adminRepo
    ) {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        Event event1 = new Event(1, "Steaua vs Dinamo", odds, LocalDateTime.now(), "Football");
        Event event2 = new Event(2, "UCluj vs Galati", odds, LocalDateTime.now(), "Football");
        Event event3 = new Event(3, "Simona vs Nadal", odds, LocalDateTime.now(), "Tennis");
        eventRepo.create(event1);
        eventRepo.create(event2);
        eventRepo.create(event3);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        Bet bet1 = new Bet(1, events, 20, LocalDateTime.now(),"active");
        Bet bet2 = new Bet(2, events, 30, LocalDateTime.now(),"ended");
        Bet bet3 = new Bet(3, events, 40, LocalDateTime.now(),"ended");
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

        footballOddsRepo.create(new FootballOdds(odds, "Peste 5"));
        footballOddsRepo.create(new FootballOdds(odds, "Sub 5"));

        tennisOddsRepo.create(new TennisOdds(odds, "Most aces"));
        tennisOddsRepo.create(new TennisOdds(odds, "Minim un set"));

        basketOddsRepo.create(new BasketOdds(odds, "Peste 90 puncte"));
        basketOddsRepo.create(new BasketOdds(odds, "Triple double in match"));

        transactionsRepo.create(new Transactions(1, player1, 100, LocalDateTime.now(), "Withdraw", "Completed"));
        transactionsRepo.create(new Transactions(2, player2, 100, LocalDateTime.now(), "Deposit", "Completed"));

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
        assertEquals(100, player.getBalance());
    }

    //Tests for BetService
    @Test
    void testCalculateOdd() {
        assertEquals(6.0, betService.calculateOdd(1));
        assertEquals(1.0, betService.calculateOdd(5));
    }

    @Test
    void testCalculatePotentialWinning() {
        assertEquals(120.0, betService.calculatePotentialWinning(1));
        assertEquals(0.0, betService.calculatePotentialWinning(5));
    }

    @Test
    void testCreateBet() {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        Event event1 = new Event(3, "Caini VS Pisici", odds, LocalDateTime.now(), "Basket");
        Event event2 = new Event(4, "Ronaldo VS Messi", odds, LocalDateTime.now(), "Tennis");
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        betService.createBet(1, events, 30);
        betService.getAvailableBets();
    }

    @Test
    void testCreateEvent() {
        betService.addEvent("Romani VS Tatari", "Football");
        betService.getAvailableBets();
    }

    @Test
    void testCreateOdds() {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        betService.addOdds(odds, "Romani VS Tatari", "Football");
        betService.getFootballOdds();
    }

    @Test
    void testFilterBySportTyoe() {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        Event event1 = new Event(3, "Caini VS Pisici", odds, LocalDateTime.now(), "Basket");
        Event event2 = new Event(4, "Ronaldo VS Messi", odds, LocalDateTime.now(), "Tennis");
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        assertEquals(event1, betService.filterbySportsType(events, "Basket"));
    }

    @Test
    void testFilterByOdds() {
        List<Double> odds = new ArrayList<>();
        odds.add(1.0);
        odds.add(2.0);
        odds.add(3.0);
        FootballOdds footballOdds = new FootballOdds(odds, "Peste 5");
        FootballOdds footballOdds1 = new FootballOdds(odds, "Sub 5");
        List<FootballOdds> listFootball = new ArrayList<>();
        listFootball.add(footballOdds);
        listFootball.add(footballOdds1);
        List<FootballOdds> listFootball1 = new ArrayList<>();
        listFootball1.add(footballOdds);

        assertEquals(listFootball1, betService.filterbyOdds(listFootball, "Peste 5"));
    }

    @Test
    void testSortEventsByDate() {}

    @Test
    void testSortPlayersByName() {}
}
