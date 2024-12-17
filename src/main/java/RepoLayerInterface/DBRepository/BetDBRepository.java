package RepoLayerInterface.DBRepository;
import ModelLayer.Bet;
import ModelLayer.Event;
import ModelLayer.Odds;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;



public class BetDBRepository extends DBRepository<Bet> {

    public BetDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Bet bet) {
        String sql = "Insert INTO \"Bet\" (\"bet_id\", \"player_id\", \"event\", \"amount\", \"bet_date\", \"bet_status\") VALUES (?,?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, bet.getBet_id());
            statement.setInt(2, bet.getPlayer_id());
            statement.setArray(3, conn.createArrayOf("TEXT", bet.getEvent().toArray()));
            statement.setInt(4, bet.getAmount());
            statement.setDate(5, Date.valueOf(bet.getBet_date().toLocalDate()));
            statement.setString(6, bet.getBetstatus());

            statement.execute();
        } catch (SQLException e) {
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

                Array eventArray = resultSet.getArray("event");
                List<Event> events = new ArrayList<>();
                String[] eventStrings = (String[]) eventArray.getArray();
                for (String eventStr : eventStrings) {
                    String[] eventParts = eventStr.split(",");
                    int event_id = Integer.parseInt(eventParts[0]);
                    String event_name = eventParts[1];
                    Map<Odds, Double> oddsMap = new HashMap<>();
                    for (int i = 2; i < eventParts.length; i += 4) {
                        Odds odds = new Odds(
                                Integer.parseInt(eventParts[i]),
                                eventParts[i + 1],
                                eventParts[i + 2]
                        );
                        oddsMap.put(odds, Double.parseDouble(eventParts[i + 3]));
                    }

                    Event event = new Event(event_id, event_name, oddsMap, eventParts[4], eventParts[5]);
                    events.add(event);
                }

                int amount = resultSet.getInt("amount");
                LocalDateTime bet_date = resultSet.getTimestamp("bet_date").toLocalDateTime();
                String bet_status = resultSet.getString("bet_status");

                return new Bet(bet_id, player_id, events, amount, bet_date, bet_status);
            } else {
                return null;
            }
        } catch (SQLException e) {
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
            statement.setArray(3, conn.createArrayOf("TEXT", bet.getEvent().toArray()));
            statement.setInt(4, bet.getAmount());
            statement.setDate(5, Date.valueOf(bet.getBet_date().toLocalDate()));
            statement.setString(6, bet.getBetstatus());

            statement.execute();

        } catch (SQLException e) {
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


    public Bet find_by_ID(Integer id){
        String sql = "SELECT * FROM \"Bet\" WHERE \"bet_id\" = ?";

        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int bet_id = resultSet.getInt("bet_id");
                int player_id = resultSet.getInt("player_id");

                Array eventArray = resultSet.getArray("event");
                List<Event> events = new ArrayList<>();
                String[] eventStrings = (String[]) eventArray.getArray();
                for (String eventStr : eventStrings) {
                    String[] eventParts = eventStr.split(",");
                    int event_id = Integer.parseInt(eventParts[0]);
                    String event_name = eventParts[1];
                    Map<Odds, Double> oddsMap = new HashMap<>();
                    for (int i = 2; i < eventParts.length; i += 4) {
                        Odds odds = new Odds(
                                Integer.parseInt(eventParts[i]),
                                eventParts[i + 1],
                                eventParts[i + 2]
                        );
                        oddsMap.put(odds, Double.parseDouble(eventParts[i + 3]));
                    }

                    Event event = new Event(event_id, event_name, oddsMap, eventParts[4], eventParts[5]);
                    events.add(event);
                }

                int amount = resultSet.getInt("amount");
                LocalDateTime bet_date = resultSet.getTimestamp("bet_date").toLocalDateTime();
                String bet_status = resultSet.getString("bet_status");

                return new Bet(bet_id, player_id, events, amount, bet_date, bet_status);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

                // Preluăm evenimentele ca array de texte
                Array eventArray = resultSet.getArray("event");
                List<Event> events = new ArrayList<>();
                String[] eventStrings = (String[]) eventArray.getArray();
                for (String eventStr : eventStrings) {
                    // Despachetăm fiecare eveniment și creăm un obiect Event
                    String[] eventParts = eventStr.split(",");
                    int event_id = Integer.parseInt(eventParts[0]);
                    String event_name = eventParts[1];
                    Map<Odds, Double> oddsMap = new HashMap<>();
                    for (int i = 2; i < eventParts.length; i += 4) {
                        Odds odds = new Odds(
                                Integer.parseInt(eventParts[i]),
                                eventParts[i + 1],
                                eventParts[i + 2]
                        );
                        oddsMap.put(odds, Double.parseDouble(eventParts[i + 3]));
                    }

                    Event event = new Event(event_id, event_name, oddsMap, eventParts[4], eventParts[5]);
                    events.add(event);
                }

                int amount = resultSet.getInt("amount");
                LocalDateTime bet_date = resultSet.getTimestamp("bet_date").toLocalDateTime();
                String bet_status = resultSet.getString("bet_status");

                // Adăugăm fiecare Bet la lista de Bets
                bets.add(new Bet(bet_id, player_id, events, amount, bet_date, bet_status));
            }

            return bets;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

