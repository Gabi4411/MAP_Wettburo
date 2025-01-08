package ModelLayer;

import java.util.Date;
import java.util.Objects;

/**
 * Represents an authentication session for a user, containing login attempts and last login information.
 */
public class Authentification implements HasId{
    private int auth_id;
    private int user_id;
    private int login_attempts;
    private Date last_login;

    // Constructors
    public Authentification(int auth_id, int user_id, int login_attempts, Date last_login) {
        this.auth_id = auth_id;
        this.user_id = user_id;
        this.login_attempts = login_attempts;
        this.last_login = last_login;
    }

    public Authentification() {
        this(0, 0, 0, new Date(0));
    }

    // Getters and Setters
    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLogin_attempts() {
        return login_attempts;
    }

    public void setLogin_attempts(int login_attempts) {
        this.login_attempts = login_attempts;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    // Override equals() for logical equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentification that = (Authentification) o;
        return auth_id == that.auth_id &&
                user_id == that.user_id &&
                login_attempts == that.login_attempts &&
                Objects.equals(last_login, that.last_login);
    }

    // Override hashCode() for efficient hash-based collections
    @Override
    public int hashCode() {
        return Objects.hash(auth_id, user_id, login_attempts, last_login);
    }

    // Override toString() for better debugging
    @Override
    public String toString() {
        return "Authentification{" +
                "auth_id=" + auth_id +
                ", user_id=" + user_id +
                ", login_attempts=" + login_attempts +
                ", last_login=" + last_login +
                '}';
    }

    @Override
    public int getId() {
        return auth_id;
    }

    @Override
    public void setId(int id) {
        this.auth_id = id;
    }
}
