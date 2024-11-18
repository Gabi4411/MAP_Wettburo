package Model;

import java.util.Date;

/**
 * Represents an authentication session for a user, containing login attempts and last login information.
 */
public class Authentification {
    private int auth_id;
    private User user;
    private int login_attempts;
    private Date last_login;

    /**
     * Constructs an Authentification object with the specified details.
     *
     * @param auth_id        the unique ID of the authentication session
     * @param user           the user associated with this authentication
     * @param login_attempts the number of login attempts for this session
     * @param last_login     the date and time of the last successful login
     */

    public Authentification(int auth_id, User user, int login_attempts, Date last_login) {
        this.auth_id = auth_id;
        this.user = user;
        this.login_attempts = login_attempts;
        this.last_login = last_login;
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
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for this authentication session.
     *
     * @param user the user to be set
     */
    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", login_attempts=" + login_attempts +
                ", last_login=" + last_login +
                '}';
    }
}
