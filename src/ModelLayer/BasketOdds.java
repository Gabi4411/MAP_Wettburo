package ModelLayer;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the odds related to a football event.
 * This class extends the Odds class and includes additional fields specific to football.
 */
public class BasketOdds extends Odds implements Serializable {

    private final String type = "Basket";

    /**
     * Constructs an Odds object with the specified event ID, list of odd values, and event type.
     *
     * @param oddValue the list of odd values for the event
     * @param eventType the type of event (e.g., sport type or category)
     */
    public BasketOdds(List<Double> oddValue, String eventType) {
        super(oddValue, eventType);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + "BasketOdds{" +
                "type='" + type + '\'' +
                '}';
    }
}