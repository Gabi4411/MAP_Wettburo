package RepoLayerInterface.DBRepository;

import ModelLayer.Authentification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.sql.Date;

public class AuthentificationDBRepository extends DBRepository<Authentification> {

    public AuthentificationDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Authentification authentification) {
        String sql = "INSERT INTO \"Authentification\" (\"auth_id\", \"user_id\", \"login_attempts\", \"last_login\") VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, authentification.getAuth_id());
            statement.setInt(2, authentification.getUser_id()); // Folosim user_id direct
            statement.setInt(3, authentification.getLogin_attempts());
            statement.setDate(4, Date.valueOf(authentification.getLast_login().toString())); // Conversia LocalDateTime la Date

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating authentification: " + e.getMessage(), e);
        }
    }

    @Override
    public Authentification get(Integer id) {
        String sql = "SELECT * FROM \"Authentification\" WHERE \"auth_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int auth_id = resultSet.getInt("auth_id");
                int user_id = resultSet.getInt("user_id");  // Se ia direct user_id
                int login_attempts = resultSet.getInt("login_attempts");
                Date last_login = resultSet.getDate("last_login");

                return new Authentification(auth_id, user_id, login_attempts, last_login);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving authentification: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Authentification authentification) {
        String sql = "UPDATE \"Authentification\" SET \"user_id\" = ?, \"login_attempts\" = ?, \"last_login\" = ? WHERE \"auth_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, authentification.getUser_id());
            statement.setInt(2, authentification.getLogin_attempts());
            statement.setDate(3, Date.valueOf(authentification.getLast_login().toString())); // Conversia LocalDateTime la Date
            statement.setInt(4, authentification.getAuth_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating authentification: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Authentification\" WHERE \"auth_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting authentification: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Authentification> getAll() {
        String sql = "SELECT * FROM \"Authentification\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Authentification> authentificationsList = new ArrayList<>();
            while (resultSet.next()) {
                int auth_id = resultSet.getInt("auth_id");
                int user_id = resultSet.getInt("user_id");  // Se ia direct user_id
                int login_attempts = resultSet.getInt("login_attempts");
                Date last_login = resultSet.getDate("last_login");

                authentificationsList.add(new Authentification(auth_id, user_id, login_attempts, last_login));
            }

            return authentificationsList;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all authentifications: " + e.getMessage(), e);
        }
    }
}
