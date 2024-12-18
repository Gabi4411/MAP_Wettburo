package RepoLayerInterface.DBRepository;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import ModelLayer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerDBRepository extends DBRepository<Player> {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDBRepository.class);

    public PlayerDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Player player) {
        String sql = "INSERT INTO \"Player\" (\"user_id\", \"user_name\", \"password\", \"email\", \"balance\", \"activeBets\", \"allBets\", \"bonus_balance\", \"account_status\") VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, player.getUser_id());
            statement.setString(2, player.getUser_name());
            statement.setString(3, player.getPassword());
            statement.setString(4, player.getEmail());
            statement.setDouble(5, player.getBalance());

            // Convert activeBets to JSON
            ObjectMapper mapper = new ObjectMapper();
            String activeBetsJson = mapper.writeValueAsString(player.getActiveBets());
            String allBetsJson = mapper.writeValueAsString(player.getAllBets());

            statement.setString(6, activeBetsJson);
            statement.setString(7, allBetsJson);

            statement.setInt(8, player.getBonus_balance());
            statement.setString(9, player.getAccount_status());

            statement.execute();
        } catch (SQLException | IOException e) {
            logger.error("Error inserting player", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Player get(Integer id) {
        String sql = "SELECT * FROM \"Player\" WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                double balance = resultSet.getDouble("balance");

                // Convert JSON strings back to lists
                String activeBetsJson = resultSet.getString("activeBets");
                ObjectMapper mapper = new ObjectMapper();
                List<Bet> activeBets = mapper.readValue(activeBetsJson, new TypeReference<List<Bet>>() {});

                String allBetsJson = resultSet.getString("allBets");
                List<Bet> allBets = mapper.readValue(allBetsJson, new TypeReference<List<Bet>>() {});

                int bonus_balance = resultSet.getInt("bonus_balance");
                String account_status = resultSet.getString("account_status");

                return new Player(user_id, user_name, password, email, balance, activeBets, allBets, bonus_balance, account_status);
            } else {
                return null;
            }
        } catch (SQLException | IOException e) {
            logger.error("Error retrieving player by id", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Player player) {
        String sql = "UPDATE \"Player\" SET \"user_name\" = ?, \"password\" = ?, \"email\" = ?, \"balance\" = ?, \"activeBets\" = ?, \"allBets\" = ?, \"bonus_balance\" = ?, \"account_status\" = ? WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, player.getUser_name());
            statement.setString(2, player.getPassword());
            statement.setString(3, player.getEmail());
            statement.setDouble(4, player.getBalance());

            // Convert activeBets to JSON
            ObjectMapper mapper = new ObjectMapper();
            String activeBetsJson = mapper.writeValueAsString(player.getActiveBets());
            String allBetsJson = mapper.writeValueAsString(player.getAllBets());

            statement.setString(5, activeBetsJson);
            statement.setString(6, allBetsJson);

            statement.setInt(7, player.getBonus_balance());
            statement.setString(8, player.getAccount_status());
            statement.setInt(9, player.getUser_id());

            statement.execute();
        } catch (SQLException | IOException e) {
            logger.error("Error updating player", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Player\" WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Error deleting player", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Player> getAll() {
        String sql = "SELECT * FROM \"Player\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Player> players = new ArrayList<>();

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                double balance = resultSet.getDouble("balance");

                // Convert JSON strings back to lists
                String activeBetsJson = resultSet.getString("activeBets");
                ObjectMapper mapper = new ObjectMapper();
                List<Bet> activeBets = mapper.readValue(activeBetsJson, new TypeReference<List<Bet>>() {});

                String allBetsJson = resultSet.getString("allBets");
                List<Bet> allBets = mapper.readValue(allBetsJson, new TypeReference<List<Bet>>() {});

                int bonus_balance = resultSet.getInt("bonus_balance");
                String account_status = resultSet.getString("account_status");

                players.add(new Player(user_id, user_name, password, email, balance, activeBets, allBets, bonus_balance, account_status));
            }

            return players;
        } catch (SQLException | IOException e) {
            logger.error("Error retrieving all players", e);
            throw new RuntimeException(e);
        }
    }
}
