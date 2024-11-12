package ModelLayer;

import java.util.Date;

public class Authentification {
    private int auth_id;
    private User user;
    private int login_attempts;
    private Date last_login;

    public Authentification(int auth_id, User user, int login_attempts, Date last_login) {
        this.auth_id = auth_id;
        this.user = user;
        this.login_attempts = login_attempts;
        this.last_login = last_login;
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
