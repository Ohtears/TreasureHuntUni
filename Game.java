import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;

public class Game {


    public static Player currentPlayer = new Player();

    public static int Turn;

    public static Playermode mode;

    public static List<Player> players = new ArrayList<>();

    private static Gameboard gameBoard;

    private static final int game_req_2_win = 100; 

    public static void start_newgame(Playermode mode){

        Turn = 1;

        Player player1 = new Player(1, new Point(0,9));
        currentPlayer = player1;
        Player player2 = new Player(2, new Point(9,0));

        Game.mode = mode;

        switch (mode){

            case TWOPLAYER_MODE:
            
            players.add(player1); 
            players.add(player2); 

            gameBoard = new Gameboardimplement(players, mode);

            gameBoard.display(players, player1);
    
            displayStatus();

            FileHandler.GameloggerSave();

            break;

            case FOURPLAYER_MODE:
            

            Player player3 = new Player(3, new Point(0,0));
            Player player4 = new Player(4, new Point(9,9));

            players.add(player1);
            players.add(player2);
            players.add(player3);
            players.add(player4);

            gameBoard = new Gameboardimplement(players, mode);

            gameBoard.display(players, player1);

            displayStatus();

            FileHandler.GameloggerSave();

            break;
        

        }

        gameLoop();

    }
    public static void gameLoop() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String action = scanner.nextLine();
            if (processAction(action)) {

                Turn++;

                // player_turn = (Turn % players.size() == 0) ? "PL2" : "PL1";


                currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % (players.size()));


                gameBoard.display(players, currentPlayer);
                displayStatus();

                FileHandler.saveGameState("gameState.json", Gameboardimplement.tiles, players, Turn, "PL"+ currentPlayer.getID(), mode);
            }


            if (isGameOver()) {
                System.out.println("Game over!");
                break;
            }
        
        }

        // scanner.close();
    }

    public static boolean processAction(String action) {

        char direction = action.charAt(0);
        char ability = action.length() > 1 ? action.charAt(1) : '\0';  

        if (direction == '0'){
            System.out.println("You are returning to the main menu");
            Main.main(null);
        }


        switch (direction) {
            case 'W':
                if (ability == 'L') {
                    currentPlayer.longJump('W', currentPlayer, players);}
                else if (ability == 'D'){
                currentPlayer.Destroy('W', currentPlayer);
               } 
               else if (ability == 'S'){
                currentPlayer.spawnTrap('W', currentPlayer, players);
            }
                else {
                    currentPlayer.moveUp(currentPlayer, players);
                }
                break;
            case 'A':
                if (ability == 'L') {
                    currentPlayer.longJump('A', currentPlayer, players);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('A', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('A', currentPlayer, players);
                }
                else {
                    currentPlayer.moveLeft(currentPlayer, players);
                }
                break;
            case 'S':
                if (ability == 'L') {
                    currentPlayer.longJump('S', currentPlayer, players);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('S', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('S', currentPlayer, players);
                }
                else {
                    currentPlayer.moveDown(currentPlayer, players);
                }
                break;
            case 'D':
                if (ability == 'L') {
                    currentPlayer.longJump('D', currentPlayer, players);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('D', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('D', currentPlayer, players);
                }
                
                else {
                    currentPlayer.moveRight(currentPlayer, players);
                }
                break;

            default:
                System.out.println("Invalid action. Please try again.");
                return false;
        }
        return true;
    }

    public static void displayStatus() {

        for (Player player : players){

            System.out.print("PL"+player.getID()+ "Score: "+ player.getScore() + " | ");

        }
        System.out.println();

        for (Player player : players){

            System.out.print("PL"+player.getID()+ "HP: "+ player.getHp() + " | ");

        }
        System.out.println();

        for (Player player : players){

            System.out.print("PL"+player.getID()+ " Abilities -> "+ player.getAbilities() + "  ");

        }
        System.out.println();
        System.out.println("------------- Turn: " + Turn + " PL" + currentPlayer.getID() + "'s Turn , Choose an action -------------");
        System.out.println("W. Move Up - A. Move Left - S. Move Down - D. Move Right - D. Destruction - L. Long Jump - S. Spawn Trap");
        System.out.println("in order to use your abilities first Enter the Direction key then the key representing the ability (e.g., DT)");

    }

    public static boolean isGameOver() {

        String filePath = "gameState.json";

        for (Player player : players){

            if(player.getScore() >= game_req_2_win){
                System.out.println("Player"+player.getID()+ " has reached maximum score. Winner winner chicken dinner");
                reset();
                return true;
            }
            else if (player.getHp() <= 0){
                System.out.println("Player"+player.getID()+" has died.\n");
                
                
                players.remove(player);
                
                if (players.size() == 1){

                    System.out.println("Player"+players.get(0).getID()+ " is the last man standing. Winner winner chicken dinner");

                    reset();
                    return true;

                }
            }   
            
        }

        return false;
    }

    public static void continueLoadedGame(List<Player> players, Tile[][] tiles, int turn, String player_turn, int mode1) {
        Game.players = players;
        Game.Turn = turn;

        switch (mode1){

            case 2:

            mode = Playermode.TWOPLAYER_MODE;

            break;

            case 4:

            mode = Playermode.FOURPLAYER_MODE;

        }



        Game.gameBoard = new Gameboardimplement(players, mode);
        Gameboardimplement.setTiles(tiles);

        currentPlayer = players.get(Character.getNumericValue(player_turn.charAt(2)) -1 );
        gameBoard.display(players, currentPlayer);
        displayStatus();
        gameLoop();
    }


    public static void reset(){

        String filePath = "gameState.json";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("{}");
            try (FileWriter filewriter2 = new FileWriter("GameLog.json")){
                filewriter2.write("[]");
                players.clear();
                Turn = 1;
                currentPlayer = new Player(1, new Point(9,0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
