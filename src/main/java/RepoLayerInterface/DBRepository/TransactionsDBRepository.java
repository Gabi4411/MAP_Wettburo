package RepoLayerInterface.DBRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ModelLayer.Transactions;

public class TransactionsDBRepository extends DBRepository<Transactions> {

    public TransactionsDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Transactions transaction) {
        String sql = "INSERT INTO \"Transactions\" (\"transaction_id\", \"player_id\", \"amount\", \"transaction_date\", \"transaction_type\", \"transaction_status\") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, transaction.getTransaction_id());
            statement.setInt(2, transaction.getPlayerID());
            statement.setInt(3, transaction.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(transaction.getTranscation_date())); // Conversia LocalDateTime în Timestamp
            statement.setString(5, transaction.getTransaction_type());
            statement.setString(6, transaction.getTransaction_status());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public Transactions get(Integer id) {
        String sql = "SELECT * FROM \"Transactions\" WHERE \"transaction_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int transaction_id = resultSet.getInt("transaction_id");
                int player_id = resultSet.getInt("player_id");
                int amount = resultSet.getInt("amount");
                LocalDateTime transaction_date = resultSet.getTimestamp("transaction_date").toLocalDateTime();
                String transaction_type = resultSet.getString("transaction_type");
                String transaction_status = resultSet.getString("transaction_status");

                return new Transactions(transaction_id, player_id, amount, transaction_date, transaction_type, transaction_status);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Transactions transaction) {
        String sql = "UPDATE \"Transactions\" SET \"player_id\" = ?, \"amount\" = ?, \"transaction_date\" = ?, \"transaction_type\" = ?, \"transaction_status\" = ? WHERE \"transaction_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, transaction.getPlayerID());
            statement.setInt(2, transaction.getAmount());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getTranscation_date())); // Conversia LocalDateTime în Timestamp
            statement.setString(4, transaction.getTransaction_type());
            statement.setString(5, transaction.getTransaction_status());
            statement.setInt(6, transaction.getTransaction_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Transactions\" WHERE \"transaction_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Transactions> getAll() {
        String sql = "SELECT * FROM \"Transactions\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Transactions> transactionsList = new ArrayList<>();
            while (resultSet.next()) {
                int transaction_id = resultSet.getInt("transaction_id");
                int player_id = resultSet.getInt("player_id");
                int amount = resultSet.getInt("amount");
                LocalDateTime transaction_date = resultSet.getTimestamp("transaction_date").toLocalDateTime();
                String transaction_type = resultSet.getString("transaction_type");
                String transaction_status = resultSet.getString("transaction_status");

                transactionsList.add(new Transactions(transaction_id, player_id, amount, transaction_date, transaction_type, transaction_status));
            }

            return transactionsList;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all transactions: " + e.getMessage(), e);
        }
    }
}
