package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class UserService {
    private final repo<Player> playerRepo;
    private final repo<Admin> adminRepo;
    private final repo<Transactions> transactionsRepo;

    public UserService(repo<Player> playerRepo, repo<Admin> adminRepo, repo<Transactions> transactionsRepo) {
        this.playerRepo = playerRepo;
        this.adminRepo = adminRepo;
        this.transactionsRepo = transactionsRepo;
    }

    public boolean Login(String username, String password) {
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) && player.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean AdminLogin(String username, String password) {
        for (Admin admin : adminRepo.getAll()) {
            if (admin.getUser_name().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<Player> getAllPlayers() {
        return playerRepo.getAll();
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.getAll();
    }

    public boolean addPlayer(String username, String password, String email) {
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) &&  player.getEmail().equals(email)) {
                return false;
            }
        }
        int lastPlayerId = playerRepo.getAll().getLast().getUser_id();
        List<Bet> emptyList = new ArrayList<>();
        Player newPlayer = new Player(lastPlayerId + 1, username, password, email, 0, emptyList, 0, "Active");
        playerRepo.create(newPlayer);
        return true;
    }

    public void removePlayer(int playerId) {
        playerRepo.delete(playerId);
    }
    public void  removeAdmin(int adminId){
        adminRepo.delete(adminId);
    }

    public void updateAccesLevelAdmin(Integer adminId,int accessLevel){
        Admin admin = adminRepo.get(adminId);
        if(adminId == null){
            System.out.println("Admin not found");
        }

        if(accessLevel <= admin.getAccess_level()){
            System.out.println("Admin access level must be higher");
        }
        admin.setAccess_level(accessLevel);
        adminRepo.update(admin);
        System.out.println("Admin access level has been updated");

    }

    public boolean addAdmin(String username, String password, String email) {
        for (Admin admin : adminRepo.getAll()) {
            if (admin.getUser_name().equals(username) &&  admin.getEmail().equals(email)) {
                return false;
            }
        }
        int lastAdminId = adminRepo.getAll().getLast().getUser_id();
        Admin newAdmin = new Admin(lastAdminId, username, password,email,1000, 3, "Suport");
        adminRepo.create(newAdmin);
        return true;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.getAll();
    }

    public boolean deposit(String username, String password, int amount) {
        Player user = null;
        for (Transactions transaction : transactionsRepo.getAll()) {
            if(transaction.getUser().getUser_name().equals(username) && transaction.getUser().getPassword().equals(password)) {
                user = transaction.getUser();
                break;
            }
        }
        if (user == null) {
            return false;
        }
        int lastTransactions = transactionsRepo.getAll().getLast().getTransaction_id();
        Transactions transactions = new Transactions(lastTransactions + 1, user, amount, LocalDateTime.now(), "Deposit", "Done");
        transactionsRepo.create(transactions);
        user.setBalance(user.getBalance() + amount);


        return true;
    }

    public boolean withdraw(String username, String password, int amount) {
        Player user = null;
        for (Transactions transaction : transactionsRepo.getAll()) {
            if(transaction.getUser().getUser_name().equals(username) && transaction.getUser().getPassword().equals(password)) {
                user = transaction.getUser();
                break;
            }
        }
        if (user == null) {
            return false;
        }
        int lastTransactions = transactionsRepo.getAll().getLast().getTransaction_id();
        Transactions transactions = new Transactions(lastTransactions + 1, user, amount, LocalDateTime.now(), "Withdraw", "Done");
        transactionsRepo.create(transactions);
        user.setBalance(user.getBalance() - amount);

        return true;
    }
}
