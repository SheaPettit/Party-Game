package players;
public class Player {
    private int points;
    private String name;
    private final int id;
    private static int nextId = 0;
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.id = ++nextId;
    }
    public void addPoints(int points) {
        if(points < 0) {
            throw new IllegalArgumentException("Cannot add negative points");
        }
        this.points += points;
    }
    public void subPoints(int points) {
        if(points < 0) {
            throw new IllegalArgumentException("Cannot subtract negative points");
        }
        this.points -= points;
        if (this.points < 0) this.points = 0;
    }
    public void rename(String name) {
        this.name = name;
    }
    public int getId() {return this.id;}
    public int getPoints() {return this.points;}
    public String getName() {return this.name;}
}