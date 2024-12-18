package RepoLayerInterface.DBRepository;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import ModelLayer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventDBRepository extends DBRepository<Event> {

    public EventDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    private static Logger logger = LoggerFactory.getLogger(EventDBRepository.class);

    @Override
    public void create(Event event) {
        String sql = "Insert INTO \"Event\" (\"event_id\", \"event_name\", \"oddsList\", \"event_date\", \"sports_type\") VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, event.getEvent_id());
            statement.setString(2, event.getEvent_name());

            ObjectMapper mapper = new ObjectMapper();
            String oddsListJson = mapper.writeValueAsString(event.getOddsList());
            statement.setString(3, oddsListJson);

            statement.setString(4, event.getEvent_date());
            statement.setString(5, event.getSports_type());

            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Event get(Integer id) {
        String sql = "SELECT * FROM \"Event\" WHERE \"event_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                String event_name = resultSet.getString("event_name");

                String oddsListJson = resultSet.getString("oddsList");
                ObjectMapper mapper = new ObjectMapper();
                Map<Odds, Double> oddsList = mapper.readValue(oddsListJson, new TypeReference<Map<Odds, Double>>() {});

                String event_date = resultSet.getString("event_date");
                String sports_type = resultSet.getString("sports_type");

                return new Event(event_id, event_name, oddsList, event_date, sports_type);
            } else {
                return null;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Event event) {
        String sql = "UPDATE \"Event\" SET \"event_name\"=?, \"oddsList\"=?, \"event_date\"=?, \"sports_type\"=? WHERE \"event_id\"=?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, event.getEvent_name());

            ObjectMapper mapper = new ObjectMapper();
            String oddsListJson = mapper.writeValueAsString(event.getOddsList());
            statement.setString(2, oddsListJson);

            statement.setString(3, event.getEvent_date());
            statement.setString(4, event.getSports_type());
            statement.setInt(5, event.getEvent_id());

            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Event\" WHERE \"event_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> getAll() {
        String sql = "SELECT * FROM \"Event\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();

            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                String event_name = resultSet.getString("event_name");

                String oddsListJson = resultSet.getString("oddsList");
                ObjectMapper mapper = new ObjectMapper();
                Map<Odds, Double> oddsList = mapper.readValue(oddsListJson, new TypeReference<Map<Odds, Double>>() {});

                String event_date = resultSet.getString("event_date");
                String sports_type = resultSet.getString("sports_type");

                events.add(new Event(event_id, event_name, oddsList, event_date, sports_type));
            }

            return events;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
