import java.awt.Point;

public class Main{

    public static void main(String [] args){

        System.out.println();
        System.out.println("==============================");
        System.out.println("        GAME MAIN MENU");
        System.out.println("==============================\n");
        System.out.println("Welcome to the Game!\n");
        System.out.println("1. Start New Game 2 player mode");
        System.out.println("2. Start New Game 4 player mode");
        System.out.println("3. Load Game");
        System.out.println("4. Load latest Game log");
        System.out.println("0. Exit\n");
        System.out.print("Choose an option: ");

        int choice_main_menu = InputHandler.choice_number();

        switch (choice_main_menu) {

            case 1: 

                Game.start_newgame();

            break;

            case 2:

                // 4 player mode
                break;

            case 3:

            FileHandler.loadGameState("gameState.json");

            break;

            case 4:

            break;


            case 0:

            System.out.println("You are exiting TreasureHunt. Thank you for playing");

        }
}
}