import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

public class Gameboardimplement implements Gameboard {
    
    // private static final int DENSITY = 2;

    // private static final int SIZE = 10;
    public static int sizex;

    public static int sizey;

    private static final double BOMB_PERCENTAGE = 0.3;
    private static final double WALL_PERCENTAGE = 0.2;

    public static Tile[][] tiles;
    
    public static void setTiles(Tile[][] loadedTiles) {
        Gameboardimplement.tiles = loadedTiles;
    }

    public Gameboardimplement(List<Player> players, Playermode mode) {

        switch (mode){

            case TWOPLAYER_MODE:

            sizex = 10;
            sizey = 10;

            break;

            case FOURPLAYER_MODE:
            sizex= 10;
            sizey = 20;
            break;
        }

        Gameboardimplement.tiles = new Tile[sizex][sizey];

        initializeBoard();
    }
    private void initializeBoard() {



        int totalTiles = sizex * sizey;
        int totalBombs = (int) (totalTiles * BOMB_PERCENTAGE);
        int totalWalls = (int) (totalTiles * WALL_PERCENTAGE);

        int bombs = totalBombs / 3; 
        int walls = totalWalls / 2; 

        if (sizex == sizey){

        tiles[0][0] = new Tile(0, 0, Tile.Type.PORTAL);
        tiles[9][9] = new Tile(9, 9, Tile.Type.PORTAL);
        }


        placeTiles(bombs, Tile.Type.BOMB, sizex, sizey);
        placeTiles(bombs, Tile.Type.TNT, sizex, sizey);
        placeTiles(bombs, Tile.Type.MOUSETRAP, sizex, sizey);

        placeTiles(walls, Tile.Type.UNBREAKABLEWALL, sizex, sizey);
        placeTiles(walls, Tile.Type.BREAKABLEWALL, sizex, sizey);

        placeTiles(1, Tile.Type.TREASURE, sizex, sizey);

        placeTiles(1, Tile.Type.SPIN, sizex, sizey);

        fillRemainingTiles();
    }

    private void placeTiles(int totalTiles, Tile.Type tileType, int sizex, int sizey) {
        Random random = new Random();
        while (totalTiles > 0) {
            int x = random.nextInt(sizex);
            int y = random.nextInt(sizey);

            if (!((x == 0 || x == sizex - 1) && (y == 0 || y == sizey - 1))){
                // && checkDensity(x, y, tileType)
                tiles[x][y] = new Tile(x, y, tileType);
             
                totalTiles--;
            }
        }
    }




    // private boolean checkDensity(int x, int y, Tile.Type tileType) {
    //     int trapBombCount = 0;
    //     for (int i = x; i < x + 2; i++) {
    //         for (int j = y; j < y + 2; j++) {
    //             if (i >= 0 && i < SIZE && j >= 0 && j < SIZE) {
    //                 if (tiles[i][j] != null && (tiles[i][j].getType() == Tile.Type.BOMB || tiles[i][j].getType() == Tile.Type.MOUSETRAP || tiles[i][j].getType() == Tile.Type.TNT || tiles[i][j].getType() == Tile.Type.UNBREAKABLEWALL || tiles[i][j].getType() == Tile.Type.BREAKABLEWALL)) {
    //                     trapBombCount++;
    //                 }
    //             }
    //         }
    //     }
    //     return trapBombCount <= DENSITY;
    // }

    private void fillRemainingTiles() {
        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizey; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile(i, j, Tile.Type.EMPTY);
                }
            }
        }
    }

    @Override
    public void display(List<Player> players, Player currentPlayer) {
        StringBuilder board = new StringBuilder();
        int startX = Math.max(0, currentPlayer.getPosition().x - 2);
        int startY = Math.max(0, currentPlayer.getPosition().y - 2);
        int endX = Math.min(sizey, currentPlayer.getPosition().x + 3);
        int endY = Math.min(sizex, currentPlayer.getPosition().y + 3);

        board.append("╔");
        for (int i=0; i < sizey - 1; i++){

            board.append("═════╦");

        }
        board.append("═════╗\n");

        
        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizey; j++) {
                if (i >= startY && i < endY && j >= startX && j < endX) {
                    boolean playerPresent = false;
                    for (Player player : players) {
                        if (player.getPosition().x == j && player.getPosition().y == i) {
                            board.append("║ PL").append(player.getID()).append(" ");

                            playerPresent = true;
                            break;
                        }
                    }
                    if (!playerPresent) {
                        board.append("║ ").append(tiles[i][j].getSymbol()).append(" ");

                    }
                } else {
                    board.append("║ ??? ");

                }
            }
            board.append("║ "+(i+1)+"\n");
            
            if (i < sizex - 1) {
                board.append("╠");
                for (int z=0; z < sizey - 1; z++){
                    board.append("═════╬");
                }
                board.append("═════╣───\n");

            }
        }
        board.append("╚");
        for (int z=0; z < sizey - 1; z++){
            board.append("═════╩");
        }
        board.append("═════╝\n");

        
        board.append("   A  ");
        for (int z=0; z< sizey - 1; z++){
            char asci = (char)('B' + z);
            board.append("│  " + asci+ "  ");
        }

        
        System.out.println(board.toString());
    }
    
    public static void setTrap(int x, int y, Player player, List<Player> players) {

        
        if (player.getAbilities("Spawn_Trap") > 0 ){

            if ((x != 0 | y != 9) && (x!=9 | y!=0)){

                if (tiles[y][x].getType() == Tile.Type.EMPTY){

                    if (players.get(0).getPosition().equals(new Point(x, y))){
                        
                        Player playeralt = players.get(0);
                        
                        playeralt.setPosition(new Point(0, 9));

                        playeralt.replaceHp(-1);
                        playeralt.replaceScore(-5);
                        
                        
                    }
                    else if (players.get(1).getPosition().equals(new Point(x, y))){

                        Player playeralt = players.get(1);

                        playeralt.setPosition(new Point(9, 0));

                        playeralt.replaceHp(-1);
                        playeralt.replaceScore(-5);
                    }

                    else{
                        
                        tiles[y][x] = new Tile(y, x, Tile.Type.MOUSETRAP);
                        JSONObject log = new JSONObject();
                        String symbol = Tile.TileSymbol(new Point(x, y));
                        log.put("Spawn " + "Player " + player.getID(), symbol);
                        
                        FileHandler.appendlog(log);

                }
                    player.replaceAbilities("Spawn_Trap");
                }
                else {
                    System.out.println("You cannot place a trap there");
                    player.replaceAbilities("Spawn_Trap");
                }
        }
        else{
            System.out.println("You cannot spawn trap!");
        }
    }
        else {
            System.out.println("You do not have a suffient amount of traps to place");
        }

    }


    public static void destroyTrap(int x, int y, Player player) {

        if (player.getAbilities("Destroy") > 0){
            
            if (tiles[y][x].getType().equals(Tile.Type.BREAKABLEWALL) | tiles[y][x].getType().equals(Tile.Type.BOMB) | tiles[y][x].getType().equals(Tile.Type.MOUSETRAP)){

                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);
                player.replaceAbilities("Destroy");
                JSONObject log = new JSONObject();
                String symbol = Tile.TileSymbol(new Point(x, y));
                log.put("Destroy " + "player " + player.getID(), symbol);
                
                FileHandler.appendlog(log);
            }
            
            else {
                
                System.out.println("You cannot destroy this object");
                player.replaceAbilities("Destroy");
            }
        }
        else {
            System.out.println("You can no longer destroy objects, you have used all your abilities");

        }
    }
    public static boolean LongJump(Player player){

        if (player.getAbilities("Long_Jump") > 0 ){
            player.replaceAbilities("Long_Jump");

            return true;

        }
        else {
            System.out.println("You do not have a suffient amount of long jumps to use");
            return false;
        }

    }

    public static boolean MoveChecker(int x , int y, Player player, List<Player> players){

        switch (tiles[y][x].getType()){

            case Tile.Type.BREAKABLEWALL:

                return false;
            
            case Tile.Type.UNBREAKABLEWALL:
            
                return false;

            case Tile.Type.MOUSETRAP:
                player.replaceHp(-1);
                player.replaceScore(-5);
                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);

                return true;

            case Tile.Type.BOMB:
            
                player.replaceHp(-2);
                player.replaceScore(-10);
                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);

                return true;


            case Tile.Type.TNT:
                player.replaceHp(-3);
                player.replaceScore(-15);
                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);

                return true;
        
            case Tile.Type.TREASURE:

                player.replaceScore(10);
                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);

                Random random = new Random();


                int xnew, ynew;

                do {
                    xnew = random.nextInt(sizex);
                    ynew = random.nextInt(sizey);
                } while ((xnew == 9 && ynew == 0) || (xnew == 0 && ynew == 9));
        
                tiles[xnew][ynew] = new Tile(xnew, ynew, Tile.Type.TREASURE);
            

                return true;
            case Tile.Type.SPIN:
                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);

                Random Random = new Random();

                int xnew_spin, ynew_spin;

                do {
                    xnew_spin = Random.nextInt(sizex);
                    ynew_spin = Random.nextInt(sizey);
                } while ((xnew_spin == 9 && ynew_spin == 0) || (xnew_spin == 0 && ynew_spin == 9));
        
                tiles[xnew_spin][ynew_spin] = new Tile(xnew_spin, ynew_spin, Tile.Type.SPIN);
            

                if (Gameboardimplement.Spin(player, players)){

                    return false;

                }
                
                return true;


            case Tile.Type.PORTAL:

                if (x==0 && y==0){

                    player.setPosition(new Point(9, 9));
                    return false;
                }
                else if (x==9 && y==9){
                    player.setPosition(new Point(0, 0));
                    return false;
                }   

            default:
                break;
            }
        return true;
    }

    public static void show_collision(){



    }

    public static boolean collision(int x, int y, Player player, List<Player> players, Point translation){

        // tiles[y][x].setteroccupation(false);

        Point move = new Point(x,y);

        move.translate(translation.x, translation.y);


        if (tiles[move.y][move.x].getteroccupation()){

            System.out.println("You cannot move to an occupied tile");
            return false;


        }
        tiles[y][x].setteroccupation(false); 
        tiles[move.y][move.x].setteroccupation(true); 

        return true;




    //     if (player.getID() == 1){

    //         if (new Point(x,y).equals(players.get(1).getPosition())){

    //             System.out.println("You cannot move to an occupied tile");
    //             return false;
    //     }}
    //     else if (player.getID() == 2){

    //         if (new Point(x,y).equals(players.get(0).getPosition())){;


    //             System.out.println("You cannot move to an occupied tile");
    //             return false;
    //         }
    //     }
    //     return true;
 }

    public static boolean Spin(Player player, List<Player> players){

        Random random = new Random();

        int randomNumber = random.nextInt(100) + 1; 

        if (randomNumber <= 10) {
           
            int randomNumber1 = random.nextInt(3);

            if (randomNumber1 == 0) {
                
                player.replaceAbilitiesforspin("Destroy"); // 33.33%                      
            } 
            else if (randomNumber1 == 1) {
                player.replaceAbilitiesforspin("Long_Jump"); // 33.33%
            } 
            else {

                player.replaceAbilitiesforspin("Spawn_Trap"); // 33.33%

            }

        System.out.println("You have been awarded an extra ability!!!");
        return false;
        }
        else if (randomNumber <= 50) { //40%

            if (player.getID() == 1){
                player.setPosition(new Point(0, 9));
            }
            else if (player.getID() == 2){
                player.setPosition(new Point(9, 0));
            }
        System.out.println("You have been teleported to your starting position!");        
        return true;    
        } 


        else if (randomNumber <= 60) { //10%

            Random random3 = new Random();

            int randomindex = random3.nextInt(players.size());

            Player randomPlayer = players.get(randomindex);



            if (players.get(0).getID() == (player.getID())){
                
                Player playeralt = players.get(1);
                        
                playeralt.setPosition(new Point(9, 0));
            
        }
            else if (players.get(1).getID() == player.getID()){

                Player playeralt = players.get(0);
                        
                playeralt.setPosition(new Point(0, 9));


            }
        System.out.println("Your opponent has been teleported to their starting position!");
        return false;
        } 
        else if (randomNumber <= 80) { // 20%
            
            Gameboardimplement.placeRandomTNTs(3);

            System.out.println("3 TNT have been spawned across the map");
            return false;
        } 
        else { 
            Gameboardimplement.removeRandomTraps(3); // 20%
            
            System.out.println("3 random traps have been removed from the map!");
            return false;
        }

    }

    public static void placeRandomTNTs(int count) {
        List<Point> emptyTiles = new ArrayList<>();

        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizey; j++) {
                if (tiles[i][j].getType() == Tile.Type.EMPTY) {
                    emptyTiles.add(new Point(j, i));
                }
            }
        }

        Random random = new Random();
        for (int i = 0; i < count && !emptyTiles.isEmpty(); i++) {
            int randomIndex = random.nextInt(emptyTiles.size());
            Point selectedPoint = emptyTiles.remove(randomIndex);

            tiles[selectedPoint.y][selectedPoint.x] = new Tile(selectedPoint.y, selectedPoint.x, Tile.Type.TNT);
        }

    }
    public static void removeRandomTraps(int count) {
        List<Point> trapTiles = new ArrayList<>();

        for (int i = 0; i < sizex; i++) {
            for (int j = 0; j < sizey; j++) {
                Tile.Type type = tiles[i][j].getType();
                if (type .equals(Tile.Type.BOMB)  || type.equals(Tile.Type.MOUSETRAP ) || type.equals(Tile.Type.TNT)) {
                    trapTiles.add(new Point(j, i));
                }
            }
        }

        Random random = new Random();
        for (int i = 0; i < count && !trapTiles.isEmpty(); i++) {
            int randomIndex = random.nextInt(trapTiles.size());
            Point selectedPoint = trapTiles.remove(randomIndex);

            tiles[selectedPoint.y][selectedPoint.x] = new Tile(selectedPoint.y ,selectedPoint.x, Tile.Type.EMPTY);
        }
    }


}