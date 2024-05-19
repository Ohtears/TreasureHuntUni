import java.awt.Point;

public class Player {
    private int id;
    private int hp;
    private Point position;
    private int score;

    private int Ability_destruction;
    private int Ability_long_jump;
    private int Ability_spawn_trap;


    public Player(int id, Point startingPosition) {
        this.id = id;
        this.hp = 5; 
        this.position = startingPosition;
        this.score = 0;
        this.Ability_destruction = 3;
        this.Ability_long_jump = 3;
        this.Ability_spawn_trap = 6;

    }

    public Point getPosition() {
        return position;
    }

    public int getScore(){

        return score;

    }
    public int getHp(){

        return hp;
    }

    public int getAbilities(String ability){

        switch (ability){

            case "Destroy":
                return Ability_destruction;
            

            case "Long_Jump":
                return Ability_long_jump;
            

            case "Spawn_Trap":
                return Ability_spawn_trap;
            

            default:
                return 0;

        }

    }
    public String getAbilities(){

        return "Destruction : " + Ability_destruction + "| Long Jump: "+ Ability_long_jump + "| Spawn Traps: " + Ability_spawn_trap;

        }

    
    public void setPosition(Point position) {
        this.position = position;
    }

    public void moveUp() {
        position.translate(0, -1);
    }

    public void moveLeft() {
        position.translate(-1, 0);
    }

    public void moveDown() {
        position.translate(0, 1);
    }

    public void moveRight() {
        position.translate(1, 0);
    }

    public void Destroy(char direction) {
    }

    public void longJump(char direction) {

    }

    public void spawnTrap(char direction) {
    }
}