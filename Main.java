import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Main{

    public static void main(String [] args){

        System.err.println("Welcome");

        List<Player> players = new ArrayList<>();

        players.add(new Player(1, new Point(0, 0))); 
        players.add(new Player(2, new Point(9, 9))); 

        Gameboard gameBoard = new Gameboardimplement(players);

        gameBoard.display();

    }


}