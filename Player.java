import java.awt.Point;

public class Player {
    private int id;
    private int hp;
    private Point position;
    private int score;

    public Player(int id, Point startingPosition) {
        this.id = id;
        this.hp = 5; // Example initial HP
        this.position = startingPosition;
        this.score = 0;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    // Other methods like move, setTrap, destroyTrap, longJump...
}