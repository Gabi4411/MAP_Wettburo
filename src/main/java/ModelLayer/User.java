package ModelLayer;

import java.util.Objects;

/**
 * Represents an abstract user with basic information such as ID, name, password, and email.
 * This class is intended to be extended by specific types of users.
 */
public abstract class User implements HasId{
    private int user_id;
    private String user_name;
    private String password;
    private String email;

    /**
     * Constructs a User with the specified details.
     *
     * @param user_id   the unique identifier for the user
     * @param user_name the username of the user
     * @param password  the password of the user
     * @param email     the email address of the user
     */
    public User(int user_id, String user_name, String password, String email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the user ID.
     *
     * @param user_id the new user ID
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Sets the username.
     *
     * @param user_name the new username
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string containing the user's details
     */
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return user_id == user.user_id &&
                user_name.equals(user.user_name) &&
                password.equals(user.password) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_name, password, email);
    }

    @Override
    public int getId() {
        return user_id;
    }

    @Override
    public void setId(int id) {
        this.user_id = id;
    }
 }
