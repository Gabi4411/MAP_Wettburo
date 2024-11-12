package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                int lastPlayerId = playerRepo.getAll().getLast().getUser_id();
                List<Bet> emptyList = new ArrayList<>();
                Player newPlayer = new Player(lastPlayerId + 1, username, password, email, 0, emptyList, 0, "Active");
                playerRepo.create(newPlayer);
                return true;
            }
        }
        return false;
    }

    public boolean removePlayer(String username, String password) {
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) && player.getPassword().equals(password)) {
                int idPlayer = 1;
                playerRepo.delete(idPlayer);
                return true;
            }
        }
        return false;
    }

    public boolean addAdmin(String username, String password, String email) {
        for (Admin admin : adminRepo.getAll()) {
            if (admin.getUser_name().equals(username) &&  admin.getEmail().equals(email)) {
                int lastAdminId = adminRepo.getAll().getLast().getUser_id();
                Admin newAdmin = new Admin(lastAdminId, username, password,email,1000, "Standard", "Suport");
                adminRepo.create(newAdmin);
                return true;
            }
        }
        return false;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.getAll();
    }

    public boolean deposit(String username, String password, int amount) {
        Player thePlayer;
        boolean find = false;
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) && player.getPassword().equals(password)) {
                thePlayer = player;
                find = true;
                break;
            }
        }
        if (!find) {
            return false;
        }


        return true;
    }

    public boolean withdraw(String username, String password, int amount) {
        Player thePlayer;
        boolean find = false;
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) && player.getPassword().equals(password)) {
                thePlayer = player;
                find = true;
                break;
            }
        }
        if (!find) {
            return false;
        }


        return true;
    }
}
