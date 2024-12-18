package ModelLayer;

import java.util.Date;

/**
 * Represents an authentication session for a user, containing login attempts and last login information.
 */
public class Authentification {
    private int auth_id;
    private int user_id;
    private int login_attempts;
    private Date last_login;

    /**
     * Constructs an Authentification object with the specified details.
     *
     * @param auth_id        the unique ID of the authentication session
     * @param user_id           the user associated with this authentication
     * @param login_attempts the number of login attempts for this session
     * @param last_login     the date and time of the last successful login
     */

    public Authentification(int auth_id, int user_id, int login_attempts, Date last_login) {
        this.auth_id = auth_id;
        this.user_id = user_id;
        this.login_attempts = login_attempts;
        this.last_login = last_login;
    }

    public Authentification() {
        this.auth_id = 0;
        this.user_id = 0;
        this.login_attempts = 0;
        this.last_login = new Date(0); // Default to epoch time
    }

    /**
     * Gets the authentication ID.
     *
     * @return the authentication ID
     */
    public int getAuth_id() {
        return auth_id;
    }

    /**
     * Sets the authentication ID.
     *
     * @param auth_id the authentication ID to be set
     */
    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    /**
     * Gets the user associated with this authentication session.
     *
     * @return the user associated with this authentication session
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the user for this authentication session.
     *
     * @param user_id the user to be set
     */
    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the number of login attempts for this authentication session.
     *
     * @return the number of login attempts
     */
    public int getLogin_attempts() {
        return login_attempts;
    }

    /**
     * Sets the number of login attempts for this authentication session.
     *
     * @param login_attempts the number of login attempts to be set
     */
    public void setLogin_attempts(int login_attempts) {
        this.login_attempts = login_attempts;
    }

    /**
     * Gets the date and time of the last successful login.
     *
     * @return the last login date and time
     */
    public Date getLast_login() {
        return last_login;
    }

    /**
     * Sets the date and time of the last successful login.
     *
     * @param last_login the last login date and time to be set
     */

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }


    /**
     * Returns a string representation of the Authentificaton object, including auth_id, user, login_attempts and last log_in
     *
     * @return a string representation of the Authentification object
     */
    @Override
    public String toString() {
        return "Authentification{" +
                "auth_id=" + auth_id +
                ", user=" + user_id +
                ", login_attempts=" + login_attempts +
                ", last_login=" + last_login +
                '}';
    }

    /**
     * Converts this Authentification object to a CSV string.
     *
     * @return a CSV representation of the Authentification object
     */
    public String toCSV() {
        return auth_id + "," + user_id + "," + login_attempts + "," + last_login.getTime();
    }

    /**
     * Creates an Authentification object from a CSV string.
     *
     * @param csvLine the CSV string
     * @return the Authentification object created from the CSV string
     */
    public static Authentification fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        int auth_id = Integer.parseInt(values[0]);
        int user_id = Integer.parseInt(values[1]); // Fetch user by ID
        int login_attempts = Integer.parseInt(values[2]);
        Date last_login = new Date(Long.parseLong(values[3]));

        // Assuming we have a UserRepo or another way to fetch the User object by user_id
        User user = fetchUserById(user_id);  // This method needs to be defined elsewhere

        return new Authentification(auth_id, user_id, login_attempts, last_login);
    }

    /**
     * Fetches a User object by its ID.
     * (You would replace this with actual logic for fetching a user from a repository or database.)
     *
     * @param user_id the ID of the user to fetch
     * @return a User object corresponding to the provided user_id
     */
    private static User fetchUserById(int user_id) {
        // This is a placeholder method. You can fetch the user from a repository like UserRepo
        // For now, let's assume the user is an Admin with a default constructor (you would ideally fetch the real user)
        return new Admin(user_id, "Example Name", "password", "email@example.com", 5000, 1, "IT");
    }
}
