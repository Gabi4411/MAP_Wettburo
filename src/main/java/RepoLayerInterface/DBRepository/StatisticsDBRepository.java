package RepoLayerInterface.DBRepository;

import java.sql.*;
import java.util.*;
import ModelLayer.Statistics;

public class StatisticsDBRepository extends DBRepository<Statistics> {

    public StatisticsDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    // Metoda pentru a crea o nouă statistică în baza de date
    @Override
    public void create(Statistics statistics) {
        String sql = "INSERT INTO \"Statistics\" (\"stat_id\", \"event_id\", \"eventDescription\", \"eventPrediction\") VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, statistics.getStat_id());
            statement.setInt(2, statistics.getEvent_id());
            statement.setString(3, statistics.getEventDescription());
            statement.setString(4, statistics.getEventPrediction());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating statistics: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a obține o statistică din baza de date
    @Override
    public Statistics get(Integer id) {
        String sql = "SELECT * FROM \"Statistics\" WHERE \"stat_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int stat_id = resultSet.getInt("stat_id");
                int event_id = resultSet.getInt("event_id");
                String eventDescription = resultSet.getString("eventDescription");
                String eventPrediction = resultSet.getString("eventPrediction");

                return new Statistics(stat_id, event_id, eventDescription, eventPrediction);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving statistics: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a actualiza o statistică existentă
    @Override
    public void update(Statistics statistics) {
        String sql = "UPDATE \"Statistics\" SET \"event_id\" = ?, \"eventDescription\" = ?, \"eventPrediction\" = ? WHERE \"stat_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, statistics.getEvent_id());
            statement.setString(2, statistics.getEventDescription());
            statement.setString(3, statistics.getEventPrediction());
            statement.setInt(4, statistics.getStat_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating statistics: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a șterge o statistică din baza de date
    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Statistics\" WHERE \"stat_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting statistics: " + e.getMessage(), e);
        }
    }

    // Metoda pentru a obține toate statisticile din baza de date
    @Override
    public List<Statistics> getAll() {
        String sql = "SELECT * FROM \"Statistics\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Statistics> statisticsList = new ArrayList<>();
            while (resultSet.next()) {
                int stat_id = resultSet.getInt("stat_id");
                int event_id = resultSet.getInt("event_id");
                String eventDescription = resultSet.getString("eventDescription");
                String eventPrediction = resultSet.getString("eventPrediction");

                statisticsList.add(new Statistics(stat_id, event_id, eventDescription, eventPrediction));
            }

            return statisticsList;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all statistics: " + e.getMessage(), e);
        }
    }
}
