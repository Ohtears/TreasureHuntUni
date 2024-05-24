import java.awt.Point;
import java.util.List;

import org.json.JSONObject;

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

    public Player(){
        
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

    public void replaceAbilitiesforspin(String ability){
        switch (ability){

            case "Destroy":
                this.Ability_destruction = this.Ability_destruction + 1;
                break;

            case "Long_Jump":
                this.Ability_long_jump = this.Ability_long_jump + 1;
                break;


            case "Spawn_Trap":
                this.Ability_spawn_trap = this.Ability_spawn_trap + 1;
                break;

        }

    }

    public void changeLong_Jump(int Abilitylongjump){

        this.Ability_long_jump = Abilitylongjump;

    }

    public void changeDestroy(int AbilityDestroy){

        this.Ability_destruction = AbilityDestroy;

    }
    public void changeSpawn_Trap(int Abilityspawntrap){

        this.Ability_spawn_trap = Abilityspawntrap;

    }


    public void replaceHp(int hp){

        this.hp = this.hp + hp;

    }

    public void changehp(int hp){

        this.hp = hp;
    }

    public void replaceScore(int score){

        this.score = this.score + score;


    }
    public void changeScore(int score){

        this.score = score;

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

    public void moveUp(Player player, List<Player> players, Playermode mode) {

        int boardWidth=10;
        int boardHeight=10;

        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }



        if (player.getPosition().y > 0) {
            if ((player.getPosition().x != 0 || player.getPosition().y - 1 != boardHeight - 1) &&
                (player.getPosition().x != boardWidth - 1 || player.getPosition().y - 1 != 0)) {
    
                if (Gameboardimplement.MoveChecker(player.getPosition().x, player.getPosition().y - 1, player, players)) {
                    if (Gameboardimplement.collision(player.getPosition().x, player.getPosition().y - 1, player, players)) {
                        player.getPosition().translate(0, -1);
                        JSONObject log = new JSONObject();
                        String symbol = Tile.TileSymbol(new Point(player.getPosition()));
                        log.put("movement " + "Player " + player.getID(), symbol);
                        FileHandler.appendlog(log);
                    }
                }
            } else {
                System.out.println("You cannot move to spawn");
            }
        } else {
            System.out.println("Move upwards not possible. Already at the top edge.");
        }
    }

    public void moveLeft(Player player, List<Player> players, Playermode mode) {
        int boardWidth = 10;
        int boardHeight = 10;

        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }
        if (player.getPosition().x > 0) {
            if ((player.getPosition().x - 1 != 0 || player.getPosition().y != boardHeight - 1) &&
                (player.getPosition().x - 1 != boardWidth - 1 || player.getPosition().y != 0)) {
    
                if (Gameboardimplement.MoveChecker(player.getPosition().x - 1, player.getPosition().y, player, players)) {
                    if (Gameboardimplement.collision(player.getPosition().x - 1, player.getPosition().y, player, players)) {
                        player.getPosition().translate(-1, 0);
                        JSONObject log = new JSONObject();
                        String symbol = Tile.TileSymbol(new Point(player.getPosition()));
                        log.put("movement " + "Player " + player.getID(), symbol);
                        FileHandler.appendlog(log);
                    }
                }
            } else {
                System.out.println("You cannot move to spawn");
            }
        } else {
            System.out.println("Move leftwards not possible. Already at the left edge.");
        }
    }
    public void moveDown(Player player, List<Player> players, Playermode mode) {
        int boardWidth = 10;
        int boardHeight = 10;

        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }
        if (player.getPosition().y < boardHeight - 1) {
            if ((player.getPosition().x != 0 || player.getPosition().y + 1 != boardHeight - 1) &&
                (player.getPosition().x != boardWidth - 1 || player.getPosition().y + 1 != 0)) {
    
                if (Gameboardimplement.MoveChecker(player.getPosition().x, player.getPosition().y + 1, player, players)) {
                    if (Gameboardimplement.collision(player.getPosition().x, player.getPosition().y + 1, player, players)) {
                        player.getPosition().translate(0, 1);
                        JSONObject log = new JSONObject();
                        String symbol = Tile.TileSymbol(new Point(player.getPosition()));
                        log.put("movement " + "Player " + player.getID(), symbol);
                        FileHandler.appendlog(log);
                    }
                }
            } else {
                System.out.println("You cannot move to spawn");
            }
        } else {
            System.out.println("Move downwards not possible. Already at the bottom edge.");
        }
    }


    public void moveRight(Player player, List<Player> players, Playermode mode) {
        int boardWidth=10;
        int boardHeight=10;

        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }

        if (player.getPosition().x < boardWidth - 1) {
            if ((player.getPosition().x + 1 != 0 || player.getPosition().y != boardHeight - 1) &&
                (player.getPosition().x + 1 != boardWidth - 1 || player.getPosition().y != 0)) {
    
                if (Gameboardimplement.MoveChecker(player.getPosition().x + 1, player.getPosition().y, player, players)) {
                    if (Gameboardimplement.collision(player.getPosition().x + 1, player.getPosition().y, player, players)) {
                        player.getPosition().translate(1, 0);
                        JSONObject log = new JSONObject();
                        String symbol = Tile.TileSymbol(new Point(player.getPosition()));
                        log.put("movement " + "Player " + player.getID(), symbol);
                        FileHandler.appendlog(log);
                    }
                }
            } else {
                System.out.println("You cannot move to spawn");
            }
        } else {
            System.out.println("Move rightwards not possible. Already at the right edge.");
        }
    }

    public void Destroy(char direction, Player player, Playermode mode) {
        int boardWidth=10;
        int boardHeight=10;
    
        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }
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
            if (position.y < boardHeight - 1) {

                Point position_player = getPosition(); 
                
                int x = position_player.x;

                int y =  position_player.y + 1;
                Gameboardimplement.destroyTrap(x, y, player);

            } else {
                System.out.println("You can't destroy that!");

            }
            break;
            case 'D':
            if (position.x < boardWidth - 1) {

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
    
    public void spawnTrap(char direction, Player player, List<Player> players, Playermode mode) {

        int boardWidth=10;
        int boardHeight=10;
    
        switch (mode){

            case TWOPLAYER_MODE:
                boardWidth = 10;
                boardHeight = 10;
            break;

            case FOURPLAYER_MODE:
                boardWidth = 20;
                boardHeight = 10;
            
            break;

        }

        switch (direction){

            case 'W':
            if (position.y > 0) {

                Point position_player = getPosition(); 
                
                int x = position_player.x;

                int y =  position_player.y - 1;


                Gameboardimplement.setTrap(x, y, player, players);

            } else {
                System.out.println("You can't place it there");

            }
            break;
            
            case 'A':
            if (position.x > 0) {

                Point position_player = getPosition(); 
                
                int x = position_player.x - 1;

                int y =  position_player.y ;

                Gameboardimplement.setTrap(x, y, player, players);                

            } else {
                System.out.println("You can't place it there");

            }
            break;
            

            case 'S':
            if (position.y < boardHeight) {

                Point position_player = getPosition(); 
                    
                int x = position_player.x;

                int y =  position_player.y + 1;
                
                Gameboardimplement.setTrap(x, y, player, players);

            } else {
                System.out.println("You can't place it there");

            }
            break;
            case 'D':
            if (position.x < boardWidth) {

                Point position_player = getPosition(); 
                
                int x = position_player.x + 1;
                
                int y =  position_player.y;
                Gameboardimplement.setTrap(x, y, player, players);

            } else {
                System.out.println("You can't place it there");
            }
            break;
        }
    }
    
        public void longJump(char direction, Player player, List<Player> players, Playermode mode) {
            int boardWidth=10;
            int boardHeight=10;
        
            switch (mode){

                case TWOPLAYER_MODE:
                    boardWidth = 10;
                    boardHeight = 10;
                break;

                case FOURPLAYER_MODE:
                    boardWidth = 20;
                    boardHeight = 10;
                
                break;

            }
            switch (direction){
    
                case 'W':
                if (this.position.y > 0) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x, position.y - 2, player, players)){

                        
                            this.position.translate(0, -2); 
                            JSONObject log = new JSONObject();
                            String symbol = Tile.TileSymbol(new Point(this.position));
                            log.put("LongJump " + "Player " + player.getID(), symbol);
                            
                            FileHandler.appendlog(log);
                            // this.Ability_long_jump --;
                        }
                        // else {

                        //     System.out.println("You cannot jump there!");
                            // this.Ability_long_jump --;

                        //}
                        }
                } else {
                    System.out.println("LongJump upwards not possible.");
                    // this.Ability_long_jump --;

                }
                break;
                case 'A':
                if (this.position.x > 0) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x - 2, position.y, player, players)){

                            this.position.translate(-2, 0);  
                            JSONObject log = new JSONObject();
                            String symbol = Tile.TileSymbol(new Point(this.position));
                            log.put("LongJump " + "Player " + player.getID(), symbol);
                            FileHandler.appendlog(log);

                            // this.Ability_long_jump --;
                    }
                        // else {

                        //     System.out.println("You cannot jump there!");
                            // this.Ability_long_jump --;

                       //}
                    }
                } else {
                    System.out.println("LongJump leftwards not possible.");
                    // this.Ability_long_jump --;

                }
                break;
    
                case 'S':
                if (this.position.y < boardHeight) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x, position.y + 2, player, players)){


                            this.position.translate(0, 2);
                            JSONObject log = new JSONObject();
                            String symbol = Tile.TileSymbol(new Point(this.position));
                            log.put("LongJump " + "Player " + player.getID(), symbol);
                            FileHandler.appendlog(log);

                            // this.Ability_long_jump --;
                        }
                        // else {

                        //     System.out.println("You cannot jump there!");
                        //}
                            
                    }
                } else {
                    System.out.println("LongJump downwards not possible.");
                    
                }
                break;
    
                case 'D':
                if (this.position.x < boardWidth) {
                    if (Gameboardimplement.LongJump(player)){
                        if (Gameboardimplement.MoveChecker(position.x + 2, position.y, player, players)){


                            this.position.translate(2, 0); 
                            JSONObject log = new JSONObject();
                            String symbol = Tile.TileSymbol(new Point(this.position));
                            log.put("LongJump " + "Player " + player.getID(), symbol);
                            FileHandler.appendlog(log);

                            // this.Ability_long_jump --;
                        }
                        // else {

                        //     System.out.println("You cannot jump there!");
                        //}
                    }
                } else {
                    System.out.println("LongJump rightwards not possible.");
                }
                break;
            
        }
    }


}