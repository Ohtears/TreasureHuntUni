import java.awt.Point;
import java.util.ArrayList;

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

    public int getID(){
        return id;
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

    public Point getPosition() {
        return position;
    }

    public boolean moveUp() {
        if (position.y > 0) {
            position.translate(0, -1); 
            return true;
        } else {
            System.out.println("Move up not possible. Already at the top edge.");
            return false;
        }
    }

    public boolean moveLeft() {
        if (position.x > 0) {
            position.translate(-1, 0);  
            return true;

        } else {
            System.out.println("Move left not possible. Already at the left edge.");
            return false;

        }
    }
    public boolean moveDown() {
        if (position.y < 9) {
            position.translate(0, 1);  
            return true;

        } else {
            System.out.println("Move down not possible. Already at the bottom edge.");
            return false;

        }
    }


    public boolean moveRight() {
        if (position.x < 9) {
            position.translate(1, 0); 
            return true;

        } else {
            System.out.println("Move right not possible. Already at the right edge.");
            return false;

        }
    }

    public boolean Destroy(char direction ) {

        switch (direction){

            case 'W':
            if (position.y > 0) {

                Point position_player = getPosition(); 
                
                    
                    int x = position_player.x;

                    int y =  position_player.y - 1;


                Gameboardimplement.destroyTrap(x, y);
                return true;

            } else {
                System.out.println("Move up not possible. Already at the top edge.");
                return false;

            }
            
            case 'A':
            if (position.x > 0) {

                Point position_player = getPosition(); 
                

                    
                int x = position_player.x - 1;

                int y =  position_player.y ;

                Gameboardimplement.destroyTrap(x, y);                
                return true;

            } else {
                System.out.println("Move left not possible. Already at the left edge.");
                return false;

            }


            case 'S':
            if (position.y < 9) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x;

                int y =  position_player.y + 1;
                Gameboardimplement.destroyTrap(x, y);
                return true;
  
            } else {
                System.out.println("Move down not possible. Already at the bottom edge.");
                return false;

            }

            case 'D':
            if (position.x < 9) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x + 1;

                int y =  position_player.y;
                Gameboardimplement.destroyTrap(x, y);
                return true;

            } else {
                System.out.println("Move right not possible. Already at the right edge.");
                return false;

            }

        }
        return false;



    }

    public boolean longJump(char direction) {

        switch (direction){

            case 'W':
            if (position.y > 0) {
                position.translate(0, -2); 
                return true;

            } else {
                System.out.println("Move up not possible. Already at the top edge.");
                return false;

            }
            
            case 'A':
            if (position.x > 0) {
                position.translate(-2, 0);  
                return true;

            } else {
                System.out.println("Move left not possible. Already at the left edge.");
                return false;

            }


            case 'S':
            if (position.y < 9) {
                position.translate(0, 2);
                return true;
  
            } else {
                System.out.println("Move down not possible. Already at the bottom edge.");
                return false;

            }

            case 'D':
            if (position.x < 9) {
                position.translate(2, 0); 
                return true;

            } else {
                System.out.println("Move right not possible. Already at the right edge.");
                return false;

            }

        }
        return false;
    }

    public void spawnTrap(char direction) {
    }
}