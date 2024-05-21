import java.util.List;
import java.util.Random;

import javax.swing.border.TitledBorder;

public class Gameboardimplement implements Gameboard {
    
    // private static final int DENSITY = 2;

    private static final int SIZE = 10;

    private static final double BOMB_PERCENTAGE = 0.3;
    private static final double WALL_PERCENTAGE = 0.2;

    public static Tile[][] tiles;
    
    // private List<Player> players;

    public Gameboardimplement(List<Player> players) {
        Gameboardimplement.tiles = new Tile[SIZE][SIZE];
        // this.players = players;
        initializeBoard();
    }
    private void initializeBoard() {

        int totalTiles = SIZE * SIZE;
        int totalBombs = (int) (totalTiles * BOMB_PERCENTAGE);
        int totalWalls = (int) (totalTiles * WALL_PERCENTAGE);

        int bombs = totalBombs / 3; 
        int walls = totalWalls / 2; 

        placeTiles(bombs, Tile.Type.BOMB);
        placeTiles(bombs, Tile.Type.TNT);
        placeTiles(bombs, Tile.Type.MOUSETRAP);

        placeTiles(walls, Tile.Type.UNBREAKABLEWALL);
        placeTiles(walls, Tile.Type.BREAKABLEWALL);

        placeTiles(1, Tile.Type.TREASURE);

        placeTiles(1, Tile.Type.SPIN);

        fillRemainingTiles();
    }

    private void placeTiles(int totalTiles, Tile.Type tileType) {
        Random random = new Random();
        while (totalTiles > 0) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);

            if (!((x == 0 || x == SIZE - 1) && (y == 0 || y == SIZE - 1))){
                // && checkDensity(x, y, tileType)
                tiles[x][y] = new Tile(x, y, tileType);
                tiles[0][0] = new Tile(0, 0, Tile.Type.PORTAL);
                tiles[9][9] = new Tile(9, 9, Tile.Type.PORTAL);

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
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile(i, j, Tile.Type.EMPTY);
                }
            }
        }
    }

    @Override
    public void display(List<Player> players) {
        StringBuilder board = new StringBuilder();
        
        board.append("╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗\n");
    
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boolean player1Present = false;
                boolean player2Present = false;
                for (Player player : players) {
                    if (player.getPosition().x == j && player.getPosition().y == i) {
                        if (player.getID() == 1) {
                            board.append("║ PL1 ");
                            player1Present = true;
                        } else if (player.getID() == 2) {
                            board.append("║ PL2 "); 
                            player2Present = true;
                        }
                        break;
                    }
                }
                if (!player1Present && !player2Present) {
                    board.append("║ ").append(tiles[i][j].getSymbol()).append(" ");
                }
            }
            board.append("║\n");
    
            if (i < SIZE - 1) {
                board.append("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣\n");
            }
        }
    
        board.append("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝\n");
    
        System.out.println(board.toString());
    }

    public static void setTrap(int x, int y, Player player) {

        if (tiles[y][x].getType() == Tile.Type.EMPTY){

            if (player.getAbilities("Spawn_Trap") > 0 ){

                tiles[y][x] = new Tile(y, x, Tile.Type.MOUSETRAP);
                player.replaceAbilities("Spawn_Trap");
            }
            else {
                System.out.println("You do not have a suffient amount of traps to place");
            }
        }
        else {
            System.out.println("You cannot place a trap there");
        }

    }

    public static void destroyTrap(int x, int y, Player player) {

        if (tiles[y][x].getType() != Tile.Type.UNBREAKABLEWALL | tiles[y][x].getType() != Tile.Type.TNT){

            if (player.getAbilities("Destroy") > 0){

                tiles[y][x] = new Tile(y, x, Tile.Type.EMPTY);
                player.replaceAbilities("Destroy");
            }
            else {
                System.out.println("You can no longer destroy objects, you have used all your abilities");

            }

        }
        else {

            System.out.println("You cannot destroy this object");

        }
    }
    public static boolean LongJump(Player player){

        if (player.getAbilities("Long_Jump") > 0 ){

            return true;

        }
        else {
            System.out.println("You do not have a suffient amount of long jumps to use");
            return false;
        }

    }


    @Override
    public void saveGame(String filePath) {
        // Save game logic
    }

    @Override
    public void loadGame(String filePath) {
        // Load game logic
    }
}
