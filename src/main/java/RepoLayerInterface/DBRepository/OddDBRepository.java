package RepoLayerInterface.DBRepository;

import ModelLayer.Odds;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OddDBRepository extends DBRepository<Odds> {

    public OddDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Odds odds) {
        String sql = "INSERT INTO \"Odds\" (\"odd_id\", \"odd_name\", \"event_type\") VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, odds.getOdd_id());
            statement.setString(2, odds.getOddName());
            statement.setString(3, odds.getEventType());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Odds get(Integer id) {
        String sql = "SELECT * FROM \"Odds\" WHERE \"odd_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int odd_id = resultSet.getInt("odd_id");
                String oddName = resultSet.getString("odd_name");
                String eventType = resultSet.getString("event_type");

                return new Odds(odd_id, oddName, eventType);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Odds odds) {
        String sql = "UPDATE \"Odds\" SET \"odd_name\" = ?, \"event_type\" = ? WHERE \"odd_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, odds.getOddName());
            statement.setString(2, odds.getEventType());
            statement.setInt(3, odds.getOdd_id());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Odds\" WHERE \"odd_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Odds> getAll() {
        String sql = "SELECT * FROM \"Odds\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Odds> oddsList = new ArrayList<>();

            while (resultSet.next()) {
                int odd_id = resultSet.getInt("odd_id");
                String oddName = resultSet.getString("odd_name");
                String eventType = resultSet.getString("event_type");

                oddsList.add(new Odds(odd_id, oddName, eventType));
            }

            return oddsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

