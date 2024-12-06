package ModelLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the odds related to a football event.
 * This class extends the Odds class and includes additional fields specific to football.
 */
public class FootballOdds extends Odds{

    private final String type = "Football";

    /**
     * Constructs an Odds object with the specified event ID, list of odd values, and event type.
     *
     * @param oddValue the list of odd values for the event
     * @param eventType the type of event (e.g., sport type or category)
     */
    public FootballOdds(List<Double> oddValue, String eventType) {
        super(oddValue, eventType);
    }
    public FootballOdds() {
        super(new ArrayList<>(), "");
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + "FootballOdds{" +
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

    public static FootballOdds fromCSV(String csvLine) {
        String[] parts = csvLine.split(";", 2);
        List<Double> oddValue = Arrays.stream(parts[0].split(","))
                .map(Double::parseDouble)
                .toList();
        return new FootballOdds(oddValue, parts[1]);
    }
}
