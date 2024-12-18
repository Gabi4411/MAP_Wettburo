package RepoLayerInterface.DBRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import ModelLayer.Suport;

public class SuportDBRepository extends DBRepository<Suport> {

    public SuportDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    // Metoda pentru a crea un nou suport în baza de date
    @Override
    public void create(Suport suport) {
        String sql = "INSERT INTO \"Suport\" (\"playerId\", \"suport_id\", \"subject\", \"help_date\", \"status\") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, suport.getPlayerId());
            statement.setInt(2, suport.getSuport_id());
            statement.setString(3, suport.getSubject());
            statement.setTimestamp(4, Timestamp.valueOf(suport.getHelp_date()));  // Conversia LocalDateTime în Timestamp
            statement.setString(5, suport.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating suport: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a obține un suport din baza de date
    @Override
    public Suport get(Integer id) {
        String sql = "SELECT * FROM \"Suport\" WHERE \"suport_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int playerId = resultSet.getInt("playerId");
                int suport_id = resultSet.getInt("suport_id");
                String subject = resultSet.getString("subject");
                LocalDateTime help_date = resultSet.getTimestamp("help_date").toLocalDateTime();
                String status = resultSet.getString("status");

                return new Suport(playerId, suport_id, subject, help_date, status);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving suport: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a actualiza un suport existent
    @Override
    public void update(Suport suport) {
        String sql = "UPDATE \"Suport\" SET \"subject\" = ?, \"help_date\" = ?, \"status\" = ? WHERE \"suport_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, suport.getSubject());
            statement.setTimestamp(2, Timestamp.valueOf(suport.getHelp_date()));  // Conversia LocalDateTime în Timestamp
            statement.setString(3, suport.getStatus());
            statement.setInt(4, suport.getSuport_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating suport: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a șterge un suport din baza de date
    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Suport\" WHERE \"suport_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting suport: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a obține toate suporturile din baza de date
    @Override
    public List<Suport> getAll() {
        String sql = "SELECT * FROM \"Suport\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Suport> suportList = new ArrayList<>();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("playerId");
                int suport_id = resultSet.getInt("suport_id");
                String subject = resultSet.getString("subject");
                LocalDateTime help_date = resultSet.getTimestamp("help_date").toLocalDateTime();
                String status = resultSet.getString("status");

                suportList.add(new Suport(playerId, suport_id, subject, help_date, status));
            }

            return suportList;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all suport: " + e.getMessage(), e);
        }
    }
}
