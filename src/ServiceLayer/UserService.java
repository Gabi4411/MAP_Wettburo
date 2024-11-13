package ServiceLayer;

import ModelLayer.*;
import RepoLayerInterface.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Service layer that handles user-related actions such as login, player and admin management,
 * transactions, and updating access levels in the system.
 */
public class UserService {
    /**
     * Repository for managing player data.
     */
    private final repo<Player> playerRepo;

    /**
     * Repository for managing admin data.
     */
    private final repo<Admin> adminRepo;

    /**
     * Repository for managing transaction data.
     */
    private final repo<Transactions> transactionsRepo;

    /**
     * Constructs a UserService object with the specified repositories for players, admins, and transactions.
     *
     * @param playerRepo     the repository for player data
     * @param adminRepo      the repository for admin data
     * @param transactionsRepo the repository for transaction data
     */
    public UserService(repo<Player> playerRepo, repo<Admin> adminRepo, repo<Transactions> transactionsRepo) {
        this.playerRepo = playerRepo;
        this.adminRepo = adminRepo;
        this.transactionsRepo = transactionsRepo;
    }

    /**
     * Attempts to log in a player with the provided username and password.
     *
     * @param username the username of the player
     * @param password the password of the player
     * @return true if the login credentials are valid, false otherwise
     */
    public boolean Login(String username, String password) {
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) && player.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to log in an admin with the provided username and password.
     *
     * @param username the username of the admin
     * @param password the password of the admin
     * @return true if the login credentials are valid, false otherwise
     */
    public boolean AdminLogin(String username, String password) {
        for (Admin admin : adminRepo.getAll()) {
            if (admin.getUser_name().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all players from the player repository.
     *
     * @return a list of all players
     */
    public List<Player> getAllPlayers() {
        return playerRepo.getAll();
    }

    /**
     * Retrieves all admins from the admin repository.
     *
     * @return a list of all admins
     */
    public List<Admin> getAllAdmins() {
        return adminRepo.getAll();
    }

    /**
     * Adds a new player to the system, ensuring the username and email are unique.
     *
     * @param username the username of the new player
     * @param password the password of the new player
     * @param email    the email of the new player
     * @return true if the player was successfully added, false if the username or email already exists
     */
    public boolean addPlayer(String username, String password, String email) {
        for (Player player : playerRepo.getAll()) {
            if (player.getUser_name().equals(username) &&  player.getEmail().equals(email)) {
                return false;
            }
        }
        int lastPlayerId;
        if (playerRepo.getAll().size() == 0) {
            lastPlayerId = 0;
        }
        else {
            lastPlayerId = playerRepo.getAll().getLast().getUser_id();
        }
        List<Bet> emptyList = new ArrayList<>();
        Player newPlayer = new Player(lastPlayerId + 1, username, password, email, 0, emptyList, 0, "Active");
        playerRepo.create(newPlayer);
        return true;
    }

    /**
     * Removes a player from the system based on their player ID.
     *
     * @param playerId the ID of the player to remove
     */
    public void removePlayer(int playerId) {
        playerRepo.delete(playerId);
    }

    /**
     * Removes an admin from the system based on their admin ID.
     *
     * @param adminId the ID of the admin to remove
     */
    public void removeAdmin(int adminId) {
        adminRepo.delete(adminId);
    }

    /**
     * Updates the access level of an admin.
     *
     * @param adminId     the ID of the admin whose access level is being updated
     * @param accessLevel the new access level to set
     */
    public void updateAccesLevelAdmin(Integer adminId, int accessLevel) {
        Admin admin = adminRepo.get(adminId);
        if (adminId == null) {
            System.out.println("Admin not found");
        }

        if (accessLevel <= admin.getAccess_level()) {
            System.out.println("Admin access level must be higher");
        }
        admin.setAccess_level(accessLevel);
        adminRepo.update(admin);
        System.out.println("Admin access level has been updated");
    }

    /**
     * Adds a new admin to the system, ensuring the username and email are unique.
     *
     * @param username the username of the new admin
     * @param password the password of the new admin
     * @param email    the email of the new admin
     * @return true if the admin was successfully added, false if the username or email already exists
     */
    public boolean addAdmin(String username, String password, String email) {
        for (Admin admin : adminRepo.getAll()) {
            if (admin.getUser_name().equals(username) && admin.getEmail().equals(email)) {
                return false;
            }
        }
        int lastAdminId;
        if (adminRepo.getAll().size() == 0) {
            lastAdminId = 0;
        }
        else {
            lastAdminId = adminRepo.getAll().getLast().getUser_id();
        }
        Admin newAdmin = new Admin(lastAdminId, username, password, email, 1000, 3, "Suport");
        adminRepo.create(newAdmin);
        return true;
    }

    /**
     * Retrieves all transactions from the transaction repository.
     *
     * @return a list of all transactions
     */
    public List<Transactions> getAllTransactions() {
        return transactionsRepo.getAll();
    }

    /**
     * Deposits the specified amount into a player's account, creating a transaction record.
     *
     * @param username the username of the player
     * @param password the password of the player
     * @param amount   the amount to deposit
     * @return true if the deposit was successful, false if the player is not found
     */
    public boolean deposit(String username, String password, int amount) {
        Player user = null;
        for (Transactions transaction : transactionsRepo.getAll()) {
            if (transaction.getUser().getUser_name().equals(username) && transaction.getUser().getPassword().equals(password)) {
                user = transaction.getUser();
                break;
            }
        }
        if (user == null) {
            return false;
        }
        int lastTransactions;
        if (transactionsRepo.getAll().size() == 0) {
            lastTransactions = 0;
        } else {
            lastTransactions = transactionsRepo.getAll().getLast().getTransaction_id();
        }
        Transactions transactions = new Transactions(lastTransactions + 1, user, amount, LocalDateTime.now(), "Deposit", "Done");
        transactionsRepo.create(transactions);
        user.setBalance(user.getBalance() + amount);

        return true;
    }

    /**
     * Withdraws the specified amount from a player's account, creating a transaction record.
     *
     * @param username the username of the player
     * @param password the password of the player
     * @param amount   the amount to withdraw
     * @return true if the withdrawal was successful, false if the player is not found
     */
    public boolean withdraw(String username, String password, int amount) {
        Player user = null;
        for (Transactions transaction : transactionsRepo.getAll()) {
            if (transaction.getUser().getUser_name().equals(username) && transaction.getUser().getPassword().equals(password)) {
                user = transaction.getUser();
                break;
            }
        }
        if (user == null) {
            return false;
        }
        int lastTransactions;
        if (transactionsRepo.getAll().size() == 0) {
            lastTransactions = 0;
        } else {
            lastTransactions = transactionsRepo.getAll().getLast().getTransaction_id();
        }
        Transactions transactions = new Transactions(lastTransactions + 1, user, amount, LocalDateTime.now(), "Withdraw", "Done");
        transactionsRepo.create(transactions);
        user.setBalance(user.getBalance() - amount);

        return true;
    }
}
