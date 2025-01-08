package ModelLayer;

import java.util.Objects;

import java.io.Serializable;

/**
 * Represents the odds associated with a specific event in a sports betting system.
 * This is an abstract class that can be extended by specific types of odds implementations.
 */
public class Odds implements HasId{

    private String oddName;
    private int odd_id;
    private String eventType;

    public Odds(int odd_id, String oddName, String eventType) {
        this.odd_id = odd_id;
        this.oddName = oddName;
        this.eventType = eventType;
    }

    public String getOddName() {
        return oddName;
    }

    public void setOddName(String oddName) {
        this.oddName = oddName;
    }

    public int getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(int odd_id) {
        this.odd_id = odd_id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Odds{" +
                "oddID='" + odd_id + '\'' +
                ", oddName=" + oddName +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Odds)) return false;
        Odds odds = (Odds) o;
        return odd_id == odds.odd_id &&
                Objects.equals(oddName, odds.oddName) &&
                Objects.equals(eventType, odds.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odd_id, oddName, eventType);
    }

    @Override
    public int getId() {
        return odd_id;
    }

    @Override
    public void setId(int id) {
        this.odd_id = id;
    }
}
