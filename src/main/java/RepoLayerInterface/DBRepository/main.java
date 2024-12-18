package RepoLayerInterface.DBRepository;

import ModelLayer.User;
import ModelLayer.Player;
import ModelLayer.Admin;
import ModelLayer.*;
import ModelLayer.Transactions;
import ModelLayer.Authentification;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        // Creare instanțe pentru User, Player, Admin, Bet, Transactions, Authentification
        Admin adminUser = new Admin(2, "Miahi Name", "admin123", "Mihaisuper@bet",3,3,"smecher" );


        Odds odd1 = new Odds(1, "Gol in minutul 10", "Football");
        Map<Odds, Double> map1 = new HashMap<Odds, Double>();
        map1.put(odd1, 2.5);


        Event event1 = new Event(1, "Steaua vs Dinamo", map1, "11.11.2024", "Football");
        List<Event> events = new ArrayList<>();
        events.add(event1);

        // Creare Bet
        Bet bet1 = new Bet(1, 1, events, 100,LocalDateTime.now(),"active");
        Bet bet2 = new Bet(2, 1, events, 50, LocalDateTime.now(),"active");
        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);

        Player playerUser = new Player(1,"Gica Popescu","gicboss","gica@boss",50,bets,bets,0,"active");

        // Creare Transactions
        Transactions transaction1 = new Transactions(1, 1, 100, LocalDateTime.now(), "Deposit", "Success");
        Transactions transaction2 = new Transactions(2, 1, 50, LocalDateTime.now(), "Withdrawal", "Failed");

        // Creare Authentification
        Authentification auth1 = new Authentification(1, 1, 0, new Date(System.currentTimeMillis()));
        Authentification auth2 = new Authentification(2, 2, 0, new Date(System.currentTimeMillis()));

        // Instanțiere repository-uri
        AdminDBRepository adminDBRepository = new AdminDBRepository("jdbc:mysql://127.0.0.1:3306/wettburo", "root", "MAPfinal");
        PlayerDBRepository playerDBRepository = new PlayerDBRepository("jdbc:mysql://127.0.0.1:3306/wettburo", "root", "MAPfinal");
        BetDBRepository betDBRepository = new BetDBRepository("jdbc:mysql://127.0.0.1:3306/wettburo", "root", "MAPfinal");
        TransactionsDBRepository transactionsDBRepository = new TransactionsDBRepository("jdbc:mysql://127.0.0.1:3306/wettburo", "root", "MAPfinal");
        AuthentificationDBRepository authentificationDBRepository = new AuthentificationDBRepository("jdbc:mysql://127.0.0.1:3306/wettburo", "root", "MAPfinal");

        // Creare înregistrări în baza de date
        adminDBRepository.create(adminUser);
        playerDBRepository.create(playerUser);
        betDBRepository.create(bet1);
        betDBRepository.create(bet2);
        transactionsDBRepository.create(transaction1);
        transactionsDBRepository.create(transaction2);
        authentificationDBRepository.create(auth1);
        authentificationDBRepository.create(auth2);

        // Afișare mesaje pentru confirmarea înregistrărilor
        System.out.println("Player and Admin users, bets, transactions, and authentifications have been saved to the database.");
    }
}
