package RepoLayerInterface.DBRepository;
import ModelLayer.Bet;
import ModelLayer.Event;
import ModelLayer.Odds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BetDBRepository extends DBRepository<Bet> {

    public BetDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }


    private static Logger logger = LoggerFactory.getLogger(BetDBRepository.class);

    @Override
    public void create(Bet bet) {
        String sql = "Insert INTO \"Bet\" (\"bet_id\", \"player_id\", \"event\", \"amount\", \"bet_date\", \"bet_status\") VALUES (?,?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, bet.getBet_id());
            statement.setInt(2, bet.getPlayer_id());

            ObjectMapper mapper = new ObjectMapper();
            String eventJson = mapper.writeValueAsString(bet.getEvent());
            statement.setString(3, eventJson);
            statement.setInt(4, bet.getAmount());
            statement.setDate(5, Date.valueOf(bet.getBet_date().toLocalDate()));
            statement.setString(6, bet.getBetstatus());

            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Bet get(Integer id) {
        String sql = "SELECT * FROM \"Bet\" WHERE \"bet_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int bet_id = resultSet.getInt("bet_id");
                int player_id = resultSet.getInt("player_id");

                String eventJson = resultSet.getString("event");

                ObjectMapper mapper = new ObjectMapper();
                List<Event> events = mapper.readValue(eventJson, mapper.getTypeFactory().constructCollectionType(List.class,Event.class));

                int amount = resultSet.getInt("amount");
                LocalDateTime bet_date = resultSet.getTimestamp("bet_date").toLocalDateTime();
                String bet_status = resultSet.getString("bet_status");

                return new Bet(bet_id, player_id, events, amount, bet_date, bet_status);
            } else {
                return null;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Bet bet) {
        String sql = "UPDATE\"Bet\"SET \"bet_id\"=?, \"player_id\"=?, \"event\"=?, \"amount\"=?, \"bet_date\"=?, \"bet_status\"=? WHERE \"bet_id\"=?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, bet.getBet_id());
            statement.setInt(2, bet.getPlayer_id());

            ObjectMapper mapper = new ObjectMapper();
            String eventJson = mapper.writeValueAsString(bet.getEvent());
            statement.setString(3, eventJson);
            statement.setInt(4, bet.getAmount());
            statement.setDate(5, Date.valueOf(bet.getBet_date().toLocalDate()));
            statement.setString(6, bet.getBetstatus());

            statement.execute();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Bet\" WHERE \"bet_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Bet> getAll() {
        String sql = "SELECT * FROM \"Bet\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Bet> bets = new ArrayList<>();

            while (resultSet.next()) {


                // Extragem datele pentru fiecare Bet
                int bet_id = resultSet.getInt("bet_id");
                int player_id = resultSet.getInt("player_id");

                String eventJson = resultSet.getString("event");
                ObjectMapper mapper = new ObjectMapper();
                List<Event> events = mapper.readValue(eventJson,mapper.getTypeFactory().constructCollectionType(List.class, Event.class));

                int amount = resultSet.getInt("amount");
                LocalDateTime bet_date = resultSet.getTimestamp("bet_date").toLocalDateTime();
                String bet_status = resultSet.getString("bet_status");

                bets.add(new Bet(bet_id, player_id, events, amount, bet_date, bet_status));
            }

            return bets;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

