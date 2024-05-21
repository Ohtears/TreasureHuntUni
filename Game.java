import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Point;

public class Game {

    public static String player_turn = "PL1";

    public static int Turn;

    public static List<Player> players = new ArrayList<>();

    private static Gameboard gameBoard;


    public static void start_newgame(){

        Turn = 1;

        Player player1 = new Player(1, new Point(0, 9));
        Player player2 = new Player(2, new Point(9, 0));
        
        players.add(player1); 
        players.add(player2); 

        gameBoard = new Gameboardimplement(players);

        gameBoard.display(players);

        displayStatus(player1, player2);

        gameLoop();

    }

    public static void gameLoop() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String action = scanner.nextLine();
            if (processAction(action)) {

                Turn++;

                player_turn = (Turn % 2 == 0) ? "PL2" : "PL1";


                gameBoard.display(players);
                displayStatus(players.get(0), players.get(1));
            }


            if (isGameOver()) {
                System.out.println("Game over!");
                break;
            }
        }
        scanner.close();
    }

    public static boolean processAction(String action) {
        Player currentPlayer = player_turn.equals("PL1") ? players.get(0) : players.get(1);

        char direction = action.charAt(0);
        char ability = action.length() > 1 ? action.charAt(1) : '\0';  


        switch (direction) {
            case 'W':
                if (ability == 'L') {
                    currentPlayer.longJump('W', currentPlayer);}
                else if (ability == 'D'){
                currentPlayer.Destroy('W', currentPlayer);
               } 
               else if (ability == 'S'){
                currentPlayer.spawnTrap('W', currentPlayer);
            }
                else {
                    currentPlayer.moveUp();
                }
                break;
            case 'A':
                if (ability == 'L') {
                    currentPlayer.longJump('A', currentPlayer);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('A', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('A', currentPlayer);
                }
                else {
                    currentPlayer.moveLeft();
                }
                break;
            case 'S':
                if (ability == 'L') {
                    currentPlayer.longJump('S', currentPlayer);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('S', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('S', currentPlayer);
                }
                else {
                    currentPlayer.moveDown();
                }
                break;
            case 'D':
                if (ability == 'L') {
                    currentPlayer.longJump('D', currentPlayer);}
                else if (ability == 'D'){
                    currentPlayer.Destroy('D', currentPlayer);
                }
                else if (ability == 'S'){
                    currentPlayer.spawnTrap('D', currentPlayer);
                }
                
                else {
                    currentPlayer.moveRight();
                }
                break;

            default:
                System.out.println("Invalid action. Please try again.");
                return false;
        }
        return true;
    }

    public static void displayStatus(Player player1, Player player2) {

        System.out.println("PL1" + " Score: " + player1.getScore() + " | " + "PL2" + " Score: " + player2.getScore());
        System.out.println("PL1" + " HP: " + player1.getHp() + " | " + "PL2" + " HP: " + player2.getHp());
        System.out.println("PL1" + " Abilities -> " + player1.getAbilities() + "\nPL2" + " Abilities -> " + player2.getAbilities());
        System.out.println("------------- Turn: " + Turn + " " + player_turn + "'s Turn , Choose an action -------------");
        System.out.println("W. Move Up - A. Move Left - S. Move Down - D. Move Right - D. Destruction - L. Long Jump - S. Spawn Trap");
        System.out.println("in order to use your abilities first Enter the Direction key then the key representing the ability (e.g., DT)");
    
    
    }

    public static boolean isGameOver() {

        return false;
    }



}
