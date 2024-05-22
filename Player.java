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

    public void replaceAbilities(String ability){
        switch (ability){

            case "Destroy":
                this.Ability_destruction = this.Ability_destruction - 1;
                break;

            case "Long_Jump":
                this.Ability_long_jump = this.Ability_long_jump - 1;
                break;


            case "Spawn_Trap":
                this.Ability_spawn_trap = this.Ability_spawn_trap - 1;
                break;

        }

    }

    public void replaceHp(int hp){

        this.hp = this.hp + hp;

    }

    public void replaceScore(int score){

        this.score = this.score + score;

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

    public void moveUp(Player player) {
        if (position.y > 0) {
            if (Gameboardimplement.MoveChecker(position.x, position.y - 1, player)){
                position.translate(0, -1); 
            }
            else {
                System.out.println("You cannot move there!");
            }
        }
        else {
            System.out.println("Move upwards not possible. Already at the top edge.");
        }
    }

    public void moveLeft(Player player) {
        if (position.x > 0) {
            if (Gameboardimplement.MoveChecker(position.x - 1, position.y, player)){
                position.translate(-1, 0);  
            }
            else {
                System.out.println("You cannot move there!");
            }
            
        } else {
            System.out.println("Move leftwards not possible. Already at the left edge.");

        }
    }
    public void moveDown(Player player) {
        if (position.y < 9) {
            if (Gameboardimplement.MoveChecker(position.x, position.y + 1, player)){

                position.translate(0, 1);  
            }
            else {
                System.out.println("You cannot move there!");
            }
        } else {
            System.out.println("Move downwards not possible. Already at the bottom edge.");

        }
    }


    public void moveRight(Player player) {
        if (position.x < 9) {
            if (Gameboardimplement.MoveChecker(position.x + 1, position.y, player)){

                position.translate(1, 0); 
            }
            else {
                System.out.println("You cannot move there!");
            }
        } else {
            System.out.println("Move rightwards not possible. Already at the right edge.");

        }
    }

    public void Destroy(char direction, Player player) {

        switch (direction){

            case 'W':
            if (position.y > 0) {

                Point position_player = getPosition(); 
            
                int x = position_player.x;

                int y =  position_player.y - 1;


                Gameboardimplement.destroyTrap(x, y, player);

            } else {
                System.out.println("You can't destroy that!");

            }
            break;
            case 'A':
            if (position.x > 0) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x - 1;

                int y =  position_player.y ;

                Gameboardimplement.destroyTrap(x, y, player);                

            } else {
                System.out.println("You can't destroy that!");

            }
            break;

            case 'S':
            if (position.y < 9) {

                Point position_player = getPosition(); 
                
                int x = position_player.x;

                int y =  position_player.y + 1;
                Gameboardimplement.destroyTrap(x, y, player);

            } else {
                System.out.println("You can't destroy that!");

            }
            break;
            case 'D':
            if (position.x < 9) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x + 1;

                int y =  position_player.y;
                Gameboardimplement.destroyTrap(x, y, player);

            } else {
                System.out.println("You can't destroy that!");

            }
            break;
        }
    }
    
    public void spawnTrap(char direction, Player player) {

        switch (direction){

            case 'W':
            if (position.y > 0) {

                Point position_player = getPosition(); 
                
                int x = position_player.x;

                int y =  position_player.y - 1;


                Gameboardimplement.setTrap(x, y, player);

            } else {
                System.out.println("You can't place it there");

            }
            break;
            
            case 'A':
            if (position.x > 0) {

                Point position_player = getPosition(); 
                
                int x = position_player.x - 1;

                int y =  position_player.y ;

                Gameboardimplement.setTrap(x, y, player);                

            } else {
                System.out.println("You can't place it there");

            }
            break;
            

            case 'S':
            if (position.y < 9) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x;

                int y =  position_player.y + 1;
                
                Gameboardimplement.setTrap(x, y, player);

            } else {
                System.out.println("You can't place it there");

            }
            break;
            case 'D':
            if (position.x < 9) {

                Point position_player = getPosition(); 
                
                int x = position_player.x + 1;
                
                int y =  position_player.y;
                Gameboardimplement.setTrap(x, y, player);

            } else {
                System.out.println("You can't place it there");
            }
            break;
        }
    }
    
        public void longJump(char direction, Player player) {
    
            switch (direction){
    
                case 'W':
                if (this.position.y > 0) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x, position.y - 2, player)){

                        
                            this.position.translate(0, -2); 
                            this.Ability_long_jump --;
                        }
                        else {

                            System.out.println("You cannot jump there!");
                        }
                        }
                } else {
                    System.out.println("LongJump upwards not possible.");
                }
                break;
                case 'A':
                if (this.position.x > 0) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x - 2, position.y, player)){

                            this.position.translate(-2, 0);  
                            this.Ability_long_jump --;
                    }
                        else {

                            System.out.println("You cannot jump there!");
                        }
                    }
                } else {
                    System.out.println("LongJump leftwards not possible.");

                }
                break;
    
                case 'S':
                if (this.position.y < 9) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x, position.y + 2, player)){


                            this.position.translate(0, 2);
                            this.Ability_long_jump --;
                        }
                        else {

                            System.out.println("You cannot jump there!");
                        }
                            
                    }
                } else {
                    System.out.println("LongJump downwards not possible.");
                    
                }
                break;
    
                case 'D':
                if (this.position.x < 9) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x + 2, position.y, player)){


                            this.position.translate(2, 0); 
                            this.Ability_long_jump --;
                        }
                        else {

                            System.out.println("You cannot jump there!");
                        }
                    }
                } else {
                    System.out.println("LongJump rightwards not possible.");
                }
                break;
            
        }
    }}