package ModelLayer;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a support request submitted by a user, with details about the request's subject, date, and status.
 */
public class Suport implements HasId{
    private int playerId;
    private int suport_id;
    private String subject;
    private LocalDateTime help_date;
    private String status;

    /**
     * Constructs a Support object with specified details.
     *
     * @param playerId       the user who submitted the support request
     * @param suport_id      the unique ID of the support request
     * @param subject        the subject of the support request
     * @param help_date      the date of the support request
     * @param status         the current status of the support request
     */
    public Suport(int playerId, int suport_id, String subject, LocalDateTime help_date, String status) {
        this.playerId = playerId;
        this.suport_id = suport_id;
        this.subject = subject;
        this.help_date = help_date;
        this.status = status;
    }

    /**
     * Gets the user associated with this support request.
     *
     * @return the user who submitted the support request
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the user associated with this support request.
     *
     * @param playerId the user to be set for this support request
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Gets the ID of the support request.
     *
     * @return the support request ID
     */
    public int getSuport_id() {
        return suport_id;
    }

    /**
     * Sets the ID of the support request.
     *
     * @param suport_id the support request ID to be set
     */
    public void setSuport_id(int suport_id) {
        this.suport_id = suport_id;
    }

    /**
     * Gets the subject of the support request.
     *
     * @return the subject of the support request
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the support request.
     *
     * @param subject the subject to be set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the date when the support request was created.
     *
     * @return the date of the support request
     */
    public LocalDateTime getHelp_date() {
        return help_date;
    }

    /**
     * Sets the date when the support request was created.
     *
     * @param help_date the date to be set
     */
    public void setHelp_date(LocalDateTime help_date) {
        this.help_date = help_date;
    }

    /**
     * Gets the status of the support request.
     *
     * @return the status of the support request (e.g., pending, resolved)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the support request.
     *
     * @param status the status to be set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the Suport object, including user, support ID, subject, help date, and status.
     *
     * @return a string representation of the Suport object
     */
    @Override
    public String toString() {
        return "Suport{" +
                "playerId=" + playerId +
                ", suport_id=" + suport_id +
                ", subject='" + subject + '\'' +
                ", help_date=" + help_date +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Suport)) return false;
        Suport suport = (Suport) o;
        return playerId == suport.playerId &&
                suport_id == suport.suport_id &&
                Objects.equals(subject, suport.subject) &&
                Objects.equals(help_date, suport.help_date) &&
                Objects.equals(status, suport.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, suport_id, subject, help_date, status);
    }

    @Override
    public int getId() {
        return suport_id;
    }

    @Override
    public void setId(int id) {
        this.suport_id = id;
    }
}
