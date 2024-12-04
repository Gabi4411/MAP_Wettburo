package ModelLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the odds related to a football event.
 * This class extends the Odds class and includes additional fields specific to football.
 */
public class BasketOdds extends Odds{

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
    public BasketOdds() {
        super(new ArrayList<>(), "");
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

    @Override
    public String toCSV() {
        List<Double> oddValue = getOdd_value();
        return String.join(";",
                getType(),
                String.join(",", oddValue.stream().map(String::valueOf).toArray(String[]::new)),
                getEventType()
        );
    }

    public static BasketOdds fromCSV(String csvLine) {
        String[] parts = csvLine.split(";", 2);
        List<Double> oddValue = Arrays.stream(parts[0].split(","))
                .map(Double::parseDouble)
                .toList();
        return new BasketOdds(oddValue, parts[1]);
    }
}