package ModelLayer;

public abstract class Odds {
    private int id;
    private int odd_tream1;
    private  int odd_tream2;
    Event event;

    public Odds(int odd_tream1, int id, int odd_tream2, Event event) {
        this.odd_tream1 = odd_tream1;
        this.id = id;
        this.odd_tream2 = odd_tream2;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public int getOdd_tream1() {
        return odd_tream1;
    }

    public int getOdd_tream2() {
        return odd_tream2;
    }

    public Event getEvent() {
        return event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOdd_tream1(int odd_tream1) {
        this.odd_tream1 = odd_tream1;
    }

    public void setOdd_tream2(int odd_tream2) {
        this.odd_tream2 = odd_tream2;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
