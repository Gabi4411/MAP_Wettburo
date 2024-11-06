package ModelLayer;

import java.util.Date;
public class Suport {
    private User user;
    private int suport_id;
    private String subject;
    private Date help_date;
    private String status;

    public Suport(User user, int suport_id, String subject, Date help_date, String status) {
        this.user = user;
        this.suport_id = suport_id;
        this.subject = subject;
        this.help_date = help_date;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSuport_id() {
        return suport_id;
    }

    public void setSuport_id(int suport_id) {
        this.suport_id = suport_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getHelp_date() {
        return help_date;
    }

    public void setHelp_date(Date help_date) {
        this.help_date = help_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
