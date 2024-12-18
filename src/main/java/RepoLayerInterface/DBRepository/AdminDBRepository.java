package RepoLayerInterface.DBRepository;

import ModelLayer.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminDBRepository extends DBRepository<Admin> {

    private static Logger logger = LoggerFactory.getLogger(AdminDBRepository.class);

    public AdminDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Admin admin) {
        String sql = "INSERT INTO \"Admin\" (\"user_id\", \"user_name\", \"password\", \"email\", \"salary\", \"access_level\", \"department\") VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, admin.getUser_id());
            statement.setString(2, admin.getUser_name());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getEmail());
            statement.setInt(5, admin.getSalary());
            statement.setInt(6, admin.getAccess_level());
            statement.setString(7, admin.getDepartment());

            statement.execute();
        } catch (SQLException e) {
            logger.error("Error inserting admin into the database", e);
        }
    }

    @Override
    public Admin get(Integer id) {
        String sql = "SELECT * FROM \"Admin\" WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int salary = resultSet.getInt("salary");
                int access_level = resultSet.getInt("access_level");
                String department = resultSet.getString("department");

                return new Admin(user_id, user_name, password, email, salary, access_level, department);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error retrieving admin from the database", e);
            return null;
        }
    }

    @Override
    public void update(Admin admin) {
        String sql = "UPDATE \"Admin\" SET \"user_name\" = ?, \"password\" = ?, \"email\" = ?, \"salary\" = ?, \"access_level\" = ?, \"department\" = ? WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, admin.getUser_name());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getEmail());
            statement.setInt(4, admin.getSalary());
            statement.setInt(5, admin.getAccess_level());
            statement.setString(6, admin.getDepartment());
            statement.setInt(7, admin.getUser_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating admin in the database", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM \"Admin\" WHERE \"user_id\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting admin from the database", e);
        }
    }

    @Override
    public List<Admin> getAll() {
        String sql = "SELECT * FROM \"Admin\"";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Admin> admins = new ArrayList<>();

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int salary = resultSet.getInt("salary");
                int access_level = resultSet.getInt("access_level");
                String department = resultSet.getString("department");

                admins.add(new Admin(user_id, user_name, password, email, salary, access_level, department));
            }

            return admins;
        } catch (SQLException e) {
            logger.error("Error retrieving all admins from the database", e);
            return null;
        }
    }
}
